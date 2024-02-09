package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import static com.huntercodexs.demojobs.services.Help4DevsBaseService.*;
import static com.huntercodexs.demojobs.services.Help4DevsCurrencyService.*;
import static com.huntercodexs.demojobs.services.Help4DevsDateService.*;
import static com.huntercodexs.demojobs.services.Help4DevsFileHandlerService.*;
import static com.huntercodexs.demojobs.services.Help4DevsFileReaderService.getFileContent;
import static com.huntercodexs.demojobs.services.Help4DevsPathService.sanitizeAscii;
import static com.huntercodexs.demojobs.services.Help4DevsPathService.sanitizePath;
import static com.huntercodexs.demojobs.services.Help4DevsStringHandlerService.*;
import static com.huntercodexs.demojobs.services.Help4DevsToolsService.guide;
import static com.huntercodexs.demojobs.services.Help4DevsToolsService.md5;
import static com.huntercodexs.demojobs.services.Help4DevsValidatorService.*;

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
        String result = sanitizeAscii("Teste com acentuação é inevital !");
        System.out.println("RESULT IS: " + result);
    }

    /**
     * File Reader Tests
     */

    @Test
    public void getFileContentTest() throws Exception {
        /*TIP: Edit the file src/test/resources/help4devs/file.txt and press Enter button*/
        String code = getFileContent("src/test/resources/help4devs/file.txt", "[0-9]{6}",1500000000);
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
        InputStream result = bytesExtractorShipmentFile("/home/jereelton/Documentos/Devel/Java/JProjects/demo-projects/help4devs/src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToByteArrayTest() throws IOException {
        InputStream result = fileToByteArray("/home/jereelton/Documentos/Devel/Java/JProjects/demo-projects/help4devs/src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToDataSourceTest() throws IOException {
        ByteArrayDataSource result = fileToDataSource("/home/jereelton/Documentos/Devel/Java/JProjects/demo-projects/help4devs/src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToStringTest() throws IOException {
        String result = fileToString("/home/jereelton/Documentos/Devel/Java/JProjects/demo-projects/help4devs/src/test/resources/help4devs/", "external.tests.properties");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void fileToArrayTest() throws IOException {
        ArrayList<String> result = fileToArray("/home/jereelton/Documentos/Devel/Java/JProjects/demo-projects/help4devs/src/test/resources/help4devs/", "external.tests.properties");
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
        System.out.println(" > ["+ rgFormatter("9090909090", "CNH")+"]");
        System.out.println(" > ["+ rgFormatter("7878787878", "SSP SC")+"]");
        System.out.println(" > ["+ rgFormatter("6767676767", "SSPSP")+"]");
        System.out.println(" > ["+ rgFormatter("1212121212", "SSCMG")+"]");
        System.out.println(" > ["+ rgFormatter("2020202020", "SSP/RJ")+"]");
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
    public void calculateDateTest() {
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:53:26");
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:54:26");
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 16:54:26");
        quantifyDate("14/07/2023 15:53:25", "15/07/2023 16:54:26");
        quantifyDate("14/07/2023 15:53:25", "15/08/2023 16:54:26");
        quantifyDate("14/07/2022 15:53:25", "15/08/2023 16:54:26");
    }

    @Test
    public void quantifyMillisDateTest() {
        quantifyMillisDate();
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
        String localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "-", 3);
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
        boolean result = phoneValidator("5511982772389");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        boolean result2 = phoneValidator("5511982772");
        System.out.println("RESULT IS: " + result2);
        codexsTesterAssertBool(result2, false);

        boolean result3 = phoneValidator("551187722212");
        System.out.println("RESULT IS: " + result3);
        codexsTesterAssertBool(result3, true);
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

}





