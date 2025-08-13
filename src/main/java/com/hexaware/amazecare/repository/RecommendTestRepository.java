package com.hexaware.amazecare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.entities.RecommendTest;

public interface RecommendTestRepository extends  JpaRepository<RecommendTest,Integer>  {

	
	 List<RecommendTest> findByAppointmentAppointmentId(int appointmentId);

	    List<RecommendTest> findByTestNameIgnoreCase(String testName);

//	    List<RecommendTest> findByPatientPatientId(int patientId);
	}

