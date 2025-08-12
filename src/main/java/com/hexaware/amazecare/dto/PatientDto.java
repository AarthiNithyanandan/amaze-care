package com.hexaware.amazecare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

	

    private Integer patientId;  // nullable for create

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNumber;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 0, message = "Age must be zero or positive")
    private Integer age;

    @NotBlank(message = "Address is required")
    private String address;
}
