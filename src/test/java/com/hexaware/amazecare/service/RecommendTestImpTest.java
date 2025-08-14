package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.RecommendTestDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.RecommendTest;
import com.hexaware.amazecare.exception.RecommendTestNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.RecommendTestRepository;

@SpringBootTest
class RecommendTestImpTest {

    @Autowired
    private RecommendTestImp recommendTestService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RecommendTestRepository recommendTestRepository;

    private Doctor testDoctor;
    private Appointment testAppointment;
    private RecommendTest testRecommendTest;

    @BeforeEach
    void setUp() {
        // Create doctor
        testDoctor = new Doctor();
        testDoctor.setName("Test Doctor");
        testDoctor.setSpecialty("Neurology");
        testDoctor.setExperience(8);
        testDoctor.setQualification("MBBS");
        testDoctor.setDesignation("Consultant");
        testDoctor.setEmail("testdoc@example.com");
        testDoctor.setPasswordDoctor("password");
        testDoctor.setContactNumber("9999999999");
        doctorRepository.save(testDoctor);

        testAppointment = new Appointment();
        testAppointment.setDoctor(testDoctor);
        testAppointment.setStatus("Scheduled");
        appointmentRepository.save(testAppointment);

        RecommendTestDto dto = new RecommendTestDto();
        dto.setTestName("Blood Test");
        dto.setDescription("Basic blood work");
        dto.setPreparationInstructions("Fasting for 8 hours");
        dto.setCost(500.0f);
        dto.setDuration("1 day");
        dto.setRecordId(testAppointment.getAppointmentId()); // maps to appointmentId in addTest

        testRecommendTest = recommendTestService.addTest(dto);
    }

    @AfterEach
    void tearDown() {
        recommendTestRepository.deleteById(testRecommendTest.getTestId());
        appointmentRepository.deleteById(testAppointment.getAppointmentId());
        doctorRepository.deleteById(testDoctor.getDoctorId());
    }

    @Test
    void testAddTest() {
        assertNotNull(testRecommendTest.getTestId());
        assertEquals("Blood Test", testRecommendTest.getTestName());
    }

    @Test
    void testUpdateTestReport() {
        RecommendTestDto dto = new RecommendTestDto();
        dto.setTestId(testRecommendTest.getTestId());
        dto.setTestName("Updated Blood Test");
        dto.setDescription("Updated description");
        dto.setPreparationInstructions("No fasting required");
        dto.setCost(750.0f);
        dto.setDuration("2 days");

        RecommendTest updated = recommendTestService.updateTestReport(dto);
        assertEquals("Updated Blood Test", updated.getTestName());
        assertEquals(750.0f, updated.getCost());
    }

    @Test
    void testGetByAppointmentId() {
        List<RecommendTest> results = recommendTestService.getByAppointmentId(testAppointment.getAppointmentId());
        assertFalse(results.isEmpty());
        assertEquals("Blood Test", results.get(0).getTestName());
    }

    @Test
    void testGetByTestName() {
        List<RecommendTest> results = recommendTestService.getByTestName("Blood Test");
        assertFalse(results.isEmpty());
    }

//    @Test
//    void testGetRecommendedTestsByPatientId_NotFound() {
//        assertThrows(RecommendTestNotFoundException.class, () -> {
//            recommendTestService.getRecommendedTestsByPatientId(999999);
//        });
//    }

    @Test
    void testGetAllRecommendTests() {
        List<RecommendTest> results = recommendTestService.getAllRecommendTests();
        assertFalse(results.isEmpty());
    }
}

