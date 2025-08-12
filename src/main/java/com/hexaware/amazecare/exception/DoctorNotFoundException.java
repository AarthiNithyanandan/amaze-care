package com.hexaware.amazecare.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
