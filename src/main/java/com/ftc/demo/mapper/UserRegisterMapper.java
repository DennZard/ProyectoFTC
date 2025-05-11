package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.UserRegisterDTO;
import com.ftc.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
	public User mapToEntity (UserRegisterDTO userRegisterDTO);
	public UserRegisterDTO mapToDTO (User user);
	
}
