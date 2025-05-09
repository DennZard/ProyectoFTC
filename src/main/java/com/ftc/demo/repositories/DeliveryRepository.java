package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
