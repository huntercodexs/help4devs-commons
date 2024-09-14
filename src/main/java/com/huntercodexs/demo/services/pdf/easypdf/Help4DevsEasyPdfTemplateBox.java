package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.EasyPdfPage.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.BoxTemplateSettings.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsEasyPdfTemplateBox extends Help4DevsEasyPdfTemplateBuilder {

    private static void drawContainer(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        EasyPdfContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(BOX_DEFAULT_WIDTH);
        rectSettings.setHeight(BOX_DEFAULT_HEIGHT);

        EasyPdfPage pageSettings = settings.getPage();

        int widthAdjustA4 = 0;
        int offsetXAdjustA4 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().contains("A4")) {
            widthAdjustA4 = -10;
            offsetXAdjustA4 = -10;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(BOX_DEFAULT_WIDTH+(widthAdjustA4));

        for (int rows = 1; rows <= BOXES/2; rows++) {

            if (rows == 1) {
                rectSettings.setOffsetY(640+(offsetYAdjustA4));
            } else if (rows == 2) {
                rectSettings.setOffsetY(485+(offsetYAdjustA4));
            } else if (rows == 3) {
                rectSettings.setOffsetY(330+(offsetYAdjustA4));
            } else if (rows == 4) {
                rectSettings.setOffsetY(175+(offsetYAdjustA4));
            } else {
                rectSettings.setOffsetY(20+(offsetYAdjustA4));
            }

            for (int cols = 1; cols <= BOXES/5; cols++) {

                if (cols == 1) {
                    rectSettings.setOffsetX(20);
                } else {
                    rectSettings.setOffsetX(308+(offsetXAdjustA4));
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

            contentStreamText("Box", pageSettings, contentStream);

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

    private static void boxContainerBackgroundCreate(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void boxContainerCreate(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawContainer(settings, contentStream);

        if (settings.getBox().templateTitleEnabled) {
            drawTemplateTitle(settings.getPage(), contentStream);
        }
    }

    public void boxTemplateBuilder(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        boxContainerBackgroundCreate(document, settings, contentStream);
        boxContainerCreate(settings, contentStream);
    }

}
