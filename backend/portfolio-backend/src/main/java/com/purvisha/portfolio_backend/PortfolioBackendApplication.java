package com.purvisha.portfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.purvisha.portfolio_backend.repository.UserRepository;

@SpringBootApplication
public class PortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner resetRunner(UserRepository userRepository) {
		return args -> {
			if (Boolean.parseBoolean(System.getenv("RESET_USERS"))) {
				userRepository.deleteAll();
				System.out.println("========== DATABASE RESET: All users have been deleted successfully! ==========");
			}
		};
	}
}
