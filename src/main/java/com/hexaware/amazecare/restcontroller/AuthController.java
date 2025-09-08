package com.hexaware.amazecare.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.LoginRequest;
import com.hexaware.amazecare.dto.LoginResponse;
import com.hexaware.amazecare.exception.InvalidCredentialsException;
import com.hexaware.amazecare.service.AuthenticationService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationService authenticationService;

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginRequest request) throws InvalidCredentialsException {
        return ResponseEntity.ok(authenticationService.loginAdmin(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/doctor/login")
    public ResponseEntity<LoginResponse> loginDoctor(@RequestBody LoginRequest request) throws InvalidCredentialsException {
        return ResponseEntity.ok(authenticationService.loginDoctor(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/patient/login")
    public ResponseEntity<LoginResponse> loginPatient(@RequestBody LoginRequest request) throws InvalidCredentialsException {
        return ResponseEntity.ok(authenticationService.loginPatient(request.getEmail(), request.getPassword()));
    }
}
