package com.huntercodexs.demo.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.SlimTemplateSettings.slimValues;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.TemplatesToPdfBox.template;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateService extends Help4DevsPdfBoxService {

    private static void drawBackground(
            PDDocument document,
            PDPageContentStream contentStream,
            PdfBoxTemplateSettings tplSettings
    ) {
        try {
            PDImageXObject pdfImageBackground = PDImageXObject.createFromFile(tplSettings.getImageBackground(), document);
            contentStream.drawImage(pdfImageBackground, 0, 0, 620, 792);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static boolean hasTitle(int n, SlimContentSettings slimContentSettings) {

        if (slimContentSettings.leftTitleEnable[n]) {
            return true;
        }

        if (slimContentSettings.centerTitleEnable[n]) {
            return true;
        }

        return slimContentSettings.rightTitleEnable[n];
    }

    private static void drawTitle(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxPageSettings pageSettings,
            String title
    ) {
        try {

            contentStream("begin", page, document, pageSettings, rectSettings, contentStream);
            contentStream.showText(title);
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawText(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxPageSettings pageSettings,
            String[] lines,
            boolean hasTitle
    ) {
        contentStream("begin", page, document, pageSettings, rectSettings, contentStream);

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

    private static void drawTableContainer(int box, SlimContentSettings settings, PDPageContentStream contentStream) {
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
            SlimContentSettings settings,
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
            SlimContentSettings settings,
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
            SlimContentSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            //Column Box 1
            contentStream.setStrokingColor(color(settings.textColor));
            contentStream.addRect(
                    settings.columnBoxOffsetX[0],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //Column Box 2
            contentStream.setStrokingColor(color(settings.textColor));
            contentStream.addRect(
                    settings.columnBoxOffsetX[1],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //Column Box 3
            contentStream.setStrokingColor(color(settings.textColor));
            contentStream.addRect(
                    settings.columnBoxOffsetX[2],
                    settings.columnBoxOffsetY[box],
                    settings.columnBoxWidth,
                    settings.columnBoxHeight);
            contentStream.closeAndStroke();
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
            PdfBoxRectangleSettings rectSettings,
            SlimContentSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            //Signature Box
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    settings.signatureBoxOffsetX[index],
                    settings.signatureOffsetY[0],
                    settings.getSignatureBoxWidth(),
                    settings.getSignatureBoxHeight());
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //Signature Title
            pageSettings.setOffsetX(settings.signatureElectronicOffsetX[index]);
            pageSettings.setOffsetY(settings.signatureOffsetY[1]);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //Signature Value
            pageSettings.setOffsetX(settings.signatureValueOffsetX[index]);
            pageSettings.setOffsetY(settings.signatureOffsetY[2]);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            pageSettings.setLineHeight(16);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);
            contentStream.setLeading(pageSettings.getLineHeight());

            contentStream.showText("Name Middle Name Last");
            contentStream.newLine();
            contentStream.showText("DOC:123456789011 ");
            contentStream.newLine();
            contentStream.showText("9089739827389");
            contentStream.newLine();
            contentStream.showText("2020.01.01 10:00:00 -03:00");
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawSignatureFit(
            PDDocument document,
            PDPage page,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            SlimContentSettings settings,
            PDPageContentStream contentStream
    ) {
        try {

            //Signature Box
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    settings.getSignatureFitOffsetX(),
                    settings.getSignatureFitOffsetY(),
                    settings.getSignatureFitWidth(),
                    settings.getSignatureFitHeight());
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //Signature Title
            pageSettings.setOffsetX(settings.getSignatureFitTitleOffsetX());
            pageSettings.setOffsetY(settings.getSignatureFitTitleOffsetY());
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //Signature Value
            pageSettings.setOffsetX(settings.getSignatureFitValueOffsetX());
            pageSettings.setOffsetY(settings.getSignatureFitValueOffsetY());
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Name Middle Name Last - DOC:123456789011 - 9089739827389 - 2020.01.01 10:00:00 -03:00");
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimRectangleBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        rectSettings.setHeight(slimValues(SlimTemplateSettings.DEFAULT_HEIGHT));
        rectSettings.setWidth(slimValues(SlimTemplateSettings.DEFAULT_WIDTH));

        for (int n = 1; n <= 5; n++) {

            if (n == 1) {
                rectSettings.setOffsetY(slimValues(SlimTemplateSettings.OFFSET_Y_BLOCK1));
            } else if (n == 2) {
                rectSettings.setOffsetY(slimValues(SlimTemplateSettings.OFFSET_Y_BLOCK2));
            } else if (n == 3) {
                rectSettings.setOffsetY(slimValues(SlimTemplateSettings.OFFSET_Y_BLOCK3));
            } else if (n == 4) {
                rectSettings.setOffsetY(slimValues(SlimTemplateSettings.OFFSET_Y_BLOCK4));
            } else {
                rectSettings.setOffsetY(slimValues(SlimTemplateSettings.OFFSET_Y_BLOCK5));
            }

            contentStream("rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream("rec-border", page, document, pageSettings, rectSettings, contentStream);
            }
        }
    }

    private static void slimTitleBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        SlimContentSettings slimContentSettings = new SlimContentSettings();
        slimContentSettings.setLeftTitleEnable(new boolean[]{true,true,true,true,true});
        slimContentSettings.setCenterTitleEnable(new boolean[]{true,true,true,true,true});
        slimContentSettings.setRightTitleEnable(new boolean[]{true,true,true,true,true});

        for (int n = 0; n < slimContentSettings.getBoxQuantity(); n++) {

            //Left Title
            if (slimContentSettings.leftTitleEnable[n]) {
                pageSettings.setOffsetX(slimContentSettings.titleOffsetX[0]);
                pageSettings.setOffsetY(slimContentSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimContentSettings.getLeftTitleColor());
                pageSettings.setFontName(slimContentSettings.getLeftTitleFont());
                pageSettings.setFontSize(slimContentSettings.getLeftTitleSize());

                String title = slimContentSettings.getLeftTitleText();
                drawTitle(document, page, contentStream, rectSettings, pageSettings, title);
            }

            //Center Title
            if (slimContentSettings.centerTitleEnable[n]) {
                pageSettings.setOffsetX(slimContentSettings.titleOffsetX[1]);
                pageSettings.setOffsetY(slimContentSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimContentSettings.getCenterTitleColor());
                pageSettings.setFontName(slimContentSettings.getCenterTitleFont());
                pageSettings.setFontSize(slimContentSettings.getCenterTitleSize());

                String title = slimContentSettings.getCenterTitleText();
                drawTitle(document, page, contentStream, rectSettings, pageSettings, title);
            }

            //Right Title
            if (slimContentSettings.rightTitleEnable[n]) {
                pageSettings.setOffsetX(slimContentSettings.titleOffsetX[2]);
                pageSettings.setOffsetY(slimContentSettings.titleOffsetY[n]);

                pageSettings.setFontColor(slimContentSettings.getRightTitleColor());
                pageSettings.setFontName(slimContentSettings.getRightTitleFont());
                pageSettings.setFontSize(slimContentSettings.getRightTitleSize());

                String title = slimContentSettings.getRightTitleText();
                drawTitle(document, page, contentStream, rectSettings, pageSettings, title);
            }

        }

    }

    private static void slimTextBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        SlimContentSettings settings = new SlimContentSettings();
        settings.setTextEnable(new boolean[]{true,true,true,true,true});

        pageSettings.setOffsetX(settings.textOffsetX);
        pageSettings.setFontColor(settings.getTextColor());
        pageSettings.setFontName(settings.getTextFont());
        pageSettings.setFontSize(settings.getTextSize());
        pageSettings.setTextContent(settings.getTextContent());
        pageSettings.setLineHeight(settings.lineHeight);

        String[] lines = pageSettings.getTextContent().split("\n");

        for (int n = 0; n < settings.getBoxQuantity(); n++) {
            if (settings.textEnable[n]) {
                pageSettings.setOffsetY(settings.textOffsetY[n]);
                drawText(document, page, contentStream, rectSettings, pageSettings, lines, hasTitle(n, settings));
            }
        }
    }

    private static void slimImageBoxCreate(
            PDDocument document,
            PDPageContentStream contentStream,
            PdfBoxImageSettings imgSettings
    ) {
        SlimContentSettings settings = new SlimContentSettings();
        settings.setLeftImageEnable(new boolean[]{true,true,true,true,true});
        settings.setCenterImageEnable(new boolean[]{true,true,true,true,true});
        settings.setRightImageEnable(new boolean[]{true,true,true,true,true});

        settings.setLeftImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setCenterImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setRightImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });

        for (int n = 0; n < settings.getBoxQuantity(); n++) {

            String imgLeft = settings.leftImagePaths[n];
            String imgCenter = settings.centerImagePaths[n];
            String imgRight = settings.rightImagePaths[n];

            //Image Left
            if (settings.leftImageEnable[n] && imgLeft != null && !imgLeft.isEmpty()) {
                imgSettings.setOffsetX(settings.imageOffsetX[0]);
                imgSettings.setOffsetY(settings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgLeft);
                drawImage(document, imgSettings, contentStream);
            }

            //Image Center
            if (settings.centerImageEnable[n] && imgCenter != null && !imgCenter.isEmpty()) {
                imgSettings.setOffsetX(settings.imageOffsetX[1]);
                imgSettings.setOffsetY(settings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgCenter);
                drawImage(document, imgSettings, contentStream);
            }

            //Image Right
            if (settings.rightImageEnable[n] && imgRight != null && !imgRight.isEmpty()) {
                imgSettings.setOffsetX(settings.imageOffsetX[2]);
                imgSettings.setOffsetY(settings.imageOffsetY[n]);
                imgSettings.setFilenamePath(imgRight);
                drawImage(document, imgSettings, contentStream);
            }
        }
    }

    private static void slimTableBoxCreate(
            PDPageContentStream contentStream,
            PdfBoxRectangleSettings rectSettings
    ) {
        SlimContentSettings settings = new SlimContentSettings();
        settings.setTableSize(TableTemplateToPdbBox.TABLE_5X6);
        settings.setTableEnable(new boolean[]{true,true,true,true,true});

        for (int box = 0; box < settings.getBoxQuantity(); box++) {

            if (settings.tableEnable[box]) {

                drawTableContainer(box, settings, contentStream);

                for (int cols = 0; cols < settings.getTableSize().getTableColumns(); cols++) {
                    drawTableHeader(box, cols, settings, contentStream);
                }

                int initialX = settings.tableOffsetX;
                int initialY = settings.tableDataOffsetY[box];
                int celWidth = settings.columnWidth;
                int celHeight = settings.columnHeight;

                //tableLines-1: to remove the first line (because was used in the table header)
                int tableLines = settings.getTableSize().getTableLines()-1;
                int tableColumns = settings.getTableSize().getTableColumns();

                for (int row = 0; row < tableLines; row++) {
                    for (int col = 0; col < tableColumns; col++) {
                        drawTableBody(initialX, initialY, celWidth, celHeight, settings, contentStream);
                        initialX += celWidth; //move to the next column
                    }

                    initialX = settings.tableOffsetX; //reset offset-x position
                    initialY -= celHeight; //move to the next line
                }
            }
        }
    }

    private static void slimColumnBoxCreate(PDPageContentStream contentStream) {

        SlimContentSettings settings = new SlimContentSettings();
        settings.setColumnBoxEnable(new boolean[]{true,true,true,true,true});

        for (int box = 0; box < settings.getBoxQuantity(); box++) {
            if (settings.columnBoxEnable[box]) {
                drawColumnBox(box, settings, contentStream);
            }
        }
    }

    private static void slimSignatureBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        SlimContentSettings settings = new SlimContentSettings();
        settings.setLeftSignatureBoxEnable(true);
        settings.setCenterSignatureBoxEnable(true);
        settings.setRightSignatureBoxEnable(true);

        //Left Signature
        if (settings.isLeftSignatureBoxEnable()) {
            drawSignatureBox(0, document, page, pageSettings, rectSettings, settings, contentStream);
        }

        //Center Signature
        if (settings.isCenterSignatureBoxEnable()) {
            drawSignatureBox(1, document, page, pageSettings, rectSettings, settings, contentStream);
        }

        //Right Signature
        if (settings.isRightSignatureBoxEnable()) {
            drawSignatureBox(2, document, page, pageSettings, rectSettings, settings, contentStream);
        }
    }

    private static void slimSignatureFitCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        SlimContentSettings settings = new SlimContentSettings();
        settings.setSignatureFitEnable(true);

        if (settings.isSignatureFitEnable()) {
            drawSignatureFit(document, page, pageSettings, rectSettings, settings, contentStream);
        }
    }

    private static void slimTemplate(
            PDDocument document,
            PDPage page,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxImageSettings imgSettings,
            PdfBoxTextSettings textSettings,
            PDPageContentStream contentStream
    ) {
        slimRectangleBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimTitleBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimTableBoxCreate(contentStream, rectSettings);
        slimColumnBoxCreate(contentStream);
        slimSignatureBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimSignatureFitCreate(document, page, contentStream, pageSettings, rectSettings);
        slimTextBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimImageBoxCreate(document, contentStream, imgSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplate</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types</p>
     * <p>
     * Templates available:<br />
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
     * @param docSettings  (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param rectSettings (PdfBoxRectangleSettings)
     * @param textSettings (PdfBoxTextSettings)
     * @param imgSettings  (PdfBoxImageSettings)
     * @param tplSettings  (PdfBoxTemplateSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfBoxTemplate(
            PdfBoxDocumentSettings docSettings,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxTextSettings textSettings,
            PdfBoxImageSettings imgSettings,
            PdfBoxTemplateSettings tplSettings
    ) {
        pdfCreate(docSettings, pageSettings);

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            String[] lines = new String[]{};
            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, pageSettings, rectSettings, null);

            drawBackground(document, contentStream, tplSettings);

            if (tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.SLIM))) {
                slimTemplate(document, page, pageSettings, rectSettings, imgSettings, textSettings, contentStream);
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.BOX))) {
                throw new RuntimeException("TODO: BOX Template");
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.BOX_OPEN))) {
                throw new RuntimeException("TODO: BOX_OPEN Template");
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.SLIM_BOX))) {
                throw new RuntimeException("TODO: SLIM_BOX Template");
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.SIMPLE_2))) {
                throw new RuntimeException("TODO: SIMPLE_2 Template");
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.SIMPLE_3))) {
                throw new RuntimeException("TODO: SIMPLE_3 Template");
            } else if(tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.FREE))) {
                throw new RuntimeException("TODO: FREE Template");
            } else {
                throw new RuntimeException("PDFBox Template not found");
            }

            contentStream.close();
            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    @Getter
    public enum TemplatesToPdfBox {
        FREE("FREE"),
        SLIM("SLIM"),
        BOX("BOX"),
        BOX_OPEN("BOX_OPEN"),
        SLIM_BOX("SLIM_BOX"),
        SIMPLE_2("SIMPLE_2"),
        SIMPLE_3("SIMPLE_3");

        private final String template;

        TemplatesToPdfBox(String template) {
            this.template = template;
        }

        public static String template(TemplatesToPdfBox template) {
            return template.getTemplate();
        }
    }

    @Getter
    public enum SlimTemplateSettings {
        DEFAULT_WIDTH(570),
        DEFAULT_HEIGHT(135),
        OFFSET_Y_BLOCK1(640),
        OFFSET_Y_BLOCK2(485),
        OFFSET_Y_BLOCK3(330),
        OFFSET_Y_BLOCK4(175),
        OFFSET_Y_BLOCK5(20);

        private final int slimValues;

        SlimTemplateSettings(int slimValues) {
            this.slimValues = slimValues;
        }

        public static int slimValues(SlimTemplateSettings slimValues) {
            return slimValues.getSlimValues();
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTemplateSettings {
        int width;
        int height;
        int offsetX;
        int offsetY;
        String imageBackground;
        TemplatesToPdfBox template;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlimContentSettings {
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

        //Text settings
        int lineHeight = 18;
        int textOffsetX = 35;
        int[] textOffsetY = new int[]{732,577,421,266,111};
        boolean[] textEnable = new boolean[]{false,false,false,false,false};
        String textContent =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
            "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
            "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
            "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
            "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
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
        boolean[] columnBoxEnable = new boolean[]{false,false,false,false,false};

        //Signature Box Settings
        int signatureBoxWidth = 200;
        int signatureBoxHeight = 100;
        int[] signatureBoxOffsetX = new int[]{55,210,355};
        int[] signatureElectronicOffsetX = new int[]{105,260,405};
        int[] signatureValueOffsetX = new int[]{70,230,370};
        int[] signatureOffsetY = new int[]{30,122,100};
        boolean leftSignatureBoxEnable = false;
        boolean centerSignatureBoxEnable = false;
        boolean rightSignatureBoxEnable = false;

        //Signature Fit Settings
        int signatureFitWidth = 500;
        int signatureFitHeight = 30;
        int signatureFitOffsetX = 55;
        int signatureFitOffsetY = 30;
        int signatureFitTitleOffsetX = 260;
        int signatureFitTitleOffsetY = 52;
        int signatureFitValueOffsetX = 130;
        int signatureFitValueOffsetY = 35;
        boolean signatureFitEnable = false;
    }

}
