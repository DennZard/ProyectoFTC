package com.ftc.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.entities.Product;
import com.ftc.demo.services.ProductService;

@RestController
@RequestMapping("product")
public class ProductControllerImpl implements ProductController {

	private final ProductService productService;
	
	public ProductControllerImpl(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Override
	@GetMapping("all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		if (!products.isEmpty()) {
			return ResponseEntity.ok().body(products);
		} else {
			return ResponseEntity.badRequest().eTag("No se encontraron productos").body(null);
		}
	}
	
	//Para admin
	@Override
	@PutMapping("byId")
	public ResponseEntity<Boolean> updateProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping("byId")
	public ResponseEntity<Product> deleteProduct(@RequestBody long id) {
		Optional<Product> product = productService.deleteProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		} else {
			return ResponseEntity.badRequest().eTag("No se pudo eliminar el producto").body(null);
		}
	}

	@Override
	@PostMapping("byId")
	public ResponseEntity<Boolean> saveProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("byId")
	public ResponseEntity<Product> getProduct(@RequestBody long id) {
		Optional<Product> product = productService.getProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		}
		return ResponseEntity.badRequest().eTag("No se encontro el producto").body(null);
	}

	

}
