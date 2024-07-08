package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Slf4j
@Service
public class Help4DevsStringHandlerService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">repeat</h6>
     *
     * <p style="color: #CDCDCD">Repeat a string or char one or more times - Java 1.8 or minor</p>
     *
     * @param str (String: Data to repeat)
     * @param len (int: Quantity to repeat a str)
     * @return String (A Data repeated n times according len parameter)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">reverse</h6>
     *
     * <p style="color: #CDCDCD">Reverse a string - Java 1.8 or minor</p>
     *
     * @param str (String: Data to reverse)
     * @return String (Data reversed)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryStringBuilder</h6>
     *
     * <p style="color: #CDCDCD">Create a query string from Data JSON or List structures</p>
     *
     * @param input (Object: Data input to create a query string)
     * @return String (Query string created)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String queryStringBuilder(Object input) {
        String[] splitter = input.toString().split("},");
        StringBuilder queryBuilder = new StringBuilder();

        for (String s : splitter) {
            String tmp = s.replaceAll("[]}{\\[\"']", "")
                    .replaceAll(", ", "&")
                    .replaceAll(",", "&")
                    .replaceAll(":", "=")
                    .replaceAll("= ", "=")+"&";
            queryBuilder.append(tmp);
        }

        return String.valueOf(queryBuilder).replaceAll("&$", "").trim();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">getDataFromQueryString</h6>
     *
     * <p style="color: #CDCDCD">Extract field from a Query String Data Structure</p>
     *
     * @param queryString (String: Query string to extract field)
     * @param field (String: Field to extract from Query String)
     * @return Object (Field found)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Object getDataFromQueryString(String queryString, String field) {

        String str = (queryString.split(field+"=")[1]);

        if (str.contains("&")) {
            str = str.split("&")[0];
        }

        return str;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryStringToJson</h6>
     *
     * <p style="color: #CDCDCD">Convert a Query String into JSON Object</p>
     *
     * @param input (String: Query string)
     * @return JSONObject (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">stringToJson</h6>
     *
     * <p style="color: #CDCDCD">Convert a String into JSON Object</p>
     *
     * @param str (String: Query string)
     * @return JSONObject (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">sanitizeAscii</h6>
     *
     * <p style="color: #CDCDCD">Data Convert and Data Clean remove non ASCII characters</p>
     *
     * @param input (String: String to sanitize)
     * @param letterType (String: Output format type [upper, lower])
     * @return String (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizeAscii(String input, String letterType) {
        if (letterType == null) letterType = "";
        try {
            if (letterType.endsWith("upper")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
            } else if (letterType.endsWith("lower")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            } else {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
        } catch (RuntimeException re) {
            throw new RuntimeException(re.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryExtractor</h6>
     *
     * <p style="color: #CDCDCD">Data extract from initial and final position from any string source</p>
     *
     * @param input (String: Data input to cut)
     * @param begin (int: Initial position to cut)
     * @param end (int: Final position to cut)
     * @return String (Data cut)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String queryExtractor(String input, int begin, int end) {
        if (begin > input.length() || end > input.length()) {
            return input;
        }
        return input.substring(begin, end);
    }

}
