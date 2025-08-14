package com.hexaware.amazecare.service;


/*Author name:Aarthi
 * Date modified:11-8-25
 * Service implementation for managing MedicalRecord entities.
 * 
 * Responsibilities:
 *  - Add new medical records
 *  - Update existing medical records
 *  - Retrieve medical records by record ID, appointment ID, or doctor ID
 * 
 * Works with:
 *  - Appointment
 *  - Doctor
 *  - Prescription
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.MedicalRecordDto;
import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Prescription;
import com.hexaware.amazecare.exception.AppointmentNotFoundException;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.MedicalRecordNotFoundException;
import com.hexaware.amazecare.exception.PrescriptionNotFoundException;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.MedicalRecordRepository;
import com.hexaware.amazecare.repository.PrescriptionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class MedicalRecordServiceImp implements IMedicalRecordService {
	
	 @Autowired
	    private MedicalRecordRepository medicalRecordRepository;

	 @Autowired
      private AppointmentRepository appointmentRepository;
	 @Autowired
	 private DoctorRepository doctorRepository;
	 
	 @Autowired
	 private PrescriptionRepository prescriptionRepository;
	    
	 @Override
	    public MedicalRecord addMedicalRecord(MedicalRecordDto recordDto) {
	        log.info("Adding new medical record for appointment ID: {}", recordDto.getAppointmentId());
	        Appointment appointment = appointmentRepository.findById(recordDto.getAppointmentId()).orElseThrow(() -> {
	        log.error("Appointment not found with ID: {}", recordDto.getAppointmentId());
	        return new AppointmentNotFoundException("Appointment not found with ID: " + recordDto.getAppointmentId());
	                });
	        Doctor doctor = doctorRepository.findById(recordDto.getDoctorId()).orElseThrow(() -> {
	        log.error("Doctor not found with ID:", recordDto.getDoctorId());
	             return new DoctorNotFoundException("Doctor not found with ID: " + recordDto.getDoctorId());
	                });
	     
	        Prescription prescription = prescriptionRepository.findById(recordDto.getPrescriptionId()).orElseThrow(() -> {
	        	 return new PrescriptionNotFoundException("Prescription not found with ID: " + recordDto.getPrescriptionId());
	        	    });
	        

	        MedicalRecord medicalRecord = new MedicalRecord();
	        medicalRecord.setDiagnosis(recordDto.getDiagnosis());
	        medicalRecord.setNotes(recordDto.getNotes());
	        medicalRecord.setRecordDate(recordDto.getRecordDate());
	        medicalRecord.setAppointment(appointment);
	        medicalRecord.setDoctor(doctor);
	        medicalRecord.setPrescription(prescription);
	        return medicalRecordRepository.save(medicalRecord);
	    }



	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecordDto recordDto) {
		 log.info("Updating medical record ID: {}", recordDto.getRecordId());

	     MedicalRecord existing = medicalRecordRepository.findById(recordDto.getRecordId()).orElseThrow(() ->
	           new MedicalRecordNotFoundException("Medical record not found"));

	     existing.setDiagnosis(recordDto.getDiagnosis());
	     existing.setNotes(recordDto.getNotes());
	     existing.setRecordDate(recordDto.getRecordDate());
		 MedicalRecord updated = medicalRecordRepository.save(existing);
	        log.info("Medical record updated: {}", updated.getRecordId());
	        return updated;
	}

	@Override
	public MedicalRecord getByAppointmentId(int appointmentId) {
		 return medicalRecordRepository.findByAppointmentAppointmentId(appointmentId).orElseThrow(() ->
		 new MedicalRecordNotFoundException("Medical record not found for appointment ID: " + appointmentId));
	    }

	@Override
	public MedicalRecord getByRecordId(int recordId) {
		  return medicalRecordRepository.findById(recordId).orElseThrow(() ->
		  new MedicalRecordNotFoundException("Medical record not found with ID: " + recordId));
	    }

	@Override
	public List<MedicalRecord> getByDoctorId(int doctorId) {
		List<MedicalRecord> records = medicalRecordRepository.findByDoctorDoctorId(doctorId);
        if(records.isEmpty()) {
            throw new MedicalRecordNotFoundException("No medical records found for doctor ID: " + doctorId);
        }
        return records;

	}

}
