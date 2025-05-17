package com.ftc.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.entities.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryCreateMapper {
	public DeliveryCreateDTO mapToDto(Delivery delivery);
	public Delivery mapToEntity(DeliveryCreateDTO categoryDTO);

}
