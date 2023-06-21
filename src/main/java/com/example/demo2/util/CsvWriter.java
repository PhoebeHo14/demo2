package com.example.demo2.util;

import com.example.demo2.pojo.RoleFuncDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private static final String CSV_FILE_PATH = "C:\\output.csv";
    private static final String CSV_SEPARATOR = ",";

    private CsvWriter(){}

    public static void writeRoleFuncDtoListToCsv(List<RoleFuncDto> roleFuncDtoList) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            // Write header
            writer.append("FuncNameEn").append(CSV_SEPARATOR)
                    .append("FuncCode").append(CSV_SEPARATOR)
                    .append("RoleName").append(CSV_SEPARATOR)
                    .append("RoleCode").append(CSV_SEPARATOR)
                    .append("SLA").append('\n');

            // Write data
            for (RoleFuncDto roleFuncDto : roleFuncDtoList) {
                writer.append(roleFuncDto.getFuncNameEn()).append(CSV_SEPARATOR)
                        .append(roleFuncDto.getFuncCode()).append(CSV_SEPARATOR)
                        .append(roleFuncDto.getRoleName()).append(CSV_SEPARATOR)
                        .append(roleFuncDto.getRoleCode()).append(CSV_SEPARATOR)
                        .append(roleFuncDto.getSla()).append('\n');
            }

            writer.flush();
            System.out.println("CSV file has been created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
