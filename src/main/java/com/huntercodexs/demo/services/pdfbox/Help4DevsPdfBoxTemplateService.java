package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

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
    
    private static void drawSlimTemplate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        slimContainerCreate(document, page, settings, contentStream);
        slimContainerTitleCreate(document, page, settings, contentStream);
        slimContainerTableCreate(settings, contentStream);
        slimContainerColumnCreate(settings, contentStream);
        slimContainerSignatureCreate(document, page, settings, contentStream);
        slimContainerSignatureTapeCreate(document, page, settings, contentStream);
        slimContainerTextCreate(document, page, settings, contentStream);
        slimContainerImageCreate(document, settings, contentStream);
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
        if (!settings.isImageBackgroundEnable()) return;

        try {
            PDImageXObject pdfImageBackground = PDImageXObject.createFromFile(settings.getImageBackground(), document);
            contentStream.drawImage(pdfImageBackground, 0, 0, 620, 792);
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

    private static void drawTableContainer(
            int box,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.setStrokingColor(color(settings.tableBorderColor));
            contentStream.addRect(
                    settings.tableOffsetX,
                    settings.tableContainerOffsetY[box],
                    settings.tableWidth,
                    settings.tableHeight);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawTableHeader(
            int box,
            int column,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.setStrokingColor(color(settings.tableBorderColor));
            contentStream.setNonStrokingColor(color(settings.tableHeaderColor));
            contentStream.addRect(
                    settings.tableColumnOffsetX[column],
                    settings.tableHeaderOffsetY[box],
                    settings.columnWidth,
                    settings.columnHeight);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

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

    private static void drawColumnBox(
            int box,
            SlimTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            //Column Box 1
            contentStream.setLineWidth(settings.columnLeftBorderWidth[box]);
            contentStream.setStrokingColor(color(settings.columnLeftBorderColor[box]));
            contentStream.addRect(
                    settings.columnBoxOffsetX[0],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            if (settings.columnLeftBorderEnable[box]) {
                contentStream.closeAndStroke();
            }
            contentStream.setStrokingColor(0,0,0);

            //Column Box 2
            contentStream.setLineWidth(settings.columnCenterBorderWidth[box]);
            contentStream.setStrokingColor(color(settings.columnCenterBorderColor[box]));
            contentStream.addRect(
                    settings.columnBoxOffsetX[1],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            if (settings.columnCenterBorderEnable[box]) {
                contentStream.closeAndStroke();
            }
            contentStream.setStrokingColor(0,0,0);

            //Column Box 3
            contentStream.setLineWidth(settings.columnRightBorderWidth[box]);
            contentStream.setStrokingColor(color(settings.columnRightBorderColor[box]));
            contentStream.addRect(
                    settings.columnBoxOffsetX[2],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            if (settings.columnRightBorderEnable[box]) {
                contentStream.closeAndStroke();
            }
            contentStream.setStrokingColor(0,0,0);

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

            contentStream.showText(settings.getSignaturePersonName());
            contentStream.newLine();
            contentStream.showText("DOC:"+settings.getSignaturePersonDoc());
            contentStream.newLine();
            contentStream.showText(settings.getSignatureRecord());
            contentStream.newLine();
            contentStream.showText(settings.getSignatureDateGmt());
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

            String signature = settings.getSignaturePersonName();
            signature = signature + " - DOC:" + settings.getSignaturePersonDoc();
            signature = signature + " - " + settings.getSignatureRecord();
            signature = signature + " - " + settings.getSignatureDateGmt();
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
            String[] lines,
            SlimTemplateSettings slimSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        boolean hasTitle = hasTitle(box, slimSettings);

        if (!hasTitle) {
            pageSettings.setOffsetY(pageSettings.getOffsetY()+slimSettings.getLineHeight());
        }

        contentStream("text", page, document, pageSettings, null, contentStream);

        try {

            int stop = 0;

            for (String line : lines) {
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

    private static void slimContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setHeight(DEFAULT_HEIGHT);
        rectSettings.setWidth(DEFAULT_WIDTH);

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

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();

        for (int n = 0; n < BOX_QUANTITY; n++) {

            //Left Title
            if (slimSettings.leftTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[0]);
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimSettings.getLeftTitleColor());
                pageSettings.setFontName(slimSettings.getLeftTitleFont());
                pageSettings.setFontSize(slimSettings.getLeftTitleSize());

                String title = slimSettings.getLeftTitleText();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

            //Center Title
            if (slimSettings.centerTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[1]);
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimSettings.getCenterTitleColor());
                pageSettings.setFontName(slimSettings.getCenterTitleFont());
                pageSettings.setFontSize(slimSettings.getCenterTitleSize());

                String title = slimSettings.getCenterTitleText();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

            //Right Title
            if (slimSettings.rightTitleEnable[n]) {
                pageSettings.setOffsetX(slimSettings.titleOffsetX[2]);
                pageSettings.setOffsetY(slimSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimSettings.getRightTitleColor());
                pageSettings.setFontName(slimSettings.getRightTitleFont());
                pageSettings.setFontSize(slimSettings.getRightTitleSize());

                String title = slimSettings.getRightTitleText();
                drawTitle(document, page, title, pageSettings, contentStream);
            }

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

        if (slimSettings.getTextContentOne() != null) {
            pageSettings.setTextContent(slimSettings.getTextContentOne());
        }

        pageSettings.setLineHeight(slimSettings.lineHeight);

        String[] lines = new String[]{};

        if (slimSettings.getTextContentOne() != null) {
            lines = pageSettings.getTextContent().split("\n");
        }

        for (int box = 0; box < BOX_QUANTITY; box++) {
            if (slimSettings.textEnable[box]) {
                pageSettings.setOffsetY(slimSettings.textOffsetY[box]);
                drawText(box, document, page, lines, slimSettings, pageSettings, contentStream);
            }
        }
    }

    private static void slimContainerImageCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxImage imgSettings = settings.getImage();

        for (int n = 0; n < BOX_QUANTITY; n++) {

            String imgLeft = slimSettings.leftImagePaths[n];
            String imgCenter = slimSettings.centerImagePaths[n];
            String imgRight = slimSettings.rightImagePaths[n];

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

    private static void slimContainerTableCreate(
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

                for (int cols = 0; cols < slimSettings.getTableSize().getTableColumns(); cols++) {
                    drawTableHeader(box, cols, slimSettings, contentStream);
                }

                int initialX = slimSettings.tableOffsetX;
                int initialY = slimSettings.tableDataOffsetY[box];
                int celWidth = slimSettings.columnWidth;
                int celHeight = slimSettings.columnHeight;

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
            }
        }
    }

    private static void slimContainerColumnCreate(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < BOX_QUANTITY; box++) {
            if (slimSettings.columnBoxEnable[box]) {
                drawColumnBox(box, slimSettings, contentStream);
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

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPage pageSettings = settings.getPage();
        PdfBoxContainer rectSettings = settings.getContainer();

        //Left Signature
        if (slimSettings.isLeftSignatureBoxEnable()) {
            drawSignatureBox(0, document, page, pageSettings, rectSettings, slimSettings, contentStream);
        }

        //Center Signature
        if (slimSettings.isCenterSignatureBoxEnable()) {
            drawSignatureBox(1, document, page, pageSettings, rectSettings, slimSettings, contentStream);
        }

        //Right Signature
        if (slimSettings.isRightSignatureBoxEnable()) {
            drawSignatureBox(2, document, page, pageSettings, rectSettings, slimSettings, contentStream);
        }
    }

    private static void slimContainerSignatureTapeCreate(
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
        PdfBoxContainer rectSettings = settings.getContainer();

        if (slimSettings.isSignatureTapeEnable()) {
            drawSignatureTape(document, page, pageSettings, rectSettings, slimSettings, contentStream);
        }
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
     *     <li>Free</li>
     *     <li>Slim</li>
     *     <li>Box</li>
     *     <li>Box Open</li>
     *     <li>Slim Box</li>
     *     <li>Triple Fall</li>
     *     <li>Simple 1</li>
     *     <li>Simple 2</li>
     *     <li>Simple 3</li>
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
                drawSlimTemplate(document, page, settings, contentStream);
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX))) {
                throw new RuntimeException("TODO: BOX Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX_OPEN))) {
                throw new RuntimeException("TODO: BOX_OPEN Template");
            } else if(settings.getTemplate().name().equals(template(PdfBoxTemplates.SLIM_BOX))) {
                throw new RuntimeException("TODO: SLIM_BOX Template");
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
