package com.huntercodexs.demo.services;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.itextpdf.io.font.FontNames;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This class use as "iText" base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPDFService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file quickly</p>
     *
     * @param data (String: Data to PDF create)
     * @param filenamePath (String: Path filename)
     * @param fontSize (String: Font Size [small, normal, large])
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCreate(String data, String filenamePath, String fontSize) throws FileNotFoundException {

        PdfWriter pdfWriter = new PdfWriter(filenamePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        int sizeFont = 0;

        switch (fontSize) {
            case "small":
                sizeFont = 8;
                break;
            case "normal":
                sizeFont = 11;
                break;
            case "large":
                sizeFont = 16;
                break;
        }

        document.setFontSize(sizeFont);
        document.add(new Paragraph(data));
        document.close();

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfFromImage</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file from image</p>
     *
     * @param imagePath (String: Image path to PDF create)
     * @param filenamePath (String: Path filename to save file)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfFromImage(String imagePath, String filenamePath) throws FileNotFoundException, MalformedURLException {

        PdfWriter pdfWriter = new PdfWriter(filenamePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        Image image = new Image(ImageDataFactory.create(imagePath));

        Document document = new Document(pdfDocument, new PageSize(image.getImageWidth(), image.getImageHeight()));
        document.setMargins(0,0,0,0);
        document.add(image);
        document.close();

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
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get(docPath)));
            String folderConverter = "C:\\\\IConverter";
            new File(folderConverter).mkdirs();

            IConverter converter = LocalConverter
                    .builder()
                    .baseFolder(new File(folderConverter))
                    .workerPool(20, 25, 2, TimeUnit.SECONDS)
                    .processTimeout(5, TimeUnit.SECONDS)
                    .build();

            Future<Boolean> conversion = converter
                    .convert(inputStream).as(DocumentType.MS_WORD)
                    .to(bo).as(DocumentType.PDF)
                    .prioritizeWith(1000)
                    .schedule();

            conversion.get();

            try (OutputStream outputStream = Files.newOutputStream(Paths.get(filenamePath))) {
                bo.writeTo(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

            inputStream.close();
            bo.close();

        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfReader</h6>
     *
     * <p style="color: #CDCDCD">Reader an specific PDF file</p>
     *
     * @param path (String: Location where the PDF file is placed)
     * @param page (int: Number of page)
     * @return String (PDF Content)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static String pdfReader(String path, int page) {

        try (PdfReader pdfReader = new PdfReader(path)) {

            int i = 1;
            StringBuilder pdfContent = new StringBuilder();
            PdfDocument pdfDocument = new PdfDocument(pdfReader);

            try {
                if (page > 0) {
                    pdfContent.append(new String(pdfDocument.getPage(page).getContentBytes()));
                } else {
                    while (pdfDocument.getPage(i) != null) {
                        pdfContent.append(new String(pdfDocument.getPage(i).getContentBytes()));
                        i += 1;
                    }
                }
            } catch (RuntimeException re) {
                System.out.println(re.getMessage());
            }

            pdfReader.close();

            return String.valueOf(pdfContent);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfDetails</h6>
     *
     * <p style="color: #CDCDCD">Get details from one PDF file</p>
     *
     * @param path (String: Location where the PDF file is placed)
     * @return String (PDF Content)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static PDFDetails pdfDetails(String path) {

        try (PdfReader pdfReader = new PdfReader(path)) {

            PDFDetails pdfDetails = new PDFDetails();
            PdfDocument pdfDocument = new PdfDocument(pdfReader);
            Rectangle pageSize = pdfDocument.getPage(1).getPageSize();

            pdfDetails.setId(pdfDocument.getDocumentId());
            pdfDetails.setNumberOfPages(pdfDocument.getNumberOfPages());
            pdfDetails.setWidth(pageSize.getWidth());
            pdfDetails.setHeight(pageSize.getHeight());
            pdfDetails.setVersion(pdfDocument.getPdfVersion());
            pdfDetails.setAuthor(pdfDocument.getDocumentInfo().getAuthor());
            pdfDetails.setCreator(pdfDocument.getDocumentInfo().getCreator());
            pdfDetails.setTitle(pdfDocument.getDocumentInfo().getTitle());
            pdfDetails.setSubject(pdfDocument.getDocumentInfo().getSubject());
            pdfDetails.setTrapped(pdfDocument.getDocumentInfo().getTrapped());
            pdfDetails.setKeywords(pdfDocument.getDocumentInfo().getKeywords());
            pdfDetails.setLabels(pdfDocument.getPageLabels());
            pdfDetails.setPageSize(pdfDocument.getDefaultPageSize());
            pdfDetails.setFontName(pdfDocument.getDefaultFont().getFontProgram().getFontNames());
            pdfDetails.setAssocFiles(pdfDocument.getAssociatedFiles());
            pdfDetails.setFirstPage(pdfDocument.getFirstPage());
            pdfDetails.setLastPage(pdfDocument.getLastPage());

            pdfReader.close();

            return pdfDetails;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCopy</h6>
     *
     * <p style="color: #CDCDCD">Make a copy from one PDF file to another</p>
     *
     * @param from (String: Location where the original/source PDF file is placed)
     * @param to (String: Location where the PDF copy should be put)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCopy(String from, String to) {

        try (PdfReader pdfReader = new PdfReader(from)) {

            PdfDocument pdfDocument = new PdfDocument(pdfReader);
            int pages = pdfDocument.getNumberOfPages();
            Rectangle pageSize = pdfDocument.getPage(1).getPageSize();
            float width = pageSize.getWidth();
            float height = pageSize.getHeight();

            PdfWriter pdfWriter = new PdfWriter(to);
            PdfDocument newPdfDocument = new PdfDocument(pdfWriter);
            Document newPdf = new Document(newPdfDocument, new PageSize(width, height));

            pdfDocument.copyPagesTo(1, pages, newPdf.getPdfDocument());

            newPdf.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfEncrypt</h6>
     *
     * <p style="color: #CDCDCD">Encrypt one PDF file to data protection</p>
     *
     * @param pdfData (String: Data to write in the PDF encrypted)
     * @param pdfPath (String: Path where the PDF file should be saved)
     * @param secretKey (String: key to use in the PDF encrypt)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfEncrypt(String pdfData, String pdfPath, String secretKey) {

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PDFDetails {
        long id;
        int numberOfPages;
        float width;
        float height;
        PdfVersion version;
        String author;
        String creator;
        String title;
        String subject;
        PdfName trapped;
        String keywords;
        String[] labels;
        PageSize pageSize;
        FontNames fontName;
        PdfArray assocFiles;
        PdfPage firstPage;
        PdfPage lastPage;
    }

}
