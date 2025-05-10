package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.entities.Product;

public interface ProductController {
	public ResponseEntity<List<ProductDTO>> getAllProducts();
	public ResponseEntity<ProductDTO> deleteProduct(long id);
	public ResponseEntity<ProductDTO> getProduct(long id);
	public ResponseEntity<Boolean> updateProduct(ProductDTO productDTO);
	public ResponseEntity<Boolean> saveProduct(ProductDTO productDTO);
	public ResponseEntity<List<ProductSummaryDTO>> getAll();
}
