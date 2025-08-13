package com.hexaware.amazecare.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Patient {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int patientId;
     
	    private String name;
	    private String gender;
	    private String contactNumber;
	    private String email;
	    private int age;
	    private String address;
	    private String passwordPatient;
	    
	    public Patient(int patientId, String name, String gender, String contactNumber, String email, int age,
				String address, String passwordPatient, List<Appointment> appointments) {
			super();
			this.patientId = patientId;
			this.name = name;
			this.gender = gender;
			this.contactNumber = contactNumber;
			this.email = email;
			this.age = age;
			this.address = address;
			this.passwordPatient = passwordPatient;
			this.appointments = appointments;
		}
		public String getPasswordPatient() {
			return passwordPatient;
		}
		public void setPasswordPatient(String passwordPatient) {
			this.passwordPatient = passwordPatient;
		}
		public List<Appointment> getAppointments() {
			return appointments;
		}
		public void setAppointments(List<Appointment> appointments) {
			this.appointments = appointments;
		}
		@OneToMany(mappedBy = "patient")
	    private List<Appointment> appointments;

		public Patient(int patientId, String name, String gender, String contactNumber, String email, int age,
				String address) {
			super();
			this.patientId = patientId;
			this.name = name;
			this.gender = gender;
			this.contactNumber = contactNumber;
			this.email = email;
			this.age = age;
			this.address = address;
		}
		public Patient() {
			super();
		}
		public int getPatientId() {
			return patientId;
		}
		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
}
