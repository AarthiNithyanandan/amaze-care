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

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

@SpringBootTest
public class AppointmentServiceImpTest {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    private Doctor testDoctor;
    private Patient testPatient;

    @BeforeEach
    public void setup() {
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRepository.deleteAll();

        testDoctor = new Doctor();
        testDoctor.setName("Dr. Kavya");
        testDoctor.setSpecialty("Pediatrics");
        testDoctor.setEmail("kavya@amazecare.com");
        testDoctor.setPasswordDoctor("kavya123");
        testDoctor.setContactNumber("9998887770");
        testDoctor.setExperience(8);
        testDoctor.setQualification("MD");
        testDoctor.setDesignation("Consultant");
        doctorRepository.save(testDoctor);

        testPatient = new Patient();
        testPatient.setName("Rohan");
        testPatient.setEmail("rohan@amazecare.com");
        testPatient.setContactNumber("8887776660");
        testPatient.setAge(12);
        testPatient.setGender("Male");
        testPatient.setAddress("Hyderabad");
        testPatient.setPasswordPatient("rohan123");
        patientRepository.save(testPatient);
    }

    @Test
    public void testAddAppointment() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(1));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Fever");
        dto.setVisitType("In-person");

        Appointment appointment = appointmentService.addAppointment(dto);
        assertNotNull(appointment);
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    public void testUpdateAppointmentStatus() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(2));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Cold");
        dto.setVisitType("Online");

        Appointment appointment = appointmentService.addAppointment(dto);
        Appointment updated = appointmentService.updateAppointmentStatus(appointment.getAppointmentId(), "Completed");

        assertEquals("Completed", updated.getStatus());
    }

    @Test
    public void testGetAppointmentById() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(3));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Cough");
        dto.setVisitType("In-person");

        Appointment appointment = appointmentService.addAppointment(dto);
        Appointment fetched = appointmentService.getAppointmentById(appointment.getAppointmentId());

        assertEquals(appointment.getAppointmentId(), fetched.getAppointmentId());
    }

    @Test
    public void testGetAppointmentsByPatientId() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(4));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Headache");
        dto.setVisitType("Online");

        appointmentService.addAppointment(dto);
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByPatientId(testPatient.getPatientId());

        assertFalse(appointments.isEmpty());
    }

    @Test
    public void testGetAppointmentsByDoctorId() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(5));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Stomach ache");
        dto.setVisitType("In-person");

        appointmentService.addAppointment(dto);
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDoctorId(testDoctor.getDoctorId());

        assertFalse(appointments.isEmpty());
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        assertNotNull(allAppointments);
    }

    @Test
    public void testGetByStatus() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(6));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Fatigue");
        dto.setVisitType("Online");

        appointmentService.addAppointment(dto);
        List<Appointment> scheduledAppointments = appointmentService.getByStatus("Scheduled");

        assertFalse(scheduledAppointments.isEmpty());
    }

    @Test
    public void testCancelAppointmentById() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(7));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Body pain");
        dto.setVisitType("In-person");

       appointmentService.addAppointment(dto);
        Appointment appointment = appointmentService.cancelAppointmentById(testPatient.getPatientId());

//        assertEquals(1, cancelledCount);
    }
    
   
//    @AfterEach
//    public void cleanup() {
//        appointmentRepository.deleteAll();
//        doctorRepository.deleteAll();
//        patientRepository.deleteAll();
//    }

}

