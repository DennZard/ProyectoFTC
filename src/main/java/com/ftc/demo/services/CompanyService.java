package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;

public interface CompanyService {
	public Optional<CompanyDTO> getCompany(long id) throws IllegalArgumentException;
	public List<CompanyDTO> getAllCompanies();
	public boolean updateCompany(CompanyDTO companyDTO) throws IllegalArgumentException;
	public boolean saveCompany(CompanyDTO companyDTO) throws IllegalArgumentException;
	public List<ProductDetailsDTO> getProducts(long id);
	Optional<CompanyDTO> deleteCompany(long id) throws IllegalArgumentException;
}
