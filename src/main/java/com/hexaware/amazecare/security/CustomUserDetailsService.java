package com.hexaware.amazecare.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AdminRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

	@Service
	public class CustomUserDetailsService implements UserDetailsService {

	    @Autowired
	    private AdminRepository adminRepository;

	    @Autowired
	    private DoctorRepository doctorRepository;

	    @Autowired
	    private PatientRepository patientRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        Admin admin = adminRepository.findByEmail(email).orElse(null);
	        if (admin != null) {
	            return new User(
	                admin.getEmail(),
	                admin.getAdminPassword(),
	                List.of(new SimpleGrantedAuthority("ADMIN"))
	            );
	        }

	        Doctor doctor = doctorRepository.findByEmail(email).orElse(null);
	        if (doctor != null) {
	            return new User(
	                doctor.getEmail(),
	                doctor.getPasswordDoctor(),
	                List.of(new SimpleGrantedAuthority("DOCTOR"))
	            );
	        }

	        Patient patient = patientRepository.findByEmail(email).orElse(null);
	        if (patient != null) {
	            return new User(
	                patient.getEmail(),
	                patient.getPasswordPatient(),
	                List.of(new SimpleGrantedAuthority("PATIENT"))
	            );
	        }

	        throw new UsernameNotFoundException("User not found with email: " + email);
	    }


	}
