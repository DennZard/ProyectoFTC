package com.ftc.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftc.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);

}
