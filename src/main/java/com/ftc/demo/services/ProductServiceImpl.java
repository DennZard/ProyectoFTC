package com.ftc.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.controllers.ProductSummaryDTO;
import com.ftc.demo.entities.Product;
import com.ftc.demo.mapper.ProductMapper;
import com.ftc.demo.mapper.ProductSummaryMapper;
import com.ftc.demo.repositories.ProductRepository;

public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductSummaryMapper productSummaryMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductSummaryMapper productSummaryMapper) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSummaryMapper = productSummaryMapper;
		
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> all = productRepository.findAll();
		if (all.isEmpty()) {
			return new ArrayList<>();
		} else {
			return all.stream()
					.map(productMapper::mapToDTO)
					.toList();
		}
	}

	@Override
	public boolean updateProduct(ProductDTO productoDTO) {
		if (productoDTO.id()== null)  throw new IllegalArgumentException("El id no existe"); 
		if (productRepository.existsById(productoDTO.id())) {
			try {
				productRepository.deleteById(productoDTO.id());
				productRepository.save(productMapper.mapToEntity(productoDTO));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public Optional<ProductDTO> deleteProduct(long id) {
		Optional<Product> byId = productRepository.findById(id);
		if(byId.isPresent()) {
			productRepository.deleteById(id);
			return Optional.of(productMapper.mapToDTO(byId.get()));
		}
		return Optional.empty();
	}

	@Override
	public boolean saveProduct(ProductDTO productDTO) throws Exception {
		if (productDTO.id() == null) throw new IllegalArgumentException("No se proporciona id");
		//TODO poner la excepcion en concreto
		if (productRepository.existsById(productDTO.id())) throw new Exception("El producto ya existe");
		try {
			productRepository.save(productMapper.mapToEntity(productDTO));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<ProductDTO> getProduct(long id) {
		Optional<Product> byId = productRepository.findById(id);
		if (byId.isPresent()) {
			return Optional.of(productMapper.mapToDTO(byId.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ProductSummaryDTO> getAll() {
		List<Product> all = productRepository.findAll();
		if (all.isEmpty()) {
			return new ArrayList<>();
		} else {
			return all.stream()
					.map(productSummaryMapper::mapToDTO)
					.toList();
		}
	}
}
