package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.ProductSummaryDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductSummaryMapper {
	public Product mapToEntity(ProductSummaryDTO productDTO);
	public ProductSummaryDTO mapToDTO(Product product);
}
