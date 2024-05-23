package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCnpj;
import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCpf;
import static com.huntercodexs.demo.services.Help4DevsFormatterService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsFormatterUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void cpfFormatterTest() {
        stdout(cpfFormatter(randomCpf().replaceAll("[^0-9]", "")));
    }

    @Test
    public void cnpjFormatterTest() {
        stdout(cnpjFormatter(randomCnpj().replaceAll("[^0-9]", "")));
    }

    @Test
    public void moneyFormatterTest() {
        stdout(moneyFormatter("1000", "real"));
        stdout(moneyFormatter("1000", "dollar"));
        stdout(moneyFormatter("1000", "euro"));
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

}