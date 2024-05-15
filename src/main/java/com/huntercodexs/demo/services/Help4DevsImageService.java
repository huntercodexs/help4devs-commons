package com.huntercodexs.demo.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;

@Slf4j
@Service
public class Help4DevsImageService {

    @Getter
    @Setter
    public static class Dimension {
        int width;
        int height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    @Getter
    public enum ImageType {
        BMP(true, "Bitmap"),
        GIF(true, "Graphics Interchange Format"),
        PNG(true, "Portable Network Graphics"),
        JPEG(true, "Joint Photographic Experts Group"),
        JPG(true, "Joint Photographic Experts Group"),
        TIFF(false, "Tag Image File Format"),
        PSD(false, "Photoshop Document"),
        SVG(false, "Scalable Vector Graphics"),
        WEBP(false, "WEBP"),
        NEF(false, "Nikon Electronic Format"),
        PDF(false, "Portable Document Format");

        final boolean accepted;
        final String description;

        ImageType(boolean accepted, String description) {
            this.accepted = accepted;
            this.description = description;
        }
    }

    private static String imageTypeCheck(String imageInfo, String imageInfo4, String imageInfo15) {
        String fileType;
        if (imageInfo4.equals("BM|\u0011")) {
            fileType = ImageType.BMP.name();
        } else if (imageInfo4.contains("GIF")) {
            fileType = ImageType.GIF.name();
        } else if (imageInfo4.replaceAll("[^A-Z]", "").equals("PNG")) {
            fileType = ImageType.PNG.name();
        } else if (imageInfo4.equals("����") && imageInfo15.equals("JFIF")) {
            fileType = ImageType.JPEG.name();
        } else if (imageInfo4.equals("����")) {
            fileType = ImageType.JPG.name();
        } else if (imageInfo4.contains("II")) {
            fileType = ImageType.TIFF.name();
        } else if (imageInfo4.equals("8BPS")) {
            fileType = ImageType.PSD.name();
        } else if (imageInfo.contains("<svg") && imageInfo.contains("<?xml")) {
            fileType = ImageType.SVG.name();
        } else if (imageInfo.contains("WEBP") && imageInfo4.equals("RIFF")) {
            fileType = ImageType.WEBP.name();
        } else if (imageInfo4.contains("MM")) {
            fileType = ImageType.NEF.name();
        } else if (imageInfo4.contains("%PDF")) {
            fileType = ImageType.PDF.name();
        } else {
            fileType = "UNKNOWN FILE";
        }
        return fileType;

    }

    private static String calculateResult(String[] metrics, String scale) {
        if (metrics[1].length() >= 2) {
            metrics[1] = metrics[1].substring(0, 2);
        } else {
            metrics[1] = String.valueOf(metrics[1].charAt(0));
        }
        return metrics[0]+"."+metrics[1]+scale;
    }

    private static String calculateBytes(int bytesLength) {
        if (bytesLength >= 1 && bytesLength < 1024) {
            if (bytesLength == 1) {
                return bytesLength + "byte";
            } else {
                return bytesLength + "bytes";
            }
        }
        return null;
    }

    private static String calculateKilobytes(int bytesLength) {
        if (bytesLength >= 1024 && bytesLength < 1024000) {
            String size = String.valueOf(((float) bytesLength) / 1024);
            return calculateResult(size.split("\\."), "KB");
        }
        return null;
    }

    private static String calculateMegabytes(int bytesLength) {
        if (bytesLength >= 1024000 && bytesLength < 1024000000) {
            float kilobytes = (((float) bytesLength) / 1024);
            float megabytes = (kilobytes / 1024);
            String size = String.valueOf(megabytes);
            return calculateResult(size.split("\\."), "MB");
        }
        return null;
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in bytes from an Integer value
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateBytesTest(int bytesLength) {
        return calculateBytes(bytesLength);
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in kilobytes from an Integer value
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateKilobytesTest(int bytesLength) {
        return calculateKilobytes(bytesLength);
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in megabytes from an Integer value
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateMegabytesTest(int bytesLength) {
        return calculateMegabytes(bytesLength);
    }

    /**
     * @return boolean
     * @implNote Check if the image is in the accepted formats
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean isAnAcceptedImage(String imageType) {
        for (ImageType type : ImageType.values()) {
            if (imageType.toUpperCase().equals(type.name()) && type.isAccepted()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @implNote Check if the current file is really a valid image
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean isAnImage(byte[] image) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            return (bufferedImage != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return String (Image Type)
     * @implNote Get the type of image from a byte source
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageType(byte[] byteImage) {
        String imageInfo = new String(byteImage).substring(0, 255);
        String imageInfo4 = new String(byteImage).substring(0, 4);
        String imageInfo15 = new String(byteImage).substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @return String (Image Type)
     * @implNote Get the type of image from string source
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageType(String binaryImage) {
        String imageInfo = binaryImage.substring(0, 255);
        String imageInfo4 = binaryImage.substring(0, 4);
        String imageInfo15 = binaryImage.substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @return String (Image Format)
     * @implNote Get the image format from byte[] image (same that imageType)
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageFormat(byte[] image) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(image));
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            if (!reader.getFormatName().isEmpty()) {
                return reader.getFormatName().toUpperCase();
            }
        }

        return "UNKNOWN";
    }

    /**
     * @return Dimension (Image Dimension)
     * @implNote Get the image size (height x width) from byte[] image
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Dimension imageDimension(byte[] image) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(image));
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            try {
                reader.setInput(iis);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return new Dimension(width, height);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return new Dimension(0, 0);
    }

    /**
     * @return String (Image Size)
     * @implNote Get the image length in {"bytes", "kilobytes", "megabytes"} from byte[] image
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageSize(byte[] image) {

        if (!isAnImage(image)) {
            throw new RuntimeException("Invalid Image File");
        }

        if (image.length >= 1 && image.length < 1024) {
            return calculateBytes(image.length);
        }

        if (image.length >= 1024 && image.length < 1024000) {
            return calculateKilobytes(image.length);
        }

        if (image.length >= 1024000 && image.length < 1024000000) {
            return calculateMegabytes(image.length);
        }

        return "0.00KB";
    }

    /**
     * @return String (Image File encoded in Base64)
     * @implNote Encode an image in base64 format from a byte data source
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageEncode(byte[] imageToEncode) {
        return new String(Base64.getEncoder().encode(imageToEncode));
    }

    /**
     * @return String (Image File Binary)
     * @implNote Decode data from a string bas64 data source
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageDecode(String encodedImage) {
        return new String(Base64.getDecoder().decode(encodedImage));
    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageCrop() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageResize() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageToMatrix() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageFromMatrix() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageEncrypted() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageDecrypted() {

    }

}
