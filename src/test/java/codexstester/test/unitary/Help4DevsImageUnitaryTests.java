package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.IOException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.byteFile;
import static com.huntercodexs.demo.services.Help4DevsImageService.ImageType.*;
import static com.huntercodexs.demo.services.Help4DevsImageService.*;
import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsImageUnitaryTests extends Help4DevsBridgeTests {

    public static String path = "src/test/resources/help4devs/images";

    @Test
    public void isAcceptedTest() {
        codexsTesterAssertBool(true, isAnAcceptedImage("bmp"));
        codexsTesterAssertBool(true, isAnAcceptedImage("gif"));
        codexsTesterAssertBool(true, isAnAcceptedImage("png"));
        codexsTesterAssertBool(true, isAnAcceptedImage("jpeg"));
        codexsTesterAssertBool(true, isAnAcceptedImage("jpg"));
        codexsTesterAssertBool(false, isAnAcceptedImage("tiff"));
        codexsTesterAssertBool(false, isAnAcceptedImage("psd"));
        codexsTesterAssertBool(false, isAnAcceptedImage("svg"));
        codexsTesterAssertBool(false, isAnAcceptedImage("webp"));
        codexsTesterAssertBool(false, isAnAcceptedImage("nef"));
        codexsTesterAssertBool(false, isAnAcceptedImage("pdf"));
    }

    @Test
    public void isImageTest() throws IOException {
        boolean bmpResult = isAnImage(byteFile(path+"/1-bmp/file.bmp"));
        boolean gifResult = isAnImage(byteFile(path+"/2-gif/file.gif"));
        boolean pngResult = isAnImage(byteFile(path+"/3-png/file.png"));
        boolean jpeg1Result = isAnImage(byteFile(path+"/4-jpeg/file1.jpeg"));
        boolean jpeg2Result = isAnImage(byteFile(path+"/4-jpeg/file2.jpeg"));
        boolean jpeg3Result = isAnImage(byteFile(path+"/4-jpeg/file3.jpeg"));
        boolean jpg1Result = isAnImage(byteFile(path+"/5-jpg/file1.jpg"));
        boolean jpg2Result = isAnImage(byteFile(path+"/5-jpg/file2.jpg"));
        boolean tiffResult = isAnImage(byteFile(path+"/6-tiff/file.tiff"));
        boolean psdResult = isAnImage(byteFile(path+"/7-psd/file.psd"));
        boolean svgResult = isAnImage(byteFile(path+"/8-svg/file.svg"));
        boolean webpResult = isAnImage(byteFile(path+"/9-webp/file.webp"));
        boolean nefResult = isAnImage(byteFile(path+"/10-nef/file.NEF"));
        boolean pdfResult = isAnImage(byteFile(path+"/11-pdf/file.pdf"));

        codexsTesterAssertBool(true, bmpResult);
        codexsTesterAssertBool(true, gifResult);
        codexsTesterAssertBool(true, pngResult);
        codexsTesterAssertBool(true, jpeg1Result);
        codexsTesterAssertBool(true, jpeg2Result);
        codexsTesterAssertBool(true, jpeg3Result);
        codexsTesterAssertBool(true, jpg1Result);
        codexsTesterAssertBool(true, jpg2Result);
        codexsTesterAssertBool(false, tiffResult);
        codexsTesterAssertBool(false, psdResult);
        codexsTesterAssertBool(false, svgResult);
        codexsTesterAssertBool(false, webpResult);
        codexsTesterAssertBool(false, nefResult);
        codexsTesterAssertBool(false, pdfResult);
    }

    @Test
    public void imageTypeTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageType(byteFile(path+"/1-bmp/file.bmp")));
        stdout(repeat("-", 100));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(byteFile(path+"/2-gif/file.gif")));
        stdout(repeat("-", 100));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(byteFile(path+"/3-png/file.png")));
        stdout(repeat("-", 100));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/5-jpg/file1.jpg")));
        stdout(repeat("-", 100));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(byteFile(path+"/5-jpg/file2.jpg")));
        stdout(repeat("-", 100));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(byteFile(path+"/6-tiff/file.tiff")));
        stdout(repeat("-", 100));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(byteFile(path+"/7-psd/file.psd")));
        stdout(repeat("-", 100));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(byteFile(path+"/8-svg/file.svg")));
        stdout(repeat("-", 100));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(byteFile(path+"/9-webp/file.webp")));
        stdout(repeat("-", 100));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(byteFile(path+"/10-nef/file.NEF")));
        stdout(repeat("-", 100));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(byteFile(path+"/11-pdf/file.pdf")));
        stdout(repeat("-", 100));
    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageType(binFile(path+"/1-bmp/file.bmp")));
        stdout(repeat("-", 100));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(binFile(path+"/2-gif/file.gif")));
        stdout(repeat("-", 100));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(binFile(path+"/3-png/file.png")));
        stdout(repeat("-", 100));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 100));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/5-jpg/file1.jpg")));
        stdout(repeat("-", 100));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(binFile(path+"/5-jpg/file2.jpg")));
        stdout(repeat("-", 100));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(binFile(path+"/6-tiff/file.tiff")));
        stdout(repeat("-", 100));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(binFile(path+"/7-psd/file.psd")));
        stdout(repeat("-", 100));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(binFile(path+"/8-svg/file.svg")));
        stdout(repeat("-", 100));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(binFile(path+"/9-webp/file.webp")));
        stdout(repeat("-", 100));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(binFile(path+"/10-nef/file.NEF")));
        stdout(repeat("-", 100));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(binFile(path+"/11-pdf/file.pdf")));
        stdout(repeat("-", 100));
    }

    @Test
    public void imageEncodeTest() throws IOException {
        stdout(imageEncode(byteFile(path+"/5-jpg/file1.jpg")));
    }

    @Test
    public void imageDecodeTest() throws IOException {
        stdout(imageDecode(imageEncode(byteFile(path + "/5-jpg/file1.jpg"))));
    }

}
