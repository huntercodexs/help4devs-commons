package com.huntercodexs.demo.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsImageService {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    enum ImageType {
        GIF,
        PNG,
        JPG,
        BMP,
        TIFF,
        JPEG,
        PSD,
        SVG,
        WEBP,
        RAW,
        PDF;
    }

    /**
     * @implNote
     * */
    public static void isImage(byte[] image) {

    }

    /**
     * @implNote
     * */
    public static void imageType(byte[] image) {

    }

    /**
     * @implNote
     * */
    public static void imageType(String image) {

    }

    /**
     * @implNote
     * */
    public static void imageEncode(byte[] imageToEncode) {

    }

    /**
     * @implNote
     * */
    public static void imageDecode(String encodedImage) {

    }

}
