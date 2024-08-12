package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.barcode.Help4DevsBarcodeScannerService.PdfBarcodeScannerResults;
import static com.huntercodexs.demo.services.file.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBox.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.CodeType4ScannerToPdfBox.codeType4Scanner;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontNameToPdfBox.fontName;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontSizeToPdfBox.fontSize;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ImageQualityToPdfBox.imageQuality;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ImageTypeToPdfBox.imageType;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PageSizeToPdfBox.pageSize;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ProtectionLevelToPdfBox.protectionLevel;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.QrCodeBorderStyleToPdfBox.qrCodeBorderStyle;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.TableDimensionsToPdfBox.tableSize;

public class Help4DevsPdfBoxUnitaryTests extends Help4DevsBridgeTests {

    private final static String PATH = "./src/test/resources/help4devs/files";
    private final static String FILEPATH_SOURCE = PATH+"/txt/file.txt";
    private final static String FILEPATH_TARGET = PATH+"/pdf/my-pdfbox-test.pdf";
    private final static String FILEPATH_TARGET_IMAGE = PATH+"/pdf/my-pdfbox-test-image.pdf";
    private final static String FILEPATH_TARGET_ADD_IMAGE = PATH+"/pdf/my-pdfbox-test-add-image.pdf";
    private final static String FILEPATH_TARGET_IMAGE_PASSWORD = PATH+"/pdf/my-pdfbox-test-image-password.pdf";
    private final static String FILEPATH_TARGET_MERGER = PATH+"/pdf/merger/my-pdfbox-test-merged.pdf";
    private final static String FILEPATH_TARGET_SCANNER = PATH+"/pdf/my-pdfbox-test-barcode.pdf";
    private final static String FILEPATH_TARGET_CONTAINER = PATH+"/pdf/my-pdfbox-test-container.pdf";
    private final static String FILEPATH_TARGET_BC_128 = PATH+"/pdf/my-pdfbox-test-bc128.pdf";
    private final static String FILEPATH_TARGET_BC_39 = PATH+"/pdf/my-pdfbox-test-bc39.pdf";
    private final static String FILEPATH_TARGET_PDF_417 = PATH+"/pdf/my-pdfbox-test-bc-pdf417.pdf";
    private final static String FILEPATH_TARGET_QRCODE = PATH+"/pdf/my-pdfbox-test-qrcode.pdf";
    private final static String FILEPATH_TARGET_BC = PATH+"/pdf/my-pdfbox-test-barcode-form.pdf";
    private final static String FILEPATH_TARGET_PASSWORD = PATH+"/pdf/my-pdfbox-test-password.pdf";
    private final static String IMAGE_PATH = "./src/test/resources/help4devs/images/ads/file.png";
    private final static String IMAGE_PATH_ADS = "./src/test/resources/help4devs/images/ads/img.png";
    private final static String USER_PASSWORD = "123456";
    private final static String OWNER_PASSWORD = "password";
    private final static String BARCODE_TEXT_VALUE = "03399.31339 03600.000008 74216.301015 7 96480000061000";

    private PdfBoxDocument documentSettings(String filenamePath) {
        PdfBoxDocument settings = new PdfBoxDocument();
        settings.setStartPage(1);
        settings.setEndPage(1);
        settings.setNumberOfPages(1);
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
        settings.setFilenamePath(filenamePath);
        return settings;
    }

    private PdfBoxPage pageSettings() {
        PdfBoxPage settings = new PdfBoxPage();
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
        settings.setTextContent(null);
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxContainer containerSettings() {
        PdfBoxContainer settings = new PdfBoxContainer();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(ColorsToPdfBox.BLUE_SAD);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        return settings;
    }

    private PdfBoxText textSettings() {
        PdfBoxText settings = new PdfBoxText();
        settings.setWidth(500);
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
        settings.setImageType(ImageTypeToPdfBox.JPEG);
        return settings;
    }

    private PdfBoxTable tableSettings() {
        PdfBoxTable settings = new PdfBoxTable();
        settings.setWidth(100);
        settings.setHeight(100);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToPdfBox.ORANGE);
        settings.setCelColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        settings.setTableTemplate(TableDimensionsToPdfBox.TABLE_5X6);
        return settings;
    }

