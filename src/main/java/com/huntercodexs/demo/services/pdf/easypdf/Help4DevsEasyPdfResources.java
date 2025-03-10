package com.huntercodexs.demo.services.pdf.easypdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.PageSizeToEasyPdf.pageSize;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.ProtectionLevelToEasyPdf.protectionLevel;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
public abstract class Help4DevsEasyPdfResources extends Help4DevsEasyPdfCore {

    protected static void propertiesPDF(PDDocument document, EasyPdfDocument settings) {
        PDDocumentInformation information = document.getDocumentInformation();
        information.setAuthor(settings.getAuthor());
        information.setTitle(settings.getTitle());
        information.setCreator(settings.getAuthor());
        information.setSubject(settings.getSubject());
        information.setKeywords(settings.getKeywords());
        information.setProducer(settings.getAuthor());

        Calendar cal = Calendar.getInstance();
        Locale localBrazil = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localBrazil);

        try {
            cal.setTime(sdf.parse(settings.getDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        information.setCreationDate(cal);
        information.setModificationDate(cal);
    }

    protected static void initPDF(String filenamePath, EasyPdfPage pageSettings) {

        try (PDDocument documentCreator = new PDDocument()) {

            PDPage page = new PDPage(pageSize(pageSettings.getPageSize()));
            documentCreator.addPage(page);
            documentCreator.save(filenamePath);
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void addPDF(EasyPdfDocument docSettings, EasyPdfPage pageSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument documentCreator = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDPage page = new PDPage(pageSize(pageSettings.getPageSize()));
            documentCreator.addPage(page);
            documentCreator.setAllSecurityToBeRemoved(true);
            documentCreator.save(docSettings.getFilenamePath());
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void createPDF(EasyPdfDocument docSettings, EasyPdfPage pageSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            propertiesPDF(document, docSettings);

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDPageContentStream contentStream = contentStreamInit(page, document, null);

            String[] lines = new String[]{};

            if (pageSettings.getTextContent() != null) {
                lines = pageSettings.getTextContent().replace("\r", "").split("\n");
            }

            contentStreamText(lines, pageSettings, contentStream);

            contentStream.close();
            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }
    
    protected static AccessPermission permissionPDF(boolean restrict) {
        AccessPermission accessPermission = new AccessPermission();
        
        if (restrict) {
            accessPermission.setReadOnly();
        }

        accessPermission.setCanModify(!restrict);
        accessPermission.setCanPrint(!restrict);
        accessPermission.setCanExtractContent(!restrict);
        accessPermission.setCanPrintDegraded(!restrict);

        return accessPermission;
    }

    protected static void protectPDF(EasyPdfDocument docSettings) {
        if (docSettings.getUserPassword() == null || docSettings.getUserPassword().isEmpty()) return;
        if (docSettings.getOwnerPassword() == null || docSettings.getOwnerPassword().isEmpty()) return;

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            AccessPermission accessPermission = permissionPDF(true);

            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(
                    docSettings.getOwnerPassword(),
                    docSettings.getUserPassword(),
                    accessPermission);

            protectionPolicy.setEncryptionKeyLength(protectionLevel(docSettings.getProtectionLevel()));
            protectionPolicy.setPermissions(accessPermission);

            document.protect(protectionPolicy);
            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static void unprotectPDF(EasyPdfDocument docSettings) {

        if (docSettings.getOwnerPassword() == null || docSettings.getOwnerPassword().isEmpty()) {
            throw new RuntimeException("Missing password for PDF Decrypt");
        }

        File file = new File(docSettings.getFilenamePath());
        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            document.setAllSecurityToBeRemoved(true);
            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static void barcode128(
            EasyPdfBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            EasyPdfBarcode bcSettings = new EasyPdfBarcode();
            if (bcOverwrite != null) {
                bcSettings = bcOverwrite;
            }

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    bcSettings.getDpi(), BufferedImage.TYPE_BYTE_BINARY, false, bcSettings.getOrientation());

            Code128Bean code128Bean = new Code128Bean();
            code128Bean.setFontSize(bcSettings.getFontSize());
            code128Bean.setHeight(bcSettings.getLineHeight());
            code128Bean.setVerticalQuietZone(0);
            code128Bean.setFontName(String.valueOf(bcSettings.getFontName()).toLowerCase());
            code128Bean.doQuietZone(bcSettings.isDoQuiteZone());
            code128Bean.setMsgPosition(bcSettings.getTextPosition());
            code128Bean.generateBarcode(canvas, bcSettings.getData());
            canvas.finish();

            contentStreamBufferedImage(
                    bcSettings.getDpi(),
                    bcSettings.getWidth(),
                    bcSettings.getHeight(),
                    bcSettings.getOffsetX(),
                    bcSettings.getOffsetY(),
                    canvas.getBufferedImage(),
                    document,
                    contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void barcode39(
            EasyPdfBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            EasyPdfBarcode bcSettings = new EasyPdfBarcode();
            if (bcOverwrite != null) {
                bcSettings = bcOverwrite;
            }

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    bcSettings.getDpi(), BufferedImage.TYPE_BYTE_BINARY, false, bcSettings.getOrientation());

            Code39Bean code39Bean = new Code39Bean();
            code39Bean.setFontName(String.valueOf(bcSettings.getFontName()).toLowerCase());
            code39Bean.setFontSize(bcSettings.getFontSize());
            code39Bean.setQuietZone(bcSettings.getFixQuiteZone());
            code39Bean.setMsgPosition(bcSettings.getTextPosition());
            code39Bean.generateBarcode(canvas, bcSettings.getData());
            canvas.finish();

            contentStreamBufferedImage(
                    bcSettings.getDpi(),
                    bcSettings.getWidth(),
                    bcSettings.getHeight(),
                    bcSettings.getOffsetX(),
                    bcSettings.getOffsetY(),
                    canvas.getBufferedImage(),
                    document,
                    contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void barcodePdf417(
            EasyPdfBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            EasyPdfBarcode bcSettings = new EasyPdfBarcode();
            if (bcOverwrite != null) {
                bcSettings = bcOverwrite;
            }

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    bcSettings.getDpi(), BufferedImage.TYPE_BYTE_BINARY, false, bcSettings.getOrientation());

            PDF417Bean pdf417Bean = new PDF417Bean();
            pdf417Bean.setFontName(String.valueOf(bcSettings.getFontName()).toLowerCase());
            pdf417Bean.setFontSize(bcSettings.getFontSize());
            pdf417Bean.setQuietZone(bcSettings.getFixQuiteZone());
            pdf417Bean.setMsgPosition(bcSettings.getTextPosition());
            pdf417Bean.generateBarcode(canvas, bcSettings.getData());
            canvas.finish();

            contentStreamBufferedImage(
                    bcSettings.getDpi(),
                    bcSettings.getWidth(),
                    bcSettings.getHeight(),
                    bcSettings.getOffsetX(),
                    bcSettings.getOffsetY(),
                    canvas.getBufferedImage(),
                    document,
                    contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void qrCode(
            EasyPdfQrCode qrOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            EasyPdfQrCode qrCodeSettings = new EasyPdfQrCode();
            if (qrOverwrite != null) {
                qrCodeSettings = qrOverwrite;
            }

            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.MARGIN, qrCodeSettings.getMargin());
            hintMap.put(EncodeHintType.QR_COMPACT, true);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    new String(qrCodeSettings.getData().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    BarcodeFormat.QR_CODE,
                    qrCodeSettings.matrix,
                    qrCodeSettings.matrix,
                    hintMap);

            MatrixToImageConfig config = new MatrixToImageConfig(qrCodeSettings.getOnColor(), qrCodeSettings.getOffColor());

            contentStreamBufferedImage(
                    qrCodeSettings.getDpi(),
                    qrCodeSettings.size,
                    qrCodeSettings.size,
                    qrCodeSettings.getOffsetX(),
                    qrCodeSettings.getOffsetY(),
                    MatrixToImageWriter.toBufferedImage(bitMatrix, config),
                    document,
                    contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void barcodeForm(
            PDDocument document,
            EasyPdfPage pageSettings,
            EasyPdfBarcodeForm bcFormSettings,
            PDPageContentStream contentStream
    ) {
        Help4DevsEasyPdfBarcodeFormExtension barcodeFormExtension = new Help4DevsEasyPdfBarcodeFormExtension();

        barcodeFormExtension.barcodeFormBuild(
                document,
                pageSettings,
                bcFormSettings,
                contentStream);
    }

    protected static void formPDF() {

    }

}
