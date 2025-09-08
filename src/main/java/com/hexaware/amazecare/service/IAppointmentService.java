package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Map;
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
		 List<AppointmentDto> getAppointmentsByPatientId(int patientId);
		  Map<String, Long> getAppointmentCountsByPatientId(int patientId) ;
//		 List<Appointment> getAppointmentsByPatientId1(int patientId);
		 List<AppointmentDto> getAppointmentsByDoctorId(int doctorId);
		 List<Appointment> getAllAppointments();
		 List<Appointment> getByStatus(String status);
		 public Appointment cancelAppointmentById(int appointmentId);

	    
	}


