package com.ftc.demo.populaters;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.User;
import com.ftc.demo.repositories.CompanyRepository;
import com.ftc.demo.repositories.UserRepository;

@Component
public class CompanyPopulater {
	
	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;
	private List<Company> of;
	
	public CompanyPopulater(CompanyRepository companyRepository, UserRepository userRepository) {
		super();
		this.companyRepository = companyRepository;
		this.userRepository = userRepository;
	}



	public List<Company> populate(List<User> sellers) {
		of = List.of(
			new Company("Company1", sellers.get(0)),	
			new Company("Company2", sellers.get(1)),	
			new Company("Company3", sellers.get(2))	
		);
		return companyRepository.saveAll(of);
	}
	
	public List<Company> repopulate(List<Product> products) {
		of.get(0).setProducts(List.of(products.get(0), products.get(1), products.get(2)));
		of.get(1).setProducts(List.of(products.get(3), products.get(4), products.get(5), products.get(6)));
		of.get(2).setProducts(List.of(products.get(7), products.get(8), products.get(9)));
		return companyRepository.saveAll(of);
	}

}
