package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.CategoryDTO;
import com.ftc.demo.services.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryControllerImpl implements CategoryController{

	private final CategoryService categoryService;
	
	public CategoryControllerImpl(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@Override
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("all")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categories = categoryService.getCategories();
		if (!categories.isEmpty()) {
			return ResponseEntity.ok().body(categories);
		}
		return ResponseEntity.badRequest().body(null);
		
	}

}
