package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.dto.PrescriptionDto;
import com.hexaware.amazecare.entities.Prescription;

public interface IPrescriptionService {

	Prescription addPrescription(PrescriptionDto prescriptionDto);
	 Prescription getByMedicalRecordId(int recordId);
	 Prescription updatePrescription(PrescriptionDto prescriptionDto);
     List<Prescription> getByDoctorId(int doctorId);
//     List<Prescription> getPrescriptionsByPatientId(int patientId);

	

}
