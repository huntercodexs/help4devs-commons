package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsStringHandlerService {

    /**
     * @implNote Java 1.8 or minor
     * */
    public static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    public static String queryStringBuilder(Object input) {
        return input.toString().split("},")[0]
                .replaceAll("[]}{\\[\"']", "")
                .replaceAll(", ", "&")
                .replaceAll(",", "&")
                .replaceAll(":", "=")
                .replaceAll("= ", "=");
    }

    public static String getDataFromQueryString(String queryString, String field) {

        String str = (queryString.split(field+"=")[1]);

        if (str.contains("&")) {
            str = str.split("&")[0];
        }

        return str;
    }

    public static JSONObject queryStringToJson(String input) {

        System.out.println("INPUT STRING: " + input);

        String[] splitter = input.split("&");
        JSONObject jsonData = new JSONObject();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
        }

        return jsonData;

    }

    public static JSONObject stringToJson(String str) {

        JSONObject jsonData = new JSONObject();
        String strClean = str.replaceAll("([\"{\\[\\]}'/\\\\]+)", "");

        try {
            String[] splitter = strClean.split(",");

            for (String split : splitter) {
                String[] splitter2 = split.split(":");
                jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
            }
        } catch (Exception e) {
            try {
                String[] splitter = strClean.split(":");
                jsonData.appendField(splitter[0].trim(), splitter[1].trim());
            } catch (Exception er) {
                jsonData.appendField("message", null);
            }
        }

        return jsonData;
    }

}
