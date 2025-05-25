package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.EmployeeCreateDTO;
import com.ftc.demo.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeCreateMapper {
	public Employee mapToEntity(EmployeeCreateDTO dto);

}
