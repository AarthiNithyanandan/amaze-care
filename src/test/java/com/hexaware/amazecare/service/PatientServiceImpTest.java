package com.hexaware.amazecare.service;

import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
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
        patientDto.setName("John Doe");
        patientDto.setGender("Male");
        patientDto.setContactNumber("1234567890");
        patientDto.setEmail("john.doe@example.com");
        patientDto.setAge(35);
        patientDto.setAddress("123 Main Street");
    }

    @Test
    public void testAddPatient_Success() {
        Patient savedPatient = patientService.addPatient(patientDto);

        assertNotNull(savedPatient.getPatientId());
        assertEquals("John Doe", savedPatient.getName());
        assertEquals("Male", savedPatient.getGender());
    }

    @Test
    public void testLoginPatient() throws InvalidCredentialsException {
  
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setGender("Female");
        patient.setContactNumber("9876543210");
        patient.setEmail("jane@example.com");
        patient.setAge(28);
        patient.setAddress("456 Elm Street");
        patient.setPasswordPatient("password123");
        patientRepository.save(patient);

        LoginResponse loggedIn = patientService.loginPatient("jane@example.com", "password123");

        assertEquals("jane@example.com", loggedIn.getEmail());
        assertThrows(PatientNotFoundException.class, () -> {
            patientService.loginPatient("noone@example.com", "wrongpassword");
        });
    }

   

    @Test
    public void testUpdatePatient() {
        Patient patient = new Patient();
        patient.setName("Old Name");
        patient.setGender("Male");
        patient.setContactNumber("0000000000");
        patient.setEmail("old@example.com");
        patient.setAge(40);
        patient.setAddress("Old Address");
        patient = patientRepository.save(patient);
        patientDto.setPatientId(patient.getPatientId());
        patientDto.setName("New Name");
        Patient updated = patientService.updatePatient(patientDto);
        assertEquals("New Name", updated.getName());
        assertEquals(patient.getPatientId(), updated.getPatientId());
        patientDto.setPatientId(999);
        assertThrows(PatientNotFoundException.class, () -> {
            patientService.updatePatient(patientDto);
        });
    }
    

  
    }

