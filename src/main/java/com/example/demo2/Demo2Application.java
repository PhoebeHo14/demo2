package com.example.demo2;

import com.example.demo2.controller.DataReplicationController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Demo2Application implements CommandLineRunner {
	private final DataReplicationController dataReplicationController;

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataReplicationController.replicateWorkTime();
		System.exit(0);
	}
}
