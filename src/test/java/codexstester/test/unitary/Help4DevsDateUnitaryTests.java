package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.util.Calendar;

import static com.huntercodexs.demo.services.Help4DevsDateService.*;

public class Help4DevsDateUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void reverseDateTest() {
        System.out.println(reverseDate("14/07/2023 14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023 14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "-"));

        System.out.println(reverseDate("14/07/2023TZ14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023TZ14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "/"));

        System.out.println(reverseDate("14/07/2023", "-"));
        System.out.println(reverseDate("2023-08-16", "/"));
        System.out.println(reverseDate("14/07/2023", "/"));
        System.out.println(reverseDate("2023-08-16", "-"));
    }

    @Test
    public void expiredDateTest() {
        boolean time = expiredDate("14/07/2023 14:53:25", 1, "nano");
        System.out.println("RESULT IS [NANO]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "second");
        System.out.println("RESULT IS [SECOND]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "minute");
        System.out.println("RESULT IS [MINUTE]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "hour");
        System.out.println("RESULT IS [HOUR]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "day");
        System.out.println("RESULT IS [DAY]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "week");
        System.out.println("RESULT IS [WEEK]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "month");
        System.out.println("RESULT IS [MONTH]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "year");
        System.out.println("RESULT IS [YEAR]: " + time);
    }

    @Test
    public void quantifyDateTest() {
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:53:26");
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:54:26");
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 16:54:26");
        quantifyDate("14/07/2023 15:53:25", "15/07/2023 16:54:26");
        quantifyDate("14/07/2023 15:53:25", "15/08/2023 16:54:26");
        quantifyDate("14/07/2022 15:53:25", "15/08/2023 16:54:26");
    }

    @Test
    public void quantifyMillisDateTest() {
        long startDate = Calendar.getInstance().getTimeInMillis();
        try {
            Thread.sleep(3200);
            long endDate = Calendar.getInstance().getTimeInMillis();
            long duration = quantifyMillisDate(startDate, endDate);
            System.out.println("Duration: " + duration + " milliseconds");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void quantifyMillisParamsDateTest() {
        quantifyMillisParamsDate("2023/08/20 15:30:10.100", "2023/08/20 15:31:10.500");
        quantifyMillisParamsDate("2023/08/20 15:30:10", "2023/08/20 15:31:10");
        quantifyMillisParamsDate("2023/08/20 15:30", "2023/08/20 15:31");
        quantifyMillisParamsDate("2023/08/20 15", "2023/08/20 16");
        quantifyMillisParamsDate("2023/08/20", "2023/08/21");

        quantifyMillisParamsDate("2023-08-20 15:30:10.100", "2023-08-20 15:30:10.500");
        quantifyMillisParamsDate("2023-08-20 15:30:10", "2023-08-20 15:31:10");
        quantifyMillisParamsDate("2023-08-20 15:30", "2023-08-20 15:31");
        quantifyMillisParamsDate("2023-08-20 15", "2023-08-20 16");
        quantifyMillisParamsDate("2023-08-20", "2023-08-21");
    }

    @Test
    public void convertToLocalDateTest() {
        String localDate = localDateFromGmtDate("2023-08-15T02:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023-08-14 23:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023-08-15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023/08/15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("2023-08-15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("2023/08/15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("invalid date format", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("invalid date format", localDate);
    }

    public static String timeConversion(String s) {

        String result = null;
        int militaryHour = 12;
        String[] matches = s.replaceAll("(PM|AM)$", "").split(":");

        if (s.matches("(.*)PM$")) {

            if (!matches[0].equals("12")) {
                militaryHour = Integer.parseInt(matches[0]) + 12;
            }
            result = militaryHour +":"+ matches[1] +":"+ matches[2];

        } else if (s.matches("(.*)AM")) {

            if (matches[0].equals("12")) {
                matches[0] = "00";
            }
            result = matches[0] +":"+ matches[1] +":"+ matches[2];
        }

        return result;
    }
    @Test
    public void militaryTimeConversionTest() {
        System.out.println("12:00:00PM = " +timeConversion("12:00:00PM"));
        System.out.println("12:00:00AM = " +timeConversion("12:00:00AM"));
        System.out.println("06:00:00AM = " +timeConversion("06:00:00AM"));
        System.out.println("06:00:00PM = " +timeConversion("06:00:00PM"));
        System.out.println("12:24:00PM = " +timeConversion("12:24:00PM"));
        System.out.println("12:45:00AM = " +timeConversion("12:45:00AM"));
        System.out.println("06:59:00AM = " +timeConversion("06:59:00AM"));
        System.out.println("06:30:00PM = " +timeConversion("06:30:00PM"));
    }

}





