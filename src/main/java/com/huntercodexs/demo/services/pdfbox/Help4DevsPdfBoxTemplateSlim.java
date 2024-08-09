package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PdfBoxPage.getPageHeight;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PdfBoxPage.getPageWidth;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.SlimTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateSlim extends Help4DevsPdfBoxTemplateBuilder {

    private static int correctOffsetY(int box, String pageSize, PdfBoxContainer rectSettings) {
        int adjustOffsetY = 0;

        if (pageSize.equals("A4")) {
            adjustOffsetY = OFFSET_Y_ADJUST_A4;
        }

        if (box == 0) {
            rectSettings.setOffsetY(OFFSET_Y_BLOCK1+(adjustOffsetY));
        } else if (box == 1) {
            rectSettings.setOffsetY(OFFSET_Y_BLOCK2+(adjustOffsetY));
        } else if (box == 2) {
            rectSettings.setOffsetY(OFFSET_Y_BLOCK3+(adjustOffsetY));
        } else if (box == 3) {
            rectSettings.setOffsetY(OFFSET_Y_BLOCK4+(adjustOffsetY));
        } else {
            rectSettings.setOffsetY(OFFSET_Y_BLOCK5+(adjustOffsetY));
        }
        return rectSettings.getOffsetY();
    }

    private static void drawContainer(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();

        PdfBoxPage pageSettings = settings.getPage();

        for (int box = 0; box < BOX_QUANTITY; box++) {

            rectSettings.setOffsetX(DEFAULT_OFFSET_X);
            rectSettings.setWidth(DEFAULT_WIDTH);
            rectSettings.setHeight(DEFAULT_HEIGHT);

            String pageSize = pageSettings.getPageSize().name();
            rectSettings.setOffsetY(correctOffsetY(box, pageSize, rectSettings));

            int widthAdjustA4 = 0;
            int offsetYAdjustA4 = 0;
            if (pageSize.equals("A4")) {
                widthAdjustA4 = WIDTH_ADJUST_A4;
                offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
            }

            if (settings.getSlim().boxWidth[box] != 0) {
                rectSettings.setWidth(settings.getSlim().boxWidth[box]+(widthAdjustA4));
            }

            if (settings.getSlim().boxAdjustOffsetX[box] != 0) {
                rectSettings.setOffsetX(DEFAULT_OFFSET_X+(settings.getSlim().boxAdjustOffsetX[box])+(widthAdjustA4));
            }

            if (settings.getSlim().boxAdjustOffsetY[box] != 0) {
                rectSettings.setOffsetY(rectSettings.getOffsetY()+(settings.getSlim().boxAdjustOffsetY[box])+(offsetYAdjustA4));
            }

            rectSettings.setBorder(settings.getSlim().boxBorderEnabled[box]);

            if (settings.getSlim().boxBackColor[box] != null) {
                rectSettings.setBackColor(settings.getSlim().boxBackColor[box]);
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

    private static void drawTemplateTitle(
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            pageSettings.setFontSize(FontSizeToPdfBox.LARGE);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_BI);
            pageSettings.setFontColor(ColorsToPdfBox.LIGHT_GRAY);

            pageSettings.setOffsetX(50);
            pageSettings.setOffsetY(700);

            contentStream("text", page, document, pageSettings, null, contentStream);
            contentStream.showText("Slim");
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
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
            contentStream.drawImage(pdfImageBackground,
                    0,
                    0,
                    getPageWidth(settings.getPage().getPageSize().name()),
                    getPageHeight(settings.getPage().getPageSize().name()));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void slimContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawContainer(document, page, settings, contentStream);

        if (settings.getSlim().templateTitleEnabled) {
            drawTemplateTitle(document, page, settings.getPage(), contentStream);
        }
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
                titleBuild(document, page, title, pageSettings, contentStream);
            }

            //Center Title
            if (slimSettings.centerTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[1]+(slimSettings.centerTitleAdjustmentX));
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]+(slimSettings.centerTitleAdjustmentY));

                pageSettings.setFontColor(slimSettings.getCenterTitleColor());
                pageSettings.setFontName(slimSettings.getCenterTitleFont());
                pageSettings.setFontSize(slimSettings.getCenterTitleSize());

                String title = slimData.getCenterTitleContent();
                titleBuild(document, page, title, pageSettings, contentStream);
            }

            //Right Title
            if (slimSettings.rightTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[2]+(slimSettings.rightTitleAdjustmentX));
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]+(slimSettings.rightTitleAdjustmentY));

                pageSettings.setFontColor(slimSettings.getRightTitleColor());
                pageSettings.setFontName(slimSettings.getRightTitleFont());
                pageSettings.setFontSize(slimSettings.getRightTitleSize());

                String title = slimData.getRightTitleContent();
                titleBuild(document, page, title, pageSettings, contentStream);
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
            columnBoxBuild(box, settings.getPage(), slimSettings, contentStream);
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

            if (settings.getSlimContent().getColumnContent().get(box*3) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box * 3)
                        .replaceAll("\n", "")
                        .replaceAll("\r", "")
                        .split("(?<=\\G.{" + settings.getSlim().columnBoxChars + "})"));
            }

            if (settings.getSlimContent().getColumnContent().get(box*3+1) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box * 3 + 1)
                        .replaceAll("\n", "")
                        .replaceAll("\r", "")
                        .split("(?<=\\G.{" + settings.getSlim().columnBoxChars + "})"));
            }

            if (settings.getSlimContent().getColumnContent().get(box*3+2) != null) {
                listLines.add(settings.getSlimContent().getColumnContent().get(box * 3 + 2)
                        .replaceAll("\n", "")
                        .replaceAll("\r", "")
                        .split("(?<=\\G.{" + settings.getSlim().columnBoxChars + "})"));
            }

            columnContentBuild(box, document, page, listLines, settings, contentStream);
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

                tableContainerBuild(box, slimSettings, contentStream);

                for (int col = 0; col < slimSettings.getTableSize().getTableColumns(); col++) {
                    tableHeaderBuild(box, col, slimSettings, contentStream);
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
                        tableBodyBuild(initialX, initialY, celWidth, celHeight, slimSettings, contentStream);
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
                    tableHeaderContentBuild(box, col, document, page, settings, contentStream);
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
                    tableBodyContentBuild(box, contLoop, adjustY, item, document, page, settings, contentStream);
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

            imgSettings.setWidth(slimSettings.imageWidth);
            imgSettings.setHeight(slimSettings.imageHeight);

            //Image Left
            if (slimSettings.leftImageEnable[n] && imgLeft != null && !imgLeft.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[0]+(slimSettings.imageAdjustOffsetX));
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]+(slimSettings.imageAdjustOffsetY));
                imgSettings.setFilenamePath(imgLeft);
                imageBuild(document, imgSettings, contentStream);
            }

            //Image Center
            if (slimSettings.centerImageEnable[n] && imgCenter != null && !imgCenter.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[1]+(slimSettings.imageAdjustOffsetX));
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]+(slimSettings.imageAdjustOffsetY));
                imgSettings.setFilenamePath(imgCenter);
                imageBuild(document, imgSettings, contentStream);
            }

            //Image Right
            if (slimSettings.rightImageEnable[n] && imgRight != null && !imgRight.isEmpty()) {
                imgSettings.setOffsetX(slimSettings.imageOffsetX[2]+(slimSettings.imageAdjustOffsetX));
                imgSettings.setOffsetY(slimSettings.imageOffsetY[n]+(slimSettings.imageAdjustOffsetY));
                imgSettings.setFilenamePath(imgRight);
                imageBuild(document, imgSettings, contentStream);
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
            signatureBoxBuild(0, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }

        //Center Signature
        if (slimSettings.isCenterSignatureBoxEnable()) {
            signatureBoxBuild(1, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
        }

        //Right Signature
        if (slimSettings.isRightSignatureBoxEnable()) {
            signatureBoxBuild(2, document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
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
            signatureTapeBuild(document, page, pageSettings, rectSettings, slimSettings, slimData, contentStream);
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

            if (slimSettings.textEnable[box] && settings.getSlimContent().getTextContent().get(box) != null) {

                listLines.add(settings.getSlimContent().getTextContent().get(box)
                        .replaceAll("\n", " ")
                        .replaceAll("\r", " ")
                        .split("(?<=\\G.{" + settings.getSlim().textChars + "})"));

                pageSettings.setOffsetY(slimSettings.textOffsetY[box]);
                textBuild(box, document, page, listLines, slimSettings, pageSettings, contentStream);

            } else {
                listLines.add(new String[]{});
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

                    int offsetY = settings.getSlim().barcodeOffsetY[box]+(settings.getSlim().barcodeAdjustOffsetY);

                    settings.getBarcode().setData(value);
                    settings.getBarcode().setOffsetX(barcodeOffsetX+(settings.getSlim().barcodeAdjustOffsetX));
                    settings.getBarcode().setOffsetY(offsetY);

                    settings.getBarcode().setWidth(settings.getSlim().barcodeWidth);
                    settings.getBarcode().setHeight(settings.getSlim().barcodeHeight);

                    if (!settings.getSlim().barcodeShowText) {
                        settings.getBarcode().setTextPosition(HumanReadablePlacement.HRP_NONE);
                    }

                    barcodeBuild(document, settings.getBarcode(), contentStream);
                    barcodeInfoBuild(box, document, page, settings.getPage(), settings, contentStream);
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
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[0]+(settings.getSlim().qrCodeAdjustOffsetX));
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3));
                qrCodeBuild(document, settings.getQrCode(), contentStream);
                qrCodeInfoBuild(box, document, page, settings.getPage(), settings, contentStream);
            }

            //Center
            if (settings.getSlim().qrCodeCenterEnable[box]) {
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[1]+(settings.getSlim().qrCodeAdjustOffsetX));
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3+1));
                qrCodeBuild(document, settings.getQrCode(), contentStream);
                qrCodeInfoBuild(box, document, page, settings.getPage(), settings, contentStream);
            }

            //Right
            if (settings.getSlim().qrCodeRightEnable[box]) {
                settings.getQrCode().setOffsetX(settings.getSlim().qrCodeOffsetX[2]+(settings.getSlim().qrCodeAdjustOffsetX));
                settings.getQrCode().setOffsetY(settings.getSlim().qrCodeOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));
                settings.getQrCode().setData(settings.getSlimContent().getQrCodeValue().get(box*3+2));
                qrCodeBuild(document, settings.getQrCode(), contentStream);
                qrCodeInfoBuild(box, document, page, settings.getPage(), settings, contentStream);
            }
        }
    }

    public void slimTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        slimContainerBackgroundCreate(document, settings, contentStream);
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

}
