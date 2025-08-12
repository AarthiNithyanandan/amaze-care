package com.hexaware.amazecare.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexaware.amazecare.exception.AppointmentAlreadyExistsException;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.MedicalRecordNotFoundException;
import com.hexaware.amazecare.exception.PatientAlreadyExistsException;
import com.hexaware.amazecare.exception.PatientNotFoundException;
import com.hexaware.amazecare.exception.PrescriptionNotFoundException;
import com.hexaware.amazecare.exception.RecommendTestNotFoundException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handlePatientNotFound(PatientNotFoundException ex) {
    	log.error("Patient not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<String> handlePatientAlreadyExists(PatientAlreadyExistsException ex) {
    	log.error("patient already exists", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> handleAppointmentNotFound(AppointmentNotFoundException ex) {
    	log.error("Appointment not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ResponseEntity<String> handleAppointmentAlreadyExists(AppointmentAlreadyExistsException ex) {
    	log.error("Appointment already exists", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> handleDoctorNotFound(DoctorNotFoundException ex) {
    	log.error("Doctor not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<String> handlePrescriptionNotFound(PrescriptionNotFoundException ex) {
    	log.error("prescription not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<String> handleMedicalRecordNotFound(MedicalRecordNotFoundException ex) {
    	log.error("Medical record not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException ex) {
    	log.error("Patient not found", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


//    // Handle validation errors for @Valid annotated inputs
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> 
//            errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllOtherExceptions(Exception ex) {
    	log.error("Exception occurred", ex.getMessage());
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RecommendTestNotFoundException.class)
    public ResponseEntity<String> handleRecommendTestNotFoundException(RecommendTestNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<String> handlePrescriptionNotFoundException(PrescriptionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
//  @ExceptionHandler(InvalidInputException.class)
//  public ResponseEntity<String> handleInvalidInput(InvalidInputException ex) {
//      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//  }

//  @ExceptionHandler(InvalidStatusException.class)
//  public ResponseEntity<String> handleInvalidStatus(InvalidStatusException ex) {
//      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//  }

//  @ExceptionHandler(OperationNotAllowedException.class)
//  public ResponseEntity<String> handleOperationNotAllowed(OperationNotAllowedException ex) {
//      return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
//  }
}
