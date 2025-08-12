package com.hexaware.amazecare.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
