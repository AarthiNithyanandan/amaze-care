package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.MedicalRecordDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Prescription;
import com.hexaware.amazecare.exception.MedicalRecordNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.MedicalRecordRepository;
import com.hexaware.amazecare.repository.PrescriptionRepository;

@SpringBootTest
class MedicalRecordServiceImpTest {

    @Autowired
    private MedicalRecordServiceImp medicalRecordService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private Doctor testDoctor;
    private Appointment testAppointment;
    private Prescription testPrescription;
    private MedicalRecord testMedicalRecord;

    @BeforeEach
    void setUp() {
        // Create doctor
        testDoctor = new Doctor();
        testDoctor.setName("Test Doctor");
        testDoctor.setSpecialty("Cardiology");
        testDoctor.setExperience(12);
        testDoctor.setQualification("MD");
        testDoctor.setDesignation("Senior Consultant");
        testDoctor.setEmail("doc@example.com");
        testDoctor.setPasswordDoctor("password123");
        testDoctor.setContactNumber("9876543210");
        doctorRepository.save(testDoctor);

        // Create appointment
        testAppointment = new Appointment();
        testAppointment.setDoctor(testDoctor);
        testAppointment.setStatus("Pending");
        appointmentRepository.save(testAppointment);

        // Create prescription
        testPrescription = new Prescription();
        testPrescription.setMedicineName("Paracetamol");
        testPrescription.setDosage("500mg");
        testPrescription.setTiming("Twice a day");
        testPrescription.setInstructions("After meals");
        testPrescription.setDoctor(testDoctor);
        prescriptionRepository.save(testPrescription);


        MedicalRecordDto dto = new MedicalRecordDto();
        dto.setDiagnosis("Flu");
        dto.setNotes("Patient needs rest");
        dto.setRecordDate(LocalDate.now());
        dto.setAppointmentId(testAppointment.getAppointmentId());
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPrescriptionId(testPrescription.getPrescriptionId());

        testMedicalRecord = medicalRecordService.addMedicalRecord(dto);
    }

    @AfterEach
    void tearDown() {
        medicalRecordRepository.deleteById(testMedicalRecord.getRecordId());
        prescriptionRepository.deleteById(testPrescription.getPrescriptionId());
        appointmentRepository.deleteById(testAppointment.getAppointmentId());
        doctorRepository.deleteById(testDoctor.getDoctorId());
    }

    @Test
    void testAddMedicalRecord() {
        assertNotNull(testMedicalRecord.getRecordId());
        assertEquals("Flu", testMedicalRecord.getDiagnosis());
    }

    @Test
    void testUpdateMedicalRecord() {
        MedicalRecordDto dto = new MedicalRecordDto();
        dto.setRecordId(testMedicalRecord.getRecordId());
        dto.setDiagnosis("Cold");
        dto.setNotes("Updated notes");
        dto.setRecordDate(LocalDate.now());

        MedicalRecord updated = medicalRecordService.updateMedicalRecord(dto);
        assertEquals("Cold", updated.getDiagnosis());
        assertEquals("Updated notes", updated.getNotes());
    }

    @Test
    void testGetByAppointmentId() {
        MedicalRecord record = medicalRecordService.getByAppointmentId(testAppointment.getAppointmentId());
        assertEquals(testMedicalRecord.getRecordId(), record.getRecordId());
    }

    @Test
    void testGetByRecordId() {
        MedicalRecord record = medicalRecordService.getByRecordId(testMedicalRecord.getRecordId());
        assertEquals("Flu", record.getDiagnosis());
    }

    @Test
    void testGetByDoctorId() {
        List<MedicalRecord> records = medicalRecordService.getByDoctorId(testDoctor.getDoctorId());
        assertFalse(records.isEmpty());
    }

    @Test
    void testGetByDoctorId_NotFound() {
        assertThrows(MedicalRecordNotFoundException.class, () -> {
            medicalRecordService.getByDoctorId(99999);
        });
    }
}

