package com.ftc.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
	List<Delivery> findByCustomerId(Long customerId);
	List<Delivery> findByEmployeeId(Long customerId);
}
