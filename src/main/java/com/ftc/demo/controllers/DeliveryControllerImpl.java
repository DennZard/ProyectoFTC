package com.ftc.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.demo.DTOs.DeliveryChangeStatusDTO;
import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.DTOs.DeliveryDTO;
import com.ftc.demo.services.DeliveryService;


@RestController
@RequestMapping("deliveries")
public class DeliveryControllerImpl implements DeliveryController{
	private final DeliveryService deliveryService;
	
	protected DeliveryControllerImpl(DeliveryService deliveryService) {
		super();
		this.deliveryService = deliveryService;
	}


	@Override
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("all")
	public ResponseEntity<List<DeliveryDTO>> getDeliveries() {
		try {
			List<DeliveryDTO> deliveries = deliveryService.getDeliveries();
			if (!deliveries.isEmpty()) {
				return ResponseEntity.ok().body(deliveries);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		return ResponseEntity.badRequest().eTag("No se pudo obtener los pedidos").body(null);
	}


	@Override
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("byId")
	public ResponseEntity<DeliveryDTO> getDelivery(@RequestParam long id) {
		try {
			Optional<DeliveryDTO> delivery = deliveryService.getDelivery(id);
			if (delivery.isPresent()) {
				return ResponseEntity.ok().body(delivery.get()); 
			}
			return null;
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
	}
	
	@Override
	@CrossOrigin("http://localhost:4200/")
	@PostMapping("create")
	public ResponseEntity<DeliveryCreateDTO> createDelivery(@RequestBody DeliveryCreateDTO deliveryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@CrossOrigin("http://localhost:4200/")
	@PutMapping("update")
	public ResponseEntity<Boolean> changeStatus(@RequestBody DeliveryChangeStatusDTO deliveryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@CrossOrigin("http://localhost:4200/")
	@DeleteMapping("byId")
	public ResponseEntity<Boolean> deleteDelivery(@RequestParam long id) {
		try {
			return ResponseEntity.ok(deliveryService.deleteDelivery(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().eTag(e.getMessage()).body(null);
		}
		
	}


	@Override
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("customer")
	public ResponseEntity<List<DeliveryDTO>> getDeliveriesByCustomer(@RequestParam long id) {
		List<DeliveryDTO> byCustomer = deliveryService.getByCustomer(id);
		if (!byCustomer.isEmpty()) {
			return ResponseEntity.ok().body(byCustomer);
		}
		return ResponseEntity.badRequest().eTag("No se pudo obtener los pedidos del usuario").body(byCustomer);
	}

}
