package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.*;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.SlimDataContent;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.SlimTemplateSettings;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMegabytes;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplate.pdfBoxTemplateSlim;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates.template;

public class Help4DevsPdfBoxTemplateSlimUnitaryTests extends Help4DevsBridgeTests {

    private final static String pdfFilename = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/ads/pdfbox-background-sample-2.jpg";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private PdfBoxDocument documentSettings() {
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
        settings.setFilenamePath(pdfFilename);
        return settings;
    }

    private PdfBoxPage pageSettings() {
        PdfBoxPage settings = new PdfBoxPage();
        settings.setWidth(842);
        settings.setHeight(595);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(PageSizeToPdfBox.LETTER);
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

    private SlimTemplateSettings slimSettings() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

        settings.setTemplateTitleEnabled(true);

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleAdjustmentX(0);
        settings.setLeftTitleAdjustmentY(0);
        settings.setCenterTitleAdjustmentX(-31);
        settings.setCenterTitleAdjustmentY(0);
        settings.setRightTitleAdjustmentX(0);
        settings.setRightTitleAdjustmentY(0);
        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setLeftTitleColor(ColorsToPdfBox.GREEN2);
        settings.setCenterTitleColor(ColorsToPdfBox.GRAY);
        settings.setRightTitleColor(ColorsToPdfBox.RED2);
        settings.setLeftTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setCenterTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setRightTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setLeftTitleFont(FontNameToPdfBox.TIMES_B);
        settings.setCenterTitleFont(FontNameToPdfBox.COURIER_B);
        settings.setRightTitleFont(FontNameToPdfBox.HELVETICA_B);

        /*Column*/
        boolean columnLeftOn = false;
        boolean columnCenterOn = false;
        boolean columnRightOn = false;
        settings.setColumnBoxWidth(170);
        settings.setColumnBoxHeight(90);
        settings.setColumnBoxChars(25);
        settings.setColumnBoxOffsetX(new int[]{35,220,405});
        settings.setColumnBoxOffsetY(new int[]{655,500,345,190,35});
        settings.setColumnBoxLeftPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxCenterPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxRightPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxLeftBorderWidth(new int[] {3,3,3,3,3});
        settings.setColumnBoxCenterBorderWidth(new int[] {6,6,6,6,6});
        settings.setColumnBoxRightBorderWidth(new int[] {9,9,9,9,9});
        settings.setColumnBoxLeftLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxCenterLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxRightLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxLeftAdjustmentX(new int[]{10,10,10,10,10});
        settings.setColumnBoxLeftAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn});
        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn});
        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,columnRightOn,columnRightOn,columnRightOn,columnRightOn});
        settings.setColumnBoxLeftBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxCenterBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxRightBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxLeftBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED,
                ColorsToPdfBox.GREEN,
                ColorsToPdfBox.BLUE
        });
        settings.setColumnBoxCenterBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.ORANGE,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.PURPLE
        });
        settings.setColumnBoxRightBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.MAGENTA,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.BLUE3,
                ColorsToPdfBox.GOLD2
        });
        settings.setColumnBoxLeftBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxCenterBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxRightBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.BLACK
        });
        settings.setColumnBoxLeftTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.WHITE
        });
        settings.setColumnBoxCenterTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.CYAN
        });
        settings.setColumnBoxRightTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.BLACK
        });
        settings.setColumnBoxLeftFontSize(new FontSizeToPdfBox[]{
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL
        });
        settings.setColumnBoxCenterFontSize(new FontSizeToPdfBox[]{
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL
        });
        settings.setColumnBoxRightFontSize(new FontSizeToPdfBox[]{
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL,
                FontSizeToPdfBox.NORMAL
        });
        settings.setColumnBoxLeftFontName(new FontNameToPdfBox[]{
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA
        });
        settings.setColumnBoxCenterFontName(new FontNameToPdfBox[]{
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA
        });
        settings.setColumnBoxRightFontName(new FontNameToPdfBox[]{
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA,
                FontNameToPdfBox.HELVETICA
        });

        /*Table*/
        boolean tableOn = false;
        settings.setTableWidth(540);
        settings.setTableHeight(90);
        settings.setTableOffsetX(35);
        settings.setTableColumnWidth(90);
        settings.setTableColumnHeight(18);
        settings.setTableHeaderHeight(30);
        settings.setTableHeaderAdjustOffsetX(20);
        settings.setTableHeaderAdjustOffsetY(30);
        settings.setTableBodyAdjustOffsetX(3);
        settings.setTableBodyAdjustOffsetY(5);
        settings.setTableContainerOffsetY(new int[]{656, 500, 346, 190, 35});
        settings.setTableHeaderOffsetY(new int[]{728, 572, 418, 262, 107});
        settings.setTableColumnOffsetX(new int[] {35,125,215,305,395,485});
        settings.setTableDataOffsetY(new int[]{710, 554, 400, 244, 89});
        settings.setTableHeaderColor(ColorsToPdfBox.GRAY);
        settings.setTableBodyColor(ColorsToPdfBox.ICE);
        settings.setTableBorderColor(ColorsToPdfBox.WHITE);
        settings.setTableHeaderFontColor(ColorsToPdfBox.WHITE);
        settings.setTableBodyFontColor(ColorsToPdfBox.GRAY);
        settings.setTableHeaderFontSize(FontSizeToPdfBox.NORMAL);
        settings.setTableBodyFontSize(FontSizeToPdfBox.SMALL);
        settings.setTableHeaderFontName(FontNameToPdfBox.HELVETICA_B);
        settings.setTableBodyFontName(FontNameToPdfBox.HELVETICA);
        settings.setTableSize(TableDimensionsToPdfBox.TABLE_5X6);
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});

        /*Image*/
        boolean imageLeftOn = false;
        boolean imageCenterOn = false;
        boolean imageRightOn = false;
        settings.setImageWidth(180);
        settings.setImageHeight(70);
        settings.setImageOffsetX(new int[]{35,215,395});
        settings.setImageOffsetY(new int[]{670,515,360,205,55});
        settings.setLeftImageEnable(new boolean[]{imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});

        /*Signature Box*/
        boolean signatureOn = false;
        settings.setSignatureBoxWidth(200);
        settings.setSignatureBoxHeight(100);
        settings.setSignatureBoxAdjustOffsetX(0);
        settings.setSignatureBoxOffsetX(new int[]{55,210,355});
        settings.setSignatureBoxOffsetY(new int[]{35,122,100});
        settings.setSignatureBoxDigitalTitleOffsetX(new int[]{105,260,405});
        settings.setSignatureBoxContentOffsetX(new int[]{70,230,370});
        settings.setSignatureBoxBorderEnable(true);
        settings.setLeftSignatureBoxEnable(signatureOn);
        settings.setCenterSignatureBoxEnable(signatureOn);
        settings.setRightSignatureBoxEnable(signatureOn);
        settings.setSignatureFontSize(FontSizeToPdfBox.NORMAL);
        settings.setSignatureFontName(FontNameToPdfBox.HELVETICA_B);
        settings.setSignatureBoxColor(ColorsToPdfBox.GRAY);

        /*Signature Tape*/
        settings.setSignatureTapeWidth(500);
        settings.setSignatureTapeHeight(30);
        settings.setSignatureTapeOffsetX(55);
        settings.setSignatureTapeOffsetY(35);
        settings.setSignatureTapeTitleOffsetX(260);
        settings.setSignatureTapeTitleOffsetY(57);
        settings.setSignatureTapeValueOffsetX(130);
        settings.setSignatureTapeValueOffsetY(40);
        settings.setSignatureTapeAdjustOffsetX(10);
        settings.setSignatureTapeEnable(false);
        settings.setSignatureTapeFontSize(FontSizeToPdfBox.SMALL);
        settings.setSignatureTapeFontName(FontNameToPdfBox.HELVETICA_B);
        settings.setSignatureTapeColor(ColorsToPdfBox.GRAY);

        /*Text Content*/
        boolean textOn = false;
        settings.setLineHeight(18);
        settings.setTextOffsetX(35);
        settings.setTextChars(98);
        settings.setTextOffsetY(new int[]{732,577,421,266,111});
        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});
        settings.setTextColor(ColorsToPdfBox.GRAY);
        settings.setTextSize(FontSizeToPdfBox.NORMAL);
        settings.setTextFont(FontNameToPdfBox.HELVETICA);

        /*Barcode Content*/
        boolean barcodeOn = false;
        settings.setBarcodeDpi(400);
        settings.setBarcodeWidth(300);
        settings.setBarcodeHeight(30);
        settings.setBarcodeAdjustOffsetX(0);
        settings.setBarcodeAdjustOffsetY(0);
        settings.setBarcodeOffsetY(new int[]{655,500,345,190,35});
        settings.setBarcodeInfoOffsetY(new int[]{750, 595, 440, 285, 130});
        settings.setBarcodeValueOffsetY(new int[]{690, 535, 380, 225, 70});
        settings.setBarcodeAmountOffsetY(new int[]{745, 590, 435, 280, 135});
        settings.setBarcodeShowText(false);
        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,barcodeOn});

        /*QRCode Content*/
        boolean qrCodeLeftOn = false;
        boolean qrCodeCenterOn = false;
        boolean qrCodeRightOn = false;
        settings.setQrCodeDpi(400);
        settings.setQrCodeWidth(500);
        settings.setQrCodeHeight(50);
        settings.setQrCodeAdjustOffsetX(0);
        settings.setQrCodeAdjustOffsetY(0);
        settings.setQrCodeOffsetX(new int[]{40,260,470});
        settings.setQrCodeOffsetY(new int[]{655,502,348,193,38});
        settings.setQrCodeInfoOffsetX(new int[]{145, 365, 365});
        settings.setQrCodeInfoOffsetY(new int[]{745, 595, 440, 285, 130});
        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
        settings.setQrCodeRightEnable(new boolean[]{qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});

        return settings;
    }

    private SlimDataContent slimData() {
        SlimDataContent settings = new SlimDataContent();

        /*Title*/
        settings.setLeftTitleContent("Title of Section 1");
        settings.setCenterTitleContent("Title of Section 2");
        settings.setRightTitleContent("Title of Section 3");

        /*Column*/
        HashMap<Integer, String> columnContentMap = new HashMap<>();
        String lorenText =
                "There are many the Lorena\n" +
                "of passages of All lorem off\n" +
                "but the majority All the into\n" +
                "in some form, by All the off\n" +
                "randomised words a Lorem";
        for (int i = 0; i < 15; i++) {
            columnContentMap.put(i, (i+1)+".\n"+lorenText);
        }
        settings.setColumnContent(columnContentMap);

        /*Table*/
        List<String> tableHeaderContent = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tableHeaderContent.add("Header " + i);
        }
        HashMap<Integer, List<String>> tableHeaderMap = new HashMap<>();
        for (int j = 0; j < 5; j++) {
            tableHeaderMap.put(j, tableHeaderContent);
        }

        List<String> tableBodyContent = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            tableBodyContent.add("Data " + i);
        }
        HashMap<Integer, List<String>> tableBodyMap = new HashMap<>();
        for (int j = 0; j < 5; j++) {
            tableBodyMap.put(j, tableBodyContent);
        }

        settings.setTableHeaderContent(tableHeaderMap);
        settings.setTableBodyContent(tableBodyMap);

        /*Image*/
        settings.setLeftImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setCenterImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setRightImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });

        /*Signature*/
        settings.setSignaturePersonName("John Smith Mountain");
        settings.setSignaturePersonDoc("123456789011");
        settings.setSignatureRecord("9089739827389");
        settings.setSignatureDateGmt("2020.01.01 10:00:00 -03:00");

        /*Text*/
        HashMap<Integer, String> textContentMap = new HashMap<>();
        String textContent1 =
                "1. One in the simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
        String textContent2 =
                "2. Two more simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
        String textContent3 =
                "3. Three is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
        String textContent4 =
                "4. Four is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
        String textContent5 =
                "5. is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";

        textContentMap.put(0, textContent1);
        textContentMap.put(1, textContent2);
        textContentMap.put(2, textContent3);
        textContentMap.put(3, textContent4);
        textContentMap.put(4, textContent5);
        settings.setTextContent(textContentMap);

        /*Barcode*/
        HashMap<Integer, String> barcodeValueMap = new HashMap<>();
        HashMap<Integer, String> barcodeOneMap = new HashMap<>();
        HashMap<Integer, String> barcodeTwoMap = new HashMap<>();
        HashMap<Integer, String> barcodeThreeMap = new HashMap<>();
        HashMap<Integer, String> barcodeFourMap = new HashMap<>();
        HashMap<Integer, String> barcodeAmountMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            barcodeValueMap.put(i, i+"23456789123456789123456789123456789");
            barcodeOneMap.put(i, i + " John Smith Viz Owing");
            barcodeTwoMap.put(i, i + " Street Saint Luz Fright 200 ");
            barcodeThreeMap.put(i, i + " This is a message for the customer");
            barcodeFourMap.put(i, i + " Space reserved to operator");
            barcodeAmountMap.put(i, "R$ 1.000.00"+i+",00");
        }
        settings.setBarcodeValue(barcodeValueMap);
        settings.setBarcodeInfoOne(barcodeOneMap);
        settings.setBarcodeInfoTwo(barcodeTwoMap);
        settings.setBarcodeInfoThree(barcodeThreeMap);
        settings.setBarcodeInfoFour(barcodeFourMap);
        settings.setBarcodeInfoFour(barcodeFourMap);
        settings.setBarcodeAmount(barcodeAmountMap);

        /*QRCode*/
        HashMap<Integer, String> qrCodeValueMap = new HashMap<>();
        HashMap<Integer, String> qrCodeOneMap = new HashMap<>();
        HashMap<Integer, String> qrCodeTwoMap = new HashMap<>();
        HashMap<Integer, String> qrCodeThreeMap = new HashMap<>();
        HashMap<Integer, String> qrCodeFourMap = new HashMap<>();
        HashMap<Integer, String> qrCodeAmountMap = new HashMap<>();
        for (int i = 0; i < 15; i++) {
            qrCodeValueMap.put(i, "https://www.huntercodexs.com/"+(i+1));
            qrCodeOneMap.put(i, (i+1) + " One Info");
            qrCodeTwoMap.put(i, (i+1) + " Two Info");
            qrCodeThreeMap.put(i, (i+1) + " Three Info");
            qrCodeFourMap.put(i, (i+1) + " Four Info");
            qrCodeAmountMap.put(i, "R$ 10"+(i+1)+",00");
        }
        settings.setQrCodeValue(qrCodeValueMap);
        settings.setQrCodeInfoOne(qrCodeOneMap);
        settings.setQrCodeInfoTwo(qrCodeTwoMap);
        settings.setQrCodeInfoThree(qrCodeThreeMap);
        settings.setQrCodeInfoFour(qrCodeFourMap);
        settings.setQrCodeAmount(qrCodeAmountMap);

        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings() {
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings());
        settings.setPage(pageSettings());
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setSlim(slimSettings());
        settings.setSlimContent(slimData());
        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(PdfBoxTemplates.SLIM);
        codexsTesterAssertExact("SLIM", template);
    }

    @Test
    public void pdfBoxTemplateSlimTest() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        pdfBoxTemplateSlim(pdfBoxTemplateSettings());

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