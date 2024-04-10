package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Slf4j
@Service
public class Help4DevsCurrencyService {

    public static String brCurrency(float value) {
        if (value <= 0) return "";
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }

    public static String brCurrency(double value) {
        if (value <= 0) value = 0.00;
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }

    public static double currencySum(double current, double add) {
        System.out.println(brCurrency(current) +"+"+ brCurrency(add));
        double sum = current + add;
        System.out.println(brCurrency((float) sum));
        return sum;
    }

    public static double currencySumFromString(String current, String add) {
        System.out.println(brCurrency(Double.parseDouble(current)) +"+"+ brCurrency(Double.parseDouble(add)));
        double sum = Double.parseDouble(current) + Double.parseDouble(add);
        System.out.println(brCurrency((float) sum));
        return sum;
    }

    public static String currencyFormatReal(Object value) {
        DecimalFormat formatter = new DecimalFormat("R$ #,##0.00");
        return formatter.format(Integer.parseInt(value.toString().replaceAll("[^0-9]", "")))
                .replaceAll("\\.", "+")
                .replaceAll(",", ".")
                .replaceAll("\\+", ",");
    }

    public static String currencyFormatDollar(Object value) {
        DecimalFormat formatter = new DecimalFormat("$ #,##0.00");
        return formatter.format(Integer.parseInt(value.toString().replaceAll("[^0-9]", "")))
                .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                .replaceAll("\\.", ",")
                .replaceAll("\\+", ".");
    }

    public static String currencyFormatEuro(Object value, boolean postfix) {
        String euroChar = "€"; //1 234,56 €
        String euroAcronym = "EUR"; //1 234,56 EUR
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,##0.00");

        String euroAmount = formatter
                .format(Integer.parseInt(value.toString().replaceAll("[^0-9]", "")))
                .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                .replaceAll(",", " ")
                .replaceAll("\\+", ",");

        if (postfix) {
            return euroAmount +" "+ euroChar;
        }

        return euroAmount +" "+ euroAcronym;
    }

}
