package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;

import java.io.File;
import java.io.IOException;

import static com.huntercodexs.demo.services.barcode.Help4DevsBarcodeService.*;

public class Help4DevsBarcodeUnitaryTests extends Help4DevsBridgeTests {

    private final static String filepathTarget = "./src/test/resources/help4devs/files/pdf/my-easypdf-test-barcode.pdf";

    @Test
    public void barcodeTest() {

        try (PDDocument documentCreator = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.LETTER);
            documentCreator.addPage(page);
            documentCreator.save(filepathTarget);
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        File file = new File(filepathTarget);

        try (PDDocument document = PDDocument.load(file)) {

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            BarcodeSettings barcodeSettings = new BarcodeSettings();
            CodeQRSettings codeQRSettings = new CodeQRSettings();

            //Code128
            barcodeSettings.setDpi(400);
            barcodeSettings.setWidth(500);
            barcodeSettings.setHeight(50);
            barcodeSettings.setFontSize(2);
            barcodeSettings.setLineHeight(20);
            barcodeSettings.setOffsetX(55);
            barcodeSettings.setOffsetY(670);
            barcodeSettings.setFixQuiteZone(0);
            barcodeSettings.setDoQuiteZone(false);
            barcodeSettings.setData("123456789123456789123456789123456789");
            barcodeSettings.setFontName("courier");
            barcodeSettings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
            barcode128(barcodeSettings, document, contentStream);

            //Code39
            barcodeSettings.setDpi(400);
            barcodeSettings.setWidth(200);
            barcodeSettings.setHeight(40);
            barcodeSettings.setFontSize(4);
            barcodeSettings.setLineHeight(20);
            barcodeSettings.setOffsetX(200);
            barcodeSettings.setOffsetY(530);
            barcodeSettings.setFixQuiteZone(0);
            barcodeSettings.setDoQuiteZone(false);
            barcodeSettings.setData("123456789");
            barcodeSettings.setFontName("courier");
            barcodeSettings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
            barcode39(barcodeSettings, document, contentStream);

            //CodePdf417
            barcodeSettings.setDpi(400);
            barcodeSettings.setWidth(200);
            barcodeSettings.setHeight(40);
            barcodeSettings.setFontSize(4);
            barcodeSettings.setLineHeight(20);
            barcodeSettings.setOffsetX(200);
            barcodeSettings.setOffsetY(420);
            barcodeSettings.setFixQuiteZone(0);
            barcodeSettings.setDoQuiteZone(false);
            barcodeSettings.setData("1234567890");
            barcodeSettings.setFontName("courier");
            barcodeSettings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
            barcodePdf417(barcodeSettings, document, contentStream);

            //QRCode
            codeQRSettings.setDpi(400);
            codeQRSettings.setMargin(0);
            codeQRSettings.setMatrix(200);
            codeQRSettings.setSize(200);
            codeQRSettings.setOnColor(0xFF000001);
            codeQRSettings.setOffColor(0xFFFFFFFF);
            codeQRSettings.setOffsetX(200);
            codeQRSettings.setOffsetY(100);
            codeQRSettings.setData("https://huntercodexs.com");
            qrCode(codeQRSettings, document, contentStream);

            contentStream.close();
            document.save(filepathTarget);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
