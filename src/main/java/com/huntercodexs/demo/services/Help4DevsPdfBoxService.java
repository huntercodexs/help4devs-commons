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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * This class use as "pdfbox 2.0.0" base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxService {

    private static int fontSize(String ref) {
        switch (ref) {
            case "x-small":
                return 5;

            case "small":
                return 8;

            case "medium":
                return 16;

            case "large":
                return 24;

            case "x-large":
                return 45;

            default:
                /*normal*/
                return 12;
        }
    }

    private static PDType1Font fontName(String ref) {
        switch (ref) {
            case "times":
                return PDType1Font.TIMES_ROMAN;

            case "courier":
                return PDType1Font.COURIER;

            case "symbol":
                return PDType1Font.SYMBOL;

            default:
                return PDType1Font.HELVETICA;
        }
    }

    private static void propertiesPDF(PDDocument document, PdfBoxSettings settings) {
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

    private static void createPDF(String data, String filenamePath, PdfBoxSettings settings) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            propertiesPDF(document, settings);

            PDPage page = document.getPage(settings.getPageNumber());
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setLeading(settings.getLineHeight());
            contentStream.newLineAtOffset(settings.getOffsetX(), settings.getOffsetY());
            contentStream.setFont(fontName(settings.getFontName()), fontSize(settings.getFontSize()));

            String[] lines = data.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save(filenamePath);
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    private static void protectPDF(String filenamePath, String password) {
        if (password == null || password.isEmpty()) return;

        File file = new File(filenamePath);
        try (PDDocument document = PDDocument.load(file)) {

            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setReadOnly();
            accessPermission.setCanModify(false);
            accessPermission.setCanPrint(false);
            accessPermission.setCanExtractContent(false);
            accessPermission.setCanPrintDegraded(false);

            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(
                    password,
                    password,
                    accessPermission);

            protectionPolicy.setEncryptionKeyLength(256);
            protectionPolicy.setPermissions(accessPermission);

            document.protect(protectionPolicy);
            document.save(filenamePath);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void unprotectPDF(String filenamePath, String password) {
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Missing password for PDF Decrypt");
        }

        File file = new File(filenamePath);
        try (PDDocument document = PDDocument.load(file, password)) {

            document.setAllSecurityToBeRemoved(true);
            document.save(filenamePath);
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
     * @param data (String: Data to PDF create)
     * @param filenamePath (String: Path filename)
     * @param settings (Object: PdfBoxSettings)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCreate(String data, String filenamePath, PdfBoxSettings settings) {
        if (filenamePath == null || filenamePath.isEmpty()) {
            filenamePath = UUID.randomUUID()+".pdf";
        }

        if (settings.getPageNumber() > 0) {
            addPDF(filenamePath);
        } else {
            initPDF(filenamePath);
        }

        createPDF(data, filenamePath, settings);
        protectPDF(filenamePath, settings.getPassword());
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfAddImage</h6>
     *
     * <p style="color: #CDCDCD">Add an image to a PDF file</p>
     *
     * @param imagePath (String: Image path to add in the pdf file)
     * @param filenamePath (String: Path filename to add image)
     * @param settings (PdfBoxSettings)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfAddImage(String imagePath, String filenamePath, PdfBoxSettings settings) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            PDPage pdPage = document.getPage(settings.getPageNumber());

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(settings.getPageNumber());
            stripper.setEndPage(settings.getPageNumber()+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);
            contentStream.beginText();
            contentStream.setLeading(settings.getLineHeight());
            contentStream.newLineAtOffset(settings.getOffsetX(), settings.getOffsetY());
            contentStream.setFont(fontName(settings.getFontName()), fontSize(settings.getFontSize()));

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();

            PDImageXObject pdImageXObject = PDImageXObject.createFromFile(imagePath, document);
            contentStream.drawImage(pdImageXObject, 25, 150);
            contentStream.close();

            document.save(filenamePath);
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
     * @param filenamePath (String: Path filename to add image)
     * @param settings (PdfBoxSettings)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfAddBox(String filenamePath, PdfBoxSettings settings) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            PDPage pdPage = document.getPage(settings.getPageNumber());

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(settings.getPageNumber()+1);
            stripper.setEndPage(settings.getPageNumber()+1);
            String content = stripper.getText(document);

            PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);

            contentStream.setNonStrokingColor(235,235,235);
            contentStream.addRect(
                    settings.getOffsetX(),
                    settings.getOffsetY(),
                    settings.getWidth(),
                    settings.getHeight());
            contentStream.fill();
            contentStream.setNonStrokingColor(0,0,0);

            contentStream.beginText();
            contentStream.setLeading(settings.getLineHeight());
            contentStream.newLineAtOffset(40, 730);
            contentStream.setFont(fontName(settings.getFontName()), fontSize(settings.getFontSize()));

            String[] lines = content.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save(filenamePath);
            document.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfReader</h6>
     *
     * <p style="color: #CDCDCD">Reader an specific PDF file</p>
     *
     * <br />It is possible to read a whole pdf document using 0 for pageStart and 0 for pageEnd
     * <br />Also it is possible to read a range of page using the initial page range and the final page range
     * <br />More one option is to read a single page using in the pageStart and pageEnd the same value
     * <br />Examples:
     * <br />- Read Whole document: (filenamePath, 0, 0)
     * <br />- Read range page of document: (filenamePath, 2, 6)
     * <br />- Read a single page of document: (filenamePath, 0, 1)
     *
     * @param filenamePath (String: Location where the PDF file is placed)
     * @param pageStart (int: Number of page to start)
     * @param pageEnd (int: Number of page to finish)
     * @return String (PDF Content)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static String pdfReader(String filenamePath, int pageStart, int pageEnd) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            if (pageStart > pageEnd) {
                throw new RuntimeException("Failed: Page End should be greater than Page Start");
            }

            if (pageStart > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page Start > numberOfPages");
            } else if (pageEnd > document.getNumberOfPages()) {
                throw new RuntimeException("Failed: Page End > numberOfPages");
            }

            PDFTextStripper stripper = new PDFTextStripper();

            if (pageStart == 0 && pageEnd == 0) {
                String content = stripper.getText(document);
                document.close();
                return content;
            }

            stripper.setStartPage(pageStart);
            stripper.setEndPage(pageEnd);
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
     * @param pdfPath (String: Path where the PDF file should be saved)
     * @param password (String: key to use in the PDF encrypt)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfProtect(String pdfPath, String password) throws IOException {
        protectPDF(pdfPath, password);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfUnprotect</h6>
     *
     * <p style="color: #CDCDCD">Unprotect PDF file</p>
     *
     * @param pdfSource (String: Path refer to PDF encrypted)
     * @param currentPassword (String: key to use in the PDF decrypt)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfUnprotect(String pdfSource, String currentPassword) throws IOException {
        unprotectPDF(pdfSource, currentPassword);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfFromImage</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file from image</p>
     *
     * @param imagePath (String: Image path to PDF create)
     * @param filenamePath (String: Path filename to save file)
     * @param password (String: Password to protect file)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfFromImage(String imagePath, String filenamePath, String password) {

        initPDF(filenamePath);

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
            image.setHeight(image.getHeight());
            image.setWidth(image.getWidth());

            document.removePage(0);
            PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);
            content.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
            content.close();

            document.save(filenamePath);
            document.close();

            protectPDF(filenamePath, password);

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
    public static PdfBoxDetails pdfDetails(String filenamePath) {

        File file = new File(filenamePath);

        try (PDDocument document = PDDocument.load(file)) {

            PDDocumentInformation information = document.getDocumentInformation();

            PdfBoxDetails details = new PdfBoxDetails();
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
    @NoArgsConstructor
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

        private PDRectangle letter;

        PageSizeAvailable(PDRectangle letter) {
            this.letter = letter;
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxDetails {
        int numberOfPages;
        boolean isProtected;
        String pageSize;
        String title;
        String fontName;
        String fontSize;
        String author;
        String date;
        String subject;
        String keywords;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxSettings {
        int lineHeight;
        int pageNumber;
        int offsetX;
        int offsetY;
        int width;
        int height;
        String title;
        String fontName;
        String fontSize;
        String author;
        String date;
        String subject;
        String keywords;
        String password;
    }

}
