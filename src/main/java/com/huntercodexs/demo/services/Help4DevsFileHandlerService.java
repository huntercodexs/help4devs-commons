package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;

@Slf4j
@Service
public class Help4DevsFileHandlerService {

    public static Properties loadProps(String classpath) {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(classpath);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    public static InputStream bytesFileExtractor(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    public static InputStream fileToByteArray(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException {
        InputStream fileArray = fileToByteArray(targetPath, targetFile);
        return new ByteArrayDataSource(byteConvert(fileArray), "application/octet-stream");
    }

    public static byte[] byteConvert(InputStream fileArray) throws IOException {
        ByteArrayOutputStream bytArrayOutputStream = new ByteArrayOutputStream();
        bytArrayOutputStream.write(fileArray.read());
        return bytArrayOutputStream.toByteArray();
    }

    public static String fileToString(String targetPath, String targetFile) {

        StringBuilder fileContent = new StringBuilder();

        File path = new File(sanitizePath(targetPath));
        File file = new File(path, targetFile);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                fileContent.append(line).append("\n");
            }

            return fileContent.toString();

        } catch (IOException e) {
            log.error("fileToString: Exception: " + e.getMessage());
            throw new RuntimeException("FILE READER EXCEPTION: " + e.getMessage());
        }

    }

    public static ArrayList<String> fileToArray(String targetPath, String targetFile) {

        ArrayList<String> arrayFile = new ArrayList<>();

        File path = new File(sanitizePath(targetPath));
        File file = new File(path, targetFile);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                arrayFile.add(line);
            }

            return arrayFile;

        } catch (IOException e) {
            log.error("!!! E R R O R !!!");
            log.error("ScpcBoaVistaFileHandler > arrayFileReader: Exception: " + e.getMessage());
            throw new RuntimeException("FILE READER EXCEPTION: " + e.getMessage());
        }

    }

    public static String fileInputStream(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        return IOUtils.toString(fis, StandardCharsets.UTF_8);
    }

}
