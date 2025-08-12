package com.hexaware.amazecare.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto {

	
	private Integer prescriptionId;  

	@Min(value = 1, message = "Medical record  ID must be a positive integer")
	 private int recordId; 
	 @Min(value = 1, message = "Doctor ID must be a positive integer")
	 private int doctorId; 
    @NotBlank(message = "Medicine name is required")
    @Size(max = 255, message = "Medicine name must be at most 255 characters")
    private String medicineName;

    @NotBlank(message = "Dosage is required")
    @Pattern(
        regexp = "^\\d+\\s?(mg|g|ml|units|pills)?$", 
        message = "Dosage must be a number optionally followed by mg, g, ml, units, or pills"
    )
    private String dosage;

    @NotBlank(message = "Timing is required")
    @Size(max = 100, message = "Timing must be at most 100 characters")
    private String timing;

    @Size(max = 500, message = "Instructions must be at most 500 characters")
    private String instructions;

}
