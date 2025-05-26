package com.ftc.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.EmployeeCreateDTO;
import com.ftc.demo.DTOs.EmployeeDTO;
import com.ftc.demo.DTOs.EmployeeLoginDTO;
import com.ftc.demo.services.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	
	@GetMapping("all")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> employees = employeeService.getEmployees();
		if (!employees.isEmpty()) {
			return ResponseEntity.ok().body(employees);
		} 
		return ResponseEntity.badRequest().eTag("No se pudo obtener los empleados").body(null);
	}
	
	@DeleteMapping("byId")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity<Boolean> deleteEmployee(@RequestParam long id) {
		return ResponseEntity.ok().body(employeeService.deleteEmployee(id));
	}
	
	@PostMapping("create")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeCreateDTO dto) {
		try {
			return ResponseEntity.ok().body( employeeService.createEmployee(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag("Hubo un fallo inesperado").body(null);
		}
	}
	
	@PostMapping("login")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity<EmployeeDTO> logEmployee(@RequestBody EmployeeLoginDTO dto) {
		Optional<EmployeeDTO> login = employeeService.login(dto);
		if (login.isPresent()) {
			return ResponseEntity.ok().body(login.get());
		}
		return ResponseEntity.badRequest().eTag("No se encontro al empleado").body(null);
	}
	
	
	
}
