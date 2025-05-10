package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.StatusDTO;
import com.ftc.demo.entities.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
	public StatusDTO mapToDto(Status status);
	public Status mapToEntity(StatusDTO statusDTO);

}
