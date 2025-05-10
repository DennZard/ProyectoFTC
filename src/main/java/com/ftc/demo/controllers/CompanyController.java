package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.CompanyDTO;

public interface CompanyController {
	public ResponseEntity<CompanyDTO> getCompany(long id);
	public ResponseEntity<List<CompanyDTO>> getAllCompanies();
	public ResponseEntity<Boolean> updateCompany(CompanyDTO companyDTO);
	public ResponseEntity<CompanyDTO> deleteCompany(CompanyDTO companyDTO);
	public ResponseEntity<Boolean> saveCompany(CompanyDTO companyDTO);
}
