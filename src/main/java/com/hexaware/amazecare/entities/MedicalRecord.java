package com.hexaware.amazecare.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class MedicalRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
   
    private String diagnosis;
    private String notes;
    private LocalDate recordDate;
    
   
	@OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

	@OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private Prescription prescription;

    
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


	public LocalDate getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(LocalDate recordDate) {
		this.recordDate = recordDate;
	}
	public MedicalRecord(int recordId, String diagnosis, String notes,LocalDate recordDate) {
		super();
		this.recordId = recordId;
	
		this.diagnosis = diagnosis;
		this.notes = notes;
		this.recordDate=recordDate;
	}
		public MedicalRecord() {
		super();
	}

	    public Appointment getAppointment() {
			return appointment;
		}
		public void setAppointment(Appointment appointment) {
			this.appointment = appointment;
		}
		public Prescription getPrescription() {
			return prescription;
		}
		public void setPrescription(Prescription prescription) {
			this.prescription = prescription;
		}
		public Doctor getDoctor() {
			return doctor;
		}
		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
    
}
