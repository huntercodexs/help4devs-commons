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
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.*;
import java.util.stream.Stream;

import static com.huntercodexs.demo.services.Help4DevsToolsService.md5;

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

    private static String ioFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toString(fis);
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
     * @param bytesLength (int)
     * @return String (Image Size)
     * @implNote Simulate one image length in bytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateBytesTest(int bytesLength) {
        return calculateBytes(bytesLength);
    }

    /**
     * @param bytesLength (int)
     * @return String (Image Size)
     * @implNote Simulate one image length in kilobytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateKilobytesTest(int bytesLength) {
        return calculateKilobytes(bytesLength);
    }

    /**
     * @param bytesLength (int)
     * @return String (Image Size)
     * @implNote Simulate one image length in megabytes from an Integer value
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String simulateCalculateMegabytesTest(int bytesLength) {
        return calculateMegabytes(bytesLength);
    }

    /**
     * @param imageType (String: Image type [JPG, PNG, GIF etc...])
     * @return boolean
     * @implNote Check if the image is in the accepted formats
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image Content in bytes)
     * @return boolean
     * @implNote Check if the current file is really a valid image
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image Content in bytes)
     * @return String (Image Type)
     * @implNote Get the type of image from a byte source
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageType(byte[] image) {
        String imageInfo = new String(image).substring(0, 255);
        String imageInfo4 = new String(image).substring(0, 4);
        String imageInfo15 = new String(image).substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @param binaryImage (String: Binary Image)
     * @return String (Image Type)
     * @implNote Get the type of image from string source
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageType(String binaryImage) {
        String imageInfo = binaryImage.substring(0, 255);
        String imageInfo4 = binaryImage.substring(0, 4);
        String imageInfo15 = binaryImage.substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    /**
     * @param image (byte[]: Image Content in bytes)
     * @return String (Image Format)
     * @implNote Get the image format from byte[] image (same that imageType)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image Content in bytes)
     * @return Dimension (Image Dimension)
     * @implNote Get the image size (height x width) from byte[] image
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image Content in bytes)
     * @return String (Image Size)
     * @implNote Get the image length in {"bytes", "kilobytes", "megabytes"} from byte[] image
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image Content in bytes to encode)
     * @return String (Image File encoded in Base64)
     * @implNote Encode an image in base64 format from a byte data source
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageEncode(byte[] image) {
        return new String(Base64.getEncoder().encode(image));
    }

    /**
     * @param encodedImage (String: Encoded image to decode)
     * @return String (Image File Binary)
     * @implNote Decode data from a string bas64 data source
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageDecode(String encodedImage) {
        return new String(Base64.getDecoder().decode(encodedImage));
    }

    /**
     * @param image (byte[]: Image File in bytes to encrypt)
     * @param secretKey (String: Key to use in to encrypt)
     * @param salt (String: Salt to apply in to encrypt)
     * @return String (Image Encrypted: Base64(AES-256-CBC))
     * @implNote Encrypt one image from byte[] data using AES-256-CBC method
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageEncrypted(byte[] image, String secretKey, String salt) {
        System.out.println("Working on Image Encryption");
        long start = Calendar.getInstance().getTimeInMillis();

        String encode = imageEncode(image);
        String imageEncrypted = encryptAesCbc256(encode, secretKey, salt);

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);
        System.out.println("Elapsed Time: " + time + " seconds");
        System.out.println("Finishing Image Encryption");

        return String.valueOf(imageEncrypted);
    }

    /**
     * @param base64ImageToDecrypt (byte[]: Image File in bytes to decrypt)
     * @param secretKey (String: Key to use in to decrypt)
     * @param salt (String: Salt to apply in to decrypt)
     * @return String (Image Decrypted: Base64())
     * @implNote Decrypt on image from String data (previously encrypted with imageEncrypted method)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param image (byte[]: Image File in bytes)
     * @param matrixSize (int: Size to create a matrix, example [2=2x2, 3=3x3, 10=10x10])
     * @return List&lt;List&lt;String&gt;&gt; (Image Matrix: Base64)
     * @implNote This method convert an image file (from bytes) in one matrix with base64 values
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     */
    public static List<List<String>> imageToMatrix(byte[] image, int matrixSize) {
        if (matrixSize <= 1) {
            return null;
        }

        String encode = imageEncode(image);
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
     * @param imageMatrix (List&lt;List&lt;String&gt;&gt;: Matrix)
     * @return String (Image From Matrix: Base64)
     * @implNote This method revert a conversion made by imageToMatrix method from this class
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
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
     * @param filenamePath (String: Where the image should be saved)
     * @param image (byte[]: Image file to save)
     * @return boolean
     * @implNote Save data image in disk from memory (Bytes -> Base64)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean imageBse64Save(String filenamePath, byte[] image) {
        try {
            ImageFileWriter imageFileWriter = new ImageFileWriter();
            imageFileWriter.fileCreate(filenamePath);
            imageFileWriter.fileWrite(imageEncode(image));
            imageFileWriter.fileClose();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] IMAGE SAVE: " + e.getMessage());
        }
    }

    /**
     * @param imageSource (String: Image File Origin)
     * @param dataDestiny (String: Image File Target)
     * @return boolean
     * @implNote Copy one image from one origin to any destiny
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean imageCopy(String imageSource, String dataDestiny) {
        try {
            byte[] origin = byteFile(imageSource);
            String imageType = imageType(origin);
            String filenamePahFix = dataDestiny.split("\\.")[0]+"."+imageType;
            return fileWriter(origin, filenamePahFix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param image (byte[]: Image File in bytes)
     * @param pathToSave (String: Path to save fragmented image)
     * @return String (Folder Name: 4f37594a8968dac79a652e6a792b07fe_bmp)
     * @implNote Split one image in the matrix 20x20 format from a data byte image
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageFragment(byte[] image, String pathToSave) {
        Date date = new Date();

        String imageType = imageType(image).toLowerCase();
        String folderName = md5(String.valueOf(date.getTime()))+"_"+imageType;
        String filePath = pathToSave.replaceAll("/$", "") + "/" + folderName;

        ImageFileWriter imageFileWriter = new ImageFileWriter();
        imageFileWriter.folderCreate(filePath);

        List<List<String>> imageMatrix = imageToMatrix(image, 20);

        if (imageMatrix == null) {
            throw new RuntimeException("[ERROR] Image Matrix is null");
        }

        int index = 0;
        for (List<String> matrixLine : imageMatrix) {
            for (String matrixColumn : matrixLine) {
                index++;
                try {
                    imageFileWriter.fileCreate(filePath+"/"+folderName+"_"+String.format("%03d", index)+".txt");
                    imageFileWriter.fileWrite(matrixColumn);
                    imageFileWriter.fileClose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return folderName;
    }

    /**
     * @param filePath (String: Path where the image was previously fragmented)
     * @return String (Image: Base64)
     * @implNote Revert the image fragmentation from imageFragment method in this class
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String imageFragmentRevert(String filePath) {
        File file = new File(filePath);

        String[] filenames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches("[0-9a-z]{32}_[a-z]{3,4}_[0-9]{1,3}\\.txt");
            }
        });

        Stream<String> filesSorted = Arrays.stream(filenames).sorted(Comparator.naturalOrder());

        StringBuilder stringBuilder = new StringBuilder();
        filesSorted.forEach(current -> {
            try {
                stringBuilder.append(ioFile(filePath+"/"+current));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        return String.valueOf(stringBuilder);
    }

    /**
     * @param image (byte[]: Image File in bytes)
     * @return byte[] (Image Flipped: X axis)
     * @implNote Flip one image in X axis with true color support
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] imageFlipX(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = imageDimension(image).getWidth();
            String imageType = imageType(image).toLowerCase();

            /*Flip X*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(-1.0, 1.0);
            affineTransform.translate(-width, 0);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Flip X: " + ex.getMessage());
        }
    }

    /**
     * @param image (byte[]: Image File in bytes)
     * @return byte[] (Image Flipped: Y axis)
     * @implNote Flip one image in Y axis with true color support
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] imageFlipY(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int height = imageDimension(image).getHeight();
            String imageType = imageType(image).toLowerCase();

            /*Flip Y*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(1.0, -1.0);
            affineTransform.translate(0, -height);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Flip Y: " + ex.getMessage());
        }
    }

    /**
     * @param image (byte[]: Image File in bytes)
     * @return byte[] (Image Rotate)
     * @implNote Rotate an image in 180 degrees
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] imageRotate(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = imageDimension(image).getWidth();
            int height = imageDimension(image).getHeight();
            String imageType = imageType(image).toLowerCase();

            /*Rotate - 180 degrees*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.translate(width, height);
            affineTransform.rotate(Math.PI);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Rotate: " + ex.getMessage());
        }
    }

    /**
     * @param image (byte[]: Image File in bytes)
     * @param width (int: Image width to resize)
     * @param height (int: Image height to resize)
     * @return byte[] (Image Resized)
     * @implNote Resize an image in a specific size delimited by width x height
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] imageResize(byte[] image, int width, int height) {
        try {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            String imageType = imageType(image).toLowerCase();

            Image newImage = originalImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage destinationImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param image File Image in bytes[]
     * @param xAxis Position in the Image to begin the crop - axis X
     * @param yAxis Position in the Image to begin the crop - axis Y
     * @param cropWidth Width size to crop
     * @param cropHeight Height size to crop
     * @return byte[] (Image Cropped)
     * @implNote Crop an image in a specific point delimited by parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] imageCrop(byte[] image, int xAxis, int yAxis, int cropWidth, int cropHeight) {
        try {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            String imageType = imageType(image).toLowerCase();

            Image newImage = originalImage.getSubimage(xAxis, yAxis, cropWidth, cropHeight);
            BufferedImage destinationImage = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_RGB);
            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
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

        public boolean folderCreate(String path) {
            try {

                File file = new File(path);

                if (file.mkdirs()) {
                    return true;
                }

            } catch (Exception ex) {
                throw new RuntimeException("[EXCEPTION] Folder not created: " + ex.getMessage());
            }

            System.out.println("[ERROR] Folder not created: " + path);
            return false;
        }

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
