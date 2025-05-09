package com.ftc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{

}
