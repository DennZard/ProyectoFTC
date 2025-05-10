package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.entities.Product;

@Service
public interface ProductController {
	public ResponseEntity<List<ProductDTO>> getAllProducts();
	public ResponseEntity<Boolean> updateProduct();
	public ResponseEntity<ProductDTO> deleteProduct(long id);
	public ResponseEntity<Boolean> saveProduct();
	public ResponseEntity<ProductDTO> getProduct(long id);
	
}
