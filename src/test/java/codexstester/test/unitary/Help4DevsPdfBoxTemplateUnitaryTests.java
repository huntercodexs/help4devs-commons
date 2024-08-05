package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxService.*;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.PdfBoxTemplateSettings;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.PdfBoxTemplates;
import org.junit.Test;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.PdfBoxTemplates.template;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.SlimTemplateSettings;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.pdfBoxTemplate;


public class Help4DevsPdfBoxTemplateUnitaryTests extends Help4DevsBridgeTests {

    private final static String pdfFilename = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/ads/pdfbox-background-sample-2.jpg";
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
        settings.setUserPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setOwnerPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setProtectionLevel(ProtectionLevelToPdfBox.HIGH);
        settings.setSignature(null);
        settings.setFilenamePath(pdfFilename);
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

    private PdfBoxContainerSettings containerSettings() {
        PdfBoxContainerSettings settings = new PdfBoxContainerSettings();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(ColorsToPdfBox.WHITE);
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
        settings.setFilenamePath(imgJava);
        return settings;
    }

    private PdfBoxTemplateSettings templateSettings() {
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
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
    
    private SlimTemplateSettings slimSettings() {
        SlimTemplateSettings settings = new SlimTemplateSettings();

        /*Title*/
        boolean titleOn = false;
        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});

        /*Table*/
        boolean tableOn = false;
        settings.setTableSize(TableTemplateToPdbBox.TABLE_5X6);
        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});

        /*Column*/
        boolean columnOn = false;
        settings.setColumnBoxEnable(new boolean[]{columnOn,columnOn,columnOn,columnOn,columnOn});
        settings.setColumnLeftBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnCenterBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnRightBorderEnable(new boolean[]{true,true,true,true,true});
        settings.setColumnLeftBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.BLACK,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.YELLOW,
                ColorsToPdfBox.BLUE,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnCenterBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.RED,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.PINK,
                ColorsToPdfBox.ORANGE
        });
        settings.setColumnRightBorderColor(new ColorsToPdfBox[]{
                ColorsToPdfBox.GOLD,
                ColorsToPdfBox.GREEN2,
                ColorsToPdfBox.RED2,
                ColorsToPdfBox.GRAY,
                ColorsToPdfBox.BLACK
        });
        settings.setColumnLeftBorderWidth(new int[] {1,2,3,4,5});
        settings.setColumnCenterBorderWidth(new int[] {5,4,3,2,1});
        settings.setColumnRightBorderWidth(new int[] {1,2,3,1,2});

        /*Signature Box*/
        settings.setLeftSignatureBoxEnable(false);
        settings.setCenterSignatureBoxEnable(false);
        settings.setRightSignatureBoxEnable(false);
        settings.setSignatureBoxColor(ColorsToPdfBox.GRAY);
        settings.setSignatureBoxBorderEnable(true);
        settings.setSignatureBoxAdjustOffsetX(0);

        /*Signature Tape*/
        settings.setSignatureTapeEnable(false);
        settings.setSignatureTapeColor(ColorsToPdfBox.GRAY);
        settings.setSignatureTapeAdjustOffsetX(10);

        /*Signature Details*/
        settings.setSignaturePersonName("John Smith Mountain");
        settings.setSignaturePersonDoc("123456789011");
        settings.setSignatureRecord("9089739827389");
        settings.setSignatureDateGmt("2020.01.01 10:00:00 -03:00");

        /*Text Content*/
        boolean textOn = false;
        String textContent =
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been of\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});
        settings.setTextContent(textContent);
        settings.setLineHeight(18);
        settings.setTextOffsetX(35);
        settings.setTextColor(ColorsToPdfBox.BLUE3);

        /*Image*/
        boolean imageOn = false;
        settings.setLeftImageEnable(new boolean[]{imageOn,imageOn,imageOn,imageOn,imageOn});
        settings.setCenterImageEnable(new boolean[]{imageOn,imageOn,imageOn,imageOn,imageOn});
        settings.setRightImageEnable(new boolean[]{imageOn,imageOn,imageOn,imageOn,imageOn});

        settings.setLeftImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setCenterImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });
        settings.setRightImagePaths(new String[]{
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png",
                "./src/test/resources/help4devs/images/ads/java.png"
        });

        return settings;
    }

    @Test
    public void templateTest() {
        String template = template(PdfBoxTemplates.FREE);
        codexsTesterAssertExact("FREE", template);

        template = template(PdfBoxTemplates.SLIM);
        codexsTesterAssertExact("SLIM", template);

        template = template(PdfBoxTemplates.BOX);
        codexsTesterAssertExact("BOX", template);

        template = template(PdfBoxTemplates.BOX_OPEN);
        codexsTesterAssertExact("BOX_OPEN", template);

        template = template(PdfBoxTemplates.SLIM_BOX);
        codexsTesterAssertExact("SLIM_BOX", template);

        template = template(PdfBoxTemplates.SIMPLE_2);
        codexsTesterAssertExact("SIMPLE_2", template);

        template = template(PdfBoxTemplates.SIMPLE_3);
        codexsTesterAssertExact("SIMPLE_3", template);
    }

    @Test
    public void pdfBoxTemplateSlimTest() {
        PdfBoxTemplateSettings templateSettings = templateSettings();
        templateSettings.setTemplate(PdfBoxTemplates.SLIM);
        templateSettings.setWidth(0);
        templateSettings.setHeight(0);
        templateSettings.setOffsetX(0);
        templateSettings.setOffsetY(0);
        templateSettings.setImageBackground(imgBackground);
        templateSettings.setDocument(documentSettings());
        templateSettings.setPage(pageSettings());
        templateSettings.setContainer(containerSettings());
        templateSettings.setText(textSettings());
        templateSettings.setImage(imageSettings());
        templateSettings.setTable(tableSettings());
        templateSettings.setSlim(slimSettings());
        pdfBoxTemplate(templateSettings);
    }

}
