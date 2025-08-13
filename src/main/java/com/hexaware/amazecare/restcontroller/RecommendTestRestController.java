package com.hexaware.amazecare.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.RecommendTestDto;
import com.hexaware.amazecare.entities.RecommendTest;
import com.hexaware.amazecare.service.IRecommendTest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/recommend-tests")
@Slf4j
public class RecommendTestRestController {

    @Autowired
    private IRecommendTest recommendTestService;
    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping("/add")
    public RecommendTest addRecommendTest(@Valid @RequestBody RecommendTestDto testDto) {
        log.info("Received request to add recommended test: {}", testDto.getTestName());
        return recommendTestService.addTest(testDto);
    }
    
    
    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping("/{testId}")
    public RecommendTest updateRecommendTest(@PathVariable int testId, @Valid @RequestBody RecommendTestDto testDto) {
        log.info("Received request to update recommended test with ID: {}", testId);
        testDto.setTestId(testId);
        return recommendTestService.updateTestReport(testDto);
    }
    
    @PreAuthorize("hasAnyAuthority('DOCTOR','PATIENT')")
    @GetMapping("/appointment/{appointmentId}")
    public List<RecommendTest> getByAppointmentId(@PathVariable int appointmentId) {
        log.info("Received request to get recommended tests by appointment ID: {}", appointmentId);
        return recommendTestService.getByAppointmentId(appointmentId);
    }
  
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'ADMIN')")
    @GetMapping("/get/test-name")
    public List<RecommendTest> getByTestName(@RequestParam String testName) {
        log.info("Received request to get recommended tests by test name: {}", testName);
        return recommendTestService.getByTestName(testName);
    }

  
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/alltests")
    public List<RecommendTest> getAllRecommendedTests() {
        log.info("Received request to get all recommended tests");
        return recommendTestService.getAllRecommendTests();
    }
    
    
}
    
//  @GetMapping("/patient/{patientId}")
//  public List<RecommendTest> getRecommendedTestsByPatientId(@PathVariable int patientId) {
//      log.info("Received request to get recommended tests by patient ID: {}", patientId);
//      return recommendTestService.getRecommendedTestsByPatientId(patientId);
//  }

