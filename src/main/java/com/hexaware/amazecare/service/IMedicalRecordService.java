package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.dto.MedicalRecordDto;
import com.hexaware.amazecare.entities.MedicalRecord;

public interface IMedicalRecordService {

	MedicalRecord addMedicalRecord(MedicalRecordDto recordDto);
	MedicalRecord getByAppointmentId(int appointmentId);
	MedicalRecord getByRecordId(int recordId);
	List<MedicalRecord> getByDoctorId(int doctorId);
	MedicalRecord updateMedicalRecord(MedicalRecordDto recordDto);
	

	
}
