/*Author name:Aarthi
 * Date modified:12-8-2025
 * Service implementation for managing Appointments.
 *
 * Responsibilities:
 * Book, update, cancel, and fetch appointments
 */


package com.hexaware.amazecare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.AppointmentAlreadyExistsException;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AppointmentServiceImp implements IAppointmentService{

	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	
	@Override
    public Appointment addAppointment(AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException, DoctorNotFoundException, PatientNotFoundException {
		    log.info("Attempting to create new appointment for patient:", appointmentDto.getPatientId());
		    
		    Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId()).orElseThrow(() ->
		    new DoctorNotFoundException("Doctor not found with ID: " + appointmentDto.getDoctorId()));
		    Patient patient = patientRepository.findById(appointmentDto.getPatientId()).orElseThrow(() -> 
		    new PatientNotFoundException("Patient not found with ID: " + appointmentDto.getPatientId()));
		   
		    
		    Appointment appointment = new Appointment();
		    appointment.setDoctor(doctor); 
		    appointment.setPatient(patient); 
		    appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
		    appointment.setStatus(appointmentDto.getStatus());
		    appointment.setSymptoms(appointmentDto.getSymptoms());
		    appointment.setVisitType(appointmentDto.getVisitType());
		    Appointment savedAppointment = appointmentRepository.save(appointment);
		    log.info("Successfully created appointment with ID:", savedAppointment.getAppointmentId());
		    return appointmentRepository.findById(savedAppointment.getAppointmentId()).orElse(savedAppointment);

	}
	
	@Override
    public Appointment updateAppointmentStatus(int appointmentId, String status) throws AppointmentNotFoundException{
		      

		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
		new AppointmentNotFoundException("Appointment not found with ID:" + appointmentId));
		appointment.setStatus(status);
          return appointmentRepository.save(appointment);
	}
	
	
	@Override
	 public Appointment getAppointmentById(int appointmentId) {
        log.info("Fetching appointment with ID:", appointmentId);
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> {
        log.error("Appointment not found with ID:", +appointmentId);
        return new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
          });
	}

	public List<Appointment> getAppointmentsByPatientId(int patientId) throws AppointmentNotFoundException{
	    log.info("Fetching appointments for patient ID:", patientId);
	    List<Appointment> appointments = appointmentRepository.findByPatientPatientId(patientId);

	    if (appointments.isEmpty()) {
	        log.warn("No appointments found for patient ID:", patientId);
	        throw new AppointmentNotFoundException("No appointments found for patient ID: " + patientId);
	    }
	    return appointments;
	}

	public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
	    log.info("Fetching appointments for doctor ID: {}", doctorId);
	    List<Appointment> appointments = appointmentRepository.findByDoctorDoctorId(doctorId);

	    if (appointments.isEmpty()) {
	        log.warn("No appointments found for doctor ID:", doctorId);
	        throw new AppointmentNotFoundException("No appointments found for doctor ID: " + doctorId);
	    }
	    return appointments;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public List<Appointment> getByStatus(String status) {
	    log.info("Fetching appointments by status", status);
	    List<Appointment> appointments = appointmentRepository.findByStatus(status);

	    if (appointments.isEmpty()) {
	        log.warn("No appointments found with status:", status);
	    }

	    return appointments;
	}

	public int cancelAppointmentByPatientId(int patientId) {
	    List<Appointment> appointments = appointmentRepository.findByPatientPatientId(patientId);

	    if (appointments.isEmpty()) {
	        log.error("No appointments found for patientId: ", patientId);
	        throw new AppointmentNotFoundException("No appointments found for patientId:" + patientId);
	    }

	    int updatedCount = 0;
	    for (Appointment appointment : appointments) {
	        if (!"Cancelled".equalsIgnoreCase(appointment.getStatus())) {
	            appointment.setStatus("Cancelled");
	            appointmentRepository.save(appointment);
	            updatedCount++;
	            log.info("Cancelled appointmentId: {} for patientId: {}", appointment.getAppointmentId(), patientId);
	        }
	    }

	    return updatedCount;
	}

	
}
