package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.demo.entities.Product;

@Service
public interface ProductController {
	public ResponseEntity<List<Product>> getAllProducts();
	public ResponseEntity<Boolean> updateProduct();
	public ResponseEntity<Product> deleteProduct(long id);
	public ResponseEntity<Boolean> saveProduct();
	public ResponseEntity<Product> getProduct(long id);
	
}
