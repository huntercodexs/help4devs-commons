package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.*;
import static com.huntercodexs.demo.services.Help4DevsFileReaderService.exists;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsFileHandlerUnitaryTests extends Help4DevsBridgeTests {

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

    @Test
    public void fileInputStreamTest() throws IOException {
        String stream = fileInputStream("src/test/resources/help4devs/file.txt");
        stdout(stream);
    }

    @Test
    public void existsTest() {
        if (exists("/home/jereelton/logs/.lock")) {
            stdout("LOG NOT ALLOWED");
        } else {
            stdout("LOG ALLOWED");
        }
    }

}





