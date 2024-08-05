package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.huntercodexs.demo.services.date.Help4DevsDateService.*;

public class Help4DevsDateUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void dateReverseTest() {
        codexsTesterAssertExact("2023-07-14 14:53:25", dateReverse("14/07/2023 14:53:25", "-"));
        codexsTesterAssertExact("16/08/2023 16:10:28", dateReverse("2023-08-16 16:10:28", "/"));
        codexsTesterAssertExact("2023/07/14 14:53:25", dateReverse("14/07/2023 14:53:25", "/"));
        codexsTesterAssertExact("16-08-2023 16:10:28", dateReverse("2023-08-16 16:10:28", "-"));

        codexsTesterAssertExact("2023-07-14 14:53:25", dateReverse("14/07/2023TZ14:53:25", "-"));
        codexsTesterAssertExact("16/08/2023 16:10:28", dateReverse("2023-08-16TZ16:10:28", "/"));
        codexsTesterAssertExact("2023/07/14 14:53:25", dateReverse("14/07/2023TZ14:53:25", "/"));
        codexsTesterAssertExact("16-08-2023 16:10:28", dateReverse("2023-08-16TZ16:10:28", "-"));
        codexsTesterAssertExact("15-08-2023 18:02:26.737", dateReverse("2023-08-15T18:02:26.737Z", "-"));
        codexsTesterAssertExact("15/08/2023 18:02:26.737", dateReverse("2023-08-15T18:02:26.737Z", "/"));

