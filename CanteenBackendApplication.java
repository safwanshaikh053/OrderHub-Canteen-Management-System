package com.canteen.canteen_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class CanteenBackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CanteenBackendApplication.class, args);
	}
}