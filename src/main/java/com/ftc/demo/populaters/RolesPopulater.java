package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Roles;
import com.ftc.demo.repositories.RolesRepository;

@Component
public class RolesPopulater {
	private final RolesRepository rolesRepository;

	public RolesPopulater(RolesRepository rolesRepository) {
		super();
		this.rolesRepository = rolesRepository;
	}
	
	public List<Roles> populate() {
		List<Roles> of = List.of(
				new Roles("Admin"),
				new Roles("Customer"),
				new Roles("Seller")
		);
		return rolesRepository.saveAll(of);
	}
	
	
}