        codexsTesterAssertExact("2023-07-14", dateReverse("14/07/2023", "-"));
        codexsTesterAssertExact("16/08/2023", dateReverse("2023-08-16", "/"));
        codexsTesterAssertExact("2023/07/14", dateReverse("14/07/2023", "/"));
        codexsTesterAssertExact("16-08-2023", dateReverse("2023-08-16", "-"));
    }

    @Test
    public void dateExpiredTest() {
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "nano"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "second"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "minute"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "hour"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "day"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "week"));
        codexsTesterAssertBool(true, dateExpired("14/07/2023 14:53:25", 1, "month"));
        codexsTesterAssertBool(false, dateExpired("14/07/2023 14:53:25", 1, "year"));
    }

    @Test
    public void dateQuantifyTest() {

        List<Long> arrayList;

        arrayList = dateQuantify("14/07/2023 15:53:25", "14/07/2023 15:53:26");
        codexsTesterAssertExact("[0, 0, 0, 0, 0, 1, 0]", String.valueOf(arrayList));
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

        arrayList = dateQuantify("14/07/2023 15:53:25", "14/07/2023 15:54:26");
        codexsTesterAssertExact("[0, 0, 0, 0, 1, 1, 0]", String.valueOf(arrayList));
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

        arrayList = dateQuantify("14/07/2023 15:53:25", "14/07/2023 16:54:26");
        codexsTesterAssertExact("[0, 0, 0, 1, 1, 1, 0]", String.valueOf(arrayList));
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

        arrayList = dateQuantify("14/07/2023 15:53:25", "15/07/2023 16:54:26");
        codexsTesterAssertExact("[0, 0, 1, 1, 1, 1, 0]", String.valueOf(arrayList));
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

        arrayList = dateQuantify("14/07/2023 15:53:25", "15/08/2023 16:54:26");
        codexsTesterAssertExact("[0, 1, 1, 1, 1, 1, 0]", String.valueOf(arrayList));
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

        arrayList = dateQuantify("14/07/2022 15:53:25", "15/08/2023 16:54:26");
        codexsTesterAssertExact("[1, 1, 1, 1, 1, 1, 0]", String.valueOf(arrayList));
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

        codexsTesterAssertInt(
                60400,
                (int) (int) quantifyMillisParamsDate("2023/08/20 15:30:10.100", "2023/08/20 15:31:10.500"));

        codexsTesterAssertInt(
                60000,
                (int) quantifyMillisParamsDate("2023/08/20 15:30:10", "2023/08/20 15:31:10"));

        codexsTesterAssertInt(
                60000,
                (int) quantifyMillisParamsDate("2023/08/20 15:30", "2023/08/20 15:31"));

        codexsTesterAssertInt(
                3600000,
                (int) quantifyMillisParamsDate("2023/08/20 15", "2023/08/20 16"));

        codexsTesterAssertInt(
                86400000,
                (int) quantifyMillisParamsDate("2023/08/20", "2023/08/21"));

        codexsTesterAssertInt(
                400,
                (int) quantifyMillisParamsDate("2023-08-20 15:30:10.100", "2023-08-20 15:30:10.500"));

        codexsTesterAssertInt(
                60000,
                (int) quantifyMillisParamsDate("2023-08-20 15:30:10", "2023-08-20 15:31:10"));

        codexsTesterAssertInt(
                60000,
                (int) quantifyMillisParamsDate("2023-08-20 15:30", "2023-08-20 15:31"));

        codexsTesterAssertInt(
                3600000,
                (int) quantifyMillisParamsDate("2023-08-20 15", "2023-08-20 16"));

        codexsTesterAssertInt(
                86400000,
                (int) quantifyMillisParamsDate("2023-08-20", "2023-08-21"));
    }

    @Test
    public void localDateFromGmtDateTest() {
        String localDate = localDateFromGmtDate("2023-08-15T02:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertExact("2023-08-14 23:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertExact("2023-08-15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertExact("2023/08/15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertExact("2023-08-15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertExact("2023/08/15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertExact("invalid date format: 2023-08-15 18:02:26", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertExact("invalid date format: 2023-08-15 18:02:26", localDate);
    }

    @Test
    public void militaryTimeConversionTest() {
        codexsTesterAssertExact("12:00:00", militaryHour("12:00:00PM"));
        codexsTesterAssertExact("00:00:00", militaryHour("12:00:00AM"));
        codexsTesterAssertExact("06:00:00", militaryHour("06:00:00AM"));
        codexsTesterAssertExact("18:00:00", militaryHour("06:00:00PM"));
        codexsTesterAssertExact("12:24:00", militaryHour("12:24:00PM"));
        codexsTesterAssertExact("00:45:00", militaryHour("12:45:00AM"));
        codexsTesterAssertExact("06:59:00", militaryHour("06:59:00AM"));
        codexsTesterAssertExact("18:30:00", militaryHour("06:30:00PM"));
    }

    @Test
    public void dateFormatter_UsingHyphen_Test() {

        //DATETIME + HOUR + MINUTE + SECOND + MILLISECOND

        codexsTesterAssertExact(
                "2020-12-01 10:00:00.003",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "2021-09-10 10:00:00.007",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31-08-2022 10:00:00.008",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01-07-2019 10:00:00.009",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "01-04-15 10:00:00.010",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "90-06-23 10:00:00.011",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss.ms", true));

        //DATE + HOUR + MINUTE + SECOND

        codexsTesterAssertExact(
                "2020-12-01 10:00:00",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss", true));
        codexsTesterAssertExact(
                "2021-09-10 10:00:00",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss", true));

        codexsTesterAssertExact(
                "31-08-2022 10:00:00",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss", true));
        codexsTesterAssertExact(
                "01-07-2019 10:00:00",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss", true));

        codexsTesterAssertExact(
                "01-04-15 10:00:00",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss", true));
        codexsTesterAssertExact(
                "90-06-23 10:00:00",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss", true));

        //DATE + HOUR + MINUTE

        codexsTesterAssertExact(
                "2020-12-01 10:00",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm", true));
        codexsTesterAssertExact(
                "2021-09-10 10:00",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm", true));

        codexsTesterAssertExact(
                "31-08-2022 10:00",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm", true));
        codexsTesterAssertExact(
                "01-07-2019 10:00",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm", true));

        codexsTesterAssertExact(
                "01-04-15 10:00",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm", true));
        codexsTesterAssertExact(
                "90-06-23 10:00",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm", true));

        //DATE + HOUR

        codexsTesterAssertExact(
                "2020-12-01 10",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH", true));
        codexsTesterAssertExact(
                "2021-09-10 10",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH", true));

        codexsTesterAssertExact(
                "31-08-2022 10",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH", true));
        codexsTesterAssertExact(
                "01-07-2019 10",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH", true));

        codexsTesterAssertExact(
                "01-04-15 10",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH", true));
        codexsTesterAssertExact(
                "90-06-23 10",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH", true));

        //ONLY DATE

        codexsTesterAssertExact(
                "2020-12-01",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd", true));
        codexsTesterAssertExact(
                "2021-09-10",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd", true));

        codexsTesterAssertExact(
                "31-08-2022",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy", true));
        codexsTesterAssertExact(
                "01-07-2019",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy", true));

        codexsTesterAssertExact(
                "01-04-15",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy", true));
        codexsTesterAssertExact(
                "90-06-23",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd", true));

        //GMT

        codexsTesterAssertExact(
                "2020-12-01T10:00:00.003Z",
                dateFormatter("2020/12/01T10:00:00.003Z", "yyy-MM-dd HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "2021-09-10T10:00:00.007Z",
                dateFormatter("2021/09/10T10:00:00.007Z", "yyyy-MM-dd HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31-08-2022T10:00:00.008Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01-07-2019T10:00:00.009Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31-08-2022T10:00:00.008Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01-07-2019T10:00:00.009Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31-08-2022T10:00:00Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss", true));
        codexsTesterAssertExact(
                "01-07-2019T10:00:00Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss", true));

        codexsTesterAssertExact(
                "01-04-15T10:00:00Z",
                dateFormatter("2015/04/01T10:00:00.010Z", "dd-MM-yy HH:mm:ss", true));
        codexsTesterAssertExact(
                "90-06-23T10:00:00Z",
                dateFormatter("1990/06/23T10:00:00.011Z", "yy-MM-dd HH:mm:ss", true));

        //ONLY NUMBERS

        codexsTesterAssertExact(
                "20201201100000003",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmmssms", true));
        codexsTesterAssertExact(
                "20210910100000007",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmmssms", true));

        codexsTesterAssertExact(
                "31082022100000008",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmmssms", true));
        codexsTesterAssertExact(
                "01072019100000009",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmmssms", true));

        codexsTesterAssertExact(
                "010415100000010",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmmssms", true));
        codexsTesterAssertExact(
                "900623100000011",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmmssms", true));

        codexsTesterAssertExact(
                "20201201100000",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmmss", true));
        codexsTesterAssertExact(
                "20210910100000",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmmss", true));

        codexsTesterAssertExact(
                "31082022100000",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmmss", true));
        codexsTesterAssertExact(
                "01072019100000",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmmss", true));

        codexsTesterAssertExact(
                "010415100000",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmmss", true));
        codexsTesterAssertExact(
                "900623100000",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmmss", true));

        codexsTesterAssertExact(
                "202012011000",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmm", true));
        codexsTesterAssertExact(
                "202109101000",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmm", true));

        codexsTesterAssertExact(
                "310820221000",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmm", true));
        codexsTesterAssertExact(
                "010720191000",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmm", true));

        codexsTesterAssertExact(
                "0104151000",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmm", true));
        codexsTesterAssertExact(
                "9006231000",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmm", true));

        codexsTesterAssertExact(
                "2020120110",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHH", true));
        codexsTesterAssertExact(
                "2021091010",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHH", true));

        codexsTesterAssertExact(
                "3108202210",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHH", true));
        codexsTesterAssertExact(
                "0107201910",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHH", true));

        codexsTesterAssertExact(
                "01041510",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHH", true));
        codexsTesterAssertExact(
                "90062310",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHH", true));

        codexsTesterAssertExact(
                "20201201",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMdd", true));
        codexsTesterAssertExact(
                "20210910",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMdd", true));

        codexsTesterAssertExact(
                "31082022",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyy", true));
        codexsTesterAssertExact(
                "01072019",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyy", true));

        codexsTesterAssertExact(
                "010415",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyy", true));
        codexsTesterAssertExact(
                "900623",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMdd", true));
    }

    @Test
    public void dateFormatter_UsingBar_Test() {

        //DATETIME + HOUR + MINUTE + SECOND + MILLISECOND

        codexsTesterAssertExact(
                "2020/12/01 10:00:00.003",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "2021/09/10 10:00:00.007",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31/08/2022 10:00:00.008",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01/07/2019 10:00:00.009",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "01/04/15 10:00:00.010",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "90/06/23 10:00:00.011",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm:ss.ms", true));

        //DATE + HOUR + MINUTE + SECOND

        codexsTesterAssertExact(
                "2020/12/01 10:00:00",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm:ss", true));
        codexsTesterAssertExact(
                "2021/09/10 10:00:00",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm:ss", true));

        codexsTesterAssertExact(
                "31/08/2022 10:00:00",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm:ss", true));
        codexsTesterAssertExact(
                "01/07/2019 10:00:00",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm:ss", true));

        codexsTesterAssertExact(
                "01/04/15 10:00:00",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm:ss", true));
        codexsTesterAssertExact(
                "90/06/23 10:00:00",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm:ss", true));

        //DATE + HOUR + MINUTE

        codexsTesterAssertExact(
                "2020/12/01 10:00",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm", true));
        codexsTesterAssertExact(
                "2021/09/10 10:00",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm", true));

        codexsTesterAssertExact(
                "31/08/2022 10:00",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm", true));
        codexsTesterAssertExact(
                "01/07/2019 10:00",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm", true));

        codexsTesterAssertExact(
                "01/04/15 10:00",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm", true));
        codexsTesterAssertExact(
                "90/06/23 10:00",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm", true));

        //DATE + HOUR

        codexsTesterAssertExact(
                "2020/12/01 10",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH", true));
        codexsTesterAssertExact(
                "2021/09/10 10",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH", true));

        codexsTesterAssertExact(
                "31/08/2022 10",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH", true));
        codexsTesterAssertExact(
                "01/07/2019 10",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH", true));

        codexsTesterAssertExact(
                "01/04/15 10",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH", true));
        codexsTesterAssertExact(
                "90/06/23 10",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH", true));

        //ONLY DATE

        codexsTesterAssertExact(
                "2020/12/01",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd", true));
        codexsTesterAssertExact(
                "2021/09/10",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd", true));

        codexsTesterAssertExact(
                "31/08/2022",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy", true));
        codexsTesterAssertExact(
                "01/07/2019",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy", true));

        codexsTesterAssertExact(
                "01/04/15",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy", true));
        codexsTesterAssertExact(
                "90/06/23",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd", true));

        //GMT

        codexsTesterAssertExact(
                "2020/12/01T10:00:00.003Z",
                dateFormatter("2020-12-01T10:00:00.003Z", "yyy/MM/dd HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "2021/09/10T10:00:00.007Z",
                dateFormatter("2021-09-10T10:00:00.007Z", "yyyy/MM/dd HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31/08/2022T10:00:00.008Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01/07/2019T10:00:00.009Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31/08/2022T10:00:00.008Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss.ms", true));
        codexsTesterAssertExact(
                "01/07/2019T10:00:00.009Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss.ms", true));

        codexsTesterAssertExact(
                "31/08/2022T10:00:00Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss", true));
        codexsTesterAssertExact(
                "01/07/2019T10:00:00Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss", true));

        codexsTesterAssertExact(
                "01/04/15T10:00:00Z",
                dateFormatter("2015-04-01T10:00:00.010Z", "dd/MM/yy HH:mm:ss", true));
        codexsTesterAssertExact(
                "90/06/23T10:00:00Z",
                dateFormatter("1990-06-23T10:00:00.011Z", "yy/MM/dd HH:mm:ss", true));

        //ONLY NUMBERS

        codexsTesterAssertExact(
                "20201201100000003",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmmssms", true));
        codexsTesterAssertExact(
                "20210910100000007",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmmssms", true));

        codexsTesterAssertExact(
                "31082022100000008",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmmssms", true));
        codexsTesterAssertExact(
                "01072019100000009",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmmssms", true));

        codexsTesterAssertExact(
                "010415100000010",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmmssms", true));
        codexsTesterAssertExact(
                "900623100000011",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmmssms", true));

        codexsTesterAssertExact(
                "20201201100000",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmmss", true));
        codexsTesterAssertExact(
                "20210910100000",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmmss", true));

        codexsTesterAssertExact(
                "31082022100000",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmmss", true));
        codexsTesterAssertExact(
                "01072019100000",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmmss", true));

        codexsTesterAssertExact(
                "010415100000",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmmss", true));
        codexsTesterAssertExact(
                "900623100000",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmmss", true));

        codexsTesterAssertExact(
                "202012011000",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmm", true));
        codexsTesterAssertExact(
                "202109101000",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmm", true));

        codexsTesterAssertExact(
                "310820221000",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmm", true));
        codexsTesterAssertExact(
                "010720191000",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmm", true));

        codexsTesterAssertExact(
                "0104151000",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmm", true));
        codexsTesterAssertExact(
                "9006231000",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmm", true));

        codexsTesterAssertExact(
                "2020120110",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHH", true));
        codexsTesterAssertExact(
                "2021091010",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHH", true));

        codexsTesterAssertExact(
                "3108202210",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHH", true));
        codexsTesterAssertExact(
                "0107201910",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHH", true));

        codexsTesterAssertExact(
                "01041510",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHH", true));
        codexsTesterAssertExact(
                "90062310",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHH", true));

        codexsTesterAssertExact(
                "20201201",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMdd", true));
        codexsTesterAssertExact(
                "20210910",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMdd", true));

        codexsTesterAssertExact(
                "31082022",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyy", true));
        codexsTesterAssertExact(
                "01072019",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyy", true));

        codexsTesterAssertExact(
                "010415",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyy", true));
        codexsTesterAssertExact(
                "900623",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMdd", true));
    }

    @Test
    public void stringToCalendarTest() {
        String date = "1990-01-01 10:00:00";
        Calendar result = stringToCalendar(date);
        codexsTesterAssertExact(result.getTime().toString(), "Mon Jan 01 10:00:00 BRST 1990");
        codexsTesterAssertExact(String.valueOf(result.getTimeInMillis()), "631195200000");
    }

    @Test
    public void stringToDateTest() {
        String date = "1990-01-01";
        Date result = stringToDate(date);
        codexsTesterAssertExact(result.toString(), "Mon Jan 01 00:00:00 BRST 1990");
        codexsTesterAssertExact(String.valueOf(result.getTime()), "631159200000");

        date = "1990/01/01";
        result = stringToDate(date);
        codexsTesterAssertExact(result.toString(), "Mon Jan 01 00:00:00 BRST 1990");
        codexsTesterAssertExact(String.valueOf(result.getTime()), "631159200000");

        date = "1990.01.01";
        result = stringToDate(date);
        codexsTesterAssertExact(result.toString(), "Mon Jan 01 00:00:00 BRST 1990");
        codexsTesterAssertExact(String.valueOf(result.getTime()), "631159200000");
    }

    @Test
    public void stringToLocalDatetimeTest() {
        String date = "2019-mai-29 10:15:30 AM";
        LocalDateTime result = stringToLocalDatetime(date, 4, Locale.FRENCH);
        System.out.println(result);

        date = "2019-01-29 10:15:30 AM";
        result = stringToLocalDatetime(date, 1, Locale.ENGLISH);
        System.out.println(result);

        date = "2019-01-29 10:15:30 AM";
        result = stringToLocalDatetime(date, 1, Locale.UK);
        System.out.println(result);

        Locale BR = new Locale("pt", "BR");

        date = "2019-01-29 10:15:30 AM";
        result = stringToLocalDatetime(date, 1, BR);
        System.out.println(result);
    }

}
