package com.ftc.demo.controllers;

import org.springframework.http.ResponseEntity;

import com.ftc.demo.DTOs.DeliveryDTO;

public interface DeliveryController {
	public ResponseEntity<DeliveryDTO> getDeliveries();
	public ResponseEntity<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO);

}
