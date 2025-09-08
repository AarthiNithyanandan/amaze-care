package com.hexaware.amazecare.dto;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer appointmentId;

    private Integer doctorId;
    private Integer patientId;
    private Integer medicalRecordId;  
    private Integer prescriptionId;

    private String doctorName;   
    private String patientName;  

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;   

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "(?i)scheduled|confirmed|cancelled|completed")
    private String status;

    @NotBlank(message = "Symptoms are required")
    @Size(max = 500, message = "Symptoms must not exceed 500 characters")
    private String symptoms;

    @NotBlank(message = "Visit type is required")
    private String visitType;

    public AppointmentDto(Integer appointmentId, Integer doctorId, Integer patientId,
            String doctorName, String patientName, String status,
            LocalDate appointmentDate, LocalTime appointmentTime,
            String symptoms, String visitType) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.status = status;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.symptoms = symptoms;
        this.visitType = visitType;
    }
    public AppointmentDto(int appointmentId, int doctorId, int patientId, String doctorName, String patientName,
            String status, LocalDate appointmentDate, LocalTime appointmentTime, String symptoms,
            String visitType, Integer medicalRecordId) {
this.appointmentId = appointmentId;
this.doctorId = doctorId;
this.patientId = patientId;
this.doctorName = doctorName;
this.patientName = patientName;
this.status = status;
this.appointmentDate = appointmentDate;
this.appointmentTime = appointmentTime;
this.symptoms = symptoms;
this.visitType = visitType;
this.medicalRecordId = medicalRecordId;

}

}
