package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.krysalis.barcode4j.HumanReadablePlacement;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class Help4DevsEasyPdfElements {

    @Getter
    public enum PageSizeToEasyPdf {
        A4_LAYOUT(PDRectangle.A4),
        LETTER_LAYOUT(PDRectangle.LETTER),

        A0(PDRectangle.A0),
        A1(PDRectangle.A1),
        A2(PDRectangle.A2),
        A3(PDRectangle.A3),
        A4(PDRectangle.A4),
        A5(PDRectangle.A5),
        A6(PDRectangle.A6),
        LEGAL(PDRectangle.LEGAL),
        LETTER(PDRectangle.LETTER),
        A4_LANDSCAPE(new PDRectangle(842.0F, 595.5F)),
        LETTER_LANDSCAPE(new PDRectangle(792.0F, 612.0F));

        private final PDRectangle pageSize;

        PageSizeToEasyPdf(PDRectangle pageSize) {
            this.pageSize = pageSize;
        }

        public static PDRectangle pageSize(PageSizeToEasyPdf pageSize) {
            return pageSize.getPageSize();
        }
    }

    @Getter
    public enum ColorsToEasyPdf {
        NONE(new Color(255, 255, 255, 0)),
        BLACK(new Color(0, 0, 0)),
        GRAY(new Color(128, 128, 128)),
        LIGHT_GRAY(new Color(192, 192, 192)),
        ICE(new Color(235, 235, 235)),
        WHITE(new Color(255, 255, 255)),
        RED(new Color(255, 0, 0)),
        RED_DARK(new Color(162, 22, 43)),
        RED_BRIGHT(new Color(243, 75, 75)),
        RED_LIGHT(new Color(243, 160, 160)),
        ORANGE(new Color(250, 123, 24)),
        GREEN(new Color(0, 255, 0)),
        GREEN_SEA(new Color(26, 188, 156)),
        GREEN_DARK(new Color(9, 112, 28)),
        BLUE(new Color(0, 0, 255)),
        BLUE_DARK(new Color(17, 17, 155)),
        BLUE_SEA(new Color(51,181,255)),
        BLUE_SAD(new Color(78, 120, 149)),
        CYAN(new Color(0, 255, 255)),
        YELLOW(new Color(255, 255, 0)),
        GOLD(new Color(255, 215, 0)),
        GOLD_DARK(new Color(194, 175, 13)),
        PINK(new Color(231, 6, 176)),
        MAGENTA(new Color(255,0,255)),
        PURPLE(new Color(173, 108, 227));

        private final Color colorName;

        ColorsToEasyPdf(Color colorName) {
            this.colorName = colorName;
        }

        public static Color color(ColorsToEasyPdf colorName) {
            if (colorName == null) {
                return ColorsToEasyPdf.NONE.getColorName();
            }
            return colorName.getColorName();
        }
    }

    @Getter
    public enum FontNameToEasyPdf {
        ZAP(PDType1Font.ZAPF_DINGBATS),
        SYMBOL(PDType1Font.SYMBOL),

        TIMES(PDType1Font.TIMES_ROMAN),
        TIMES_B(PDType1Font.TIMES_BOLD),
        TIMES_I(PDType1Font.TIMES_ITALIC),
        TIMES_BI(PDType1Font.TIMES_BOLD_ITALIC),

        COURIER(PDType1Font.COURIER),
        COURIER_B(PDType1Font.COURIER_BOLD),
        COURIER_I(PDType1Font.COURIER_OBLIQUE),
        COURIER_BI(PDType1Font.COURIER_BOLD_OBLIQUE),

        HELVETICA(PDType1Font.HELVETICA),
        HELVETICA_B(PDType1Font.HELVETICA_BOLD),
        HELVETICA_I(PDType1Font.HELVETICA_OBLIQUE),
        HELVETICA_BI(PDType1Font.HELVETICA_BOLD_OBLIQUE);

        private final PDType1Font fontName;

        FontNameToEasyPdf(PDType1Font fontName) {
            this.fontName = fontName;
        }

        public static PDType1Font fontName(FontNameToEasyPdf fontName) {
            return fontName.getFontName();
        }
    }

    @Getter
    public enum FontSizeToEasyPdf {
        X_SMALL(5),
        M_SMALL(7),
        SMALL(8),
        NORMAL(12),
        PLUS(14),
        MEDIUM(16),
        X_MEDIUM(20),
        LARGE(24),
        X_LARGE(45);

        private final int fontSize;

        FontSizeToEasyPdf(int fontSize) {
            this.fontSize = fontSize;
        }

        public static int fontSize(FontSizeToEasyPdf fontSize) {
            return fontSize.getFontSize();
        }
    }

    @Getter
    public enum ProtectionLevelToEasyPdf {
        LOW(64),
        MIDDLE(128),
        HIGH(256);

        private final int protectionLevel;

        ProtectionLevelToEasyPdf(int protectionLevel) {
            this.protectionLevel = protectionLevel;
        }

        public static int protectionLevel(ProtectionLevelToEasyPdf protectionLevel) {
            return protectionLevel.getProtectionLevel();
        }
    }

    @Getter
    public enum ImageTypeToEasyPdf {
        JPEG("JPEG"),
        JPG("JPEG"),
        PNG("PNG"),
        GIF("GIF"),
        TIFF("TIFF"),
        BMP("BMP");

        private final String imageType;

        ImageTypeToEasyPdf(String imageType) {
            this.imageType = imageType;
        }

        public static String imageType(ImageTypeToEasyPdf imageType) {
            return imageType.getImageType();
        }
    }

    @Getter
    public enum ImageQualityToEasyPdf {
        LOW(50),
        NORMAL(120),
        GOOD(300),
        ULTRA(500),
        SUPER(800);

        private final int imageQuality;

        ImageQualityToEasyPdf(int imageQuality) {
            this.imageQuality = imageQuality;
        }

        public static int imageQuality(ImageQualityToEasyPdf imageQuality) {
            return imageQuality.getImageQuality();
        }
    }

    @Getter
    public enum TableDimensionsToEasyPdf {
        TABLE_5X6(5, 6),
        TABLE_5X5(5, 5),
        TABLE_5X4(5, 4),
        TABLE_5X3(5, 3),
        TABLE_5X2(5, 2),

        TABLE_4X6(4, 6),
        TABLE_4X5(4, 5),
        TABLE_4X4(4, 4),
        TABLE_4X3(4, 3),
        TABLE_4X2(4, 2),

        TABLE_3X6(3, 6),
        TABLE_3X5(3, 5),
        TABLE_3X4(3, 4),
        TABLE_3X3(3, 3),
        TABLE_3X2(3, 2),

        TABLE_2X6(2, 6),
        TABLE_2X5(2, 5),
        TABLE_2X4(2, 4),
        TABLE_2X3(2, 3),
        TABLE_2X2(2, 2);

        private final int tableLines;
        private final int tableColumns;

        TableDimensionsToEasyPdf(int tableLines, int tableColumns) {
            this.tableLines = tableLines;
            this.tableColumns = tableColumns;
        }

        public static int[] tableSize(TableDimensionsToEasyPdf template) {
            return new int[]{template.getTableLines(), template.getTableColumns()};
        }
    }

    @Getter
    public enum CodeType4ScannerToEasyPdf {
        CODE128("CODE128"),
        CODE39("CODE39"),
        PDF417("PDF417"),
        QRCODE("QRCODE");

        private final String codeType4Scanner;

        CodeType4ScannerToEasyPdf(String codeType) {
            this.codeType4Scanner = codeType;
        }

        public static String codeType4Scanner(CodeType4ScannerToEasyPdf codeType) {
            return codeType.getCodeType4Scanner();
        }
    }

    @Getter
    public enum QrCodeBorderStyleToEasyPdf {
        BORDERED("BORDERED"),
        BORDERLESS("BORDERLESS"),
        LEFT_BORDERED("LEFT_BORDERED");

        private final String borderStyle;

        QrCodeBorderStyleToEasyPdf(String borderStyle) {
            this.borderStyle = borderStyle;
        }

        public static String qrCodeBorderStyle(QrCodeBorderStyleToEasyPdf borderStyle) {
            return borderStyle.getBorderStyle();
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfDocumentDetails {
        int numberOfPages;
        boolean isProtected;
        boolean hasSignature;
        String date;
        String title;
        String author;
        String subject;
        String pageSize;
        String fontName;
        String fontSize;
        String keywords;
        String signature;
        String documentId;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfDocument {
        int startPage;
        int endPage;
        int numberOfPages;
        String date;
        String title;
        String author;
        String subject;
        FontNameToEasyPdf fontName;
        FontSizeToEasyPdf fontSize;
        String keywords;
        String userPassword;
        String ownerPassword;
        ProtectionLevelToEasyPdf protectionLevel;
        String filenamePath;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfPage {
        /*Page Size*/
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int WIDTH_ADJUST_A4 = -15;
        static final int OFFSET_Y_ADJUST_A4 = 25;
        static final int[] PAGE_SIZE_LETTER = new int[]{620,792};
        static final int[] PAGE_SIZE_A4 = new int[]{596,842};

        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int pageNumber;
        int margin;
        int padding;
        byte[] byteContent;
        PageSizeToEasyPdf pageSize;
        FontNameToEasyPdf fontName;
        FontSizeToEasyPdf fontSize;
        ColorsToEasyPdf fontColor;
        ColorsToEasyPdf pageColor;
        String textContent = "";
        String imageFilepath = null;

        public static int getPageWidth(String type) {
            if (type.equals("LETTER") || type.equals("LETTER_LAYOUT")) {
                return PAGE_SIZE_LETTER[0];
            }
            return PAGE_SIZE_A4[0];
        }

        public static int getPageHeight(String type) {
            if (type.equals("LETTER") || type.equals("LETTER_LAYOUT")) {
                return PAGE_SIZE_LETTER[1];
            }
            return PAGE_SIZE_A4[1];
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfContainer {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int borderWidth = 1;
        boolean border = false;
        boolean roundedBorder = false;
        ColorsToEasyPdf backColor;
        ColorsToEasyPdf borderColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfTitle {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int letterSpace;
        boolean bold;
        boolean italic;
        boolean underline;
        FontNameToEasyPdf fontName;
        FontSizeToEasyPdf fontSize;
        ColorsToEasyPdf fontColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfColumns {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int borderWidth;
        boolean border;
        ColorsToEasyPdf textColor;
        ColorsToEasyPdf backgroundColor;
        ColorsToEasyPdf borderColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfText {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int letterSpace;
        boolean bold = false;
        boolean italic = false;
        boolean underline = false;
        FontNameToEasyPdf fontName;
        FontSizeToEasyPdf fontSize;
        ColorsToEasyPdf fontColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfImage {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int maxWidth;
        int maxHeight;
        boolean border;
        boolean resize;
        String filenamePath;
        ImageTypeToEasyPdf imageType;
        ImageQualityToEasyPdf imageQuality;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfTable {
        int width;
        int height;
        int offsetX;
        int offsetY;
        boolean border;
        ColorsToEasyPdf headerColor;
        ColorsToEasyPdf celColor;
        ColorsToEasyPdf borderColor;
        TableDimensionsToEasyPdf tableTemplate;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfBarcode {
        int dpi = 400;
        int width = 370;
        int height = 40;
        int fontSize = 2;
        int lineHeight = 20;
        int margin = 0;
        int orientation = 0;
        float offsetX = 0;
        float offsetY = 400;
        double fixQuiteZone = 0;
        boolean doQuiteZone = false;
        String data = "00000.00000 00000.000000 00000.00000 0 00000000000000";
        String infoOne = "";
        String infoThree = "";
        String infoFour = "";
        FontNameToEasyPdf fontName = FontNameToEasyPdf.COURIER;
        HumanReadablePlacement textPosition = HumanReadablePlacement.HRP_NONE;
        CodeType4ScannerToEasyPdf codeType4Scanner;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfQrCode {
        int dpi = 400;
        int margin = 0;
        int matrix = 70;
        int size = 70;
        int onColor = 0xFF000001;
        int offColor = 0xFFFFFFFF;
        float offsetX = 0;
        float offsetY = 400;
        String data;
        String infoOne;
        String infoThree;
        String infoFour;
        CodeType4ScannerToEasyPdf codeType4Scanner;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfBarcodeForm {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int borderWidth = 1;
        int adjustOffsetX = 0;
        int adjustOffsetY = 0;
        boolean border = true;
        boolean qrcode = true;
        boolean revealFields = false;
        ColorsToEasyPdf headerColor;
        ColorsToEasyPdf celColor;
        ColorsToEasyPdf borderColor;
        EasyPdfBarcode barcode = new EasyPdfBarcode();
        EasyPdfBarcodeFormFields fields = new EasyPdfBarcodeFormFields();
        EasyPdfBarcodeFormData data = new EasyPdfBarcodeFormData();
        QrCodeBorderStyleToEasyPdf borderStyle = QrCodeBorderStyleToEasyPdf.BORDERED;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfBarcodeFormFields {
        String fieldLeft1 = "fieldLeft1";
        String fieldLeft2 = "fieldLeft2";
        String fieldLeft3 = "fieldLeft3";
        String fieldLeft4 = "fieldLeft4";
        String fieldLeft5 = "fieldLeft5";
        String fieldLeft6 = "fieldLeft6";
        String fieldLeft7 = "fieldLeft7";
        String fieldLeft8 = "fieldLeft8";
        String fieldLeft9 = "fieldLeft9";
        String fieldLeft10 = "fieldLeft10";
        String fieldLeft11 = "fieldLeft11";
        String fieldLeft12 = "fieldLeft12"; /*Can be used like a qrcode reserved space*/
        String fieldLeft13 = "fieldLeft13";

        String fieldRight1 = "fieldRight1";
        String fieldRight2 = "fieldRight2";
        String fieldRight3 = "fieldRight3";
        String fieldRight4 = "fieldRight4";
        String fieldRight5 = "fieldRight5";
        String fieldRight6 = "fieldRight6";
        String fieldRight7 = "fieldRight7";
        String fieldRight8 = "fieldRight8";
        String fieldRight9 = "fieldRight9";
        String fieldRight10 = "fieldRight10";

        String fieldFooter1 = "fieldFooter1";
        String fieldFooter2 = "fieldFooter2";
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfBarcodeFormData {
        String dataHeaderImage = null;
        String dataHeaderOperator = "0000-00";
        String dataHeaderBarcode = "00000.00000 00000.000000 00000.00000 0 00000000000000";
        String dataLeft1 = "dataLeft1";
        String dataLeft2 = "dataLeft2";
        String dataLeft3 = "dataLeft3";
        String dataLeft4 = "dataLeft4";
        String dataLeft5 = "dataLeft5";
        String dataLeft6 = "dataLeft6";
        String dataLeft7 = "dataLeft7";
        String dataLeft8 = "dataLeft8";
        String dataLeft9 = "dataLeft9";
        String dataLeft10 = "dataLeft10";
        List<String> dataLeft11 = Arrays.asList("dataLeft11-1", "dataLeft11-2","dataLeft11-3","dataLeft11-4", "dataLeft11-5","dataLeft11-6","dataLeft11-7","dataLeft11-8");
        String dataLeft12 = "dataLeft12"; /*Can be used like a qrcode reserved space*/
        List<String> dataLeft13 = Arrays.asList("dataLeft13-1", "dataLeft13-2","dataLeft13-3");
        String dataRight1 = "dataRight1";
        String dataRight2 = "dataRight2";
        String dataRight3 = "dataRight3";
        String dataRight4 = "dataRight4";
        String dataRight5 = "dataRight5";
        String dataRight6 = "dataRight6";
        String dataRight7 = "dataRight7";
        String dataRight8 = "dataRight8";
        String dataRight9 = "dataRight9";
        String dataRight10 = "dataRight10";
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EasyPdfForm {
        int width;
        int height;
        int offsetX;
        int offsetY;
        boolean border;
        ColorsToEasyPdf headerColor;
        ColorsToEasyPdf celColor;
        ColorsToEasyPdf borderColor;
    }

}
