package com.example.demo2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo2.util.ReadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class Demo2Application implements CommandLineRunner {
//	private final DataReplicationService dataReplicationService;

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
		shutdown();
	}

	@Override
	public void run(String... args){
		String sidebarString = ReadUtils.readJsonFile("C:\\rawdata\\sidebar.json");
		String enString = ReadUtils.readJsonFile("C:\\rawdata\\general-en.json");
		String zhString = ReadUtils.readJsonFile("C:\\rawdata\\general-zh.json");

		JSONArray sidebarArray = JSONArray.parseArray(sidebarString);
		JSONObject enFunc = JSON.parseObject(enString);
		JSONObject zhFunc = JSON.parseObject(zhString);

		Map<String, String> sidebarMap = convertJsonToMap(sidebarArray);
//		for (Map.Entry<String, String> entry : sidebarMap.entrySet()) {
//			System.out.println("Label: " + entry.getKey() + ", FuncName: " + entry.getValue());
//		}
		Map<String, String> enMap = (Map)JSON.parse(enString);
		Map<String, String> zhMap = (Map)JSON.parse(zhString);

		for (Map.Entry<String, String> entry : enMap.entrySet()) {
			System.out.println("Label: " + entry.getKey() + ", FuncName: " + entry.getValue());
		}

		try (PrintWriter writer = new PrintWriter(new File("C:\\output.csv"), StandardCharsets.UTF_8)) {

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
				sb.append(entry.getKey());
				sb.append(',');
				sb.append(enMap.get(entry.getKey()));
				sb.append(',');
				sb.append(zhMap.get(entry.getKey()));
				sb.append(',');
				sb.append(zhMap.get(entry.getKey()));
				sb.append('\n');
			}


			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
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
