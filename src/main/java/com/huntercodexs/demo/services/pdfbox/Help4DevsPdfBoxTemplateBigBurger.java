package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.BigBurgerTemplateSettings.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateBigBurger extends Help4DevsPdfBox {

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
            contentStream.showText("Big Burger");
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
        /*Header*/
        rectSettings.setOffsetX(BIG_BURGER_HEADER_OFFSET_X);
        rectSettings.setOffsetY(BIG_BURGER_HEADER_OFFSET_Y);

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

        /*Body*/
        rectSettings.setHeight(BIG_BURGER_BODY_HEIGHT);
        rectSettings.setOffsetY(BIG_BURGER_BODY_OFFSET_Y);

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

        /*Footer*/
        rectSettings.setHeight(BIG_BURGER_FOOTER_HEIGHT);
        rectSettings.setOffsetY(BIG_BURGER_FOOTER_OFFSET_Y);

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

    private static void headerBodyContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void bigBurgerContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(BIG_BURGER_HEADER_WIDTH);
        rectSettings.setHeight(BIG_BURGER_HEADER_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        drawContainer(document, page, rectSettings, pageSettings, contentStream);

        if (settings.getBigBurger().templateTitleEnabled) {
            drawTemplateTitle(document, page, pageSettings, contentStream);
        }
    }

    public void bigBurgerTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        headerBodyContainerBackgroundCreate(document, settings, contentStream);
        bigBurgerContainerCreate(document, page, settings, contentStream);
    }

}