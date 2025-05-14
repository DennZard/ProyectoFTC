package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	public ProductDTO mapToDTO(Product product);
	public Product mapToEntity(ProductDTO productDTO);
}
