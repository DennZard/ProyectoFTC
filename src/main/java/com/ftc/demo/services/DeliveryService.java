package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.DTOs.DeliveryDTO;

public interface DeliveryService {
	public List<DeliveryDTO> getDeliveries();
	public Optional<DeliveryDTO> getDelivery(long id);
	public Optional<DeliveryDTO> createDelivery(DeliveryCreateDTO deliveryDTO);
	public Boolean changeStatus(long id, long StatusId);
	public Boolean deleteDelivery(long id );
}
