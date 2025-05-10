package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.CategoryDTO;
import com.ftc.demo.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	public CategoryDTO mapToDto(Category category);
	public Category mapToEntity(CategoryDTO categoryDTO);

}
