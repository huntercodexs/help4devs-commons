package com.huntercodexs.demo.services.pdfbox;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.huntercodexs.demo.services.barcode.Help4DevsBarcodeScannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.huntercodexs.demo.services.barcode.Help4DevsBarcodeScannerService.PdfBarcodeScannerResults;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontNameToPdfBox.fontName;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontSizeToPdfBox.fontSize;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ImageQualityToPdfBox.imageQuality;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ImageTypeToPdfBox.imageType;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.PageSizeToPdfBox.pageSize;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ProtectionLevelToPdfBox.protectionLevel;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxService extends Help4DevsPdfBoxElements {

    protected static void propertiesPDF(PDDocument document, PdfBoxDocument settings) {
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

    protected static void initPDF(String filenamePath, PdfBoxPage pageSettings) {

        try (PDDocument documentCreator = new PDDocument()) {

            PDPage page = new PDPage(pageSize(pageSettings.getPageSize()));
            documentCreator.addPage(page);
            documentCreator.save(filenamePath);
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static void addPDF(PdfBoxDocument docSettings, PdfBoxPage pageSettings) {

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

    protected static PDPageContentStream contentStream(
            String option,
            PDPage page,
            PDDocument document,
            PdfBoxPage pageSettings,
            PdfBoxContainer rectSettings,
            PDPageContentStream contentStream
    ) {
        try {

            if (contentStream == null) {
                contentStream = new PDPageContentStream(document, page);
            }

            switch (option) {

                case "new":
                    return contentStream;

                case "text":
                    contentStream.beginText();
                    contentStream.setNonStrokingColor(color(pageSettings.getFontColor()));
                    contentStream.setLeading(pageSettings.getLineHeight());
                    contentStream.newLineAtOffset(pageSettings.getOffsetX(), pageSettings.getOffsetY());
                    contentStream.setFont(fontName(pageSettings.getFontName()), fontSize(pageSettings.getFontSize()));
                    return contentStream;

                case "rec-empty":
                    contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.setNonStrokingColor(0,0,0);
                    return contentStream;

                case "rec-fill":
                    contentStream.setNonStrokingColor(color(rectSettings.getBackColor()));
                    contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.fill();
                    contentStream.setNonStrokingColor(0,0,0);
                    return contentStream;

                case "rec-border":
                    contentStream.setLineWidth(rectSettings.getBorderWidth());
                    contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
                    contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.closeAndStroke();
                    contentStream.setStrokingColor(0,0,0);
                    return contentStream;

            }

            return contentStream;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void createPDF(PdfBoxDocument docSettings, PdfBoxPage pageSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            propertiesPDF(document, docSettings);

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "text", page, document, pageSettings, null, null);

            String[] lines = new String[]{};

            if (pageSettings.getTextContent() != null) {
                lines = pageSettings.getTextContent().replace("\r", "").split("\n");
            }

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

    protected static void protectPDF(PdfBoxDocument docSettings) {
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
            throw new RuntimeException(e);
        }
    }

    protected static void unprotectPDF(PdfBoxDocument docSettings) {

        if (docSettings.getOwnerPassword() == null || docSettings.getOwnerPassword().isEmpty()) {
            throw new RuntimeException("Missing password for PDF Decrypt");
        }

        File file = new File(docSettings.getFilenamePath());
        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            document.setAllSecurityToBeRemoved(true);
            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void barcode128(
            PdfBoxBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            PdfBoxBarcode bcSettings = new PdfBoxBarcode();
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

            BufferedImage bImage = canvas.getBufferedImage();

            PDImageXObject image = JPEGFactory.createFromImage(document, bImage, 1, bcSettings.getDpi());
            contentStream.drawImage(image, bcSettings.getOffsetX(), bcSettings.getOffsetY(), bcSettings.getWidth(), bcSettings.getHeight());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void barcode39(
            PdfBoxBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            PdfBoxBarcode bcSettings = new PdfBoxBarcode();
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

            BufferedImage bImage = canvas.getBufferedImage();

            PDImageXObject image = JPEGFactory.createFromImage(document, bImage, 1, bcSettings.getDpi());
            contentStream.drawImage(image, bcSettings.getOffsetX(), bcSettings.getOffsetY(), bcSettings.getWidth(), bcSettings.getHeight());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void barcodePdf417(
            PdfBoxBarcode bcOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            PdfBoxBarcode bcSettings = new PdfBoxBarcode();
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

            BufferedImage bImage = canvas.getBufferedImage();

            PDImageXObject image = JPEGFactory.createFromImage(document, bImage, 1, bcSettings.getDpi());
            contentStream.drawImage(image, bcSettings.getOffsetX(), bcSettings.getOffsetY(), bcSettings.getWidth(), bcSettings.getHeight());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected static void qrCode(
            PdfBoxQrCode qrOverwrite,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            PdfBoxQrCode qrCodeSettings = new PdfBoxQrCode();
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
            BufferedImage bImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            PDImageXObject image = JPEGFactory.createFromImage(document, bImage, 1, qrCodeSettings.getDpi());

            contentStream.drawImage(
                    image, qrCodeSettings.getOffsetX(), qrCodeSettings.getOffsetY(), qrCodeSettings.size, qrCodeSettings.size);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file quickly</p>
     *
     * @param docSettings  (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfCreate(PdfBoxDocument docSettings, PdfBoxPage pageSettings) {
        String filenamePath = docSettings.getFilenamePath();

        if (filenamePath == null || filenamePath.isEmpty()) {
            filenamePath = UUID.randomUUID()+".pdf";
            docSettings.setFilenamePath(filenamePath);
        }

        if ((pageSettings.getPageNumber()-1) > 0) {
            addPDF(docSettings, pageSettings);
        } else {
            initPDF(filenamePath, pageSettings);
        }

        createPDF(docSettings, pageSettings);
        protectPDF(docSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddImage</h6>
     *
     * <p style="color: #CDCDCD">Add an image to a PDF file</p>
     *
     * @param docSettings  (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param imgSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfAddImage(
            PdfBoxDocument docSettings,
            PdfBoxPage pageSettings,
            PdfBoxImage imgSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber()-1);
            stripper.setEndPage((pageSettings.getPageNumber()-1)+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "text", page, document, pageSettings, null, null);

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            PDImageXObject pdImageXObject = PDImageXObject.createFromFile(pageSettings.getImageFilepath(), document);

            if (imgSettings.isResize()) {
                pdImageXObject.setWidth(imgSettings.getWidth());
                pdImageXObject.setHeight(imgSettings.getHeight());
            }

            contentStream.drawImage(pdImageXObject, imgSettings.getOffsetX(), imgSettings.getOffsetY());
            contentStream.close();

            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddContainer</h6>
     *
     * <p style="color: #CDCDCD">Add an box (square, rectangle) to a PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param rectSettings (PdfBoxRectangleSettings)
     * @param textSettings (PdfBoxTextSettings)
     * @param imgSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfAddContainer(
            PdfBoxDocument docSettings,
            PdfBoxPage pageSettings,
            PdfBoxContainer rectSettings,
            PdfBoxText textSettings,
            PdfBoxImage imgSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

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
                    "text", page, document, pageSettings, rectSettings, contentStream);

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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddBarcode</h6>
     *
     * <p style="color: #CDCDCD">Add an barcode (128, 39, pdf417) to a PDF file</p>
     *
     * @param docSettings (PdfBoxDocument)
     * @param pageSettings (PdfBoxPage)
     * @param barcodeSettings (PdfBoxBarcode)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfAddBarcode(
            PdfBoxDocument docSettings,
            PdfBoxPage pageSettings,
            PdfBoxBarcode barcodeSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber()-1);
            stripper.setEndPage((pageSettings.getPageNumber()-1)+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "text", page, document, pageSettings, null, null);

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            switch (barcodeSettings.getCodeType4Scanner().name()) {

                case "CODE128":
                    barcode128(barcodeSettings, document, contentStream);
                    break;
                case "CODE39":
                    barcode39(barcodeSettings, document, contentStream);
                    break;
                case "PDF417":
                    barcodePdf417(barcodeSettings, null, null);
                    break;
                default:
                    throw new RuntimeException("Invalid Barcode Type");
            }

            contentStream.close();

            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddQrCode</h6>
     *
     * <p style="color: #CDCDCD">Add an QR Code to a PDF file</p>
     *
     * @param docSettings (PdfBoxDocument)
     * @param pageSettings (PdfBoxPage)
     * @param qrCodeSettings (PdfBoxQrCode)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfAddQrCode(
            PdfBoxDocument docSettings,
            PdfBoxPage pageSettings,
            PdfBoxQrCode qrCodeSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber()-1);
            stripper.setEndPage((pageSettings.getPageNumber()-1)+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "text", page, document, pageSettings, null, null);

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            if (qrCodeSettings.getCodeType4Scanner().name().equals("QRCODE")) {
                qrCode(qrCodeSettings, document, contentStream);
            } else {
                throw new RuntimeException("Invalid Code Type: " + qrCodeSettings.getCodeType4Scanner().name());
            }

            contentStream.close();

            document.save(docSettings.getFilenamePath());
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfReader</h6>
     *
     * <p style="color: #CDCDCD">Reader an specific PDF file</p>
     * <p>
     * <br />It is possible to read a whole pdf document using 0 for pageStart and 0 for pageEnd
     * <br />Also it is possible to read a range of page using the initial page range and the final page range
     * <br />More one option is to read a single page using in the pageStart and pageEnd the same value
     * <br />Examples:
     * <br />- Read Whole document: (filenamePath, 0, 0)
     * <br />- Read range page of document: (filenamePath, 2, 6)
     * <br />- Read a single page of document: (filenamePath, 0, 1)
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @return String (PDF Content)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static String pdfReader(PdfBoxDocument docSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            if ((docSettings.getStartPage()-1) > docSettings.getEndPage()) {
                throw new RuntimeException("Failed: Page End should be greater than Page Start");
            }

            if ((docSettings.getStartPage()-1) > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page Start > numberOfPages");
            } else if (docSettings.getEndPage() > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page End > numberOfPages");
            }

            PDFTextStripper stripper = new PDFTextStripper();

            if ((docSettings.getStartPage()-1) == 0 && docSettings.getEndPage() == 0) {
                String content = stripper.getText(document);
                document.close();
                return content;
            }

            stripper.setStartPage(docSettings.getStartPage()-1);
            stripper.setEndPage(docSettings.getEndPage()-1);
            String content = stripper.getText(document);
            document.close();

            return content;

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfProtect</h6>
     *
     * <p style="color: #CDCDCD">Encrypt one PDF file to data protection</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfProtect(PdfBoxDocument docSettings) {
        protectPDF(docSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfUnprotect</h6>
     *
     * <p style="color: #CDCDCD">Unprotect PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfUnprotect(PdfBoxDocument docSettings) {
        unprotectPDF(docSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfDetails</h6>
     *
     * <p style="color: #CDCDCD">Get details from one PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @return String (PDF Content)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     */
    public static PdfBoxDocumentDetails pdfDetails(PdfBoxDocument docSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDDocumentInformation information = document.getDocumentInformation();

            PdfBoxDocumentDetails details = new PdfBoxDocumentDetails();
            details.setNumberOfPages(document.getNumberOfPages());
            details.setProtected(document.isEncrypted());
            details.setPageSize(String.valueOf(information.getPropertyStringValue("Paper size")));
            details.setTitle(information.getTitle());
            details.setFontName(null);
            details.setFontSize(null);
            details.setAuthor(information.getAuthor());
            details.setDate(String.valueOf(information.getCreationDate().getTime()));
            details.setSubject(information.getSubject());
            details.setKeywords(information.getKeywords());

            return details;

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfFromImage</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file from image</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfFromImage(PdfBoxDocument docSettings, PdfBoxPage pageSettings) {

        initPDF(docSettings.getFilenamePath(), pageSettings);

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDImageXObject image = PDImageXObject.createFromFile(pageSettings.getImageFilepath(), document);
            image.setHeight(image.getHeight());
            image.setWidth(image.getWidth());

            document.removePage(0);
            PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);
            content.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
            content.close();

            document.save(docSettings.getFilenamePath());
            document.close();

            protectPDF(docSettings);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfToImage</h6>
     *
     * <p style="color: #CDCDCD">Export a PDF file to image</p>
     *
     * To export whole PDF document to IMAGE file you can pass the value 0
     * in the argument pageSettings.pageNumber
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param imageSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfToImage(
            PdfBoxDocument docSettings,
            PdfBoxPage pageSettings,
            PdfBoxImage imageSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            if (imageSettings.getImageQuality() == null) {
                imageSettings.setImageQuality(ImageQualityToPdfBox.NORMAL);
            }

            if (imageSettings.getImageType() == null) {
                imageSettings.setImageType(ImageTypeToPdfBox.JPEG);
            }

            if (pageSettings.getPageNumber() <= 0) {
                pageSettings.setPageNumber(1);
            }

            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImageWithDPI(
                    pageSettings.getPageNumber()-1,
                    imageQuality(imageSettings.getImageQuality()),
                    ImageType.RGB);

            File fileImage = new File(imageSettings.getFilenamePath());
            ImageIO.write(image, imageType(imageSettings.getImageType()), fileImage);

            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfSplitter</h6>
     *
     * <p style="color: #CDCDCD">Split a PDF file into many files</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pathToSave (String)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfSplitter(PdfBoxDocument docSettings, String pathToSave) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);
            Iterator<PDDocument> iterator = pages.listIterator();

            String prefix = docSettings.getFilenamePath()
                    .replaceFirst("^\\./", "")
                    .split("\\.")[0];

            String[] arPrefix = prefix.split("/");
            String pdfPrefix = arPrefix[arPrefix.length-1];

            pathToSave = pathToSave.replaceFirst("/$", "")+"/"+pdfPrefix;

            int i = 0;
            while (iterator.hasNext()) {
                try (PDDocument pd = iterator.next()) {

                    i++;
                    String filename = pathToSave+"-"+i+".pdf";

                    pd.save(filename);
                    docSettings.setFilenamePath(filename);
                    protectPDF(docSettings);

                } catch (IOException iox) {
                    throw new RuntimeException(iox.getMessage());
                }
            }

            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfMerge</h6>
     *
     * <p style="color: #CDCDCD">Merge pdf files in a single PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pdfListToMerge (List<String>)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static void pdfMerger(PdfBoxDocument docSettings, List<String> pdfListToMerge) {

        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        pdfMerger.setDestinationFileName(docSettings.getFilenamePath());

        try {

            try (PDDocument finalPDF = new PDDocument()) {
                for (String pdfToMerge : pdfListToMerge) {
                    File pdfFile = new File(pdfToMerge);
                    try (PDDocument document = PDDocument.load(pdfFile, docSettings.getOwnerPassword())) {
                        pdfMerger.appendDocument(finalPDF, document);
                    }
                }

                finalPDF.save(docSettings.getFilenamePath());
                protectPDF(docSettings);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfFromDoc</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file from doc</p>
     *
     * @param docPath (String: Doc path to PDF create)
     * @param filenamePath (String: Path filename to save file)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * @implNote For Windows + MS Office Word
     * */
    public static void pdfFromDoc(String docPath, String filenamePath) {
        /*TODO*/
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfScanner</h6>
     *
     * <p style="color: #CDCDCD">Scanner a PDF file to get some barcode in it</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @return List (PdfBarcodeScannerResults)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public static List<PdfBarcodeScannerResults> pdfScanner(PdfBoxDocument docSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file, docSettings.getOwnerPassword())) {

            int numPages = document.getNumberOfPages();

            Help4DevsBarcodeScannerService pdfScanner = new Help4DevsBarcodeScannerService();

            for (int page = 0; page < numPages; page++) {
                PDPage pdPage = document.getPage(page);
                pdfScanner.scanner(pdPage, page, 20);
            }

            return pdfScanner.results();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAssign</h6>
     *
     * <p style="color: #CDCDCD">Assign a PDF file with a keystore, date and md5sum|sha256</p>
     *
     * @param docPath (String: Doc path to PDF create)
     * @param filenamePath (String: Path filename to save file)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * @implNote For Windows + MS Office Word
     * */
    public static void pdfAssign(String docPath, String filenamePath) {
        /*TODO*/
    }

}
