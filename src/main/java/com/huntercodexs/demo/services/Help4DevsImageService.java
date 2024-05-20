package com.huntercodexs.demo.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.*;

@Slf4j
@Service
public class Help4DevsImageService {

    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;

    private static String encryptAesCbc256(String strToEncrypt, String secretKey, String salt) {
        try {

            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] encryptedData = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);

        } catch (Exception e) {
            System.out.println("Encrypt Exception: " + e.getMessage());
            return null;
        }
    }

    private static String decryptAesCbc256(String strToDecrypt, String secretKey, String salt) {
        try {

            byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
            byte[] iv = new byte[16];
            System.arraycopy(encryptedData, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = new byte[encryptedData.length - 16];
            System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Decrypt Exception: " + e.getMessage());
            return null;
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

    private static byte[] byteFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toByteArray(fis);
    }

    private static boolean fileWriter(byte[] data, String path) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(data);
            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Is not possible to write in the file: " + ex.getMessage());
        }

        return true;
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in bytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateBytesTest(int bytesLength) {
        return calculateBytes(bytesLength);
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in kilobytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateKilobytesTest(int bytesLength) {
        return calculateKilobytes(bytesLength);
    }

    /**
     * @return String (Image Size)
     * @implNote Simulate one image length in megabytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateMegabytesTest(int bytesLength) {
        return calculateMegabytes(bytesLength);
    }

    /**
     * @return boolean
     * @implNote Check if the image is in the accepted formats
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
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
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageEncode(byte[] imageToEncode) {
        return new String(Base64.getEncoder().encode(imageToEncode));
    }

    /**
     * @return String (Image File Binary)
     * @implNote Decode data from a string bas64 data source
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageDecode(String encodedImage) {
        return new String(Base64.getDecoder().decode(encodedImage));
    }

    /**
     * @return String (Image Encrypted: Base64(AES-256-CBC))
     * @implNote Encrypt one image from byte[] data using AES-256-CBC method
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageEncrypted(byte[] byteImageToEncrypt, String secretKey, String salt) {
        System.out.println("Working on Image Encryption");
        long start = Calendar.getInstance().getTimeInMillis();

        String encode = imageEncode(byteImageToEncrypt);
        String imageEncrypted = encryptAesCbc256(encode, secretKey, salt);

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);
        System.out.println("Elapsed Time: " + time + " seconds");
        System.out.println("Finishing Image Encryption");

        return String.valueOf(imageEncrypted);
    }

    /**
     * @return String (Image Decrypted: Base64())
     * @implNote Decrypt on image from String data (previously encrypted with imageEncrypted method)
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageDecrypted(String base64ImageToDecrypt, String secretKey, String salt) {
        System.out.println("Working on Image Decryption");
        long start = Calendar.getInstance().getTimeInMillis();

        String imageDecrypted = decryptAesCbc256(base64ImageToDecrypt, secretKey, salt);

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);
        System.out.println("Finishing Image Decryption, elapsed time: " + time + " seconds");

        return String.valueOf(imageDecrypted);
    }

    /**
     * @return List<List<String>> (Image Matrix: Base64)
     * @implNote This method convert an image file (from bytes) in one matrix with base64 values
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     */
    public static List<List<String>> imageToMatrix(byte[] byteImageToMatrix, int matrixSize) {
        if (matrixSize <= 1) {
            return null;
        }

        String encode = imageEncode(byteImageToMatrix);
        int encodeLength = encode.length();
        int bytesLength = (encodeLength+matrixSize) / matrixSize;
        String[] lines = encode.split("(?<=\\G.{" + bytesLength + "})");

        List<List<String>> imageMatrix = new ArrayList<>();

        for (String line : lines) {

            List<String> matrixColumns = new ArrayList<>();

            int lineLength = line.length();
            int columnsLength = (lineLength+matrixSize) / matrixSize;
            String[] columns = line.split("(?<=\\G.{" + columnsLength + "})");

            Collections.addAll(matrixColumns, columns);

            imageMatrix.add(matrixColumns);
        }

        return imageMatrix;
    }

    /**
     * @return String (Image From Matrix: Base64)
     * @implNote This method revert a conversion made by imageToMatrix method from this class
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageFromMatrix(List<List<String>> imageMatrix) {

        StringBuilder stringBuilder = new StringBuilder();
        int matrixSize = imageMatrix.size();

        for (List<String> matrixLine : imageMatrix) {
            if (matrixLine.size() != matrixSize) {
                throw new RuntimeException("WRONG MATRIX SIZE: " + matrixSize +"x"+ matrixLine.size());
            }

            for (String matrixColumn : matrixLine) {
                stringBuilder.append(matrixColumn);
            }

        }

        return String.valueOf(stringBuilder);
    }

    /**
     * @return boolean
     * @implNote Save data image in disk from memory (Bytes -> Base64)
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean imageSave(String filenamePath, byte[] data) {
        try {
            ImageFileWriter imageFileWriter = new ImageFileWriter();
            imageFileWriter.fileCreate(filenamePath);
            imageFileWriter.fileWrite(imageEncode(data));
            imageFileWriter.fileClose();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] IMAGE SAVE: " + e.getMessage());
        }
    }

    /**
     * @return boolean
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean imageCopy(String dataOrigin, String dataDestiny) {
        try {
            byte[] origin = byteFile(dataOrigin);
            String imageType = imageType(origin);
            String filenamePahFix = dataDestiny.split("\\.")[0]+"."+imageType;
            return fileWriter(origin, filenamePahFix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageCompact() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageExtract() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageResize() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageRotate() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageFlip() {

    }

    /**
     * @return
     * @implNote
     * @see <a href="https://github.com/huntercodexs/help4devs">GitHub</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void imageCrop() {

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
    @Setter
    public static class ImageFileWriter {

        public BufferedWriter bufferedWriter;

        public void fileCreate(String filepath) throws FileNotFoundException {
            File file = new File(filepath);

            if (file.exists()) {
                if (!file.delete()) {
                    System.out.println("ERROR: File Not deleted: " + filepath);
                }
            }

            OutputStream os = new FileOutputStream(filepath, true);
            Writer wr = new OutputStreamWriter(os);
            this.bufferedWriter = new BufferedWriter(wr);
        }

        public void fileWrite(String data) {
            try {
                this.bufferedWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void fileClose() throws IOException {
            this.bufferedWriter.close();
        }
    }

}
