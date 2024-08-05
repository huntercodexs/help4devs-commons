package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.PdfBoxTemplateSettings;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.PdfBoxTemplates;
import com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.SlimTemplateSettings;
import org.junit.Test;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateBase.PdfBoxTemplates.template;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxTemplateService.*;

public class Help4DevsPdfBoxTemplateUnitaryTests extends Help4DevsBridgeTests {

    private final static String pdfFilename = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-template-slim.pdf";
    private final static String imgJava = "./src/test/resources/help4devs/images/ads/java.png";
    private final static String imgBackground = "./src/test/resources/help4devs/images/ads/pdfbox-background-sample-2.jpg";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private PdfBoxDocument documentSettings() {
        PdfBoxDocument settings = new PdfBoxDocument();
        settings.setStartPage(1);
        settings.setEndPage(1);
        settings.setNumberOfPages(1);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Document Test");
        settings.setFontName(FontNameToPdfBox.COURIER);
        settings.setFontSize(FontSizeToPdfBox.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setOwnerPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
        settings.setProtectionLevel(ProtectionLevelToPdfBox.HIGH);
        settings.setFilenamePath(pdfFilename);
        return settings;
    }

    private PdfBoxPage pageSettings() {
        PdfBoxPage settings = new PdfBoxPage();
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
        settings.setTextContent(null);
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private PdfBoxContainer containerSettings() {
        PdfBoxContainer settings = new PdfBoxContainer();
        settings.setWidth(570);
        settings.setHeight(780);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(ColorsToPdfBox.WHITE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        return settings;
    }

    private PdfBoxTable tableSettings() {
        PdfBoxTable settings = new PdfBoxTable();
        settings.setWidth(540);
        settings.setHeight(90);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setBorder(true);
        settings.setHeaderColor(ColorsToPdfBox.ORANGE);
        settings.setCelColor(ColorsToPdfBox.ICE);
        settings.setBorderColor(ColorsToPdfBox.BLACK);
        settings.setTableTemplate(TableDimensionsToPdfBox.TABLE_5X6);
        return settings;
    }

    private PdfBoxText textSettings() {
        PdfBoxText settings = new PdfBoxText();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setLineHeight(18);
        settings.setLetterSpace(1);
        settings.setBold(false);
        settings.setItalic(false);
        settings.setUnderline(false);
        settings.setFontName(FontNameToPdfBox.HELVETICA);
        settings.setFontSize(FontSizeToPdfBox.NORMAL);
        return settings;
    }

    private PdfBoxImage imageSettings() {
        PdfBoxImage settings = new PdfBoxImage();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setFilenamePath(imgJava);
        settings.setImageType(ImageTypeToPdfBox.JPEG);
        settings.setImageQuality(ImageQualityToPdfBox.GOOD);
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
        settings.setTableSize(TableDimensionsToPdfBox.TABLE_5X6);
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
        boolean textOn = true;
        String textContent1 =
                "Content One in the simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        String textContent2 =
                "Content Two more simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        String textContent3 =
                "Content Three is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        String textContent4 =
                "Content Four is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        String textContent5 =
                "Content Five is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
                "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
                "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
                "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
                "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make other";
        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});
        settings.setTextContentOne(textContent1);
        settings.setTextContentTwo(textContent2);
        settings.setTextContentThree(textContent3);
        settings.setTextContentFour(textContent4);
        settings.setTextContentFive(textContent5);
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

    private PdfBoxTemplateSettings pdfBoxTemplateSettings() {
        PdfBoxTemplateSettings settings = new PdfBoxTemplateSettings();
        settings.setTemplate(PdfBoxTemplates.SLIM);
        settings.setWidth(0);
        settings.setHeight(0);
        settings.setOffsetX(0);
        settings.setOffsetY(0);
        settings.setImageBackgroundEnable(false);
        settings.setImageBackground(imgBackground);
        settings.setDocument(documentSettings());
        settings.setPage(pageSettings());
        settings.setContainer(containerSettings());
        settings.setTable(tableSettings());
        settings.setText(textSettings());
        settings.setImage(imageSettings());
        settings.setSlim(slimSettings());
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
        pdfBoxTemplate(pdfBoxTemplateSettings());
    }

}
