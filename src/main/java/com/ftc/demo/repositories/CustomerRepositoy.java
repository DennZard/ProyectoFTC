package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Customer;

@Repository
public interface CustomerRepositoy extends JpaRepository<Customer, Long> {

}
