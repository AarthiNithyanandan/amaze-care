package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.amazecare.dto.PatientDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.exception.InvalidCredentialsException;

public interface IPatientService {

	                      //registering
	    Patient loginPatient(String email, String password) throws InvalidCredentialsException;         //login
	    Patient updatePatient(PatientDto patientDto);
//	    List<MedicalRecord> getMedicalRecordsByPatientId(int patientId);  //medical history
//	    List<Appointment> getAppointmentsByPatientId(int patientId);        //view his own appointments
//	    String cancelAppointmentByPatientId(int appointmentId, int patientId); //cancel his appointment
                                                                               //search doctors by specialization, book appointments
		Patient addPatient(PatientDto patientDto);


}
