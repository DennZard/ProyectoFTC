package com.ftc.demo.populaters;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Delivery;
import com.ftc.demo.entities.Employee;
import com.ftc.demo.entities.Product;
import com.ftc.demo.entities.Status;
import com.ftc.demo.repositories.DeliveryRepository;
import com.ftc.demo.repositories.UserRepository;

@Component
public class DeliveryPopulater {
	
	private final DeliveryRepository deliveryRepository;
	private final UserRepository userRepository;
	
	public DeliveryPopulater(DeliveryRepository deliveryRepository, UserRepository userRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
		this.userRepository = userRepository;
	}

	public void populate(List<Employee> employees, List<Status> statuses, List<Product> products) {
		List<Delivery> of = List.of(
			new Delivery(Date.valueOf(LocalDate.of(2024, 6, 14)), userRepository.findById(1l).get(), statuses.get(0), "Calla Buena Vista", employees.get(0), products.get(0)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 7, 15)), userRepository.findById(1l).get(), statuses.get(1), "Calla Buena Vista", employees.get(1), products.get(1)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 8, 16)), userRepository.findById(2l).get(), statuses.get(2), "Calla Buena Vista", employees.get(2), products.get(2)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 9, 17)), userRepository.findById(2l).get(), statuses.get(2), "Calla Buena Vista", employees.get(3), products.get(4)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 10, 18)), userRepository.findById(3l).get(), statuses.get(0), "Calla Buena Vista", employees.get(2), products.get(5)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 11, 19)), userRepository.findById(3l).get(), statuses.get(2), "Calla Buena Vista", employees.get(3), products.get(6)),	
			new Delivery(Date.valueOf(LocalDate.of(2024, 12, 20)), userRepository.findById(4l).get(), statuses.get(1), "Calla Buena Vista", employees.get(1), products.get(7))
		);
		
		deliveryRepository.saveAll(of);
		
		
	}

}
