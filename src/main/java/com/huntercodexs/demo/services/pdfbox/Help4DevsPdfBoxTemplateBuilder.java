package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public abstract class Help4DevsPdfBoxTemplateBuilder extends Help4DevsPdfBox {

    protected static boolean hasTitle(
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

    protected static void linesBuild(
            int index,
            List<String[]> listLines,
            boolean hasTitle,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            int stop = 0;
            for (String line : listLines.get(index)) {
                if (stop == 5 && hasTitle) break;
                contentStreamText(line, pageSettings, contentStream);
                stop += 1;
            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void titleBuild(
            String title,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStreamText(title, pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void columnBoxBuild(
            int box,
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

    protected static void columnContentBuild(
            int box,
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

            linesBuild(0, listLines, hasTitle, pageSettings, contentStream);
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

            linesBuild(1, listLines, hasTitle, pageSettings, contentStream);
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

            linesBuild(2, listLines, hasTitle, pageSettings, contentStream);
        }
    }

    protected static void tableContainerBuild(
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

    protected static int correctTableOffsetX(int column, SlimTemplateSettings settings) {
        if (column == 0) {
            return settings.tableOffsetX;
        }

        int correctOffsetX = settings.tableColumnOffsetX[column-1] + settings.tableColumnWidth;

        if (settings.tableColumnOffsetX[column] != correctOffsetX) {
            return correctOffsetX;
        }
        return settings.tableColumnOffsetX[column];
    }

    protected static int correctTableOffsetY(int box, int ref, SlimTemplateSettings settings) {
        if (box == 0) {
            return settings.tableContainerOffsetY[0]+ref;
        }

        int correctOffsetY = settings.tableContainerOffsetY[box] + ref;

        if (settings.tableContainerOffsetY[box] != correctOffsetY) {
            return correctOffsetY;
        }
        return settings.tableContainerOffsetY[box];
    }

    protected static int correctTableWidth(SlimTemplateSettings settings) {
        int result = settings.tableColumnWidth * settings.tableSize.getTableColumns();
        if (result != settings.tableWidth) {
            return settings.tableWidth / settings.tableSize.getTableColumns();
        }
        return settings.tableColumnWidth;
    }

    protected static int correctTableHeight(SlimTemplateSettings settings) {
        int result = settings.tableColumnHeight * settings.tableSize.getTableLines();
        if (result != settings.tableHeight) {
            return settings.tableHeight / settings.tableSize.getTableLines();
        }
        return settings.tableColumnHeight;
    }

    protected static void tableHeaderBuild(
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

    protected static void tableHeaderContentBuild(
            int box,
            int column,
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

            contentStreamText(content.getTableHeaderContent().get(box).get(column), pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void tableBodyBuild(
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

    protected static void tableBodyContentBuild(
            int box,
            int column,
            int adjustOffsetY,
            String content,
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

            contentStreamText(content, pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void imageBuild(
            PDDocument document,
            PdfBoxImage imgSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStreamImage(
                    imgSettings.getFilenamePath(),
                    imgSettings.getWidth(),
                    imgSettings.getHeight(),
                    imgSettings.getOffsetX(),
                    imgSettings.getOffsetY(),
                    document,
                    contentStream
            );

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void signatureBoxBuild(
            int index,
            PdfBoxPage pageSettings,
            SlimTemplateSettings settings,
            SlimDataContent slimData,
            PDPageContentStream contentStream
    ) {
        try {

            //Signature Box
            contentStream.setStrokingColor(color(settings.getSignatureBoxColor()));
            contentStream.addRect(
                    settings.signatureBoxOffsetX[index]+(settings.getSignatureBoxAdjustOffsetX()),
                    settings.signatureBoxOffsetY[0],
                    settings.signatureBoxWidth,
                    settings.signatureBoxHeight);
            if (settings.signatureBoxBorderEnable) {
                contentStream.closeAndStroke();
            }
            contentStream.setStrokingColor(0,0,0);

            //Signature Title
            pageSettings.setOffsetX(settings.signatureBoxDigitalTitleOffsetX[index]+(settings.getSignatureBoxAdjustOffsetX()));
            pageSettings.setOffsetY(settings.signatureBoxOffsetY[1]);
            pageSettings.setFontColor(settings.signatureBoxColor);
            pageSettings.setFontName(settings.signatureFontName);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStreamText("Digital Electronic Signature", pageSettings, contentStream);

            //Signature Value
            pageSettings.setOffsetX(settings.signatureBoxContentOffsetX[index]+(settings.getSignatureBoxAdjustOffsetX()));
            pageSettings.setOffsetY(settings.signatureBoxOffsetY[2]);
            pageSettings.setFontColor(settings.signatureBoxColor);
            pageSettings.setFontName(settings.signatureFontName);
            pageSettings.setFontSize(settings.signatureFontSize);
            pageSettings.setLineHeight(16);

            contentStream.setLeading(pageSettings.getLineHeight());
            contentStreamText(slimData.getSignaturePersonName(), pageSettings, contentStream);
            contentStreamText("DOC:"+slimData.getSignaturePersonDoc(), pageSettings, contentStream);
            contentStreamText(slimData.getSignatureRecord(), pageSettings, contentStream);
            contentStreamText(slimData.getSignatureDateGmt(), pageSettings, contentStream);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void signatureTapeBuild(
            PdfBoxPage pageSettings,
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
            pageSettings.setFontName(settings.getSignatureTapeFontName());
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStreamText("Digital Electronic Signature", pageSettings, contentStream);

            //Signature Value
            pageSettings.setOffsetX(settings.getSignatureTapeValueOffsetX()+(settings.getSignatureTapeAdjustOffsetX()));
            pageSettings.setOffsetY(settings.getSignatureTapeValueOffsetY());
            pageSettings.setFontColor(settings.getSignatureTapeColor());
            pageSettings.setFontName(settings.getSignatureTapeFontName());
            pageSettings.setFontSize(settings.getSignatureTapeFontSize());

            contentStreamText("Digital Electronic Signature", pageSettings, contentStream);

            String signature = slimData.getSignaturePersonName();
            signature = signature + " - DOC:" + slimData.getSignaturePersonDoc();
            signature = signature + " - " + slimData.getSignatureRecord();
            signature = signature + " - " + slimData.getSignatureDateGmt();

            contentStreamText(signature, pageSettings, contentStream);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void textBuild(
            int box,
            List<String[]> listLines,
            SlimTemplateSettings slimSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        boolean hasTitle = hasTitle(box, slimSettings);

        if (!hasTitle) {
            pageSettings.setOffsetY(pageSettings.getOffsetY()+slimSettings.getLineHeight());
        }

        linesBuild(box, listLines, hasTitle, pageSettings, contentStream);
    }

    protected static void barcodeBuild(
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

    protected static void barcodeInfoBuild(
            int box,
            PdfBoxPage pageSettings,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            //Info 1,2,3,4 - (Name|Address|UserMessage|OperatorMessage)
            pageSettings.setOffsetX(55+(settings.getSlim().barcodeAdjustOffsetX));
            pageSettings.setOffsetY(
                    settings.getSlim().barcodeInfoOffsetY[box]+(settings.getSlim().barcodeAdjustOffsetY)
            );
            pageSettings.setLineHeight(12);

            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStreamText(settings.getSlimContent().getBarcodeInfoOne().get(box), pageSettings, contentStream);
            contentStreamText(settings.getSlimContent().getBarcodeInfoTwo().get(box), pageSettings, contentStream);
            contentStreamText(settings.getSlimContent().getBarcodeInfoThree().get(box), pageSettings, contentStream);
            contentStreamText(settings.getSlimContent().getBarcodeInfoFour().get(box), pageSettings, contentStream);

            //Info 5 - Value (Barcode)
            pageSettings.setOffsetX(85+(settings.getSlim().barcodeAdjustOffsetX));
            pageSettings.setOffsetY(
                    settings.getSlim().barcodeValueOffsetY[box]+(settings.getSlim().barcodeAdjustOffsetY)
            );
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

            contentStreamText(settings.getSlimContent().getBarcodeValue().get(box), pageSettings, contentStream);

            //Info 6 - Amount (Money)
            pageSettings.setOffsetX(270+(settings.getSlim().barcodeAdjustOffsetX));
            pageSettings.setOffsetY(
                    settings.getSlim().barcodeAmountOffsetY[box]+(settings.getSlim().barcodeAdjustOffsetY)
            );
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

            contentStreamText(settings.getSlimContent().getBarcodeAmount().get(box), pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void qrCodeBuild(
            PDDocument document,
            PdfBoxQrCode qrCode,
            PDPageContentStream contentStream
    ) {
        qrCode(qrCode, document, contentStream);
    }

    protected static void qrCodeInfoBuild(
            int box,
            PdfBoxPage pageSettings,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            pageSettings.setLineHeight(12);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            //Left
            if (settings.getSlim().qrCodeLeftEnable[box]) {
                pageSettings.setOffsetX(settings.getSlim().qrCodeInfoOffsetX[0]+(settings.getSlim().qrCodeAdjustOffsetX));
                pageSettings.setOffsetY(settings.getSlim().qrCodeInfoOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));

                contentStreamText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3), pageSettings, contentStream);
            }

            //Center
            if (settings.getSlim().qrCodeCenterEnable[box]) {
                pageSettings.setOffsetX(settings.getSlim().qrCodeInfoOffsetX[1]+(settings.getSlim().qrCodeAdjustOffsetX));
                pageSettings.setOffsetY(settings.getSlim().qrCodeInfoOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));

                contentStreamText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3 + 1), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3 + 1), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3 + 1), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3 + 1), pageSettings, contentStream);
            }

            //Right
            if (settings.getSlim().qrCodeRightEnable[box]) {
                pageSettings.setOffsetX(settings.getSlim().qrCodeInfoOffsetX[2]+(settings.getSlim().qrCodeAdjustOffsetX));
                pageSettings.setOffsetY(settings.getSlim().qrCodeInfoOffsetY[box]+(settings.getSlim().qrCodeAdjustOffsetY));

                contentStreamText(settings.getSlimContent().getQrCodeInfoOne().get(box * 3 + 2), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoTwo().get(box * 3 + 2), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoThree().get(box * 3 + 2), pageSettings, contentStream);
                contentStreamText(settings.getSlimContent().getQrCodeInfoFour().get(box * 3 + 2), pageSettings, contentStream);
            }

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void formBuild() {}

    protected static void barcodeFormBuild() {}

}
