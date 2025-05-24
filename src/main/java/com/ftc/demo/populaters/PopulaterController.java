package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Category;
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Employee;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.Roles;
import com.ftc.demo.entities.Status;
import com.ftc.demo.entities.User;

import jakarta.annotation.PostConstruct;

@Component
@ConditionalOnProperty(name="spring.jpa.hibernate.ddl-auto",havingValue = "create",matchIfMissing = false)
public class PopulaterController {
	
	private final StatusPopulater statusPopulater;
	private final CategoryPopulater categoryPopulater;
	private final RolesPopulater rolesPopulater;
	private final EmployeePopulater employeePopulater;
	private final UserPopulater userPopulater;
	private final CompanyPopulater companyPopulater;
	private final ProductPopulater productPopulater;
	private final DeliveryPopulater deliveryPopulater;
	
	public PopulaterController(StatusPopulater statusPopulater, CategoryPopulater categoryPopulater, RolesPopulater rolesPopulater, EmployeePopulater employeePopulater, UserPopulater userPopulater, CompanyPopulater companyPopulater, ProductPopulater productPopulater, DeliveryPopulater deliveryPopulater) {
		super();
		this.statusPopulater = statusPopulater;
		this.categoryPopulater = categoryPopulater;
		this.rolesPopulater = rolesPopulater;
		this.employeePopulater = employeePopulater;
		this.userPopulater = userPopulater;
		this.companyPopulater = companyPopulater;
		this.productPopulater = productPopulater;
		this.deliveryPopulater = deliveryPopulater;
	}



	@PostConstruct
	private void populate() {
		List<Status> statuses = statusPopulater.populate();
		List<Category> categories= categoryPopulater.populate();
		List<Roles> roles = rolesPopulater.populate();
		List<Employee> employees = employeePopulater.populate();
		List<User> sellers = userPopulater.populate(roles);
		List<Company> companies = companyPopulater.populate(sellers);
		userPopulater.repopulate(companies);
		List<Product> products = productPopulater.populate(companies, categories);
		List<Company> repopulate = companyPopulater.repopulate(products);
		deliveryPopulater.populate(employees, statuses, products);
	}

}
