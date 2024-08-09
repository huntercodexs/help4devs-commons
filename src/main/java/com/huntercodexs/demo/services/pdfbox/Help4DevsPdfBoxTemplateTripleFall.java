package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PdfBoxPage.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.TripleFallTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateTripleFall extends Help4DevsPdfBoxTemplateBuilder {

    private static void drawContainer(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(TRIPLE_FALL_DEFAULT_WIDTH);
        rectSettings.setHeight(TRIPLE_FALL_DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        rectSettings.setOffsetY(20);

        int widthAdjustA4 = 0;
        int offsetXAdjustA4Col1 = 0;
        int offsetXAdjustA4Col2 = 0;
        int offsetXAdjustA4Col3 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().equals("A4")) {
            widthAdjustA4 = -7;
            offsetXAdjustA4Col1 = 1;
            offsetXAdjustA4Col2 = -7;
            offsetXAdjustA4Col3 = -14;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(TRIPLE_FALL_DEFAULT_WIDTH+(widthAdjustA4));
        rectSettings.setOffsetY(20+(offsetYAdjustA4));

        for (int rows = 1; rows <= TRIPLE_FALL_QUANTITY; rows++) {

            if (rows == 1) {
                rectSettings.setOffsetX(20+(offsetXAdjustA4Col1));
            } else if (rows == 2) {
                rectSettings.setOffsetX(213+(offsetXAdjustA4Col2));
            } else {
                rectSettings.setOffsetX(405+(offsetXAdjustA4Col3));
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

            int offsetYAdjustA4 = 0;
            if (pageSettings.getPageSize().name().equals("A4")) {
                offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
            }

            pageSettings.setFontSize(FontSizeToPdfBox.LARGE);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_BI);
            pageSettings.setFontColor(ColorsToPdfBox.LIGHT_GRAY);

            pageSettings.setOffsetX(50);
            pageSettings.setOffsetY(700+(offsetYAdjustA4));

            contentStream("text", page, document, pageSettings, null, contentStream);
            contentStream.showText("Triple Fall");
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

    private static void tripleFallContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void tripleFallContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {

        drawContainer(document, page, settings, contentStream);

        if (settings.getTripleFall().templateTitleEnabled) {
            drawTemplateTitle(document, page, settings.getPage(), contentStream);
        }
    }

    public void tripleFallTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        tripleFallContainerBackgroundCreate(document, settings, contentStream);
        tripleFallContainerCreate(document, page, settings, contentStream);
    }

}
