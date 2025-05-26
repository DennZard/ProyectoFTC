package com.ftc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftc.demo.DTOs.DeliveryCreateDTO;
import com.ftc.demo.DTOs.DeliveryDTO;
import com.ftc.demo.entities.Delivery;
import com.ftc.demo.entities.Status;
import com.ftc.demo.mapper.DeliveryCreateMapper;
import com.ftc.demo.mapper.DeliveryMapper;
import com.ftc.demo.repositories.DeliveryRepository;
import com.ftc.demo.repositories.StatusRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryMapper deliveryMapper;
	private final DeliveryCreateMapper deliveryCreateMapper;
	private final StatusRepository statusRepository;
	
	protected DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper, DeliveryCreateMapper deliveryCreateMapper, StatusRepository statusRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
		this.deliveryMapper = deliveryMapper;
		this.deliveryCreateMapper = deliveryCreateMapper;
		this.statusRepository = statusRepository
				;
	}

	@Override
	public List<DeliveryDTO> getDeliveries() {
		List<Delivery> all = deliveryRepository.findAll();
		if (!all.isEmpty()) {
			return all.stream().map((delivery) -> {
				return deliveryMapper.mapToDto(delivery); 
			}).toList();
		}
		return null;
	}

	@Override
	public Optional<DeliveryDTO> getDelivery(long id) throws IllegalArgumentException{
		if (id == 0) throw new IllegalArgumentException("Id no proporcionado");
		Optional<Delivery> byId = deliveryRepository.findById(id);
		if (byId.isPresent()) {
			return Optional.of(deliveryMapper.mapToDto(byId.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<DeliveryDTO> createDelivery(DeliveryCreateDTO deliveryDTO) {
//		if ()
		return Optional.empty();
	}

	@Override
	public Boolean changeStatus(long id, long StatusId) {
		if (id == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			Optional<Delivery> deliveryOpt = deliveryRepository.findById(id);
			if (deliveryOpt.isPresent()) {
				Delivery delivery = deliveryOpt.get();
				Optional<Status> statusOpt = statusRepository.findById(StatusId);
				if (statusOpt.isPresent()) {
					delivery.setStatus(statusOpt.get());
					deliveryRepository.save(delivery);
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Boolean deleteDelivery(long id) throws IllegalArgumentException{
		if (id == 0) throw new IllegalArgumentException("Id no proporcionado");
		try {
			if (deliveryRepository.existsById(id)) {
				deliveryRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<DeliveryDTO> getByCustomer(long userId) {
		return deliveryRepository.findByCustomerId(userId).stream().map(deliveryMapper::mapToDto).toList();
	}
	
	@Override
	public List<DeliveryDTO> getByEmployee(long userId) {
		return deliveryRepository.findByEmployeeId(userId).stream().map(deliveryMapper::mapToDto).toList();
	}
	
	

}