    private PdfBoxBarcode barcode128Settings(String barcodeText) {
        PdfBoxBarcode settings = new PdfBoxBarcode();
        settings.setDpi(400);
        settings.setWidth(500);
        settings.setHeight(50);
        settings.setFontSize(2);
        settings.setLineHeight(20);
        settings.setOffsetX(55);
        settings.setOffsetY(100);
        settings.setFixQuiteZone(0);
        settings.setDoQuiteZone(false);
        settings.setData(barcodeText);
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.CODE128);
        return settings;
    }

    private PdfBoxBarcodeForm barcodeFormSettings() {

        PdfBoxBarcodeForm settings = new PdfBoxBarcodeForm();
        String barcodeValue = "03399.31339 03600.000008 74216.301015 7 96480000061000";

        settings.setAdjustOffsetX(0);
        settings.setAdjustOffsetY(0);
        settings.setQrcode(true);
        settings.setRevealFields(false);

        /*Fields Left*/
        settings.getFields().setFieldLeft1("Local de pagamento");
        settings.getFields().setFieldLeft2("Cedente");
        settings.getFields().setFieldLeft3("Data do documento");
        settings.getFields().setFieldLeft4("No. documento");
        settings.getFields().setFieldLeft5("Especie doc.");
        settings.getFields().setFieldLeft6("Data processamento");
        settings.getFields().setFieldLeft7("Uso do banco");
        settings.getFields().setFieldLeft8("Carteira");
        settings.getFields().setFieldLeft9("Quantidade");
        settings.getFields().setFieldLeft10("(x)Valor");
        settings.getFields().setFieldLeft11("Instruções (texto de responsabilidade do cedente)");
        settings.getFields().setFieldLeft12("qrcode");
        settings.getFields().setFieldLeft13("Sacado");

        /*Fields Right*/
        settings.getFields().setFieldRight1("Vencimento");
        settings.getFields().setFieldRight2("Agencia/Codigo Cedente");
        settings.getFields().setFieldRight3("Carteira/Nosso Numero");
        settings.getFields().setFieldRight4("(=)Valor documento");
        settings.getFields().setFieldRight5("(-)Desconto/Abatimentos");
        settings.getFields().setFieldRight6("(-)Outras deduções");
        settings.getFields().setFieldRight7("(+)Mora/Multa");
        settings.getFields().setFieldRight8("(+)Outros acresciscimos");
        settings.getFields().setFieldRight9("(=)Valor cobrado");
        settings.getFields().setFieldRight10("Cod. baixa");

        /*Fields Footer*/
        settings.getFields().setFieldFooter1("Sacador/Avalista");
        settings.getFields().setFieldFooter2("Autenticação mecanica - Ficha de Compensação");

        /*Data Header*/
        settings.getData().setDataHeaderImage("./src/test/resources/help4devs/images/ads/file.png");
        settings.getData().setDataHeaderOperator("1234-56");
        settings.getData().setDataHeaderBarcode(barcodeValue);

        /*Data Left*/
        settings.getData().setDataLeft1("PAGAVEL PREFERENCIALMENTE NAS AGENCIAS DO BANCO XYZ");
        settings.getData().setDataLeft2("NFE Associados e outros");
        settings.getData().setDataLeft3("12/01/2000");
        settings.getData().setDataLeft4("NF 1/1000");
        settings.getData().setDataLeft5("Test");
        settings.getData().setDataLeft6("14/10/1999");
        settings.getData().setDataLeft7("Test");
        settings.getData().setDataLeft8("008");
        settings.getData().setDataLeft9("Vinte milhoes de reais");
        settings.getData().setDataLeft10("R$ 2144,84");
        List<String> dataLeft11 = Arrays.asList(
                "Não receber apos o vencimento",
                "Boleto de teste 1 de 100 para referencia",
                "478234908840398 432894 09843290 001",
                "478234908840398 432894 09843290 002",
                "478234908840398 432894 09843290 003",
                "478234908840398 432894 09843290 004",
                "478234908840398 432894 09843290 005",
                "478234908840398 432894 09843290 006");
        settings.getData().setDataLeft11(dataLeft11);
        settings.getData().setDataLeft12(barcodeValue);
        List<String> dataLeft13 = Arrays.asList(
                "DISTRIBUIDORA DE AGUAS MINERAIS CPNJ 99.309.309/0001-12",
                "AV DAS FONTES DE AGUA 1888 10 ANDAR",
                "BAIRRO DAS FONTES ONDE TEM AGUA - CEP 123341230");
        settings.getData().setDataLeft13(dataLeft13);

        /*Data Right*/
        settings.getData().setDataRight1("30/12/2020");
        settings.getData().setDataRight2("123.123.123/0001-88");
        settings.getData().setDataRight3("0000000000001-22");
        settings.getData().setDataRight4("R$ 1693,95");
        settings.getData().setDataRight5("R$ 13,95");
        settings.getData().setDataRight6("R$ 24,95");
        settings.getData().setDataRight7("R$ 10,00");
        settings.getData().setDataRight8("R$ 16,00");
        settings.getData().setDataRight9("R$ 2144,84");
        settings.getData().setDataRight10("000001");

        /*Data Footer*/

        settings.setBorderStyle(QrCodeBorderStyleToPdfBox.LEFT_BORDERED);

        return settings;
    }

    private PdfBoxBarcode barcode39Settings() {
        PdfBoxBarcode settings = new PdfBoxBarcode();
        settings.setDpi(400);
        settings.setWidth(200);
        settings.setHeight(40);
        settings.setFontSize(4);
        settings.setLineHeight(20);
        settings.setOffsetX(200);
        settings.setOffsetY(100);
        settings.setFixQuiteZone(0);
        settings.setDoQuiteZone(false);
        settings.setData("123456789");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.CODE39);
        return settings;
    }

    private PdfBoxBarcode barcodePdf417Settings() {
        PdfBoxBarcode settings = new PdfBoxBarcode();
        settings.setDpi(400);
        settings.setWidth(200);
        settings.setHeight(40);
        settings.setFontSize(4);
        settings.setLineHeight(20);
        settings.setOffsetX(200);
        settings.setOffsetY(100);
        settings.setFixQuiteZone(0);
        settings.setDoQuiteZone(false);
        settings.setData("1234567890");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.PDF417);
        return settings;
    }

    private PdfBoxQrCode qrCodeSettings() {
        PdfBoxQrCode settings = new PdfBoxQrCode();
        settings.setDpi(400);
        settings.setMargin(0);
        settings.setMatrix(200);
        settings.setSize(200);
        settings.setOnColor(0xFF000001);
        settings.setOffColor(0xFFFFFFFF);
        settings.setOffsetX(200);
        settings.setOffsetY(100);
        settings.setData("https://huntercodexs.com");
        settings.setCodeType4Scanner(CodeType4ScannerToPdfBox.QRCODE);
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

        color = color(ColorsToPdfBox.GREEN_SEA);
        codexsTesterAssertExact("java.awt.Color[r=26,g=188,b=156]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE);
        codexsTesterAssertExact("java.awt.Color[r=0,g=0,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE_SEA);
        codexsTesterAssertExact("java.awt.Color[r=51,g=181,b=255]", String.valueOf(color));

        color = color(ColorsToPdfBox.BLUE_SAD);
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
        int[] tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_5X6);
        codexsTesterAssertExact("5,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_5X5);
        codexsTesterAssertExact("5,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_5X4);
        codexsTesterAssertExact("5,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_5X3);
        codexsTesterAssertExact("5,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_5X2);
        codexsTesterAssertExact("5,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_4X6);
        codexsTesterAssertExact("4,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_4X5);
        codexsTesterAssertExact("4,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_4X4);
        codexsTesterAssertExact("4,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_4X3);
        codexsTesterAssertExact("4,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_4X2);
        codexsTesterAssertExact("4,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_3X6);
        codexsTesterAssertExact("3,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_3X5);
        codexsTesterAssertExact("3,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_3X4);
        codexsTesterAssertExact("3,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_3X3);
        codexsTesterAssertExact("3,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_3X2);
        codexsTesterAssertExact("3,2", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_2X6);
        codexsTesterAssertExact("2,6", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_2X5);
        codexsTesterAssertExact("2,5", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_2X4);
        codexsTesterAssertExact("2,4", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_2X3);
        codexsTesterAssertExact("2,3", tableTemplate[0]+","+tableTemplate[1]);

        tableTemplate = tableSize(TableDimensionsToPdfBox.TABLE_2X2);
        codexsTesterAssertExact("2,2", tableTemplate[0]+","+tableTemplate[1]);
    }

    @Test
    public void codeType4ScannerTest() {
        String codeType4Scanner = codeType4Scanner(CodeType4ScannerToPdfBox.CODE128);
        codexsTesterAssertExact("CODE128", codeType4Scanner);

        codeType4Scanner = codeType4Scanner(CodeType4ScannerToPdfBox.CODE39);
        codexsTesterAssertExact("CODE39", codeType4Scanner);

        codeType4Scanner = codeType4Scanner(CodeType4ScannerToPdfBox.PDF417);
        codexsTesterAssertExact("PDF417", codeType4Scanner);

        codeType4Scanner = codeType4Scanner(CodeType4ScannerToPdfBox.QRCODE);
        codexsTesterAssertExact("QRCODE", codeType4Scanner);
    }

    @Test
    public void qrCodeBorderStyleTest() {
        String qrCodeBorderStyle = qrCodeBorderStyle(QrCodeBorderStyleToPdfBox.BORDERED);
        codexsTesterAssertExact("BORDERED", qrCodeBorderStyle);

        qrCodeBorderStyle = qrCodeBorderStyle(QrCodeBorderStyleToPdfBox.BORDERLESS);
        codexsTesterAssertExact("BORDERLESS", qrCodeBorderStyle);

        qrCodeBorderStyle = qrCodeBorderStyle(QrCodeBorderStyleToPdfBox.LEFT_BORDERED);
        codexsTesterAssertExact("LEFT_BORDERED", qrCodeBorderStyle);
    }

    @Test
    public void pdfCreateTest() throws IOException {
        String data = binFile(FILEPATH_SOURCE);

        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        PdfBoxPage pageSet = pageSettings();

        docSet.setNumberOfPages(3);

        for (int i = 1; i <= docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfCreateUsingPasswordTest() throws IOException {
        String data = binFile(FILEPATH_SOURCE);

        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        PdfBoxPage pageSet = pageSettings();

        docSet.setFilenamePath(FILEPATH_TARGET_PASSWORD);
        docSet.setNumberOfPages(3);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);

        for (int i = 1; i <= docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfAddImageTest() throws IOException {
        String data = binFile(FILEPATH_SOURCE);

        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_ADD_IMAGE);

        PdfBoxPage pageSet = pageSettings();
        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(IMAGE_PATH);
        pageSet.setTextContent("PAGE-1:\n" + data);

        PdfBoxImage imgSet = imageSettings();
        imgSet.setWidth(500);
        imgSet.setHeight(150);
        imgSet.setOffsetX(40);
        imgSet.setOffsetY(100);
        imgSet.setResize(false);

        pdfCreate(docSet, pageSet);
        pdfAddImage(docSet, pageSet, imgSet);
    }

    @Test
    public void pdfAddBarcode128Test() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_BC_128);
        PdfBoxPage pageSet = pageSettings();
        PdfBoxBarcode barcodeSet = barcode128Settings("03399.31339 03600.000008 74216.301015 7 96480000061000");

        pdfCreate(docSet, pageSet);

        pdfAddBarcode(docSet, pageSet, barcodeSet);
    }

    @Test
    public void pdfAddBarcode39Test() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_BC_39);
        PdfBoxPage pageSet = pageSettings();
        PdfBoxBarcode barcodeSet = barcode39Settings();

        pdfCreate(docSet, pageSet);

        pdfAddBarcode(docSet, pageSet, barcodeSet);
    }

    @Test
    public void pdfAddBarcodePdf417Test() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_PDF_417);
        PdfBoxPage pageSet = pageSettings();
        PdfBoxBarcode barcodeSet = barcodePdf417Settings();

        pdfCreate(docSet, pageSet);

        pdfAddBarcode(docSet, pageSet, barcodeSet);
    }

    @Test
    public void pdfAddQrCodeTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_QRCODE);
        PdfBoxPage pageSet = pageSettings();
        PdfBoxQrCode qrSet = qrCodeSettings();

        pdfCreate(docSet, pageSet);

        pdfAddQrCode(docSet, pageSet, qrSet);
    }

    @Test
    public void pdfAddFormTest() {
        /*TODO*/
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        PdfBoxPage pageSet = pageSettings();
        PdfBoxContainer rectSet = containerSettings();

        pageSet.setPageNumber(1);
        rectSet.setBackColor(ColorsToPdfBox.WHITE);
        rectSet.setBorderColor(ColorsToPdfBox.BLACK);

        pdfAddForm(docSet, pageSet, rectSet);
    }

    @Test
    public void pdfAddBarcodeFormTest() {

        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_BC);

        PdfBoxPage pageSet = pageSettings();
        pageSet.setPageNumber(1);

        PdfBoxBarcode bcSet = barcode128Settings(BARCODE_TEXT_VALUE);
        bcSet.setWidth(370);
        bcSet.setHeight(40);
        bcSet.setOffsetX(45);
        bcSet.setOffsetY(340);
        bcSet.setTextPosition(HumanReadablePlacement.HRP_NONE);

        PdfBoxBarcodeForm bcFormSet = barcodeFormSettings();
        bcFormSet.setBarcode(bcSet);

        pdfCreate(docSet, pageSet);
        pdfAddBarcodeForm(docSet, pageSet, bcFormSet);

    }

    @Test
    public void pdfAddContainerTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_CONTAINER);

        PdfBoxPage pageSet = pageSettings();
        pageSet.setPageNumber(1);

        PdfBoxContainer rectSet = containerSettings();
        rectSet.setBackColor(ColorsToPdfBox.ICE);
        rectSet.setBorderColor(ColorsToPdfBox.BLACK);

        pdfCreate(docSet, pageSet);
        pdfAddContainer(docSet, pageSet, rectSet);
    }

    @Test
    public void pdfReaderTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);

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
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);
        pdfProtect(docSet);
    }

    @Test
    public void pdfUnprotectTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);
        pdfUnprotect(docSet);
    }

    @Test
    public void pdfDetailsTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);
        PdfBoxDocumentDetails details = pdfDetails(docSet);
        System.out.println(details);
    }

    @Test
    public void pdfFromImageTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_IMAGE);
        docSet.setUserPassword(null);
        docSet.setOwnerPassword(null);

        PdfBoxPage pageSet = pageSettings();
        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(IMAGE_PATH_ADS);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfFromImageUsingPasswordTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_IMAGE_PASSWORD);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);

        PdfBoxPage pageSet = pageSettings();
        pageSet.setPageNumber(1);
        pageSet.setImageFilepath(IMAGE_PATH_ADS);

        pdfFromImage(docSet, pageSet);
    }

    @Test
    public void pdfToImageTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setUserPassword(OWNER_PASSWORD);

        PdfBoxPage pageSet = pageSettings();

        PdfBoxImage imageSet = imageSettings();
        imageSet.setImageQuality(ImageQualityToPdfBox.NORMAL);
        imageSet.setImageType(ImageTypeToPdfBox.JPEG);

        //Specific page
        pageSet.setPageNumber(1);
        imageSet.setFilenamePath("./src/test/resources/help4devs/images/exported/NORMAL.jpg");
        pdfToImage(docSet, pageSet, imageSet);

        //Whole document
        for (int k = 1; k < 4; k++) {
            System.out.println(k);
            pageSet.setPageNumber(k);
            imageSet.setFilenamePath("./src/test/resources/help4devs/images/exported/NORMAL-"+k+".jpg");
            pdfToImage(docSet, pageSet, imageSet);
        }
    }

    @Test
    public void pdfSplitterTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);
        pdfSplitter(docSet, "./src/test/resources/help4devs/files/pdf/splitter/");
    }

    @Test
    public void pdfMergerTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_MERGER);
        docSet.setUserPassword(USER_PASSWORD);
        docSet.setOwnerPassword(OWNER_PASSWORD);

        List<String> pdfList = new ArrayList<>();
        pdfList.add("./src/test/resources/help4devs/files/pdf/splitter/my-pdfbox-test-1.pdf");
        pdfList.add("./src/test/resources/help4devs/files/pdf/splitter/my-pdfbox-test-2.pdf");
        pdfList.add("./src/test/resources/help4devs/files/pdf/splitter/my-pdfbox-test-3.pdf");

        pdfMerger(docSet, pdfList);
    }

    @Test
    public void pdfScannerTest() {
        PdfBoxDocument docSet = documentSettings(FILEPATH_TARGET_SCANNER);
        docSet.setUserPassword(OWNER_PASSWORD);
        docSet.setOwnerPassword(USER_PASSWORD);

        List<PdfBarcodeScannerResults> result = pdfScanner(docSet);

        System.out.println(result.size()+" Found");

        for (PdfBarcodeScannerResults code : result) {
            System.out.println("Page: "+ code.getPage());
            System.out.println("Type: "+ code.getBarcodeType());
            System.out.println("Value: "+code.getBarcodeValue());
        }
    }

    @Test
    public void pdfFromDocTest() {
        /*TODO*/
    }

}
