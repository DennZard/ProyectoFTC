package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.CompanyCreateDTO;
import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;

public interface CompanyService {
	public Optional<CompanyDTO> getCompany(long id) throws IllegalArgumentException;
	public List<CompanyDTO> getAllCompanies();
	public List<ProductDetailsDTO> getProducts(long id);
	public Optional<CompanyDTO> deleteCompany(long id) throws IllegalArgumentException;
	public boolean updateCompany(long id, CompanyCreateDTO companyDTO) throws IllegalArgumentException;
	public boolean saveCompany(CompanyCreateDTO companyDTO) throws IllegalArgumentException;
}
