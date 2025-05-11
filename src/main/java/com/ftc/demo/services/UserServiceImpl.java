package com.ftc.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.User;
import com.ftc.demo.mapper.UserLoginMapper;
import com.ftc.demo.mapper.UserRegisterMapper;
import com.ftc.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserRegisterMapper userRegisterMapper;
	private final UserLoginMapper userLoginMapper;
	
	public UserServiceImpl(UserRepository userRepository, UserRegisterMapper userRegisterMapper, UserLoginMapper userLoginMapper) {
		super();
		this.userRepository = userRepository;
		this.userRegisterMapper = userRegisterMapper;
		this.userLoginMapper = userLoginMapper;
	}

	@Override
	public Optional<UserLoginDTO> register(UserRegisterDTO userRegisterDTO) throws IllegalArgumentException {
		if (userRegisterDTO.email() == null || userRegisterDTO.password() == null 
				|| userRegisterDTO.username()  == null || userRegisterDTO.phone() == null)
			throw new IllegalArgumentException("Debes proporcionar todos los valores");
		try {
			User user = userRepository.save(userRegisterMapper.mapToEntity(userRegisterDTO));
			return Optional.of(userLoginMapper.mapToDTO(user));
		} catch (Exception e) {
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<User> login() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
