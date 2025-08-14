/*Author name:Aarthi
 *Date modified:9-8-2025
 * Service implementation for managing Doctor entities.
 *
 * Responsibilities:
 *   Add, update, fetch doctor information
 *   Provides doctor-specific operation implemetations
 */


package com.hexaware.amazecare.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

	
@Slf4j
@Service
@Transactional
	
public class DoctorServiceImp implements IDoctorService {

	    @Autowired
	    private DoctorRepository doctorRepository;

	    @Autowired
	    private AppointmentRepository appointmentRepository;
	    
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;  // from config bean

	    @Autowired
	    private JwtService jwtService;
	    
	    public Doctor addDoctor(DoctorDto doctorDto) {
	        log.info("Adding new doctor: {}", doctorDto.getName());
	
	        if(doctorRepository.findByEmail(doctorDto.getEmail()).isPresent()) {
	            throw new RuntimeException("Email already exists"); // You can create a custom exception
	        }

	        Doctor doctor = new Doctor();
	        doctor.setName(doctorDto.getName());
	        doctor.setSpecialty(doctorDto.getSpeciality());
	        doctor.setExperience(doctorDto.getExperience());
	        doctor.setQualification(doctorDto.getQualification());
	        doctor.setDesignation(doctorDto.getDesignation());
	        doctor.setEmail(doctorDto.getEmail());
	        doctor.setPasswordDoctor(passwordEncoder.encode(doctorDto.getPasswordDoctor()));
	        doctor.setContactNumber(doctorDto.getContactNumber());

	        return doctorRepository.save(doctor);
	    
	    }
	    public Doctor updateDoctor(DoctorDto doctorDto) {
	        log.info("Updating doctor with ID:", doctorDto.getDoctorId());

	        Doctor existingDoctor = doctorRepository.findById(doctorDto.getDoctorId()).orElseThrow(() -> {
	        log.error("Doctor not found with ID:", doctorDto.getDoctorId());
	        return new DoctorNotFoundException("Doctor not found with ID: " + doctorDto.getDoctorId());
	        });
	 
	        existingDoctor.setName(doctorDto.getName());
	        existingDoctor.setSpecialty(doctorDto.getSpeciality());
	        existingDoctor.setExperience(doctorDto.getExperience());
	        existingDoctor.setQualification(doctorDto.getQualification());
	        existingDoctor.setDesignation(doctorDto.getDesignation());
	        existingDoctor.setEmail(doctorDto.getEmail());
	        existingDoctor.setPasswordDoctor(doctorDto.getPasswordDoctor());
	        existingDoctor.setContactNumber(doctorDto.getContactNumber());
	        
	        return doctorRepository.save(existingDoctor);
	    }


	    @Override
	    public Doctor getDoctorById(int doctorId) {
	        log.info("Fetching doctor with ID:", doctorId);
	        return doctorRepository.findById(doctorId).orElseThrow(() -> {
	        log.error("Doctor not found with ID:", doctorId);
	       return new DoctorNotFoundException("Doctor not found with ID: " + doctorId);});
	    }

	    @Override
	    public String acceptAppointment(int appointmentId) {
	        log.info("Accepting appointment with ID: {}", appointmentId);
	        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> {
	        log.error("Appointment not found with ID: {}", appointmentId);
	        return new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
	        });
	        appointment.setStatus("Accepted");
	        appointmentRepository.save(appointment);
	        return "Appointment accepted successfully.";
	    }

	    @Override
	    public String rejectAppointment(int appointmentId) {
	        log.info("Rejecting appointment with ID: {}", appointmentId);
	        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> {
	        log.error("Appointment not found with ID: {}", appointmentId);
	        return new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
	        });
	        appointment.setStatus("Rejected");
	        appointmentRepository.save(appointment);
	        return "Appointment rejected successfully.";
	    }

	    @Override
	    public String deleteDoctor(int doctorId) {
	        log.info("Deleting doctor with ID:", doctorId);
	        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> {
	        log.error("Doctor not found with ID:", doctorId);
	        return new DoctorNotFoundException("Doctor not found with ID: " + doctorId);
	        });
	        doctorRepository.delete(doctor);
	        return "Doctor deleted successfully.";
	    }

	    @Override
	    public List<Doctor> findByName(String name) {
	        log.info("Searching for doctors with name:", name);
	        List<Doctor> doctors = doctorRepository.findByNameIgnoreCase(name);
	        if (doctors.isEmpty()) {
	            log.warn("No doctors found with name:", name);
	            throw new DoctorNotFoundException("No doctors found with name: " + name);
	        }
	        return doctors;
	    }

	    @Override
	    public List<Doctor> searchDoctorsBySpecialization(String speciality) {
	        log.info("Searching for doctors with specialization:", speciality);
	        List<Doctor> doctors = doctorRepository.findBySpecialityIgnoreCase(speciality);
	        if (doctors.isEmpty()) {
	        log.warn("No doctors found with specialization:", speciality);
	        throw new DoctorNotFoundException("No doctors found with name: " + speciality);
	        }
	        return doctors;
	    }


		@Override
		public List<Doctor> getAllDoctors() {
			
			return doctorRepository.findAll();
		}
	    


		
	

}

	 
//public LoginResponse loginAdmin(String email, String password) throws InvalidCredentialsException {
//    Admin admin = adminRepository.findByEmail(email)
//        .orElseThrow(() -> new InvalidCredentialsException("Admin not found"));
//
//    if (!passwordEncoder.matches(password, admin.getAdminPassword())) {
//        throw new InvalidCredentialsException("Invalid password");
//    }
//
//    String token = jwtService.generateToken(email, "ADMIN");
//    return new LoginResponse(token, admin);
//}
