package com.ftc.demo.services;

import java.sql.Date;

import com.ftc.demo.DTOs.CategoryDTO;
import com.ftc.demo.entities.Company;

public record ProductDetailsDTO(String name, CategoryDTO category,
		float price, Date added, Company company, int sells) {

}
