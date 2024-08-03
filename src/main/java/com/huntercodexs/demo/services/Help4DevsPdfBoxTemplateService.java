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

    private static void pdfBackground(
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
        try {

            //SLIM - TITLE CONTENT 1 - LEFT
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(750);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 1");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 1 - CENTER
            pageSettings.setOffsetX(250);
            pageSettings.setOffsetY(750);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 1");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 1 - RIGHT
            pageSettings.setOffsetX(450);
            pageSettings.setOffsetY(750);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 1");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 2 - LEFT
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(595);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 2");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 2 - CENTER
            pageSettings.setOffsetX(250);
            pageSettings.setOffsetY(595);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 2");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 2 - RIGHT
            pageSettings.setOffsetX(450);
            pageSettings.setOffsetY(595);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 2");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 3 - LEFT
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(440);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 3");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 3 - CENTER
            pageSettings.setOffsetX(250);
            pageSettings.setOffsetY(440);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 3");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 3 - RIGHT
            pageSettings.setOffsetX(450);
            pageSettings.setOffsetY(440);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 3");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 4 - LEFT
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(285);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 4");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 4 - CENTER
            pageSettings.setOffsetX(250);
            pageSettings.setOffsetY(285);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 4");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 4 - RIGHT
            pageSettings.setOffsetX(450);
            pageSettings.setOffsetY(285);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 4");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 5 - LEFT
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(130);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 5");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 5 - CENTER
            pageSettings.setOffsetX(250);
            pageSettings.setOffsetY(130);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 5");
            contentStream.newLine();

            contentStream.endText();

            //SLIM - TITLE CONTENT 5 - RIGHT
            pageSettings.setOffsetX(450);
            pageSettings.setOffsetY(130);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.MEDIUM);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Title of Section 5");
            contentStream.newLine();

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimTextBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        String[] lines;

        pageSettings.setOffsetX(35);
        pageSettings.setOffsetY(725);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);
        pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
        pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

        pageSettings.setTextContent(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other");

        lines = pageSettings.getTextContent().split("\n");

        contentStream = contentStream(
                "begin", page, document, pageSettings, rectSettings, contentStream);

        try {
            int stop = 0;
            for (String line : lines) {
                if (stop == 5) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        pageSettings.setOffsetX(35);
        pageSettings.setOffsetY(575);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);
        pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
        pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

        pageSettings.setTextContent(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other");

        lines = pageSettings.getTextContent().split("\n");

        contentStream = contentStream(
                "begin", page, document, pageSettings, rectSettings, contentStream);

        try {
            int stop = 0;
            for (String line : lines) {
                if (stop == 5) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        pageSettings.setOffsetX(35);
        pageSettings.setOffsetY(415);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);
        pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
        pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

        pageSettings.setTextContent(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other");

        lines = pageSettings.getTextContent().split("\n");

        contentStream = contentStream(
                "begin", page, document, pageSettings, rectSettings, contentStream);

        try {
            int stop = 0;
            for (String line : lines) {
                if (stop == 5) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        pageSettings.setOffsetX(35);
        pageSettings.setOffsetY(260);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);
        pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
        pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);

        pageSettings.setTextContent(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other");

        lines = pageSettings.getTextContent().split("\n");

        contentStream = contentStream(
                "begin", page, document, pageSettings, rectSettings, contentStream);

        try {
            int stop = 0;
            for (String line : lines) {
                if (stop == 5) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        pageSettings.setOffsetX(35);
        pageSettings.setOffsetY(105);
        pageSettings.setFontColor(ColorsToPdfBox.BLACK);
        pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
        pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
        contentStream = contentStream(
                "begin", page, document, pageSettings, rectSettings, contentStream);

        try {
            int stop = 0;
            for (String line : lines) {
                if (stop == 5) break;
                contentStream.showText(line);
                contentStream.newLine();
                stop += 1;
            }

            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimImageBoxCreate(
            PDDocument document,
            PDPageContentStream contentStream,
            PdfBoxImageSettings imgSettings
    ) {
        try {
            //SLIM - IMAGE CONTENT 1 - LEFT
            imgSettings.setOffsetX(35);
            imgSettings.setOffsetY(650);
            PDImageXObject pdImageXObject = PDImageXObject.createFromFile(imgSettings.getFilenamePath(), document);

            if (imgSettings.isResize()) {
                pdImageXObject.setWidth(imgSettings.getWidth());
                pdImageXObject.setHeight(imgSettings.getHeight());
            }

            contentStream.drawImage(pdImageXObject, imgSettings.getOffsetX(), imgSettings.getOffsetY());

            //SLIM - IMAGE CONTENT 1 - CENTER
            imgSettings.setOffsetX(190);
            imgSettings.setOffsetY(650);
            pdImageXObject = PDImageXObject.createFromFile(imgSettings.getFilenamePath(), document);

            if (imgSettings.isResize()) {
                pdImageXObject.setWidth(imgSettings.getWidth());
                pdImageXObject.setHeight(imgSettings.getHeight());
            }

            contentStream.drawImage(pdImageXObject, imgSettings.getOffsetX(), imgSettings.getOffsetY());

            //SLIM - IMAGE CONTENT 1 - RIGHT
            imgSettings.setOffsetX(300);
            imgSettings.setOffsetY(650);
            pdImageXObject = PDImageXObject.createFromFile(imgSettings.getFilenamePath(), document);

            if (imgSettings.isResize()) {
                pdImageXObject.setWidth(imgSettings.getWidth());
                pdImageXObject.setHeight(imgSettings.getHeight());
            }

            contentStream.drawImage(pdImageXObject, imgSettings.getOffsetX(), imgSettings.getOffsetY());

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimTableBoxCreate(
            PDPageContentStream contentStream,
            PdfBoxRectangleSettings rectSettings
    ) {
        try {
            //SLIM - TABLE 2 - BODY
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    35,
                    500,
                    540,
                    90);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            //SLIM - TABLE 2 - HEADER 1
            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    35,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    125,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    215,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    305,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    395,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.setNonStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    485,
                    572,
                    90,
                    18);
            contentStream.fill();
            contentStream.closeAndStroke();
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.setStrokingColor(0, 0, 0);

            //SLIM - TABLE 2 - LINE 2
            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    35,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    125,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    215,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    305,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    395,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    485,
                    554,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            //SLIM - TABLE 2 - LINE 3
            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    35,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    125,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    215,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    305,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    395,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    485,
                    536,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            //SLIM - TABLE 2 - LINE 4
            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    35,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    125,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    215,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    305,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    395,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    485,
                    518,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            //SLIM - TABLE 2 - LINE 5
            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    35,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    125,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    215,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    305,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    395,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

            contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
            contentStream.addRect(
                    485,
                    500,
                    90,
                    18);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0, 0, 0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private static void slimColumnBoxCreate(PDPageContentStream contentStream) {
        try {

            //SLIM - SECTION 3 - COLUMN 1
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    35,
                    340,
                    170,
                    90);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //SLIM - SECTION 3 - COLUMN 2
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    210,
                    340,
                    190,
                    90);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //SLIM - SECTION 3 - COLUMN 3
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    405,
                    340,
                    170,
                    90);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimSignatureBoxCreate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings
    ) {
        try {

            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 1
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    55,
                    30,
                    500,
                    30);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 1 - TEXT
            pageSettings.setOffsetX(260);
            pageSettings.setOffsetY(52);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //SIGNATURE
            pageSettings.setOffsetX(130);
            pageSettings.setOffsetY(35);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Name Middle Name Last - DOC:123456789011 - 9089739827389 - 2020.01.01 10:00:00 -03:00");
            contentStream.newLine();
            contentStream.endText();
            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 1 - TEXT

            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 2
            contentStream.setStrokingColor(color(ColorsToPdfBox.BLACK));
            contentStream.addRect(
                    355,
                    30,
                    200,
                    100);
            contentStream.closeAndStroke();
            contentStream.setStrokingColor(0,0,0);

            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 2 - TEXT
            pageSettings.setOffsetX(405);
            pageSettings.setOffsetY(122);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_B);
            pageSettings.setFontSize(FontSizeToPdfBox.SMALL);

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            contentStream.showText("Digital Electronic Signature");
            contentStream.newLine();
            contentStream.endText();

            //SIGNATURE
            pageSettings.setOffsetX(370);
            pageSettings.setOffsetY(100);
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
            //SLIM - SECTION 5 - SIGNATURE BOX - STYLE 2 - TEXT

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimTemplate(
            PDDocument document,
            PDPage page,
            PDPageContentStream contentStream,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxImageSettings imgSettings,
            PdfBoxTextSettings textSettings
    ) {
        slimRectangleBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimTitleBoxCreate(document, page, contentStream, pageSettings, rectSettings);
        slimTableBoxCreate(contentStream, rectSettings);
        slimColumnBoxCreate(contentStream);
        slimSignatureBoxCreate(document, page, contentStream, pageSettings, rectSettings);
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

            pdfBackground(document, contentStream, tplSettings);

            if (tplSettings.getTemplate().name().equals(template(TemplatesToPdfBox.SLIM))) {
                slimTemplate(document, page, contentStream, pageSettings, rectSettings, imgSettings, textSettings);
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

}
