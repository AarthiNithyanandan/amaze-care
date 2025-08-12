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

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.service.IAppointmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentRestController {

    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        log.info("Received request to add appointment for patientId: {}", appointmentDto.getPatientId());
        Appointment appointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PutMapping("/{appointmentId}/status")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable int appointmentId, @RequestParam String status) {
        log.info("Received request to update status of appointmentId: {} to {}", appointmentId, status);
        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
        log.info("Received request to get appointment by ID: {}", appointmentId);
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable int patientId) {
        log.info("Received request to get appointments for patientId: {}", patientId);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        log.info("Received request to get appointments for doctorId: {}", doctorId);
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        log.info("Received request to get all appointments");
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Appointment>> getByStatus(@RequestParam String status) {
        log.info("Received request to get appointments by status: {}", status);
        List<Appointment> appointments = appointmentService.getByStatus(status);
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/patient/{patientId}/cancel")
    public ResponseEntity<String> cancelAppointmentsByPatientId(@PathVariable int patientId) {
        log.info("Received request to cancel appointments for patientId: {}", patientId);
        int cancelledCount = appointmentService.cancelAppointmentByPatientId(patientId);
        return ResponseEntity.ok(cancelledCount + " appointments cancelled for patientId: " + patientId);
    }
}

