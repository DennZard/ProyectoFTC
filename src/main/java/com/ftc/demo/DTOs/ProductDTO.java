package com.ftc.demo.DTOs;

import java.sql.Date;

public record ProductDTO(Long id,String name, CategoryDTO category, float price, int sells,
		Date added, CompanyDTO company) {
}
