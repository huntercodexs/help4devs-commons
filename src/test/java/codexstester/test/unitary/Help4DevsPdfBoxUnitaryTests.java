package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ColorsAvailable.color;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontAvailable.fontName;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontSize.fontSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.PageSizeAvailable.pageSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.*;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ProtectionLevel.protectionLevel;

public class Help4DevsPdfBoxUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathSource = "./src/test/resources/help4devs/files/txt/file.txt";
    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
    private final static String imagePath = "./src/test/resources/help4devs/images/ads/file.png";

    private PdfBoxDocumentSettings documentSettings() {
        PdfBoxDocumentSettings settings = new PdfBoxDocumentSettings();
        settings.setStartPage(0);
        settings.setEndPage(2);
        settings.setNumberOfPages(3);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Test");
        settings.setFontName("courier");
        settings.setFontSize("x_small");
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null);
        settings.setOwnerPassword(null);
        settings.setProtectionLevel("high");
        settings.setSignature(null);
        settings.setFilenamePath(filepathTarget);
        return settings;
    }

    private PdfBoxPageSettings pageSettings() {
        PdfBoxPageSettings settings = new PdfBoxPageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(750);
        settings.setLineHeight(18);
        settings.setPageNumber(0);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize("A4");
        settings.setFontName("courier");
        settings.setFontSize("x_small");
        settings.setFontColor("red");
        settings.setPageColor("white");
        settings.setTextContent("data to write");
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxRectangleSettings rectangleSettings() {
        PdfBoxRectangleSettings settings = new PdfBoxRectangleSettings();
        settings.setWidth(400);
        settings.setHeight(200);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor("white");
        settings.setBorderColor("black");
        return settings;
    }

    private PdfBoxTextSettings textSettings() {
        PdfBoxTextSettings settings = new PdfBoxTextSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setLetterSpace(1);
        settings.setBold(false);
        settings.setItalic(false);
        settings.setUnderline(false);
        settings.setFontName("courier");
        settings.setFontSize("x-small");
        return settings;
    }

    private PdfBoxImageSettings imageSettings() {
        PdfBoxImageSettings settings = new PdfBoxImageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        return settings;
    }

    @Test
    public void pageSizeTest() {
        PDRectangle pageSize = pageSize("A0");
        codexsTesterAssertExact("[0.0,0.0,2383.937,3370.3938]", String.valueOf(pageSize));

        pageSize = pageSize("A1");
        codexsTesterAssertExact("[0.0,0.0,1683.7795,2383.937]", String.valueOf(pageSize));

        pageSize = pageSize("A2");
        codexsTesterAssertExact("[0.0,0.0,1190.5513,1683.7795]", String.valueOf(pageSize));

        pageSize = pageSize("A3");
        codexsTesterAssertExact("[0.0,0.0,841.8898,1190.5513]", String.valueOf(pageSize));

        pageSize = pageSize("A4");
        codexsTesterAssertExact("[0.0,0.0,595.27563,841.8898]", String.valueOf(pageSize));

        pageSize = pageSize("A5");
        codexsTesterAssertExact("[0.0,0.0,419.52756,595.27563]", String.valueOf(pageSize));

        pageSize = pageSize("A6");
        codexsTesterAssertExact("[0.0,0.0,297.63782,419.52756]", String.valueOf(pageSize));

        pageSize = pageSize("LEGAL");
        codexsTesterAssertExact("[0.0,0.0,612.0,1008.0]", String.valueOf(pageSize));

        pageSize = pageSize("LETTER");
        codexsTesterAssertExact("[0.0,0.0,612.0,792.0]", String.valueOf(pageSize));
    }

    @Test
    public void colorTest() {

        Color color = color("WHITE");
        codexsTesterAssertExact("java.awt.Color[r=255,g=255,b=255]", String.valueOf(color));

        color = color("RED");
        codexsTesterAssertExact("java.awt.Color[r=255,g=0,b=0]", String.valueOf(color));

        color = color("GREEN");
        codexsTesterAssertExact("java.awt.Color[r=0,g=255,b=0]", String.valueOf(color));

        color = color("BLUE");
        codexsTesterAssertExact("java.awt.Color[r=0,g=0,b=255]", String.valueOf(color));

        color = color("BLACK");
        codexsTesterAssertExact("java.awt.Color[r=0,g=0,b=0]", String.valueOf(color));

        color = color("GRAY");
        codexsTesterAssertExact("java.awt.Color[r=128,g=128,b=128]", String.valueOf(color));

        color = color("PINK");
        codexsTesterAssertExact("java.awt.Color[r=255,g=175,b=175]", String.valueOf(color));

        color = color("YELLOW");
        codexsTesterAssertExact("java.awt.Color[r=255,g=255,b=0]", String.valueOf(color));

        color = color("ORANGE");
        codexsTesterAssertExact("java.awt.Color[r=255,g=200,b=0]", String.valueOf(color));

        color = color("CYAN");
        codexsTesterAssertExact("java.awt.Color[r=0,g=255,b=255]", String.valueOf(color));

        color = color("MAGENTA");
        codexsTesterAssertExact("java.awt.Color[r=255,g=0,b=255]", String.valueOf(color));

    }

    @Test
    public void fontTest() {

        PDType1Font font = fontName("zap");
        codexsTesterAssertExact("PDType1Font ZapfDingbats", String.valueOf(font));

        font = fontName("zap");
        codexsTesterAssertExact("PDType1Font ZapfDingbats", String.valueOf(font));

        font = fontName("symbol");
        codexsTesterAssertExact("PDType1Font Symbol", String.valueOf(font));

        font = fontName("times");
        codexsTesterAssertExact("PDType1Font Times-Roman", String.valueOf(font));

        font = fontName("times_b");
        codexsTesterAssertExact("PDType1Font Times-Bold", String.valueOf(font));

        font = fontName("times_i");
        codexsTesterAssertExact("PDType1Font Times-Italic", String.valueOf(font));

        font = fontName("times_bi");
        codexsTesterAssertExact("PDType1Font Times-BoldItalic", String.valueOf(font));

        font = fontName("courier");
        codexsTesterAssertExact("PDType1Font Courier", String.valueOf(font));

        font = fontName("courier_b");
        codexsTesterAssertExact("PDType1Font Courier-Bold", String.valueOf(font));

        font = fontName("courier_i");
        codexsTesterAssertExact("PDType1Font Courier-Oblique", String.valueOf(font));

        font = fontName("courier_bi");
        codexsTesterAssertExact("PDType1Font Courier-BoldOblique", String.valueOf(font));

        font = fontName("helvetica");
        codexsTesterAssertExact("PDType1Font Helvetica", String.valueOf(font));

        font = fontName("helvetica_b");
        codexsTesterAssertExact("PDType1Font Helvetica-Bold", String.valueOf(font));

        font = fontName("helvetica_i");
        codexsTesterAssertExact("PDType1Font Helvetica-Oblique", String.valueOf(font));

        font = fontName("helvetica_bi");
        codexsTesterAssertExact("PDType1Font Helvetica-BoldOblique", String.valueOf(font));
    }

    @Test
    public void fontSizeTest() {
        int fontSize = fontSize("x_small");
        codexsTesterAssertInt(5, fontSize);

        fontSize = fontSize("small");
        codexsTesterAssertInt(8, fontSize);

        fontSize = fontSize("normal");
        codexsTesterAssertInt(12, fontSize);

        fontSize = fontSize("medium");
        codexsTesterAssertInt(16, fontSize);

        fontSize = fontSize("large");
        codexsTesterAssertInt(24, fontSize);

        fontSize = fontSize("x_large");
        codexsTesterAssertInt(45, fontSize);
    }

    @Test
    public void protectionLevelTest() {
        int protectionLevel = protectionLevel("low");
        codexsTesterAssertInt(64, protectionLevel);

        protectionLevel = protectionLevel("middle");
        codexsTesterAssertInt(128, protectionLevel);

        protectionLevel = protectionLevel("high");
        codexsTesterAssertInt(256, protectionLevel);
    }

    @Test
    public void pdfCreateTest() throws IOException {
        String data = binFile(filepathSource);

        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();

        docSet.setNumberOfPages(3);

        for (int i = 0; i < docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfCreateUsingPasswordTest() throws IOException {
        String data = binFile(filepathSource);

        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();

        docSet.setNumberOfPages(3);
        docSet.setUserPassword("password");
        docSet.setOwnerPassword("password");

        for (int i = 0; i < docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfAddImageTest() throws IOException {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        pageSet.setPageNumber(0);
        pageSet.setImageFilepath(imagePath);
        pdfAddImage(docSet, pageSet);
    }

    @Test
    public void pdfAddBoxTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        PdfBoxRectangleSettings rectSet = rectangleSettings();
        PdfBoxTextSettings textSet = textSettings();
        PdfBoxImageSettings imgSet = imageSettings();

        pdfAddBox(docSet, pageSet, rectSet, textSet, imgSet);
    }

    @Test
    public void pdfReaderTest() {
        PdfBoxDocumentSettings docSet = documentSettings();

        /*Whole Document*/
        docSet.setStartPage(0);
        docSet.setEndPage(0);
        String result = pdfReader(docSet);
        System.out.println(result);

        /*First Page*/
        docSet.setStartPage(0);
        docSet.setEndPage(1);
        result = pdfReader(docSet);
        System.out.println(result);

        /*First and Second Page*/
        docSet.setStartPage(0);
        docSet.setEndPage(2);
        result = pdfReader(docSet);
        System.out.println(result);

        /*First and Second and Third Page*/
        docSet.setStartPage(0);
        docSet.setEndPage(3);
        result = pdfReader(docSet);
        System.out.println(result);

        /*Page Start > numberOfPages*/
        try {
            docSet.setStartPage(4);
            docSet.setEndPage(6);
            result = pdfReader(docSet);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        /*Page End > numberOfPages*/
        try {
            docSet.setStartPage(0);
            docSet.setEndPage(4);
            result = pdfReader(docSet);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        /*Page Start > Page End*/
        try {
            docSet.setStartPage(4);
            docSet.setEndPage(1);
            result = pdfReader(docSet);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void pdfProtectTest() throws IOException {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setUserPassword("password");
        docSet.setOwnerPassword("password");
        pdfProtect(docSet);
    }

    @Test
    public void pdfUnprotectTest() throws IOException {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setUserPassword("password");
        docSet.setOwnerPassword("password");
        pdfUnprotect(docSet);
    }

    @Test
    public void pdfFromImageTest() throws IOException {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath("./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image.pdf");
        docSet.setUserPassword("");
        docSet.setOwnerPassword("");

        PdfBoxPageSettings pageSet = pageSettings();
        pageSet.setPageNumber(0);
        pageSet.setImageFilepath(imagePath);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfFromImageUsingPasswordTest() throws IOException {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath("./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image-password.pdf");
        docSet.setUserPassword("password");
        docSet.setOwnerPassword("password");

        PdfBoxPageSettings pageSet = pageSettings();
        pageSet.setPageNumber(0);
        pageSet.setImageFilepath(imagePath);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfFromDocTest() throws IOException, ExecutionException, InterruptedException {
        /*TODO*/
    }

    @Test
    public void pdfDetailsTest() {
        String filenamePath = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
        PdfBoxDocumentDetails details = pdfDetails(filenamePath);
        System.out.println(details);
    }

}
