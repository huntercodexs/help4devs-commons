package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.*;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplate;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.BoxOpenDataContent;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.BoxOpenTemplateSettings;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMegabytes;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates.template;

public class Help4DevsPdfBoxTemplateBoxOpenUnitaryTests extends Help4DevsBridgeTests {

    private final static String pdfFilenameLetter = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-box-open-LETTER.pdf";
    private final static String pdfFilenameLetterLayout = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-box-open-LETTER-LAYOUT.pdf";
    private final static String pdfFilenameA4 = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-box-open-A4.pdf";
    private final static String pdfFilenameA4Layout = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-box-open-A4-LAYOUT.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/background/pdfbox-background-sample-3.jpg";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private PdfBoxDocument documentSettings(PageSizeToPdfBox pageType) {
        PdfBoxDocument settings = new PdfBoxDocument();
        settings.setStartPage(1);
        settings.setEndPage(1);
        settings.setNumberOfPages(1);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Document Test");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setOwnerPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setProtectionLevel(ProtectionLevelToPdfBox.HIGH);

        if (pageType.equals(PageSizeToPdfBox.LETTER)) {
            settings.setFilenamePath(pdfFilenameLetter);
        } else if (pageType.equals(PageSizeToPdfBox.A4)) {
            settings.setFilenamePath(pdfFilenameA4);
        } else if (pageType.equals(PageSizeToPdfBox.LETTER_LAYOUT)) {
            settings.setFilenamePath(pdfFilenameLetterLayout);
        } else if (pageType.equals(PageSizeToPdfBox.A4_LAYOUT)) {
            settings.setFilenamePath(pdfFilenameA4Layout);
        }

        return settings;
    }

    private PdfBoxPage pageSettings(PageSizeToPdfBox pageType) {
        PdfBoxPage settings = new PdfBoxPage();
        settings.setWidth(842);
        settings.setHeight(595);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(pageType);
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.SMALL);
        settings.setFontColor(ColorsToPdfBox.BLACK);
        settings.setPageColor(ColorsToPdfBox.WHITE);
        settings.setTextContent(null);
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxContainer containerSettings() {
        PdfBoxContainer settings = new PdfBoxContainer();
        settings.setWidth(570);
        settings.setHeight(780);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBorderWidth(1);
        settings.setBackColor(ColorsToPdfBox.WHITE);
        settings.setBorderColor(ColorsToPdfBox.GRAY);
        return settings;
    }

    private PdfBoxTable tableSettings() {
        PdfBoxTable settings = new PdfBoxTable();
        settings.setWidth(540);
        settings.setHeight(90);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToPdfBox.BLACK);
        settings.setCelColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        settings.setTableTemplate(TableDimensionsToPdfBox.TABLE_5X6);
        return settings;
    }

    private PdfBoxText textSettings() {
        PdfBoxText settings = new PdfBoxText();
        settings.setWidth(0);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setLetterSpace(1);
        settings.setBold(false);
        settings.setItalic(false);
        settings.setUnderline(false);
        settings.setFontName(FontNameToPdfBox.HELVETICA);
        settings.setFontSize(FontSizeToPdfBox.NORMAL);
        return settings;
    }

    private PdfBoxImage imageSettings() {
        PdfBoxImage settings = new PdfBoxImage();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setFilenamePath(imgJava);
        settings.setImageType(ImageTypeToPdfBox.JPEG);
        settings.setImageQuality(ImageQualityToPdfBox.GOOD);
        return settings;
    }

    private PdfBoxBarcode barcodeSettings() {
        PdfBoxBarcode settings = new PdfBoxBarcode();
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
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.CODE128);
        return settings;
    }

    private PdfBoxQrCode qrCodeSettings() {
        PdfBoxQrCode settings = new PdfBoxQrCode();
        settings.setDpi(400);
        settings.setMargin(0);
        settings.setMatrix(100);
        settings.setSize(100);
        settings.setOnColor(0xFF000001);
        settings.setOffColor(0xFFFFFFFF);
        settings.setOffsetX(470); //40,260,470
        settings.setOffsetY(348); //655,502,348,193,38
        settings.setData("https://huntercodexs.com");
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.QRCODE);
        return settings;
    }

    private BoxOpenTemplateSettings boxOpenSettings_LETTER_LAYOUT() {
        BoxOpenTemplateSettings settings = new BoxOpenTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.NONE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        return settings;
    }

    private BoxOpenTemplateSettings boxOpenSettings_LETTER() {
        BoxOpenTemplateSettings settings = new BoxOpenTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.NONE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        return settings;
    }

    private BoxOpenTemplateSettings boxOpenSettings_A4_LAYOUT() {
        BoxOpenTemplateSettings settings = new BoxOpenTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.NONE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        return settings;
    }

    private BoxOpenTemplateSettings boxOpenSettings_A4() {
        BoxOpenTemplateSettings settings = new BoxOpenTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,620,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.NONE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        return settings;
    }

    private BoxOpenDataContent boxOpenData() {
        BoxOpenDataContent settings = new BoxOpenDataContent();
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_LETTER_LAYOUT() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.LETTER_LAYOUT;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.BOX_OPEN);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBoxOpen(boxOpenSettings_LETTER_LAYOUT());
        settings.setBoxOpenContent(boxOpenData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_LETTER() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.LETTER;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.BOX_OPEN);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBoxOpen(boxOpenSettings_LETTER());
        settings.setBoxOpenContent(boxOpenData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_A4_LAYOUT() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.A4_LAYOUT;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.BOX_OPEN);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBoxOpen(boxOpenSettings_A4_LAYOUT());
        settings.setBoxOpenContent(boxOpenData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_A4() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.A4;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.BOX_OPEN);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setBoxOpen(boxOpenSettings_A4());
        settings.setBoxOpenContent(boxOpenData());
        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(PdfBoxTemplates.BOX_OPEN);
        codexsTesterAssertExact("BOX_OPEN", template);
    }

    @Test
    public void pdfBoxTemplateBoxOpen_LETTER_LAYOUT_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateBoxOpen(pdfBoxTemplateSettings_LETTER_LAYOUT());

        long freeMemoryAfter = rt.freeMemory();
        long usedMemory = freeMemoryBefore - freeMemoryAfter;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void pdfBoxTemplateBoxOpen_LETTER_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateBoxOpen(pdfBoxTemplateSettings_LETTER());

        long freeMemoryAfter = rt.freeMemory();
        long usedMemory = freeMemoryBefore - freeMemoryAfter;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void pdfBoxTemplateBoxOpen_A4_LAYOUT_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateBoxOpen(pdfBoxTemplateSettings_A4_LAYOUT());

        long freeMemoryAfter = rt.freeMemory();
        long usedMemory = freeMemoryBefore - freeMemoryAfter;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

    @Test
    public void pdfBoxTemplateBoxOpen_A4_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateBoxOpen(pdfBoxTemplateSettings_A4());

        long freeMemoryAfter = rt.freeMemory();
        long usedMemory = freeMemoryBefore - freeMemoryAfter;

        System.out.println("------------------------------------------------");
        System.out.println("- Memory Usage -");
        System.out.println("------------------------------------------------");
        System.out.println("Total: " + calculateMegabytes(totalMemory) + " ("+totalMemory+")");
        System.out.println("Before: " + calculateMegabytes(freeMemoryBefore) + " ("+freeMemoryBefore+")");
        System.out.println("After: " + calculateMegabytes(freeMemoryAfter) + " ("+freeMemoryAfter+")");
        System.out.println("Used: " + calculateMegabytes(usedMemory) + " ("+usedMemory+")");
    }

}
