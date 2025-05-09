package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
