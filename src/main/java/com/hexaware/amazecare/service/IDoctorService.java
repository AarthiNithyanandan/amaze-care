package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.InvalidCredentialsException;

public interface IDoctorService {
	
	Doctor addDoctor(DoctorDto doctorDto);
	Doctor updateDoctor(DoctorDto doctorDto);
//    LoginResponse loginDoctor(String email, String passwordDoctor) throws InvalidCredentialsException;
    Doctor getDoctorById(int doctorId)  throws DoctorNotFoundException;
    String acceptAppointment(int appointmentId);
    String rejectAppointment(int appointmentId);
    String deleteDoctor(int doctorId);
    List<Doctor> findByName(String name) throws DoctorNotFoundException;
    List<Doctor> searchDoctorsBySpecialization(String specialization);
	List<Doctor> getAllDoctors();
    
//    List<Feedback> getFeedbacksForDoctor(int doctorId);


}
