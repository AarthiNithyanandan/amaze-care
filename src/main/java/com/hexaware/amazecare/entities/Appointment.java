package com.hexaware.amazecare.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Appointment {

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	 private Doctor doctor;
	
	   @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;
	   
	   @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	   private MedicalRecord medicalRecord;

	   @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
	   private List<RecommendTest> recommendedTests;

	   
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private int appointmentId;
    
    private LocalDate appointmentDate;
    private String status;
    private String symptoms;
    private String visitType;
	public Appointment(int appointmentId,   LocalDate appointmentDate, String status,
			String symptoms, String visitType) {
		super();
		this.appointmentId = appointmentId;
		
		this.appointmentDate = appointmentDate;
		this.status = status;
		this.symptoms = symptoms;
		this.visitType = visitType;
	}
	
	public Appointment() {
		super();
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}
