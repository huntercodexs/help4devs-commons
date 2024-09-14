package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.*;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplate;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.BoxDataContent;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.BoxTemplateSettings;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;
import com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplates;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMegabytes;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplates.template;

public class Help4DevsEasyPdfTemplateBoxUnitaryTests extends Help4DevsBridgeTests {

    private final static String path = "./src/test/resources/help4devs/files/pdf";
    private final static String pdfFilenameLetter = path+"/my-easypdf-test-template-box-LETTER.pdf";
    private final static String pdfFilenameLetterLayout = path+"/my-easypdf-test-template-box-LETTER-LAYOUT.pdf";
    private final static String pdfFilenameA4 = path+"/my-easypdf-test-template-box-A4.pdf";
    private final static String pdfFilenameA4Layout = path+"/my-easypdf-test-template-box-A4-LAYOUT.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/background/easypdf-background-sample-5.jpg";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private EasyPdfDocument documentSettings(PageSizeToEasyPdf pageType) {
        EasyPdfDocument settings = new EasyPdfDocument();
        settings.setStartPage(1);
        settings.setEndPage(1);
        settings.setNumberOfPages(1);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Document Test");
        settings.setFontName(FontNameToEasyPdf.COURIER);
        settings.setFontSize(FontSizeToEasyPdf.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setOwnerPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setProtectionLevel(ProtectionLevelToEasyPdf.HIGH);

        if (pageType.equals(PageSizeToEasyPdf.LETTER)) {
            settings.setFilenamePath(pdfFilenameLetter);
        } else if (pageType.equals(PageSizeToEasyPdf.A4)) {
            settings.setFilenamePath(pdfFilenameA4);
        } else if (pageType.equals(PageSizeToEasyPdf.LETTER_LAYOUT)) {
            settings.setFilenamePath(pdfFilenameLetterLayout);
        } else if (pageType.equals(PageSizeToEasyPdf.A4_LAYOUT)) {
            settings.setFilenamePath(pdfFilenameA4Layout);
        }

        return settings;
    }

    private EasyPdfPage pageSettings(PageSizeToEasyPdf pageType) {
        EasyPdfPage settings = new EasyPdfPage();
        settings.setWidth(842);
        settings.setHeight(595);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(pageType);
        settings.setFontName(FontNameToEasyPdf.COURIER);
        settings.setFontSize(FontSizeToEasyPdf.SMALL);
        settings.setFontColor(ColorsToEasyPdf.BLACK);
        settings.setPageColor(ColorsToEasyPdf.WHITE);
        settings.setTextContent(null);
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private EasyPdfContainer containerSettings() {
        EasyPdfContainer settings = new EasyPdfContainer();
        settings.setWidth(570);
        settings.setHeight(780);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBorderWidth(1);
        settings.setBackColor(ColorsToEasyPdf.WHITE);
        settings.setBorderColor(ColorsToEasyPdf.GRAY);
        return settings;
    }

    private EasyPdfTable tableSettings() {
        EasyPdfTable settings = new EasyPdfTable();
        settings.setWidth(540);
        settings.setHeight(90);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToEasyPdf.BLACK);
        settings.setCelColor(ColorsToEasyPdf.ICE);
        settings.setBorderColor(ColorsToEasyPdf.BLACK);
        settings.setTableTemplate(TableDimensionsToEasyPdf.TABLE_5X6);
        return settings;
    }

    private EasyPdfText textSettings() {
        EasyPdfText settings = new EasyPdfText();
        settings.setWidth(0);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setLetterSpace(1);
        settings.setBold(false);
        settings.setItalic(false);
        settings.setUnderline(false);
        settings.setFontName(FontNameToEasyPdf.HELVETICA);
        settings.setFontSize(FontSizeToEasyPdf.NORMAL);
        return settings;
    }

    private EasyPdfImage imageSettings() {
        EasyPdfImage settings = new EasyPdfImage();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setFilenamePath(imgJava);
        settings.setImageType(ImageTypeToEasyPdf.JPEG);
        settings.setImageQuality(ImageQualityToEasyPdf.GOOD);
        return settings;
    }

    private EasyPdfBarcode barcodeSettings() {
        EasyPdfBarcode settings = new EasyPdfBarcode();
        settings.setDpi(400);
        settings.setWidth(500);
        settings.setHeight(50);
        settings.setFontSize(2);
        settings.setLineHeight(20);
        settings.setOffsetX(55);
        settings.setOffsetY(35); //650,500,345,190,35
        settings.setFixQuiteZone(0);
        settings.setDoQuiteZone(false);
        settings.setData("123456789123456789123456789123456789");
        settings.setFontName(FontNameToEasyPdf.COURIER);
        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        settings.setCodeType4Scanner(CodeType4ScannerToEasyPdf.CODE128);
        return settings;
    }

    private EasyPdfQrCode qrCodeSettings() {
        EasyPdfQrCode settings = new EasyPdfQrCode();
        settings.setDpi(400);
        settings.setMargin(0);
        settings.setMatrix(100);
        settings.setSize(100);
        settings.setOnColor(0xFF000001);
        settings.setOffColor(0xFFFFFFFF);
        settings.setOffsetX(470); //40,260,470
        settings.setOffsetY(348); //655,502,348,193,38
        settings.setData("https://huntercodexs.com");
        settings.setCodeType4Scanner(CodeType4ScannerToEasyPdf.QRCODE);
        return settings;
    }

    private BoxTemplateSettings boxSettingsLetterLayout() {
        BoxTemplateSettings settings = new BoxTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToEasyPdf[]{
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.BLACK,
                ColorsToEasyPdf.NONE,
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.WHITE
        });

        return settings;
    }

