package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.PatientRepository;

@SpringBootTest
public class PatientServiceImpTest {

    @Autowired
    private PatientServiceImp patientService;

    @Autowired
    private PatientRepository patientRepository;

    private PatientDto patientDto;
    
   
    

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();

        patientDto = new PatientDto();
        patientDto.setName("testPatient");
        patientDto.setGender("Female");
        patientDto.setEmail("testPatient@gmail.com");
        patientDto.setContactNumber("1112223333");
        patientDto.setAge(30);
        patientDto.setAddress("123 Wonderland");
        patientDto.setPasswordPatient("password123");
    }

    @Test
    void testAddPatient() {
        Patient savedPatient = patientService.addPatient(patientDto);
        assertNotNull(savedPatient.getPatientId());
        assertEquals("Alice", savedPatient.getName());
    }

   

    @Test
    void testUpdatePatient() {
        Patient savedPatient = patientService.addPatient(patientDto);
        patientDto.setPatientId(savedPatient.getPatientId());
        patientDto.setName("Alice Updated");

        Patient updated = patientService.updatePatient(patientDto);
        assertEquals("Alice Updated", updated.getName());
    }

    @Test
    void testDeletePatient() {
        Patient savedPatient = patientService.addPatient(patientDto);
        int id = savedPatient.getPatientId();
        String msg = patientService.deletePatient(id);
        assertTrue(msg.contains("Patient deleted successfully"));
        assertThrows(PatientNotFoundException.class, () -> patientService.deletePatient(id));
    }

    @Test
    void testGetAllPatients() {
        patientService.addPatient(patientDto);
        List<Patient> patients = patientService.getAllPatients();
        assertFalse(patients.isEmpty());
        assertEquals("Alice", patients.get(0).getName());
    }


//    @AfterEach
//    void cleanup() {
//        patientRepository.deleteAll();
//    }

  
}

