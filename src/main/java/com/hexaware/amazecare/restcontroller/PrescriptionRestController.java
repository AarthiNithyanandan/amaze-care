package com.hexaware.amazecare.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.PrescriptionDto;
import com.hexaware.amazecare.entities.Prescription;
import com.hexaware.amazecare.service.IPrescriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")
@Validated
public class PrescriptionRestController {
	
	 @Autowired
	IPrescriptionService prescriptionService;
       
    @PreAuthorize("hasAuthority('DOCTOR','ADMIN')")
    @PostMapping("/addPrescription")
    public Prescription addPrescription(@Valid @RequestBody PrescriptionDto prescriptionDto) {
        return prescriptionService.addPrescription(prescriptionDto);
    }
    
    @PreAuthorize("hasAuthority('DOCTOR','ADMIN')")
    @PutMapping("/{prescriptionId}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable int prescriptionId,  @Valid @RequestBody PrescriptionDto prescriptionDto) {
        prescriptionDto.setPrescriptionId(prescriptionId);
        Prescription updatedPrescription = prescriptionService.updatePrescription(prescriptionDto);
        return ResponseEntity.ok(updatedPrescription);  
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getByDoctorId(@PathVariable int doctorId) {
        return prescriptionService.getByDoctorId(doctorId);
    }
   
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'ADMIN', 'PATIENT')")
    @GetMapping("/medical-record/{recordId}")
    public Prescription getByMedicalRecordId(@PathVariable int recordId) {
        return prescriptionService.getByMedicalRecordId(recordId);
    }
}
