package com.huntercodexs.demo.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * This class use as "pdfbox 2.0.0" base process to PDF files handler
 * */
@Slf4j
@Service
public class Help4DevsPdfBoxService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file quickly</p>
     *
     * @param data (String: Data to PDF create)
     * @param filenamePath (String: Path filename)
     * @param fontSize (String: Font Size [small, normal, large])
     * @param password (String: Password to protect file)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCreate(String data, String filenamePath, String fontSize, String password)
            throws FileNotFoundException
    {
        /*Randomly Filename*/
        String pdfFilename = UUID.randomUUID()+".pdf";

        /*PDF Create*/
        try (PDDocument documentCreator = new PDDocument()) {

            PDPage page = new PDPage();
            documentCreator.addPage(page);
            documentCreator.save(pdfFilename);
            documentCreator.close();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        File file = new File(pdfFilename);

        /*Loader the PDF Created*/
        try (PDDocument documentWriter = PDDocument.load(file)) {

            /*Get the First Page*/
            PDPage page = documentWriter.getPage(0);

            /*Setting PDF Properties*/
            PDPageContentStream contentStream = new PDPageContentStream(documentWriter, page);

            /*Beginning Text*/
            contentStream.beginText();

            /*Line Height*/
            contentStream.setLeading(10);

            /*Initial Position*/
            contentStream.newLineAtOffset(25, 750);

            /*Font Definition*/
            contentStream.setFont(PDType1Font.COURIER, 5);

            String[] lines = data.replace("\r", "").split("\n");

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            /*contentStream.showText(data.replace("\n", "").replace("\r", ""));
            contentStream.newLine();

            contentStream.showText(data.replace("\n", "").replace("\r", ""));
            contentStream.newLine();

            contentStream.showText(data.replace("\n", "").replace("\r", ""));
            contentStream.newLine();*/

            /*Finishing Text*/
            contentStream.endText();

            /*Finishing Stream*/
            contentStream.close();

            /*Save PDF File*/
            documentWriter.save(pdfFilename);
            documentWriter.close();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        System.out.println(pdfFilename + " generated successfully");
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
    public static void pdfFromImage(String imagePath, String filenamePath, String password)
            throws FileNotFoundException, MalformedURLException
    {

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

        return null;

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
    public static PdfBoxDetails pdfDetails(String path) {

        return null;

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

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfUnprotect</h6>
     *
     * <p style="color: #CDCDCD">Unprotect PDF file</p>
     *
     * @param pdfSource (String: Path refer to PDF encrypted)
     * @param pdfTarget (String: Path to save the PDF File decrypted)
     * @param currentPassword (String: key to use in the PDF decrypt)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfUnprotect(String pdfSource, String pdfTarget, String currentPassword) throws IOException {

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBoxDetails {
        String author;
    }

}
