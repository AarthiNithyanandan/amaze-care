package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.dto.RecommendTestDto;
import com.hexaware.amazecare.entities.RecommendTest;

public interface IRecommendTest {

	 RecommendTest addTest(RecommendTestDto testDto); 
	    RecommendTest updateTestReport(RecommendTestDto testDto);
	    List<RecommendTest> getByAppointmentId(int appointmentId);
	    List<RecommendTest> getByTestName(String testName);
//	    List<RecommendTest> getRecommendedTestsByPatientId(int patientId);
	    List<RecommendTest> getAllRecommendTests();
}