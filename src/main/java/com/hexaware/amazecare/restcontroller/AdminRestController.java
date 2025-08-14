package com.hexaware.amazecare.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.service.IAdminService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private IAdminService adminService;
    

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(adminService.addDoctor(doctorDto));
    }

    @PutMapping("/doctors")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(adminService.updateDoctor(doctorDto));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        return ResponseEntity.ok(adminService.deleteDoctor(id));
    }
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        log.info("API Call: GET /admin/doctors");
        return adminService.getAllDoctors();
    }

//    @GetMapping("/doctors/specialization/{specialization}")
//    public ResponseEntity<List<Doctor>> getBySpecialization(@PathVariable String specialization) {
//        return ResponseEntity.ok(adminService.searchDoctorsBySpecialization(specialization));
//    }
//
//    @GetMapping("/doctors/name/{name}")
//    public ResponseEntity<List<Doctor>> findDoctorByName(@PathVariable String name) {
//        return ResponseEntity.ok(adminService.findDoctorByName(name));
//    }

    @PutMapping("/patients")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(adminService.updatePatient(patientDto));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok(adminService.deletePatient(id));
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) throws Exception {
        return ResponseEntity.ok(adminService.addAppointment(appointmentDto));
    }

    @PutMapping("/appointments/{id}/status/{status}")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable int id, @PathVariable String status) {
        return ResponseEntity.ok(adminService.updateAppointmentStatus(id, status));
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(adminService.getAllAppointments());
    }


}
