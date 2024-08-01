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
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ColorsAvailable.color;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontAvailable.fontName;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.FontSize.fontSize;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.ProtectionLevel.protectionLevel;

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

    private static void addPDF(String filenamePath) {

        File file = new File(filenamePath);

        try (PDDocument documentCreator = PDDocument.load(file)) {

            PDPage page = new PDPage();
            documentCreator.addPage(page);
            documentCreator.save(filenamePath);
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

            PDPage page = document.getPage(pageSettings.getPageNumber());

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

        try (PDDocument document = PDDocument.load(file)) {

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

        if (pageSettings.getPageNumber() > 0) {
            addPDF(filenamePath);
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
     * @param docSettings (PdfBoxDocumentSettings)
     * @param pageSettings (PdfBoxPageSettings)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static void pdfAddImage(PdfBoxDocumentSettings docSettings, PdfBoxPageSettings pageSettings) {

        File file = new File(docSettings.getFilenamePath());

        try (PDDocument document = PDDocument.load(file)) {

            PDPage page = document.getPage(pageSettings.getPageNumber());

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber());
            stripper.setEndPage(pageSettings.getPageNumber()+1);
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
            contentStream.drawImage(pdImageXObject, 25, 150);
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

            PDPage page = document.getPage(pageSettings.getPageNumber());

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageSettings.getPageNumber()+1);
            stripper.setEndPage(pageSettings.getPageNumber()+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = contentStream(
                    "new", page, document, pageSettings, rectSettings, null);

            contentStream = contentStream(
                    "rec-fill", page, document, pageSettings, rectSettings, contentStream);

            contentStream = contentStream(
                    "rec-border", page, document, pageSettings, rectSettings, contentStream);

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
    public static void pdfFromDoc(String docPath, String filenamePath)
            throws IOException, ExecutionException, InterruptedException
    {
        /*TODO*/
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

    @Getter
    public enum PageSizeAvailable {
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

        PageSizeAvailable(PDRectangle pageSize) {
            this.pageSize = pageSize;
        }

        public static PDRectangle pageSize(String pageSize) {
            if (PageSizeAvailable.valueOf(pageSize.toUpperCase()).getPageSize() != null) {
                return PageSizeAvailable.valueOf(pageSize.toUpperCase()).getPageSize();
            } else {
                throw new RuntimeException("Invalid Page Size: use [A0,A1,A2,A3,A4,A5,A6,LEGAL,LETTER]");
            }
        }
    }

    @Getter
    public enum ColorsAvailable {
        WHITE(Color.WHITE),
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        BLACK(Color.BLACK),
        GRAY(Color.GRAY),
        PINK(Color.PINK),
        YELLOW(Color.YELLOW),
        ORANGE(Color.ORANGE),
        CYAN(Color.CYAN),
        MAGENTA(Color.MAGENTA);

        private final Color colorName;

        ColorsAvailable(Color colorName) {
            this.colorName = colorName;
        }

        public static Color color(String colorName) {
            if (ColorsAvailable.valueOf(colorName.toUpperCase()).getColorName() != null) {
                return ColorsAvailable.valueOf(colorName.toUpperCase()).getColorName();
            } else {
                throw new RuntimeException("Invalid Color !");
            }
        }
    }

    @Getter
    public enum FontAvailable {
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

        FontAvailable(PDType1Font fontName) {
            this.fontName = fontName;
        }

        public static PDType1Font fontName(String colorName) {
            if (FontAvailable.valueOf(colorName.toUpperCase()).getFontName() != null) {
                return FontAvailable.valueOf(colorName.toUpperCase()).getFontName();
            } else {
                throw new RuntimeException("Invalid Font Name !");
            }
        }
    }

    @Getter
    public enum FontSize {
        X_SMALL(5),
        SMALL(8),
        NORMAL(12),
        MEDIUM(16),
        LARGE(24),
        X_LARGE(45);

        private final int fontSize;

        FontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public static int fontSize(String fontSize) {
            if (FontSize.valueOf(fontSize.toUpperCase()).getFontSize() > 0) {
                return FontSize.valueOf(fontSize.toUpperCase()).getFontSize();
            } else {
                throw new RuntimeException("Invalid Font Size !");
            }
        }
    }

    @Getter
    public enum ProtectionLevel {
        LOW(64),
        MIDDLE(128),
        HIGH(256);

        private final int protectionLevel;

        ProtectionLevel(int protectionLevel) {
            this.protectionLevel = protectionLevel;
        }

        public static int protectionLevel(String fontSize) {
            if (ProtectionLevel.valueOf(fontSize.toUpperCase()).getProtectionLevel() > 0) {
                return ProtectionLevel.valueOf(fontSize.toUpperCase()).getProtectionLevel();
            } else {
                throw new RuntimeException("Invalid Protection Level !");
            }
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
        String fontName;
        String fontSize;
        String keywords;
        String userPassword;
        String ownerPassword;
        String protectionLevel;
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
        String pageSize;
        String fontName;
        String fontSize;
        String fontColor;
        String pageColor;
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
        String backColor;
        String borderColor;
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
    }

}
