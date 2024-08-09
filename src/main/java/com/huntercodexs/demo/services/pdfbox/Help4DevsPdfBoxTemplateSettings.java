package com.huntercodexs.demo.services.pdfbox;

import lombok.*;

import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.*;

public class Help4DevsPdfBoxTemplateSettings {

    @Getter
    public enum PdfBoxTemplates {
        SLIM("SLIM"),
        BOX("BOX"),
        BOX_OPEN("BOX_OPEN"),
        SLIM_BOX("SLIM_BOX"),
        TRIPLE_FALL("TRIPLE_FALL"),
        FREE("FREE"),
        HEADER_BODY("HEADER_BODY"),
        BIG_BURGER("BIG_BURGER");

        private final String template;

        PdfBoxTemplates(String template) {
            this.template = template;
        }

        public static String template(PdfBoxTemplates template) {
            return template.getTemplate();
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTemplateSettings {
        /*Template Name*/
        PdfBoxTemplates template = null;

        /*Template Background*/
        String imageBackground = null;

        /*Template Elements*/
        PdfBoxDocument document = null;
        PdfBoxPage page = null;
        PdfBoxContainer container = null;
        PdfBoxText text = null;
        PdfBoxImage image = null;
        PdfBoxTable table = null;
        PdfBoxBarcode barcode = null;
        PdfBoxQrCode qrCode = null;

        /*Template Behavior*/
        SlimTemplateSettings slim = null;
        BoxTemplateSettings box = null;
        BoxOpenTemplateSettings boxOpen = null;
        SlimBoxTemplateSettings slimBox = null;
        TripleFallTemplateSettings tripleFall = null;
        FreeTemplateSettings free = null;
        HeaderBodyTemplateSettings headerBody = null;
        BigBurgerTemplateSettings bigBurger = null;

        /*Template Content*/
        SlimDataContent slimContent = null;
        BoxDataContent boxContent = null;
        BoxOpenDataContent boxOpenContent = null;
        SlimBoxDataContent slimBoxContent = null;
        TripleFallDataContent tripleFallContent = null;
        FreeDataContent freeContent = null;
        HeaderBodyDataContent headerBodyContent = null;
        BigBurgerDataContent bigBurgerContent = null;

    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimTemplateSettings {
        //General
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int BOX_QUANTITY = 5;
        static final int COLUMN_QUANTITY = 3;
        static final int DEFAULT_WIDTH = 570;
        static final int DEFAULT_HEIGHT = 135;
        static final int DEFAULT_OFFSET_X = 20;
        static final int OFFSET_Y_BLOCK1 = 640;
        static final int OFFSET_Y_BLOCK2 = 485;
        static final int OFFSET_Y_BLOCK3 = 330;
        static final int OFFSET_Y_BLOCK4 = 175;
        static final int OFFSET_Y_BLOCK5 = 20;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};

        //Title
        int leftTitleAdjustmentX = 0;
        int leftTitleAdjustmentY = 0;
        int centerTitleAdjustmentX = 0;
        int centerTitleAdjustmentY = 0;
        int rightTitleAdjustmentX = 0;
        int rightTitleAdjustmentY = 0;
        int[] titleOffsetX = new int[]{35,250,450};
        int[] titleOffsetY = new int[]{750,595,440,285,130};
        boolean[] leftTitleEnable = new boolean[]{false,false,false,false,false};
        boolean[] centerTitleEnable = new boolean[]{false,false,false,false,false};
        boolean[] rightTitleEnable = new boolean[]{false,false,false,false,false};
        ColorsToPdfBox leftTitleColor = ColorsToPdfBox.BLACK;
        ColorsToPdfBox centerTitleColor = ColorsToPdfBox.BLACK;
        ColorsToPdfBox rightTitleColor = ColorsToPdfBox.BLACK;
        FontSizeToPdfBox leftTitleSize = FontSizeToPdfBox.MEDIUM;
        FontSizeToPdfBox centerTitleSize = FontSizeToPdfBox.MEDIUM;
        FontSizeToPdfBox rightTitleSize = FontSizeToPdfBox.MEDIUM;
        FontNameToPdfBox leftTitleFont = FontNameToPdfBox.HELVETICA_B;
        FontNameToPdfBox centerTitleFont = FontNameToPdfBox.HELVETICA_B;
        FontNameToPdfBox rightTitleFont = FontNameToPdfBox.HELVETICA_B;

        //Column
        int columnBoxWidth = 170;
        int columnBoxHeight = 90;
        int columnBoxChars = 40;
        int[] columnBoxOffsetX = new int[]{35,220,405};
        int[] columnBoxOffsetY = new int[]{655,500,345,190,35};
        int[] columnBoxLeftPadding = new int[]{10,10,10,10,10};
        int[] columnBoxCenterPadding = new int[]{10,10,10,10,10};
        int[] columnBoxRightPadding = new int[]{10,10,10,10,10};
        int[] columnBoxLeftBorderWidth = new int[]{1,1,1,1,1};
        int[] columnBoxCenterBorderWidth = new int[]{1,1,1,1,1};
        int[] columnBoxRightBorderWidth = new int[]{1,1,1,1,1};
        int[] columnBoxLeftLineHeight = new int[]{14,14,14,14,14};
        int[] columnBoxCenterLineHeight = new int[]{14,14,14,14,14};
        int[] columnBoxRightLineHeight = new int[]{14,14,14,14,14};
        int[] columnBoxLeftAdjustmentX = new int[]{0,0,0,0,0};
        int[] columnBoxLeftAdjustmentY = new int[]{0,0,0,0,0};
        int[] columnBoxCenterAdjustmentX = new int[]{0,0,0,0,0};
        int[] columnBoxCenterAdjustmentY = new int[]{0,0,0,0,0};
        int[] columnBoxRightAdjustmentX = new int[]{0,0,0,0,0};
        int[] columnBoxRightAdjustmentY = new int[]{0,0,0,0,0};
        boolean[] columnBoxLeftEnable = new boolean[]{false,false,false,false,false};
        boolean[] columnBoxCenterEnable = new boolean[]{false,false,false,false,false};
        boolean[] columnBoxRightEnable = new boolean[]{false,false,false,false,false};
        boolean[] columnBoxLeftBorderEnable = new boolean[]{true,true,true,true,true};
        boolean[] columnBoxCenterBorderEnable = new boolean[]{true,true,true,true,true};
        boolean[] columnBoxRightBorderEnable = new boolean[]{true,true,true,true,true};
        ColorsToPdfBox[] columnBoxLeftBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxCenterBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxRightBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxLeftBorderColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxCenterBorderColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxRightBorderColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxLeftTextColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxCenterTextColor = new ColorsToPdfBox[]{null, null, null, null, null};
        ColorsToPdfBox[] columnBoxRightTextColor = new ColorsToPdfBox[]{null, null, null, null, null};
        FontSizeToPdfBox[] columnBoxLeftFontSize = new FontSizeToPdfBox[]{null, null, null, null, null};
        FontSizeToPdfBox[] columnBoxCenterFontSize = new FontSizeToPdfBox[]{null, null, null, null, null};
        FontSizeToPdfBox[] columnBoxRightFontSize = new FontSizeToPdfBox[]{null, null, null, null, null};
        FontNameToPdfBox[] columnBoxLeftFontName = new FontNameToPdfBox[]{null, null, null, null, null};
        FontNameToPdfBox[] columnBoxCenterFontName = new FontNameToPdfBox[]{null, null, null, null, null};
        FontNameToPdfBox[] columnBoxRightFontName = new FontNameToPdfBox[]{null, null, null, null, null};

        //Table
        int tableWidth = 540;
        int tableHeight = 90;
        int tableOffsetX = 35;
        int tableColumnWidth = 90;
        int tableColumnHeight = 18;
        int tableHeaderHeight = 30;
        int tableHeaderAdjustOffsetX = 0;
        int tableHeaderAdjustOffsetY = 0;
        int tableBodyAdjustOffsetX = 0;
        int tableBodyAdjustOffsetY = 0;
        int[] tableContainerOffsetY = new int[]{656, 500, 346, 190, 35};
        int[] tableHeaderOffsetY = new int[]{728, 572, 418, 262, 107};
        int[] tableColumnOffsetX = new int[] {35,125,215,305,395,485};
        int[] tableDataOffsetY = new int[]{710, 554, 400, 244, 89};
        boolean[] tableEnable = new boolean[]{false,false,false,false,false};
        ColorsToPdfBox tableBorderColor = ColorsToPdfBox.WHITE;
        ColorsToPdfBox tableHeaderColor = ColorsToPdfBox.GRAY;
        ColorsToPdfBox tableBodyColor = ColorsToPdfBox.ICE;
        ColorsToPdfBox tableHeaderFontColor = ColorsToPdfBox.WHITE;
        ColorsToPdfBox tableBodyFontColor = ColorsToPdfBox.GRAY;
        FontSizeToPdfBox tableHeaderFontSize = FontSizeToPdfBox.NORMAL;
        FontSizeToPdfBox tableBodyFontSize = FontSizeToPdfBox.NORMAL;
        FontNameToPdfBox tableHeaderFontName = FontNameToPdfBox.HELVETICA;
        FontNameToPdfBox tableBodyFontName = FontNameToPdfBox.HELVETICA;
        TableDimensionsToPdfBox tableSize = TableDimensionsToPdfBox.TABLE_5X6;

        //Signature Box
        int signatureBoxWidth = 200;
        int signatureBoxHeight = 100;
        int signatureBoxAdjustOffsetX = 0;
        int[] signatureBoxOffsetX = new int[]{55,210,355};
        int[] signatureBoxOffsetY = new int[]{35,122,100};
        int[] signatureBoxDigitalTitleOffsetX = new int[]{105,260,405};
        int[] signatureBoxContentOffsetX = new int[]{70,230,370};
        boolean signatureBoxBorderEnable = false;
        boolean leftSignatureBoxEnable = false;
        boolean centerSignatureBoxEnable = false;
        boolean rightSignatureBoxEnable = false;
        FontSizeToPdfBox signatureFontSize = FontSizeToPdfBox.SMALL;
        FontNameToPdfBox signatureFontName = FontNameToPdfBox.HELVETICA_B;
        ColorsToPdfBox signatureBoxColor = ColorsToPdfBox.BLACK;

        //Signature Tape
        int signatureTapeWidth = 500;
        int signatureTapeHeight = 30;
        int signatureTapeOffsetX = 55;
        int signatureTapeOffsetY = 35;
        int signatureTapeTitleOffsetX = 260;
        int signatureTapeTitleOffsetY = 57;
        int signatureTapeValueOffsetX = 130;
        int signatureTapeValueOffsetY = 40;
        int signatureTapeAdjustOffsetX = 10;
        boolean signatureTapeEnable = false;
        FontSizeToPdfBox signatureTapeFontSize = FontSizeToPdfBox.SMALL;
        FontNameToPdfBox signatureTapeFontName = FontNameToPdfBox.HELVETICA_B;
        ColorsToPdfBox signatureTapeColor = ColorsToPdfBox.GRAY;

        //Text
        int lineHeight = 18;
        int textOffsetX = 35;
        int textChars = 400;
        int[] textOffsetY = new int[]{732,577,421,266,111};
        boolean[] textEnable = new boolean[]{false,false,false,false,false};
        ColorsToPdfBox textColor = ColorsToPdfBox.BLACK;
        FontSizeToPdfBox textSize = FontSizeToPdfBox.NORMAL;
        FontNameToPdfBox textFont = FontNameToPdfBox.HELVETICA;

        //Image
        int imageWidth = 0;
        int imageHeight = 0;
        int imageAdjustOffsetX = 0;
        int imageAdjustOffsetY = 0;
        int[] imageOffsetX = new int[]{35,180,330};
        int[] imageOffsetY = new int[]{650,495,340,185,30};
        boolean[] leftImageEnable = new boolean[]{false,false,false,false,false};
        boolean[] centerImageEnable = new boolean[]{false,false,false,false,false};
        boolean[] rightImageEnable = new boolean[]{false,false,false,false,false};

        //Barcode
        int barcodeDpi = 400;
        int barcodeWidth = 500;
        int barcodeHeight = 50;
        static final int barcodeOffsetX = 55;
        int barcodeAdjustOffsetX = 0;
        int barcodeAdjustOffsetY = 0;
        int[] barcodeOffsetY = new int[]{655,500,345,190,35};
        int[] barcodeInfoOffsetY = new int[]{750, 595, 440, 285, 130};
        int[] barcodeValueOffsetY = new int[]{690, 535, 380, 225, 70};
        int[] barcodeAmountOffsetY = new int[]{745, 590, 435, 280, 135};
        boolean barcodeShowText = true;
        boolean[] barcodeEnabled = new boolean[]{false,false,false,false,false};

        //QRCode
        int qrCodeDpi = 400;
        int qrCodeWidth = 200;
        int qrCodeHeight = 50;
        int qrCodeAdjustOffsetX = 0;
        int qrCodeAdjustOffsetY = 0;
        int[] qrCodeOffsetX = new int[]{40,260,470};
        int[] qrCodeOffsetY = new int[]{655,502,348,193,38};
        int[] qrCodeInfoOffsetX = new int[]{145, 365, 365};
        int[] qrCodeInfoOffsetY = new int[]{745, 595, 440, 285, 130};
        boolean[] qrCodeLeftEnable = new boolean[]{false,false,false,false,false};
        boolean[] qrCodeCenterEnable = new boolean[]{false,false,false,false,false};
        boolean[] qrCodeRightEnable = new boolean[]{false,false,false,false,false};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimDataContent {

        //Title
        String leftTitleContent = "";
        String centerTitleContent = "";
        String rightTitleContent = "";

        //Column
        HashMap<Integer, String> columnContent = new HashMap<>();

        //Table
        HashMap<Integer, List<String>> tableHeaderContent = new HashMap<>();
        HashMap<Integer, List<String>> tableBodyContent = new HashMap<>();

        //Signature
        String signaturePersonName = "";
        String signaturePersonDoc = "";
        String signatureRecord = "";
        String signatureDateGmt = "";
        String signatureStampMark = "";

        //Text
        HashMap<Integer, String> textContent = new HashMap<>();

        //Image
        String[] leftImagePaths = new String[]{null,null,null,null,null};
        String[] centerImagePaths = new String[]{null,null,null,null,null};
        String[] rightImagePaths = new String[]{null,null,null,null,null};

        //Barcode
        HashMap<Integer, String>  barcodeValue = new HashMap<>();
        HashMap<Integer, String>  barcodeInfoOne = new HashMap<>();
        HashMap<Integer, String>  barcodeInfoTwo = new HashMap<>();
        HashMap<Integer, String>  barcodeInfoThree = new HashMap<>();
        HashMap<Integer, String>  barcodeInfoFour = new HashMap<>();
        HashMap<Integer, String>  barcodeAmount = new HashMap<>();

        //QrCode
        HashMap<Integer, String>  qrCodeValue = new HashMap<>();
        HashMap<Integer, String> qrCodeInfoOne = new HashMap<>();
        HashMap<Integer, String>  qrCodeInfoTwo = new HashMap<>();
        HashMap<Integer, String>  qrCodeInfoThree = new HashMap<>();
        HashMap<Integer, String>  qrCodeInfoFour = new HashMap<>();
        HashMap<Integer, String>  qrCodeAmount = new HashMap<>();
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxTemplateSettings {
        //General
        int boxQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int BOXES = 10;
        static final int BOX_DEFAULT_WIDTH = 286;
        static final int BOX_DEFAULT_HEIGHT = 135;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxDataContent {
        //General settings
        int data;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxOpenTemplateSettings {
        //General
        int boxOpenQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int BOX_OPEN_QUANTITY = 10;
        static final int BOX_OPEN_DEFAULT_WIDTH = 286;
        static final int BOX_OPEN_DEFAULT_HEIGHT = 135;
        static final int BOX_OPEN_HEIGHT = 445;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxOpenDataContent {
        //General settings
        int data;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimBoxTemplateSettings {
        //General
        int slimBoxQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int SLIM_BOX_QUANTITY = 10;
        static final int SLIM_BOX_DEFAULT_WIDTH = 286;
        static final int SLIM_BOX_DEFAULT_HEIGHT = 135;
        static final int SLIM_BOX_WIDTH = 575;
        static final int SLIM_BOX_HEIGHT = 445;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimBoxDataContent {
        //General settings
        int data;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TripleFallTemplateSettings {
        //General
        int tripleFallQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int TRIPLE_FALL_QUANTITY = 3;
        static final int TRIPLE_FALL_DEFAULT_WIDTH = 188;
        static final int TRIPLE_FALL_DEFAULT_HEIGHT = 750;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TripleFallDataContent {
        //General settings
        int data;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FreeTemplateSettings {
        //General
        int freeQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int FREE_DEFAULT_OFFSET_X = 20;
        static final int FREE_DEFAULT_OFFSET_Y = 20;
        static final int FREE_DEFAULT_WIDTH = 570;
        static final int FREE_DEFAULT_HEIGHT = 750;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FreeDataContent {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HeaderBodyTemplateSettings {
        //General
        int headerBodyQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int HEADER_BODY_DEFAULT_OFFSET_X = 20;
        static final int HEADER_BODY_DEFAULT_OFFSET_Y = 640;
        static final int HEADER_BODY_DEFAULT_WIDTH = 570;
        static final int HEADER_BODY_DEFAULT_HEIGHT = 135;
        static final int HEADER_BODY_OFFSET_Y = 20;
        static final int HEADER_BODY_HEIGHT = 600;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HeaderBodyDataContent {
        //General settings
        int data;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BigBurgerTemplateSettings {
        //General
        int bigBurgerQuantity;
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int BIG_BURGER_DEFAULT_WIDTH = 570;
        static final int BIG_BURGER_DEFAULT_HEIGHT = 135;
        static final int BIG_BURGER_HEADER_OFFSET_X = 20;
        static final int BIG_BURGER_HEADER_OFFSET_Y = 640;
        static final int BIG_BURGER_HEADER_WIDTH = 570;
        static final int BIG_BURGER_HEADER_HEIGHT = 135;
        static final int BIG_BURGER_BODY_OFFSET_X = 20;
        static final int BIG_BURGER_BODY_OFFSET_Y = 170;
        static final int BIG_BURGER_BODY_WIDTH = 570;
        static final int BIG_BURGER_BODY_HEIGHT = 455;
        static final int BIG_BURGER_FOOTER_OFFSET_X = 20;
        static final int BIG_BURGER_FOOTER_OFFSET_Y = 20;
        static final int BIG_BURGER_FOOTER_WIDTH = 570;
        static final int BIG_BURGER_FOOTER_HEIGHT = 135;
        boolean templateTitleEnabled = false;
        int[] boxWidth = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetX = new int[]{0,0,0,0,0};
        int[] boxAdjustOffsetY = new int[]{0,0,0,0,0};
        boolean[] boxBorderEnabled = new boolean[]{true, true, true, true, true};
        ColorsToPdfBox[] boxBackColor = new ColorsToPdfBox[]{null, null, null, null, null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BigBurgerDataContent {
        //General settings
        int data;
    }

}
