package com.example.demo2;

import com.example.demo2.pojo.RoleFuncDto;
import com.example.demo2.service.MergeFuncRoleService;
import com.example.demo2.util.CsvWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class Demo2Application implements CommandLineRunner {
	private final MergeFuncRoleService mergeFuncRoleService;

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args){
		List<RoleFuncDto> roleFuncDtoList = mergeFuncRoleService.mergeFuncRole();

		CsvWriter.writeRoleFuncDtoListToCsv(roleFuncDtoList);
	}
}
