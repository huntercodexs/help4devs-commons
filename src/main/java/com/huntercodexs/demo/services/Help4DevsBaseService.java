package com.huntercodexs.demo.services;

import com.huntercodexs.demo.enumerator.UfTable;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;

@Slf4j
@Service
public class Help4DevsBaseService {

    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }

    public static String numberFormatter(int input, String format) {
        return String.format(format, input);
    }

    public static String stringFormatter(String input, String format) {
        return String.format(format, input);
    }

    public static String fillerFormatter(String input, String fill, String align, int size) {

        if (!align.equals("left") && !align.equals("right")) {
            System.out.println("Error: use left or right to param [align]");
            return null;
        }

        if (size < 0) {
            System.out.println("Error: use size > 0");
            return null;
        }

        String formatted = input;

        int lenValue = input.length();
        int lenFill = size - lenValue;
        String repeat = repeat(fill, lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }

    public static String rgFormatter(String value, String rgUf, boolean rgPrefix) {
        if (value == null || value.isEmpty()) return "";
        if (rgUf == null) rgUf = "";
        if (rgUf.isEmpty()) rgUf = "";
        if (rgUf.length() == 1) rgUf = "";

        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (!UfTable.checkUfExists(rgUf) && !UfTable.checkRgSspExists(rgUf)) {
            rgUf = "";
        }

        rgUf = rgUf.replaceAll("SSP", "").replaceAll("SP", "");

        if (value.equals("0")) return "";

        if (rgPrefix) {
            return "RG" + value.replaceAll("[^0-9]+", "") + rgUf;
        }

        return value.replaceAll("[^0-9]+", "");
    }

}
