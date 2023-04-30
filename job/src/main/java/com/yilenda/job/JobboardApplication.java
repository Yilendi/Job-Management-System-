package com.yilenda.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobboardApplication {

	@Autowired
	JobService jobService;

	public static void main(String[] args) {
		SpringApplication.run(JobboardApplication.class, args);
	}

	@Bean
	public CommandLineRunner importData() {
		return args -> {
			jobService.setUp();
			jobService.importJobsFromCsvFile();
			jobService.close();
			System.out.println("Done!");
		};
	}
}
