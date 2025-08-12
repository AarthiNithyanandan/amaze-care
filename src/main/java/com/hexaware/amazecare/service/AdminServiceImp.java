package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.amazecare.entities.Admin;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AdminRepository;

public class AdminServiceImp implements IAdminService {

	@Override
	public Admin login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> viewAllAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> viewAllDoctors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> viewAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String manageDoctors(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String scheduleAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
