package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.ProductCreateDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductCreateMapper {
	public Product mapToEntity(ProductCreateDTO dto);
	
}
