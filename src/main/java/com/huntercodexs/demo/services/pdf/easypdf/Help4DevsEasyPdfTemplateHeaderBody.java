package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.EasyPdfPage.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.HeaderBodyTemplateSettings.*;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsEasyPdfTemplateHeaderBody extends Help4DevsEasyPdfTemplateBuilder {

    private static void drawContainer(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        EasyPdfContainer rectSettings = settings.getContainer();

        EasyPdfPage pageSettings = settings.getPage();

        int widthAdjustA4 = 0;
        int offsetYAdjustA4 = 0;
        if (pageSettings.getPageSize().name().contains("A4")) {
            widthAdjustA4 = WIDTH_ADJUST_A4;
            offsetYAdjustA4 = OFFSET_Y_ADJUST_A4;
        }

        rectSettings.setWidth(HEADER_BODY_DEFAULT_WIDTH+(widthAdjustA4));
        rectSettings.setHeight(HEADER_BODY_DEFAULT_HEIGHT);

        /*Header*/
        rectSettings.setOffsetX(HEADER_BODY_DEFAULT_OFFSET_X);
        rectSettings.setOffsetY(HEADER_BODY_DEFAULT_OFFSET_Y+(offsetYAdjustA4));

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

        /*Body*/
        rectSettings.setHeight(HEADER_BODY_HEIGHT);
        rectSettings.setOffsetY(HEADER_BODY_OFFSET_Y+(offsetYAdjustA4));

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

            contentStreamText("Header Body", pageSettings, contentStream);

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

    private static void headerBodyContainerBackgroundCreate(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void headerBodyContainerCreate(
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {

        drawContainer(settings, contentStream);

        if (settings.getHeaderBody().templateTitleEnabled) {
            drawTemplateTitle(settings.getPage(), contentStream);
        }
    }

    public void headerBodyTemplateBuilder(
            PDDocument document,
            EasyPdfTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        headerBodyContainerBackgroundCreate(document, settings, contentStream);
        headerBodyContainerCreate(settings, contentStream);
    }

}