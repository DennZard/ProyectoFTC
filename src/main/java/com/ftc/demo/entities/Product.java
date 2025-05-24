package com.ftc.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	private String name;
	@NonNull
	@ManyToOne
	private Category category;
	@NonNull
	private float price;
	@NonNull
	private int sells;
	@NonNull
	private Date added;
	@NonNull
	@ManyToOne
	private Company company;
	@NonNull
	private int stock;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Delivery> deliveries = new ArrayList<>();
	private String image;
}
