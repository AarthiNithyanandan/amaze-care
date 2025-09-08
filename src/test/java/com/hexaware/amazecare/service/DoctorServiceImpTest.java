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

import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

@SpringBootTest
public class DoctorServiceImpTest {

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
        testDoctor.setName("Dr. Meera");
        testDoctor.setSpeciality("Dermatology");
        testDoctor.setEmail("meera@amazecare.com");
        testDoctor.setPasswordDoctor("meera123");
        testDoctor.setContactNumber("9998887770");
        testDoctor.setExperience(5);
        testDoctor.setQualification("MD");
        testDoctor.setDesignation("Consultant");
        doctorRepository.save(testDoctor);

        testPatient = new Patient();
        testPatient.setName("Karan");
        testPatient.setEmail("karan@amazecare.com");
        testPatient.setContactNumber("8887776660");
        testPatient.setAge(28);
        testPatient.setGender("Male");
        testPatient.setAddress("Bangalore");
        testPatient.setPasswordPatient("karan123");
        patientRepository.save(testPatient);
    }

    @Test
    public void testAddDoctor() {
        DoctorDto dto = new DoctorDto();
        dto.setName("Dr. Neha");
        dto.setSpeciality("Neurology");
        dto.setEmail("neha@amazecare.com");
        dto.setPasswordDoctor("neha123");
        dto.setContactNumber("7776665550");
        dto.setExperience(10);
        dto.setQualification("DM");
        dto.setDesignation("Senior Consultant");

        Doctor savedDoctor = doctorService.addDoctor(dto);
        assertNotNull(savedDoctor);
        assertEquals("Dr. Neha", savedDoctor.getName());
    }

    @Test
    public void testUpdateDoctor() {
        DoctorDto dto = new DoctorDto();
        dto.setDoctorId(testDoctor.getDoctorId());
        dto.setName("Dr. Meera Updated");
        dto.setSpeciality("Dermatology");
        dto.setEmail("meera@amazecare.com");
        dto.setPasswordDoctor("newpass123");
        dto.setContactNumber("9998887770");
        dto.setExperience(6);
        dto.setQualification("MD");
        dto.setDesignation("Senior Consultant");

        Doctor updatedDoctor = doctorService.updateDoctor(dto);
        assertEquals("Dr. Meera Updated", updatedDoctor.getName());
        assertEquals("Senior Consultant", updatedDoctor.getDesignation());
    }

    @Test
    public void testGetDoctorById() {
        Doctor fetched = doctorService.getDoctorById(testDoctor.getDoctorId());
        assertEquals(testDoctor.getEmail(), fetched.getEmail());
    }

    @Test
    public void testAcceptAndRejectAppointment() {
        Appointment appointment = new Appointment();
        appointment.setDoctor(testDoctor);
        appointment.setPatient(testPatient);
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setStatus("Scheduled");
        appointment.setSymptoms("Skin rash");
        appointment.setVisitType("In-person");
        appointment = appointmentRepository.save(appointment);

        String acceptMsg = doctorService.acceptAppointment(appointment.getAppointmentId());
        assertEquals("Appointment accepted successfully.", acceptMsg);

        String rejectMsg = doctorService.rejectAppointment(appointment.getAppointmentId());
        assertEquals("Appointment rejected successfully.", rejectMsg);
    }

    @Test
    public void testDeleteDoctor() {
        String msg = doctorService.deleteDoctor(testDoctor.getDoctorId());
        assertEquals("Doctor deleted successfully.", msg);
        assertFalse(doctorRepository.findById(testDoctor.getDoctorId()).isPresent());
    }

    @Test
    public void testFindByName() {
        List<Doctor> doctors = doctorService.findByName("Dr. Meera");
        assertFalse(doctors.isEmpty());
        assertEquals("Dr. Meera", doctors.get(0).getName());
    }

    @Test
    public void testSearchDoctorsBySpecialization() {
        List<Doctor> doctors = doctorService.searchDoctorsBySpecialization("Dermatology");
        assertFalse(doctors.isEmpty());
        assertEquals("Dermatology", doctors.get(0).getSpeciality());
    }

    @Test
    public void testGetAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        assertTrue(doctors.size() >= 1);
    }
    


   

}

