package codexstester.test.unitary;

import codexstester.engine.util.CodexsHelperTests;
import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.enumerator.DataMasked;
import com.huntercodexs.demo.enumerator.UfTable;
import lombok.*;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static com.huntercodexs.demo.enumerator.DataMasked.dataMasked;
import static com.huntercodexs.demo.services.Help4DevsBaseService.*;
import static com.huntercodexs.demo.services.Help4DevsChallengeService.isPangram;
import static com.huntercodexs.demo.services.Help4DevsCurrencyService.*;
import static com.huntercodexs.demo.services.Help4DevsDateService.*;
import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.*;
import static com.huntercodexs.demo.services.Help4DevsFileReaderService.getFileContentByMatch;
import static com.huntercodexs.demo.services.Help4DevsHttpService.httpResponseErrorExtractor;
import static com.huntercodexs.demo.services.Help4DevsHttpService.restResponseSimulate;
import static com.huntercodexs.demo.services.Help4DevsMaskedService.cardNumberMasked;
import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizeAscii;
import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;
import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.*;
import static com.huntercodexs.demo.services.Help4DevsValidatorService.*;
import static java.lang.Math.ceil;

public class Help4DevsUnitaryTests extends Help4DevsBridgeTests {

    /**
     * File Path Tests
     */

    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        System.out.println("RESULT IS: " + result);

        result = sanitizePath("/home/user/test");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void sanitizeAsciiTest() {
        String result = sanitizeAscii("Teste com acentuação é inevital !", "upper");
        System.out.println("RESULT IS: " + result);

        result = sanitizeAscii("Teste com acentuação é inevital !", "lower");
        System.out.println("RESULT IS: " + result);

        result = sanitizeAscii("Teste com acentuação é inevital !", null);
        System.out.println("RESULT IS: " + result);
    }

    /**
     * File Reader Tests
     */

    @Test
    public void getFileContentTest() throws Exception {
        /*TIP: Edit the file ./src/test/resources/help4devs/file.txt and press [Ctrl+S] button*/
        String code = getFileContentByMatch("./src/test/resources/help4devs/file.txt", "[0-9]{6}",1500000000);
        System.out.println("Content: " + code);
    }

    /**
     * File Handler Tests
     */

    @Test
    public void loadPropsTest() {
        Properties props = loadProps("classpath:application.properties");
        System.out.println(props);
    }

    @Test
    public void bytesExtractorShipmentFileTest() throws IOException {
        InputStream result = bytesFileExtractor("./src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToByteArrayTest() throws IOException {
        InputStream result = fileToByteArray("./src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToDataSourceTest() throws IOException {
        ByteArrayDataSource result = fileToDataSource("./src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringTest() throws IOException {
        String result = fileToString("./src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringConfTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.conf");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringCsvTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.csv");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringDocTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.doc");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringJpgTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.jpg");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringPngTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.png");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringPdfTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.pdf");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringTxtTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.txt");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToStringXlsTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.xls");
        System.out.println("RESULT");
        System.out.println(result);
    }

