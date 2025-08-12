package com.hexaware.amazecare.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.service.IPatientService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    @Autowired
    private IPatientService patientService;

    @PostMapping("/register")
    public Patient addPatient(@Valid @RequestBody PatientDto patientDto) {
        log.info("Received request to add patient: {}", patientDto.getName());
        Patient patient = patientService.addPatient(patientDto);
        log.info("Patient added with ID: {}", patient.getPatientId());
        return patient;
    }

    @PostMapping("/login")
    public Patient loginPatient(@RequestParam String email, @RequestParam String password) throws InvalidCredentialsException {
        log.info("Login attempt for patient email: {}", email);
        Patient patient = patientService.loginPatient(email, password);
        log.info("Patient logged in successfully: {}", email);
        return patient;
    }

    @PutMapping("/{patientId}")
    public Patient updatePatient(@PathVariable int patientId, @Valid @RequestBody PatientDto patientDto) {
        log.info("Received request to update patient with ID: {}", patientId);
        patientDto.setPatientId(patientId);
        Patient updated = patientService.updatePatient(patientDto);
        log.info("Patient updated successfully with ID: {}", updated.getPatientId());
        return updated;
    }
}
