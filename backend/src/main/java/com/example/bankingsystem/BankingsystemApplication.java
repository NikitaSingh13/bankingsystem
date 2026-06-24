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

	/**
	 * Creates the initial transaction sequence record.
	 *
	 * @param repository transaction sequence repository
	 * @return command line runner
	 */
	@Bean
	CommandLineRunner initializeTransactionSequence(
			final TransactionSequenceRepository repository) {

		return args -> {

			if (repository.findById(1L).isEmpty()) {

				repository.save(
						new TransactionSequence(
								1L,
								1001L));
			}
		};
	}

	/**
	 * Creates the initial Account sequence record.
	 *
	 * @param repository Account sequence repository
	 * @return command line runner
	 */
	@Bean
	CommandLineRunner initializeAccountSequence(
			final AccountSequenceRepository repository) {

		return args -> {

			if (repository.findById(1L).isEmpty()) {

				repository.save(
						new AccountSequence(
								1L,
								1L));
			}
		};
	}

}
