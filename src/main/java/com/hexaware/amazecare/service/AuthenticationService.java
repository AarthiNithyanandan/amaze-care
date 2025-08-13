package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.repository.AdminRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;
import com.hexaware.amazecare.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired private AdminRepository adminRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private PatientRepository patientRepository;

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    public LoginResponse loginAdmin(String email, String password) throws InvalidCredentialsException {
        Admin admin = adminRepository.findByEmail(email)
            .orElseThrow(() -> new InvalidCredentialsException("Admin not found"));

        if (!passwordEncoder.matches(password, admin.getAdminPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(email, "ADMIN");
        return new LoginResponse(token, admin);
    }

    public LoginResponse loginDoctor(String email, String rawPassword) throws InvalidCredentialsException {
        Doctor doctor = doctorRepository.findByEmail(email)
            .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(rawPassword, doctor.getPasswordDoctor())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(email, "DOCTOR");
        return new LoginResponse(token, doctor);
    }

    public LoginResponse loginPatient(String email, String rawPassword) throws InvalidCredentialsException {
        Patient patient = patientRepository.findByEmail(email)
            .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(rawPassword, patient.getPasswordPatient())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(email, "PATIENT");
        return new LoginResponse(token, patient);

    }
}
