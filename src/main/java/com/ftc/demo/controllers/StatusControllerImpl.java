package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.StatusDTO;
import com.ftc.demo.services.StatusServiceImpl;

@RestController
@RequestMapping("status")
public class StatusControllerImpl {

	private final StatusServiceImpl statusServiceImpl;
	
	public StatusControllerImpl(StatusServiceImpl statusServiceImpl) {
		super();
		this.statusServiceImpl = statusServiceImpl;
	}

	
	
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("all")
	public ResponseEntity<List<StatusDTO>> getStatuses() {
		List<StatusDTO> statuses = statusServiceImpl.getStatuses();
		if (!statuses.isEmpty()) {
			return ResponseEntity.ok().body(statuses);
		}
		return ResponseEntity.badRequest().body(null);
		
	}
	
}
