package com.ftc.demo.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	private Date added;
	@NonNull
	@ManyToOne
	private User customer;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Status status;
	@NonNull
	private String destination;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Employee employee;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Product product;
}
