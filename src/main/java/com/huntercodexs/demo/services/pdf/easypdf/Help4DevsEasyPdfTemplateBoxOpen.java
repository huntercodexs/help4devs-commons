package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.EasyPdfPage.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.BoxOpenTemplateSettings.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.SlimBoxTemplateSettings.SLIM_BOX_DEFAULT_WIDTH;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsEasyPdfTemplateBoxOpen extends Help4DevsEasyPdfTemplateBuilder {

    private static void drawContainer(
            EasyPdfContainer rectSettings,
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        rectSettings.setWidth(BOX_OPEN_DEFAULT_WIDTH);
        rectSettings.setHeight(BOX_OPEN_DEFAULT_HEIGHT);

        int widthAdjustA4 = 0;
        int offsetXAdjustA4 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().contains("A4")) {
            widthAdjustA4 = -10;
            offsetXAdjustA4 = -10;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(SLIM_BOX_DEFAULT_WIDTH+(widthAdjustA4));

        for (int rows = 1; rows <= BOX_OPEN_QUANTITY/2; rows++) {

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

            for (int cols = 1; cols <= BOX_OPEN_QUANTITY/5; cols++) {

                if (cols == 1) {
                    rectSettings.setOffsetX(20);
                } else {
                    rectSettings.setOffsetX(308+(offsetXAdjustA4));
                }

                if (cols == 2 && (rows == 2 || rows == 3)) {
                    continue;
                }

                if (cols == 2 && rows == 4) {
                    rectSettings.setHeight(BOX_OPEN_HEIGHT);
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

                rectSettings.setHeight(BOX_OPEN_DEFAULT_HEIGHT);
                rectSettings.setWidth(BOX_OPEN_DEFAULT_WIDTH+(widthAdjustA4));
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

            contentStreamText("Box Open", pageSettings, contentStream);

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

    private static void boxOpenContainerBackgroundCreate(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void boxOpenContainerCreate(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        EasyPdfContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(BOX_OPEN_DEFAULT_WIDTH);
        rectSettings.setHeight(BOX_OPEN_DEFAULT_HEIGHT);

        EasyPdfPage pageSettings = settings.getPage();

        drawContainer(rectSettings, pageSettings, contentStream);

        if (settings.getBoxOpen().templateTitleEnabled) {
            drawTemplateTitle(pageSettings, contentStream);
        }
    }

    public void boxOpenTemplateBuilder(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        boxOpenContainerBackgroundCreate(document, settings, contentStream);
        boxOpenContainerCreate(settings, contentStream);
    }

}
