package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Employee;
import com.ftc.demo.repositories.EmployeeRepository;

@Component
public class EmployeePopulater {
	
	private final EmployeeRepository employeeRepository;

	public EmployeePopulater(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> populate() {
		List<Employee> of = List.of(
				new Employee("Alice", "Smith", "alice.smith@example.com", "123-456-7890"),
	            new Employee("Bob", "Johnson", "bob.johnson@example.com", "234-567-8901"),
	            new Employee("Carol", "Williams", "carol.williams@example.com", "345-678-9012"),
	            new Employee("David", "Brown", "david.brown@example.com", "456-789-0123"),
	            new Employee("Eva", "Davis", "eva.davis@example.com", "567-890-1234")
		);
		return employeeRepository.saveAll(of);
	}

}
