package com.huntercodexs.demo.services;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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
     * */
    public static void pdfFromDoc(String docPath, String filenamePath) throws FileNotFoundException, MalformedURLException {

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfReader</h6>
     *
     * <p style="color: #CDCDCD">Reader an specific PDF file</p>
     *
     * @param path (String: Location where the PDF file is placed)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfReader(String path) {

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCopy</h6>
     *
     * <p style="color: #CDCDCD">Make a copy from one PDF file to another</p>
     *
     * @param pdfOrigin (String: Location where the original/source PDF file is placed)
     * @param pdfDestiny (String: Location where the PDF copy should be put)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCopy(String pdfOrigin, String pdfDestiny) {

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

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfUpload</h6>
     *
     * <p style="color: #CDCDCD">Upload PDF file for the specific path defined in the parameter</p>
     *
     * @param pdfFile (MultipartFile: PDF Data file received)
     * @param pdfPath (String: Path where the PDF file should be saved)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfUpload(MultipartFile pdfFile, String pdfPath) {

    }

}
