package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
