package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.*;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontNameToPdfBox.fontName;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontSizeToPdfBox.fontSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ImageQualityToPdfBox.imageQuality;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ImageTypeToPdfBox.imageType;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.PageSizeToPdfBox.pageSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ProtectionLevelToPdfBox.protectionLevel;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.TableTemplateToPdbBox.tableSize;

public class Help4DevsPdfBoxUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathSource = "./src/test/resources/help4devs/files/txt/file.txt";
    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
    private final static String filepathTargetPassword = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-password.pdf";
    private final static String imagePath = "./src/test/resources/help4devs/images/ads/file.png";
    private final static String imagePathAds = "./src/test/resources/help4devs/images/ads/img.png";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private PdfBoxDocumentSettings documentSettings() {
        PdfBoxDocumentSettings settings = new PdfBoxDocumentSettings();
        settings.setStartPage(0);
        settings.setEndPage(2);
        settings.setNumberOfPages(3);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Test");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null);
        settings.setOwnerPassword(null);
        settings.setProtectionLevel(ProtectionLevelToPdfBox.HIGH);
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
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(PageSizeToPdfBox.LETTER);
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.X_SMALL);
        settings.setFontColor(ColorsToPdfBox.BLACK);
        settings.setPageColor(ColorsToPdfBox.WHITE);
        settings.setTextContent("data to write");
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxRectangleSettings rectangleSettings() {
        PdfBoxRectangleSettings settings = new PdfBoxRectangleSettings();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(ColorsToPdfBox.BLUE3);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
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
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setImageType(ImageTypeToPdfBox.JPEG);
        return settings;
    }

    private PdfBoxTableSettings tableSettings() {
        PdfBoxTableSettings settings = new PdfBoxTableSettings();
        settings.setWidth(100);
        settings.setHeight(100);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToPdfBox.ORANGE);
        settings.setCelColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        settings.setTableTemplate(TableTemplateToPdbBox.TABLE_5X6);
        return settings;
    }

    @Test
    public void pageSizeTest() {
        PDRectangle pageSize = pageSize(PageSizeToPdfBox.A0);
        codexsTesterAssertExact("[0.0,0.0,2383.937,3370.3938]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A1);
        codexsTesterAssertExact("[0.0,0.0,1683.7795,2383.937]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A2);
        codexsTesterAssertExact("[0.0,0.0,1190.5513,1683.7795]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A3);
        codexsTesterAssertExact("[0.0,0.0,841.8898,1190.5513]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A4);
        codexsTesterAssertExact("[0.0,0.0,595.27563,841.8898]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A5);
        codexsTesterAssertExact("[0.0,0.0,419.52756,595.27563]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.A6);
        codexsTesterAssertExact("[0.0,0.0,297.63782,419.52756]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.LEGAL);
        codexsTesterAssertExact("[0.0,0.0,612.0,1008.0]", String.valueOf(pageSize));

        pageSize = pageSize(PageSizeToPdfBox.LETTER);
        codexsTesterAssertExact("[0.0,0.0,612.0,792.0]", String.valueOf(pageSize));
    }

    @Test
    public void colorTest() {

        Color color = color(ColorsToPdfBox.WHITE);
        codexsTesterAssertExact("java.awt.Color[r=255,g=255,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.RED);
        codexsTesterAssertExact("java.awt.Color[r=255,g=0,b=0]", String.valueOf(color));

        color = color(ColorsToPdfBox.GREEN);
        codexsTesterAssertExact("java.awt.Color[r=0,g=255,b=0]", String.valueOf(color));

        color = color(ColorsToPdfBox.GREEN2);
        codexsTesterAssertExact("java.awt.Color[r=26,g=188,b=156]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE);
        codexsTesterAssertExact("java.awt.Color[r=0,g=0,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE2);
        codexsTesterAssertExact("java.awt.Color[r=51,g=181,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE3);
        codexsTesterAssertExact("java.awt.Color[r=78,g=120,b=149]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLACK);
        codexsTesterAssertExact("java.awt.Color[r=0,g=0,b=0]", String.valueOf(color));

        color = color(ColorsToPdfBox.GRAY);
        codexsTesterAssertExact("java.awt.Color[r=128,g=128,b=128]", String.valueOf(color));

        color = color(ColorsToPdfBox.LIGHT_GRAY);
        codexsTesterAssertExact("java.awt.Color[r=192,g=192,b=192]", String.valueOf(color));

        color = color(ColorsToPdfBox.ICE);
        codexsTesterAssertExact("java.awt.Color[r=235,g=235,b=235]", String.valueOf(color));

        color = color(ColorsToPdfBox.PURPLE);
        codexsTesterAssertExact("java.awt.Color[r=173,g=108,b=227]", String.valueOf(color));

        color = color(ColorsToPdfBox.GOLD);
        codexsTesterAssertExact("java.awt.Color[r=255,g=215,b=0]", String.valueOf(color));

        color = color(ColorsToPdfBox.PINK);
        codexsTesterAssertExact("java.awt.Color[r=231,g=6,b=176]", String.valueOf(color));

        color = color(ColorsToPdfBox.YELLOW);
        codexsTesterAssertExact("java.awt.Color[r=255,g=255,b=0]", String.valueOf(color));

        color = color(ColorsToPdfBox.ORANGE);
        codexsTesterAssertExact("java.awt.Color[r=250,g=123,b=24]", String.valueOf(color));

        color = color(ColorsToPdfBox.CYAN);
        codexsTesterAssertExact("java.awt.Color[r=0,g=255,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.MAGENTA);
        codexsTesterAssertExact("java.awt.Color[r=255,g=0,b=255]", String.valueOf(color));

    }

    @Test
    public void fontNameTest() {

        PDType1Font font = fontName(FontNameToPdfBox.ZAP);
        codexsTesterAssertExact("PDType1Font ZapfDingbats", String.valueOf(font));

        font = fontName(FontNameToPdfBox.SYMBOL);
        codexsTesterAssertExact("PDType1Font Symbol", String.valueOf(font));

        font = fontName(FontNameToPdfBox.TIMES);
        codexsTesterAssertExact("PDType1Font Times-Roman", String.valueOf(font));

        font = fontName(FontNameToPdfBox.TIMES_B);
        codexsTesterAssertExact("PDType1Font Times-Bold", String.valueOf(font));

        font = fontName(FontNameToPdfBox.TIMES_I);
        codexsTesterAssertExact("PDType1Font Times-Italic", String.valueOf(font));

        font = fontName(FontNameToPdfBox.TIMES_BI);
        codexsTesterAssertExact("PDType1Font Times-BoldItalic", String.valueOf(font));

        font = fontName(FontNameToPdfBox.COURIER);
        codexsTesterAssertExact("PDType1Font Courier", String.valueOf(font));

        font = fontName(FontNameToPdfBox.COURIER_B);
        codexsTesterAssertExact("PDType1Font Courier-Bold", String.valueOf(font));

        font = fontName(FontNameToPdfBox.COURIER_I);
        codexsTesterAssertExact("PDType1Font Courier-Oblique", String.valueOf(font));

        font = fontName(FontNameToPdfBox.COURIER_BI);
        codexsTesterAssertExact("PDType1Font Courier-BoldOblique", String.valueOf(font));

        font = fontName(FontNameToPdfBox.HELVETICA);
        codexsTesterAssertExact("PDType1Font Helvetica", String.valueOf(font));

        font = fontName(FontNameToPdfBox.HELVETICA_B);
        codexsTesterAssertExact("PDType1Font Helvetica-Bold", String.valueOf(font));

        font = fontName(FontNameToPdfBox.HELVETICA_I);
        codexsTesterAssertExact("PDType1Font Helvetica-Oblique", String.valueOf(font));

        font = fontName(FontNameToPdfBox.HELVETICA_BI);
        codexsTesterAssertExact("PDType1Font Helvetica-BoldOblique", String.valueOf(font));
    }

    @Test
    public void fontSizeTest() {
        int fontSize = fontSize(FontSizeToPdfBox.X_SMALL);
        codexsTesterAssertInt(5, fontSize);

        fontSize = fontSize(FontSizeToPdfBox.SMALL);
        codexsTesterAssertInt(8, fontSize);

        fontSize = fontSize(FontSizeToPdfBox.NORMAL);
        codexsTesterAssertInt(12, fontSize);

        fontSize = fontSize(FontSizeToPdfBox.MEDIUM);
        codexsTesterAssertInt(16, fontSize);

        fontSize = fontSize(FontSizeToPdfBox.LARGE);
        codexsTesterAssertInt(24, fontSize);

        fontSize = fontSize(FontSizeToPdfBox.X_LARGE);
        codexsTesterAssertInt(45, fontSize);
    }

    @Test
    public void protectionLevelTest() {
        int protectionLevel = protectionLevel(ProtectionLevelToPdfBox.LOW);
        codexsTesterAssertInt(64, protectionLevel);

        protectionLevel = protectionLevel(ProtectionLevelToPdfBox.MIDDLE);
        codexsTesterAssertInt(128, protectionLevel);

        protectionLevel = protectionLevel(ProtectionLevelToPdfBox.HIGH);
        codexsTesterAssertInt(256, protectionLevel);
    }

    @Test
    public void imageTypeTest() {
        String imageType = imageType(ImageTypeToPdfBox.JPEG);
        codexsTesterAssertExact("JPEG", imageType);

        imageType = imageType(ImageTypeToPdfBox.JPG);
        codexsTesterAssertExact("JPEG", imageType);

        imageType = imageType(ImageTypeToPdfBox.PNG);
        codexsTesterAssertExact("PNG", imageType);

        imageType = imageType(ImageTypeToPdfBox.GIF);
        codexsTesterAssertExact("GIF", imageType);

        imageType = imageType(ImageTypeToPdfBox.TIFF);
        codexsTesterAssertExact("TIFF", imageType);

        imageType = imageType(ImageTypeToPdfBox.BMP);
        codexsTesterAssertExact("BMP", imageType);
    }

    @Test
    public void imageQualityTest() {
        int imageQuality = imageQuality(ImageQualityToPdfBox.LOW);
        codexsTesterAssertInt(50, imageQuality);

        imageQuality = imageQuality(ImageQualityToPdfBox.NORMAL);
        codexsTesterAssertInt(120, imageQuality);

        imageQuality = imageQuality(ImageQualityToPdfBox.GOOD);
        codexsTesterAssertInt(300, imageQuality);

        imageQuality = imageQuality(ImageQualityToPdfBox.ULTRA);
        codexsTesterAssertInt(500, imageQuality);

        imageQuality = imageQuality(ImageQualityToPdfBox.SUPER);
        codexsTesterAssertInt(800, imageQuality);
    }

    @Test
    public void tableTemplateTest() {
        int[] tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_5X6);
        codexsTesterAssertExact("5,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_5X5);
        codexsTesterAssertExact("5,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_5X4);
        codexsTesterAssertExact("5,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_5X3);
        codexsTesterAssertExact("5,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_5X2);
        codexsTesterAssertExact("5,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_4X6);
        codexsTesterAssertExact("4,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_4X5);
        codexsTesterAssertExact("4,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_4X4);
        codexsTesterAssertExact("4,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_4X3);
        codexsTesterAssertExact("4,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_4X2);
        codexsTesterAssertExact("4,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_3X6);
        codexsTesterAssertExact("3,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_3X5);
        codexsTesterAssertExact("3,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_3X4);
        codexsTesterAssertExact("3,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_3X3);
        codexsTesterAssertExact("3,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_3X2);
        codexsTesterAssertExact("3,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_2X6);
        codexsTesterAssertExact("2,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_2X5);
        codexsTesterAssertExact("2,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_2X4);
        codexsTesterAssertExact("2,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_2X3);
        codexsTesterAssertExact("2,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableTemplateToPdbBox.TABLE_2X2);
        codexsTesterAssertExact("2,2", tableTemplate[0]+","+tableTemplate[1]);
    }

    @Test
    public void pdfCreateTest() throws IOException {
        String data = binFile(filepathSource);

        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();

        docSet.setNumberOfPages(3);

        for (int i = 1; i <= docSet.getNumberOfPages(); i++) {
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

        docSet.setFilenamePath(filepathTargetPassword);
        docSet.setNumberOfPages(3);
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);

        for (int i = 1; i <= docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfAddImageTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        PdfBoxImageSettings imgSet = imageSettings();

        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(imagePath);

        imgSet.setWidth(500);
        imgSet.setHeight(150);
        imgSet.setOffsetX(40);
        imgSet.setOffsetY(100);
        imgSet.setResize(false);

        pdfAddImage(docSet, pageSet, imgSet);
    }

    @Test
    public void pdfAddBoxTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        PdfBoxRectangleSettings rectSet = rectangleSettings();
        PdfBoxTextSettings textSet = textSettings();
        PdfBoxImageSettings imgSet = imageSettings();

        pageSet.setPageNumber(2);
        rectSet.setBackColor(ColorsToPdfBox.ICE);
        rectSet.setBorderColor(ColorsToPdfBox.BLACK);

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
        docSet.setStartPage(1);
        docSet.setEndPage(2);
        result = pdfReader(docSet);
        System.out.println(result);

        /*First and Second Page*/
        docSet.setStartPage(1);
        docSet.setEndPage(3);
        result = pdfReader(docSet);
        System.out.println(result);

        /*First and Second and Third Page*/
        docSet.setStartPage(1);
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
            docSet.setStartPage(1);
            docSet.setEndPage(4);
            result = pdfReader(docSet);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        /*Page Start > Page End*/
        try {
            docSet.setStartPage(5);
            docSet.setEndPage(4);
            result = pdfReader(docSet);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void pdfProtectTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);
        pdfProtect(docSet);
    }

    @Test
    public void pdfUnprotectTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);
        pdfUnprotect(docSet);
    }

    @Test
    public void pdfDetailsTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);
        PdfBoxDocumentDetails details = pdfDetails(docSet);
        System.out.println(details);
    }

    @Test
    public void pdfFromImageTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath("./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image.pdf");
        docSet.setUserPassword("");
        docSet.setOwnerPassword("");

        PdfBoxPageSettings pageSet = pageSettings();
        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(imagePathAds);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfFromImageUsingPasswordTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath("./src/test/resources/help4devs/files/pdf/my-pdfbox-from-image-password.pdf");
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);

        PdfBoxPageSettings pageSet = pageSettings();
        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(imagePathAds);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfToImageTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        PdfBoxImageSettings imageSet = imageSettings();

        docSet.setFilenamePath(filepathTarget);
        docSet.setUserPassword(userPassword);
        docSet.setUserPassword(ownerPassword);

        imageSet.setImageQuality(ImageQualityToPdfBox.NORMAL);
        imageSet.setImageType(ImageTypeToPdfBox.JPEG);

        //Specific page
        //pageSet.setPageNumber(1);
        //imageSet.setFilenamePath("./src/test/resources/help4devs/images/ads/exported-NORMAL.jpg");
        //pdfToImage(docSet, pageSet, imageSet);

        //Whole document
        for (int k = 1; k < 4; k++) {
            pageSet.setPageNumber(k);
            imageSet.setFilenamePath("./src/test/resources/help4devs/images/ads/exported-NORMAL-"+k+".jpg");
            pdfToImage(docSet, pageSet, imageSet);
        }
    }

    @Test
    public void pdfSplitterTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath(filepathTarget);
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);
        pdfSplitter(docSet, "./src/test/resources/help4devs/files/pdf/");
    }

    @Test
    public void pdfMergerTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        docSet.setFilenamePath("./src/test/resources/help4devs/files/pdf/my-pdfbox-test-merged.pdf");
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);

        List<String> pdfList = new ArrayList<>();
        pdfList.add("./src/test/resources/help4devs/files/pdf/my-pdfbox-test-1.pdf");
        pdfList.add("./src/test/resources/help4devs/files/pdf/my-pdfbox-test-2.pdf");
        pdfList.add("./src/test/resources/help4devs/files/pdf/my-pdfbox-test-3.pdf");

        pdfMerger(docSet, pdfList);
    }

    @Test
    public void pdfFromDocTest() {
        /*TODO*/
    }

}
