package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Category;
import com.ftc.demo.repositories.CategoryRepository;

@Component
public class CategoryPopulater {
	
	private final CategoryRepository categoryRepository;

	public CategoryPopulater(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> populate() {
		List<Category> of = List.of(
				new Category("Electronica"),
				new Category("Perifericos"),
				new Category("Libros"),
				new Category("Ropa"),
				new Category("Juegos"),
				new Category("Cocina")
		);
//		return of;
		return categoryRepository.saveAll(of);
	}

}
