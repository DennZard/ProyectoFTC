package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.UserDTO;
import com.ftc.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	public UserDTO mapToDto(User user);
}