    @Test
    public void fileToArrayTest() throws IOException {
        ArrayList<String> result = fileToArray("./src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    /**
     * Base Tests
     */

    @Test
    public void paramsTests() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }

    @Test
    public void numberFormatterTest() {
        String result = numberFormatter(1, "%09d");
        System.out.println(result);
    }

    @Test
    public void stringFormatterTest() {
        String result1 = stringFormatter("XXX", "%10s");
        System.out.println("["+result1+"]");

        String result2 = stringFormatter("XXX", "%-10s");
        System.out.println("["+result2+"]");
    }

    @Test
    public void fillerFormatterTest() {
        String result1 = fillerFormatter("XXX", "F", "left", 20);
        System.out.println("["+result1+"]");

        String result2 = fillerFormatter("XXX", "F", "right", 20);
        System.out.println("["+result2+"]");

        String result3 = fillerFormatter("ZZZ", "8", "left", 20);
        System.out.println("["+result3+"]");

        String result4 = fillerFormatter("ZZZ", "8", "right", 20);
        System.out.println("["+result4+"]");

        String result5 = fillerFormatter("YYY", "A", "left", -20);
        System.out.println("["+result5+"]");

        String result6 = fillerFormatter("YYY", "A", "right", -20);
        System.out.println("["+result6+"]");
    }

    @Test
    public void rgFormatterTest() {
        System.out.println("RJ    > ["+ rgFormatter("231048415", "RJ", true)+"]");
        System.out.println("MG    > ["+ rgFormatter("2310484159", "MG", true)+"]");
        System.out.println("SP    > ["+ rgFormatter("2310484150", "SP", true)+"]");
        System.out.println("SSPSP > ["+ rgFormatter("2310484150", "SSPSP", true)+"]");
        System.out.println("SSPTO > ["+ rgFormatter("2310484150", "SSPTO", true)+"]");
        System.out.println("SSPSC > ["+ rgFormatter("2310484150", "SSPSC", true)+"]");
        System.out.println("CNH   > ["+ rgFormatter("2310484150", "CNH", true)+"]");
        System.out.println("DOC   > ["+ rgFormatter("2310484150", "DOC", true)+"]");

        System.out.println("RJ    > ["+ rgFormatter("231048415", "RJ", false)+"]");
        System.out.println("MG    > ["+ rgFormatter("2310484159", "MG", false)+"]");
        System.out.println("SP    > ["+ rgFormatter("2310484150", "SP", false)+"]");
        System.out.println("SSPSP > ["+ rgFormatter("2310484150", "SSPSP", false)+"]");
        System.out.println("SSPTO > ["+ rgFormatter("2310484150", "SSPTO", false)+"]");
        System.out.println("SSPSC > ["+ rgFormatter("2310484150", "SSPSC", false)+"]");
        System.out.println("CNH   > ["+ rgFormatter("2310484150", "CNH", false)+"]");
        System.out.println("DOC   > ["+ rgFormatter("2310484150", "DOC", false)+"]");
    }

    /**
     * Currency Tests
     */

    @Test
    public void brCurrencyFloatTest() {
        System.out.println(brCurrency(Float.parseFloat("999111111111.00")));
    }

    @Test
    public void brCurrencyDoubleTest() {
        System.out.println(brCurrency(999111111111.00));
    }

    @Test
    public void currencySumTest() {

        double result = currencySum(0.00, 0.01);
        result += currencySum(0.01, 0.10);
        result += currencySum(0.10, 0.11);
        result += currencySum(0.11, 1.11);
        result += currencySum(1.00, 1.01);
        result += currencySum(1.00, 1.10);
        result += currencySum(11.00, 111.10);
        result += currencySum(1111.00, 11.10);
        result += currencySum(111111.00, 111.10);
        result += currencySum(111.00, 11.01);
        result += currencySum(111111111.00, 11.01);
        result += currencySum(999111111111.00, 11.01);

        System.out.println("Total");
        System.out.println(brCurrency(result));

        /*Proof*/
        double proff = currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void currencySumFromStringTest() {

        /*Proof*/
        double proff = currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void doubleTest() {
        System.out.println(Double.parseDouble("123.45001"));
        System.out.println(Double.parseDouble("123.45001d"));
        System.out.println(Double.parseDouble("123.45000"));
        System.out.println(Double.parseDouble("123.45001D"));

        System.out.println(Double.valueOf("123.45d"));
        System.out.println(Double.valueOf("123.4500d"));
        System.out.println(Double.valueOf("123.45D"));

        System.out.println(Double.valueOf("12345"));
    }

    @Test
    public void decimalFormatTest () throws ParseException {
        String str1 = "1.000.000.000,00";
        double resulted1 = DecimalFormat.getNumberInstance().parse(str1).doubleValue();
        System.out.println(brCurrency(resulted1));

        String str2 = "1,000,000,000.00";
        double resulted2 = DecimalFormat.getNumberInstance().parse(str2
                .replaceAll(",", "")
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted2));

        String str3 = "1000000000,00";
        double resulted3 = DecimalFormat.getNumberInstance().parse(str3).doubleValue();
        System.out.println(brCurrency(resulted3));

        String str4 = "1000000000.00";
        double resulted4 = DecimalFormat.getNumberInstance().parse(str4
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted4));
    }

    @Test
    public void currencyFormatRealTest() {
        codexsTesterAssertText("R$ 1,00", currencyFormatReal(1));
        codexsTesterAssertText("R$ 10,00", currencyFormatReal(10));
        codexsTesterAssertText("R$ 100,00", currencyFormatReal(100));
        codexsTesterAssertText("R$ 1.000,00", currencyFormatReal(1000));
        codexsTesterAssertText("R$ 10.000,00", currencyFormatReal(10000));
        codexsTesterAssertText("R$ 100.000,00", currencyFormatReal(100000));
        codexsTesterAssertText("R$ 1.000.000,00", currencyFormatReal(1000000));
        codexsTesterAssertText("R$ 10.000.000,00", currencyFormatReal(10000000));
        codexsTesterAssertText("R$ 100.000.000,00", currencyFormatReal(100000000));
        codexsTesterAssertText("R$ 1.000.000.000,00", currencyFormatReal(1000000000));

        codexsTesterAssertText("R$ 1,00", currencyFormatReal("1"));
        codexsTesterAssertText("R$ 10,00", currencyFormatReal("10"));
        codexsTesterAssertText("R$ 100,00", currencyFormatReal("100"));
        codexsTesterAssertText("R$ 1.000,00", currencyFormatReal("1000"));
        codexsTesterAssertText("R$ 10.000,00", currencyFormatReal("10000"));
        codexsTesterAssertText("R$ 100.000,00", currencyFormatReal("100000"));
        codexsTesterAssertText("R$ 1.000.000,00", currencyFormatReal("1000000"));
        codexsTesterAssertText("R$ 10.000.000,00", currencyFormatReal("10000000"));
        codexsTesterAssertText("R$ 100.000.000,00", currencyFormatReal("100000000"));
        codexsTesterAssertText("R$ 1.000.000.000,00", currencyFormatReal("1000000000"));
    }

    @Test
    public void currencyFormatDollarTest() {
        codexsTesterAssertText("$ 1.00", currencyFormatDollar(1));
        codexsTesterAssertText("$ 10.00", currencyFormatDollar(10));
        codexsTesterAssertText("$ 100.00", currencyFormatDollar(100));
        codexsTesterAssertText("$ 1,000.00", currencyFormatDollar(1000));
        codexsTesterAssertText("$ 10,000.00", currencyFormatDollar(10000));
        codexsTesterAssertText("$ 100,000.00", currencyFormatDollar(100000));
        codexsTesterAssertText("$ 1,000,000.00", currencyFormatDollar(1000000));
        codexsTesterAssertText("$ 10,000,000.00", currencyFormatDollar(10000000));
        codexsTesterAssertText("$ 100,000,000.00", currencyFormatDollar(100000000));
        codexsTesterAssertText("$ 1,000,000,000.00", currencyFormatDollar(1000000000));

        codexsTesterAssertText("$ 1.00", currencyFormatDollar("1"));
        codexsTesterAssertText("$ 10.00", currencyFormatDollar("10"));
        codexsTesterAssertText("$ 100.00", currencyFormatDollar("100"));
        codexsTesterAssertText("$ 1,000.00", currencyFormatDollar("1000"));
        codexsTesterAssertText("$ 10,000.00", currencyFormatDollar("10000"));
        codexsTesterAssertText("$ 100,000.00", currencyFormatDollar("100000"));
        codexsTesterAssertText("$ 1,000,000.00", currencyFormatDollar("1000000"));
        codexsTesterAssertText("$ 10,000,000.00", currencyFormatDollar("10000000"));
        codexsTesterAssertText("$ 100,000,000.00", currencyFormatDollar("100000000"));
        codexsTesterAssertText("$ 1,000,000,000.00", currencyFormatDollar("1000000000"));
    }

    @Test
    public void currencyFormatEuroTest() {
        codexsTesterAssertText("1,00 EUR", currencyFormatEuro(1, false));
        codexsTesterAssertText("10,00 EUR", currencyFormatEuro(10, false));
        codexsTesterAssertText("100,00 EUR", currencyFormatEuro(100, false));
        codexsTesterAssertText("1 000,00 EUR", currencyFormatEuro(1000, false));
        codexsTesterAssertText("10 000,00 EUR", currencyFormatEuro(10000, false));
        codexsTesterAssertText("100 000,00 EUR", currencyFormatEuro(100000, false));
        codexsTesterAssertText("1 000 000,00 EUR", currencyFormatEuro(1000000, false));
        codexsTesterAssertText("10 000 000,00 EUR", currencyFormatEuro(10000000, false));
        codexsTesterAssertText("100 000 000,00 EUR", currencyFormatEuro(100000000, false));
        codexsTesterAssertText("1 000 000 000,00 EUR", currencyFormatEuro(1000000000, false));

        codexsTesterAssertText("1,00 EUR", currencyFormatEuro("1", false));
        codexsTesterAssertText("10,00 EUR", currencyFormatEuro("10", false));
        codexsTesterAssertText("100,00 EUR", currencyFormatEuro("100", false));
        codexsTesterAssertText("1 000,00 EUR", currencyFormatEuro("1000", false));
        codexsTesterAssertText("10 000,00 EUR", currencyFormatEuro("10000", false));
        codexsTesterAssertText("100 000,00 EUR", currencyFormatEuro("100000", false));
        codexsTesterAssertText("1 000 000,00 EUR", currencyFormatEuro("1000000", false));
        codexsTesterAssertText("10 000 000,00 EUR", currencyFormatEuro("10000000", false));
        codexsTesterAssertText("100 000 000,00 EUR", currencyFormatEuro("100000000", false));
        codexsTesterAssertText("1 000 000 000,00 EUR", currencyFormatEuro("1000000000", false));

        codexsTesterAssertText("1,00 €", currencyFormatEuro(1, true));
        codexsTesterAssertText("10,00 €", currencyFormatEuro(10, true));
        codexsTesterAssertText("100,00 €", currencyFormatEuro(100, true));
        codexsTesterAssertText("1 000,00 €", currencyFormatEuro(1000, true));
        codexsTesterAssertText("10 000,00 €", currencyFormatEuro(10000, true));
        codexsTesterAssertText("100 000,00 €", currencyFormatEuro(100000, true));
        codexsTesterAssertText("1 000 000,00 €", currencyFormatEuro(1000000, true));
        codexsTesterAssertText("10 000 000,00 €", currencyFormatEuro(10000000, true));
        codexsTesterAssertText("100 000 000,00 €", currencyFormatEuro(100000000, true));
        codexsTesterAssertText("1 000 000 000,00 €", currencyFormatEuro(1000000000, true));

        codexsTesterAssertText("1,00 €", currencyFormatEuro("1", true));
        codexsTesterAssertText("10,00 €", currencyFormatEuro("10", true));
        codexsTesterAssertText("100,00 €", currencyFormatEuro("100", true));
        codexsTesterAssertText("1 000,00 €", currencyFormatEuro("1000", true));
        codexsTesterAssertText("10 000,00 €", currencyFormatEuro("10000", true));
        codexsTesterAssertText("100 000,00 €", currencyFormatEuro("100000", true));
        codexsTesterAssertText("1 000 000,00 €", currencyFormatEuro("1000000", true));
        codexsTesterAssertText("10 000 000,00 €", currencyFormatEuro("10000000", true));
        codexsTesterAssertText("100 000 000,00 €", currencyFormatEuro("100000000", true));
        codexsTesterAssertText("1 000 000 000,00 €", currencyFormatEuro("1000000000", true));
    }

    /**
     * Date Tests
     */

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

    /**
     * Address Tests
     */

    @Test
    public void checkUfExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfExists("SP"));
        codexsTesterAssertBool(false, UfTable.checkUfExists("NN"));
    }

    @Test
    public void checkUfCodeExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfCodeExists("35"));
        codexsTesterAssertBool(false, UfTable.checkUfCodeExists("99"));
    }

    @Test
    public void checkUfNameExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfNameExists("São Paulo"));
        codexsTesterAssertBool(false, UfTable.checkUfNameExists("Sao Test"));
    }

    @Test
    public void checkRgSspExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkRgSspExists("SSPSP"));
        codexsTesterAssertBool(false, UfTable.checkRgSspExists("SSPLL"));
    }

    /**
     * Validator Tests
     */

    @Test
    public void mailValidatorTest() throws Exception {
        boolean result = mailValidator("marcos_portela@yahoo.com.br");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        result = mailValidator("johnsmith23@email.com");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);
    }

    @Test
    public void cpfValidatorTest() {
        boolean result = cpfValidator("07365238801");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        boolean result2 = cpfValidator("07365238899");
        System.out.println("RESULT IS: " + result2);
        codexsTesterAssertBool(result2, false);
    }

    @Test
    public void phoneValidatorTest() {
        boolean result = phoneValidator("5511982772389", "br");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        boolean result2 = phoneValidator("5511982772", "br");
        System.out.println("RESULT IS: " + result2);
        codexsTesterAssertBool(result2, false);

        boolean result3 = phoneValidator("551187722212", "br");
        System.out.println("RESULT IS: " + result3);
        codexsTesterAssertBool(result3, true);
    }

    @Test
    public void cvvValidatorTest() {
        codexsTesterAssertBool(false, cvvValidator("1111"));
        codexsTesterAssertBool(false, cvvValidator("a99"));
        codexsTesterAssertBool(false, cvvValidator("aaa"));
        codexsTesterAssertBool(true, cvvValidator("999"));
        codexsTesterAssertBool(true, cvvValidator("123"));
        codexsTesterAssertBool(true, cvvValidator("765"));
    }

    @Test
    public void cardNumberValidatorTest() {
        codexsTesterAssertBool(false, cardNumberValidator("xxxx-eeee-1111-zzzz"));
        codexsTesterAssertBool(false, cardNumberValidator("11e1-1111-1111-1e11"));
        codexsTesterAssertBool(true, cardNumberValidator("1111-1111-1111-1111"));
        codexsTesterAssertBool(true, cardNumberValidator("4544-8909-7865-6768"));
    }

    /**
     * Masked Tests
     */

    @Test
    public void cardNumberMaskedTest() {
        String mask1 = cardNumberMasked("1234-3456-8982-8908", "*");
        CodexsHelperTests.codexsHelperLogTerm("MASK1", mask1, true);
        codexsTesterAssertText("1234-****-****-8908", mask1);

        String mask2 = cardNumberMasked("1234345689828908", "X");
        CodexsHelperTests.codexsHelperLogTerm("MASK2", mask2, true);
        codexsTesterAssertText("1234XXXXXXXX8908", mask2);

        String mask3 = cardNumberMasked("1234 3456 8982 8908", "@");
        CodexsHelperTests.codexsHelperLogTerm("MASK3", mask3, true);
        codexsTesterAssertText("1234 @@@@ @@@@ 8908", mask3);
    }

    @Test
    public void dataMaskedTest() {
        String cardMasked = dataMasked("1234-3456-8982-8908", "*", DataMasked.CARD_NUMBER_MASK);
        codexsTesterAssertText("1234-****-****-8908", cardMasked);

        String cpfMasked = dataMasked("897.654.058-23", "*", DataMasked.CPF_NUMBER_MASK);
        codexsTesterAssertText("***.***.058-23", cpfMasked);
    }

    /**
     * String Tests
     */

    @Test
    public void queryStringBuilderTest() {
        String result = queryStringBuilder("[{age: 40, gender: female},{age: 30, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("[{age: 40, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{age: 40, gender: female}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{'age': '40', 'gender': 'female'}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void getDataFromQueryStringTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        String result = getDataFromQueryString(queryString, "age");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void queryStringToJsonTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        JSONObject result = queryStringToJson(queryString);
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void stringToJsonTest() {
        JSONObject result = stringToJson("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }

    /**
     * Tools Tests
     */

    @Test
    public void md5Test() {
        String result = md5("data-to-md5");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void guideTest() {
        String result = guide(null);
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void infoLogTest() {
        infoLog("This is a infoLog", "This is a infoLog");
    }

    @Test
    public void errLogTest() {
        errLog("This is a errLog", "This is a errLog");
    }

    @Test
    public void debugLogTest() {
        debugLog("This is a debugLog", "This is a debugLog");
    }

    @Test
    public void stdoutLogTest() {
        stdout("This is a stdout", "This is a stdout");
    }

    /**
     * Challenge Tests
     */

    @Test
    public void pangramsTest() {

        String pangram = ("We promptly judged antique ivory buckles for the next prize".toLowerCase());
        String notPangram = ("The promptly judged antique ivory buckles for the next prize").toLowerCase();

        codexsTesterAssertBool(true, isPangram(pangram, "en"));
        codexsTesterAssertBool(false, isPangram(notPangram, "en"));

    }

    /**
     * Http Tests
     */

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestResponseSimulateDto {
        String code;
        String message;
    }

    @Test
    public void restResponseSimulateTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        HttpClientErrorException result = restResponseSimulate(404, false, restResponseSimulateDto);

        codexsTesterAssertText(String.valueOf(result.getStatusCode()), "404 NOT_FOUND");
        codexsTesterAssertText(result.getStatusText(), "404 Not Found");
        codexsTesterAssertText(String.valueOf(result.getRawStatusCode()), "404");
        codexsTesterAssertText(result.getResponseBodyAsString(), "Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)");
    }

    @Test
    public void restResponseSimulateThrowTest() {

        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        try {

            HttpClientErrorException result = restResponseSimulate(404, true, restResponseSimulateDto);
            System.out.println(result.getResponseBodyAsString());

        } catch (HttpClientErrorException | HttpServerErrorException he) {
            codexsTesterAssertText(String.valueOf(he.getStatusCode()), "404 NOT_FOUND");
            codexsTesterAssertText(he.getStatusText(), "404 Not Found");
            codexsTesterAssertText(String.valueOf(he.getRawStatusCode()), "404");
            codexsTesterAssertText(he.getResponseBodyAsString(), "Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)");
        }
    }

    @Test
    public void httpResponseErrorExtractorTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        HttpClientErrorException response = restResponseSimulate(404, false, restResponseSimulateDto);
        String extract = httpResponseErrorExtractor(response);
        codexsTesterAssertText(extract, "Body{Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)} StatusText{404 Not Found} StatusCode{404 NOT_FOUND} Headers{[]}");
    }

    /**
     * Math Tests
     */

    @Test
    public void ceilTest() {

        int splitter1 = (int) ceil((double) 5500 / 500);
        System.out.println(splitter1);

        int splitter2 = (int) ceil((double) 500 / 500);
        System.out.println(splitter2);

        int splitter3 = (int) ceil((double) 1200 / 500);
        System.out.println(splitter3);

        int splitter4 = (int) ceil((double) 800 / 500);
        System.out.println(splitter4);

    }

    /**
     * Generic Tests
     */

    @Test
    public void substringTest() {
        String register = "000222229999999999220324";
        System.out.println(register.substring(0, 8).trim());
    }

    @Test
    public void loopTest() {

        int maxSizeReport = 500;
        int splitter = (int) ceil((double) 5500 / 500);

        for (int i = 0; i < splitter; i++) {

            System.out.println("INT I: " + i);

            for (int j = i*maxSizeReport; j < (i*maxSizeReport)+maxSizeReport; j++) {
                System.out.println("INT J: " + j);
            }
        }
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SampleDto {
        int id;
        String name;
    }

    private List<? extends SampleDto> listDto() {

        List<SampleDto> sampleDtoList = new ArrayList<>();

        for (int k = 0; k < 1000; k++) {
            SampleDto sampleDto = new SampleDto();
            sampleDto.setId(k);
            sampleDto.setName("Testing... " + k);
            sampleDtoList.add(sampleDto);
        }

        return sampleDtoList;
    }

    @Test
    public void loopListDtoTest() {
        List<? extends SampleDto> sampleDto = listDto();
        System.out.println(sampleDto);

        int maxSizeReport = 100;

        int splitter = (int) ceil((double) sampleDto.size() / maxSizeReport);

        for (int i = 0; i < splitter; i++) {
            List<? extends SampleDto> sampleDtoCurrent = sampleDto.subList(i*maxSizeReport,(i*maxSizeReport)+maxSizeReport);

            System.out.println("I: " + i);
            System.out.println(("["+(i*maxSizeReport)+"]:["+((i*maxSizeReport)+maxSizeReport))+"]");
            System.out.println(sampleDtoCurrent);
        }
    }

}





