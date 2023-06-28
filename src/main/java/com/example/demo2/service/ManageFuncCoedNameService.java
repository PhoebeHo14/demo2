package com.example.demo2.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ManageFuncCoedNameService {
    private static void funcCodeAndNameToCsv() {
        String sidebarString = com.example.demo2.utils.IoUtils.readJsonFile("C:\\rawdata\\sidebar.json");
        String enString = com.example.demo2.utils.IoUtils.readJsonFile("C:\\rawdata\\general-en.json");
        String zhString = com.example.demo2.utils.IoUtils.readJsonFile("C:\\rawdata\\general-zh.json");

        JSONArray sidebarArray = JSONArray.parseArray(sidebarString);

        Map<String, String> sidebarMap = convertJsonToMap(sidebarArray);
//		for (Map.Entry<String, String> entry : sidebarMap.entrySet()) {
//			System.out.println("Label: " + entry.getKey() + ", FuncName: " + entry.getValue());
//		}
        Map<String, String> enMap = JSON.parseObject(enString, new TypeReference<>() {
        });
        Map<String, String> zhMap = JSON.parseObject(zhString, new TypeReference<>() {
        });

//		for (Map.Entry<String, String> entry : zhMap.entrySet()) {
//			System.out.println("Label: " + entry.getKey() + ", FuncName: " + entry.getValue());
//		}

        try (PrintWriter writer = new PrintWriter("C:\\output.csv", StandardCharsets.UTF_8)) {

            StringBuilder sb = new StringBuilder();
            sb.append("FUNC_CODE");
            sb.append(',');
            sb.append("FUNC_NAME_EN");
            sb.append(',');
            sb.append("FUNC_NAME_CN");
            sb.append(',');
            sb.append("FUNC_NAME_TW");
            sb.append('\n');

            for (Map.Entry<String, String> entry : sidebarMap.entrySet()) {
                sb.append(entry.getValue());
                sb.append(',');
                sb.append(enMap != null ? enMap.get(entry.getKey()) : null);
                sb.append(',');
                sb.append(zhMap != null ? zhMap.get(entry.getKey()) : null);
                sb.append(',');
                sb.append(zhMap != null ? zhMap.get(entry.getKey()) : null);
                sb.append('\n');
            }

            writer.write(sb.toString());
            System.out.println("done!");

        } catch (IOException e) {
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
}
