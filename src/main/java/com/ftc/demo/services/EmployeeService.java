package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.EmployeeDTO;
import com.ftc.demo.entities.Employee;
import com.ftc.demo.mapper.EmployeeMapper;
import com.ftc.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
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
	
}
