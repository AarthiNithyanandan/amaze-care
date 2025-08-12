package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;

public interface IAdminService {

	Admin login(String email, String password);
	List<Appointment> viewAllAppointments();
	List<Doctor> viewAllDoctors();
	List<Patient> viewAllPatients();
    String manageDoctors(Doctor doctor);
    String scheduleAppointment(Appointment appointment);
    

}
