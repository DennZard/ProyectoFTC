package com.ftc.demo.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.UserDataDTO;
import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.Roles;
import com.ftc.demo.entities.User;
import com.ftc.demo.mapper.UserDataMapper;
import com.ftc.demo.mapper.UserLoginMapper;
import com.ftc.demo.mapper.UserRegisterMapper;
import com.ftc.demo.repositories.RolesRepository;
import com.ftc.demo.repositories.UserRepository;
import com.ftc.demo.security.BCryptService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserRegisterMapper userRegisterMapper;
	private final UserLoginMapper userLoginMapper;
	private final RolesRepository rolesRepository;
	private final UserDataMapper userDataMapper;
	private final BCryptService bCryptService;
	
	public UserServiceImpl(UserRepository userRepository, UserRegisterMapper userRegisterMapper, UserLoginMapper userLoginMapper, RolesRepository rolesRepository, UserDataMapper userDataMapper, BCryptService bCryptService) {
		super();
		this.userRepository = userRepository;
		this.userRegisterMapper = userRegisterMapper;
		this.userLoginMapper = userLoginMapper;
		this.rolesRepository = rolesRepository;
		this.userDataMapper = userDataMapper;
		this.bCryptService = bCryptService;
	}

	@Override
	public Optional<UserLoginDTO> register(UserRegisterDTO userRegisterDTO) throws IllegalArgumentException {
		if (userRegisterDTO.email() == null || userRegisterDTO.password() == null 
				|| userRegisterDTO.username()  == null || userRegisterDTO.phone() == null)
			throw new IllegalArgumentException("Debes proporcionar todos los valores");
		try {
			HashSet<Roles> roles = new HashSet<>();
			Optional<Roles> rol = rolesRepository.findById(2l);
			if (rol.isPresent()) {
				roles.add(rol.get());
				User user = new User(userRegisterDTO.email(), userRegisterDTO.username(),
						bCryptService.hashPassword(userRegisterDTO.password()), userRegisterDTO.phone(), 0);
				user.setRoles(roles);
				userRepository.save(user);
				return Optional.of(userLoginMapper.mapToDTO(user));
			} 
			return Optional.empty();
		} catch (Exception e) {
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<UserDataDTO> login(UserLoginDTO userLoginDTO) {
		if (userLoginDTO.email() == null || userLoginDTO.password() == null ) 
			throw new IllegalArgumentException("Debes proporcionar todos los valores"); 
		try {
			Optional<User> byUsername = userRepository.findByEmail(userLoginDTO.email());
			if (byUsername.isPresent()) {
				User user = byUsername.get();
				if (user.getEmail().equals(userLoginDTO.email()) && bCryptService.verifyPassword(userLoginDTO.password(),user.getPassword())) {
					return Optional.of(userDataMapper.mapToDTO(user));
				}
			}
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

}
