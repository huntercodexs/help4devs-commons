package com.huntercodexs.demo.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontNameToPdfBox.fontName;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontSizeToPdfBox.fontSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ImageQualityToPdfBox.imageQuality;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ImageTypeToPdfBox.imageType;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ProtectionLevelToPdfBox.protectionLevel;

/**
 * This class use as "pdfbox 2.0.0" base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxService {

    private static void propertiesPDF(PDDocument document, PdfBoxDocumentSettings settings) {
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

    private static void initPDF(String filenamePath) {

        try (PDDocument documentCreator = new PDDocument()) {

            PDPage page = new PDPage();
            documentCreator.addPage(page);
            documentCreator.save(filenamePath);
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static void addPDF(PdfBoxDocumentSettings docSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument documentCreator = PDDocument.load(file, docSettings.getOwnerPassword())) {

            PDPage page = new PDPage();
            documentCreator.addPage(page);
            documentCreator.setAllSecurityToBeRemoved(true);
            documentCreator.save(docSettings.getFilenamePath());
            documentCreator.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private static PDPageContentStream contentStream(
            String option,
            PDPage page,
            PDDocument document,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            PDPageContentStream contentStream
    ) {
        try {

            if (contentStream == null) {
                contentStream = new PDPageContentStream(document, page);
            }

            switch (option) {

                case "new":
                    return contentStream;

                case "begin":
                    contentStream.beginText();
                    contentStream.setNonStrokingColor(color(pageSettings.getFontColor()));
                    contentStream.setLeading(pageSettings.getLineHeight());
                    contentStream.newLineAtOffset(pageSettings.getOffsetX(), pageSettings.getOffsetY());
                    contentStream.setFont(fontName(pageSettings.getFontName()), fontSize(pageSettings.getFontSize()));
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

    private static void createPDF(PdfBoxDocumentSettings docSettings, PdfBoxPageSettings pageSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            propertiesPDF(document, docSettings);

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDPageContentStream contentStream = contentStream(
                    "begin", page, document, pageSettings, null, null);

            String[] lines = pageSettings.getTextContent().replace("\r", "").split("\n");

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
    
    private static AccessPermission permissionPDF(boolean restrict) {
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

    private static void protectPDF(PdfBoxDocumentSettings docSettings) {
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

    private static void unprotectPDF(PdfBoxDocumentSettings docSettings) {

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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file quickly</p>
     *
     * @param docSettings  (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfCreate(PdfBoxDocumentSettings docSettings, PdfBoxPageSettings pageSettings) {
        String filenamePath = docSettings.getFilenamePath();

        if (filenamePath == null || filenamePath.isEmpty()) {
            filenamePath = UUID.randomUUID()+".pdf";
            docSettings.setFilenamePath(filenamePath);
        }

        if ((pageSettings.getPageNumber()-1) > 0) {
            addPDF(docSettings);
        } else {
            initPDF(filenamePath);
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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfAddImage(
            PdfBoxDocumentSettings docSettings,
            PdfBoxPageSettings pageSettings,
            PdfBoxImageSettings imgSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            PDPage page = document.getPage(pageSettings.getPageNumber()-1);

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber()-1);
            stripper.setEndPage((pageSettings.getPageNumber()-1)+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "begin", page, document, pageSettings, null, null);

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
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddBox</h6>
     *
     * <p style="color: #CDCDCD">Add an box (square, rectangle) to a PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @param rectSettings (PdfBoxRectangleSettings)
     * @param textSettings (PdfBoxTextSettings)
     * @param imgSettings (PdfBoxImageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfAddBox(
            PdfBoxDocumentSettings docSettings,
            PdfBoxPageSettings pageSettings,
            PdfBoxRectangleSettings rectSettings,
            PdfBoxTextSettings textSettings,
            PdfBoxImageSettings imgSettings
    ) {
        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

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
                    "begin", page, document, pageSettings, rectSettings, contentStream);

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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static String pdfReader(PdfBoxDocumentSettings docSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            if (docSettings.getStartPage() > docSettings.getEndPage()) {
                throw new RuntimeException("Failed: Page End should be greater than Page Start");
            }

            if (docSettings.getStartPage() > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page Start > numberOfPages");
            } else if (docSettings.getEndPage() > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page End > numberOfPages");
            }

            PDFTextStripper stripper = new PDFTextStripper();

            if (docSettings.getStartPage() == 0 && docSettings.getEndPage() == 0) {
                String content = stripper.getText(document);
                document.close();
                return content;
            }

            stripper.setStartPage(docSettings.getStartPage());
            stripper.setEndPage(docSettings.getEndPage());
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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfProtect(PdfBoxDocumentSettings docSettings) {
        protectPDF(docSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfUnprotect</h6>
     *
     * <p style="color: #CDCDCD">Unprotect PDF file</p>
     *
     * @param docSettings (PdfBoxDocumentSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfUnprotect(PdfBoxDocumentSettings docSettings) {
        unprotectPDF(docSettings);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfDetails</h6>
     *
     * <p style="color: #CDCDCD">Get details from one PDF file</p>
     *
     * @param filenamePath (String: Location where the PDF file is placed)
     * @return String (PDF Content)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     */
    public static PdfBoxDocumentDetails pdfDetails(String filenamePath) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfFromImage(PdfBoxDocumentSettings docSettings, PdfBoxPageSettings pageSettings) {

        initPDF(docSettings.getFilenamePath());

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

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
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfToImage(
            PdfBoxDocumentSettings docSettings,
            PdfBoxPageSettings pageSettings,
            PdfBoxImageSettings imageSettings
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
     * <h6 style="color: #FFFF00; font-size: 11px">pdfFromDoc</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file from doc</p>
     *
     * @param docPath (String: Doc path to PDF create)
     * @param filenamePath (String: Path filename to save file)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * @implNote For Windows + MS Office Word
     * */
    public static void pdfFromDoc(String docPath, String filenamePath) {
        /*TODO*/
    }

    @Getter
    public enum PageSizeToPdfBox {
        A0(PDRectangle.A0),
        A1(PDRectangle.A1),
        A2(PDRectangle.A2),
        A3(PDRectangle.A3),
        A4(PDRectangle.A4),
        A5(PDRectangle.A5),
        A6(PDRectangle.A6),
        LEGAL(PDRectangle.LEGAL),
        LETTER(PDRectangle.LETTER);

        private final PDRectangle pageSize;

        PageSizeToPdfBox(PDRectangle pageSize) {
            this.pageSize = pageSize;
        }

        public static PDRectangle pageSize(PageSizeToPdfBox pageSize) {
            return pageSize.getPageSize();
        }
    }

    @Getter
    public enum ColorsToPdfBox {
        WHITE(new Color(255, 255, 255)),
        RED(new Color(255, 0, 0)),
        GREEN(new Color(0, 255, 0)),
        GREEN2(new Color(26, 188, 156)),
        BLUE(new Color(0, 0, 255)),
        BLUE2(new Color(51,181,255)),
        BLUE3(new Color(78, 120, 149)),
        BLACK(new Color(0, 0, 0)),
        GRAY(new Color(128, 128, 128)),
        LIGHT_GRAY(new Color(192, 192, 192)),
        ICE(new Color(235, 235, 235)),
        PURPLE(new Color(173, 108, 227)),
        GOLD(new Color(255, 215, 0)),
        PINK(new Color(231, 6, 176)),
        YELLOW(new Color(255, 255, 0)),
        ORANGE(new Color(250, 123, 24)),
        CYAN(new Color(0, 255, 255)),
        MAGENTA(new Color(255,0,255));

        private final Color colorName;

        ColorsToPdfBox(Color colorName) {
            this.colorName = colorName;
        }

        public static Color color(ColorsToPdfBox colorName) {
            return colorName.getColorName();
        }
    }

    @Getter
    public enum FontNameToPdfBox {
        ZAP(PDType1Font.ZAPF_DINGBATS),
        SYMBOL(PDType1Font.SYMBOL),

        TIMES(PDType1Font.TIMES_ROMAN),
        TIMES_B(PDType1Font.TIMES_BOLD),
        TIMES_I(PDType1Font.TIMES_ITALIC),
        TIMES_BI(PDType1Font.TIMES_BOLD_ITALIC),

        COURIER(PDType1Font.COURIER),
        COURIER_B(PDType1Font.COURIER_BOLD),
        COURIER_I(PDType1Font.COURIER_OBLIQUE),
        COURIER_BI(PDType1Font.COURIER_BOLD_OBLIQUE),

        HELVETICA(PDType1Font.HELVETICA),
        HELVETICA_B(PDType1Font.HELVETICA_BOLD),
        HELVETICA_I(PDType1Font.HELVETICA_OBLIQUE),
        HELVETICA_BI(PDType1Font.HELVETICA_BOLD_OBLIQUE);

        private final PDType1Font fontName;

        FontNameToPdfBox(PDType1Font fontName) {
            this.fontName = fontName;
        }

        public static PDType1Font fontName(FontNameToPdfBox fontName) {
            return fontName.getFontName();
        }
    }

    @Getter
    public enum FontSizeToPdfBox {
        X_SMALL(5),
        SMALL(8),
        NORMAL(12),
        MEDIUM(16),
        LARGE(24),
        X_LARGE(45);

        private final int fontSize;

        FontSizeToPdfBox(int fontSize) {
            this.fontSize = fontSize;
        }

        public static int fontSize(FontSizeToPdfBox fontSize) {
            return fontSize.getFontSize();
        }
    }

    @Getter
    public enum ProtectionLevelToPdfBox {
        LOW(64),
        MIDDLE(128),
        HIGH(256);

        private final int protectionLevel;

        ProtectionLevelToPdfBox(int protectionLevel) {
            this.protectionLevel = protectionLevel;
        }

        public static int protectionLevel(ProtectionLevelToPdfBox protectionLevel) {
            return protectionLevel.getProtectionLevel();
        }
    }

    @Getter
    public enum ImageTypeToPdfBox {
        JPEG("JPEG"),
        JPG("JPEG"),
        PNG("PNG"),
        GIF("GIF"),
        TIFF("TIFF"),
        BMP("BMP");

        private final String imageType;

        ImageTypeToPdfBox(String imageType) {
            this.imageType = imageType;
        }

        public static String imageType(ImageTypeToPdfBox imageType) {
            return imageType.getImageType();
        }
    }

    @Getter
    public enum ImageQualityToPdfBox {
        LOW(50),
        NORMAL(120),
        GOOD(300),
        ULTRA(500),
        SUPER(800);

        private final int imageQuality;

        ImageQualityToPdfBox(int imageQuality) {
            this.imageQuality = imageQuality;
        }

        public static int imageQuality(ImageQualityToPdfBox imageQuality) {
            return imageQuality.getImageQuality();
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxDocumentDetails {
        int numberOfPages;
        boolean isProtected;
        boolean hasSignature;
        String date;
        String title;
        String author;
        String subject;
        String pageSize;
        String fontName;
        String fontSize;
        String keywords;
        String signature;
        String documentId;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxDocumentSettings {
        int startPage;
        int endPage;
        int numberOfPages;
        String date;
        String title;
        String author;
        String subject;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        String keywords;
        String userPassword;
        String ownerPassword;
        ProtectionLevelToPdfBox protectionLevel;
        String signature;
        String filenamePath;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxPageSettings {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int pageNumber;
        int margin;
        int padding;
        byte[] byteContent;
        PageSizeToPdfBox pageSize;
        FontNameToPdfBox fontName;
        FontSizeToPdfBox fontSize;
        ColorsToPdfBox fontColor;
        ColorsToPdfBox pageColor;
        String textContent;
        String imageFilepath;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxRectangleSettings {
        int width;
        int height;
        int offsetX;
        int offsetY;
        boolean border;
        boolean roundedBorder;
        ColorsToPdfBox backColor;
        ColorsToPdfBox borderColor;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxTextSettings {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int lineHeight;
        int letterSpace;
        boolean bold;
        boolean italic;
        boolean underline;
        String fontName;
        String fontSize;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxImageSettings {
        int width;
        int height;
        int offsetX;
        int offsetY;
        int maxWidth;
        int maxHeight;
        boolean border;
        boolean resize;
        String filenamePath;
        ImageTypeToPdfBox imageType;
        ImageQualityToPdfBox imageQuality;
    }

}