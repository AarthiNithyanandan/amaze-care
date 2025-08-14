/*Author name:Aarthi
 * Date modified:12-8-2025
 * Service implementation for managing RecommendedTest entities.
 *
 * Responsibilities:
 *  - Add, update, and fetch recommended tests for appointments
 */


package com.hexaware.amazecare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.RecommendTestDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.RecommendTest;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.RecommendTestNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.RecommendTestRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RecommendTestImp implements IRecommendTest{

	
	@Autowired
	RecommendTestRepository recommendTestRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Override
     public RecommendTest addTest(RecommendTestDto testDto) {
        log.info("Adding new recommended test: {}", testDto.getTestName());
        Appointment appointment = appointmentRepository.findById(testDto.getRecordId()).orElseThrow(() -> {
        log.error("Appointment not found for ID: {}", testDto.getRecordId());
        return new AppointmentNotFoundException("Appointment not found with ID: " + testDto.getRecordId());
          });
        RecommendTest entity = new RecommendTest();
        entity.setTestName(testDto.getTestName());
        entity.setDescription(testDto.getDescription());
        entity.setPreparationInstructions(testDto.getPreparationInstructions());
        entity.setCost(testDto.getCost());
        entity.setDuration(testDto.getDuration());
        entity.setAppointment(appointment);

        return recommendTestRepository.save(entity);
    }

	@Override
	public RecommendTest updateTestReport(RecommendTestDto testDto) {
	     log.info("Updating recommended test with ID: {}", testDto.getTestId());
	     RecommendTest existing = recommendTestRepository.findById(testDto.getTestId()).orElseThrow(() -> {
	      log.error("Test not found with ID: {}", testDto.getTestId());
	     return new RecommendTestNotFoundException("Test not found with ID: " + testDto.getTestId());
	      });
	     existing.setTestName(testDto.getTestName());
	      existing.setDescription(testDto.getDescription());
	     existing.setPreparationInstructions(testDto.getPreparationInstructions());
	      existing.setCost(testDto.getCost());
	      existing.setDuration(testDto.getDuration());

	        return recommendTestRepository.save(existing);
	    }

	public List<RecommendTest> getByAppointmentId(int appointmentId) {
	    log.info("Fetching recommended tests for appointment ID: {}", appointmentId);
	    List<RecommendTest> tests = recommendTestRepository.findByAppointmentAppointmentId(appointmentId);
	    if (tests.isEmpty()) {
	        log.error("No recommended tests found for appointment ID: {}", appointmentId);
	        throw new RecommendTestNotFoundException("No recommended tests found for appointment ID: " + appointmentId);
	    }
	    return tests;
	}

	@Override
	public List<RecommendTest> getByTestName(String testName) {
	    log.info("Fetching recommended tests by name: {}", testName);
	    List<RecommendTest> tests = recommendTestRepository.findByTestNameIgnoreCase(testName);
	    if (tests.isEmpty()) {
	        log.error("No recommended tests found with name: {}", testName);
	        throw new RecommendTestNotFoundException("No recommended tests found with name: " + testName);
	    }
	    return tests;
	}
//	@Override
//	public List<RecommendTest> getRecommendedTestsByPatientId(int patientId) {
//	    log.info("Fetching recommended tests for patient ID: {}", patientId);
//	    List<RecommendTest> tests = recommendTestRepository.findByPatientPatientId(patientId);
//	    if (tests.isEmpty()) {
//	        log.error("No recommended tests found for patient ID: {}", patientId);
//	        throw new RecommendTestNotFoundException("No recommended tests found for patient ID: " + patientId);
//	    }
//	    return tests;
//	}

	@Override
	public List<RecommendTest> getAllRecommendTests() {
	    log.info("Fetching all recommended tests");
	    List<RecommendTest> tests = recommendTestRepository.findAll();
	    if (tests.isEmpty()) {
	        log.error("No recommended tests available");
	        throw new RecommendTestNotFoundException("No recommended tests available");
	    }
	    return tests;
	}

	
}