package com.hexaware.amazecare.repository;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.entities.Doctor;

public interface DoctorRepository  extends  JpaRepository<Doctor,Integer> {



	    

//	Optional<Doctor> findByEmailAndPasswordDoctor(String email, String passwordDoctor);

	Optional<Doctor> findByEmail(String email);
	    List<Doctor> findByNameIgnoreCase(String name);
	    List<Doctor> findBySpecialityIgnoreCase(String speciality);
	    

}
