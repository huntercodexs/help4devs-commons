package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.huntercodexs.demo.services.Help4DevsToolsService.md5;

@Slf4j
@Service
public class Help4DevsDataRandomService {

    private static void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomNumber(int digits) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(digits));
        }
        String randomNumber = (number + number).substring(0, digits);
        return Integer.parseInt(randomNumber);
    }

    public static String randomCardNumber(String separator) {
        if (separator == null) separator = " ";
        sleep();
        String part1 = String.valueOf(randomNumber(4));
        sleep();
        String part2 = String.valueOf(randomNumber(4));
        sleep();
        String part3 = String.valueOf(randomNumber(4));
        sleep();
        String part4 = String.valueOf(randomNumber(4));
        return part1+separator+part2+separator+part3+separator+part4;
    }

    public static String randomId(String prefix) {
        sleep();
        if (prefix == null) prefix = "";
        return prefix+(randomNumber(6));
    }

    public static String randomGuid() {
        return UUID.randomUUID().toString();
    }

    public static String randomToken(int len) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(len);
        String alphaNumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < len; i++) {
            stringBuilder.append(alphaNumeric.charAt(secureRandom.nextInt(alphaNumeric.length())));
        }

        return stringBuilder.toString();
    }

    public static String randomJwt() {
        Date date = new Date();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("typ", "JWT");
        jsonObject1.put("alg", "RS256");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("sub", randomGuid());
        jsonObject2.put("nbf", date.getTime());
        jsonObject2.put("type", "RANDOM");
        jsonObject2.put("exp", date.getTime());

        /*TODO: Implement a valid signature*/
        return Base64.getEncoder().encodeToString(jsonObject1.toJSONString().getBytes(StandardCharsets.UTF_8)) +
                "." +
                Base64.getEncoder().encodeToString(jsonObject2.toJSONString().getBytes(StandardCharsets.UTF_8)) +
                "." +
                randomToken(64);
    }

    public static String randomHash() {
        sleep();
        Date date = new Date();
        return md5(String.valueOf(date.getTime()));
    }

    public static String randomBinary(int size) {
        sleep();
        Date date = new Date();
        String digits = md5(String.valueOf(date.getTime())).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+8)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+2)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+6)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+4)).replaceAll("[^01]", "");

        if (digits.length() > size) {
            return digits.substring(0, size);
        }

        return digits;
    }

    public static String randomCpf() {
        sleep();
        int cpfNumber = randomNumber(9);
        String[] cpfParts = String.valueOf(cpfNumber).split("(?<=\\G.{" + 3 + "})");
        int cpfDigit = randomNumber(2);
        return cpfParts[0]+"."+cpfParts[1]+"."+cpfParts[2]+"-"+cpfDigit;
    }

    public static String randomCnpj() {
        sleep();
        int cnpjInitialNumber = randomNumber(2);
        int cnpjMiddleNumber = randomNumber(6);
        String[] cnpjParts = String.valueOf(cnpjMiddleNumber).split("(?<=\\G.{" + 3 + "})");
        String cnpjThousandInverted = "0001";
        int cnpjDigitNumber = randomNumber(2);
        return cnpjInitialNumber+"."+cnpjParts[0]+"."+cnpjParts[1]+"/"+cnpjThousandInverted+"-"+cnpjDigitNumber;
    }

}
