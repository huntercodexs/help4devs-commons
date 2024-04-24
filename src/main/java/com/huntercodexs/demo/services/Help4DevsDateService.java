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

@Slf4j
@Service
public class Help4DevsDateService {

    public static String reverseDate(String inputDate, String separator) {

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
            System.out.println("MATCH 1: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28, 2023-08-16 16:10:28*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            System.out.println("MATCH 2: " + inputDate);

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
            System.out.println("MATCH 3: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28.333, 2023-08-16 16:10:28.333*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            System.out.println("MATCH 4: " + inputDate);

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
            System.out.println("MATCH 5: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16, 2023-08-16*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}")) {
            System.out.println("MATCH 6: " + inputDate);

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

    public static boolean expiredDate(String date, int time, String metricType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(formatter);
        LocalDateTime dateTimeNowFormatter = LocalDateTime.parse(dateTimeFormat, formatter);
        LocalDateTime dateTimeRef = LocalDateTime.parse(reverseDate(date, "-"), formatter);
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
            log.info("Expired time: (now)" + dateTimeNowFormatter + " - (limit)"+ timeLimit);
            return true;
        }

        return false;
    }

    public static List<Long> quantifyDate(String initialDate, String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        LocalDateTime initialDateRef = LocalDateTime.parse(reverseDate(initialDate, "-"), formatter);
        LocalDateTime finalDateRef = LocalDateTime.parse(reverseDate(finalDate, "-"), formatter);
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

        System.out.println(arrayList);
        System.out.println(
                "RESULT: " +
                arrayList.get(0) + " years, " +
                arrayList.get(1) + " months, " +
                arrayList.get(2) + " days, " +
                arrayList.get(3) + " hours, " +
                arrayList.get(4) + " minutes, " +
                arrayList.get(5) + " seconds, " +
                arrayList.get(6) + " milliseconds"
        );

        return arrayList;

    }

    public static long quantifyMillisDate(long startDate, long endDate) {
        long duration = endDate - startDate;
        System.out.println("StartDate: " + startDate);
        System.out.println("EndDate: " + endDate);
        return duration;
    }

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

        System.out.println("DURATION: " + duration);

        return duration;

    }

    public static String localDateFromGmtDate(String gmtDate, String operation, int time) {

        if (!gmtDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})Z")) {
            return "invalid date format";
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

}
