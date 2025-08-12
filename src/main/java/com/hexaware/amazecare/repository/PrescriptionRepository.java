package com.hexaware.amazecare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.entities.Prescription;

public interface PrescriptionRepository extends  JpaRepository<Prescription,Integer>{

	List<Prescription> findByDoctorId(int doctorId);
	Optional<Prescription> findByRecordId(int recordId);
}
