package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;

@Slf4j
@Service
public class Help4DevsFormatterService {

    public static String cpfFormatter(String cpf) {
        return cpf
                .replaceAll("[^0-9]", "")
                .replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3-$4");
    }

    public static String cnpjFormatter(String cnpj) {
        return cnpj
                .replaceAll("[^0-9]", "")
                .replaceAll("^(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$", "$1.$2.$3/$4-$5");

    }

    /**
     * @param value (Object: the value or amount to format)
     * @param format (String: the currency target to format [real, dollar, euro])
     * @return String (Money Formatted)
     * @implNote Format the currency value in the specific currency format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String moneyFormatter(Object value, String format) {

        int valueFix = Integer.parseInt(value.toString().replaceAll("[^0-9]", ""));
        DecimalFormat formatter;

        switch (format) {

            case "real":

                formatter = new DecimalFormat("R$ #,##0.00");
                return formatter.format(valueFix)
                        .replaceAll("\\.", "+")
                        .replaceAll(",", ".")
                        .replaceAll("\\+", ",");

            case "dollar":

                formatter = new DecimalFormat("$ #,##0.00");
                return formatter.format(valueFix)
                        .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                        .replaceAll("\\.", ",")
                        .replaceAll("\\+", ".");

            case "euro":

                String euroChar = "€"; //1 234,56 €

                formatter = new DecimalFormat("###,###,###,###,##0.00");

                String euroAmount = formatter
                        .format(valueFix)
                        .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                        .replaceAll(",", " ")
                        .replaceAll("\\+", ",");

                return euroAmount + " " + euroChar;

            default:
                throw new RuntimeException("[ERROR] Wrong Money Format: " + format);
        }
    }

    /**
     * @param dateInput (String: the date to format)
     * @param dateFormat (String: the format to apply in the date [
     *                   <br />yyy-MM-dd HH:mm:ss.ms,
     *                   <br />yyyy-MM-dd HH:mm:ss.ms,
     *                   <br />dd-MM-yyy HH:mm:ss.ms,
     *                   <br />dd-MM-yyyy HH:mm:ss.ms,
     *                   <br />dd-MM-yy HH:mm:ss.ms,
     *                   <br />yy-MM-dd HH:mm:ss.ms,
     *                   <br />yyy-MM-dd HH:mm:ss,
     *                   <br />yyyy-MM-dd HH:mm:ss,
     *                   <br />dd-MM-yyy HH:mm:ss,
     *                   <br />dd-MM-yyyy HH:mm:ss,
     *                   <br />dd-MM-yy HH:mm:ss,
     *                   <br />yy-MM-dd HH:mm:ss,
     *                   <br />yyy-MM-dd HH:mm,
     *                   <br />yyyy-MM-dd HH:mm,
     *                   <br />dd-MM-yyy HH:mm,
     *                   <br />dd-MM-yyyy HH:mm,
     *                   <br />dd-MM-yy HH:mm,
     *                   <br />yy-MM-dd HH:mm,
     *                   <br />yyy-MM-dd HH,
     *                   <br />yyyy-MM-dd HH,
     *                   <br />dd-MM-yyy HH,
     *                   <br />dd-MM-yyyy HH,
     *                   <br />dd-MM-yy HH,
     *                   <br />yy-MM-dd HH,
     *                   <br />yyy-MM-dd,
     *                   <br />yyyy-MM-dd,
     *                   <br />dd-MM-yyy,
     *                   <br />dd-MM-yyyy,
     *                   <br />dd-MM-yy,
     *                   <br />yy-MM-dd,
     *                   <br />yyyMMddHHmmssms,
     *                   <br />yyyyMMddHHmmssms,
     *                   <br />ddMMyyyHHmmssms,
     *                   <br />ddMMyyyyHHmmssms,
     *                   <br />ddMMyyHHmmssms,
     *                   <br />yyMMddHHmmssms,
     *                   <br />yyyMMddHHmmss,
     *                   <br />yyyyMMddHHmmss,
     *                   <br />ddMMyyyHHmmss,
     *                   <br />ddMMyyyyHHmmss,
     *                   <br />ddMMyyHHmmss,
     *                   <br />yyMMddHHmmss,
     *                   <br />yyyMMddHHmm,
     *                   <br />yyyyMMddHHmm,
     *                   <br />ddMMyyyHHmm,
     *                   <br />ddMMyyyyHHmm,
     *                   <br />ddMMyyHHmm,
     *                   <br />yyMMddHHmm,
     *                   <br />yyyMMddHH,
     *                   <br />yyyyMMddHH,
     *                   <br />ddMMyyyHH,
     *                   <br />ddMMyyyyHH,
     *                   <br />ddMMyyHH,
     *                   <br />yyMMddHH,
     *                   <br />yyyMMdd,
     *                   <br />yyyyMMdd,
     *                   <br />ddMMyyy,
     *                   <br />ddMMyyyy,
     *                   <br />ddMMyy,
     *                   <br />yyMMdd,
     *                   <br/>])
     * @return String (Date Formatted)
     * @implNote Format the date in the specific format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String dateFormatter(String dateInput, String dateFormat) {

        String separator = "-";
        String replacement = "";
        boolean reverseDate = false;
        boolean cut2Digits = false;
        boolean gmt = false;
        boolean includeHour = false;
        boolean includeMinutes = false;
        boolean includeSeconds = false;
        boolean includeMilliSeconds = false;
        String dateOutput = dateInput.trim();

        String formatter = dateFormat.trim()
                .replaceAll("[/-]", "([-/])?")
                .replaceAll("[ TZ]", "([ ])?")
                .replaceAll(":", "([:])?")
                .replaceAll("\\.", "([.])?")
                .trim();

        if (dateFormat.contains("/")) {
            separator = "/";
        } else if (dateFormat.contains("-")) {
            separator = "-";
        } else {
            separator = "";
        }

        /*GMT*/
        if (dateOutput.contains("T") && dateOutput.contains("Z")) {
            gmt = true;
            dateOutput = dateOutput.replaceAll("[TZ]", " ").trim();
        }

