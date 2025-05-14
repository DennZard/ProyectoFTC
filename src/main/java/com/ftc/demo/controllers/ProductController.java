package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.ProductBuyDTO;
import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.ProductSummaryDTO;

public interface ProductController {
	public ResponseEntity<List<ProductDTO>> getAllProducts();
	public ResponseEntity<ProductDTO> deleteProduct(long id);
	public ResponseEntity<ProductDTO> getProduct(long id);
	public ResponseEntity<Boolean> updateProduct(ProductDTO productDTO);
	public ResponseEntity<Boolean> saveProduct(ProductDTO productDTO);
	public ResponseEntity<List<ProductSummaryDTO>> getAll();
	public ResponseEntity<List<ProductSummaryDTO>> getByPrefix(String prefix);
	public ResponseEntity<ProductDetailsDTO> getDetails(long id);
	public ResponseEntity<Boolean> buyProduct(ProductBuyDTO productBuyDTO);
}
