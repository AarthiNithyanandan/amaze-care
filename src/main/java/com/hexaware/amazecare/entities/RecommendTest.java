package com.hexaware.amazecare.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecommendTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    private String testName;
    private String description; 
    private String preparationInstructions;
    private float cost;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    @JsonBackReference
    private Appointment appointment;

    public RecommendTest() {}

    public RecommendTest(int testId, String testName, String description, 
                         String preparationInstructions, float cost, 
                         String duration, Appointment appointment) {
        this.testId = testId;
        this.testName = testName;
        this.description = description;
        this.preparationInstructions = preparationInstructions;
        this.cost = cost;
        this.duration = duration;
        this.appointment = appointment;
    }

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPreparationInstructions() { return preparationInstructions; }
    public void setPreparationInstructions(String preparationInstructions) { 
        this.preparationInstructions = preparationInstructions; 
    }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
}

	


