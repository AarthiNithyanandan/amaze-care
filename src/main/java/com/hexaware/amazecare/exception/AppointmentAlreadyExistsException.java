package com.hexaware.amazecare.exception;

public class AppointmentAlreadyExistsException extends RuntimeException {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
