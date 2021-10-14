package com.example.DigioProgrammingTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DigioProgrammingTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigioProgrammingTaskApplication.class, args);
	}

}
