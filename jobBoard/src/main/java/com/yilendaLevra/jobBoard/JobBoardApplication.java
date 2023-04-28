package com.yilendaLevra.jobBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class JobBoardApplication {

//	@Autowired
//	private JobService jobService;

	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner importData() {
//		return args -> {
//			jobService = new JobService();
//			jobService.importJobsFromCsvFile();
//			jobService.close();
//		};
//	}

}

