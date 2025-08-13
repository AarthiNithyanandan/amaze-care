package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;

@SpringBootTest

class DoctorServiceImpTest {

    @Autowired
    private DoctorServiceImp doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Doctor savedDoctor;
    private Appointment savedAppointment;

    @BeforeEach
    void setUp() {
        doctorRepository.deleteAll();
        appointmentRepository.deleteAll();

        DoctorDto dto = new DoctorDto();
        dto.setName("John Doe");
        dto.setSpeciality("Cardiology");
        dto.setExperience(10);
        dto.setQualification("MD");
        dto.setDesignation("Consultant");
        dto.setEmail("john@example.com");
        dto.setPasswordDoctor("password123");
        dto.setContactNumber("1234567890");

        savedDoctor = doctorService.addDoctor(dto);

        Appointment appt = new Appointment();
        appt.setDoctor(savedDoctor);
        appt.setStatus("Pending");
        savedAppointment = appointmentRepository.save(appt);
    }

    @Test
    void testAddDoctor() {
        assertNotNull(savedDoctor.getDoctorId());
        assertEquals("John Doe", savedDoctor.getName());
    }

    @Test
    void testGetDoctorById() {
        Doctor doctor = doctorService.getDoctorById(savedDoctor.getDoctorId());
        assertEquals("John Doe", doctor.getName());
    }

    @Test
    void testGetDoctorById_NotFound() {
        assertThrows(DoctorNotFoundException.class, () -> doctorService.getDoctorById(999));
    }

    @Test
    void testAcceptAppointment() {
        String result = doctorService.acceptAppointment(savedAppointment.getAppointmentId());
        assertEquals("Appointment accepted successfully.", result);

        Appointment updated = appointmentRepository.findById(savedAppointment.getAppointmentId()).get();
        assertEquals("Accepted", updated.getStatus());
    }

  
    @Test
    void testFindByName() {
        List<Doctor> doctors = doctorService.findByName("John Doe");
        assertFalse(doctors.isEmpty());
    }
    @AfterEach
    void tearDown() {
        appointmentRepository.deleteById(savedAppointment.getAppointmentId());
        doctorRepository.deleteById(savedDoctor.getDoctorId());
    }
}

