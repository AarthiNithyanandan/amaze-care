/*Author name:Aarthi
 * Date modified:12-8-2025
 * Service implementation for managing Prescription entities.
 *
 * Responsibilities:
 * Add, update, and fetch prescriptions
 */


package com.hexaware.amazecare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.PrescriptionDto;
import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.Prescription;
import com.hexaware.amazecare.exception.DoctorNotFoundException;
import com.hexaware.amazecare.exception.MedicalRecordNotFoundException;
import com.hexaware.amazecare.exception.PrescriptionNotFoundException;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.MedicalRecordRepository;
import com.hexaware.amazecare.repository.PrescriptionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j

public class PrescriptionServiceImp implements IPrescriptionService {

	@Autowired
    private PrescriptionRepository prescriptionRepository;
	 @Autowired
	    private MedicalRecordRepository medicalRecordRepository;
	    @Autowired
	    private DoctorRepository doctorRepository;
	    
	    
	@Override
    public Prescription addPrescription(PrescriptionDto prescriptionDto) {
	   log.info("Adding prescription for recordId: {} by doctorId: {}", prescriptionDto.getRecordId(), prescriptionDto.getDoctorId());
	   MedicalRecord medicalRecord = medicalRecordRepository.findById(prescriptionDto.getRecordId()) .orElseThrow(() -> {
	   log.error("Medical record not found with ID: {}", prescriptionDto.getRecordId());
	   return new MedicalRecordNotFoundException("Medical record not found with ID: " + prescriptionDto.getRecordId());
	   });
	   Doctor doctor = doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> {
         log.error("Doctor not found with ID:", prescriptionDto.getDoctorId());
        return new DoctorNotFoundException("Doctor not found with ID: " +prescriptionDto.getDoctorId());
               });
	   Prescription prescription = new Prescription();
       prescription.setMedicalRecord(medicalRecord);
       prescription.setDoctor(doctor);
       prescription.setMedicineName(prescriptionDto.getMedicineName());
       prescription.setDosage(prescriptionDto.getDosage());
       prescription.setTiming(prescriptionDto.getTiming());
       prescription.setInstructions(prescriptionDto.getInstructions());

       return prescriptionRepository.save(prescription);
   }
	       
	     
	    
	@Override
	public Prescription updatePrescription(PrescriptionDto prescriptionDto) {
	  Prescription existingPrescription = prescriptionRepository.findById(prescriptionDto.getPrescriptionId()).orElseThrow(() -> 
	  new RuntimeException("Prescription not found with ID: " + prescriptionDto.getPrescriptionId()));
	  existingPrescription.setMedicineName(prescriptionDto.getMedicineName());
	  existingPrescription.setDosage(prescriptionDto.getDosage());
	  existingPrescription.setTiming(prescriptionDto.getTiming());
	  existingPrescription.setInstructions(prescriptionDto.getInstructions());
	  
	  Prescription updatedPrescription = prescriptionRepository.save(existingPrescription);
	    log.info("Prescription updated successfully with ID:", updatedPrescription.getPrescriptionId());

	    return updatedPrescription;
	
	}

	public List<Prescription> getByDoctorId(int doctorId) throws PrescriptionNotFoundException {
		
		log.info("Fetching prescriptions for doctor ID: {}", doctorId);

	    if (!doctorRepository.existsById(doctorId)) {
	        log.error("Doctor not found with ID: {}", doctorId);
	        throw new DoctorNotFoundException("Doctor not found with ID: " + doctorId);
	    }

	    List<Prescription> prescriptions = prescriptionRepository.findByDoctorDoctorId(doctorId);
	    if (prescriptions.isEmpty()) {
	        log.warn("No prescriptions found for doctor ID: {}", doctorId);
	        throw new PrescriptionNotFoundException("Prescriptions not found for: " + doctorId);
	    }

	    return prescriptions;
	}



	public Prescription getByMedicalRecordId(int recordId){
	    log.info("Fetching prescription for medical record ID: {}", recordId);

	    Prescription prescription = prescriptionRepository.findByMedicalRecordRecordId(recordId).orElseThrow(() -> {
	    log.error("No prescription found for medical record ID: {}", recordId);
	    return new PrescriptionNotFoundException(
	   "No prescription found for medical record ID: " + recordId);
	    });

	    return prescription;
	}

}
