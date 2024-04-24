package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class Help4DevsValidatorService {

    public static boolean cpfValidator(String cpf) {

        if (cpf.length() > 11) return false;

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
        } catch(NumberFormatException e){
            return false;
        }

        int d1, d2;
        int digit1, digit2, rest;
        int cpfDigit;
        String nDigResult;

        d1 = d2 = 0;
        digit1 = digit2 = rest = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            cpfDigit = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * cpfDigit;
            d2 = d2 + (12 - nCount) * cpfDigit;
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

        String digitVerify = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        return digitVerify.equals(nDigResult);
    }

    public static boolean mailValidator(String email) {
        boolean isValidMail = false;
        if (email != null && !email.isEmpty()) {
            String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email.trim());
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        return isValidMail;
    }

    public static boolean phoneValidator(String phoneNumber, String country) {
        boolean isValidPhone = false;

        String expression = phoneRegex(country);

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        return isValidPhone;
    }

    public static String phoneRegex(String country) {
        switch (country) {
            case "us":
                return "^[+]?1[0-9]{2}9?[0-9]{8}$";
            case "br":
                return "^[+]?55[0-9]{2}9?[0-9]{8}$";
            default:
                throw new RuntimeException("Invalid Country ID");
        }
    }

    public static boolean cvvValidator(String cvv) {
        return (cvv.matches("^[0-9]{3}$"));
    }

    public static boolean cardNumberValidator(String cardNumber) {
        /*NOTA: Esse validator nao considera a mascara de cartoes das bandeiras: VISA, Mastercard, CIELO,...*/
        return (cardNumber.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"));
    }

}
