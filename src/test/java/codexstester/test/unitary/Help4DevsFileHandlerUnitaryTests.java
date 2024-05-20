package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.*;
import static com.huntercodexs.demo.services.Help4DevsFileReaderService.exists;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsFileHandlerUnitaryTests extends Help4DevsBridgeTests {

    private static final String pathImages = "src/test/resources/help4devs/images";

    @Test
    public void loadPropsTest() {
        Properties props = loadProps("classpath:application.properties");
        stdout(String.valueOf(props));
    }

    @Test
    public void bytesExtractorShipmentFileTest() throws IOException {
        InputStream result = bytesFileExtractor("./src/test/resources/help4devs/", "external.tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileToByteArrayTest() throws IOException {
        InputStream result = fileToByteArray("./src/test/resources/help4devs/", "external.tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileToDataSourceTest() throws IOException {
        ByteArrayDataSource result = fileToDataSource("./src/test/resources/help4devs/", "external.tests.properties");
        stdout("RESULT");
        stdout(String.valueOf(result));
    }

    @Test
    public void fileToStringTest() throws IOException {
        String result = fileToString("./src/test/resources/help4devs/", "external.tests.properties");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringConfTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.conf");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringCsvTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.csv");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringDocTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.doc");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringJpgTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.jpg");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringPngTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.png");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringPdfTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.pdf");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringTxtTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.txt");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToStringXlsTest() throws IOException {
        String result = fileToString("./src/main/resources/", "attach.xls");
        stdout("RESULT");
        stdout(result);
    }

    @Test
    public void fileToArrayTest() throws IOException {
        ArrayList<String> result = fileToArray("./src/test/resources/help4devs/", "external.tests.properties");
        stdout("RESULT IS: " + result);
    }

    @Test
    public void fileInputStreamTest() throws IOException {
        String stream = fileInputStream("src/test/resources/help4devs/file.txt");
        stdout(stream);
    }

    @Test
    public void byteFileTest() throws IOException {
        stdout(Arrays.toString(byteFile("src/test/resources/help4devs/images/file2.jpg")));
    }

    @Test
    public void ioFileTest() throws IOException {
        stdout(ioFile(pathImages + "/5-jpg/file1.jpg"));
    }

    @Test
    public void binFileTest() throws IOException {
        stdout(binFile("src/test/resources/help4devs/images/11-pdf/file.pdf"));
    }

    @Test
    public void existsTest() {
        if (exists("/home/jereelton/logs/.lock")) {
            stdout("LOG NOT ALLOWED");
        } else {
            stdout("LOG ALLOWED");
        }
    }

    @Test
    public void folderCreateTest() {
        codexsTesterAssertBool(true, folderCreate("/home/jereelton/tmp/folder-created-from-java-tests"));
    }

    @Test
    public void fileDeleteTest() {
        codexsTesterAssertBool(true, fileDelete("/home/jereelton/tmp/folder-created-from-java-tests"));
    }

    @Test
    public void fileMoveTest() {
        codexsTesterAssertBool(true, fileMove(
                "/home/jereelton/tmp/folder-created-from-java-tests",
                "/home/jereelton/tmp/folder-moved-from-java-tests"));
    }

    @Test
    public void fileWriterTest() {
        String textTest = "This is only a test!\n";
        byte[] bytes = textTest.getBytes();
        codexsTesterAssertBool(true, fileWriter(bytes,"/home/jereelton/tmp/file-tests-2.txt"));
    }

}





