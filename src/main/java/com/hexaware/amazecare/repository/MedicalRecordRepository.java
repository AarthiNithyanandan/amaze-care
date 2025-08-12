package com.hexaware.amazecare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Prescription;

public interface MedicalRecordRepository extends  JpaRepository<MedicalRecord,Integer> {

	
	    List<MedicalRecord> findByDoctorId(int doctorId);
	    Optional<MedicalRecord> findByAppointmentId(int appointmentId);
	    Optional<Prescription> findByPrescriptionId(int prescriptionId);

	    
}
