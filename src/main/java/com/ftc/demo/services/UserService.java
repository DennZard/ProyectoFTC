package com.ftc.demo.services;

import java.util.Optional;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.User;

public interface UserService {
	public Optional<UserLoginDTO> register(UserRegisterDTO userRegisterDTO);
	//TODO
	public Optional<User> login(UserLoginDTO userLoginDTO);
}
