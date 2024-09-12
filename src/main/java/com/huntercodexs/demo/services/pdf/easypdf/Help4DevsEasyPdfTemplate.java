package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdf.pdfCreate;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfCore.contentStreamInit;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplateSettings;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplates;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfTemplateSettings.EasyPdfTemplates.template;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsEasyPdfTemplate {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateSlim</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Slim Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateSlim(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.SLIM))) {
            throw new RuntimeException("Invalid Template: Expected SLIM");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateSlim template = new Help4DevsEasyPdfTemplateSlim();
            template.slimTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateBox</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Box Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateBox(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.BOX))) {
            throw new RuntimeException("Invalid Template: Expected BOX");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateBox template = new Help4DevsEasyPdfTemplateBox();
            template.boxTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateBoxOpen</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Box Open Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateBoxOpen(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.BOX_OPEN))) {
            throw new RuntimeException("Invalid Template: Expected BOX_OPEN");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateBoxOpen template = new Help4DevsEasyPdfTemplateBoxOpen();
            template.boxOpenTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateSlimBox</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Slim Box Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateSlimBox(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.SLIM_BOX))) {
            throw new RuntimeException("Invalid Template: Expected SLIM_BOX");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateSlimBox template = new Help4DevsEasyPdfTemplateSlimBox();
            template.slimBoxTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateTripleFall</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Triple Fall Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateTripleFall(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.TRIPLE_FALL))) {
            throw new RuntimeException("Invalid Template: Expected TRIPLE_FALL");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateTripleFall template = new Help4DevsEasyPdfTemplateTripleFall();
            template.tripleFallTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateFree</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using Free Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateFree(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.FREE))) {
            throw new RuntimeException("Invalid Template: Expected FREE");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateFree template = new Help4DevsEasyPdfTemplateFree();
            template.freeTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateHeaderBody</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using HeaderBody Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateHeaderBody(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.HEADER_BODY))) {
            throw new RuntimeException("Invalid Template: Expected HEADER_BODY");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateHeaderBody template = new Help4DevsEasyPdfTemplateHeaderBody();
            template.headerBodyTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">easyPdfTemplateBigBurger</h6>
     *
     * <p style="color: #CDCDCD">Create a template file for PDF media types using BigBurger Template</p>
     *
     * @param settings (EasyPdfTemplateSettings: All template settings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public void easyPdfTemplateBigBurger(EasyPdfTemplateSettings settings) {

        if (!settings.getTemplate().name().equals(template(EasyPdfTemplates.BIG_BURGER))) {
            throw new RuntimeException("Invalid Template: Expected BIG_BURGER");
        }

        pdfCreate(settings.getDocument(), settings.getPage());

        File file = new File(settings.getDocument().getFilenamePath());

        try (PDDocument document = PDDocument.load(file, settings.getDocument().getOwnerPassword())) {

            PDPage page = document.getPage(settings.getPage().getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            Help4DevsEasyPdfTemplateBigBurger template = new Help4DevsEasyPdfTemplateBigBurger();
            template.bigBurgerTemplateBuilder(document, settings, contentStream);

            contentStream.close();
            document.save(settings.getDocument().getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

}
