package com.ftc.demo.controllers;

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

import com.ftc.demo.DTOs.ProductDTO;
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

	// Para admin
	@Override
	@GetMapping("full")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();
		if (!products.isEmpty()) {
			return ResponseEntity.ok().body(products);
		} else {
			return ResponseEntity.badRequest().eTag("No se encontraron productos").body(null);
		}
	}

	@Override
	@PutMapping("update")
	public ResponseEntity<Boolean> updateProduct(ProductDTO productDTO) {
		try {
			return ResponseEntity.ok().body(productService.updateProduct(productDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(false);
		}
		
	}

	@Override
	@DeleteMapping("byId")
	public ResponseEntity<ProductDTO> deleteProduct(@RequestBody long id) {
		Optional<ProductDTO> product = productService.deleteProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		} else {
			return ResponseEntity.badRequest().eTag("No se pudo eliminar el producto").body(null);
		}
	}

	@Override
	@PostMapping("save")
	public ResponseEntity<Boolean> saveProduct(ProductDTO productDTO) {
		try {
			return ResponseEntity.ok().body(productService.saveProduct(productDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag("No se pudo eliminar el producto").body(null);
		}
	};

	@Override
	@GetMapping("byId")
	public ResponseEntity<ProductDTO> getProduct(@RequestBody long id) {
		Optional<ProductDTO> product = productService.getProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		}
		return ResponseEntity.badRequest().eTag("No se encontro el producto").body(null);
	}

}
