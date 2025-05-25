package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.EmployeeDTO;
import com.ftc.demo.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	public EmployeeDTO mapToDto(Employee employee);

}
