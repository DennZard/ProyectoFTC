package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;


import com.ftc.demo.entities.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	public boolean updateProduct();
	public Optional<Product> deleteProduct(long id);
	public boolean saveProduct();
	public Optional<Product> getProduct(long id);
}