        if (separator.isEmpty()) {
            dateOutput = dateOutput.replaceAll("[^0-9]", "");
        }

        /*Date*/
        if (dateFormat.startsWith("dd-")) {

            formatter = formatter
                    .replaceAll("dd", "([0-9]{2})")
                    .replaceAll("MM", "([0-9]{2})");

            if (dateFormat.startsWith("dd-MM-yy ") || dateFormat.endsWith("dd-MM-yy")) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{2})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            reverseDate = true;

        } else {

            if (dateFormat.startsWith("yy-")) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{4})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            formatter = formatter
                    .replaceAll("MM", "([0-9]{2})")
                    .replaceAll("dd", "([0-9]{2})");

        }

        /*Default Replacement*/
        replacement = "$1" + separator + "$3" + separator + "$5";

        /*Time*/
        if (dateFormat.contains("HH:mm:ss.ms")) {
            formatter = formatter.replaceAll("ms", "([0-9]{1,3})?");
            replacement = "$1" + separator + "$3" + separator + "$5$6$7$8$9$10$11$12$13";
            includeMilliSeconds = true;

        } else if (dateFormat.contains("HH:mm:ss")) {
            formatter = formatter.replaceAll("ss", "([0-9]{2})?");
            replacement = "$1" + separator + "$3" + separator + "$5$6$7$8$9$10$11";
            includeSeconds = true;

        } else if (dateFormat.contains("HH:mm")) {
            formatter = formatter.replaceAll("mm", "([0-9]{2})?");
            replacement = "$1" + separator + "$3" + separator + "$5$6$7$8";
            includeMinutes = true;

        } else if (dateFormat.contains("HH")) {
            formatter = formatter.replaceAll("HH", "([0-9]{2})?");
            replacement = "$1" + separator + "$3" + separator + "$5$6$7";
            includeHour = true;
        }

        if (reverseDate) {

            String dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
            String hourTmp = dateOutput.split(" ")[1];
            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);
            String year = dateTmp.substring(0, 4);

            if (hourTmp != null &&! hourTmp.isEmpty()) {

                if (includeMilliSeconds) {
                    hourTmp = hourTmp;
                } else if (includeSeconds) {
                    hourTmp = hourTmp.split("\\.")[0];
                } else if (includeMinutes) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                } else if (includeHour) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                } else {
                    hourTmp = "";
                }
            }

            if (cut2Digits) {
                year = year.substring(2, 4);
            }

            dateOutput = day+separator+month+separator+year+" "+hourTmp;

        } else if (cut2Digits) {

            String dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
            String hourTmp = dateOutput.split(" ")[1];
            String year = dateTmp.substring(0, 4).substring(2, 4);
            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);

            if (hourTmp != null &&! hourTmp.isEmpty()) {

                if (includeMilliSeconds) {
                    hourTmp = hourTmp;
                } else if (includeSeconds) {
                    hourTmp = hourTmp.split("\\.")[0];
                } else if (includeMinutes) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                } else if (includeHour) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                } else {
                    hourTmp = "";
                }
            }

            dateOutput = year+separator+month+separator+day+" "+hourTmp;

        } else {

            String dateTmp = dateOutput.split(" ")[0];
            String hourTmp = dateOutput.split(" ")[1];

            if (hourTmp != null &&! hourTmp.isEmpty()) {

                if (includeMilliSeconds) {
                    hourTmp = hourTmp;
                } else if (includeSeconds) {
                    hourTmp = hourTmp.split("\\.")[0];
                } else if (includeMinutes) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                } else if (includeHour) {
                    hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                } else {
                    hourTmp = "";
                }
            }

            dateOutput = dateTmp.replaceAll("[/-]", separator)+" "+hourTmp;
        }

        dateOutput = dateOutput.replaceAll("^" + formatter + "$", replacement).trim();

        if (gmt && (includeSeconds || includeMilliSeconds)) {
            dateOutput = (dateOutput.replaceAll(" ", "T")+"Z").replaceAll("TZ$", "");
        }

        System.out.println(repeat("-", 120));
        System.out.println("REVERSE-DATE: "+reverseDate);
        System.out.println("CUT-2-DIGITS: "+cut2Digits);
        System.out.println("GMT: "+gmt);
        System.out.println("HOUR: "+includeHour);
        System.out.println("MINUTE: "+includeMinutes);
        System.out.println("SECONDS: "+includeSeconds);
        System.out.println("MILLISECONDS: "+includeMilliSeconds);
        System.out.println(dateFormat);
        System.out.println(formatter);
        System.out.println(replacement);
        System.out.println("IN PUT: " + dateInput);
        System.out.println("OUTPUT: " + dateOutput);

        return dateOutput;

    }

}
