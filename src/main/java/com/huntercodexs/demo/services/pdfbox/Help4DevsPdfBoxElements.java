package com.huntercodexs.demo.services.pdfbox;

import lombok.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

public class Help4DevsPdfBoxElements {

    @Getter
    public enum PageSizeToPdfBox {
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

        PageSizeToPdfBox(PDRectangle pageSize) {
            this.pageSize = pageSize;
        }

        public static PDRectangle pageSize(PageSizeToPdfBox pageSize) {
            return pageSize.getPageSize();
        }
    }

    @Getter
    public enum ColorsToPdfBox {
        NONE(new Color(255, 255, 255, 0)),
        WHITE(new Color(255, 255, 255)),
        RED(new Color(255, 0, 0)),
        RED2(new Color(136, 0, 20)),
        GREEN(new Color(0, 255, 0)),
        GREEN2(new Color(26, 188, 156)),
        BLUE(new Color(0, 0, 255)),
        BLUE2(new Color(51,181,255)),
        BLUE3(new Color(78, 120, 149)),
        BLACK(new Color(0, 0, 0)),
        GRAY(new Color(128, 128, 128)),
        LIGHT_GRAY(new Color(192, 192, 192)),
        ICE(new Color(235, 235, 235)),
        PURPLE(new Color(173, 108, 227)),
        GOLD(new Color(255, 215, 0)),
        GOLD2(new Color(194, 175, 13)),
        PINK(new Color(231, 6, 176)),
        YELLOW(new Color(255, 255, 0)),
        ORANGE(new Color(250, 123, 24)),
        CYAN(new Color(0, 255, 255)),
        MAGENTA(new Color(255,0,255));

        private final Color colorName;

        ColorsToPdfBox(Color colorName) {
            this.colorName = colorName;
        }

        public static Color color(ColorsToPdfBox colorName) {
            if (colorName == null) {
                return ColorsToPdfBox.NONE.getColorName();
            }
            return colorName.getColorName();
        }
    }

    @Getter
    public enum FontNameToPdfBox {
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

        FontNameToPdfBox(PDType1Font fontName) {
            this.fontName = fontName;
        }

        public static PDType1Font fontName(FontNameToPdfBox fontName) {
            return fontName.getFontName();
        }
    }

    @Getter
    public enum FontSizeToPdfBox {
        X_SMALL(5),
        SMALL(8),
        NORMAL(12),
        MEDIUM(16),
        LARGE(24),
        X_LARGE(45);

        private final int fontSize;

        FontSizeToPdfBox(int fontSize) {
            this.fontSize = fontSize;
        }

        public static int fontSize(FontSizeToPdfBox fontSize) {
            return fontSize.getFontSize();
        }
    }

    @Getter
    public enum ProtectionLevelToPdfBox {
        LOW(64),
        MIDDLE(128),
        HIGH(256);

        private final int protectionLevel;

        ProtectionLevelToPdfBox(int protectionLevel) {
            this.protectionLevel = protectionLevel;
        }

        public static int protectionLevel(ProtectionLevelToPdfBox protectionLevel) {
            return protectionLevel.getProtectionLevel();
        }
    }

    @Getter
    public enum ImageTypeToPdfBox {
        JPEG("JPEG"),
        JPG("JPEG"),
        PNG("PNG"),
        GIF("GIF"),
        TIFF("TIFF"),
        BMP("BMP");

        private final String imageType;

        ImageTypeToPdfBox(String imageType) {
            this.imageType = imageType;
        }

        public static String imageType(ImageTypeToPdfBox imageType) {
            return imageType.getImageType();
        }
    }

    @Getter
    public enum ImageQualityToPdfBox {
        LOW(50),
        NORMAL(120),
        GOOD(300),
        ULTRA(500),
        SUPER(800);

        private final int imageQuality;

        ImageQualityToPdfBox(int imageQuality) {
            this.imageQuality = imageQuality;
        }

        public static int imageQuality(ImageQualityToPdfBox imageQuality) {
            return imageQuality.getImageQuality();
        }
    }

    @Getter
    public enum TableDimensionsToPdfBox {
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

        TableDimensionsToPdfBox(int tableLines, int tableColumns) {
            this.tableLines = tableLines;
            this.tableColumns = tableColumns;
        }

        public static int[] tableSize(TableDimensionsToPdfBox template) {
            return new int[]{template.getTableLines(), template.getTableColumns()};
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxDocumentDetails {
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
    public static class PdfBoxDocument {
        int startPage;
        int endPage;
        int numberOfPages;
        String date;
        String title;
        String author;
        String subject;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        String keywords;
        String userPassword;
        String ownerPassword;
        ProtectionLevelToPdfBox protectionLevel;
        String filenamePath;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxPage {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int pageNumber;
        int margin;
        int padding;
        byte[] byteContent;
        PageSizeToPdfBox pageSize;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        ColorsToPdfBox fontColor;
        ColorsToPdfBox pageColor;
        String textContent = "";
        String imageFilepath;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxContainer {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int borderWidth = 1;
        boolean border;
        boolean roundedBorder;
        ColorsToPdfBox backColor;
        ColorsToPdfBox borderColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTitle {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int letterSpace;
        boolean bold;
        boolean italic;
        boolean underline;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        ColorsToPdfBox fontColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxColumns {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int borderWidth;
        boolean border;
        ColorsToPdfBox textColor;
        ColorsToPdfBox backgroundColor;
        ColorsToPdfBox borderColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxText {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int letterSpace;
        boolean bold;
        boolean italic;
        boolean underline;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        ColorsToPdfBox fontColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxImage {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int maxWidth;
        int maxHeight;
        boolean border;
        boolean resize;
        String filenamePath;
        ImageTypeToPdfBox imageType;
        ImageQualityToPdfBox imageQuality;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTable {
        int width;
        int height;
        int offsetX;
        int offsetY;
        boolean border;
        ColorsToPdfBox headerColor;
        ColorsToPdfBox celColor;
        ColorsToPdfBox borderColor;
        TableDimensionsToPdfBox tableTemplate;
    }

}
