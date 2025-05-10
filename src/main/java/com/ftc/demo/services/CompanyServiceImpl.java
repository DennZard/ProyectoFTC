package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.entities.Company;
import com.ftc.demo.mapper.CompanyMapper;
import com.ftc.demo.repositories.CompanyRepository;

public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
		super();
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
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
	public boolean updateCompany(CompanyDTO companyDTO) throws IllegalArgumentException {
		if(companyDTO.id() == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			long id = companyDTO.id();
			if (companyRepository.existsById(id)) {
				companyRepository.deleteById(id);
				companyRepository.save(companyMapper.mapToEntity(companyDTO));
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Optional<CompanyDTO> deleteCompany(CompanyDTO companyDTO) throws IllegalArgumentException {
		if(companyDTO.id() == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			long id = companyDTO.id();
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
		if(companyDTO.id() == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			if (companyRepository.findById(companyDTO.id()).isEmpty()) {
				companyRepository.save(companyMapper.mapToEntity(companyDTO));
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
