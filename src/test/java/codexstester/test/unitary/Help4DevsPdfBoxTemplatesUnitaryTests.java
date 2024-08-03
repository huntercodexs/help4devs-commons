package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsPdfBoxService.*;
import com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.PdfBoxTemplateSettings;
import com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.TemplatesToPdfBox;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.TemplatesToPdfBox.template;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxTemplateService.pdfBoxTemplate;


public class Help4DevsPdfBoxTemplatesUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathSource = "./src/test/resources/help4devs/files/txt/file.txt";
    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
    private final static String filepathTargetTemplate = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim.pdf";
    private final static String filepathTargetPassword = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-password.pdf";
    private final static String imagePathJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imagePathPromotion = "./src/test/resources/help4devs/images/ads/promotion.png";
    private final static String imagePathBackground = "./src/test/resources/help4devs/images/ads/pdfbox-background-sample-1.jpg";
    private final static String imagePathBackground2 = "./src/test/resources/help4devs/images/ads/pdfbox-background-sample-2.jpg";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private PdfBoxDocumentSettings documentSettings() {
        PdfBoxDocumentSettings settings = new PdfBoxDocumentSettings();
        settings.setStartPage(0);
        settings.setEndPage(2);
        settings.setNumberOfPages(3);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Test");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null);
        settings.setOwnerPassword(null);
        settings.setProtectionLevel(ProtectionLevelToPdfBox.HIGH);
        settings.setSignature(null);
        settings.setFilenamePath(filepathTarget);
        return settings;
    }

    private PdfBoxPageSettings pageSettings() {
        PdfBoxPageSettings settings = new PdfBoxPageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(750);
        settings.setLineHeight(18);
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(PageSizeToPdfBox.LETTER);
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.NORMAL);
        settings.setFontColor(ColorsToPdfBox.BLACK);
        settings.setPageColor(ColorsToPdfBox.WHITE);
        settings.setTextContent("data to write");
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxRectangleSettings rectangleSettings() {
        PdfBoxRectangleSettings settings = new PdfBoxRectangleSettings();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        return settings;
    }

    private PdfBoxTextSettings textSettings() {
        PdfBoxTextSettings settings = new PdfBoxTextSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setLetterSpace(1);
        settings.setBold(false);
        settings.setItalic(false);
        settings.setUnderline(false);
        settings.setFontName("courier");
        settings.setFontSize("x-small");
        return settings;
    }

    private PdfBoxImageSettings imageSettings() {
        PdfBoxImageSettings settings = new PdfBoxImageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setImageType(ImageTypeToPdfBox.JPEG);
        return settings;
    }

    private PdfBoxTemplateSettings templateSettings() {
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(TemplatesToPdfBox.SLIM);
        return settings;
    }

    private PdfBoxTableSettings tableSettings() {
        PdfBoxTableSettings settings = new PdfBoxTableSettings();
        settings.setWidth(100);
        settings.setHeight(100);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToPdfBox.ORANGE);
        settings.setCelColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        settings.setTableTemplate(TableTemplateToPdbBox.TABLE_5X6);
        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(TemplatesToPdfBox.SLIM);
        codexsTesterAssertExact("SLIM", template);

        template = template(TemplatesToPdfBox.BOX);
        codexsTesterAssertExact("BOX", template);

        template = template(TemplatesToPdfBox.BOX_OPEN);
        codexsTesterAssertExact("BOX_OPEN", template);

        template = template(TemplatesToPdfBox.SLIM_BOX);
        codexsTesterAssertExact("SLIM_BOX", template);

        template = template(TemplatesToPdfBox.SIMPLE_2);
        codexsTesterAssertExact("SIMPLE_2", template);

        template = template(TemplatesToPdfBox.SIMPLE_3);
        codexsTesterAssertExact("SIMPLE_3", template);
    }

    @Test
    public void pdfBoxTemplateTest() {
        PdfBoxDocumentSettings docSet = documentSettings();
        PdfBoxPageSettings pageSet = pageSettings();
        PdfBoxRectangleSettings rectSet = rectangleSettings();
        PdfBoxTextSettings textSet = textSettings();
        PdfBoxImageSettings imgSet = imageSettings();
        PdfBoxTemplateSettings templateSet = templateSettings();

        docSet.setFilenamePath(filepathTargetTemplate);

        imgSet.setFilenamePath(imagePathJava);

        templateSet.setTemplate(TemplatesToPdfBox.SLIM);
        templateSet.setImageBackground(imagePathBackground2);

        //rectSet.setBorderWidth(10);

        pdfBoxTemplate(docSet, pageSet, rectSet, textSet, imgSet, templateSet);

    }

}
