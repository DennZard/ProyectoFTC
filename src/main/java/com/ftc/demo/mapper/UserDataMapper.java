package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.UserDataDTO;
import com.ftc.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
	public UserDataDTO mapToDTO(User user);

}
