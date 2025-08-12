package com.hexaware.amazecare.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.MedicalRecordDto;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.service.IMedicalRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medical-records")
@Validated
public class MedicalRecordRestController {

    @Autowired
    private IMedicalRecordService medicalRecordService;

  
    @PostMapping("/add")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecordDto recordDto) {
        MedicalRecord medicalRecord = medicalRecordService.addMedicalRecord(recordDto);
        return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}")
    public MedicalRecord updateMedicalRecord(@PathVariable int recordId, @Valid @RequestBody MedicalRecordDto medicalRecordDto) {
        medicalRecordDto.setRecordId(recordId); 
        return medicalRecordService.updateMedicalRecord(medicalRecordDto);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<MedicalRecord> getByAppointmentId(@PathVariable int appointmentId) {
        MedicalRecord medicalRecord = medicalRecordService.getByAppointmentId(appointmentId);
        return ResponseEntity.ok(medicalRecord);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<MedicalRecord> getByRecordId(@PathVariable int recordId) {
        MedicalRecord medicalRecord = medicalRecordService.getByRecordId(recordId);
        return ResponseEntity.ok(medicalRecord);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecord>> getByDoctorId(@PathVariable int doctorId) {
        List<MedicalRecord> records = medicalRecordService.getByDoctorId(doctorId);
        return ResponseEntity.ok(records);
    }
}