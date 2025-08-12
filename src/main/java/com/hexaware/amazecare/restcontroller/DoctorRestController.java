package com.hexaware.amazecare.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.service.IDoctorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {

    @Autowired
    private IDoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        log.info("Received request to add doctor: {}", doctorDto.getName());
        Doctor doctor = doctorService.addDoctor(doctorDto);
        log.info("Doctor added with ID: {}", doctor.getDoctorId());
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int doctorId, @Valid @RequestBody DoctorDto doctorDto) {
        log.info("Received request to update doctor with ID: {}", doctorId);
        doctorDto.setDoctorId(doctorId);
        Doctor updatedDoctor = doctorService.updateDoctor(doctorDto);
        log.info("Doctor updated with ID: {}", updatedDoctor.getDoctorId());
        return ResponseEntity.ok(updatedDoctor);
    }

    @PostMapping("/login")
    public ResponseEntity<Doctor> loginDoctor(@RequestParam String email, @RequestParam String password) {
        log.info("Login attempt for doctor email: {}", email);
        Doctor doctor = doctorService.loginDoctor(email, password);
        log.info("Doctor logged in successfully: {}", email);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable int doctorId) {
        log.info("Fetching doctor by ID: {}", doctorId);
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/appointments/{appointmentId}/accept")
    public ResponseEntity<String> acceptAppointment(@PathVariable int appointmentId) {
        log.info("Accepting appointment with ID: {}", appointmentId);
        String response = doctorService.acceptAppointment(appointmentId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/appointments/{appointmentId}/reject")
    public ResponseEntity<String> rejectAppointment(@PathVariable int appointmentId) {
        log.info("Rejecting appointment with ID: {}", appointmentId);
        String response = doctorService.rejectAppointment(appointmentId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deletedoctor/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctorId) {
        log.info("Deleting doctor with ID: {}", doctorId);
        String response = doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> findByName(@RequestParam String name) {
        log.info("Searching doctors by name: {}", name);
        List<Doctor> doctors = doctorService.findByName(name);
        return ResponseEntity.ok(doctors);
    }
    
    @GetMapping("/specialization")
    public ResponseEntity<List<Doctor>> searchDoctorsBySpecialization(@RequestParam String specialization) {
        log.info("Searching doctors by specialization: {}", specialization);
        List<Doctor> doctors = doctorService.searchDoctorsBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }
}