package com.ftc.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	@ManyToOne
	private Customer customer;
	@NonNull
	@ManyToOne
	private Status status;
	@NonNull
	private String destination;
	@NonNull
	@ManyToOne
	private Employee employee;
	@NonNull
	@ManyToOne
	private Product product;
}
