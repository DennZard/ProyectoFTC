package com.ftc.demo.populaters;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Category;
import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Product;
import com.ftc.demo.repositories.ProductRepository;

@Component
public class ProductPopulater {
	
	private final ProductRepository productRepository;
	private List<String> images = new ArrayList<>();
	

	public ProductPopulater(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	public List<Product> populate(List<Company> companies, List<Category> categories) {
		
		images.add("https://img.fruugo.com/product/9/43/1363819439_max.jpg");
		images.add("https://p1-ofp.static.pub/medias/bWFzdGVyfHJvb3R8MTY1MTg2fGltYWdlL3BuZ3xoMDMvaDI4Lzk4ODE5MjAyMDg5MjYucG5nfDJiNTJkMjkxYzYzYmEyODRmOTlmNTUzOGJhODlkYjcxODgwMjM0Y2QwODMyODJmZGJiZjE3NDUyMWY2OTdhNzI/lenovo-ideapad-320-15-hero.png");
		images.add("https://www.almacenesalvarez.com/4922-superlarge_default/batidora-oster-ofp093-amasadora-500w.jpg");
		images.add("https://m.media-amazon.com/images/I/7126LijOdXL.jpg");
		images.add("https://m.media-amazon.com/images/I/81YOuOGFCJL.jpg");
		images.add("https://m.media-amazon.com/images/I/81Ng3IwZQxL.jpg");
		images.add("https://i.pinimg.com/736x/4e/92/d6/4e92d6aac85c459f82374858c9762cae.jpg");
		images.add("https://cdn.grupoelcorteingles.es/SGFM/dctm/MEDIA03/202303/21/00138545736993____1__1200x1200.jpg");
		images.add("https://www.creapromocion.com/22905-large_default/toalla-absorbente-curt.jpg");
		
		List<Product> of = List.of(
				  new Product("Smartphone Samsung A14", categories.get(0), 199.99f, 1500, Date.valueOf(LocalDate.of(2024, 03, 01)),companies.get(0), 120),
		            new Product("Laptop Lenovo IdeaPad", categories.get(0), 549.99f, 800, Date.valueOf(LocalDate.of(2024, 05, 21)),companies.get(0), 35),
		            new Product("Batidora Oster", categories.get(5), 89.50f, 300, Date.valueOf(LocalDate.of(2024, 01, 20)),companies.get(0), 60),
		            
		            new Product("Juego de ollas T-Fal", categories.get(5), 129.99f, 200, Date.valueOf(LocalDate.of(2025, 07, 01)),companies.get(1), 40),
		            new Product("Harry Potter y la piedra filosofal", categories.get(2), 14.99f, 1200, Date.valueOf(LocalDate.of(2025, 03, 9)),companies.get(1), 500),
		            new Product("Jenga Original", categories.get(4), 19.99f, 650, Date.valueOf(LocalDate.of(2025, 03, 01)),companies.get(1), 75),
		            new Product("Bicicleta MTB R26", categories.get(1), 299.00f, 100, Date.valueOf(LocalDate.of(2025, 04, 04)),companies.get(1), 20),
		            
		            new Product("Audífonos Bluetooth JBL", categories.get(0), 49.99f, 950, Date.valueOf(LocalDate.of(2024, 12, 20)),companies.get(2), 85),
		            new Product("Toalla deportiva absorbente", categories.get(3), 9.99f, 400, Date.valueOf(LocalDate.of(2024, 04, 06)),companies.get(2), 150),
		            new Product("Cafetera italiana Bialetti", categories.get(5), 39.90f, 180, Date.valueOf(LocalDate.of(2024, 07, 07)),companies.get(2), 25)
		);
		
		for (int i = 0; i < images.size(); i++) {
			Product product = of.get(i);
			product.setImage(images.get(i));
		}
		return productRepository.saveAll(of);
		
	}

}
