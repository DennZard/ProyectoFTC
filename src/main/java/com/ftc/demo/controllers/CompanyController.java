package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.UserLoginDTO;

public interface CompanyController {
	public ResponseEntity<CompanyDTO> getCompany(long id);
	public ResponseEntity<List<CompanyDTO>> getAllCompanies();
	public ResponseEntity<Boolean> updateCompany(CompanyDTO companyDTO);
	public ResponseEntity<Boolean> saveCompany(CompanyDTO companyDTO);
	public ResponseEntity<CompanyDTO> deleteCompany(long id);
	//El usuario va a ser para una futura validacion
	public ResponseEntity<List<ProductDetailsDTO>> getProducts(long id, UserLoginDTO user);
}
