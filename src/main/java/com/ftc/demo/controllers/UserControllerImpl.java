package com.ftc.demo.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.Response;
import com.ftc.demo.entities.User;
import com.ftc.demo.services.UserService;

@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController{
	
	private final UserService userService;
	
	public UserControllerImpl(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	@CrossOrigin("http://localhost:4200/")
	@PostMapping("register")
	public ResponseEntity<Response> register(@RequestBody UserRegisterDTO customerDTO) {
		try {
			Optional<UserLoginDTO> user = userService.register(customerDTO);
			if (user.isPresent()) {
				return ResponseEntity.ok().body(Response.builder().data(Map.of("user", user.get())).build());
			}
			return ResponseEntity.badRequest().eTag("Registro inválido").body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}

	@Override
	@CrossOrigin("http://localhost:4200/")
	@PostMapping("login")
	public ResponseEntity<Response> login(@RequestBody UserLoginDTO userLoginDTO) {
		Optional<User> login = userService.login(userLoginDTO);
		try {
			if (login.isPresent()) {
				
				return ResponseEntity.ok().body(Response.builder().data(Map.of("user",login.get())).build());
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		return ResponseEntity.badRequest().eTag(Response.builder().status(HttpStatus.BAD_REQUEST).build().toString()).body(null);
	}

}
