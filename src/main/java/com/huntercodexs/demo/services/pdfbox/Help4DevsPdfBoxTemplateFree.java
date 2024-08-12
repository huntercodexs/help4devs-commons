package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PdfBoxPage.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.FreeTemplateSettings.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateFree extends Help4DevsPdfBoxTemplateBuilder {

    private static void drawContainer(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(FREE_DEFAULT_WIDTH);
        rectSettings.setHeight(FREE_DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        int widthAdjustA4 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().contains("A4")) {
            widthAdjustA4 = -15;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(FREE_DEFAULT_WIDTH+(widthAdjustA4));
        rectSettings.setOffsetX(FREE_DEFAULT_OFFSET_X);
        rectSettings.setOffsetY(FREE_DEFAULT_OFFSET_Y+(offsetYAdjustA4));

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

            contentStreamText("Free", pageSettings, contentStream);

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
                    contentStream);

        } catch (Exception ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void freeContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void freeContainerCreate(
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {

        drawContainer(settings, contentStream);

        if (settings.getFree().templateTitleEnabled) {
            drawTemplateTitle(settings.getPage(), contentStream);
        }
    }

    public void freeTemplateBuilder(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        freeContainerBackgroundCreate(document, settings, contentStream);
        freeContainerCreate(settings, contentStream);
    }

}
