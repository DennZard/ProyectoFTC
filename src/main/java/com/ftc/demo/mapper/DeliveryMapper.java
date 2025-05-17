package com.ftc.demo.mapper;

import org.mapstruct.Mapper;

import com.ftc.demo.DTOs.DeliveryDTO;
import com.ftc.demo.entities.Delivery;


@Mapper(componentModel = "spring")
public interface DeliveryMapper {
	public DeliveryDTO mapToDto(Delivery delivery);
	public Delivery mapToEntity(DeliveryDTO categoryDTO);

}
