package com.hexaware.amazecare.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional // ensures DB changes are rolled back after each test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminServiceImpTest {

    @Autowired
    private AdminServiceImp adminService;

    private static int createdDoctorId;
    private static int createdPatientId;

    @Test
    @Order(1)
    @Rollback
    void testAddDoctor() {
        log.info("Starting test: addDoctor");
        DoctorDto dto = new DoctorDto();
        dto.setName("Test Doctor");
        dto.setSpeciality("Cardiology");
        dto.setExperience(10);
        dto.setQualification("MBBS");
        dto.setDesignation("Consultant");

        Doctor doctor = adminService.addDoctor(dto);
        createdDoctorId = doctor.getDoctorId();

        log.info("Doctor created with ID: {}", createdDoctorId);
        Assertions.assertNotNull(doctor.getDoctorId());
        Assertions.assertEquals("Cardiology", doctor.getSpecialty());
    }
//
//    @Test
//    @Order(2)
//    @Rollback
//    void testAddPatient() {
//        log.info("Starting test: addPatient");
//        PatientDto dto = new PatientDto();
//        dto.setName("Test Patient");
//        dto.setGender("Male");
//        dto.setContactNumber("9876543210");
//        dto.setEmail("testpatient@example.com");
//        dto.setAge(30);
//        dto.setAddress("Test Address");
//
//        Patient patient = adminService.addPatient(dto);
//        createdPatientId = patient.getPatientId();
//
//        log.info("Patient created with ID: {}", createdPatientId);
//        Assertions.assertNotNull(patient.getPatientId());
//        Assertions.assertEquals("Male", patient.getGender());
//    }

    @Test
    @Order(3)
    @Rollback
    void testGetAllDoctors() {
        log.info("Fetching all doctors");
        List<Doctor> doctors = adminService.getAllDoctors();
        Assertions.assertNotNull(doctors);
        log.info("Doctors found: {}", doctors.size());
    }

    @Test
    @Order(4)
    @Rollback
    void testGetAllPatients() {
        log.info("Fetching all patients");
        List<Patient> patients = adminService.getAllPatients();
        Assertions.assertNotNull(patients);
        log.info("Patients found: {}", patients.size());
    }

    @Test
    @Order(5)
    @Rollback
    void testDeleteDoctor() {
        log.info("Deleting doctor with ID: {}", createdDoctorId);
        String result = adminService.deleteDoctor(createdDoctorId);
        log.info(result);
        Assertions.assertTrue(result.contains("deleted"));
    }

    @Test
    @Order(6)
    @Rollback
    void testDeletePatient() {
        log.info("Deleting patient with ID: {}", createdPatientId);
        String result = adminService.deletePatient(createdPatientId);
        log.info(result);
        Assertions.assertTrue(result.contains("deleted"));
    }
}
