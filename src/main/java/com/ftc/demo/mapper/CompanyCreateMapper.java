package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.CompanyCreateDTO;
import com.ftc.demo.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyCreateMapper {
	public CompanyCreateDTO mapToDTO(Company company);
	public Company mapToEntity(CompanyCreateDTO companyCreateDTO);
}
