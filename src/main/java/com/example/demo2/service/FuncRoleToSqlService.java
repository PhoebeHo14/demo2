package com.example.demo2.service;

import com.example.demo2.dao.SysRevampFuncRepository;
import com.example.demo2.dao.SysRoleRepository;
import com.example.demo2.model.SysRevampRoleFuncDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncRoleToSqlService {
    private static final String SQL_FILE_PATH = "C:/roleFuncSql.sql";
    private final SysRoleRepository sysRoleRepository;
    private final SysRevampFuncRepository sysRevampFuncRepository;
    public void funcRoleToSql(List<SysRevampRoleFuncDo> sysRevampRoleFuncDoList){
        // 建立SQL檔案寫入器
        try  (BufferedWriter bw = new BufferedWriter(new FileWriter(SQL_FILE_PATH))) {

            // 逐行解析CSV資料並產生INSERT語句
            for(SysRevampRoleFuncDo sysRevampRoleFuncDo: sysRevampRoleFuncDoList) {

                // 產生INSERT語句
                String insertStatement = String.format("INSERT INTO mms.SYS_REVAMP_ROLE_FUNC (ROLE_ID, FUNC_ID, ACTIVE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY) " +
                        "VALUES (%d, %d, %d, NOW(), 'SYSTEM', NOW(), 'SYSTEM');", sysRevampRoleFuncDo.getRoleId(), sysRevampRoleFuncDo.getFuncId(), sysRevampRoleFuncDo.getActive());

                // 寫入SQL檔案
                bw.write(insertStatement);
                bw.newLine();
            }

            System.out.println("CSV檔案已成功轉換為SQL檔案。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SysRevampRoleFuncDo> convertCsvToList(String csvFilePath) {
        List<SysRevampRoleFuncDo> roleFuncList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }

                String[] funcValues = line.split(",\"", -1);
                String funcNameData = funcValues[0];

                int startIndex = line.indexOf("\"") + 1;
                int endIndex = line.lastIndexOf("\"");
                String roleNameData = line.substring(startIndex, endIndex);

                String[] funcNameDataParts = funcNameData.split("\\(");
                String funcCode = funcNameDataParts[1].replaceAll("[()]", "");

//                System.out.println("funcCode: " + funcCode);

                String[] roleNameDataParts = roleNameData.split(", ");

//                for (String s : roleNameDataParts){
//                    System.out.println(s);
//                }

                for (String roleNameDataPart : roleNameDataParts) {
                    String[] roleNameParts = roleNameDataPart.split("\\(");
                    String roleCode = roleNameParts[1].replaceAll("[()]", "");

                    SysRevampRoleFuncDo roleFuncDo = new SysRevampRoleFuncDo();
                    roleFuncDo.setFuncId(sysRevampFuncRepository.findByFuncCode(funcCode).getId());
                    roleFuncDo.setRoleId(sysRoleRepository.findByRoleCode(roleCode).getId());
                    roleFuncDo.setActive(1); // Set active to 1 by default
                    roleFuncDo.setCreatedBy("SYSTEM"); // Set created by to "SYSTEM" by default
                    roleFuncDo.setCreatedDate(LocalDateTime.now());
                    roleFuncDo.setLastUpdatedBy("SYSTEM"); // Set last updated by to "SYSTEM" by default
                    roleFuncDo.setLastUpdatedDate(LocalDateTime.now());

                    System.out.println(roleFuncDo.getFuncId() + "//" + roleFuncDo.getRoleId());

                    roleFuncList.add(roleFuncDo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return roleFuncList;
    }
}
