package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.exception.AppointmentAlreadyExistsException;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.PatientNotFoundException;



	public interface IAppointmentService {
		
		 Appointment addAppointment(AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException, DoctorNotFoundException, PatientNotFoundException;
		 Appointment updateAppointmentStatus(int appointmentId, String status);
		 Appointment getAppointmentById(int id)throws AppointmentNotFoundException;
		 List<Appointment> getAppointmentsByPatientId(int patientId);
		 List<Appointment> getAppointmentsByDoctorId(int doctorId);
		 List<Appointment> getAllAppointments();
		 List<Appointment> getByStatus(String status);
		 int cancelAppointmentByPatientId(int patientId);

	    
	}


