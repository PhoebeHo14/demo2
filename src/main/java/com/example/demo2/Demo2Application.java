package com.example.demo2;

import com.example.demo2.model.SysRevampRoleFuncDo;
import com.example.demo2.pojo.MergedRoleFuncDto;
import com.example.demo2.service.FuncRoleToSqlService;
import com.example.demo2.service.MergeFuncRoleService;
import com.example.demo2.util.CsvWriter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class Demo2Application implements CommandLineRunner {
	private final MergeFuncRoleService mergeFuncRoleService;
	private final FuncRoleToSqlService funcRoleToSqlService;

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args){
		//產生CSV
		List<MergedRoleFuncDto> roleFuncDtoList = mergeFuncRoleService.mergeFuncRole();

		CsvWriter.writeRoleFuncDtoListToCsv(roleFuncDtoList);

		//轉SQL
//		List<SysRevampRoleFuncDo> sysRevampRoleFuncDos = funcRoleToSqlService.convertCsvToList("C:/input.csv");
//
//		funcRoleToSqlService.funcRoleToSql(sysRevampRoleFuncDos);
	}
}
