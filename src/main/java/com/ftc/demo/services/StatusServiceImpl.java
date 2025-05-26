package com.ftc.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.StatusDTO;
import com.ftc.demo.entities.Status;
import com.ftc.demo.mapper.StatusMapper;
import com.ftc.demo.repositories.StatusRepository;

@Service
public class StatusServiceImpl {
	
	private final StatusRepository statusRepository;
	private final StatusMapper statusMapper;
	
	public StatusServiceImpl(StatusRepository statusRepository, StatusMapper statusMapper) {
		super();
		this.statusRepository = statusRepository;
		this.statusMapper = statusMapper;
	}

	public List<StatusDTO>getStatuses() {
		return statusRepository.findAll().stream().map(statusMapper::mapToDto).toList();
	}
	
}
