package com.hexaware.amazecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.amazecare.entities")
public class AmazeCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazeCareApplication.class, args);
	}

}
