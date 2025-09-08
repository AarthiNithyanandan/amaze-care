package com.hexaware.amazecare.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.service.IAppointmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentRestController {

    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR', 'PATIENT')")
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        log.info("Received request to add appointment for patientId: {}", appointmentDto.getPatientId());
        Appointment appointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }
    
    
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR','PATIENT')")
    @PutMapping("/{appointmentId}/{status}")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable int appointmentId, @PathVariable String status) {
        log.info("Received request to update status of appointmentId: {} to {}", appointmentId, status);
        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR', 'PATIENT')")
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
        log.info("Received request to get appointment by ID: {}", appointmentId);
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPatientId(@PathVariable int patientId) {
        log.info("Received request to get appointments for patientId: {}", patientId);
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
    
    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        log.info("Received request to get appointments for doctorId: {}", doctorId);
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
    

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        log.info("Received request to get all appointments");
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<List<Appointment>> getByStatus(@PathVariable String status) {
        log.info("Received request to get appointments by status: {}", status);
        List<Appointment> appointments = appointmentService.getByStatus(status);
        return ResponseEntity.ok(appointments);
    }

    
//    @DeleteMapping("/patient/{patientId}/cancel")
//    @PreAuthorize("hasAuthority('PATIENT')")
//    public ResponseEntity<String> cancelAppointmentsByPatientId(@PathVariable int patientId) {
//        log.info("Received request to cancel appointments for patientId: {}", patientId);
//        int cancelledCount = appointmentService.cancelAppointmentByPatientId(patientId);
//        return ResponseEntity.ok(cancelledCount + " appointments cancelled for patientId: " + patientId);
//    }

    
    @PutMapping("/{appointmentId}/cancel")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<String> cancelAppointment(@PathVariable int appointmentId) {
        log.info("Received request to cancel appointmentId: {}", appointmentId);
        Appointment appointment = appointmentService.cancelAppointmentById(appointmentId);
        return ResponseEntity.ok("Appointment " + appointmentId + " cancelled successfully");
    }
    
    @GetMapping("/patient/{patientId}/counts")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<Map<String, Long>> getAppointmentCounts(@PathVariable int patientId) {
        try {
            Map<String, Long> counts = appointmentService.getAppointmentCountsByPatientId(patientId);
            return ResponseEntity.ok(counts);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}

