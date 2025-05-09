package com.ftc.demo.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.NonNull;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Email
	@NonNull
	private String email;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	private String phone;
	@NonNull
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Roles> roles = new HashSet<>();

}
