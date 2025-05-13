package com.ftc.demo.populaters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Status;
import com.ftc.demo.repositories.StatusRepository;

 @Component
public class StatusPopulater {
	
	private final StatusRepository statusRepository;
	
	public StatusPopulater(StatusRepository statusRepository) {
		super();
		this.statusRepository = statusRepository;
	}



	public List<Status> populate() {
		List<Status> statuses = List.of(
				new Status("Abierto"),
				new Status("En proceso"),
				new Status("Completado"),
				new Status("Cancelado")
		);
		return statusRepository.saveAll(statuses);
	}
}
