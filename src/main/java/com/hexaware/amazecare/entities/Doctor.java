package com.hexaware.amazecare.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor {
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
    private String name;
    private String speciality;
    private int experience;
    private String qualification;
    private String designation;
    private String email;
    private String passwordDoctor;
    private String contactNumber;
    
    
    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<Appointment> appointments;
    
    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<MedicalRecord> records;

    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Prescription> prescriptions;
    
	public Doctor(int doctorId, String name, String specialty, int experience, String qualification, String designation,
			String email, String passwordDoctor, String contactNumber) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.speciality = speciality;
		this.experience = experience;
		this.qualification = qualification;
		this.designation = designation;
		this.email = email;
		this.passwordDoctor = passwordDoctor;
		this.contactNumber = contactNumber;
	}
	
	
	public Doctor() {
		super();
	}


	public Doctor(String name, String speciality, int experience, String qualification, String designation, String email,
			String passwordDoctor, String contactNumber, List<Appointment> appointments, List<MedicalRecord> records,
			List<Prescription> prescriptions) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.experience = experience;
		this.qualification = qualification;
		this.designation = designation;
		this.email = email;
		this.passwordDoctor = passwordDoctor;
		this.contactNumber = contactNumber;
		this.appointments = appointments;
		this.records = records;
		this.prescriptions = prescriptions;
	}

	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialty() {
		return speciality;
	}
	public void setSpecialty(String speciality) {
		this.speciality = speciality;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordDoctor() {
		return passwordDoctor;
	}
	public void setPasswordDoctor(String passwordDoctor) {
		this.passwordDoctor = passwordDoctor;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
