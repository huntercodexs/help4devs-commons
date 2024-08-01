package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplatesService extends Help4DevsPdfBoxService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfSlimTemplate</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types - Slim Template</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param rectSettings (PdfBoxRectangleSettings)
     * @param textSettings (PdfBoxTextSettings)
     * @param imgSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfSlimTemplate(
            Help4DevsPdfBoxService.PdfBoxDocumentSettings docSettings,
            Help4DevsPdfBoxService.PdfBoxPageSettings pageSettings,
            Help4DevsPdfBoxService.PdfBoxRectangleSettings rectSettings,
            Help4DevsPdfBoxService.PdfBoxTextSettings textSettings,
            Help4DevsPdfBoxService.PdfBoxImageSettings imgSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage((pageSettings.getPageNumber()-1)+1);
            stripper.setEndPage((pageSettings.getPageNumber()-1)+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, pageSettings, rectSettings, null);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

}
