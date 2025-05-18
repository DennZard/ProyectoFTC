package com.ftc.demo.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
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
	@PostMapping("register")
	public ResponseEntity<UserLoginDTO> register(@RequestBody UserRegisterDTO customerDTO) {
		try {
			Optional<UserLoginDTO> user = userService.register(customerDTO);
			if (user.isPresent()) {
				return ResponseEntity.ok().body(user.get());
			}
			return ResponseEntity.badRequest().eTag("Registro inválido").body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}

	@Override
	@GetMapping("login")
	public ResponseEntity<Boolean> login(@RequestBody UserLoginDTO userLoginDTO) {
		Optional<User> login = userService.login(userLoginDTO);
		try {
			if (login.isPresent()) {
				return ResponseEntity.ok().body(true);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		return ResponseEntity.badRequest().eTag("Comprueba el usuario o la contraseña").body(false);
	}

}
