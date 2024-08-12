package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PdfBoxPage.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.SlimBoxTemplateSettings.*;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateSlimBox extends Help4DevsPdfBoxTemplateBuilder {

    private static void drawContainer(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(SLIM_BOX_DEFAULT_WIDTH);
        rectSettings.setHeight(SLIM_BOX_DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        int widthAdjustA4 = 0;
        int widthAdjustA4Rows15 = 0;
        int offsetXAdjustA4 = 0;
        int offsetYAdjustA4 = 0;

        if (pageSettings.getPageSize().name().contains("A4")) {
            widthAdjustA4 = -10;
            widthAdjustA4Rows15 = -20;
            offsetXAdjustA4 = -10;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(SLIM_BOX_DEFAULT_WIDTH+(widthAdjustA4));

        for (int rows = 1; rows <= SLIM_BOX_QUANTITY/2; rows++) {

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

            for (int cols = 1; cols <= SLIM_BOX_QUANTITY/5; cols++) {

                if (cols == 1) {
                    rectSettings.setOffsetX(20);
                } else {
                    rectSettings.setOffsetX(308+(offsetXAdjustA4));
                }

                if (cols == 2 && rows != 4) {
                    continue;
                }

                if (cols == 2) {
                    rectSettings.setHeight(SLIM_BOX_HEIGHT);
                }

                if (cols == 1 && (rows == 1 || rows == 5)) {
                    rectSettings.setWidth(SLIM_BOX_WIDTH+(widthAdjustA4Rows15));
                }

                if (rectSettings.getBackColor() == null) {
                    contentStreamEmptyRect(contentStream);

                } else if (rectSettings.getBackColor().getColorName().equals(ColorsToPdfBox.NONE.getColorName())) {
                    contentStreamEmptyRect(contentStream);

                } else {
                    contentStreamFillRect(rectSettings, contentStream);
                }

                if (rectSettings.isBorder()) {
                    contentStreamBorderRect(rectSettings, contentStream);
                }

                rectSettings.setHeight(SLIM_BOX_DEFAULT_HEIGHT);
                rectSettings.setWidth(SLIM_BOX_DEFAULT_WIDTH+(widthAdjustA4));
            }
        }
    }

    private static void drawTemplateTitle(
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            int offsetYAdjustA4 = 0;
            if (pageSettings.getPageSize().name().contains("A4")) {
                offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
            }

            pageSettings.setFontSize(FontSizeToPdfBox.LARGE);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_BI);
            pageSettings.setFontColor(ColorsToPdfBox.LIGHT_GRAY);

            pageSettings.setOffsetX(50);
            pageSettings.setOffsetY(700+(offsetYAdjustA4));

            contentStreamText("Slim Box", pageSettings, contentStream);

        } catch (Exception ioe) {
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

            contentStreamImage(
                    settings.getImageBackground(),
                    getPageWidth(settings.getPage().getPageSize().name()),
                    getPageHeight(settings.getPage().getPageSize().name()),
                    0,
                    0,
                    document,
                    contentStream
            );

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void slimBoxContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void slimBoxContainerCreate(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {

        drawContainer(settings, contentStream);

        if (settings.getSlimBox().templateTitleEnabled) {
            drawTemplateTitle(settings.getPage(), contentStream);
        }
    }

    public void slimBoxTemplateBuilder(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        slimBoxContainerBackgroundCreate(document, settings, contentStream);
        slimBoxContainerCreate(settings, contentStream);
    }

}
