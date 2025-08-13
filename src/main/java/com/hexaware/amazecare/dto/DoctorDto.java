package com.hexaware.amazecare.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    private Integer doctorId;  

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Specialty is mandatory")
    private String speciality;

    @Min(value = 0, message = "Experience must be zero or positive")
    private int experience;

    @NotBlank(message = "Qualification is mandatory")
    private String qualification;

    @NotBlank(message = "Designation is mandatory")
    private String designation;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    @Size(min = 12, message = "Password must be at least 12 characters long")
    private String passwordDoctor;
    
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNumber;

    
}
