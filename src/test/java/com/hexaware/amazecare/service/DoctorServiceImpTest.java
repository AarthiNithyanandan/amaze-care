package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.exception.DoctorNotFoundException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)  // To run tests in order
public class DoctorServiceImpTest {

    @Autowired
    private DoctorServiceImp doctorService;

  
    @Test

    void testAddDoctor() {
        DoctorDto dto = new DoctorDto();
        dto.setName("Dr. Test");
        dto.setSpecialty("Cardiology");
        dto.setExperience(5);
        dto.setQualification("MD");
        dto.setDesignation("Consultant");
        dto.setEmail("dr.test@example.com");
        dto.setPasswordDoctor("password123456");
        dto.setContactNumber("9876543210");

        Doctor savedDoctor = doctorService.addDoctor(dto);
        assertNotNull(savedDoctor);
        assertNotNull(savedDoctor.getDoctorId());

        
    }

    @Test
    void testGetDoctorById() {
    	   DoctorDto dto = new DoctorDto();
           dto.setName("Dr. Test");
           dto.setSpecialty("Cardiology");
           dto.setExperience(5);
           dto.setQualification("MD");
           dto.setDesignation("Consultant");
           dto.setEmail("dr.test@example.com");
           dto.setPasswordDoctor("password123456");
           dto.setContactNumber("9876543210");

           Doctor savedDoctor = doctorService.addDoctor(dto);
    
      
        assertEquals("Dr.Test", savedDoctor.getName());
    }

  
