package com.ftc.demo.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	private String name;
	@NonNull
	@ManyToOne
	private Category category;
	@NonNull
	private Float decimal;
	@NonNull
	private int Sells;
	@NonNull
	private Date added;
	@NonNull
	@ManyToOne
	private Company company;
}
