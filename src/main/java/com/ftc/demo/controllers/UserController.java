package com.ftc.demo.controllers;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;

public interface UserController {
	public ResponseEntity<UserLoginDTO> register(UserRegisterDTO customerDTO);
	
}
