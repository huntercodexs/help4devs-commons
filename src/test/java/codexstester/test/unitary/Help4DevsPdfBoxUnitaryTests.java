package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.*;

public class Help4DevsPdfBoxUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathSource = "./src/test/resources/help4devs/files/txt/file.txt";
    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";

    private PdfBoxSettings settings() {
        PdfBoxSettings settings = new PdfBoxSettings();
        settings.setLineHeight(15);
        settings.setOffsetX(25);
        settings.setOffsetY(750);
        settings.setTitle("Title Test");
        settings.setFontName("courier");
        settings.setFontSize("x-small");
        settings.setAuthor("Huntercodexs");
        settings.setDate("1990-01-01 10:00:00");
        settings.setSubject("Test");
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setPassword(null);
        return settings;
    }

    @Test
    public void pdfCreateTest() throws IOException {
        String data = binFile(filepathSource);

        int numberOfPages = 3;
        PdfBoxSettings settings = settings();

        for (int i = 0; i < numberOfPages; i++) {
            settings.setPageNumber(i);
            pdfCreate("PAGE-" + i + ":\n" + data, filepathTarget, settings);
        }
    }

    @Test
    public void pdfCreateUsingPasswordTest() throws IOException {
        String data = binFile(filepathSource);

        int numberOfPages = 1;
        PdfBoxSettings settings = settings();
        settings.setPassword("password");

        for (int i = 0; i < numberOfPages; i++) {
            settings.setPageNumber(i);
            pdfCreate("PAGE-" + i + ":\n" + data, filepathTarget, settings);
        }
    }

    @Test
    public void pdfAddImageTest() throws IOException {
        String imagePath = "./src/test/resources/help4devs/images/ads/file.png";
        PdfBoxSettings settings = settings();
        settings.setPageNumber(0);
        pdfAddImage(imagePath, filepathTarget, settings);
    }

    @Test
    public void pdfAddBoxTest() {
        PdfBoxSettings settings = settings();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setPageNumber(1);
        pdfAddBox(filepathTarget, settings);
    }

    @Test
    public void pdfReaderTest() {
        /*Whole Document*/
        String result = pdfReader(
                "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                0,
                0);
        System.out.println(result);

        /*First Page*/
        result = pdfReader(
                "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                0,
                1);
        System.out.println(result);

        /*First and Second Page*/
        result = pdfReader(
                "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                0,
                2);
        System.out.println(result);

        /*First and Second and Third Page*/
        result = pdfReader(
                "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                0,
                3);
        System.out.println(result);

        /*Page Start > numberOfPages*/
        try {
            result = pdfReader(
                    "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                    4,
                    6);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        /*Page End > numberOfPages*/
        try {
            result = pdfReader(
                    "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                    0,
                    4);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        /*Page Start > Page End*/
        try {
            result = pdfReader(
                    "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf",
                    4,
                    1);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void pdfProtectTest() throws IOException {
        pdfProtect(filepathTarget, "password");
    }

    @Test
    public void pdfUnprotectTest() throws IOException {
        pdfUnprotect(filepathTarget, "password");
    }

    @Test
    public void pdfFromImageTest() throws IOException {
        String imagePath = "./src/test/resources/help4devs/images/ads/img.png";
        String filenamePath = "./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image.pdf";
        String password = "";
        pdfFromImage(imagePath, filenamePath, password);
    }

    @Test
    public void pdfFromImageUsingPasswordTest() throws IOException {
        String imagePath = "./src/test/resources/help4devs/images/ads/img.png";
        String filenamePath = "./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image-password.pdf";
        String password = "password";
        pdfFromImage(imagePath, filenamePath, password);
    }

    @Test
    public void pdfFromDocTest() throws IOException, ExecutionException, InterruptedException {
        /*TODO*/
    }

    @Test
    public void pdfDetailsTest() {
        String filenamePath = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
        PdfBoxDetails details = pdfDetails(filenamePath);
        System.out.println(details);
    }

}
