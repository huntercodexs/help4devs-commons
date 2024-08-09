package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBox.contentStream;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBox.pdfCreate;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplateSettings;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateSettings.PdfBoxTemplates.template;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxTemplate {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateSlim</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Slim Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateSlim(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.SLIM))) {
            throw new RuntimeException("Invalid Template: Expected SLIM");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateSlim template = new Help4DevsPdfBoxTemplateSlim();
            template.slimTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateBox</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Box Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateBox(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX))) {
            throw new RuntimeException("Invalid Template: Expected BOX");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateBox template = new Help4DevsPdfBoxTemplateBox();
            template.boxTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateBoxOpen</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Box Open Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateBoxOpen(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.BOX_OPEN))) {
            throw new RuntimeException("Invalid Template: Expected BOX_OPEN");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateBoxOpen template = new Help4DevsPdfBoxTemplateBoxOpen();
            template.boxOpenTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateSlimBox</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Slim Box Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateSlimBox(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.SLIM_BOX))) {
            throw new RuntimeException("Invalid Template: Expected SLIM_BOX");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateSlimBox template = new Help4DevsPdfBoxTemplateSlimBox();
            template.slimBoxTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateTripleFall</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Triple Fall Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateTripleFall(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.TRIPLE_FALL))) {
            throw new RuntimeException("Invalid Template: Expected TRIPLE_FALL");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateTripleFall template = new Help4DevsPdfBoxTemplateTripleFall();
            template.tripleFallTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateFree</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Free Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateFree(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.FREE))) {
            throw new RuntimeException("Invalid Template: Expected FREE");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateFree template = new Help4DevsPdfBoxTemplateFree();
            template.freeTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateHeaderBody</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using HeaderBody Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateHeaderBody(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.HEADER_BODY))) {
            throw new RuntimeException("Invalid Template: Expected HEADER_BODY");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateHeaderBody template = new Help4DevsPdfBoxTemplateHeaderBody();
            template.headerBodyTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfBoxTemplateBigBurger</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using BigBurger Template</p>
     *
     * @param settings (PdfBoxTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void pdfBoxTemplateBigBurger(PdfBoxTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(PdfBoxTemplates.BIG_BURGER))) {
            throw new RuntimeException("Invalid Template: Expected BIG_BURGER");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, settings.getPage(), settings.getContainer(), null);

            Help4DevsPdfBoxTemplateBigBurger template = new Help4DevsPdfBoxTemplateBigBurger();
            template.bigBurgerTemplateBuilder(document, page, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

}
