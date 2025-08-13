package com.hexaware.amazecare.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.PatientRepository;
import com.hexaware.amazecare.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class PatientServiceImp implements IPatientService {

	
	 @Autowired
   private PatientRepository patientRepository;
	 
	 @Autowired
	    private PasswordEncoder passwordEncoder;  // from config bean

	    @Autowired
	    private JwtService jwtService;
	 
	
	    public Patient addPatient(PatientDto patientDto) {
	        if(patientRepository.findByEmail(patientDto.getEmail()).isPresent()) {
	            throw new RuntimeException("Email already registered");
	        }

	        Patient patient = new Patient();
	        patient.setName(patientDto.getName());
	        patient.setGender(patientDto.getGender());
	        patient.setContactNumber(patientDto.getContactNumber());
	        patient.setEmail(patientDto.getEmail());
	        patient.setAge(patientDto.getAge());
	        patient.setAddress(patientDto.getAddress());
	        patient.setPasswordPatient(passwordEncoder.encode(patientDto.getPasswordPatient()));

	        return patientRepository.save(patient);
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
	 
	 @Override
	    public String deletePatient(int patientId) {
	        Patient patient = patientRepository.findById(patientId)
	                .orElseThrow(() -> {
	                    log.error("Patient not found with ID: {}", patientId);
	                    return new PatientNotFoundException("Patient not found with ID: " + patientId);
	                });

	        patientRepository.delete(patient);
	        log.info("Deleted patient with ID: {}", patientId);
	        return "Patient deleted successfully with ID: " + patientId;
	    }

	    @Override
	    public List<Patient> getAllPatients() {
	        log.info("Fetching all patients");
	        return patientRepository.findAll();
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


	
	
	

