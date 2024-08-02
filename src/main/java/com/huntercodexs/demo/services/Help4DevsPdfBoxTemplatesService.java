package com.huntercodexs.demo.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.PageSizeToPdfBox.pageSize;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplatesService extends Help4DevsPdfBoxService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplate</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types</p>
     *
     * Templates available:<br />
     * <ul>
     *     <li>Slim</li>
     *     <li>Box</li>
     *     <li>Box Open</li>
     *     <li>Slim Box</li>
     *     <li>Simple 2</li>
     *     <li>Simple 3</li>
     * </ul>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param rectSettings (PdfBoxRectangleSettings)
     * @param textSettings (PdfBoxTextSettings)
     * @param imgSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfBoxTemplate(
            Help4DevsPdfBoxService.PdfBoxDocumentSettings docSettings,
            Help4DevsPdfBoxService.PdfBoxPageSettings pageSettings,
            Help4DevsPdfBoxService.PdfBoxRectangleSettings rectSettings,
            Help4DevsPdfBoxService.PdfBoxTextSettings textSettings,
            Help4DevsPdfBoxService.PdfBoxImageSettings imgSettings
    ) {
        //PDF CREATE
        try (PDDocument documentCreator = new PDDocument()) {

            //TODO: DEFINIR TAMANHO DA PAGINA EM TODOS OS LUGARES DE USO DO PDFBOX

            PDPage page = new PDPage(pageSize(pageSettings.getPageSize()));
            documentCreator.addPage(page);
            documentCreator.save(docSettings.getFilenamePath());
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        //START TEMPLATE
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, pageSettings, rectSettings, null);

            //SLIM CONFIG
            int defaultHeight = 135;
            int blockY1 = 640;
            int blockY2 = 485;
            int blockY3 = 330;
            int blockY4 = 175;
            int blockY5 = 20;

            //DATA
            String[] lines = {"line 1", "line 2", "line 3", "line 4", "line 5", "line 6"};

            //SLIM - RECTANGLE 1
            rectSettings.setHeight(defaultHeight);
            rectSettings.setOffsetY(blockY1);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            //SLIM - TEXT CONTENT 1
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(750);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            //SLIM - RECTANGLE 2
            rectSettings.setHeight(defaultHeight);
            rectSettings.setOffsetY(blockY2);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            //SLIM - TEXT CONTENT 2
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(595);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            //SLIM - RECTANGLE 3
            rectSettings.setHeight(defaultHeight);
            rectSettings.setOffsetY(blockY3);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            //SLIM - TEXT CONTENT 3
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(440);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            //SLIM - RECTANGLE 4
            rectSettings.setHeight(defaultHeight);
            rectSettings.setOffsetY(blockY4);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            //SLIM - TEXT CONTENT 4
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(285);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            //SLIM - RECTANGLE 5
            rectSettings.setHeight(defaultHeight);
            rectSettings.setOffsetY(blockY5);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            if (rectSettings.isBorder()) {
                contentStream = contentStream(
                        "rec-border", page, document, pageSettings, rectSettings, contentStream);
            }

            //SLIM - TEXT CONTENT 5
            pageSettings.setOffsetX(35);
            pageSettings.setOffsetY(130);
            pageSettings.setFontColor(ColorsToPdfBox.BLACK);
            pageSettings.setFontName(FontNameToPdfBox.HELVETICA);
            pageSettings.setFontSize(FontSizeToPdfBox.NORMAL);
            contentStream = contentStream(
                    "begin", page, document, pageSettings, rectSettings, contentStream);

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

    @Getter
    public enum TemplatesToPdfBox {
        SLIM("SLIM"),
        BOX("BOX"),
        BOX_OPEN("BOX_OPEN"),
        SLIM_BOX("SLIM_BOX"),
        SIMPLE_2("SIMPLE_2"),
        SIMPLE_3("SIMPLE_3");

        private final String template;

        TemplatesToPdfBox(String template) {
            this.template = template;
        }

        public static String template(TemplatesToPdfBox template) {
            return template.getTemplate();
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTemplateSettings {
        TemplatesToPdfBox template;
    }

}
