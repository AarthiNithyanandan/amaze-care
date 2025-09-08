package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.PrescriptionDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.entities.Prescription;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.MedicalRecordRepository;
import com.hexaware.amazecare.repository.PatientRepository;
import com.hexaware.amazecare.repository.PrescriptionRepository;

@SpringBootTest
public class PrescriptionServiceImpTest {

    @Autowired
    private IPrescriptionService prescriptionService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    private Doctor testDoctor;
    private Patient testPatient;
    private Appointment testAppointment;
    private MedicalRecord testRecord;

    @BeforeEach
    public void setup() {
        prescriptionRepository.deleteAll();
        medicalRecordRepository.deleteAll();
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRepository.deleteAll();

        testDoctor = new Doctor();
        testDoctor.setName("Dr. Nisha");
        testDoctor.setSpeciality("General Medicine");
        testDoctor.setEmail("nisha@amazecare.com");
        testDoctor.setPasswordDoctor("nisha123");
        testDoctor.setContactNumber("9998887770");
        testDoctor.setExperience(7);
        testDoctor.setQualification("MBBS");
        testDoctor.setDesignation("Consultant");
        doctorRepository.save(testDoctor);

        testPatient = new Patient();
        testPatient.setName("Amit");
        testPatient.setEmail("amit@amazecare.com");
        testPatient.setContactNumber("8887776660");
        testPatient.setAge(40);
        testPatient.setGender("Male");
        testPatient.setAddress("Delhi");
        testPatient.setPasswordPatient("amit123");
        patientRepository.save(testPatient);

        testAppointment = new Appointment();
        testAppointment.setDoctor(testDoctor);
        testAppointment.setPatient(testPatient);
        testAppointment.setAppointmentDate(LocalDate.now());
        testAppointment.setStatus("Completed");
        testAppointment.setSymptoms("Fever");
        testAppointment.setVisitType("In-person");
        appointmentRepository.save(testAppointment);

        testRecord = new MedicalRecord();
        testRecord.setAppointment(testAppointment);
        testRecord.setDoctor(testDoctor);
        testRecord.setDiagnosis("Viral Infection");
        testRecord.setNotes("Prescribe rest and fluids");
        testRecord.setRecordDate(LocalDate.now());
        medicalRecordRepository.save(testRecord);
    }

    @Test
    public void testAddPrescription() {
        PrescriptionDto dto = new PrescriptionDto();
        dto.setRecordId(testRecord.getRecordId());
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setMedicineName("Paracetamol");
        dto.setDosage("500mg");
        dto.setTiming("Twice a day");
        dto.setInstructions("After meals");

        Prescription prescription = prescriptionService.addPrescription(dto);
        assertNotNull(prescription);
        assertEquals("Paracetamol", prescription.getMedicineName());
    }

    @Test
    public void testUpdatePrescription() {
        PrescriptionDto dto = new PrescriptionDto();
        dto.setRecordId(testRecord.getRecordId());
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setMedicineName("Ibuprofen");
        dto.setDosage("400mg");
        dto.setTiming("Once a day");
        dto.setInstructions("Before sleep");

        Prescription prescription = prescriptionService.addPrescription(dto);

        PrescriptionDto updateDto = new PrescriptionDto();
        updateDto.setPrescriptionId(prescription.getPrescriptionId());
        updateDto.setMedicineName("Ibuprofen Updated");
        updateDto.setDosage("600mg");
        updateDto.setTiming("Twice a day");
        updateDto.setInstructions("With water");

        Prescription updated = prescriptionService.updatePrescription(updateDto);
        assertEquals("Ibuprofen Updated", updated.getMedicineName());
        assertEquals("600mg", updated.getDosage());
    }

    @Test
    public void testGetByDoctorId() {
        PrescriptionDto dto = new PrescriptionDto();
        dto.setRecordId(testRecord.getRecordId());
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setMedicineName("Cetirizine");
        dto.setDosage("10mg");
        dto.setTiming("Once a day");
        dto.setInstructions("At bedtime");

        prescriptionService.addPrescription(dto);
        List<Prescription> prescriptions = prescriptionService.getByDoctorId(testDoctor.getDoctorId());

        assertFalse(prescriptions.isEmpty());
    }

//    @AfterEach
//    public void cleanup() {
//        prescriptionRepository.deleteAll();
//        medicalRecordRepository.deleteAll();
//        appointmentRepository.deleteAll();
//        doctorRepository.deleteAll();
//        patientRepository.deleteAll();
//    }

}