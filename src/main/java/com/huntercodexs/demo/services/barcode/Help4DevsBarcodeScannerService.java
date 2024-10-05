package com.huntercodexs.demo.services.barcode;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * Note: This code was made initially by David Keller and afterward changed/adapted
 * by Jereelton Teixeira to resolve a specific problem to read a barcode value form PDF files.<br />
 * This code works fine, but there is a bit of technical debts to solve and fix yet, you can use less
 * big problems, and if you have any questions just email to huntercodexs@gmail.com informing
 * that problem, no hassle.
 * <br />
 * License: MIT (free to use or copy)
 * <br />
 * About: Scanner one PDF file to get all barcode and qrcode from this file in the specific format
 * knowing by PdfBarcodeScannerResults that offers the following information:<br />
 * - page<br />
 * - barcodeType<br />
 * - barcodeValue<br />
 * Those information above are retrieve in the List as the affordable data structured
 *
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @author David KELLER <david.keller.fr@gmail.com>
 * @author huntercodexs (powered by jereelton-devel)
 */

@Slf4j
@Service
public class Help4DevsBarcodeScannerService {

    private int pageNumber;
    private int pixelDelimiter = 20;
    private List<Result> resultList;
    private GenericMultipleBarcodeReader reader;
    private Hashtable<DecodeHintType, Object> decodeHintTypes;

    private void initScanner(int pageNumber, int pixelDelimiter) {
        this.pageNumber = pageNumber;
        this.pixelDelimiter = pixelDelimiter;
        this.resultList = new ArrayList<>();
    }

    private void initReader() {
        this.reader = new GenericMultipleBarcodeReader(new DataMatrixReader());
        this.decodeHintTypes = new Hashtable<>();
        this.decodeHintTypes.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
    }

