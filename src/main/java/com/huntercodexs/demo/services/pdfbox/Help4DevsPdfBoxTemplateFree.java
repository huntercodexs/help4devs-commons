package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.FreeTemplateSettings.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateFree extends Help4DevsPdfBox {

    private static void drawTemplateTitle(
            PDDocument document,
            PDPage page,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            pageSettings.setFontSize(FontSizeToPdfBox.LARGE);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA_BI);
            pageSettings.setFontColor(ColorsToPdfBox.LIGHT_GRAY);

            pageSettings.setOffsetX(50);
            pageSettings.setOffsetY(700);

            contentStream("text", page, document, pageSettings, null, contentStream);
            contentStream.showText("Free");
            contentStream.newLine();
            contentStream.endText();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void drawContainer(
            PDDocument document,
            PDPage page,
            PdfBoxContainer rectSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        rectSettings.setOffsetX(FREE_DEFAULT_OFFSET_X);
        rectSettings.setOffsetY(FREE_DEFAULT_OFFSET_Y);

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

    private static void drawBackground(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        if (settings.getImageBackground() == null) return;

        try {
            PDImageXObject pdfImageBackground = PDImageXObject.createFromFile(settings.getImageBackground(), document);
            contentStream.drawImage(pdfImageBackground, 0, 0, 620, 792);
        } catch (IOException ioe) {
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
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(FREE_DEFAULT_WIDTH);
        rectSettings.setHeight(FREE_DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        drawContainer(document, page, rectSettings, pageSettings, contentStream);

        if (settings.getFree().templateTitleEnabled) {
            drawTemplateTitle(document, page, pageSettings, contentStream);
        }
    }

    public void freeTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        freeContainerBackgroundCreate(document, settings, contentStream);
        freeContainerCreate(document, page, settings, contentStream);
    }

}