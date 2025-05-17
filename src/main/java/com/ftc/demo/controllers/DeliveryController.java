package com.ftc.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.DeliveryChangeStatusDTO;
import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.DTOs.DeliveryDTO;

public interface DeliveryController {
	public ResponseEntity<List<DeliveryDTO>> getDeliveries();
	public ResponseEntity<DeliveryDTO> getDelivery(long id);
	public ResponseEntity<DeliveryCreateDTO> createDelivery(DeliveryCreateDTO deliveryDTO);
	public ResponseEntity<Boolean> deleteDelivery(long id );
	public ResponseEntity<Boolean> changeStatus(DeliveryChangeStatusDTO deliveryDTO);

}
