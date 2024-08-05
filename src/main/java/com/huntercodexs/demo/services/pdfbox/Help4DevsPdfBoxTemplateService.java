package com.huntercodexs.demo.services.pdfbox;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxService.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.PdfBoxTemplates.template;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.SlimTemplateDimensions.slimDimensions;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateService extends Help4DevsPdfBoxService {

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
    
    private static void drawSlimTemplate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        slimRectangleBoxCreate(document, page, settings, contentStream);
        slimTitleBoxCreate(document, page, settings, contentStream);
        slimTableBoxCreate(settings, contentStream);
        slimColumnBoxCreate(settings, contentStream);
        slimSignatureBoxCreate(document, page, settings, contentStream);
        slimSignatureBoxTapeCreate(document, page, settings, contentStream);
        slimTextBoxCreate(document, page, settings, contentStream);
        slimImageBoxCreate(document, settings, contentStream);
    }

    private static void drawRectangle(
            PDDocument document,
            PDPage page,
            int boxQuantity,
            PdfBoxContainerSettings rectSettings,
            PdfBoxPageSettings pageSettings,
            PDPageContentStream contentStream
    ) {
        for (int n = 1; n <= boxQuantity; n++) {

            if (n == 1) {
                rectSettings.setOffsetY(slimDimensions(SlimTemplateDimensions.OFFSET_Y_BLOCK1));
            } else if (n == 2) {
                rectSettings.setOffsetY(slimDimensions(SlimTemplateDimensions.OFFSET_Y_BLOCK2));
            } else if (n == 3) {
                rectSettings.setOffsetY(slimDimensions(SlimTemplateDimensions.OFFSET_Y_BLOCK3));
            } else if (n == 4) {
                rectSettings.setOffsetY(slimDimensions(SlimTemplateDimensions.OFFSET_Y_BLOCK4));
            } else {
                rectSettings.setOffsetY(slimDimensions(SlimTemplateDimensions.OFFSET_Y_BLOCK5));
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
            PdfBoxPageSettings pageSettings,
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
            PdfBoxPageSettings pageSettings,
            PdfBoxContainerSettings rectSettings,
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
            PdfBoxPageSettings pageSettings,
            PdfBoxContainerSettings rectSettings,
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
            PdfBoxPageSettings pageSettings,
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
            PdfBoxImageSettings imgSettings,
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

    private static void slimRectangleBoxCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainerSettings rectSettings = settings.getContainer();
        rectSettings.setHeight(slimDimensions(SlimTemplateDimensions.DEFAULT_HEIGHT));
        rectSettings.setWidth(slimDimensions(SlimTemplateDimensions.DEFAULT_WIDTH));

        PdfBoxPageSettings pageSettings = settings.getPage();

        int boxQuantity = settings.getSlim().getBoxQuantity();

        drawRectangle(document, page, boxQuantity, rectSettings, pageSettings, contentStream);
    }

    private static void slimTitleBoxCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPageSettings pageSettings = settings.getPage();

        for (int n = 0; n < slimSettings.getBoxQuantity(); n++) {

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

    private static void slimTextBoxCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPageSettings pageSettings = settings.getPage();

        pageSettings.setOffsetX(slimSettings.textOffsetX);
        pageSettings.setFontColor(slimSettings.getTextColor());
        pageSettings.setFontName(slimSettings.getTextFont());
        pageSettings.setFontSize(slimSettings.getTextSize());
        pageSettings.setTextContent(slimSettings.getTextContent());
        pageSettings.setLineHeight(slimSettings.lineHeight);

        String[] lines = pageSettings.getTextContent().split("\n");

        for (int box = 0; box < slimSettings.getBoxQuantity(); box++) {
            if (slimSettings.textEnable[box]) {
                pageSettings.setOffsetY(slimSettings.textOffsetY[box]);
                drawText(box, document, page, lines, slimSettings, pageSettings, contentStream);
            }
        }
    }

    private static void slimImageBoxCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxImageSettings imgSettings = settings.getImage();

        for (int n = 0; n < slimSettings.getBoxQuantity(); n++) {

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

    private static void slimTableBoxCreate(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < slimSettings.getBoxQuantity(); box++) {

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

    private static void slimColumnBoxCreate(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        for (int box = 0; box < slimSettings.getBoxQuantity(); box++) {
            if (slimSettings.columnBoxEnable[box]) {
                drawColumnBox(box, slimSettings, contentStream);
            }
        }
    }

    private static void slimSignatureBoxCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPageSettings pageSettings = settings.getPage();
        PdfBoxContainerSettings rectSettings = settings.getContainer();

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

    private static void slimSignatureBoxTapeCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        SlimTemplateSettings slimSettings = new SlimTemplateSettings();

        if (settings.getSlim() != null) {
            slimSettings = settings.getSlim();
        }

        PdfBoxPageSettings pageSettings = settings.getPage();
        PdfBoxContainerSettings rectSettings = settings.getContainer();

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
    public enum SlimTemplateDimensions {
        DEFAULT_WIDTH(570),
        DEFAULT_HEIGHT(135),
        OFFSET_Y_BLOCK1(640),
        OFFSET_Y_BLOCK2(485),
        OFFSET_Y_BLOCK3(330),
        OFFSET_Y_BLOCK4(175),
        OFFSET_Y_BLOCK5(20);

        private final int slimDimensions;

        SlimTemplateDimensions(int slimDimensions) {
            this.slimDimensions = slimDimensions;
        }

        public static int slimDimensions(SlimTemplateDimensions slim) {
            return slim.getSlimDimensions();
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
        String imageBackground = null;

        /*Elements Settings*/
        PdfBoxDocumentSettings document = null;
        PdfBoxPageSettings page = null;
        PdfBoxContainerSettings container = null;
        PdfBoxTextSettings text = null;
        PdfBoxImageSettings image = null;
        PdfBoxTableSettings table = null;

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
        int boxQuantity = 5;

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
        TableTemplateToPdbBox tableSize = TableTemplateToPdbBox.TABLE_5X6;

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
        String textContent = "";
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
