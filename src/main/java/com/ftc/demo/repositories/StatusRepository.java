package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
