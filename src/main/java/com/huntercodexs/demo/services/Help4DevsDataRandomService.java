package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.huntercodexs.demo.services.Help4DevsCurrencyService.*;
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomNumber</h6>
     *
     * <p style="color: #CDCDCD">Generate a random number with a specified number of digits given in the parameters</p>
     *
     * @param digits (int: Quantities of digits to generate random number)
     * @return int (Random Number)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static int randomNumber(int digits) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(digits));
        }
        String randomNumber = (number + number).substring(0, digits);
        return Integer.parseInt(randomNumber);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomMoney</h6>
     *
     * <p style="color: #CDCDCD">Generate random money with a specified number of digits and the currency format,
     * both given in the parameters</p>
     *
     * @param digits (int: Quantities of digits to generate random money)
     * @param currency (String: Currency Format [real, euro, default:dollar])
     * @return String (Random Money)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomMoney(int digits, String currency) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(digits));
        }

        String value = (number + number).substring(0, digits);

        switch (currency) {
            case "real":
                return currencyFormatReal(value);
            case "euro":
                return currencyFormatEuro(value, false);
            default:
                return currencyFormatDollar(value);
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomCents</h6>
     *
     * <p style="color: #CDCDCD">Generate random cents money based on currency in the parameter</p>
     *
     * @param currency (String: Currency Format [real, euro, default:dollar])
     * @return String (Random Money)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomCents(String currency) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(2));
        }

        String value = (number + number).substring(0, 2);

        switch (currency) {
            case "real":
                return "R$ 0,"+String.format("%02d", Integer.parseInt(value));
            case "euro":
                return "0," +String.format("%02d", Integer.parseInt(value))+ " EUR";
            default:
                return "$ 0."+String.format("%02d", Integer.parseInt(value));
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomCardNumber</h6>
     *
     * <p style="color: #CDCDCD">Generate random card number with a specified separator</p>
     *
     * @param separator (String: Type of separator to card number [-,., ])
     * @return String (Random Card Number)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomId</h6>
     *
     * <p style="color: #CDCDCD">Generate random ID with 6 digits and Prefix if given in the parameters</p>
     *
     * @param prefix (String: Specific Prefix to ID)
     * @return String (Random ID)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomId(String prefix) {
        sleep();
        if (prefix == null) prefix = "";
        return prefix+(randomNumber(6));
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomGuid</h6>
     *
     * <p style="color: #CDCDCD">Generate random GUID</p>
     *
     * @return String (GUID)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomGuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomToken</h6>
     *
     * <p style="color: #CDCDCD">Generate random Token with a size specified in the parameter</p>
     *
     * @param length (int: Size of token)
     * @return String (Random Token with a specified length)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        String alphaNumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            stringBuilder.append(alphaNumeric.charAt(secureRandom.nextInt(alphaNumeric.length())));
        }

        return stringBuilder.toString();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomJwt</h6>
     *
     * <p style="color: #CDCDCD">Generate random JWT to simulate requests using JSON WEB TOKEN</p>
     *
     * @return String (Random JWT)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomHash</h6>
     *
     * <p style="color: #CDCDCD">Generate random Hash (md5)</p>
     *
     * @return String (Random Hash)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomHash() {
        sleep();
        Date date = new Date();
        return md5(String.valueOf(date.getTime()));
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomBinary</h6>
     *
     * <p style="color: #CDCDCD">Generate random data binary to simulate binary operation</p>
     *
     * @return String (Random Data Binary)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomCpf</h6>
     *
     * <p style="color: #CDCDCD">Generate random CPF without valid and correct digits verifier</p>
     *
     * @return String (Random CPF)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomCpf() {
        sleep();
        int cpfNumber = randomNumber(9);
        String[] cpfParts = String.valueOf(cpfNumber).split("(?<=\\G.{" + 3 + "})");

        int cpfDigit;
        int d1 = 0, d2 = 0;
        int digit1, digit2, rest = 0;
        String nDigResult;

        for (int i = 0; i < String.valueOf(cpfNumber).length(); i++) {
            cpfDigit = Integer.parseInt(String.valueOf(String.valueOf(cpfNumber).charAt(i)));
            d1 = d1 + ((11 - (i + 1)) * cpfDigit);
            d2 = d2 + ((12 - (i + 1)) * cpfDigit);
        }

        rest = (d1 % 11);

        if (rest < 2)
            digit1 = 0;
        else
            digit1 = 11 - rest;

        d2 += 2 * digit1;

        rest = (d2 % 11);

        if (rest < 2)
            digit2 = 0;
        else
            digit2 = 11 - rest;

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        return cpfParts[0]+"."+cpfParts[1]+"."+cpfParts[2]+"-"+nDigResult;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomCnpj</h6>
     *
     * <p style="color: #CDCDCD">Generate random CNPJ - Brazil</p>
     *
     * @return String (Random CNPJ)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomCnpj() {
        sleep();
        int cnpjInitialNumber = randomNumber(2);
        int cnpjMiddleNumber = randomNumber(6);
        String[] cnpjParts = String.valueOf(cnpjMiddleNumber).split("(?<=\\G.{" + 3 + "})");
        String cnpjThousandInverted = "0001";
        int cnpjDigitNumber = randomNumber(2);
        return cnpjInitialNumber+"."+cnpjParts[0]+"."+cnpjParts[1]+"/"+cnpjThousandInverted+"-"+cnpjDigitNumber;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomDate</h6>
     *
     * <p style="color: #CDCDCD">Generate random date with the format: uuuu-MM-dd</p>
     *
     * @return String (Random Date)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">randomDateTime</h6>
     *
     * <p style="color: #CDCDCD">Generate random datetime with the format: uuuu-MM-dd HH:mm:ss</p>
     *
     * @return String (Random Datetime)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusMinutes(randomNumber(3)).minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

}
