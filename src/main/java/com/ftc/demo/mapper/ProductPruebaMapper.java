package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductPruebaMapper {
	public ProductDetailsDTO mapToDto(Product product);

}
