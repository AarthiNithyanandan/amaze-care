package com.hexaware.amazecare.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendTestDto {

	
	 private Integer testId;

	    @NotBlank(message = "Test name is required")
	    private String testName;

	    @Size(max = 1000, message = "Description can be up to 1000 characters")
	    private String description;

	    @Size(max = 500, message = "Preparation instructions can be up to 500 characters")
	    private String preparationInstructions;

	    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be positive")
	    private float cost;

	    @Size(max = 50, message = "Duration can be up to 50 characters")
	    private String duration;
	    @Min(value=0,message="Medical record id should be a positive value")
	    private int recordId;
}
