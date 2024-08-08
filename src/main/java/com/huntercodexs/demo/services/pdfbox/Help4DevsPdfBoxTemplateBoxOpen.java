package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.BoxOpenTemplateSettings.*;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplateBoxOpen extends Help4DevsPdfBox {

    private static void drawContainer(
            PDDocument document,
            PDPage page,
            PdfBoxContainer rectSettings,
            PdfBoxPage pageSettings,
            PDPageContentStream contentStream
    ) {
        for (int rows = 1; rows <= BOX_OPEN_QUANTITY/2; rows++) {

            if (rows == 1) {
                rectSettings.setOffsetY(640);
            } else if (rows == 2) {
                rectSettings.setOffsetY(485);
            } else if (rows == 3) {
                rectSettings.setOffsetY(330);
            } else if (rows == 4) {
                rectSettings.setOffsetY(175);
            } else {
                rectSettings.setOffsetY(20);
            }

            for (int cols = 1; cols <= BOX_OPEN_QUANTITY/5; cols++) {

                if (cols == 1) {
                    rectSettings.setOffsetX(20);
                } else {
                    rectSettings.setOffsetX(308);
                }

                if (cols == 2 && (rows == 2 || rows == 3)) {
                    continue;
                }

                if (cols == 2 && rows == 4) {
                    rectSettings.setHeight(BOX_OPEN_HEIGHT);
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

                rectSettings.setHeight(BOX_OPEN_DEFAULT_HEIGHT);
            }
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

    private static void boxOpenContainerBackgroundCreate(
            PDDocument document,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        drawBackground(document, settings, contentStream);
    }

    private static void boxOpenContainerCreate(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        PdfBoxContainer rectSettings = settings.getContainer();
        rectSettings.setWidth(BOX_OPEN_DEFAULT_WIDTH);
        rectSettings.setHeight(BOX_OPEN_DEFAULT_HEIGHT);

        PdfBoxPage pageSettings = settings.getPage();

        drawContainer(document, page, rectSettings, pageSettings, contentStream);
    }

    public void boxOpenTemplateBuilder(
            PDDocument document,
            PDPage page,
            PdfBoxTemplateSettings settings,
            PDPageContentStream contentStream
    ) {
        boxOpenContainerBackgroundCreate(document, settings, contentStream);
        boxOpenContainerCreate(document, page, settings, contentStream);
    }

}