    private static List<Rectangle> getAllAreaByColor(
            BufferedImage in,
            BufferedImage out, /* if not null draw rectangle for debug purpose */
            int redColor,
            int greenColor,
            int blueColor,
            int maximumBlankPixelDelimiterCount,
            boolean debug
    )
            throws IOException
    {
        int w = in.getWidth();
        int h = in.getHeight();
        int pixel;
        List<Rectangle> areaList = new ArrayList<>();

        Graphics2D gc = null;

        if (out != null){
            gc = out.createGraphics();
            gc.setColor(new Color(1f, 0f, 0f));
        }

        int maximumBlankPixelDelimiterCountDouble = maximumBlankPixelDelimiterCount * 2;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                pixel = in.getRGB(x, y);
                int alpha = ((pixel >>24 ) & 0xFF);
                int red = ((pixel >>16 ) & 0xFF);
                int green = ((pixel >>8 ) & 0xFF);
                int blue = (pixel & 0xFF);

                if (red == redColor
                        && green == greenColor
                        && blue == blueColor)
                {
                    Rectangle rect
                            = new Rectangle(
                            x - maximumBlankPixelDelimiterCount,
                            y - maximumBlankPixelDelimiterCount,
                            maximumBlankPixelDelimiterCountDouble,
                            maximumBlankPixelDelimiterCountDouble);

                    boolean isInArea = false;
                    for(Rectangle rectangle : areaList){
                        if(rectangle.contains(x, y))
                        {
                            rectangle.add(rect);
                            isInArea = true;
                            break;
                        }
                    }

                    if (isInArea)
                    {
                        continue;
                    }

                    /*
                     * get pixel colors
                     */
                    pixel = pixel & 0x00000000;
                    pixel = pixel | (alpha << 24);
                    pixel = pixel | (0 <<16);
                    pixel = pixel | (255 <<8);
                    pixel = pixel | (0);

                    isInArea = false;
                    for (Rectangle rectangle : areaList) {
                        Rectangle intersection = rectangle.intersection(rect);
                        if(intersection.width > 0 && intersection.height > 0)
                        {
                            isInArea = true;
                            rectangle.add(rect);

                            break;
                        }
                    }

                    if (!isInArea) {
                        areaList.add(rect);
                    }

                    while (isInArea)
                    {
                        Rectangle rectToRemove = null;
                        isInArea = false;
                        for (Rectangle rectangle : areaList){
                            for(Rectangle rec2 : areaList)
                            {
                                if(rec2 == rectangle) continue;

                                Rectangle intersection = rectangle.intersection(rec2);
                                if (intersection.width > 0 && intersection.height > 0)
                                {
                                    if (debug) {
                                        System.out.println(rectangle + " intersect " + rec2);
                                    }
                                    isInArea = true;
                                    rectangle.add(rec2);
                                    rectToRemove = rec2;
                                    break;
                                }

                                if (isInArea) {
                                    break;
                                }
                            }
                        }

                        if (rectToRemove != null) {
                            areaList.remove(rectToRemove);
                        }
                    }

                    if (out != null) {
                        out.setRGB(x, y, pixel);
                        gc.draw(rect);
                    }
                }
            }
        }
        return areaList;
    }

    private static List<Rectangle> getAreaList(
            BufferedImage image,
            int maximumBlankPixelDelimiterCount
    )
            throws IOException
    {
        return getAllAreaByColor(
                image,
                null, // only for debug use 'out' image,
                0,
                0,
                0,
                maximumBlankPixelDelimiterCount,
                false);
    }

    private void extractBarcodeArrayByAreas(BufferedImage image, int pixelDelimiter) throws IOException {

        BufferedImage blackAndWhiteImage = getThresholdImage(image);
        List<Rectangle> areaList = getAreaList(blackAndWhiteImage, pixelDelimiter);

        for (Rectangle rectangle : areaList) {

            /*
             * verify bounds before crop image
             */
            if (rectangle.x < 0) {
                rectangle.x = 0;
            }

            if (rectangle.y < 0) {
                rectangle.y = 0;
            }

            if (rectangle.y + rectangle.height > image.getHeight()) {
                rectangle.height = image.getHeight() - rectangle.y;
            }

            if (rectangle.x + rectangle.width > image.getWidth()) {
                rectangle.width = image.getWidth() - rectangle.x;
            }

            /*
             * crop image to extract barcodes
             */
            BufferedImage croppedImage = image.getSubimage(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

            /*
             * zxing library can not deals with DataMatrix in all orientations, so
             * we have to rotate the image and ask zxing four times to find DataMatrix
             */
            if (addResults(extractBarcodeArray(croppedImage))) return;
            if (addResults(extractBarcodeArray(rotate90ToLeftImage(croppedImage, BufferedImage.TYPE_INT_ARGB)))) return;
            if (addResults(extractBarcodeArray(rotate90ToRightImage(croppedImage, BufferedImage.TYPE_INT_ARGB)))) return;
            if (addResults(extractBarcodeArray(rotate180Image(croppedImage, BufferedImage.TYPE_INT_ARGB)))) return;
        }
    }

    private boolean addResults(Result[] results) {
        if (results == null) {
            return false;
        }

        Collections.addAll(this.resultList, results);
        return true;
    }

    private Result[] extractBarcodeArray2(BufferedImage bufferedImage)
    {
        Result[] results = null;

        try {

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            results = reader.decodeMultiple(bitmap, decodeHintTypes);

        } catch (NotFoundException e) {
            System.out.println("There is some problem when try to extractBarcodeArray2");
            System.out.println(e.getMessage());
        }

        return results;
    }

    private static Result[] extractBarcodeArray(BufferedImage bufferedImage) {

        Result[] results = null;

        try {

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);
            results = new Result[1];
            results[0] = result;
            return results;

        } catch (Exception e) {
            System.out.println("There is some problem when try to extractBarcodeArray");
            System.out.println(e.getMessage());
        }

        return results;
    }

    private static BufferedImage rotate180Image(BufferedImage inputImage, int imageType) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(width, height, imageType);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(
                        width - x - 1,
                        height - y - 1,
                        inputImage.getRGB(x, y));
            }
        }

        return returnImage;
    }

    private static BufferedImage rotate90ToRightImage(BufferedImage inputImage, int imageType) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, imageType);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(
                        height - y - 1,
                        x,
                        inputImage.getRGB(x, y));
            }
        }
        return returnImage;
    }

    private static BufferedImage rotate90ToLeftImage(BufferedImage inputImage, int imageType) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, imageType);

        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
            }
        }
        return returnImage;
    }

    private static BufferedImage getBlackAndWhiteImage(BufferedImage image)
            throws IOException
    {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage imageBW = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = imageBW.createGraphics();
        g.drawRenderedImage(image, null);
        g.dispose();

        return  imageBW;
    }

    private static BufferedImage getThresholdImage(BufferedImage image)
            throws IOException
    {
        float saturationMin = 0.10f;
        float brightnessMin = 0.80f;
        BufferedImage thresholdImage = copyImage(image, BufferedImage.TYPE_INT_ARGB);

        computeBlackAndWhite(image, thresholdImage, saturationMin, brightnessMin);

        return thresholdImage;
    }

    private static void computeBlackAndWhite(
            BufferedImage bufferedImageInput,
            BufferedImage bufferedImageOutput,
            float saturationMin,
            float brightnessMin
    )
            throws IOException
    {
        int w = bufferedImageInput.getWidth();
        int h = bufferedImageInput.getHeight();
        int pixel;
        float[] hsb = new float[3];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                pixel = bufferedImageInput.getRGB(x, y);

                int alpha = ((pixel >>24 ) & 0xFF);
                int red = ((pixel >>16 ) & 0xFF);
                int green = ((pixel >>8 ) & 0xFF);
                int blue = (pixel & 0xFF);

                Color.RGBtoHSB(red, green, blue, hsb);

                if(hsb[2] < brightnessMin || (hsb[2] >= brightnessMin &&  hsb[1] >= saturationMin)) {
                    red = 0;
                    green = 0;
                    blue = 0;

                } else {
                    red = 255;
                    green = 255;
                    blue = 255;
                }

                pixel = pixel & 0x00000000;
                pixel = pixel | (alpha <<24 );
                pixel = pixel | (red <<16 );
                pixel = pixel | (green <<8 );
                pixel = pixel | (blue  );

                bufferedImageOutput.setRGB(x, y, pixel);
            }
        }
    }

    private static BufferedImage copyImage(BufferedImage bi, int type) {
        BufferedImage result = new BufferedImage(bi.getWidth(), bi.getHeight(), type);
        Graphics2D g = result.createGraphics();
        g.drawRenderedImage(bi, null);
        g.dispose();
        return result;
    }

    public void scanner(PDPage pdPage, int pageNumber, int pixelDelimiter) throws IOException {

        initScanner(pageNumber, pixelDelimiter);
        initReader();

        PDResources pdResources = pdPage.getResources();

        for (COSName name : pdResources.getXObjectNames()) {

            PDXObject pdxObject = pdResources.getXObject(name);

            if (pdxObject instanceof PDImageXObject) {

                PDImageXObject imageObject = (PDImageXObject) pdxObject;
                String suffix = imageObject.getSuffix();

                if (suffix != null) {
                    BufferedImage image = imageObject.getImage();
                    extractBarcodeArrayByAreas(image, this.pixelDelimiter);
                }
            }
        }
    }

    public List<PdfBarcodeScannerResults> results() {

        List<PdfBarcodeScannerResults> scannerResults = new ArrayList<>();

        for (Result result : resultList) {

            PdfBarcodeScannerResults results = new PdfBarcodeScannerResults();
            results.setPage(pageNumber);
            results.setBarcodeType(String.valueOf(result.getBarcodeFormat()));
            results.setBarcodeValue(result.getText());

            scannerResults.add(results);
        }

        return scannerResults;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfBarcodeScannerResults {
        int page;
        String barcodeType;
        String barcodeValue;
    }
}

