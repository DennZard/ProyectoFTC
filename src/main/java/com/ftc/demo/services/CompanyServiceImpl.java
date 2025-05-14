package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.mapper.CompanyMapper;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.repositories.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	private final ProductDetailsMapper productDetailsMapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, ProductDetailsMapper productDetailsMapper) {
		super();
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
		this.productDetailsMapper = productDetailsMapper;
	}
	
	@Override
	public List<ProductDetailsDTO> getProducts(long id) throws IllegalArgumentException,EntityNotFoundException  {
		if (id == 0) throw new IllegalArgumentException("Id no proporcionado");
		Optional<Company> company = companyRepository.findById(id);
		if (company.isPresent()) {
			List<Product> products = company.get().getProducts();
			if (!products.isEmpty()) {
				return products.stream()
					.map(productDetailsMapper::mapToDto)
					.toList();
			} else throw new EntityNotFoundException("No se encontro ningun producto");
		} else throw new EntityNotFoundException("No se encontro la empresa");
	}

	@Override
	public Optional<CompanyDTO> getCompany(long id) throws IllegalArgumentException {
		if (id == 0) throw new IllegalArgumentException("Debes proporcionar un id");
		try {
			Optional<Company> byId = companyRepository.findById(id);
			if (byId.isPresent()) {
				return Optional.of(companyMapper.mapToDto(byId.get()));
			}
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public List<CompanyDTO> getAllCompanies() {
		return companyRepository.findAll()
				.stream()
				.map(companyMapper::mapToDto)
				.toList();
	}

	@Override
	public boolean updateCompany(long id, CompanyDTO companyDTO) throws IllegalArgumentException {
		if(id == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			Optional<Company> company = companyRepository.findById(id);
			if (company.isPresent()) {
				company.get().setId(id);
				company.get().setName(companyDTO.name());
				companyRepository.save(company.get());
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Optional<CompanyDTO> deleteCompany(long id) throws IllegalArgumentException {
//		if(companyDTO.id() == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
//			long id = companyDTO.id();
			Optional<Company> company = companyRepository.findById(id);
			if (company.isPresent()) {
				companyRepository.deleteById(id);
				return Optional.of(companyMapper.mapToDto(company.get()));
			}
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public boolean saveCompany(CompanyDTO companyDTO) throws IllegalArgumentException {
//		if(companyDTO.id() == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
//			if (companyRepository.findById(companyDTO.id()).isEmpty()) {
				companyRepository.save(companyMapper.mapToEntity(companyDTO));
				return true;
//			}
		} catch (Exception e) {
			return false;
		}
	}



}
