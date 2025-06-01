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
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.User;
import com.ftc.demo.mapper.ProductCreateMapper;
import com.ftc.demo.mapper.ProductDetailsMapper;
import com.ftc.demo.mapper.ProductMapper;
import com.ftc.demo.mapper.ProductSummaryMapper;
import com.ftc.demo.repositories.CategoryRepository;
import com.ftc.demo.repositories.CompanyRepository;
import com.ftc.demo.repositories.ProductRepository;
import com.ftc.demo.repositories.UserRepository;

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
	private final UserRepository userRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductSummaryMapper productSummaryMapper, ProductDetailsMapper productDetailsMapper, ProductCreateMapper productCreateMapper, CompanyRepository companyRepository, CategoryRepository categoryRepository, DeliveryServiceImpl deliveryServiceImpl, UserRepository userRepository) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSummaryMapper = productSummaryMapper;
		this.productDetailsMapper = productDetailsMapper;
		this.productCreateMapper = productCreateMapper;
		this.categoryRepository = categoryRepository;
		this.companyRepository = companyRepository;
		this.deliveryServiceImpl = deliveryServiceImpl;
		this.userRepository = userRepository;
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
			product.setCategory(categoryRepository.findById(productDTO.categoryId()).get());
			Company company = companyRepository.findById(productDTO.companyId()).get();
			product.setCompany(company);
			product.setAdded(Date.valueOf(LocalDate.now()));
			productRepository.save(product);
			company.getProducts().add(product);
			companyRepository.save(company);
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
		if (productDTO.userId() == 0) throw new IllegalArgumentException("No hay ningun usuario");
		Optional<Product> product = productRepository.findById(productDTO.id());
		Optional<User> byId = userRepository.findById(productDTO.userId());
		if (byId.isPresent()) {
			User user = byId.get();
			if (product.isPresent()) {
				Product prod = product.get();
				if (prod.getStock() <= 0) {
					return false;
				}
				if (prod.getPrice() > user.getMoney()) {
					return false;
				}
				prod.setStock(prod.getStock()-1);
				prod.setSells(prod.getSells()+1);
				user.setMoney(user.getMoney()-prod.getPrice());
				userRepository.save(user);
				productRepository.save(prod);
				deliveryServiceImpl.createDelivery(new DeliveryCreateDTO(productDTO.userId(), productDTO.destination(), productDTO.id()));
			}
		}
		return true;
	}

	@Override
	public List<ProductSummaryDTO> getThreeMostSells() {
		return productRepository.findTop3ByOrderBySellsDesc().stream().map(productSummaryMapper::mapToDTO).toList();
	}



	
}
