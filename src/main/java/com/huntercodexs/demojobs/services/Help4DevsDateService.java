package com.huntercodexs.demojobs.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
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
            System.out.println("DEU MATCH 1: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28, 2023-08-16 16:10:28*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            System.out.println("DEU MATCH 2: " + inputDate);

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
            System.out.println("DEU MATCH 3: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28.333, 2023-08-16 16:10:28.333*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            System.out.println("DEU MATCH 4: " + inputDate);

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
            System.out.println("DEU MATCH 5: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16, 2023-08-16*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}")) {
            System.out.println("DEU MATCH 6: " + inputDate);

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

    public static boolean expiredDate(String date, int time, String periodType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(formatter);
        LocalDateTime dateTimeNowFormatter = LocalDateTime.parse(dateTimeFormat, formatter);

        LocalDateTime dateTimeSession = LocalDateTime.parse(reverseDate(date, "-"), formatter);

        LocalDateTime timeLimit = switch (periodType) {
            case "nano" -> dateTimeSession.plusNanos(time);
            case "second" -> dateTimeSession.plusSeconds(time);
            case "minute" -> dateTimeSession.plusMinutes(time);
            case "hour" -> dateTimeSession.plusHours(time);
            case "day" -> dateTimeSession.plusDays(time);
            case "week" -> dateTimeSession.plusWeeks(time);
            case "month" -> dateTimeSession.plusMonths(time);
            case "year" -> dateTimeSession.plusYears(time);
            default -> throw new RuntimeException("Invalid period to expiredDate");
        };

        int diffTime = dateTimeNowFormatter.compareTo(timeLimit);

        if (diffTime > 0) {
            log.info("Expired time: (now)" + dateTimeNowFormatter + " - (limit)"+ timeLimit);
            return true;
        }

        return false;
    }

}
