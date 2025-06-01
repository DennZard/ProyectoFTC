package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.CompanyCreateDTO;
import com.ftc.demo.DTOs.CompanyDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.UserLoginDTO;
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.Roles;
import com.ftc.demo.entities.User;
import com.ftc.demo.mapper.CompanyCreateMapper;
import com.ftc.demo.mapper.CompanyMapper;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.mapper.UserLoginMapper;
import com.ftc.demo.repositories.CompanyRepository;
import com.ftc.demo.repositories.RolesRepository;
import com.ftc.demo.repositories.UserRepository;
import com.ftc.demo.security.BCryptService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	private final ProductDetailsMapper productDetailsMapper;
	private final CompanyCreateMapper companyCreateMapper;
	private final UserRepository userRepository;
	private final UserLoginMapper userLoginMapper;
	private final RolesRepository rolesRepository;
	private final BCryptService bCryptService;

	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper,
			ProductDetailsMapper productDetailsMapper, CompanyCreateMapper companyCreateMapper,
			UserRepository userRepository, UserLoginMapper userLoginMapper, RolesRepository rolesRepository, BCryptService bCryptService) {
		super();
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
		this.productDetailsMapper = productDetailsMapper;
		this.companyCreateMapper = companyCreateMapper;
		this.userRepository = userRepository;
		this.userLoginMapper = userLoginMapper;
		this.rolesRepository = rolesRepository;
		this.bCryptService = bCryptService;
	}

	@Override
	public List<ProductDetailsDTO> getProducts(long id) throws IllegalArgumentException, EntityNotFoundException {
		if (id == 0)
			throw new IllegalArgumentException("Id no proporcionado");
		Optional<Company> company = companyRepository.findById(id);
		if (company.isPresent()) {
			List<Product> products = company.get().getProducts();
			if (!products.isEmpty()) {
				return products.stream().map(productDetailsMapper::mapToDto).toList();
			} else
				throw new EntityNotFoundException("No se encontro ningun producto");
		} else
			throw new EntityNotFoundException("No se encontro la empresa");
	}

	@Override
	public Optional<CompanyDTO> getCompany(long id) throws IllegalArgumentException {
		if (id == 0)
			throw new IllegalArgumentException("Debes proporcionar un id");
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
		return companyRepository.findAll().stream().map(companyMapper::mapToDto).toList();
	}

	@Override
	public boolean updateCompany(long id, CompanyCreateDTO companyDTO) throws IllegalArgumentException {
		if (id == 0)
			throw new IllegalArgumentException("Id no proporcionado");
		try {
			Optional<Company> companyOptional = companyRepository.findById(id);
			if (companyOptional.isPresent()) {
				Company company = companyCreateMapper.mapToEntity(companyDTO);
				Optional<User> byEmail = userRepository.findByEmail(companyDTO.owner().email());
				if (byEmail.isPresent()) {
					User owner = byEmail.get();
					company.setOwner(owner);
					company.setId(companyOptional.get().getId());
					Optional<Company> map = companyOptional.map((comp) -> {
						return companyRepository.save(company);
					});
					if (map.isPresent()) {
						owner.setCompany(map.get());
						userRepository.save(owner);
						return true;
					}
				}
			}
		} catch (ConstraintViolationException e) {
			return false;
		}
		return false;
	}

	private boolean checkUser(Company company, UserLoginDTO login, Optional<User> userOpt) {
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (bCryptService.verifyPassword(login.password(), user.getPassword()) && user.getEmail().equals(login.email())) {
				company.setOwner(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<CompanyDTO> deleteCompany(long id) throws IllegalArgumentException {
		if (id == 0)
			throw new IllegalArgumentException("Id no proporcionado");
		try {
			Optional<Company> companyOpt = companyRepository.findById(id);
			if (companyOpt.isPresent()) {
				Company company = companyOpt.get();
				User owner = company.getOwner();
				owner.setCompany(null);
				userRepository.save(owner);
				company.setActive(false);
				company.setOwner(null);
				companyRepository.save(company);
				return Optional.of(companyMapper.mapToDto(company));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public boolean saveCompany(CompanyCreateDTO companyDTO) throws IllegalArgumentException, IllegalStateException {
		Optional<User> byUsername = userRepository.findByEmail(companyDTO.owner().email());
		if (byUsername.isPresent()) {
			User user = byUsername.get();
			if (user.getCompany() != null) throw new IllegalStateException("El usuario ya tiene una compañia");
			try {
				if (bCryptService.verifyPassword(companyDTO.owner().password(), user.getPassword())) {
					Company company = companyCreateMapper.mapToEntity(companyDTO);
					Set<Roles> roles = user.getRoles();
					roles.add(rolesRepository.findById(3l).get());
					user.setRoles(roles);
					company.setOwner(user);
					Company save = companyRepository.save(company);
					user.setCompany(save);
					userRepository.save(user);
					
					return true;
				}
				return false;
			} catch (Exception e) {
			}
		}
		return false;
	}

}
