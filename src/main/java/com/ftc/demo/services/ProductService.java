package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.controllers.ProductSummaryDTO;

public interface ProductService {
	public List<ProductSummaryDTO> getAll();
	public List<ProductSummaryDTO> getByPrefix(String prefix);
	public Optional<ProductDetailsDTO> getDetails(long id);
	public List<ProductDTO> getAllProducts();
	public boolean updateProduct(ProductDTO productoDTO);
	public Optional<ProductDTO> deleteProduct(long id);
	public Optional<ProductDTO> getProduct(long id);
	//TODO
	public boolean saveProduct(ProductDTO productDTO) throws Exception;
}
