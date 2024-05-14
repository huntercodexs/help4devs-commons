package com.huntercodexs.demo.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
public class Help4DevsImageService {

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

    /**
     * @implNote Check if the image is in the accepted formats
     * @return boolean
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
     * @implNote Check if the current file is really a valid image
     * @return boolean
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
     * @implNote Get the type of image from a byte source
     * @return String (Image Type)
     * */
    public static String imageType(byte[] byteImage) {
        String imageInfo = new String(byteImage).substring(0, 255);
        String imageInfo4 = new String(byteImage).substring(0, 4);
        String imageInfo15 = new String(byteImage).substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @implNote Get the type of image from string source
     * @return String (Image Type)
     * */
    public static String imageType(String binaryImage) {
        String imageInfo = binaryImage.substring(0, 255);
        String imageInfo4 = binaryImage.substring(0, 4);
        String imageInfo15 = binaryImage.substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @implNote Encode an image in base64 format from a byte data source
     * @return String (Image File encoded in Base64)
     * */
    public static String imageEncode(byte[] imageToEncode) {
        return new String(Base64.getEncoder().encode(imageToEncode));
    }

    /**
     * @implNote Decode data from a string bas64 data source
     * @return String (Image File Binary)
     * */
    public static String imageDecode(String encodedImage) {
        return new String(Base64.getDecoder().decode(encodedImage));
    }

}
