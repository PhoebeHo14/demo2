package com.example.demo2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.demo2.utils.IoUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreateFuncSql implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CreateFuncSql.class, args);
        shutdown();
    }

    @Override
    public void run(String... args) {
        String sidebarString = IoUtils.readJsonFile("C:\\rawdata\\sidebar.json");
        String enString = IoUtils.readJsonFile("C:\\rawdata\\general-en.json");
        String zhString = IoUtils.readJsonFile("C:\\rawdata\\general-zh.json");

        JSONArray sidebarArray = JSONArray.parseArray(sidebarString);

        Map<String, String> sidebarMap = convertJsonToMap(sidebarArray);
        Map<String, String> enMap = JSON.parseObject(enString, new TypeReference<>() {
        });
        Map<String, String> zhMap = JSON.parseObject(zhString, new TypeReference<>() {
        });

        try (PrintWriter writer = new PrintWriter("C:\\updateSYS_REVAMP_FUNC.sql", StandardCharsets.UTF_8)) {

            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> entry : sidebarMap.entrySet()) {
                if (enMap != null && zhMap != null) {
                    stringBuilder.append("UPDATE SYS_REVAMP_FUNC SET FUNC_NAME_EN='")
                            .append(enMap.get(entry.getKey()))
                            .append("', FUNC_NAME_CN='")
                            .append(zhMap.get(entry.getKey()))
                            .append("', FUNC_NAME_TW='")
                            .append(zhMap.get(entry.getKey()))
                            .append("', LAST_UPDATED_DATE='")
                            .append(LocalDateTime.now())
                            .append("', LAST_UPDATED_BY='SYSTEM' WHERE FUNC_CODE='")
                            .append(entry.getValue())
                            .append("';")
                            .append('\n');
                }
            }
            System.out.println(stringBuilder);

            writer.write(stringBuilder.toString());
            System.out.println("done!");
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Map<String, String> convertJsonToMap(JSONArray jsonArray) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray subFuncArray = jsonObject.getJSONArray("children");

            for (int j = 0; j < subFuncArray.size(); j++) {
                JSONObject subJsonObject = subFuncArray.getJSONObject(j);
                String subLabel = subJsonObject.getString("label");
                String subFuncName = subJsonObject.getString("funcName");

                JSONArray subSubFuncArray = subJsonObject.getJSONArray("children");

                for (int k = 0; k < subSubFuncArray.size(); k++) {
                    JSONObject subSubJsonObject = subSubFuncArray.getJSONObject(k);
                    String subSubLabel = subSubJsonObject.getString("label");
                    String subSubFuncName = subSubJsonObject.getString("funcName");

                    map.put(subSubLabel, subSubFuncName);
                }

                map.put(subLabel, subFuncName);
            }

            String label = jsonObject.getString("label");
            String funcName = jsonObject.getString("funcName");
            map.put(label, funcName);
        }

        return map;
    }

    private static void shutdown() {
        System.exit(0);
    }
}
