package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.CompanyCreateDTO;
import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.UserLoginDTO;

public interface CompanyController {
	public ResponseEntity<CompanyDTO> getCompany(long id);
	public ResponseEntity<List<CompanyDTO>> getAllCompanies();
	public ResponseEntity<CompanyDTO> deleteCompany(long id);
	public ResponseEntity<Boolean> updateCompany(long id, CompanyCreateDTO companyDTO);
	public ResponseEntity<Boolean> saveCompany(CompanyCreateDTO companyDTO);
	public ResponseEntity<List<ProductDetailsDTO>> getProducts(long companyId);
	
}
