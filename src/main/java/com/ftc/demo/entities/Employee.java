package com.ftc.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	private String name;
	@NonNull
	private String lastName;
	@NonNull
	@Email
	@Column(unique = true)
	private String email;
	@NonNull
	private String phone;
	@NonNull
	@OneToMany(mappedBy = "employee")
	private List<Delivery> deliveries=new ArrayList<>();
	@NonNull
	private boolean active = true;
}
