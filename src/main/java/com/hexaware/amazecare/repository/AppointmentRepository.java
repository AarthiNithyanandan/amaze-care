package com.hexaware.amazecare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.amazecare.entities.Appointment;

import jakarta.transaction.Transactional;

public interface AppointmentRepository  extends  JpaRepository<Appointment,Integer> {

	
	 List<Appointment>findByStatus(String status);
//	 @Modifying
//	 @Query("update Appointment a set a.status=:status where a.appointmentId=:appointmentId")
//	 int  updateAppointmentStatus(@Param("appointmentId")int appointmentId,@Param("status") String status);
//     List<AppointmentDto> findByPatientPatientId(int patientId);
     List<Appointment> findByPatientPatientId(int patientId);
     List<Appointment> findByDoctorDoctorId(int doctorId);
     
     @Modifying
     @Transactional
     @Query("UPDATE Appointment a SET a.status = :status WHERE a.appointmentId = :id")
     int updateStatusOnly(@Param("id") int appointmentId, @Param("status") String status);
}
