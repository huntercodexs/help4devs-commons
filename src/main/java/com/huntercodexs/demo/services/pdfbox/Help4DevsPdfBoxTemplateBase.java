package com.huntercodexs.demo.services.pdfbox;

import lombok.*;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.*;

public class Help4DevsPdfBoxTemplateBase {

    @Getter
    public enum PdfBoxTemplates {
        FREE("FREE"),
        SLIM("SLIM"),
        BOX("BOX"),
        BOX_OPEN("BOX_OPEN"),
        SLIM_BOX("SLIM_BOX"),
        SIMPLE_2("SIMPLE_2"),
        SIMPLE_3("SIMPLE_3");

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
        /*Template*/
        PdfBoxTemplates template = null;

        /*Template Dimensions*/
        int width = 0;
        int height = 0;
        int offsetX = 0;
        int offsetY = 0;

        /*Template Background*/
        boolean imageBackgroundEnable = true;
        String imageBackground = null;

        /*Elements Settings*/
        PdfBoxDocument document = null;
        PdfBoxPage page = null;
        PdfBoxContainer container = null;
        PdfBoxText text = null;
        PdfBoxImage image = null;
        PdfBoxTable table = null;

        /*Templates Available*/
        FreeTemplateSettings free = null;
        SlimTemplateSettings slim = null;
        BoxTemplateSettings box = null;
        BoxOpenTemplateSettings boxOpen = null;
        Simple2TemplateSettings simple2 = null;
        Simple3TemplateSettings simple3 = null;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FreeTemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimTemplateSettings {
        //General settings
        /*IMPORTANT: DO NOT CHANGE THESE VALUES*/
        static final int BOX_QUANTITY = 5;
        static final int DEFAULT_WIDTH = 570;
        static final int DEFAULT_HEIGHT = 135;
        static final int OFFSET_Y_BLOCK1 = 640;
        static final int OFFSET_Y_BLOCK2 = 485;
        static final int OFFSET_Y_BLOCK3 = 330;
        static final int OFFSET_Y_BLOCK4 = 175;
        static final int OFFSET_Y_BLOCK5 = 20;

        //Title settings
        int[] leftTitleTarget = new int[]{1,2,3,4,5};
        int[] centerTitleTarget = new int[]{1,2,3,4,5};
        int[] rightTitleTarget = new int[]{1,2,3,4,5};
        int[] titleOffsetX = new int[]{35,250,450};
        int[] titleOffsetY = new int[]{750,595,440,285,130};
        boolean[] leftTitleEnable = new boolean[]{false,false,false,false,false};
        boolean[] centerTitleEnable = new boolean[]{false,false,false,false,false};
        boolean[] rightTitleEnable = new boolean[]{false,false,false,false,false};
        String leftTitleText = "Title of Section I";
        String centerTitleText = "Title of Section I";
        String rightTitleText = "Title of Section I";
        ColorsToPdfBox leftTitleColor = ColorsToPdfBox.GREEN2;
        ColorsToPdfBox centerTitleColor = ColorsToPdfBox.RED2;
        ColorsToPdfBox rightTitleColor = ColorsToPdfBox.GOLD2;
        FontSizeToPdfBox leftTitleSize = FontSizeToPdfBox.MEDIUM;
        FontSizeToPdfBox centerTitleSize = FontSizeToPdfBox.MEDIUM;
        FontSizeToPdfBox rightTitleSize = FontSizeToPdfBox.MEDIUM;
        FontNameToPdfBox leftTitleFont = FontNameToPdfBox.HELVETICA_B;
        FontNameToPdfBox centerTitleFont = FontNameToPdfBox.HELVETICA_B;
        FontNameToPdfBox rightTitleFont = FontNameToPdfBox.HELVETICA_B;

        //Table Settings
        int tableWidth = 540;
        int tableHeight = 90;
        int tableOffsetX = 35;
        int columnWidth = 90;
        int columnHeight = 18;
        int[] tableContainerOffsetY = new int[]{656, 500, 346, 190, 35};
        int[] tableHeaderOffsetY = new int[]{728, 572, 418, 262, 107};
        int[] tableColumnOffsetX = new int[] {35,125,215,305,395,485};
        int[] tableDataOffsetY = new int[]{710, 554, 400, 244, 89};
        boolean[] tableEnable = new boolean[]{false,false,false,false,false};
        ColorsToPdfBox tableHeaderColor = ColorsToPdfBox.BLACK;
        ColorsToPdfBox tableBodyColor = ColorsToPdfBox.WHITE;
        ColorsToPdfBox tableBorderColor = ColorsToPdfBox.BLACK;
        TableDimensionsToPdfBox tableSize = TableDimensionsToPdfBox.TABLE_5X6;

        //Column Settings
        int columnBoxWidth = 170;
        int columnBoxHeight = 90;
        int[] columnBoxOffsetX = new int[]{35,220,405};
        int[] columnBoxOffsetY = new int[]{655,500,345,190,35};
        int[] columnLeftBorderWidth = new int[]{1,1,1,1,1};
        int[] columnCenterBorderWidth = new int[]{1,1,1,1,1};
        int[] columnRightBorderWidth = new int[]{1,1,1,1,1};
        boolean[] columnBoxEnable = new boolean[]{false,false,false,false,false};
        boolean[] columnLeftBorderEnable = new boolean[]{true,true,true,true,true};
        boolean[] columnCenterBorderEnable = new boolean[]{true,true,true,true,true};
        boolean[] columnRightBorderEnable = new boolean[]{true,true,true,true,true};
        ColorsToPdfBox[] columnLeftBackColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnCenterBackColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnRightBackColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnLeftBorderColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnCenterBorderColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnRightBorderColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnLeftTextColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnCenterTextColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };
        ColorsToPdfBox[] columnRightTextColor = new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.BLACK
        };

