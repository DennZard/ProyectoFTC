package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.RolesDTO;
import com.ftc.demo.entities.Roles;

@Mapper(componentModel = "spring")
public interface RolesMapper {
	public RolesDTO mapToDTO(Roles roles);

}
