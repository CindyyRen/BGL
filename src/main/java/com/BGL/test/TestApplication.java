package com.BGL.test;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {

		return runner -> {
			// createInstructor(appDAO);

			System.out.println("tempInstructor: ");
		};
	}

}
