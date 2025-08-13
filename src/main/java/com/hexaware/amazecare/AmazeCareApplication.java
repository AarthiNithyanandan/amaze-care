package com.hexaware.amazecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EntityScan("com.example.amazecare.entities")
public class AmazeCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazeCareApplication.class, args);
	}
//	@Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//            .info(new Info()
//                .title("AmazeCare API")
//                .version("1.0")
//                .description("API documentation for AmazeCare"));
//    }
	


}
