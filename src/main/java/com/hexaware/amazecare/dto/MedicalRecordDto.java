package com.hexaware.amazecare.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class MedicalRecordDto {

    private Integer recordId;  // nullable for create

    @NotBlank(message = "Diagnosis is required")
    @Size(max = 500, message = "Diagnosis must be at most 500 characters")
    private String diagnosis;

    @Size(max = 1000, message = "Notes must be at most 1000 characters")
    private String notes;

    @NotNull(message = "Record date is required")
    private Timestamp recordDate;

    @NotNull(message = "Appointment ID is required")
    private Integer appointmentId;

    @NotNull(message = "Doctor ID is required")
    private Integer doctorId;
    
    @NotNull(message = "Prescription ID is required")
    private Integer prescriptionId;
    
    
}
