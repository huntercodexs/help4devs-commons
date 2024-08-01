package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsPdfBoxService;
import org.junit.Test;

import java.io.IOException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.pdfAddBox;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.pdfCreate;

public class Help4DevsPdfBoxTemplatesUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathSource = "./src/test/resources/help4devs/files/txt/file.txt";
    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test.pdf";
    private final static String filepathTargetPassword = "./src/test/resources/help4devs/files/pdf/my-pdfbox-test-password.pdf";
    private final static String imagePath = "./src/test/resources/help4devs/images/ads/file.png";
    private final static String imagePathAds = "./src/test/resources/help4devs/images/ads/img.png";
    private final static String userPassword = "123456";
    private final static String ownerPassword = "password";

    private Help4DevsPdfBoxService.PdfBoxDocumentSettings documentSettings() {
        Help4DevsPdfBoxService.PdfBoxDocumentSettings settings = new Help4DevsPdfBoxService.PdfBoxDocumentSettings();
        settings.setStartPage(0);
        settings.setEndPage(2);
        settings.setNumberOfPages(3);
        settings.setDate("1990-01-01 10:00:00");
        settings.setTitle("Title Test");
        settings.setAuthor("Huntercodexs");
        settings.setSubject("Test");
        settings.setFontName(Help4DevsPdfBoxService.FontNameToPdfBox.COURIER);
        settings.setFontSize(Help4DevsPdfBoxService.FontSizeToPdfBox.X_SMALL);
        settings.setKeywords("test, pdf, java, huntercodexs");
        settings.setUserPassword(null);
        settings.setOwnerPassword(null);
        settings.setProtectionLevel(Help4DevsPdfBoxService.ProtectionLevelToPdfBox.HIGH);
        settings.setSignature(null);
        settings.setFilenamePath(filepathTarget);
        return settings;
    }

    private Help4DevsPdfBoxService.PdfBoxPageSettings pageSettings() {
        Help4DevsPdfBoxService.PdfBoxPageSettings settings = new Help4DevsPdfBoxService.PdfBoxPageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(750);
        settings.setLineHeight(18);
        settings.setPageNumber(1);
        settings.setMargin(0);
        settings.setPadding(0);
        settings.setPageSize(Help4DevsPdfBoxService.PageSizeToPdfBox.A4);
        settings.setFontName(Help4DevsPdfBoxService.FontNameToPdfBox.COURIER);
        settings.setFontSize(Help4DevsPdfBoxService.FontSizeToPdfBox.X_SMALL);
        settings.setFontColor(Help4DevsPdfBoxService.ColorsToPdfBox.BLACK);
        settings.setPageColor(Help4DevsPdfBoxService.ColorsToPdfBox.WHITE);
        settings.setTextContent("data to write");
        settings.setImageFilepath(null);
        settings.setByteContent(null);
        return settings;
    }

    private Help4DevsPdfBoxService.PdfBoxRectangleSettings rectangleSettings() {
        Help4DevsPdfBoxService.PdfBoxRectangleSettings settings = new Help4DevsPdfBoxService.PdfBoxRectangleSettings();
        settings.setWidth(570);
        settings.setHeight(750);
        settings.setOffsetX(20);
        settings.setOffsetY(20);
        settings.setBorder(true);
        settings.setRoundedBorder(false);
        settings.setBackColor(Help4DevsPdfBoxService.ColorsToPdfBox.BLUE3);
        settings.setBorderColor(Help4DevsPdfBoxService.ColorsToPdfBox.BLACK);
        return settings;
    }

    private Help4DevsPdfBoxService.PdfBoxTextSettings textSettings() {
        Help4DevsPdfBoxService.PdfBoxTextSettings settings = new Help4DevsPdfBoxService.PdfBoxTextSettings();
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

    private Help4DevsPdfBoxService.PdfBoxImageSettings imageSettings() {
        Help4DevsPdfBoxService.PdfBoxImageSettings settings = new Help4DevsPdfBoxService.PdfBoxImageSettings();
        settings.setWidth(500);
        settings.setHeight(780);
        settings.setOffsetX(25);
        settings.setOffsetY(100);
        settings.setMaxWidth(500);
        settings.setMaxHeight(780);
        settings.setBorder(false);
        settings.setResize(false);
        settings.setImageType(Help4DevsPdfBoxService.ImageTypeToPdfBox.JPEG);
        return settings;
    }

    @Test
    public void pdfCreateUsingPasswordTest() throws IOException {
        String data = binFile(filepathSource);

        Help4DevsPdfBoxService.PdfBoxDocumentSettings docSet = documentSettings();
        Help4DevsPdfBoxService.PdfBoxPageSettings pageSet = pageSettings();

        docSet.setFilenamePath(filepathTargetPassword);
        docSet.setNumberOfPages(3);
        docSet.setUserPassword(userPassword);
        docSet.setOwnerPassword(ownerPassword);

        for (int i = 1; i <= docSet.getNumberOfPages(); i++) {
            pageSet.setTextContent("PAGE-" + i + ":\n" + data);
            pageSet.setPageNumber(i);
            pdfCreate(docSet, pageSet);
        }
    }

    @Test
    public void pdfAddBoxTest() {
        Help4DevsPdfBoxService.PdfBoxDocumentSettings docSet = documentSettings();
        Help4DevsPdfBoxService.PdfBoxPageSettings pageSet = pageSettings();
        Help4DevsPdfBoxService.PdfBoxRectangleSettings rectSet = rectangleSettings();
        Help4DevsPdfBoxService.PdfBoxTextSettings textSet = textSettings();
        Help4DevsPdfBoxService.PdfBoxImageSettings imgSet = imageSettings();

        pageSet.setPageNumber(2);
        rectSet.setBackColor(Help4DevsPdfBoxService.ColorsToPdfBox.ICE);
        rectSet.setBorderColor(Help4DevsPdfBoxService.ColorsToPdfBox.BLACK);

        pdfAddBox(docSet, pageSet, rectSet, textSet, imgSet);
    }

}
