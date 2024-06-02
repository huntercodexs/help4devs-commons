package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class Help4DevsPDFService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">pdfCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a PDF file quickly</p>
     *
     * @param data (String: Data to PDF create)
     * @param dataType (String: Data Type to PDF create)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void pdfCreate(String data, String dataType) {

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
