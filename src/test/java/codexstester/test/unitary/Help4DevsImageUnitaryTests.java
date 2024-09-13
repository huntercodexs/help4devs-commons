package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.file.Help4DevsFileHandlerService;
import com.huntercodexs.demo.services.file.Help4DevsFileWriterService;
import com.huntercodexs.demo.services.image.Help4DevsImageService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.stdout;
import static com.huntercodexs.demo.services.file.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.file.Help4DevsFileHandlerService.byteFile;
import static com.huntercodexs.demo.services.image.Help4DevsImageService.*;
import static com.huntercodexs.demo.services.image.Help4DevsImageService.ImageType.*;
import static com.huntercodexs.demo.services.stdout.Help4DevsStdoutService.matrixPrinter;

public class Help4DevsImageUnitaryTests extends Help4DevsBridgeTests {

    private static final String pathImages = "src/test/resources/help4devs/images";
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
        boolean bmpResult = isAnImage(byteFile(pathImages +"/1-bmp/file.bmp"));
        boolean gifResult = isAnImage(byteFile(pathImages +"/2-gif/file.gif"));
        boolean pngResult = isAnImage(byteFile(pathImages +"/3-png/file.png"));
        boolean jpeg1Result = isAnImage(byteFile(pathImages +"/4-jpeg/file1.jpeg"));
        boolean jpeg2Result = isAnImage(byteFile(pathImages +"/4-jpeg/file2.jpeg"));
        boolean jpeg3Result = isAnImage(byteFile(pathImages +"/4-jpeg/file3.jpeg"));
        boolean jpg1Result = isAnImage(byteFile(pathImages +"/5-jpg/file1.jpg"));
        boolean jpg2Result = isAnImage(byteFile(pathImages +"/5-jpg/file2.jpg"));
        boolean tiffResult = isAnImage(byteFile(pathImages +"/6-tiff/file.tiff"));
        boolean psdResult = isAnImage(byteFile(pathImages +"/7-psd/file.psd"));
        boolean svgResult = isAnImage(byteFile(pathImages +"/8-svg/file.svg"));
        boolean webpResult = isAnImage(byteFile(pathImages +"/9-webp/file.webp"));
        boolean nefResult = isAnImage(byteFile(pathImages +"/10-nef/file.NEF"));
        boolean pdfResult = isAnImage(byteFile(pathImages +"/11-pdf/file.pdf"));

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
        codexsTesterAssertText(BMP.name(), imageType(byteFile(pathImages +"/1-bmp/file.bmp")));
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(byteFile(pathImages +"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(byteFile(pathImages +"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(pathImages +"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(pathImages +"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(pathImages +"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(byteFile(pathImages +"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(byteFile(pathImages +"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(byteFile(pathImages +"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(byteFile(pathImages +"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(byteFile(pathImages +"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(byteFile(pathImages +"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(byteFile(pathImages +"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(byteFile(pathImages +"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageType(binFile(pathImages +"/1-bmp/file.bmp")));
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageType(binFile(pathImages +"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageType(binFile(pathImages +"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(pathImages +"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(pathImages +"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(pathImages +"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageType(binFile(pathImages +"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPG.name(), imageType(binFile(pathImages +"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        codexsTesterAssertText(TIFF.name(), imageType(binFile(pathImages +"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        codexsTesterAssertText(PSD.name(), imageType(binFile(pathImages +"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        codexsTesterAssertText(SVG.name(), imageType(binFile(pathImages +"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        codexsTesterAssertText(WEBP.name(), imageType(binFile(pathImages +"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        codexsTesterAssertText(NEF.name(), imageType(binFile(pathImages +"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        codexsTesterAssertText(PDF.name(), imageType(binFile(pathImages +"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageFormatTest() throws IOException {

        stdout("FILE BMP");
        codexsTesterAssertText(BMP.name(), imageFormat(byteFile(pathImages +"/1-bmp/file.bmp")));
        stdout(repeat("-", 120));

        stdout("FILE GIF");
        codexsTesterAssertText(GIF.name(), imageFormat(byteFile(pathImages +"/2-gif/file.gif")));
        stdout(repeat("-", 120));

        stdout("FILE PNG");
        codexsTesterAssertText(PNG.name(), imageFormat(byteFile(pathImages +"/3-png/file.png")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(pathImages +"/4-jpeg/file1.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(pathImages +"/4-jpeg/file2.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 3 JPEG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(pathImages +"/4-jpeg/file3.jpeg")));
        stdout(repeat("-", 120));

        stdout("FILE 1 JPG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(pathImages +"/5-jpg/file1.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE 2 JPG");
        codexsTesterAssertText(JPEG.name(), imageFormat(byteFile(pathImages +"/5-jpg/file2.jpg")));
        stdout(repeat("-", 120));

        stdout("FILE TIFF");
        stdout(imageFormat(byteFile(pathImages +"/6-tiff/file.tiff")));
        stdout(repeat("-", 120));

        stdout("FILE PSD");
        stdout(imageFormat(byteFile(pathImages +"/7-psd/file.psd")));
        stdout(repeat("-", 120));

        stdout("FILE SVG");
        stdout(imageFormat(byteFile(pathImages +"/8-svg/file.svg")));
        stdout(repeat("-", 120));

        stdout("FILE WEBP");
        stdout(imageFormat(byteFile(pathImages +"/9-webp/file.webp")));
        stdout(repeat("-", 120));

        stdout("FILE NEF");
        stdout(imageFormat(byteFile(pathImages +"/10-nef/file.NEF")));
        stdout(repeat("-", 120));

        stdout("FILE PDF");
        stdout(imageFormat(byteFile(pathImages +"/11-pdf/file.pdf")));
        stdout(repeat("-", 120));
    }

    @Test
    public void imageDimensionTest() throws IOException {
        Dimension dimensionBmp = imageDimension(byteFile(pathImages +"/1-bmp/file.bmp"));
        codexsTesterAssertText("1419x1001", dimensionBmp.getWidth()+"x"+dimensionBmp.getHeight());

        Dimension dimensionGif = imageDimension(byteFile(pathImages +"/2-gif/file.gif"));
        codexsTesterAssertText("320x320", dimensionGif.getWidth()+"x"+dimensionGif.getHeight());

        Dimension dimensionPng = imageDimension(byteFile(pathImages +"/3-png/file.png"));
        codexsTesterAssertText("512x205", dimensionPng.getWidth()+"x"+dimensionPng.getHeight());

        Dimension dimensionJpeg = imageDimension(byteFile(pathImages +"/4-jpeg/file1.jpeg"));
        codexsTesterAssertText("273x184", dimensionJpeg.getWidth()+"x"+dimensionJpeg.getHeight());
    }

    @Test
    public void simulateCalculateBytesTest() {
        stdout(Help4DevsImageService.simulateCalculateBytes(1));
        stdout(Help4DevsImageService.simulateCalculateBytes(500));
        stdout(Help4DevsImageService.simulateCalculateBytes(897));
        stdout(Help4DevsImageService.simulateCalculateBytes(1023));
        stdout(Help4DevsImageService.simulateCalculateBytes(1024));
    }

    @Test
    public void simulateCalculateKilobytesTest() {
        stdout(Help4DevsImageService.simulateCalculateKilobytes(109693));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(1024));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(2024));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(2048));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(22024));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(722024));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(922024));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(1023000));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(1023780));
        stdout(Help4DevsImageService.simulateCalculateKilobytes(1024000));
    }

    @Test
    public void simulateCalculateMegabytesTest() {
        stdout(Help4DevsImageService.simulateCalculateMegabytes(4264316));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(21276657));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(1024000));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(2048000));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(9122024));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(91220244));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(391220244));
        stdout(Help4DevsImageService.simulateCalculateMegabytes(1024000000));
    }

    @Test
    public void imageSizeTest() throws IOException {
        stdout("BMP  " + imageSize(byteFile(pathImages +"/1-bmp/file.bmp")));
        stdout("GIF  " + imageSize(byteFile(pathImages +"/2-gif/file.gif")));
        stdout("PNG  " + imageSize(byteFile(pathImages +"/3-png/file.png")));
        stdout("PNG  " + imageSize(byteFile(pathImages +"/3-png/file-sample-1.png")));
        stdout("JPEG " + imageSize(byteFile(pathImages +"/4-jpeg/file1.jpeg")));
        stdout("JPEG " + imageSize(byteFile(pathImages +"/4-jpeg/file2.jpeg")));
        stdout("JPEG " + imageSize(byteFile(pathImages +"/4-jpeg/file3.jpeg")));
        stdout("JPG " + imageSize(byteFile(pathImages +"/5-jpg/file-sample-1.jpg")));
        stdout("JPG " + imageSize(byteFile(pathImages +"/5-jpg/file-sample-2.jpg")));
    }

    @Test
    public void imageEncodeTest() throws IOException {
        stdout(imageEncode(byteFile(pathImages +"/5-jpg/file1.jpg")));
    }

    @Test
    public void imageDecodeTest() throws IOException {
        stdout(imageDecode(imageEncode(byteFile(pathImages + "/5-jpg/file1.jpg"))));
    }

    @Test
    public void imageEncryptedTest() throws IOException {
        String imgEnc;
        imgEnc = imageEncrypted(byteFile(pathImages + "/1-bmp/file.bmp"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(pathImages + "/2-gif/file.gif"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(pathImages + "/3-png/file.png"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(pathImages + "/3-png/file-sample-1.png"), key, salt);
        //stdout(imgEnc);
        imgEnc = imageEncrypted(byteFile(pathImages + "/5-jpg/file1.jpg"), key, salt);
        //stdout(imgEnc);
    }

    @Test
    public void imageDecryptedTest() throws IOException {
        String imgEnc;
        String imgDec;

        imgEnc = imageEncrypted(byteFile(pathImages + "/1-bmp/file.bmp"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(pathImages + "/2-gif/file.gif"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(pathImages + "/3-png/file.png"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(pathImages + "/3-png/file-sample-1.png"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);

        imgEnc = imageEncrypted(byteFile(pathImages + "/5-jpg/file1.jpg"), key, salt);
        imgDec = imageDecrypted(imgEnc, key, salt);
        //stdout(imgDec);
    }

    @Test
    public void imageToMatrixTest() throws IOException {
        List<List<String>> imageToMatrix;
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/1-bmp/file.bmp"), 10);
        matrixPrinter(imageToMatrix, 3);
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/2-gif/file.gif"), 5);
        matrixPrinter(imageToMatrix, 3);
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/3-png/file.png"), 5);
        matrixPrinter(imageToMatrix, 3);
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/3-png/file-sample-1.png"), 20);
        matrixPrinter(imageToMatrix, 3);
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/5-jpg/file1.jpg"), 5);
        matrixPrinter(imageToMatrix, 3);
        imageToMatrix = imageToMatrix(byteFile(pathImages + "/5-jpg/file-sample-1.jpg"), 10);
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageFromMatrixTest() throws IOException {
        List<List<String>> imageToMatrix;

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/1-bmp/file.bmp"), 10);
        stdout(imageFromMatrix(imageToMatrix));

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/2-gif/file.gif"), 5);
        stdout(imageFromMatrix(imageToMatrix));

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/3-png/file.png"), 5);
        stdout(imageFromMatrix(imageToMatrix));

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/3-png/file-sample-1.png"), 20);
        stdout(imageFromMatrix(imageToMatrix));

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/5-jpg/file1.jpg"), 5);
        stdout(imageFromMatrix(imageToMatrix));

        imageToMatrix = imageToMatrix(byteFile(pathImages + "/5-jpg/file-sample-1.jpg"), 10);
        stdout(imageFromMatrix(imageToMatrix));
    }

    @Test
    public void imageBse64SaveTest() throws IOException {
        codexsTesterAssertBool(true,
                imageBse64Save(
                        "/home/jereelton/tmp/java-tests/5-jpg-file1.txt",
                        byteFile(pathImages + "/5-jpg/file1.jpg")));
    }

    @Test
    public void imageCopyTest() throws IOException {
        codexsTesterAssertBool(true,
                imageCopy(
                        pathImages + "/5-jpg/file1.jpg",
                        "/home/jereelton/tmp/java-tests/5-jpg-file1.txt"));
    }

    @Test
    public void imageFragmentTest() throws IOException {
        codexsTesterAssertRegExp("[0-9a-z]{32}_[a-z]{3,4}",
                imageFragment(
                        byteFile(pathImages + "/1-bmp/file.bmp"),
                        "/home/jereelton/tmp/java-tests/"));

        codexsTesterAssertRegExp("[0-9a-z]{32}_[a-z]{3,4}",
                imageFragment(
                        byteFile(pathImages + "/5-jpg/file1.jpg"),
                        "/home/jereelton/tmp/java-tests/"));
    }

    @Test
    public void imageFragmentRevertTest() {
        try {
            String filePath = "/home/jereelton/tmp/java-tests/";
            Help4DevsFileWriterService help4DevsFileWriterService = new Help4DevsFileWriterService();

            String folder = imageFragment(byteFile(pathImages + "/5-jpg/file1.jpg"), filePath);

            help4DevsFileWriterService.fileCreate(filePath+folder+".txt");
            help4DevsFileWriterService.fileWrite(imageFragmentRevert(filePath+folder));
            help4DevsFileWriterService.fileClose();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void imageFlipXTest() throws IOException {
        String filePath = "/home/jereelton/tmp/java-tests/file1-flip-x.jpg";
        Help4DevsFileHandlerService.fileWriter(imageFlipX(byteFile(pathImages + "/5-jpg/file1.jpg")), filePath);
    }

    @Test
    public void imageFlipYTest() throws IOException {
        String filePath = "/home/jereelton/tmp/java-tests/file1-flip-y.jpg";
        Help4DevsFileHandlerService.fileWriter(imageFlipY(byteFile(pathImages + "/5-jpg/file1.jpg")), filePath);
    }

    @Test
    public void imageRotateTest() throws IOException {
        String filePath = "/home/jereelton/tmp/java-tests/file1-rotate-180.jpg";
        Help4DevsFileHandlerService.fileWriter(imageRotate(byteFile(pathImages + "/5-jpg/file1.jpg")), filePath);
    }

    @Test
    public void imageResizeTest() throws IOException {
        String filePath = "/home/jereelton/tmp/java-tests/file1-resize.jpg";
        Help4DevsFileHandlerService.fileWriter(imageResize(byteFile(pathImages + "/5-jpg/file1.jpg"), 109, 37), filePath);
    }

    @Test
    public void imageCropTest() throws IOException {
        String filePath = "/home/jereelton/tmp/java-tests/file-crop.bmp";
        Help4DevsFileHandlerService.fileWriter(
                imageCrop(
                        byteFile(pathImages + "/1-bmp/file.bmp"),
                        200,
                        200,
                        300,
                        300), filePath);
    }
}
