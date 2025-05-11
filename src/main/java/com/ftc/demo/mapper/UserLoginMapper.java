package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserLoginMapper {
	public User mapToEntity (UserLoginDTO userRegisterDTO);
	public UserLoginDTO mapToDTO (User user);
}
