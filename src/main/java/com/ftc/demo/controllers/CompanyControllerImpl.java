package com.ftc.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.services.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyControllerImpl implements CompanyController {
	private final CompanyService companyService;

	public CompanyControllerImpl(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@Override
	@GetMapping("byId")
	public ResponseEntity<CompanyDTO> getCompany(long id) {
		try {
			Optional<CompanyDTO> company = companyService.getCompany(id);
			if (company.isPresent()) {
				return ResponseEntity.ok().body(company.get());
			} else {
				return ResponseEntity.badRequest().eTag("No se encontro la compañia").body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}

	}

	@Override
	@GetMapping("all")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<CompanyDTO> allCompanies = companyService.getAllCompanies();
		if (!allCompanies.isEmpty()) {
			return ResponseEntity.ok().body(allCompanies);
		}
		return ResponseEntity.badRequest().eTag("No se encontraron compañias").body(null);
	}

	@Override
	@PutMapping("update")
	public ResponseEntity<Boolean> updateCompany(CompanyDTO companyDTO) {
		try {
			boolean update = companyService.updateCompany(companyDTO);
			return ResponseEntity.ok().body(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}

	@Override
	@DeleteMapping("delete")
	public ResponseEntity<CompanyDTO> deleteCompany(long id) {
		try {
			Optional<CompanyDTO> deleteCompany = companyService.deleteCompany(id);
			if (deleteCompany.isPresent()) {
				return ResponseEntity.ok().body(deleteCompany.get());
			}
			return ResponseEntity.badRequest().eTag("No se pudo eliminar la compañia").body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}

	@Override
	@PostMapping("save")
	public ResponseEntity<Boolean> saveCompany(CompanyDTO companyDTO) {
		try {
			boolean saveCompany = companyService.saveCompany(companyDTO);
			return ResponseEntity.ok().body(saveCompany);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}

	}

	@Override
	@GetMapping("products")
	public ResponseEntity<List<ProductDetailsDTO>> getProducts(long companyId, UserLoginDTO user) {
		try {
			List<ProductDetailsDTO> products = companyService.getProducts(companyId);
			if (!products.isEmpty()) {
				return ResponseEntity.ok().body(products);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		return ResponseEntity.badRequest().eTag("La solicitud no se pudo procesar").body(null);
	}

}
