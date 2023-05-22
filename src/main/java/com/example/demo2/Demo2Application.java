package com.example.demo2;

import com.example.demo2.service.DataReplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Demo2Application implements CommandLineRunner {
	private final DataReplicationService dataReplicationService;

	public static void main(String[] args)
	{
		SpringApplication.run(Demo2Application.class, args);
		shutdown();
	}

	@Override
	public void run(String... args) {
		dataReplicationService.start();
	}

	private static void shutdown() {
		System.exit(0);
	}
}
