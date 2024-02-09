package com.huntercodexs.demojobs.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        String repeat = fill.repeat(lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }

    public static String rgFormatter(String value, String rgUf) {
        if (value == null || value.equals("")) return "";
        if (rgUf == null) rgUf = "";
        if (!rgUf.contains("SSP") && !rgUf.equals("")) return "";

        //SSP CP = SSPSC, SSP/SP = SSPSP
        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (rgUf.equals("SSPSP") || rgUf.equals("SP")) {
            rgUf = "";
        } else {
            //SSPCRJ = RJ, SSPSC = SC
            rgUf = rgUf.replaceAll("SSP", "");
        }

        return "RG"+value.replaceAll("[^0-9]+", "")+rgUf;
    }

}
