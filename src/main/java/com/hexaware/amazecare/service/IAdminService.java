package com.hexaware.amazecare.service;

import java.util.List;

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

public interface IAdminService {

	
//	  LoginResponse loginAdmin(String email,String password) throws InvalidCredentialsException;
    // Doctor management
    Doctor addDoctor(DoctorDto doctorDto);
    Doctor updateDoctor(DoctorDto doctorDto);
    String deleteDoctor(int doctorId);
   
    List<Doctor> getAllDoctors();

    // Patient management
    Patient updatePatient(PatientDto patientDto);
    String deletePatient(int patientId);
    List<Patient> getAllPatients();

    // Appointment management
    Appointment addAppointment(AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException, DoctorNotFoundException, PatientNotFoundException;
    Appointment updateAppointmentStatus(int appointmentId, String status);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int appointmentId);
}
