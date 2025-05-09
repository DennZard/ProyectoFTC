package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.entities.Product;
import com.ftc.demo.repositories.ProductRepository;

public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public boolean updateProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Product> deleteProduct(long id) {
		Optional<Product> byId = productRepository.findById(id);
		if(byId.isPresent()) {
			productRepository.deleteById(id);
			return byId;
		}
		return Optional.empty();
	}

	@Override
	public boolean saveProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Product> getProduct(long id) {
		Optional<Product> byId = productRepository.findById(id);
		if (byId.isPresent()) {
			return byId;
		}
		return Optional.empty();
	}

}
