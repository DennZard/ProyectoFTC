package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.User;
import com.ftc.demo.repositories.CompanyRepository;

@Component
public class CompanyPopulater {
	
	private final CompanyRepository companyRepository;
	
	public CompanyPopulater(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}



	public List<Company> populate(List<User> sellers) {
		List<Company> of = List.of(
			new Company("Company1", sellers.get(0), null),	
			new Company("Company2", sellers.get(1), null),	
			new Company("Company3", sellers.get(2), null)	
		);
		
		
		
		return companyRepository.saveAll(of);
		
	}

}