    private BoxTemplateSettings boxSettingsA4Layout() {
        BoxTemplateSettings settings = new BoxTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToEasyPdf[]{
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.BLACK,
                ColorsToEasyPdf.NONE,
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.WHITE
        });

        return settings;
    }

    private BoxTemplateSettings boxSettingsLetter() {
        BoxTemplateSettings settings = new BoxTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToEasyPdf[]{
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.BLACK,
                ColorsToEasyPdf.NONE,
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.WHITE
        });

        return settings;
    }

    private BoxTemplateSettings boxSettingsA4() {
        BoxTemplateSettings settings = new BoxTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToEasyPdf[]{
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.BLACK,
                ColorsToEasyPdf.NONE,
                ColorsToEasyPdf.WHITE,
                ColorsToEasyPdf.WHITE
        });

        return settings;
    }

    private BoxDataContent boxData() {
        BoxDataContent settings = new BoxDataContent();
        return settings;
    }

    private EasyPdfTemplateSettings easyPdfTemplateSettingsLetterLayout() {
        PageSizeToEasyPdf pageType = PageSizeToEasyPdf.LETTER_LAYOUT;
        EasyPdfTemplateSettings settings = new EasyPdfTemplateSettings();
        settings.setTemplate(EasyPdfTemplates.BOX);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBox(boxSettingsLetterLayout());
        settings.setBoxContent(boxData());
        return settings;
    }

    private EasyPdfTemplateSettings easyPdfTemplateSettingsA4Layout() {
        PageSizeToEasyPdf pageType = PageSizeToEasyPdf.A4_LAYOUT;
        EasyPdfTemplateSettings settings = new EasyPdfTemplateSettings();
        settings.setTemplate(EasyPdfTemplates.BOX);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBox(boxSettingsA4Layout());
        settings.setBoxContent(boxData());
        return settings;
    }

    private EasyPdfTemplateSettings easyPdfTemplateSettingsLetter() {
        PageSizeToEasyPdf pageType = PageSizeToEasyPdf.LETTER;
        EasyPdfTemplateSettings settings = new EasyPdfTemplateSettings();
        settings.setTemplate(EasyPdfTemplates.BOX);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBox(boxSettingsLetter());
        settings.setBoxContent(boxData());
        return settings;
    }

    private EasyPdfTemplateSettings easyPdfTemplateSettingsA4() {
        PageSizeToEasyPdf pageType = PageSizeToEasyPdf.A4;
        EasyPdfTemplateSettings settings = new EasyPdfTemplateSettings();
        settings.setTemplate(EasyPdfTemplates.BOX);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBox(boxSettingsA4());
        settings.setBoxContent(boxData());
        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(EasyPdfTemplates.BOX);
        codexsTesterAssertExact("BOX", template);
    }

    @Test
    public void easyPdfTemplateBoxLetterLayoutTest() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        Help4DevsEasyPdfTemplate templateManager = new Help4DevsEasyPdfTemplate();
        templateManager.easyPdfTemplateBox(easyPdfTemplateSettingsLetterLayout());

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long usedMemory = freeMemoryAfter - freeMemoryBefore;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void easyPdfTemplateBoxA4LayoutTest() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        Help4DevsEasyPdfTemplate templateManager = new Help4DevsEasyPdfTemplate();
        templateManager.easyPdfTemplateBox(easyPdfTemplateSettingsA4Layout());

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long usedMemory = freeMemoryAfter - freeMemoryBefore;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void easyPdfTemplateBoxLetterSample1Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        Help4DevsEasyPdfTemplate templateManager = new Help4DevsEasyPdfTemplate();
        templateManager.easyPdfTemplateBox(easyPdfTemplateSettingsLetter());

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long usedMemory = freeMemoryAfter - freeMemoryBefore;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void easyPdfTemplateBoxA4Sample1Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        Help4DevsEasyPdfTemplate templateManager = new Help4DevsEasyPdfTemplate();
        templateManager.easyPdfTemplateBox(easyPdfTemplateSettingsA4());

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long usedMemory = freeMemoryAfter - freeMemoryBefore;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

}
