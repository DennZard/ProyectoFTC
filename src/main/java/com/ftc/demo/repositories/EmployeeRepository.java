package com.ftc.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Optional<Employee> findByEmail(String email);
}
