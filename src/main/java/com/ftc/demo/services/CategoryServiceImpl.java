package com.ftc.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.CategoryDTO;
import com.ftc.demo.entities.Category;
import com.ftc.demo.mapper.CategoryMapper;
import com.ftc.demo.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}



	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> all = categoryRepository.findAll();
		return all.stream().map(categoryMapper::mapToDto).toList();
	}

}
