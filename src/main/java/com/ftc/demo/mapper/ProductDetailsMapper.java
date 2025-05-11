package com.ftc.demo.mapper;

import com.ftc.demo.entities.Product;
import com.ftc.demo.services.ProductDetailsDTO;

public interface ProductDetailsMapper {
	public ProductDetailsDTO mapToDto(Product product);
	public Product mapToEntity(ProductDetailsDTO productDTO);
}
