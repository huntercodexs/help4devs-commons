package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.EasyPdfPage.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.TripleFallTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsEasyPdfTemplateTripleFall extends Help4DevsEasyPdfTemplateBuilder {

    private static void drawContainer(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        EasyPdfContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(TRIPLE_FALL_DEFAULT_WIDTH);
        rectSettings.setHeight(TRIPLE_FALL_DEFAULT_HEIGHT);

        EasyPdfPage pageSettings = settings.getPage();

        rectSettings.setOffsetY(20);

        int widthAdjustA4 = 0;
        int offsetXAdjustA4Col1 = 0;
        int offsetXAdjustA4Col2 = 0;
        int offsetXAdjustA4Col3 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().contains("A4")) {
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
                contentStreamEmptyRect(contentStream);

            } else if (rectSettings.getBackColor().getColorName().equals(ColorsToEasyPdf.NONE.getColorName())) {
                contentStreamEmptyRect(contentStream);

            } else {
                contentStreamFillRect(rectSettings, contentStream);
            }

            if (rectSettings.isBorder()) {
                contentStreamBorderRect(rectSettings, contentStream);
            }
        }
    }

    private static void drawTemplateTitle(
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            int offsetYAdjustA4 = 0;
            if (pageSettings.getPageSize().name().contains("A4")) {
                offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
            }

            pageSettings.setFontSize(FontSizeToEasyPdf.LARGE);
            pageSettings.setFontName(FontNameToEasyPdf.HELVETICA_BI);
            pageSettings.setFontColor(ColorsToEasyPdf.LIGHT_GRAY);

            pageSettings.setOffsetX(50);
            pageSettings.setOffsetY(700+(offsetYAdjustA4));

            contentStreamText("Triple Fall", pageSettings, contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawBackground(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        if (settings.getImageBackground() == null) return;

        try {

            contentStreamImage(
                    settings.getImageBackground(),
                    getPageWidth(settings.getPage().getPageSize().name()),
                    getPageHeight(settings.getPage().getPageSize().name()),
                    0,
                    0,
                    document,
                    contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void tripleFallContainerBackgroundCreate(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void tripleFallContainerCreate(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {

        drawContainer(settings, contentStream);

        if (settings.getTripleFall().templateTitleEnabled) {
            drawTemplateTitle(settings.getPage(), contentStream);
        }
    }

    public void tripleFallTemplateBuilder(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        tripleFallContainerBackgroundCreate(document, settings, contentStream);
        tripleFallContainerCreate(settings, contentStream);
    }

}
