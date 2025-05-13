package com.ftc.demo.populaters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ftc.demo.repositories.RolesRepository;

@SpringBootTest
class UserPopulaterTest {
	@Autowired
	UserPopulater userPopulater;
	@Autowired
	RolesRepository rolesRepository;

	@Test
	void testPopulate() {
		userPopulater.populate(rolesRepository.findAll());
	}

}
