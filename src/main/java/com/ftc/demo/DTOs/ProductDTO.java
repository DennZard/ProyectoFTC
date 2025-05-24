package com.ftc.demo.DTOs;

import java.sql.Date;

public record ProductDTO(long id, String name, CategoryDTO category,
		float price, Date added,CompanyDTO company, int sells) {
	}
