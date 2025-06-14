package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.EmployeeCreateDTO;
import com.ftc.demo.DTOs.EmployeeDTO;
import com.ftc.demo.DTOs.EmployeeLoginDTO;
import com.ftc.demo.entities.Employee;
import com.ftc.demo.mapper.EmployeeCreateMapper;
import com.ftc.demo.mapper.EmployeeMapper;
import com.ftc.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private final EmployeeCreateMapper employeeCreateMapper;
	
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, EmployeeCreateMapper employeeCreateMapper) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.employeeCreateMapper = employeeCreateMapper;
	}


	public List<EmployeeDTO> getEmployees() {
		return  employeeRepository.findAll().stream().map(employeeMapper::mapToDto).toList();
	}
	
	public boolean deleteEmployee(long id) {
		Optional<Employee> byId = employeeRepository.findById(id);
		if (byId.isPresent()) {
			Employee employee = byId.get();
			employee.setActive(false);
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}
	
	public boolean createEmployee(EmployeeCreateDTO dto) {
		try {
			employeeRepository.save(employeeCreateMapper.mapToEntity(dto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Optional<EmployeeDTO> login(EmployeeLoginDTO employeeLoginDTO) {
		Optional<Employee> byEmail = employeeRepository.findByEmail(employeeLoginDTO.email());
		if (byEmail.isPresent()) {
			Employee employee = byEmail.get();
			if (employee.getPhone().equals(employeeLoginDTO.phone())) {
				return Optional.of(employeeMapper.mapToDto(employee));
			}
		}
		return Optional.empty();
		
	}
	
}
