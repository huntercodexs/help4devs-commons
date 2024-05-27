package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

@Slf4j
@Service
public class Help4DevsDateService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">dateReverse</h6>
     *
     * <p style="color: #CDCDCD">Reverse the date informed in the parameters</p>
     *
     * @param inputDate (String: The date to reverse)
     * @param separator (String: The kind of separator for the date)
     * @return String (Date Reversed)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String dateReverse(String inputDate, String separator) {

        if (inputDate == null) return null;

        String[] datetime;
        String date = "";
        String hour = "";
        String day = "";
        String month = "";
        String year = "";
        String format = "en";

        inputDate = inputDate
                .replaceAll("[^0-9/: .-]", " ")
                .replaceAll("[ ]{2,}", " ")
                .trim();

        /*14/07/2023 14:53:25, 14-07-2023 14:53:25*/
        if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            //System.out.println("MATCH 1: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28, 2023-08-16 16:10:28*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            //System.out.println("MATCH 2: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*14/07/2023 14:53:25.333, 14-07-2023 14:53:25.333*/
        else if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            //System.out.println("MATCH 3: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28.333, 2023-08-16 16:10:28.333*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            //System.out.println("MATCH 4: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*16/08/2023, 16-08-2023*/
        else if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4}")) {
            //System.out.println("MATCH 5: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16, 2023-08-16*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}")) {
            //System.out.println("MATCH 6: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*invalid datetime*/
        else {
            return "invalid date: " + inputDate;
        }

        if (!hour.equals("")) {
            if (format.equals("en"))
                return year + separator + month + separator + day + " " + hour;
            else
                return day + separator + month + separator + year + " " + hour;
        } else {
            if (format.equals("en"))
                return year + separator + month + separator + day;
            else
                return day + separator + month + separator + year;
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">dateExpired</h6>
     *
     * <p style="color: #CDCDCD">Check if one specific date is expired based on parameters</p>
     *
     * @param date (String: The date to check expires)
     * @param time (int: The time to apply in the check expires)
     * @param metricType (String: The quantity in time to use in calculate)
     * @return boolean (Date Expired)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean dateExpired(String date, int time, String metricType) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(formatter);
        LocalDateTime dateTimeNowFormatter = LocalDateTime.parse(dateTimeFormat, formatter);
        LocalDateTime dateTimeRef = LocalDateTime.parse(dateReverse(date, "-"), formatter);
        LocalDateTime timeLimit = null;

        switch (metricType) {
            case "nano":
                timeLimit = dateTimeRef.plusNanos(time);
                break;
            case "second":
                timeLimit = dateTimeRef.plusSeconds(time);
                break;
            case "minute":
                timeLimit = dateTimeRef.plusMinutes(time);
                break;
            case "hour":
                timeLimit = dateTimeRef.plusHours(time);
                break;
            case "day":
                timeLimit = dateTimeRef.plusDays(time);
                break;
            case "week":
                timeLimit = dateTimeRef.plusWeeks(time);
                break;
            case "month":
                timeLimit = dateTimeRef.plusMonths(time);
                break;
            case "year":
                timeLimit = dateTimeRef.plusYears(time);
                break;
            default:
                throw new RuntimeException("Invalid period to expiredDate");
        }

        int diffTime = dateTimeNowFormatter.compareTo(timeLimit);

        if (diffTime > 0) {
            System.out.println("Time Expired: [now:"+dateTimeNowFormatter+"] - [limit:"+timeLimit+"]");
            return true;
        }

        return false;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">dateQuantify</h6>
     *
     * <p style="color: #CDCDCD">
     *     Get the date quantity difference between two dates (initial - final). The return values will be
     *      * something like this: [1, 2, 3, 4, 5, 6, 7], where:
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : is a List< Long > with seven (7) index: 0-6
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 1 is a quantity of years
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 2 is a quantity of months
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 3 is a quantity of days
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 4 is a quantity of hours
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 5 is a quantity of minutes
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 6 is a quantity of seconds
     *      * <br />[1, 2, 3, 4, 5, 6, 7] : 7 is a quantity of milliseconds
     *      * <br /> In resume the value can be interpreted like this:
     *      * 1 years, 2 months, 3 days, 4 hours, 5 minutes, 6 seconds, 7 milliseconds
     * </p>
     *
     * @param initialDate (String: Initial Date to calculate)
     * @param finalDate (String: Final Date to calculate)
     * @return List (Date Quantify values - List of Long)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static List<Long> dateQuantify(String initialDate, String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        LocalDateTime initialDateRef = LocalDateTime.parse(dateReverse(initialDate, "-"), formatter);
        LocalDateTime finalDateRef = LocalDateTime.parse(dateReverse(finalDate, "-"), formatter);
        LocalDateTime tmpDateTime = LocalDateTime.from(initialDateRef);

        long years = tmpDateTime.until(finalDateRef, ChronoUnit.YEARS);
        tmpDateTime = tmpDateTime.plusYears(years);

        long months = tmpDateTime.until(finalDateRef, ChronoUnit.MONTHS);
        tmpDateTime = tmpDateTime.plusMonths(months);

        long days = tmpDateTime.until(finalDateRef, ChronoUnit.DAYS);
        tmpDateTime = tmpDateTime.plusDays(days);

        long hours = tmpDateTime.until(finalDateRef, ChronoUnit.HOURS);
        tmpDateTime = tmpDateTime.plusHours(hours);

        long minutes = tmpDateTime.until(finalDateRef, ChronoUnit.MINUTES);
        tmpDateTime = tmpDateTime.plusMinutes(minutes);

        long seconds = tmpDateTime.until(finalDateRef, ChronoUnit.SECONDS);
        tmpDateTime = tmpDateTime.plusSeconds(seconds);

        long milliseconds = tmpDateTime.until(finalDateRef, ChronoUnit.MILLIS);

        List<Long> arrayList = new ArrayList<>();
        arrayList.add(years);
        arrayList.add(months);
        arrayList.add(days);
        arrayList.add(hours);
        arrayList.add(minutes);
        arrayList.add(seconds);
        arrayList.add(milliseconds);

        return arrayList;

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">quantifyMillisDate</h6>
     *
     * <p style="color: #CDCDCD">Get the simple difference between two dates</p>
     *
     * @param startDate (long: Initial Date to quantify)
     * @param endDate (long: Final Date to quantify)
     * @return long (Date Quantify in long value)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static long quantifyMillisDate(long startDate, long endDate) {
        return endDate - startDate;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">quantifyMillisParamsDate</h6>
     *
     * <p style="color: #CDCDCD">Get the difference in milliseconds between two dates given in the parameters</p>
     *
     * @param start (String: Initial Date to quantify)
     * @param end (String: Final Date to quantify)
     * @return long (Date Quantify in long value)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static long quantifyMillisParamsDate(String start, String end) {

        String[] startDate = start
                .replaceAll("[/-]", " ")
                .replaceAll(":", " ")
                .replaceAll("\\.", " ")
                .split(" ");

        String[] endDate = end
                .replaceAll("[/-]", " ")
                .replaceAll(":", " ")
                .replaceAll("\\.", " ")
                .split(" ");

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR, Integer.parseInt(startDate[0]));
        startCalendar.set(Calendar.MONTH, Integer.parseInt(startDate[1]));
        startCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDate[2]));

        try {
            startCalendar.set(Calendar.HOUR, Integer.parseInt(startDate[3]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.HOUR, 0);
        }

        try {
            startCalendar.set(Calendar.MINUTE, Integer.parseInt(startDate[4]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.MINUTE, 0);
        }

        try {
            startCalendar.set(Calendar.SECOND, Integer.parseInt(startDate[5]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.SECOND, 0);
        }

        try {
            startCalendar.set(Calendar.MILLISECOND, Integer.parseInt(startDate[6]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.MILLISECOND, 0);
        }

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR, Integer.parseInt(endDate[0]));
        endCalendar.set(Calendar.MONTH, Integer.parseInt(endDate[1]));
        endCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate[2]));

        try {
            endCalendar.set(Calendar.HOUR, Integer.parseInt(endDate[3]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.HOUR, 0);
        }

        try {
            endCalendar.set(Calendar.MINUTE, Integer.parseInt(endDate[4]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.MINUTE, 0);
        }

        try {
            endCalendar.set(Calendar.SECOND, Integer.parseInt(endDate[5]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.SECOND, 0);
        }

        try {
            endCalendar.set(Calendar.MILLISECOND, Integer.parseInt(endDate[6]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.MILLISECOND, 0);
        }

        long duration = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

        return duration;

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">localDateFromGmtDate</h6>
     *
     * <p style="color: #CDCDCD">Get locale date from a GMT date setting up by parameters</p>
     *
     * @param gmtDate (String: Date in GMT)
     * @param operation (String: Type of operation [+, -])
     * @param time (String: Time to apply in the GMT calculate)
     * @return String (Date Locale)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String localDateFromGmtDate(String gmtDate, String operation, int time) {

        if (!gmtDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})Z")) {
            return "invalid date format: " + gmtDate;
        }

        DateTimeFormatter formatterDash = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        DateTimeFormatter formatterBar  = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        String[] saveMillis = gmtDate.replaceAll("Z", "").split("\\.");

        gmtDate = gmtDate
                .replaceAll("[TZ]", " ")
                .trim()
                .replaceAll("\\.[0-9]+$", "");

        LocalDateTime dateTimeRef;

        try {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterDash);
        } catch (DateTimeParseException re) {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterBar);
            formatterDate = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        }

        if (operation.equals("-")) {
            String dt = dateTimeRef.minusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.minusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else if (operation.equals("+")) {
            String dt = dateTimeRef.plusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.plusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else {
            throw new RuntimeException("Invalid option to localDateFromGmtDate, use: - or +");
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">militaryHour</h6>
     *
     * <p style="color: #CDCDCD">Get the military hour according the inputHour informed in the parameter</p>
     *
     * @param inputHour (String: Date)
     * @return String (Date Military Format)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String militaryHour(String inputHour) {

        String result = null;
        int militaryHour = 12;
        String[] matches = inputHour.replaceAll("(PM|AM)$", "").split(":");

        if (inputHour.matches("(.*)PM$")) {

            if (!matches[0].equals("12")) {
                militaryHour = Integer.parseInt(matches[0]) + 12;
            }
            result = militaryHour +":"+ matches[1] +":"+ matches[2];

        } else if (inputHour.matches("(.*)AM")) {

            if (matches[0].equals("12")) {
                matches[0] = "00";
            }
            result = matches[0] +":"+ matches[1] +":"+ matches[2];
        }

        return result;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">dateFormatter</h6>
     *
     * <p style="color: #CDCDCD">Format the date in the specific format</p>
     *
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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String dateFormatter(String dateInput, String dateFormat, boolean debug) {

        String separator = "-";
        String replacement = "";
        boolean reverseDate = false;
        boolean cut2Digits = false;
        boolean gmt = false;
        boolean onlyNumbers = false;
        boolean includeHour = false;
        boolean includeMinutes = false;
        boolean includeSeconds = false;
        boolean includeMilliSeconds = false;
        String dateOutput = dateInput.trim();

        /*Formatter*/
        String formatter = dateFormat.trim()
                .replaceAll("[/-]", "([-/])?")
                .replaceAll("[ TZ]", "([ ])?")
                .replaceAll(":", "([:])?")
                .replaceAll("\\.", "([.])?")
                .trim();

        /*Separator*/
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

        /*Only Numbers*/
        if (separator.isEmpty()) {
            onlyNumbers = true;
            dateOutput = dateOutput.replaceAll("[^0-9]+", "");
            formatter = dateFormat.trim();
        }

        /*Default Replacement*/
        replacement = "$1" + separator + "$3" + separator + "$5";

        /*Date*/
        if (dateFormat.startsWith("dd")) {

            formatter = formatter
                    .replaceAll("dd", "([0-9]{2})")
                    .replaceAll("MM", "([0-9]{2})");

            if (
                    (dateFormat.startsWith("dd-MM-yy ") || dateFormat.endsWith("dd-MM-yy")) ||
                            (dateFormat.startsWith("dd/MM/yy ") || dateFormat.endsWith("dd/MM/yy")) ||
                            (dateFormat.startsWith("ddMMyy") && dateFormat.endsWith("ddMMyy")) ||
                            (dateFormat.startsWith("ddMMyyHH") || dateFormat.startsWith("yyMMddHH"))
            ) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{2})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            reverseDate = true;

        } else {

            if (dateFormat.startsWith("yy-") || dateFormat.startsWith("yy/") || dateFormat.startsWith("yyMMdd")) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{2})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            formatter = formatter
                    .replaceAll("MM", "([0-9]{2})")
                    .replaceAll("dd", "([0-9]{2})");

        }

        /*Time*/
        if (dateFormat.contains("HH:mm:ss.ms")) {
            formatter = formatter.replaceAll("ms", "([0-9]{1,3})?");
            replacement = replacement + "$6$7$8$9$10$11$12$13";
            includeMilliSeconds = true;

        } else if (dateFormat.contains("HH:mm:ss")) {
            formatter = formatter.replaceAll("ss", "([0-9]{2})?");
            replacement = replacement + "$6$7$8$9$10$11";
            includeSeconds = true;

        } else if (dateFormat.contains("HH:mm")) {
            formatter = formatter.replaceAll("mm", "([0-9]{2})?");
            replacement = replacement + "$6$7$8";
            includeMinutes = true;

        } else if (dateFormat.contains("HHmmssms")) {
            formatter = formatter.replaceAll("ms", "([0-9]{1,3})?");
            replacement = replacement + "$6$7$8$9$10$11";
            includeMilliSeconds = true;

        } else if (dateFormat.contains("HHmmss")) {
            formatter = formatter.replaceAll("ss", "([0-9]{2})?");
            replacement = replacement + "$6$7$8$9";
            includeSeconds = true;

        } else if (dateFormat.contains("HHmm")) {
            formatter = formatter.replaceAll("mm", "([0-9]{2})?");
            replacement = replacement + "$6$7";
            includeMinutes = true;

        } else if (dateFormat.contains("HH")) {
            formatter = formatter.replaceAll("HH", "([0-9]{2})?");
            replacement = replacement + "$6$7";
            includeHour = true;
        }

        /*Output*/
        if (reverseDate) {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
                hourTmp = dateOutput.split(" ")[1];
            }

            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);
            String year = dateTmp.substring(0, 4);

            if (hourTmp != null &&! hourTmp.isEmpty()) {
                if (!onlyNumbers) {
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
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            if (cut2Digits) {
                year = year.substring(2, 4);
            }

            dateOutput = day+separator+month+separator+year+" "+hourTmp;

        } else if (cut2Digits) {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
                hourTmp = dateOutput.split(" ")[1];
            }

            String year = dateTmp.substring(0, 4).substring(2, 4);
            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);

            if (hourTmp != null &&! hourTmp.isEmpty()) {
                if (!onlyNumbers) {
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
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            dateOutput = year+separator+month+separator+day+" "+hourTmp;

        } else {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0];
                hourTmp = dateOutput.split(" ")[1];
            }

            if (hourTmp != null && !hourTmp.isEmpty()) {
                if (!onlyNumbers) {
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
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            dateOutput = dateTmp.replaceAll("[/-]", separator)+" "+hourTmp;

        }

        dateOutput = dateOutput.replaceAll("^" + formatter + "$", replacement).trim();

        if (gmt && (includeSeconds || includeMilliSeconds)) {
            dateOutput = (dateOutput.replaceAll(" ", "T")+"Z").replaceAll("TZ$", "");
        }

        if (onlyNumbers) {
            dateOutput = dateOutput.replaceAll("[^0-9]", "");
        }

        /*Debug*/
        if (debug) {
            stdout(repeat("-", 120));
            stdout("REVERSE-DATE: " + reverseDate + ", CUT-2-DIGITS: " + cut2Digits + ", GMT: " + gmt + ", ONLY-NUMBERS: " + onlyNumbers);
            stdout("HOUR: " + includeHour + ", MINUTE: " + includeMinutes + ", SECONDS: " + includeSeconds + ", MILLISECONDS: " + includeMilliSeconds);
            stdout(dateFormat);
            stdout(formatter);
            stdout(replacement);
            stdout("IN PUT: " + dateInput + ", OUTPUT: " + dateOutput);
        }

        return dateOutput;

    }

}
