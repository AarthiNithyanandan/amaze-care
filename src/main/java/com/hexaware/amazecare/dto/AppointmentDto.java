package com.hexaware.amazecare.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AppointmentDto {

    private Integer appointmentId;

    @NotNull(message = "Doctor ID is required")
    @Min(value = 1, message = "Doctor ID must be a positive number")
    private Integer doctorId;

    @NotNull(message = "Patient ID is required")
    @Min(value = 1, message = "Patient ID must be a positive number")
    private Integer patientId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be in present or future")
    private LocalDate appointmentDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Symptoms are required")
    @Size(max = 500, message = "Symptoms must not exceed 500 characters")
    private String symptoms;

    @NotBlank(message = "Visit type is required")
    private String visitType;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

 
    @Override
    public String toString() {
        return "AppointmentDto [appointmentId=" + appointmentId + ", doctorId=" + doctorId 
                + ", patientId=" + patientId + ", appointmentDate=" + appointmentDate 
                + ", status=" + status + ", symptoms=" + symptoms + ", visitType=" + visitType + "]";
    }
}

