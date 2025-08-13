package com.hexaware.amazecare.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.dto.DoctorDto;
import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.AppointmentAlreadyExistsException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.AdminRepository;
import com.hexaware.amazecare.security.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImp implements IAdminService {

    @Autowired
    IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IAppointmentService appointmentService;
    
    @Autowired
    private AdminRepository adminRepository;
 
    
    @Autowired
    private PasswordEncoder passwordEncoder;  // from config bean

    @Autowired
    private JwtService jwtService;

    
   

    // Doctor management
    @Override
    public Doctor addDoctor(DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);
    }

    @Override
    public Doctor updateDoctor(DoctorDto doctorDto) {
        return doctorService.updateDoctor(doctorDto);
    }

    @Override
    public String deleteDoctor(int doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

    public List<Doctor> getAllDoctors() {
        log.info("Admin fetching all doctors");
        return doctorService.getAllDoctors();
    }

    @Override
    public Patient updatePatient(PatientDto patientDto) {
        return patientService.updatePatient(patientDto);
    }

    @Override
    public String deletePatient(int patientId) {
       
        return patientService.deletePatient(patientId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @Override
    public Appointment addAppointment(AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException, DoctorNotFoundException, PatientNotFoundException {
        return appointmentService.addAppointment(appointmentDto);
    }

    @Override
    public Appointment updateAppointmentStatus(int appointmentId, String status) {
        return appointmentService.updateAppointmentStatus(appointmentId, status);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

}
