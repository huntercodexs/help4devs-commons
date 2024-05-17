package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsImageService;
import org.junit.Test;

import java.io.IOException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.byteFile;
import static com.huntercodexs.demo.services.Help4DevsImageService.*;
import static com.huntercodexs.demo.services.Help4DevsImageService.ImageType.*;
import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsImageUnitaryTests extends Help4DevsBridgeTests {

    private static final String path = "src/test/resources/help4devs/images";
    private static final String key = "F1F2F3F4F5F6F7F8F9F00000";
    private static final String salt = "1";

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
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(byteFile(path+"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(byteFile(path+"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(path+"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(byteFile(path+"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(byteFile(path+"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(byteFile(path+"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(byteFile(path+"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(byteFile(path+"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(byteFile(path+"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(byteFile(path+"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageType(binFile(path+"/1-bmp/file.bmp")));
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(binFile(path+"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(binFile(path+"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(path+"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(binFile(path+"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(binFile(path+"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(binFile(path+"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(binFile(path+"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(binFile(path+"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(binFile(path+"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(binFile(path+"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageFormatTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageFormat(byteFile(path+"/1-bmp/file.bmp")));
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageFormat(byteFile(path+"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageFormat(byteFile(path+"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(path+"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(path+"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(path+"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(path+"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(path+"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        stdout(imageFormat(byteFile(path+"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        stdout(imageFormat(byteFile(path+"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        stdout(imageFormat(byteFile(path+"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        stdout(imageFormat(byteFile(path+"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        stdout(imageFormat(byteFile(path+"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        stdout(imageFormat(byteFile(path+"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageDimensionTest() throws IOException {
        Dimension dimensionBmp = imageDimension(byteFile(path+"/1-bmp/file.bmp"));
        codexsTesterAssertText("1419x1001", dimensionBmp.getWidth()+"x"+dimensionBmp.getHeight());

        Dimension dimensionGif = imageDimension(byteFile(path+"/2-gif/file.gif"));
        codexsTesterAssertText("320x320", dimensionGif.getWidth()+"x"+dimensionGif.getHeight());

        Dimension dimensionPng = imageDimension(byteFile(path+"/3-png/file.png"));
        codexsTesterAssertText("512x205", dimensionPng.getWidth()+"x"+dimensionPng.getHeight());

        Dimension dimensionJpeg = imageDimension(byteFile(path+"/4-jpeg/file1.jpeg"));
        codexsTesterAssertText("273x184", dimensionJpeg.getWidth()+"x"+dimensionJpeg.getHeight());
    }

    @Test
    public void simulateCalculateBytesTest() {
        stdout(Help4DevsImageService.simulateCalculateBytesTest(1));
        stdout(Help4DevsImageService.simulateCalculateBytesTest(500));
        stdout(Help4DevsImageService.simulateCalculateBytesTest(897));
        stdout(Help4DevsImageService.simulateCalculateBytesTest(1023));
        stdout(Help4DevsImageService.simulateCalculateBytesTest(1024));
    }

    @Test
    public void simulateCalculateKilobytesTest() {
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(109693));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(1024));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(2024));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(2048));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(22024));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(722024));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(922024));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(1023000));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(1023780));
        stdout(Help4DevsImageService.simulateCalculateKilobytesTest(1024000));
    }

    @Test
    public void simulateCalculateMegabytesTest() {
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(4264316));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(21276657));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(1024000));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(2048000));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(9122024));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(91220244));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(391220244));
        stdout(Help4DevsImageService.simulateCalculateMegabytesTest(1024000000));
    }

    @Test
    public void imageSizeTest() throws IOException {
        stdout("BMP  " + imageSize(byteFile(path+"/1-bmp/file.bmp")));
        stdout("GIF  " + imageSize(byteFile(path+"/2-gif/file.gif")));
        stdout("PNG  " + imageSize(byteFile(path+"/3-png/file.png")));
        stdout("PNG  " + imageSize(byteFile(path+"/3-png/file-sample-1.png")));
        stdout("JPEG " + imageSize(byteFile(path+"/4-jpeg/file1.jpeg")));
        stdout("JPEG " + imageSize(byteFile(path+"/4-jpeg/file2.jpeg")));
        stdout("JPEG " + imageSize(byteFile(path+"/4-jpeg/file3.jpeg")));
        stdout("JPG " + imageSize(byteFile(path+"/5-jpg/file-sample-1.jpg")));
        stdout("JPG " + imageSize(byteFile(path+"/5-jpg/file-sample-2.jpg")));
    }

    @Test
    public void imageEncodeTest() throws IOException {
        stdout(imageEncode(byteFile(path+"/5-jpg/file1.jpg")));
    }

    @Test
    public void imageDecodeTest() throws IOException {
        stdout(imageDecode(imageEncode(byteFile(path + "/5-jpg/file1.jpg"))));
    }

    @Test
    public void imageEncryptedTest() throws IOException {
        String imgEnc;
        imgEnc = imageEncrypted(byteFile(path + "/1-bmp/file.bmp"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(path + "/2-gif/file.gif"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(path + "/3-png/file.png"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(path + "/3-png/file-sample-1.png"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(path + "/5-jpg/file1.jpg"), key, salt);
        //stdout(imgEnc);
    }

    @Test
    public void imageDecryptedTest() throws IOException {
        String imgEnc;
        String imgDec;

        imgEnc = imageEncrypted(byteFile(path + "/1-bmp/file.bmp"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(path + "/2-gif/file.gif"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(path + "/3-png/file.png"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(path + "/3-png/file-sample-1.png"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(path + "/5-jpg/file1.jpg"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);
    }

    @Test
    public void imageToMatrixTest() throws IOException {
        String imgEnc;
        imgEnc = imageToMatrix(byteFile(path + "/1-bmp/file.bmp"), 10);
        //stdout(imgEnc);
        imgEnc = imageToMatrix(byteFile(path + "/2-gif/file.gif"), 5);
        //stdout(imgEnc);
        imgEnc = imageToMatrix(byteFile(path + "/3-png/file.png"), 5);
        //stdout(imgEnc);
        imgEnc = imageToMatrix(byteFile(path + "/3-png/file-sample-1.png"), 20);
        //stdout(imgEnc);
        imgEnc = imageToMatrix(byteFile(path + "/5-jpg/file1.jpg"), 5);
        //stdout(imgEnc);
        imgEnc = imageToMatrix(byteFile(path + "/5-jpg/file-sample-1.jpg"), 10);
        //stdout(imgEnc);
    }
}
