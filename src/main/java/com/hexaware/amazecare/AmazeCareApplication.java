package com.hexaware.amazecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@SpringBootApplication
//@EntityScan("com.example.amazecare.entities")
public class AmazeCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazeCareApplication.class, args);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.addAllowedOrigin("http://localhost:5173");
	    configuration.addAllowedMethod("*");
	    configuration.addAllowedHeader("*");
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
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
