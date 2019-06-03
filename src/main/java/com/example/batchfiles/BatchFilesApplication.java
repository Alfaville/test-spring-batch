package com.example.batchfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.*")
public class BatchFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchFilesApplication.class, args);
	}

}
