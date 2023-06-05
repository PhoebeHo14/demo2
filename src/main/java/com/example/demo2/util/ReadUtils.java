package com.example.demo2.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadUtils {
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void outPutCsv() {
        try (PrintWriter writer = new PrintWriter(new File("C:\\output.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("FUNC_CODE");
            sb.append(',');
            sb.append("FUNC_NAME_EN");
            sb.append(',');
            sb.append("FUNC_NAME_CN");
            sb.append(',');
            sb.append("FUNC_NAME_TW");
            sb.append('\n');

            sb.append("101");
            sb.append(',');
            sb.append("John Doe");
            sb.append(',');
            sb.append("Las Vegas");
            sb.append(',');
            sb.append("Las Vegas");
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}