package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class PatientServiceImp implements IPatientService {

	
	 @Autowired
   private PatientRepository patientRepository;
	 
	
	@Override
    public Patient addPatient(PatientDto patientDto) {
	log.info("Updating patient with ID:", patientDto.getPatientId());
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setGender(patientDto.getGender());
        patient.setContactNumber(patientDto.getContactNumber());
        patient.setEmail(patientDto.getEmail());
        patient.setAge(patientDto.getAge());
        patient.setAddress(patientDto.getAddress());
        
        return patientRepository.save(patient);
    }

	public Patient loginPatient(String email, String password) throws InvalidCredentialsException {
	        
	Patient patient = patientRepository.findByEmailAndPassword(email, password).orElseThrow(()->{
		log.error("Invalid login attempt for email:", email);
	    throw new PatientNotFoundException("Invalid email or password");
	  });
	
	        return patient;
	    }

	 public Patient updatePatient(PatientDto patientDto) {
	        Patient existingPatient = patientRepository.findById(patientDto.getPatientId()).orElseThrow(() -> {
	        	log.error("Patient not found with ID:", patientDto.getPatientId());
	            return new PatientNotFoundException("Patient not found with ID:" + patientDto.getPatientId());
	        });
	        existingPatient.setName(patientDto.getName());
	        existingPatient.setGender(patientDto.getGender());
	        existingPatient.setContactNumber(patientDto.getContactNumber());
	        existingPatient.setEmail(patientDto.getEmail());
	        existingPatient.setAge(patientDto.getAge());
	        existingPatient.setAddress(patientDto.getAddress());
	        log.info("Successfully updated patient with ID:");
	        return patientRepository.save(existingPatient);
	    }
	}


//	@Override
//	public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Appointment> getAppointmentsByPatientId(int patientId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String cancelAppointmentByPatientId(int appointmentId, int patientId) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	
	
	

