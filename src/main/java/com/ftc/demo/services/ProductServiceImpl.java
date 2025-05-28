package com.ftc.demo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.DTOs.ProductBuyDTO;
import com.ftc.demo.DTOs.ProductCreateDTO;
import com.ftc.demo.DTOs.ProductDTO;
import com.ftc.demo.DTOs.ProductDetailsDTO;
import com.ftc.demo.DTOs.ProductSummaryDTO;
import com.ftc.demo.entities.Product;
import com.ftc.demo.mapper.ProductCreateMapper;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.mapper.ProductMapper;
import com.ftc.demo.mapper.ProductSummaryMapper;
import com.ftc.demo.repositories.CategoryRepository;
import com.ftc.demo.repositories.CompanyRepository;
import com.ftc.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductSummaryMapper productSummaryMapper;
	private final ProductDetailsMapper productDetailsMapper;
	private final ProductCreateMapper productCreateMapper;
	private final CategoryRepository categoryRepository;
	private final CompanyRepository companyRepository;
	private final DeliveryServiceImpl deliveryServiceImpl;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductSummaryMapper productSummaryMapper, ProductDetailsMapper productDetailsMapper, ProductCreateMapper productCreateMapper, CompanyRepository companyRepository, CategoryRepository categoryRepository, DeliveryServiceImpl deliveryServiceImpl) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSummaryMapper = productSummaryMapper;
		this.productDetailsMapper = productDetailsMapper;
		this.productCreateMapper = productCreateMapper;
		this.categoryRepository = categoryRepository;
		this.companyRepository = companyRepository;
		this.deliveryServiceImpl = deliveryServiceImpl;
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
		if (productoDTO.id()== 0)  throw new IllegalArgumentException("El id no existe"); 
		if (productRepository.existsById(productoDTO.id())) {
			try {
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
	public boolean saveProduct(ProductCreateDTO productDTO) throws Exception {
		if (productDTO.categoryId() == 0) throw new IllegalArgumentException("No se proporciona id para la categoria");
		if (productDTO.companyId() == 0) throw new IllegalArgumentException("No se proporciona id para la compañia");
		try {
			Product product = productCreateMapper.mapToEntity(productDTO);
			product.setCategory(categoryRepository.findById(productDTO.categoryId()).get() );
			product.setCompany(companyRepository.findById(productDTO.companyId()).get());
			product.setAdded(Date.valueOf(LocalDate.now()));
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			return all.stream().filter(
					prod -> prod.getCompany().isActive())
				.map(productSummaryMapper::mapToDTO)
				.toList();
		}
	}

	@Override
	public boolean buyProduct(ProductBuyDTO productDTO) throws IllegalArgumentException{
		if (productDTO.id() == 0) throw new IllegalArgumentException("No se proporciona id");
		if (productDTO.money() <= 0) throw new IllegalArgumentException("El dinero proporcionado no puede ser 0 o inferior");
		Optional<Product> product = productRepository.findById(productDTO.id());
		Product prod = product.get();
		if (product.isPresent()) {
			Product product2 = prod;
			if (product2.getStock() <= 0) {
				return false;
			}
			if (product2.getPrice() > productDTO.money()) {
				return false;
			} 
		}
		prod.setStock(prod.getStock()-1);
		prod.setSells(prod.getSells()+1);
		productRepository.save(prod);
		deliveryServiceImpl.createDelivery(new DeliveryCreateDTO(productDTO.userId(), productDTO.destination(), productDTO.id()));
		return true;
	}

	@Override
	public List<ProductSummaryDTO> getThreeMostSells() {
		return productRepository.findTop3ByOrderBySellsDesc().stream().map(productSummaryMapper::mapToDTO).toList();
	}



	
}