        //Signature Box Settings
        int signatureBoxWidth = 200;
        int signatureBoxHeight = 100;
        int signatureBoxAdjustOffsetX = 0;
        int[] signatureBoxOffsetX = new int[]{55,210,355};
        int[] signatureBoxOffsetY = new int[]{30,122,100};
        int[] signatureBoxDigitalTitleOffsetX = new int[]{105,260,405};
        int[] signatureBoxContentOffsetX = new int[]{70,230,370};
        boolean signatureBoxBorderEnable = false;
        boolean leftSignatureBoxEnable = false;
        boolean centerSignatureBoxEnable = false;
        boolean rightSignatureBoxEnable = false;
        ColorsToPdfBox signatureBoxColor = ColorsToPdfBox.BLACK;

        //Signature Tape Settings
        int signatureTapeWidth = 500;
        int signatureTapeHeight = 30;
        int signatureTapeOffsetX = 55;
        int signatureTapeOffsetY = 30;
        int signatureTapeTitleOffsetX = 260;
        int signatureTapeTitleOffsetY = 52;
        int signatureTapeValueOffsetX = 130;
        int signatureTapeValueOffsetY = 35;
        int signatureTapeAdjustOffsetX = 0;
        boolean signatureTapeEnable = false;
        ColorsToPdfBox signatureTapeColor = ColorsToPdfBox.BLACK;

        //Signature Details
        String signaturePersonName = "";
        String signaturePersonDoc = "";
        String signatureRecord = "";
        String signatureDateGmt = "";
        String signatureStampMark = "";

        //Text settings
        int lineHeight = 18;
        int textOffsetX = 35;
        int[] textOffsetY = new int[]{732,577,421,266,111};
        boolean[] textEnable = new boolean[]{false,false,false,false,false};
        String textContentOne = "";
        String textContentTwo = "";
        String textContentThree = "";
        String textContentFour = "";
        String textContentFive = "";
        ColorsToPdfBox textColor = ColorsToPdfBox.BLACK;
        FontSizeToPdfBox textSize = FontSizeToPdfBox.NORMAL;
        FontNameToPdfBox textFont = FontNameToPdfBox.HELVETICA;

        //Image Settings
        int[] imageOffsetX = new int[]{35,180,330};
        int[] imageOffsetY = new int[]{650,495,340,185,30};
        boolean[] leftImageEnable = new boolean[]{false,false,false,false,false};
        boolean[] centerImageEnable = new boolean[]{false,false,false,false,false};
        boolean[] rightImageEnable = new boolean[]{false,false,false,false,false};
        String[] leftImagePaths = new String[]{null,null,null,null,null};
        String[] centerImagePaths = new String[]{null,null,null,null,null};
        String[] rightImagePaths = new String[]{null,null,null,null,null};
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxTemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoxOpenTemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimBoxTemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TripleFallTemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Simple1TemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Simple2TemplateSettings {
        //General settings
        int boxQuantity;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Simple3TemplateSettings {
        //General settings
        int boxQuantity;
    }

}
