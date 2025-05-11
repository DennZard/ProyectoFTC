package com.ftc.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	@Mapping(target = "category", source = "product.category")
	public ProductDTO mapToDTO(Product product);
	public Product mapToEntity(ProductDTO productDTO);
}
