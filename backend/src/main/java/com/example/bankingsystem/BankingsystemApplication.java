package com.example.bankingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Starts the banking system application.
 */
@SpringBootApplication
public class BankingsystemApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args application arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BankingsystemApplication.class, args);
	}
}
