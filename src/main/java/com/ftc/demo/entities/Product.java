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
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	@NonNull
	private float price;
	@NonNull
	private int sells;
	@NonNull
	private Date added;
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;
	@OneToMany(mappedBy = "product")
	private List<Delivery> deliveries = new ArrayList<>();
}
