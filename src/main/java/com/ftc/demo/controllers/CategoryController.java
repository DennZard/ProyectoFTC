package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.CategoryDTO;

public interface CategoryController {
	public ResponseEntity<List<CategoryDTO>> getCategories();
}
