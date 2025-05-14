package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.ProductBuyDTO;
import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.ProductSummaryDTO;

public interface ProductService {
	public List<ProductSummaryDTO> getAll();
	public List<ProductSummaryDTO> getByPrefix(String prefix);
	public Optional<ProductDetailsDTO> getDetails(long id);
	public List<ProductDTO> getAllProducts();
	public boolean updateProduct(ProductDTO productoDTO);
	public Optional<ProductDTO> deleteProduct(long id);
	public Optional<ProductDTO> getProduct(long id);
	//TODO El manejo de excepciones
	public boolean saveProduct(ProductDTO productDTO) throws Exception;
	public boolean buyProduct(ProductBuyDTO product) throws IllegalArgumentException;
}
