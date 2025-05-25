package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.entities.Company;


@Mapper(componentModel = "spring")
public interface CompanyMapper {
	public CompanyDTO mapToDto(Company company);
	public Company mapToEntity(CompanyDTO companyDTO);
}
