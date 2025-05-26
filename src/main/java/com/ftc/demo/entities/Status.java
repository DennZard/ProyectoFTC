package com.ftc.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	@Column(unique = true)
	private String name;
	@NonNull
	private Boolean cancelable = true;
	
	
	public Status(@NonNull String name, @NonNull Boolean cancelable) {
		super();
		this.name = name;
		this.cancelable = cancelable;
	}
	
	
}
