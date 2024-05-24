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
     * @param digits (int: Quantities of digits to generate random number)
     * @return int (Random Number)
     * @implNote Generate a random number with a specified number of digits given in the parameters
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
     * @param digits (int: Quantities of digits to generate random money)
     * @param currency (String: Currency Format [real, euro, default:dollar])
     * @return String (Random Money)
     * @implNote Generate random money with a specified number of digits and the currency format,
     * both given in the parameters
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
     * @param separator (String: Type of separator to card number [-,., ])
     * @return String (Random Card Number)
     * @implNote Generate random card number with a specified separator
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
     * @param prefix (String: Specific Prefix to ID)
     * @return String (Random ID)
     * @implNote Generate random ID with 6 digits and Prefix if given in the parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomId(String prefix) {
        sleep();
        if (prefix == null) prefix = "";
        return prefix+(randomNumber(6));
    }

    /**
     * @return String (GUID)
     * @implNote Generate random GUID
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomGuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * @param length (int: Size of token)
     * @return String (Random Token with a specified length)
     * @implNote Generate random Token with a size specified in the parameter
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
     * @return String (Random JWT)
     * @implNote Generate random JWT to simulate requests using JSON WEB TOKEN
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
     * @return String (Random Hash)
     * @implNote Generate random Hash (md5)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomHash() {
        sleep();
        Date date = new Date();
        return md5(String.valueOf(date.getTime()));
    }

    /**
     * @return String (Random Data Binary)
     * @implNote Generate random data binary to simulate binary operation
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
     * @return String (Random CPF)
     * @implNote Generate random CPF without valid and correct digits verifier
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomCpf() {
        sleep();
        int cpfNumber = randomNumber(9);
        String[] cpfParts = String.valueOf(cpfNumber).split("(?<=\\G.{" + 3 + "})");
        int cpfDigit = randomNumber(2);
        return cpfParts[0]+"."+cpfParts[1]+"."+cpfParts[2]+"-"+cpfDigit;
    }

    /**
     * @return String (Random CNPJ)
     * @implNote Generate random CNPJ - Brazil
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
     * @return String (Random Date)
     * @implNote Generate random date with the format: uuuu-MM-dd
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

    /**
     * @return String (Random Datetime)
     * @implNote Generate random datetime with the format: uuuu-MM-dd HH:mm:ss
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String randomDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusMinutes(randomNumber(3)).minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

}
