package com.hexaware.amazecare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

@SpringBootTest
public class AdminServiceImpTest {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Doctor testDoctor;
    private Patient testPatient;

    @BeforeEach
    public void setup() {
        

        testDoctor = new Doctor();
        testDoctor.setName("Dr. Arvind");
        testDoctor.setSpecialty("Orthopedics");
        testDoctor.setEmail("arvind@amazecare.com");
        testDoctor.setPasswordDoctor("arvind123");
        testDoctor.setContactNumber("9998887770");
        testDoctor.setExperience(10);
        testDoctor.setQualification("MS");
        testDoctor.setDesignation("Consultant");
        doctorRepository.save(testDoctor);

        testPatient = new Patient();
        testPatient.setName("Sneha");
        testPatient.setEmail("sneha@amazecare.com");
        testPatient.setContactNumber("8887776660");
        testPatient.setAge(35);
        testPatient.setGender("Female");
        testPatient.setAddress("Mumbai");
        testPatient.setPasswordPatient("sneha123");
        patientRepository.save(testPatient);
    }

    @Test
    public void testAddDoctorViaAdmin() {
        DoctorDto dto = new DoctorDto();
        dto.setName("Dr. Neeraj");
        dto.setSpeciality("Cardiology");
        dto.setEmail("neeraj@amazecare.com");
        dto.setPasswordDoctor("neeraj123");
        dto.setContactNumber("7776665550");
        dto.setExperience(12);
        dto.setQualification("DM");
        dto.setDesignation("Senior Consultant");

        Doctor doctor = adminService.addDoctor(dto);
        assertNotNull(doctor);
        assertEquals("Dr. Neeraj", doctor.getName());
    }

    @Test
    public void testUpdateDoctorViaAdmin() {
        DoctorDto dto = new DoctorDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setName("Dr. Arvind Updated");
        dto.setSpeciality("Orthopedics");
        dto.setEmail("arvind@amazecare.com");
        dto.setPasswordDoctor("newpass123");
        dto.setContactNumber("9998887770");
        dto.setExperience(11);
        dto.setQualification("MS");
        dto.setDesignation("Senior Consultant");

        Doctor updated = adminService.updateDoctor(dto);
        assertEquals("Dr. Arvind Updated", updated.getName());
    }

    @Test
    public void testDeleteDoctorViaAdmin() {
        String msg = adminService.deleteDoctor(testDoctor.getDoctorId());
        assertEquals("Doctor deleted successfully.", msg);
        assertFalse(doctorRepository.findById(testDoctor.getDoctorId()).isPresent());
    }

    @Test
    public void testGetAllDoctorsViaAdmin() {
        List<Doctor> doctors = adminService.getAllDoctors();
        assertTrue(doctors.size() >= 1);
    }

    @Test
    public void testUpdatePatientViaAdmin() {
        PatientDto dto = new PatientDto();
        dto.setPatientId(testPatient.getPatientId());
        dto.setName("Sneha Updated");
        dto.setEmail("sneha@amazecare.com");
        dto.setPasswordPatient("newpass456");
        dto.setContactNumber("8887776660");
        dto.setAge(36);
        dto.setGender("Female");
        dto.setAddress("Pune");

        Patient updated = adminService.updatePatient(dto);
        assertEquals("Sneha Updated", updated.getName());
    }

    @Test
    public void testDeletePatientViaAdmin() {
        String msg = adminService.deletePatient(testPatient.getPatientId());
        assertEquals("Patient deleted successfully.", msg);
        assertFalse(patientRepository.findById(testPatient.getPatientId()).isPresent());
    }

    @Test
    public void testGetAllPatientsViaAdmin() {
        List<Patient> patients = adminService.getAllPatients();
        assertTrue(patients.size() >= 1);
    }

    @Test
    public void testAddAppointmentViaAdmin() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(1));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Joint pain");
        dto.setVisitType("In-person");

        Appointment appointment = adminService.addAppointment(dto);
        assertNotNull(appointment);
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    public void testUpdateAppointmentStatusViaAdmin() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(2));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Back pain");
        dto.setVisitType("Online");

        Appointment appointment = adminService.addAppointment(dto);
        Appointment updated = adminService.updateAppointmentStatus(appointment.getAppointmentId(), "Completed");

        assertEquals("Completed", updated.getStatus());
    }

    @Test
    public void testGetAllAppointmentsViaAdmin() {
        List<Appointment> appointments = adminService.getAllAppointments();
        assertNotNull(appointments);
    }

    @Test
    public void testGetAppointmentByIdViaAdmin() {
        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setPatientId(testPatient.getPatientId());
        dto.setAppointmentDate(LocalDate.now().plusDays(3));
        dto.setStatus("Scheduled");
        dto.setSymptoms("Migraine");
        dto.setVisitType("In-person");

        Appointment appointment = adminService.addAppointment(dto);
        Appointment fetched = adminService.getAppointmentById(appointment.getAppointmentId());

        assertEquals(appointment.getAppointmentId(), fetched.getAppointmentId());
    }
    
   
    
}

