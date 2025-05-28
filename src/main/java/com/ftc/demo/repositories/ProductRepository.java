package com.ftc.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByNameIgnoreCaseStartingWith(String name);
	public List<Product> findTop3ByOrderBySellsDesc();
}
