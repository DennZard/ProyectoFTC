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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.ProductBuyDTO;
import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.ProductSummaryDTO;
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
	@PutMapping("buy")
	public ResponseEntity<Boolean> buyProduct(@RequestBody ProductBuyDTO productBuyDTO) {
		try {
			boolean buyProduct = productService.buyProduct(productBuyDTO);
			return ResponseEntity.ok().body(buyProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		
	}
	
	@Override
	@GetMapping("details")
	public ResponseEntity<ProductDetailsDTO> getDetails(@RequestParam long id) {
		try {
			Optional<ProductDetailsDTO> details = productService.getDetails(id);
			if (details.isPresent()) {
				return ResponseEntity.ok().body(details.get());
			}
			return ResponseEntity.badRequest().eTag("No se encontro el producto").body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}

	@Override
	@GetMapping("prefix")
	public ResponseEntity<List<ProductSummaryDTO>> getByPrefix(@RequestParam String prefix) {
		try {
			List<ProductSummaryDTO> products = productService.getByPrefix(prefix);
			if (!products.isEmpty()) {
				return ResponseEntity.ok().body(products);
			}
			return ResponseEntity.badRequest().eTag("No se encontraron productos").body(null);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		
	}
	
	@Override
	@GetMapping("all")
	public ResponseEntity<List<ProductSummaryDTO>> getAll() {
		List<ProductSummaryDTO> products = productService.getAll();
		if (!products.isEmpty()) {
			return ResponseEntity.ok().body(products);
		} else {
			return ResponseEntity.badRequest().eTag("No se encontraron productos").body(null);
		}
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
	public ResponseEntity<Boolean> updateProduct(@RequestBody ProductDTO productDTO) {
		try {
			return ResponseEntity.ok().body(productService.updateProduct(productDTO));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(false);
		}
	}

	@Override
	@DeleteMapping("byId")
	public ResponseEntity<ProductDTO> deleteProduct(@RequestParam long id) {
		Optional<ProductDTO> product = productService.deleteProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		} else {
			return ResponseEntity.badRequest().eTag("No se pudo eliminar el producto").body(null);
		}
	}

	@Override
	@PostMapping("save")
	public ResponseEntity<Boolean> saveProduct(@RequestBody ProductDTO productDTO) {
		try {
			return ResponseEntity.ok().body(productService.saveProduct(productDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag("No se pudo guardar el producto").body(null);
		}
	};

	@Override
	@GetMapping("byId")
	public ResponseEntity<ProductDTO> getProduct(@RequestParam long id) {
		Optional<ProductDTO> product = productService.getProduct(id);
		if (product.isPresent()) {
			return ResponseEntity.ok().body(product.get());
		}
		return ResponseEntity.badRequest().eTag("No se encontro el producto").body(null);
	}

	

	

	

}
