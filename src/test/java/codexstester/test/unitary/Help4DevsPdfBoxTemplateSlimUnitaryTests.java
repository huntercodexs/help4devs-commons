package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.*;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplate;
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
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates.template;

public class Help4DevsPdfBoxTemplateSlimUnitaryTests extends Help4DevsBridgeTests {

    private final static String pdfFilenameLetter = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim-LETTER.pdf";
    private final static String pdfFilenameLetterLayout = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim-LETTER-LAYOUT.pdf";
    private final static String pdfFilenameA4 = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim-A4.pdf";
    private final static String pdfFilenameA4Layout = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim-A4-LAYOUT.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/background/pdfbox-background-sample-5.jpg";
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

    private SlimTemplateSettings slimSettings_LETTER_LAYOUT() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,570,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,0,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{true, true, true, true, true});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});

        /*Column*/
        boolean columnLeftOn = false;
        boolean columnCenterOn = false;
        boolean columnRightOn = false;
        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn});
        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn});
        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,columnRightOn,columnRightOn,columnRightOn,columnRightOn});

        /*Table*/
        boolean tableOn = false;
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});

        /*Image*/
        boolean imageLeftOn = false;
        boolean imageCenterOn = false;
        boolean imageRightOn = false;
        settings.setLeftImageEnable(new boolean[]{imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});

        /*Signature Box*/
        boolean signatureOn = false;
        settings.setLeftSignatureBoxEnable(signatureOn);
        settings.setCenterSignatureBoxEnable(signatureOn);
        settings.setRightSignatureBoxEnable(signatureOn);

        /*Signature Tape*/
        settings.setSignatureTapeEnable(false);

        /*Text Content*/
        boolean textOn = false;
        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});

        /*Barcode Content*/
        boolean barcodeOn = false;
        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,barcodeOn});

        /*QRCode Content*/
        boolean qrCodeLeftOn = false;
        boolean qrCodeCenterOn = false;
        boolean qrCodeRightOn = false;
        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
        settings.setQrCodeRightEnable(new boolean[]{qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});

        return settings;
    }

    private SlimTemplateSettings slimSettings_LETTER() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

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

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleAdjustmentX(0);
        settings.setLeftTitleAdjustmentY(-90);
        settings.setCenterTitleAdjustmentX(0);
        settings.setCenterTitleAdjustmentY(0);
        settings.setRightTitleAdjustmentX(0);
        settings.setRightTitleAdjustmentY(0);
        settings.setLeftTitleEnable(new boolean[]{true,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setLeftTitleColor(ColorsToPdfBox.GRAY);
        settings.setCenterTitleColor(ColorsToPdfBox.GRAY);
        settings.setRightTitleColor(ColorsToPdfBox.RED_DARK);
        settings.setLeftTitleSize(FontSizeToPdfBox.LARGE);
        settings.setCenterTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setRightTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setLeftTitleFont(FontNameToPdfBox.HELVETICA_B);
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
        settings.setColumnBoxLeftBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxCenterBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxRightBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxLeftLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxCenterLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxRightLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxLeftAdjustmentX(new int[]{10,10,10,10,10});
        settings.setColumnBoxLeftAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,true,columnLeftOn,columnLeftOn,columnLeftOn});
        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,true,columnCenterOn,columnCenterOn,columnCenterOn});
        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,true,columnRightOn,columnRightOn,columnRightOn});
        settings.setColumnBoxLeftBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxCenterBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxRightBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxLeftBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_LIGHT,
                ColorsToPdfBox.RED,
                ColorsToPdfBox.GREEN,
                ColorsToPdfBox.BLUE
        });
        settings.setColumnBoxCenterBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.RED_BRIGHT,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.PURPLE
        });
        settings.setColumnBoxRightBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.MAGENTA,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.GREEN_SEA,
                ColorsToPdfBox.BLUE_SAD,
                ColorsToPdfBox.GOLD_DARK
        });
        settings.setColumnBoxLeftBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxCenterBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxRightBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.BLACK
        });
        settings.setColumnBoxLeftTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.WHITE
        });
        settings.setColumnBoxCenterTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.CYAN
        });
        settings.setColumnBoxRightTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_DARK,
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
        settings.setTableHeaderColor(ColorsToPdfBox.BLUE_SEA);
        settings.setTableBodyColor(ColorsToPdfBox.ICE);
        settings.setTableBorderColor(ColorsToPdfBox.WHITE);
        settings.setTableHeaderFontColor(ColorsToPdfBox.WHITE);
        settings.setTableBodyFontColor(ColorsToPdfBox.GRAY);
        settings.setTableHeaderFontSize(FontSizeToPdfBox.NORMAL);
        settings.setTableBodyFontSize(FontSizeToPdfBox.SMALL);
        settings.setTableHeaderFontName(FontNameToPdfBox.HELVETICA_B);
        settings.setTableBodyFontName(FontNameToPdfBox.HELVETICA);
        settings.setTableSize(TableDimensionsToPdfBox.TABLE_5X6);
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,true,tableOn});

        /*Image*/
        boolean imageLeftOn = false;
        boolean imageCenterOn = false;
        boolean imageRightOn = false;
        settings.setImageWidth(180);
        settings.setImageHeight(70);
        settings.setImageAdjustOffsetX(0);
        settings.setImageAdjustOffsetY(20);
        settings.setImageOffsetX(new int[]{35,215,395});
        settings.setImageOffsetY(new int[]{670,515,360,205,55});
        settings.setLeftImageEnable(new boolean[]{true,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});

        /*Signature Box*/
        boolean signatureOn = false;
        settings.setSignatureBoxWidth(200);
        settings.setSignatureBoxHeight(100);
        settings.setSignatureBoxAdjustOffsetX(15);
        settings.setSignatureBoxOffsetX(new int[]{55,210,355});
        settings.setSignatureBoxOffsetY(new int[]{35,122,100});
        settings.setSignatureBoxDigitalTitleOffsetX(new int[]{105,260,405});
        settings.setSignatureBoxContentOffsetX(new int[]{70,230,370});
        settings.setSignatureBoxBorderEnable(true);
        settings.setLeftSignatureBoxEnable(signatureOn);
        settings.setCenterSignatureBoxEnable(signatureOn);
        settings.setRightSignatureBoxEnable(true);
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
        settings.setTextEnable(new boolean[]{textOn,textOn,true,textOn,textOn});
        settings.setTextColor(ColorsToPdfBox.GRAY);
        settings.setTextSize(FontSizeToPdfBox.NORMAL);
        settings.setTextFont(FontNameToPdfBox.HELVETICA);

        /*Barcode Content*/
        boolean barcodeOn = false;
        settings.setBarcodeDpi(400);
        settings.setBarcodeWidth(300);
        settings.setBarcodeHeight(30);
        settings.setBarcodeAdjustOffsetX(-10);
        settings.setBarcodeAdjustOffsetY(0);
        settings.setBarcodeOffsetY(new int[]{655,500,345,190,35});
        settings.setBarcodeInfoOffsetY(new int[]{750, 595, 440, 285, 130});
        settings.setBarcodeValueOffsetY(new int[]{690, 535, 380, 225, 70});
        settings.setBarcodeAmountOffsetY(new int[]{745, 590, 435, 280, 125});
        settings.setBarcodeShowText(false);
        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,true});

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
        settings.setQrCodeRightEnable(new boolean[]{true,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});

        return settings;
    }

    private SlimTemplateSettings slimSettings_A4_LAYOUT() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(true);
        settings.setBoxWidth(new int[]{570,570,570,570,570});
        settings.setBoxAdjustOffsetX(new int[]{0,0,0,0,0});
        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
        settings.setBoxBorderEnabled(new boolean[]{true, true, true, true, true});
        settings.setBoxBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.WHITE
        });

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});

        /*Column*/
        boolean columnLeftOn = false;
        boolean columnCenterOn = false;
        boolean columnRightOn = false;
        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn});
        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn});
        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,columnRightOn,columnRightOn,columnRightOn,columnRightOn});

        /*Table*/
        boolean tableOn = false;
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});

        /*Image*/
        boolean imageLeftOn = false;
        boolean imageCenterOn = false;
        boolean imageRightOn = false;
        settings.setLeftImageEnable(new boolean[]{imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});

        /*Signature Box*/
        boolean signatureOn = false;
        settings.setLeftSignatureBoxEnable(signatureOn);
        settings.setCenterSignatureBoxEnable(signatureOn);
        settings.setRightSignatureBoxEnable(signatureOn);

        /*Signature Tape*/
        settings.setSignatureTapeEnable(false);

        /*Text Content*/
        boolean textOn = false;
        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});

        /*Barcode Content*/
        boolean barcodeOn = false;
        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,barcodeOn});

        /*QRCode Content*/
        boolean qrCodeLeftOn = false;
        boolean qrCodeCenterOn = false;
        boolean qrCodeRightOn = false;
        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
        settings.setQrCodeRightEnable(new boolean[]{qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});

        return settings;
    }

    private SlimTemplateSettings slimSettings_A4() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

        //General
        settings.setTemplateTitleEnabled(false);
        settings.setBoxWidth(new int[]{570,700,570,570,570});
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

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleAdjustmentX(0);
        settings.setLeftTitleAdjustmentY(-70);
        settings.setCenterTitleAdjustmentX(0);
        settings.setCenterTitleAdjustmentY(0);
        settings.setRightTitleAdjustmentX(0);
        settings.setRightTitleAdjustmentY(0);
        settings.setLeftTitleEnable(new boolean[]{true,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setLeftTitleColor(ColorsToPdfBox.GRAY);
        settings.setCenterTitleColor(ColorsToPdfBox.GRAY);
        settings.setRightTitleColor(ColorsToPdfBox.RED_DARK);
        settings.setLeftTitleSize(FontSizeToPdfBox.LARGE);
        settings.setCenterTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setRightTitleSize(FontSizeToPdfBox.MEDIUM);
        settings.setLeftTitleFont(FontNameToPdfBox.HELVETICA_B);
        settings.setCenterTitleFont(FontNameToPdfBox.COURIER_B);
        settings.setRightTitleFont(FontNameToPdfBox.HELVETICA_B);

        /*Column*/
        boolean columnLeftOn = false;
        boolean columnCenterOn = false;
        boolean columnRightOn = false;
        settings.setColumnBoxWidth(165);
        settings.setColumnBoxHeight(90);
        settings.setColumnBoxChars(25);
        settings.setColumnBoxOffsetX(new int[]{35,215,395});
        settings.setColumnBoxOffsetY(new int[]{655,524,345,190,35});
        settings.setColumnBoxLeftPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxCenterPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxRightPadding(new int[]{5,5,5,5,5});
        settings.setColumnBoxLeftBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxCenterBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxRightBorderWidth(new int[] {1,1,1,1,1});
        settings.setColumnBoxLeftLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxCenterLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxRightLineHeight(new int[] {14,14,14,14,14});
        settings.setColumnBoxLeftAdjustmentX(new int[]{10,10,10,10,10});
        settings.setColumnBoxLeftAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxCenterAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentX(new int[]{0,0,0,0,0});
        settings.setColumnBoxRightAdjustmentY(new int[]{0,0,0,0,0});
        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,true,columnLeftOn,columnLeftOn,columnLeftOn});
        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,true,columnCenterOn,columnCenterOn,columnCenterOn});
        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,true,columnRightOn,columnRightOn,columnRightOn});
        settings.setColumnBoxLeftBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxCenterBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxRightBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnBoxLeftBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_LIGHT,
                ColorsToPdfBox.RED,
                ColorsToPdfBox.GREEN,
                ColorsToPdfBox.BLUE
        });
        settings.setColumnBoxCenterBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.RED_BRIGHT,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.PURPLE
        });
        settings.setColumnBoxRightBackColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.MAGENTA,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.GREEN_SEA,
                ColorsToPdfBox.BLUE_SAD,
                ColorsToPdfBox.GOLD_DARK
        });
        settings.setColumnBoxLeftBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxCenterBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnBoxRightBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.BLACK
        });
        settings.setColumnBoxLeftTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.WHITE
        });
        settings.setColumnBoxCenterTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_DARK,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.CYAN
        });
        settings.setColumnBoxRightTextColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.WHITE,
                ColorsToPdfBox.RED_DARK,
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
        settings.setTableWidth(528);
        settings.setTableHeight(90);
        settings.setTableOffsetX(34);
        settings.setTableColumnWidth(90);
        settings.setTableColumnHeight(18);
        settings.setTableHeaderHeight(30);
        settings.setTableHeaderAdjustOffsetX(18);
        settings.setTableHeaderAdjustOffsetY(30);
        settings.setTableBodyAdjustOffsetX(3);
        settings.setTableBodyAdjustOffsetY(5);
        settings.setTableContainerOffsetY(new int[]{656, 500, 346, 214, 35});
        settings.setTableHeaderOffsetY(new int[]{728, 572, 418, 262, 107});
        settings.setTableColumnOffsetX(new int[] {35,125,215,305,395,485});
        settings.setTableDataOffsetY(new int[]{710, 554, 400, 244, 89});
        settings.setTableHeaderColor(ColorsToPdfBox.BLUE_SEA);
        settings.setTableBodyColor(ColorsToPdfBox.ICE);
        settings.setTableBorderColor(ColorsToPdfBox.WHITE);
        settings.setTableHeaderFontColor(ColorsToPdfBox.WHITE);
        settings.setTableBodyFontColor(ColorsToPdfBox.GRAY);
        settings.setTableHeaderFontSize(FontSizeToPdfBox.NORMAL);
        settings.setTableBodyFontSize(FontSizeToPdfBox.SMALL);
        settings.setTableHeaderFontName(FontNameToPdfBox.HELVETICA_B);
        settings.setTableBodyFontName(FontNameToPdfBox.HELVETICA);
        settings.setTableSize(TableDimensionsToPdfBox.TABLE_5X6);
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,true,tableOn});

        /*Image*/
        boolean imageLeftOn = false;
        boolean imageCenterOn = false;
        boolean imageRightOn = false;
        settings.setImageWidth(180);
        settings.setImageHeight(70);
        settings.setImageAdjustOffsetX(0);
        settings.setImageAdjustOffsetY(44);
        settings.setImageOffsetX(new int[]{35,215,395});
        settings.setImageOffsetY(new int[]{670,515,360,205,55});
        settings.setLeftImageEnable(new boolean[]{true,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});

        /*Signature Box*/
        boolean signatureOn = false;
        settings.setSignatureBoxWidth(200);
        settings.setSignatureBoxHeight(100);
        settings.setSignatureBoxAdjustOffsetX(10);
        settings.setSignatureBoxOffsetX(new int[]{55,210,355});
        settings.setSignatureBoxOffsetY(new int[]{59,146,124});
        settings.setSignatureBoxDigitalTitleOffsetX(new int[]{105,260,405});
        settings.setSignatureBoxContentOffsetX(new int[]{70,230,370});
        settings.setSignatureBoxBorderEnable(true);
        settings.setLeftSignatureBoxEnable(signatureOn);
        settings.setCenterSignatureBoxEnable(signatureOn);
        settings.setRightSignatureBoxEnable(true);
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
        settings.setTextOffsetY(new int[]{732,577,445,266,111});
        settings.setTextEnable(new boolean[]{textOn,textOn,true,textOn,textOn});
        settings.setTextColor(ColorsToPdfBox.GRAY);
        settings.setTextSize(FontSizeToPdfBox.NORMAL);
        settings.setTextFont(FontNameToPdfBox.HELVETICA);

        /*Barcode Content*/
        boolean barcodeOn = false;
        settings.setBarcodeDpi(400);
        settings.setBarcodeWidth(300);
        settings.setBarcodeHeight(30);
        settings.setBarcodeAdjustOffsetX(-15);
        settings.setBarcodeAdjustOffsetY(24);
        settings.setBarcodeOffsetY(new int[]{655,500,345,190,35});
        settings.setBarcodeInfoOffsetY(new int[]{750, 595, 440, 285, 130});
        settings.setBarcodeValueOffsetY(new int[]{690, 535, 380, 225, 70});
        settings.setBarcodeAmountOffsetY(new int[]{745, 590, 435, 280, 125});
        settings.setBarcodeShowText(false);
        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,true});

        /*QRCode Content*/
        boolean qrCodeLeftOn = false;
        boolean qrCodeCenterOn = false;
        boolean qrCodeRightOn = false;
        settings.setQrCodeDpi(400);
        settings.setQrCodeWidth(500);
        settings.setQrCodeHeight(50);
        settings.setQrCodeAdjustOffsetX(-10);
        settings.setQrCodeAdjustOffsetY(24);
        settings.setQrCodeOffsetX(new int[]{40,260,470});
        settings.setQrCodeOffsetY(new int[]{655,502,348,193,38});
        settings.setQrCodeInfoOffsetX(new int[]{145, 365, 365});
        settings.setQrCodeInfoOffsetY(new int[]{745, 595, 440, 285, 130});
        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
        settings.setQrCodeRightEnable(new boolean[]{true,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});

        return settings;
    }

    private SlimDataContent slimData() {
        SlimDataContent settings = new SlimDataContent();

        /*Title*/
        settings.setLeftTitleContent("My Sample Slim Template");
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
                "./src/test/resources/help4devs/images/ads/logo-tester.png",
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
            qrCodeOneMap.put(i, "John Smith Viz");
            qrCodeTwoMap.put(i, "Street 123 - FL");
            qrCodeThreeMap.put(i, "Pay for it easily");
            qrCodeFourMap.put(i, "huntercodexs.com");
            qrCodeAmountMap.put(i, "R$ 1000,00");
        }
        settings.setQrCodeValue(qrCodeValueMap);
        settings.setQrCodeInfoOne(qrCodeOneMap);
        settings.setQrCodeInfoTwo(qrCodeTwoMap);
        settings.setQrCodeInfoThree(qrCodeThreeMap);
        settings.setQrCodeInfoFour(qrCodeFourMap);
        settings.setQrCodeAmount(qrCodeAmountMap);

        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_LETTER_LAYOUT() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.LETTER_LAYOUT;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setSlim(slimSettings_LETTER_LAYOUT());
        settings.setSlimContent(slimData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_LETTER() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.LETTER;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setSlim(slimSettings_LETTER());
        settings.setSlimContent(slimData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_A4() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.A4;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setSlim(slimSettings_A4());
        settings.setSlimContent(slimData());
        return settings;
    }

    private PdfBoxTemplateSettings pdfBoxTemplateSettings_A4_LAYOUT() {
        PageSizeToPdfBox pageType = PageSizeToPdfBox.A4_LAYOUT;
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings(pageType));
        settings.setPage(pageSettings(pageType));
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setBarcode(barcodeSettings());
        settings.setQrCode(qrCodeSettings());
        settings.setSlim(slimSettings_A4_LAYOUT());
        settings.setSlimContent(slimData());
        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(PdfBoxTemplates.SLIM);
        codexsTesterAssertExact("SLIM", template);
    }

    @Test
    public void pdfBoxTemplateSlim_LETTER_LAYOUT_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateSlim(pdfBoxTemplateSettings_LETTER_LAYOUT());

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
    public void pdfBoxTemplateSlim_LETTER_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateSlim(pdfBoxTemplateSettings_LETTER());

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
    public void pdfBoxTemplateSlim_A4_LAYOUT_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateSlim(pdfBoxTemplateSettings_A4_LAYOUT());

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
    public void pdfBoxTemplateSlim_A4_Test() {
        Runtime rt = Runtime.getRuntime();

        long totalMemory = rt.totalMemory();
        long freeMemoryBefore = rt.freeMemory();

        Help4DevsPdfBoxTemplate templateManager = new Help4DevsPdfBoxTemplate();
        templateManager.pdfBoxTemplateSlim(pdfBoxTemplateSettings_A4());

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
