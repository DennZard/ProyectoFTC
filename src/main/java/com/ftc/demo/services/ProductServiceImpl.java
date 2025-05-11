package com.ftc.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.controllers.ProductSummaryDTO;
import com.ftc.demo.entities.Product;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.mapper.ProductMapper;
import com.ftc.demo.mapper.ProductSummaryMapper;
import com.ftc.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductSummaryMapper productSummaryMapper;
	private final ProductDetailsMapper productDetailsMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductSummaryMapper productSummaryMapper, ProductDetailsMapper productDetailsMapper) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSummaryMapper = productSummaryMapper;
		this.productDetailsMapper = productDetailsMapper;
	}
	
	@Override
	public Optional<ProductDetailsDTO> getDetails(long id) throws IllegalArgumentException {
		if (id == 0) throw new IllegalArgumentException("El id no puede ser nulo");
		Optional<Product> products = productRepository.findById(id);
		if (products.isPresent()) {
			return Optional.of(productDetailsMapper.mapToDto(products.get()));
		}
		return Optional.empty();
	}
	
	@Override
	public List<ProductSummaryDTO> getByPrefix(String prefix) throws IllegalArgumentException {
		if (prefix == null || prefix == "" || prefix == " ") throw new IllegalArgumentException("Debes proporcionar un prefijo");
		List<Product> products = productRepository.findByNameIgnoreCaseStartingWith(prefix);
		if (!products.isEmpty()) {
			return products
					.stream()
					.map(productSummaryMapper::mapToDTO)
					.toList();
		}
		return new ArrayList<>();
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
	public boolean updateProduct(ProductDTO productoDTO) throws IllegalArgumentException {
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
	public Optional<ProductDTO> deleteProduct(long id) throws IllegalArgumentException {
		if (id == 0) throw new IllegalArgumentException("No se proporciona id");
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
	public Optional<ProductDTO> getProduct(long id) throws IllegalArgumentException {
		if (id == 0) throw new IllegalArgumentException("No se proporciona id");
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
