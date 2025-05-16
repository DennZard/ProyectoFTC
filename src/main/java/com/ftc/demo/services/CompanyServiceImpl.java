package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.CompanyCreateDTO;
import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.User;
import com.ftc.demo.mapper.CompanyCreateMapper;
import com.ftc.demo.mapper.CompanyMapper;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.mapper.UserLoginMapper;
import com.ftc.demo.repositories.CompanyRepository;
import com.ftc.demo.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	private final ProductDetailsMapper productDetailsMapper;
	private final CompanyCreateMapper companyCreateMapper;
	private final UserRepository userRepository;
	private final UserLoginMapper userLoginMapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, ProductDetailsMapper productDetailsMapper, CompanyCreateMapper companyCreateMapper, UserRepository userRepository, UserLoginMapper userLoginMapper) {
		super();
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
		this.productDetailsMapper = productDetailsMapper;
		this.companyCreateMapper = companyCreateMapper;
		this.userRepository = userRepository;
		this.userLoginMapper = userLoginMapper;
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
	public boolean updateCompany(long id, CompanyCreateDTO companyDTO) throws IllegalArgumentException {
		if(id == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
//			Optional<Company> companyOptional = companyRepository.findById(id);
//			if (companyOptional.isPresent()) {
//				Company company = companyOptional.get();
//				company.setName(companyDTO.name());
//				UserLoginDTO login = companyDTO.owner();
//				Optional<User> userOpt = userRepository.findByUsername(login.username());
//				if (checkUser(company, login, userOpt)) {
//					company.setOwner(userOpt.get());
//					companyRepository.save(company);
//					return true;
//				}
//			}
			Optional<Company> companyOptional = companyRepository.findById(id);
			if (companyOptional.isPresent()) {
				Optional<User> save = userRepository.findByUsername(userLoginMapper.mapToEntity(companyDTO.owner()).getUsername()); 
				Company company = companyOptional.get();
				company.setOwner(save.get());
				companyRepository.save(company);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
		return false;
	}

	private boolean checkUser(Company company, UserLoginDTO login, Optional<User> userOpt) {
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (login.password().equals(user.getPassword()) && user.getEmail().equals(login.email())) {
				company.setOwner(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<CompanyDTO> deleteCompany(long id) throws IllegalArgumentException {
		if(id == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
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
	public boolean saveCompany(CompanyCreateDTO companyDTO) throws IllegalArgumentException {
		try {
				Optional<User> byUsername = userRepository.findByUsername(companyDTO.owner().username());
				if (byUsername.isPresent()) {
					Company company = companyCreateMapper.mapToEntity(companyDTO);
					company.setOwner(byUsername.get());
					companyRepository.save(company);
					return true;
				}
		} catch (Exception e) {
			return false;
		}
		return false;
	}



}
