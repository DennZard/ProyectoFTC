package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper {
	public ProductDetailsDTO mapToDto(Product product);
	public Product mapToEntity(ProductDetailsDTO productDTO);
	
}
