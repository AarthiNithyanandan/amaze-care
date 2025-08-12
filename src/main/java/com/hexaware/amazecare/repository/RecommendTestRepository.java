package com.hexaware.amazecare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.entities.RecommendTest;

public interface RecommendTestRepository extends  JpaRepository<RecommendTest,Integer>  {

	
	 List<RecommendTest> findByAppointmentId(int appointmentId);

	    List<RecommendTest> findByTestNameIgnoreCase(String testName);

	    List<RecommendTest> findByPatientId(int patientId);
	}

