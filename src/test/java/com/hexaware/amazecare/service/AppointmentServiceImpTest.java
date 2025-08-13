package com.hexaware.amazecare.service; 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AppointmentServiceImpTest {

    @Autowired
    private AppointmentServiceImp appointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Doctor testDoctor;
    private Patient testPatient;
    private AppointmentDto appointmentDto;

    @BeforeEach
    public void setUp() {
        // Clean up the test database to avoid data clashes
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRepository.deleteAll();

        // Create and save test doctor with required fields
        testDoctor = new Doctor();
        testDoctor.setName("Dr. Smith");
        testDoctor.setSpecialty("Cardiology");
        testDoctor.setExperience(15);
        testDoctor.setQualification("MBBS, MD");
        testDoctor.setDesignation("Senior Consultant");
        testDoctor.setEmail("dr.smith@example.com");
        testDoctor.setPasswordDoctor("securepass"); // required password field
        testDoctor.setContactNumber("1234567890");
        testDoctor = doctorRepository.save(testDoctor);

        // Create and save test patient with required fields
        testPatient = new Patient();
        testPatient.setName("John Doe");
        testPatient.setGender("Male");
        testPatient.setContactNumber("9876543210");
        testPatient.setEmail("john.doe@example.com");
        testPatient.setAge(35);
        testPatient.setAddress("456 Elm Street, City, Country");
        testPatient = patientRepository.save(testPatient);

        // Prepare test appointment DTO
        appointmentDto = new AppointmentDto();
        appointmentDto.setDoctorId(testDoctor.getDoctorId());
        appointmentDto.setPatientId(testPatient.getPatientId());
        appointmentDto.setAppointmentDate(LocalDate.now()); // match your Timestamp format
        appointmentDto.setStatus("Scheduled");
        appointmentDto.setSymptoms("Chest Pain");
        appointmentDto.setVisitType("Consultation");
    }

    @Test
    public void testAddAppointment_Success() throws Exception {
        Appointment result = appointmentService.addAppointment(appointmentDto);

        assertNotNull(result);
        assertNotNull(result.getAppointmentId());
        assertEquals(testDoctor.getDoctorId(), result.getDoctor().getDoctorId());
        assertEquals(testPatient.getPatientId(), result.getPatient().getPatientId());
        assertEquals("Scheduled", result.getStatus());
    }

    @Test
    public void testAddAppointment_DoctorNotFound() {
        appointmentDto.setDoctorId(999);
        assertThrows(DoctorNotFoundException.class, () -> {
            appointmentService.addAppointment(appointmentDto);
        });
    }

    @Test
    public void testAddAppointment_PatientNotFound() {
        appointmentDto.setPatientId(999);
        assertThrows(PatientNotFoundException.class, () -> {
            appointmentService.addAppointment(appointmentDto);
        });
    }

    @Test
    public void testUpdateAppointmentStatus_Success() throws Exception {
        Appointment created = appointmentService.addAppointment(appointmentDto);
        Appointment updated = appointmentService.updateAppointmentStatus(created.getAppointmentId(), "Completed");

        assertEquals("Completed", updated.getStatus());
    }

    @Test
    public void testUpdateAppointmentStatus_NotFound() {
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.updateAppointmentStatus(999, "Completed");
        });
    }

    @Test
    public void testGetAppointmentById_Success() throws Exception {
        Appointment created = appointmentService.addAppointment(appointmentDto);
        Appointment found = appointmentService.getAppointmentById(created.getAppointmentId());

        assertEquals(created.getAppointmentId(), found.getAppointmentId());
    }

    @Test
    public void testGetAppointmentById_NotFound() {
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.getAppointmentById(999);
        });
    }

    @Test
    public void testGetAppointmentsByPatientId_Success() throws Exception {
        appointmentService.addAppointment(appointmentDto);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(testPatient.getPatientId());

        assertFalse(appointments.isEmpty());
        assertEquals(testPatient.getPatientId(), appointments.get(0).getPatient().getPatientId());
    }

    @Test
    public void testGetAppointmentsByPatientId_NotFound() {
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.getAppointmentsByPatientId(999);
        });
    }

    @Test
    public void testGetAppointmentsByDoctorId_Success() throws Exception {
        appointmentService.addAppointment(appointmentDto);
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(testDoctor.getDoctorId());

        assertFalse(appointments.isEmpty());
        assertEquals(testDoctor.getDoctorId(), appointments.get(0).getDoctor().getDoctorId());
    }

    @Test
    public void testCancelAppointmentByPatientId_Success() throws Exception {
        appointmentService.addAppointment(appointmentDto);
        int cancelledCount = appointmentService.cancelAppointmentByPatientId(testPatient.getPatientId());

        assertEquals(1, cancelledCount);
        Appointment cancelled = appointmentRepository.findAll().get(0);
        assertEquals("Cancelled", cancelled.getStatus());
    }

    @Test
    public void testCancelAppointmentByPatientId_NotFound() {
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.cancelAppointmentByPatientId(999);
        });
    }
}
