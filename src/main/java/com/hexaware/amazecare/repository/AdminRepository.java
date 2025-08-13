package com.hexaware.amazecare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

	Optional<Admin> findByEmail(String email);
}
