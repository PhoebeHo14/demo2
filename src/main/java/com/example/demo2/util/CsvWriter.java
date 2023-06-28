package com.example.demo2.util;

import com.example.demo2.pojo.MergedRoleFuncDto;
import com.example.demo2.pojo.RoleFuncDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private static final String CSV_FILE_PATH = "C:\\output.csv";
    private static final String CSV_SEPARATOR = ",";

    private CsvWriter(){}

    public static void writeRoleFuncDtoListToCsv(List<MergedRoleFuncDto> roleFuncDtoList) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            // Write header
            writer.append("FUNC_NAME(FUNC_CODE)").append(CSV_SEPARATOR)
                    .append("ROLE_NAME(ROLE_CODE)").append(CSV_SEPARATOR)
                    .append("SLA").append('\n');

            // Write data
            for (MergedRoleFuncDto mergedRoleFuncDto : roleFuncDtoList) {
                writer.append(mergedRoleFuncDto.getFunc()).append(CSV_SEPARATOR)
                        .append("\"").append(mergedRoleFuncDto.getRole()).append("\"").append(CSV_SEPARATOR)
                        .append(mergedRoleFuncDto.getSla()).append('\n');
            }

            writer.flush();
            System.out.println("CSV file has been created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
