package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxResources.barcode128;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxResources.qrCode;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxBarcodeFormExtension extends Help4DevsPdfBoxCore {

    private static final int FRAME_DEFAULT_HEIGHT = 30;
    private static final int INITIAL_BC_OFFSET_X = 45;
    private static final int INITIAL_BC_OFFSET_Y = 40;
    private static final int LOGO_IMAGE_WIDTH = 80;
    private static final int LOGO_IMAGE_HEIGHT = 23;
    private static final int QRCODE_WIDTH = 100;
    private static final int QRCODE_HEIGHT = 100;
    private static final int FRAME_LEFT_WIDTH = 400;
    private static final int FRAME_LEFT_COL_WIDTH = 100;

    private void barcodeFormHeaderFrame(PdfBoxBarcodeForm bcFormSettings, PDPageContentStream contentStream) {

        int frameOffsetY = 310+(bcFormSettings.adjustOffsetY);
        int borderWidth = 1;
        int[] frameOffsetX = new int[]{30,30,130,210};
        int[] widthFrame = new int[]{550,100,80,370};

        try {

            for (int i = 0; i < widthFrame.length; i++) {
                contentStream.setLineWidth(borderWidth);
                contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                contentStream.addRect(
                        frameOffsetX[i] + (bcFormSettings.adjustOffsetX),
                        frameOffsetY,
                        widthFrame[i],
                        FRAME_DEFAULT_HEIGHT);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormHeaderValues(
            PDDocument document,
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        int logoOffsetX = 40+(bcFormSettings.adjustOffsetX);
        int dataOffsetY = 315+(bcFormSettings.adjustOffsetY);
        int[] dataOffsetX = new int[]{140,241};

        try {
            //Header Content - Logo Image
            if (!bcFormSettings.isRevealFields()) {

                contentStreamImage(
                        bcFormSettings.getData().getDataHeaderImage(),
                        LOGO_IMAGE_WIDTH,
                        LOGO_IMAGE_HEIGHT,
                        logoOffsetX,
                        dataOffsetY,
                        document,
                        contentStream);

            } else {
                pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
                pageSettings.setOffsetX(logoOffsetX);
                pageSettings.setOffsetY(dataOffsetY+3);
                contentStreamText("IMAGE", pageSettings, contentStream);
            }

            //Header Content - Operator Code + Barcode Text
            for (int j = 0; j < 2; j++) {

                pageSettings.setOffsetX(dataOffsetX[j]+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(dataOffsetY);

                if (j == 0) {
                    pageSettings.setFontSize(FontSizeToPdfBox.PLUS);
                } else {
                    pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
                }

                pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);

                if (j == 0) {
                    contentStreamText(bcFormSettings.getData().getDataHeaderOperator(), pageSettings, contentStream);
                } else {
                    contentStreamText(bcFormSettings.getData().getDataHeaderBarcode(), pageSettings, contentStream);
                }
            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyFrame(PdfBoxBarcodeForm bcFormSettings, PDPageContentStream contentStream) {

        try {

            //Left Side
            int frameLeftHeight = 20;
            int frameLeftOffsetX = 30+(bcFormSettings.adjustOffsetX);
            int[] frameLeftColOffsetX = new int[]{30,130,230,330};
            int[] frameLeftOffsetY = new int[]{290,270,250,230,130,100};

            //Frame
            contentStream.setLineWidth(bcFormSettings.getBorderWidth());
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    frameLeftOffsetX,
                    100+(bcFormSettings.adjustOffsetY),
                    550,
                    210);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            for (int j = 0; j < frameLeftOffsetY.length; j++) {

                if (j == 4) {

                    int width = 300;
                    if (!bcFormSettings.isQrcode()) {
                        width = 400;
                    }

                    contentStream.setLineWidth(bcFormSettings.getBorderWidth());
                    contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                    contentStream.addRect(
                            frameLeftOffsetX,
                            frameLeftOffsetY[j]+(bcFormSettings.adjustOffsetY),
                            width,
                            100);
                    contentStream.closeAndStroke();
                    contentStream.setStrokingColor(0, 0, 0);

                    //Qrcode
                    if (bcFormSettings.isQrcode()) {
                        contentStream.setLineWidth(bcFormSettings.getBorderWidth());
                        contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                        contentStream.addRect(
                                330+(bcFormSettings.adjustOffsetX),
                                frameLeftOffsetY[j]+(bcFormSettings.adjustOffsetY),
                                QRCODE_WIDTH,
                                QRCODE_HEIGHT);
                        contentStream.closeAndStroke();
                        contentStream.setStrokingColor(0, 0, 0);
                    }
                    continue;
                }

                if (j == 5) {
                    frameLeftHeight += 10;
                }

                contentStream.setLineWidth(bcFormSettings.getBorderWidth());
                contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                contentStream.addRect(
                        frameLeftOffsetX,
                        frameLeftOffsetY[j]+(bcFormSettings.adjustOffsetY),
                        FRAME_LEFT_WIDTH,
                        frameLeftHeight);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);

                if (j == 2 || j == 3) {
                    for (int leftColOffsetX : frameLeftColOffsetX) {
                        contentStream.setLineWidth(bcFormSettings.getBorderWidth());
                        contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                        contentStream.addRect(
                                leftColOffsetX+(bcFormSettings.adjustOffsetX),
                                frameLeftOffsetY[j]+(bcFormSettings.adjustOffsetY),
                                FRAME_LEFT_COL_WIDTH,
                                frameLeftHeight);
                        contentStream.closeAndStroke();
                        contentStream.setStrokingColor(0, 0, 0);
                    }
                }
            }

            //Right Side
            int frameRightWidth = 150;
            int frameRightHeight = 20;
            int frameRightOffsetX = 430+(bcFormSettings.adjustOffsetX);
            int[] frameRightOffsetY = new int[]{290,270,250,230,210,190,170,150,130,100};

            for (int offsetY : frameRightOffsetY) {

                if (offsetY == 100) {
                    frameRightHeight += 10;
                }

                contentStream.setLineWidth(bcFormSettings.getBorderWidth());
                contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                contentStream.addRect(
                        frameRightOffsetX,
                        offsetY+(bcFormSettings.adjustOffsetY),
                        frameRightWidth,
                        frameRightHeight);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyLeftFields(
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        try {

            /*NOTE: FieldLeft12 is reserved to Qrcode when it is enabled to appear in the form*/
            int[] fieldsOffsetX = new int[]{35,35,35,135,235,335,35,135,235,335,35,335,35};
            int[] fieldsOffsetY = new int[]{302,281,261,261,261,261,242,242,242,242,220,222,120};

            List<String> fieldList = new ArrayList<>();
            fieldList.add(bcFormSettings.getFields().getFieldLeft1());
            fieldList.add(bcFormSettings.getFields().getFieldLeft2());
            fieldList.add(bcFormSettings.getFields().getFieldLeft3());
            fieldList.add(bcFormSettings.getFields().getFieldLeft4());
            fieldList.add(bcFormSettings.getFields().getFieldLeft5());
            fieldList.add(bcFormSettings.getFields().getFieldLeft6());
            fieldList.add(bcFormSettings.getFields().getFieldLeft7());
            fieldList.add(bcFormSettings.getFields().getFieldLeft8());
            fieldList.add(bcFormSettings.getFields().getFieldLeft9());
            fieldList.add(bcFormSettings.getFields().getFieldLeft10());
            fieldList.add(bcFormSettings.getFields().getFieldLeft11());
            if (bcFormSettings.isQrcode()) {
                fieldList.add(bcFormSettings.getFields().getFieldLeft12());
            } else {
                fieldList.add(" ");
            }
            fieldList.add(bcFormSettings.getFields().getFieldLeft13());

            pageSettings.setFontSize(FontSizeToPdfBox.M_SMALL);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);

            int index = 0;
            for (String field : fieldList) {

                pageSettings.setOffsetX(fieldsOffsetX[index]+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(fieldsOffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(field, pageSettings, contentStream);

                index += 1;

            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyRightFields(
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        try {

            int fieldsOffsetX = 435;
            int[] fieldsOffsetY = new int[]{302,281,261,241,221,201,181,161,141,105};

            List<String> fieldList = new ArrayList<>();
            fieldList.add(bcFormSettings.getFields().getFieldRight1());
            fieldList.add(bcFormSettings.getFields().getFieldRight2());
            fieldList.add(bcFormSettings.getFields().getFieldRight3());
            fieldList.add(bcFormSettings.getFields().getFieldRight4());
            fieldList.add(bcFormSettings.getFields().getFieldRight5());
            fieldList.add(bcFormSettings.getFields().getFieldRight6());
            fieldList.add(bcFormSettings.getFields().getFieldRight7());
            fieldList.add(bcFormSettings.getFields().getFieldRight8());
            fieldList.add(bcFormSettings.getFields().getFieldRight9());
            fieldList.add(bcFormSettings.getFields().getFieldRight10());

            pageSettings.setFontSize(FontSizeToPdfBox.M_SMALL);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);

            int index = 0;
            for (String field : fieldList) {

                pageSettings.setOffsetX(435+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(fieldsOffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(field, pageSettings, contentStream);

                index += 1;

                //Turns the last element with normal font (without bold)
                if (index == fieldList.size()-1) {
                    pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
                }

            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyFooterFields(
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        try {
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            //Left Field
            pageSettings.setOffsetX(35+(bcFormSettings.adjustOffsetX));
            pageSettings.setOffsetY(90+(bcFormSettings.adjustOffsetY));
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            contentStreamText(bcFormSettings.getFields().getFieldFooter1(), pageSettings, contentStream);

            //Right Field
            pageSettings.setOffsetX(390+(bcFormSettings.adjustOffsetX));
            pageSettings.setOffsetY(90+(bcFormSettings.adjustOffsetY));
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            contentStreamText(bcFormSettings.getFields().getFieldFooter2(), pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyFinalize(PdfBoxBarcodeForm bcFormSettings, PDPageContentStream contentStream) {

        int frameLeftOffsetX = 30+(bcFormSettings.adjustOffsetX);
        int frameLeftOffsetY = 100+(bcFormSettings.adjustOffsetY);

        try {

            if (bcFormSettings.getBorderStyle().equals(QrCodeBorderStyleToPdfBox.BORDERLESS)) {
                contentStream.setLineWidth(3);
                contentStream.setStrokingColor(color(ColorsToPdfBox.WHITE));
                contentStream.addRect(frameLeftOffsetX, frameLeftOffsetY, 550, 240);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0,0,0);
                return;
            }

            if (bcFormSettings.getBorderStyle().equals(QrCodeBorderStyleToPdfBox.LEFT_BORDERED)) {
                contentStream.setLineWidth(3);
                contentStream.setStrokingColor(color(ColorsToPdfBox.WHITE));
                contentStream.addRect(
                        580+(bcFormSettings.adjustOffsetX),
                        99+(bcFormSettings.adjustOffsetY),
                        2,
                        240);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);

                contentStream.setLineWidth(3);
                contentStream.setStrokingColor(color(ColorsToPdfBox.WHITE));
                contentStream.addRect(
                        30+(bcFormSettings.adjustOffsetX),
                        340+(bcFormSettings.adjustOffsetY),
                        553,
                        1);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);
                return;
            }

            if (bcFormSettings.getBorderStyle().equals(QrCodeBorderStyleToPdfBox.BORDERED)) {
                contentStream.setLineWidth(1);
                contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
                contentStream.addRect(frameLeftOffsetX, frameLeftOffsetY, 550, 240);
                contentStream.closeAndStroke();
                contentStream.setStrokingColor(0, 0, 0);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyLeftValues(
            PDDocument document,
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        try {

            //Box 1...10 (Box 12 is reserved for Qrcode)
            int[] valuesOffsetX = new int[]{35,35,35,135,235,335,35,135,235,335};
            int[] valuesOffsetY = new int[]{292,272,252,252,252,252,233,233,233,233};
            //Box 11
            int[] extraValues1OffsetX = new int[]{35,35,35,35,35,35,35,35};
            int[] extraValues1OffsetY = new int[]{205,195,185,175,165,155,145,135};
            //Box 13
            int[] extraValues2OffsetX = new int[]{70,70,70};
            int[] extraValues2OffsetY = new int[]{122,112,102};

            List<String> dataList = new ArrayList<>();
            dataList.add(bcFormSettings.getData().getDataLeft1());
            dataList.add(bcFormSettings.getData().getDataLeft2());
            dataList.add(bcFormSettings.getData().getDataLeft3());
            dataList.add(bcFormSettings.getData().getDataLeft4());
            dataList.add(bcFormSettings.getData().getDataLeft5());
            dataList.add(bcFormSettings.getData().getDataLeft6());
            dataList.add(bcFormSettings.getData().getDataLeft7());
            dataList.add(bcFormSettings.getData().getDataLeft8());
            dataList.add(bcFormSettings.getData().getDataLeft9());
            dataList.add(bcFormSettings.getData().getDataLeft10());

            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);

            int index = 0;

            //Box 1...10: Values
            for (String value : dataList) {
                pageSettings.setOffsetX(valuesOffsetX[index]+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(valuesOffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(value, pageSettings, contentStream);

                index += 1;
            }

            index = 0;

            //Box 11: Extra Values
            for (String extraValue : bcFormSettings.getData().getDataLeft11()) {
                if (index > 0) {
                    pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
                }

                pageSettings.setOffsetX(extraValues1OffsetX[index]+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(extraValues1OffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(extraValue, pageSettings, contentStream);

                index += 1;
            }

            index = 0;
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);

            //Box 13: Extra Values
            for (String extraValue : bcFormSettings.getData().getDataLeft13()) {
                pageSettings.setOffsetX(extraValues2OffsetX[index]+(bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(extraValues2OffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(extraValue, pageSettings, contentStream);

                index += 1;
            }

            //Box 12
            if (bcFormSettings.isQrcode()) {
                PdfBoxQrCode qrCode = new PdfBoxQrCode();
                qrCode.setDpi(400);
                qrCode.setMargin(0);
                qrCode.setMatrix(0);
                qrCode.setSize(70);
                qrCode.setOnColor(0xFF000001);
                qrCode.setOffColor(0xFFFFFFFF);
                qrCode.setOffsetX(345+(bcFormSettings.adjustOffsetX));
                qrCode.setOffsetY(145+(bcFormSettings.adjustOffsetY));
                qrCode.setData(bcFormSettings.getData().getDataLeft12());
                qrCode.setCodeType4Scanner(CodeType4ScannerToPdfBox.QRCODE);
                qrCode(qrCode, document, contentStream);
            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyRightValues(
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        try {

            int valuesOffsetX = 435;
            int[] valuesOffsetY = new int[]{292,272,252,232,212,192,172,152,132};

            List<String> valuesList = new ArrayList<>();
            valuesList.add(bcFormSettings.getData().getDataRight1());
            valuesList.add(bcFormSettings.getData().getDataRight2());
            valuesList.add(bcFormSettings.getData().getDataRight3());
            valuesList.add(bcFormSettings.getData().getDataRight4());
            valuesList.add(bcFormSettings.getData().getDataRight5());
            valuesList.add(bcFormSettings.getData().getDataRight6());
            valuesList.add(bcFormSettings.getData().getDataRight7());
            valuesList.add(bcFormSettings.getData().getDataRight8());
            valuesList.add(bcFormSettings.getData().getDataRight9());

            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);

            int index = 0;
            for (String value : valuesList) {

                pageSettings.setOffsetX(assertBarcodeFormOffsetX(value, valuesOffsetX, bcFormSettings.adjustOffsetX));
                pageSettings.setOffsetY(valuesOffsetY[index]+(bcFormSettings.adjustOffsetY));
                contentStreamText(value, pageSettings, contentStream);

                index += 1;

            }

            pageSettings.setFontSize(FontSizeToPdfBox.PLUS);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setOffsetX(480+(bcFormSettings.adjustOffsetX));
            pageSettings.setOffsetY(110+(bcFormSettings.adjustOffsetY));
            contentStreamText(bcFormSettings.getData().getDataRight10(),pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private void barcodeFormBodyFooterValues(
            PDDocument document,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        bcFormSettings.getBarcode().setOffsetX(INITIAL_BC_OFFSET_X+(bcFormSettings.adjustOffsetX));
        bcFormSettings.getBarcode().setOffsetY(INITIAL_BC_OFFSET_Y+(bcFormSettings.adjustOffsetY));
        barcode128(bcFormSettings.getBarcode(), document, contentStream);
    }

    public void barcodeFormBuild(
            PDDocument document,
            PdfBoxPage pageSettings,
            PdfBoxBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        if (bcFormSettings.isRevealFields()) {
            bcFormSettings = new PdfBoxBarcodeForm();
            bcFormSettings.setRevealFields(true);
        }
        /*Frame*/
        this.barcodeFormHeaderFrame(bcFormSettings, contentStream);
        this.barcodeFormBodyFrame(bcFormSettings, contentStream);
        this.barcodeFormBodyFinalize(bcFormSettings, contentStream);
        /*Fields*/
        this.barcodeFormBodyLeftFields(pageSettings, bcFormSettings, contentStream);
        this.barcodeFormBodyRightFields(pageSettings, bcFormSettings, contentStream);
        this.barcodeFormBodyFooterFields(pageSettings, bcFormSettings, contentStream);
        /*Values*/
        this.barcodeFormHeaderValues(document, pageSettings, bcFormSettings, contentStream);
        this.barcodeFormBodyLeftValues(document, pageSettings, bcFormSettings, contentStream);
        this.barcodeFormBodyRightValues(pageSettings, bcFormSettings, contentStream);
        this.barcodeFormBodyFooterValues(document, bcFormSettings, contentStream);
    }

}
