package com.ftc.demo.mapper;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.entities.User;

class UserLoginMapperTest {

	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@BeforeEach
	void before() {
		userLoginMapper = new UserLoginMapperImpl();
	}
	
	@Test
	void testMapToDTO() {
		System.out.println();
		UserLoginDTO mapToDTO = userLoginMapper.mapToDTO(new User("juanito@gmail", "Juantio", "12314", "124123"));
		System.out.println();
	}

}
