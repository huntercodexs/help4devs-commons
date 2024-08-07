package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.PdfBoxTemplates.template;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.SlimTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateService extends Help4DevsPdfBoxService {

    private static void drawContainer(
            PDDocument document,
            PDPage page,
            PdfBoxContainer rectSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        for (int n = 1; n <= BOX_QUANTITY; n++) {

            if (n == 1) {
                rectSettings.setOffsetY(OFFSET_Y_BLOCK1);
            } else if (n == 2) {
                rectSettings.setOffsetY(OFFSET_Y_BLOCK2);
            } else if (n == 3) {
                rectSettings.setOffsetY(OFFSET_Y_BLOCK3);
            } else if (n == 4) {
                rectSettings.setOffsetY(OFFSET_Y_BLOCK4);
            } else {
                rectSettings.setOffsetY(OFFSET_Y_BLOCK5);
            }

            if (rectSettings.getBackColor() == null) {
                contentStream("rec-empty", page, document, pageSettings, rectSettings, contentStream);

            } else if (rectSettings.getBackColor().getColorName().equals(ColorsToPdfBox.NONE.getColorName())) {
                contentStream("rec-empty", page, document, pageSettings, rectSettings, contentStream);

            } else {
                contentStream("rec-fill", page, document, pageSettings, rectSettings, contentStream);
            }

            if (rectSettings.isBorder()) {
                contentStream("rec-border", page, document, pageSettings, rectSettings, contentStream);
            }
        }
    }

    private static void drawBackground(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        if (settings.getImageBackground() == null) return;

        try {
            PDImageXObject pdfImageBackground = PDImageXObject.createFromFile(settings.getImageBackground(), document);
            contentStream.drawImage(pdfImageBackground, 0, 0, 620, 792);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawLines(
            int index,
            PDDocument document,
            PDPage page,
            List<String[]> listLines,
            boolean hasTitle,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream("text", page, document, pageSettings, null, contentStream);

            int stop = 0;
            for (String line : listLines.get(index)) {
                if (stop == 5 && hasTitle) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawTitle(
            PDDocument document,
            PDPage page,
            String title,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream("text", page, document, pageSettings, null, contentStream);
            contentStream.showText(title);
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawColumnBox(
            int box,
            PdfBoxPage pageSettings,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        int columnHeight = settings.columnBoxHeight;

        if (!hasTitle(box, settings)) {
            columnHeight = columnHeight + settings.getLineHeight();
        }

        try {

            //Left Column
            if (settings.columnBoxLeftEnable[box]) {
                if (settings.columnBoxLeftBorderEnable[box]) {
                    contentStream.setLineWidth(settings.columnBoxLeftBorderWidth[box]);
                    contentStream.setStrokingColor(color(settings.columnBoxLeftBorderColor[box]));
                    contentStream.addRect(
                            settings.columnBoxOffsetX[0],
                            settings.columnBoxOffsetY[box],
                            settings.columnBoxWidth,
                            columnHeight);
                    contentStream.closeAndStroke();

                    if (settings.columnBoxLeftBackColor[box] != null) {
                        contentStream.setNonStrokingColor(color(settings.columnBoxLeftBackColor[box]));
                        contentStream.addRect(
                                settings.columnBoxOffsetX[0],
                                settings.columnBoxOffsetY[box],
                                settings.columnBoxWidth,
                                columnHeight);
                        contentStream.fill();
                    }
                }
            }

            //Center Column
            if (settings.columnBoxCenterEnable[box]) {
                if (settings.columnBoxCenterBorderEnable[box]) {
                    contentStream.setLineWidth(settings.columnBoxCenterBorderWidth[box]);
                    contentStream.setStrokingColor(color(settings.columnBoxCenterBorderColor[box]));
                    contentStream.addRect(
                            settings.columnBoxOffsetX[1],
                            settings.columnBoxOffsetY[box],
                            settings.columnBoxWidth,
                            columnHeight);
                    contentStream.closeAndStroke();

                    if (settings.columnBoxCenterBackColor[box] != null) {
                        contentStream.setNonStrokingColor(color(settings.columnBoxCenterBackColor[box]));
                        contentStream.addRect(
                                settings.columnBoxOffsetX[1],
                                settings.columnBoxOffsetY[box],
                                settings.columnBoxWidth,
                                columnHeight);
                        contentStream.fill();
                    }
                }
            }

            //Right Column
            if (settings.columnBoxRightEnable[box]) {
                if (settings.columnBoxRightBorderEnable[box]) {
                    contentStream.setLineWidth(settings.columnBoxRightBorderWidth[box]);
                    contentStream.setStrokingColor(color(settings.columnBoxRightBorderColor[box]));
                    contentStream.addRect(
                            settings.columnBoxOffsetX[2],
                            settings.columnBoxOffsetY[box],
                            settings.columnBoxWidth,
                            columnHeight);
                    contentStream.closeAndStroke();

                    if (settings.columnBoxRightBackColor[box] != null) {
                        contentStream.setNonStrokingColor(color(settings.columnBoxRightBackColor[box]));
                        contentStream.addRect(
                                settings.columnBoxOffsetX[2],
                                settings.columnBoxOffsetY[box],
                                settings.columnBoxWidth,
                                columnHeight);
                        contentStream.fill();
                    }
                }
            }

            //Reset Color
            contentStream.setStrokingColor(0,0,0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawColumnContent(
            int box,
            PDDocument document,
            PDPage page,
            List<String[]> listLines,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();
        PdfBoxPage pageSettings = settings.getPage();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        int heightAdjust = 0;
        boolean hasTitle = hasTitle(box, slimSettings);

        if (!hasTitle) {
            heightAdjust = slimSettings.getLineHeight();
        }

        int offsetX;
        int offsetY;

        //Text Left Column
        if (slimSettings.columnBoxLeftEnable[box]) {

            pageSettings.setFontName(slimSettings.columnBoxLeftFontName[box]);
            pageSettings.setFontSize(slimSettings.columnBoxLeftFontSize[box]);
            pageSettings.setFontColor(slimSettings.columnBoxLeftTextColor[box]);
            pageSettings.setLineHeight(slimSettings.columnBoxLeftLineHeight[box]);

            offsetX = slimSettings.columnBoxOffsetX[0] + slimSettings.columnBoxLeftPadding[box];
            offsetY = slimSettings.columnBoxOffsetY[box];
            offsetY = offsetY + heightAdjust + 75 - slimSettings.columnBoxLeftPadding[box];

            pageSettings.setOffsetX(offsetX+slimSettings.columnBoxLeftAdjustmentX[box]);
            pageSettings.setOffsetY(offsetY+slimSettings.columnBoxLeftAdjustmentY[box]);

            drawLines(0, document, page, listLines, hasTitle, pageSettings, contentStream);
        }

        //Text Center Column
        if (slimSettings.columnBoxCenterEnable[box]) {

            pageSettings.setFontName(slimSettings.columnBoxCenterFontName[box]);
            pageSettings.setFontSize(slimSettings.columnBoxCenterFontSize[box]);
            pageSettings.setFontColor(slimSettings.columnBoxCenterTextColor[box]);
            pageSettings.setLineHeight(slimSettings.columnBoxCenterLineHeight[box]);

            offsetX = slimSettings.columnBoxOffsetX[1] + slimSettings.columnBoxCenterPadding[box];
            offsetY = slimSettings.columnBoxOffsetY[box];
            offsetY = offsetY + heightAdjust + 75 - slimSettings.columnBoxCenterPadding[box];

            pageSettings.setOffsetX(offsetX+slimSettings.columnBoxCenterAdjustmentX[box]);
            pageSettings.setOffsetY(offsetY+slimSettings.columnBoxCenterAdjustmentY[box]);

            drawLines(1, document, page, listLines, hasTitle, pageSettings, contentStream);
        }

        //Text Right Column
        if (slimSettings.columnBoxRightEnable[box]) {

            pageSettings.setFontName(slimSettings.columnBoxRightFontName[box]);
            pageSettings.setFontSize(slimSettings.columnBoxRightFontSize[box]);
            pageSettings.setFontColor(slimSettings.columnBoxRightTextColor[box]);
            pageSettings.setLineHeight(slimSettings.columnBoxRightLineHeight[box]);

            offsetX = slimSettings.columnBoxOffsetX[2] + slimSettings.columnBoxRightPadding[box];
            offsetY = slimSettings.columnBoxOffsetY[box];
            offsetY = offsetY + heightAdjust + 75 - slimSettings.columnBoxRightPadding[box];

            pageSettings.setOffsetX(offsetX+slimSettings.columnBoxRightAdjustmentX[box]);
            pageSettings.setOffsetY(offsetY+slimSettings.columnBoxRightAdjustmentY[box]);

            drawLines(2, document, page, listLines, hasTitle, pageSettings, contentStream);
        }
    }

    private static void drawTableContainer(
            int box,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.setNonStrokingColor(color(settings.tableBodyColor));
            contentStream.setStrokingColor(color(settings.tableBorderColor));
            contentStream.addRect(
                    settings.tableOffsetX,
                    settings.tableContainerOffsetY[box],
                    settings.tableWidth,
                    settings.tableHeight);
            contentStream.fillAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static int correctTableOffsetX(int column, SlimTemplateSettings settings) {
        if (column == 0) {
            return settings.tableOffsetX;
        }

        int correctOffsetX = settings.tableColumnOffsetX[column-1] + settings.tableColumnWidth;

        if (settings.tableColumnOffsetX[column] != correctOffsetX) {
            return correctOffsetX;
        }
        return settings.tableColumnOffsetX[column];
    }

    private static int correctTableOffsetY(int box, int ref, SlimTemplateSettings settings) {
        if (box == 0) {
            return settings.tableContainerOffsetY[0]+ref;
        }

        int correctOffsetY = settings.tableContainerOffsetY[box] + ref;

        if (settings.tableContainerOffsetY[box] != correctOffsetY) {
            return correctOffsetY;
        }
        return settings.tableContainerOffsetY[box];
    }

    private static int correctTableWidth(SlimTemplateSettings settings) {
        int result = settings.tableColumnWidth * settings.tableSize.getTableColumns();
        if (result != settings.tableWidth) {
            return settings.tableWidth / settings.tableSize.getTableColumns();
        }
        return settings.tableColumnWidth;
    }

    private static int correctTableHeight(SlimTemplateSettings settings) {
        int result = settings.tableColumnHeight * settings.tableSize.getTableLines();
        if (result != settings.tableHeight) {
            return settings.tableHeight / settings.tableSize.getTableLines();
        }
        return settings.tableColumnHeight;
    }

    private static void drawTableHeader(
            int box,
            int column,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            /*Auto Fix: Prevent incorrect settings*/
            settings.tableColumnOffsetX[column] = correctTableOffsetX(column, settings);
            settings.tableHeaderOffsetY[box] = correctTableOffsetY(box, 72, settings);
            settings.tableColumnWidth = correctTableWidth(settings);

            contentStream.setStrokingColor(color(settings.tableBorderColor));
            contentStream.setNonStrokingColor(color(settings.tableHeaderColor));
            contentStream.addRect(
                    settings.tableColumnOffsetX[column],
                    settings.tableHeaderOffsetY[box],
                    settings.tableColumnWidth,
                    settings.tableHeaderHeight);
            contentStream.fillAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawTableHeaderContent(
            int box,
            int column,
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings pdfBoxTemplateSettings,
            PDPageContentStream contentStream
    ) {
        try {

            SlimTemplateSettings settings = pdfBoxTemplateSettings.getSlim();
            PdfBoxPage pageSettings = pdfBoxTemplateSettings.getPage();
            SlimDataContent content = pdfBoxTemplateSettings.getSlimContent();

            pageSettings.setOffsetX(settings.tableColumnOffsetX[column]+(settings.tableHeaderAdjustOffsetX));
            pageSettings.setOffsetY(settings.tableDataOffsetY[box]+(settings.tableHeaderAdjustOffsetY));
            pageSettings.setFontName(settings.tableHeaderFontName);
            pageSettings.setFontSize(settings.tableHeaderFontSize);
            pageSettings.setFontColor(settings.tableHeaderFontColor);

            contentStream("text", page, document, pageSettings, null, contentStream);

            contentStream.showText(content.getTableHeaderContent().get(box).get(column));
            contentStream.newLine();

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawTableBody(
            int initialX,
            int initialY,
            int celWidth,
            int celHeight,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.setStrokingColor(color(settings.tableBorderColor));
            contentStream.addRect(
                    initialX,
                    initialY,
                    celWidth,
                    celHeight);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawImage(
            PDDocument document,
            PdfBoxImage imgSettings,
            PDPageContentStream contentStream
    ) {
        try {

            PDImageXObject pdImageXObject = PDImageXObject.createFromFile(imgSettings.getFilenamePath(), document);

            if (imgSettings.isResize()) {
                pdImageXObject.setWidth(imgSettings.getWidth());
                pdImageXObject.setHeight(imgSettings.getHeight());
            }

            contentStream.drawImage(pdImageXObject, imgSettings.getOffsetX(), imgSettings.getOffsetY());

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawTableBodyContent(
            int box,
            int column,
            int adjustOffsetY,
            String content,
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings pdfBoxTemplateSettings,
            PDPageContentStream contentStream
    ) {
        try {

            SlimTemplateSettings settings = pdfBoxTemplateSettings.getSlim();
            PdfBoxPage pageSettings = pdfBoxTemplateSettings.getPage();

            pageSettings.setOffsetX(settings.tableColumnOffsetX[column]+(settings.tableBodyAdjustOffsetX));
            pageSettings.setOffsetY(settings.tableDataOffsetY[box]+(settings.tableBodyAdjustOffsetY)-adjustOffsetY);
            pageSettings.setFontName(settings.tableBodyFontName);
            pageSettings.setFontSize(settings.tableBodyFontSize);
            pageSettings.setFontColor(settings.tableBodyFontColor);

            contentStream("text", page, document, pageSettings, null, contentStream);

            contentStream.showText(content);
            contentStream.newLine();

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawSignatureBox(
            int index,
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PdfBoxContainer rectSettings,
            SlimTemplateSettings settings,
            SlimDataContent slimData,
            PDPageContentStream contentStream
    ) {
        try {

            //Signature Box
            contentStream.setStrokingColor(color(settings.getSignatureBoxColor()));
            contentStream.addRect(
                    settings.signatureBoxOffsetX[index],
                    settings.signatureBoxOffsetY[0],
                    settings.getSignatureBoxWidth(),
                    settings.getSignatureBoxHeight());
            if (settings.signatureBoxBorderEnable) {
                contentStream.closeAndStroke();
            }
            contentStream.setStrokingColor(0,0,0);

            //Signature Title
            pageSettings.setOffsetX(settings.signatureBoxDigitalTitleOffsetX[index]);
            pageSettings.setOffsetY(settings.signatureBoxOffsetY[1]);
            pageSettings.setFontColor(settings.getSignatureBoxColor());
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "text", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //Signature Value
            pageSettings.setOffsetX(settings.signatureBoxContentOffsetX[index]+(settings.getSignatureBoxAdjustOffsetX()));
            pageSettings.setOffsetY(settings.signatureBoxOffsetY[2]);
            pageSettings.setFontColor(settings.getSignatureBoxColor());
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            pageSettings.setLineHeight(16);

            contentStream = contentStream(
                    "text", page, document, pageSettings, rectSettings, contentStream);
            contentStream.setLeading(pageSettings.getLineHeight());

            contentStream.showText(slimData.getSignaturePersonName());
            contentStream.newLine();
            contentStream.showText("DOC:"+slimData.getSignaturePersonDoc());
            contentStream.newLine();
            contentStream.showText(slimData.getSignatureRecord());
            contentStream.newLine();
            contentStream.showText(slimData.getSignatureDateGmt());
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawSignatureTape(
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PdfBoxContainer rectSettings,
            SlimTemplateSettings settings,
            SlimDataContent slimData,
            PDPageContentStream contentStream
    ) {
        try {

            //Signature Box
            contentStream.setStrokingColor(color(settings.getSignatureTapeColor()));
            contentStream.addRect(
                    settings.getSignatureTapeOffsetX(),
                    settings.getSignatureTapeOffsetY(),
                    settings.getSignatureTapeWidth(),
                    settings.getSignatureTapeHeight());
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //Signature Title
            pageSettings.setOffsetX(settings.getSignatureTapeTitleOffsetX());
            pageSettings.setOffsetY(settings.getSignatureTapeTitleOffsetY());
            pageSettings.setFontColor(settings.getSignatureTapeColor());
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "text", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //Signature Value
            pageSettings.setOffsetX(settings.getSignatureTapeValueOffsetX()+(settings.getSignatureTapeAdjustOffsetX()));
            pageSettings.setOffsetY(settings.getSignatureTapeValueOffsetY());
            pageSettings.setFontColor(settings.getSignatureTapeColor());
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "text", page, document, pageSettings, rectSettings, contentStream);

            String signature = slimData.getSignaturePersonName();
            signature = signature + " - DOC:" + slimData.getSignaturePersonDoc();
            signature = signature + " - " + slimData.getSignatureRecord();
            signature = signature + " - " + slimData.getSignatureDateGmt();
            contentStream.showText(signature);

            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawText(
            int box,
            PDDocument document,
            PDPage page,
            List<String[]> listLines,
            SlimTemplateSettings slimSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        boolean hasTitle = hasTitle(box, slimSettings);

        if (!hasTitle) {
            pageSettings.setOffsetY(pageSettings.getOffsetY()+slimSettings.getLineHeight());
        }

        drawLines(box, document, page, listLines, hasTitle, pageSettings, contentStream);
    }

    private static void drawBarcode(
            PDDocument document,
            PdfBoxBarcode settings,
            PDPageContentStream contentStream
    ) {
        switch (settings.getCodeType4Scanner().name()) {
            case "CODE128":
                barcode128(settings, document, contentStream);
                break;
            case "CODE39":
                barcode39(settings, document, contentStream);
                break;
            case "PDF417":
                barcodePdf417(settings, document, contentStream);
                break;
        }
    }

    private static void drawBarcodeInfo(
            int box,
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            int[] infoOffsetY = new int[]{750, 595, 440, 285, 130};

            pageSettings.setOffsetX(55);
            pageSettings.setOffsetY(infoOffsetY[box]);
            pageSettings.setLineHeight(12);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream("text", page, document, pageSettings, null, contentStream);

            contentStream.showText(settings.getSlimContent().getBarcodeInfoOne().get(box));
            contentStream.newLine();

            contentStream.showText(settings.getSlimContent().getBarcodeInfoTwo().get(box));
            contentStream.newLine();

            contentStream.showText(settings.getSlimContent().getBarcodeInfoThree().get(box));
            contentStream.newLine();

            contentStream.showText(settings.getSlimContent().getBarcodeInfoFour().get(box));
            contentStream.newLine();

            contentStream.endText();

            int[] valueOffsetY = new int[]{690, 535, 380, 225, 70};

            pageSettings.setOffsetX(85);
            pageSettings.setOffsetY(valueOffsetY[box]);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

            contentStream("text", page, document, pageSettings, null, contentStream);

            contentStream.showText(settings.getSlimContent().getBarcodeValue().get(box));
            contentStream.newLine();

            contentStream.endText();

            int[] amountOffsetY = new int[]{745, 590, 435, 280, 135};

            pageSettings.setOffsetX(270);
            pageSettings.setOffsetY(amountOffsetY[box]);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

            contentStream("text", page, document, pageSettings, null, contentStream);

            contentStream.showText(settings.getSlimContent().getBarcodeAmount().get(box));
            contentStream.newLine();

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawQRCode(
            PDDocument document,
            PdfBoxQrCode qrCode,
            PDPageContentStream contentStream
    ) {
        qrCode(qrCode, document, contentStream);
    }

    private static void drawQRCodeInfo(
            int box,
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            int[] infoOffsetX = new int[]{145, 365, 365};
            int[] infoOffsetY = new int[]{745, 595, 440, 285, 130};

            pageSettings.setLineHeight(12);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            //Left
            if (settings.getSlim().qrCodeLeftEnable[box]) {
                pageSettings.setOffsetX(infoOffsetX[0]);
                pageSettings.setOffsetY(infoOffsetY[box]);

                contentStream("text", page, document, pageSettings, null, contentStream);

                contentStream.showText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeAmount().get(box * 3));
                contentStream.newLine();

                contentStream.endText();
            }

            //Center
            if (settings.getSlim().qrCodeCenterEnable[box]) {
                pageSettings.setOffsetX(infoOffsetX[1]);
                pageSettings.setOffsetY(infoOffsetY[box]);

                contentStream("text", page, document, pageSettings, null, contentStream);

                contentStream.showText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3 + 1));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3 + 1));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3 + 1));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3 + 1));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeAmount().get(box * 3 + 1));
                contentStream.newLine();

                contentStream.endText();
            }

            //Right
            if (settings.getSlim().qrCodeRightEnable[box]) {
                pageSettings.setOffsetX(infoOffsetX[2]);
                pageSettings.setOffsetY(infoOffsetY[box]);

                contentStream("text", page, document, pageSettings, null, contentStream);

                contentStream.showText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3 + 2));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3 + 2));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3 + 2));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3 + 2));
                contentStream.newLine();

                contentStream.showText(settings.getSlimContent().getQrCodeAmount().get(box * 3 + 2));
                contentStream.newLine();

                contentStream.endText();
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(DEFAULT_WIDTH);
        rectSettings.setHeight(DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        drawContainer(document, page, rectSettings, pageSettings, contentStream);
    }

    private static void slimContainerTitleCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();
        SlimDataContent slimData = settings.getSlimContent();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();

        for (int n = 0; n < BOX_QUANTITY; n++) {

            //Left Title
            if (slimSettings.leftTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[0]+(slimSettings.leftTitleAdjustmentX));
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]+(slimSettings.leftTitleAdjustmentY));

                pageSettings.setFontColor(slimSettings.getLeftTitleColor());
                pageSettings.setFontName(slimSettings.getLeftTitleFont());
                pageSettings.setFontSize(slimSettings.getLeftTitleSize());

                String title = slimData.getLeftTitleContent();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

            //Center Title
            if (slimSettings.centerTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[1]+(slimSettings.centerTitleAdjustmentX));
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]+(slimSettings.centerTitleAdjustmentY));

                pageSettings.setFontColor(slimSettings.getCenterTitleColor());
                pageSettings.setFontName(slimSettings.getCenterTitleFont());
                pageSettings.setFontSize(slimSettings.getCenterTitleSize());

                String title = slimData.getCenterTitleContent();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

            //Right Title
            if (slimSettings.rightTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[2]+(slimSettings.rightTitleAdjustmentX));
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]+(slimSettings.rightTitleAdjustmentY));

                pageSettings.setFontColor(slimSettings.getRightTitleColor());
                pageSettings.setFontName(slimSettings.getRightTitleFont());
                pageSettings.setFontSize(slimSettings.getRightTitleSize());

                String title = slimData.getRightTitleContent();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

        }

        //Reset Color
        pageSettings.setPageColor(ColorsToPdfBox.WHITE);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);

    }

    private static void slimContainerColumnCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < BOX_QUANTITY; box++) {
            drawColumnBox(box, settings.getPage(), slimSettings, contentStream);
        }
    }

    private static void slimContainerColumnContentCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        /*
         * [NOTE]
         * In the below looping "for" we go running the number of BOX_QUANTITY(5) for the SLIM template
         * jumping from 3 on 3 item inside one box, it means that the loop it will be executed five times
         * running in each box that could be 3x1=3boxes, 3x2=6boxes, 3x3=9boxes, 3x4=12boxes or 3x5=15boxes.
         * Another point is that even though the boxes are jumped from 3 on 3, each one can be hidden by informing
         * the border=false, and in this case it will not show in the pdf document because there is one flag checker
         * in this code flow.
         * */
        for (int box = 0; box < BOX_QUANTITY; box++) {

            List<String[]> listLines = new ArrayList<>();

            /*TODO: Fix the lines using length (NOT \n)*/
            if (settings.getSlimContent().getColumnContent().get(box*3) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box*3).split("\n"));
            }

            if (settings.getSlimContent().getColumnContent().get(box*3+1) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box*3+1).split("\n"));
            }

            if (settings.getSlimContent().getColumnContent().get(box*3+2) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box*3+2).split("\n"));
            }

            drawColumnContent(box, document, page, listLines, settings, contentStream);
        }
    }

    private static void slimContainerTableCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < BOX_QUANTITY; box++) {

            if (slimSettings.tableEnable[box]) {

                drawTableContainer(box, slimSettings, contentStream);

                for (int col = 0; col < slimSettings.getTableSize().getTableColumns(); col++) {
                    drawTableHeader(box, col, slimSettings, contentStream);
                }

                /*Auto Fix: Prevent incorrect settings*/
                int swapHeight = slimSettings.tableColumnHeight;
                slimSettings.tableDataOffsetY[box] = correctTableOffsetY(box, 54, slimSettings);
                slimSettings.tableColumnHeight = correctTableHeight(slimSettings);

                int initialX = slimSettings.tableOffsetX;
                int initialY = slimSettings.tableDataOffsetY[box];
                int celWidth = slimSettings.tableColumnWidth;
                int celHeight = slimSettings.tableColumnHeight;

                //tableLines-1: to remove the first line (because was used in the table header)
                int tableLines = slimSettings.getTableSize().getTableLines()-1;
                int tableColumns = slimSettings.getTableSize().getTableColumns();

                for (int row = 0; row < tableLines; row++) {

                    for (int col = 0; col < tableColumns; col++) {
                        drawTableBody(initialX, initialY, celWidth, celHeight, slimSettings, contentStream);
                        initialX += celWidth; //move to the next column
                    }

                    initialX = slimSettings.tableOffsetX; //reset offset-x position
                    initialY -= celHeight; //move to the next line
                }

                slimSettings.tableColumnHeight = swapHeight;
            }
        }
    }

    private static void slimContainerTableContentCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < BOX_QUANTITY; box++) {

            if (slimSettings.tableEnable[box]) {

                for (int col = 0; col < slimSettings.getTableSize().getTableColumns(); col++) {
                    drawTableHeaderContent(box, col, document, page, settings, contentStream);
                }

                //tableLines-1: to remove the first line (because was used in the table header)
                int tableLines = slimSettings.getTableSize().getTableLines()-1;

                List<String> contentList = settings.getSlimContent().getTableBodyContent().get(box);
                int loopSize = settings.getSlimContent().getTableBodyContent().get(box).size()/tableLines;

                int contLoop = 0;
                int adjustY = 0;
                for (String item : contentList) {
                    if (contLoop == loopSize) {
                        contLoop = 0;
                        adjustY += slimSettings.tableColumnHeight;
                    }
                    drawTableBodyContent(box, contLoop, adjustY, item, document, page, settings, contentStream);
                    contLoop++;
                }
            }
        }
    }

    private static void slimContainerImageCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();
        SlimDataContent slimData = settings.getSlimContent();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxImage imgSettings = settings.getImage();

        for (int n = 0; n < BOX_QUANTITY; n++) {

            String imgLeft = slimData.leftImagePaths[n];
            String imgCenter = slimData.centerImagePaths[n];
            String imgRight = slimData.rightImagePaths[n];

            //Image Left
            if (slimSettings.leftImageEnable[n] && imgLeft != null && !imgLeft.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[0]);
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgLeft);
                drawImage(document, imgSettings, contentStream);
            }

            //Image Center
            if (slimSettings.centerImageEnable[n] && imgCenter != null && !imgCenter.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[1]);
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgCenter);
                drawImage(document, imgSettings, contentStream);
            }

            //Image Right
            if (slimSettings.rightImageEnable[n] && imgRight != null && !imgRight.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[2]);
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgRight);
                drawImage(document, imgSettings, contentStream);
            }
        }
    }

    private static void slimContainerSignatureCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();
        SlimDataContent slimData = settings.getSlimContent();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();
        PdfBoxContainer rectSettings = settings.getContainer();

        //Left Signature
        if (slimSettings.isLeftSignatureBoxEnable()) {
            drawSignatureBox(0, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }

        //Center Signature
        if (slimSettings.isCenterSignatureBoxEnable()) {
            drawSignatureBox(1, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }

        //Right Signature
        if (slimSettings.isRightSignatureBoxEnable()) {
            drawSignatureBox(2, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }
    }

    private static void slimContainerSignatureTapeCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();
        SlimDataContent slimData = settings.getSlimContent();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();
        PdfBoxContainer rectSettings = settings.getContainer();

        if (slimSettings.isSignatureTapeEnable()) {
            drawSignatureTape(document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }
    }

    private static void slimContainerTextCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();

        pageSettings.setOffsetX(slimSettings.textOffsetX);
        pageSettings.setFontColor(slimSettings.getTextColor());
        pageSettings.setFontName(slimSettings.getTextFont());
        pageSettings.setFontSize(slimSettings.getTextSize());
        pageSettings.setLineHeight(slimSettings.lineHeight);

        List<String[]> listLines = new ArrayList<>();

        for (int box = 0; box < BOX_QUANTITY; box++) {

            /*TODO: Fix the lines using length (NOT \n)*/
            if (settings.getSlimContent().getTextContent().get(box) != null) {
                listLines.add(settings.getSlimContent().getTextContent().get(box).split("\n"));
            }

            if (slimSettings.textEnable[box]) {
                pageSettings.setOffsetY(slimSettings.textOffsetY[box]);
                drawText(box, document, page, listLines, slimSettings, pageSettings, contentStream);
            }
        }
    }

    private static void slimContainerBarcodeCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        for (int box = 0; box < BOX_QUANTITY; box++) {

            if (settings.getSlim().barcodeEnabled[box]) {

                String value = settings.getSlimContent().getBarcodeValue().get(box);

                if (value != null && !value.isEmpty()) {

                    settings.getBarcode().setOffsetY(settings.getSlim().barcodeOffsetY[box]);
                    settings.getBarcode().setData(value);
                    settings.getBarcode().setWidth(settings.getSlim().barcodeWidth);
                    settings.getBarcode().setHeight(settings.getSlim().barcodeHeight);

                    if (!settings.getSlim().barcodeShowText) {
                        settings.getBarcode().setTextPosition(HumanReadablePlacement.HRP_NONE);
                    }

                    drawBarcode(document, settings.getBarcode(), contentStream);
                    drawBarcodeInfo(box, document, page, settings.getPage(), settings, contentStream);
                }
            }
        }
    }

    private static void slimContainerQrCodeCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        for (int box = 0; box < BOX_QUANTITY; box++) {

            //Left
            if (settings.getSlim().qrCodeLeftEnable[box]) {
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[0]);
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]);
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3));
                drawQRCode(document, settings.getQrCode(), contentStream);
                drawQRCodeInfo(box, document, page, settings.getPage(), settings, contentStream);
            }

            //Center
            if (settings.getSlim().qrCodeCenterEnable[box]) {
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[1]);
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]);
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3+1));
                drawQRCode(document, settings.getQrCode(), contentStream);
                drawQRCodeInfo(box, document, page, settings.getPage(), settings, contentStream);
            }

            //Right
            if (settings.getSlim().qrCodeRightEnable[box]) {
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[2]);
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]);
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3+2));
                drawQRCode(document, settings.getQrCode(), contentStream);
                drawQRCodeInfo(box, document, page, settings.getPage(), settings, contentStream);
            }
        }
    }

    private static boolean hasTitle(
            int box,
            SlimTemplateSettings slimTemplateSettings
    ) {
        if (slimTemplateSettings.leftTitleEnable[box]) {
            return true;
        }

        if (slimTemplateSettings.centerTitleEnable[box]) {
            return true;
        }

        return slimTemplateSettings.rightTitleEnable[box];
    }

    private static void slimTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        slimContainerCreate(document, page, settings, contentStream);
        slimContainerTitleCreate(document, page, settings, contentStream);
        slimContainerColumnCreate(document, page, settings, contentStream);
        slimContainerTableCreate(document, page, settings, contentStream);
        slimContainerImageCreate(document, page, settings, contentStream);
        /*NOTE: Text,Content should be in the final of the process*/
        slimContainerColumnContentCreate(document, page, settings, contentStream);
        slimContainerTableContentCreate(document, page, settings, contentStream);
        slimContainerTextCreate(document, page, settings, contentStream);
        slimContainerSignatureCreate(document, page, settings, contentStream);
        slimContainerSignatureTapeCreate(document, page, settings, contentStream);
        slimContainerBarcodeCreate(document, page, settings, contentStream);
        slimContainerQrCodeCreate(document, page, settings, contentStream);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplate</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types
     * <br />
     * Templates available:
     * </p>
     *
     * <ul>
     *     <li>Slim</li>
     *     <li>Box</li>
     *     <li>Box Open</li>
     *     <li>Slim Box</li>
     *     <li>Triple Fall</li>
     *     <li>Simple 1</li>
     *     <li>Simple 2</li>
     *     <li>Simple 3</li>
     *     <li>Free</li>
     * </ul>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfBoxTemplate(PdfBoxTemplateSettings settings) {

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            drawBackground(document, settings, contentStream);

            if (settings.getTemplate().name().equals(template(PdfBoxTemplates.SLIM))) {
                slimTemplateBuilder(document, page, settings, contentStream);
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX))) {
                throw new RuntimeException("TODO: BOX Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX_OPEN))) {
                throw new RuntimeException("TODO: BOX_OPEN Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.SLIM_BOX))) {
                throw new RuntimeException("TODO: SLIM_BOX Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.TRIPLE_FALL))) {
                throw new RuntimeException("TODO: TRIPLE_FALL Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.SIMPLE_1))) {
                throw new RuntimeException("TODO: SIMPLE_1 Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.SIMPLE_2))) {
                throw new RuntimeException("TODO: SIMPLE_2 Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.SIMPLE_3))) {
                throw new RuntimeException("TODO: SIMPLE_3 Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.FREE))) {
                throw new RuntimeException("TODO: FREE Template");
            } else {
                throw new RuntimeException("PDFBox Template not found");
            }

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

}
