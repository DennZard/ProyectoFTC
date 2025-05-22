package com.ftc.demo.controllers;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.Response;

public interface UserController {
	public ResponseEntity<Response> register(UserRegisterDTO customerDTO);
	public ResponseEntity<Response> login(UserLoginDTO userLoginDTO);
	
}
