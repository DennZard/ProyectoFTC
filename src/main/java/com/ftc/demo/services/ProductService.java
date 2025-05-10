package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.controllers.ProductSummaryDTO;

public interface ProductService {
	public List<ProductDTO> getAllProducts();
	public boolean updateProduct(ProductDTO productoDTO);
	public Optional<ProductDTO> deleteProduct(long id);
	public Optional<ProductDTO> getProduct(long id);
	public boolean saveProduct(ProductDTO productDTO) throws Exception;
	public List<ProductSummaryDTO> getAll();
}
