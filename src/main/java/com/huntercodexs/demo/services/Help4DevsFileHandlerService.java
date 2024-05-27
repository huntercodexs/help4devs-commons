package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;

import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;
import static com.huntercodexs.demo.services.Help4DevsToolsService.errLog;

@Slf4j
@Service
public class Help4DevsFileHandlerService {

    /** Legacy */
    public static InputStream bytesFileExtractor(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /** Legacy */
    public static InputStream fileToByteArray(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /** Legacy */
    public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException {
        InputStream fileArray = fileToByteArray(targetPath, targetFile);
        return new ByteArrayDataSource(byteConvert(fileArray), "application/octet-stream");
    }

    /** Legacy */
    public static String fileInputStream(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        return IOUtils.toString(fis, StandardCharsets.UTF_8);
    }

    /** Legacy */
    public static boolean exists(String filepath) {
        try {
            File file = new File(filepath);
            return file.exists();
        } catch (Exception e) {
            errLog(e.getMessage());
            return false;
        }
    }

    /**
     * @param classpath (String: The path where the application properties file is placed)
     * @return Properties (Application Properties Details)
     * @implNote Get all data from application properties file passed in the classpath parameter
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Properties loadProps(String classpath) {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(classpath);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        return properties;
    }

    /**
     * @param fileArray (InputStream: File Content in Array Format)
     * @return byte (The file content un bytes)
     * @implNote Convert the InputStream in the bytes
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] byteConvert(InputStream fileArray) throws IOException {
        ByteArrayOutputStream bytArrayOutputStream = new ByteArrayOutputStream();
        bytArrayOutputStream.write(fileArray.read());
        return bytArrayOutputStream.toByteArray();
    }

    /**
     * @param targetPath (String: Pathname target)
     * @param targetFile (String: Filename target)
     * @return String (File Content)
     * @implNote Get the content file in the string format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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
            throw new RuntimeException("[EXCEPTION] FILE READER: " + e.getMessage());
        }

    }

    /**
     * @param targetPath (String: Pathname target)
     * @param targetFile (String: Filename target)
     * @return ArrayList (File Content in ArrayList)
     * @implNote Convert a txt file into an array
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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
            throw new RuntimeException("[EXCEPTION] FILE READER: " + e.getMessage());
        }

    }

    /**
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return byte[] (File Content in bytes)
     * @implNote Read a file and get the content in bytes format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] byteFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toByteArray(fis);
    }

    /**
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return String (File Content)
     * @implNote Read a file and get the content in string format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String ioFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toString(fis);
    }

    /**
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return String (File Binary Content)
     * @implNote Read a file and get the content in binary format
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String binFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return new String(IOUtils.toByteArray(fis), StandardCharsets.UTF_8);
    }

    /**
     * @param path (String: The current path to create folder)
     * @return boolean
     * @implNote Create a folder according to path parameter
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean folderCreate(String path) {
        try {

            File file = new File(path);

            if (file.mkdirs()) {
                return true;
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Folder not created: " + ex.getMessage());
        }

        System.out.println("ERROR: Folder not created: " + path);
        return false;
    }

    /**
     * @param filePath (String: The current path to list files)
     * @param regExpFilter (String: The regular expression to match files)
     * @return String[]
     * @implNote List files in the specific directory according to filepath parameter
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String[] fileList(String filePath, String regExpFilter) {
        File file = new File(filePath);

        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches(regExpFilter);
            }
        });
    }

    /**
     * @param path (String: The current file or directory path to delete)
     * @return boolean
     * @implNote Delete on file or directory simply passing the path in the parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileDelete(String path) {
        try {

            File file = new File(path);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not deleted: " + ex.getMessage());
        }

        System.out.println("ERROR: File not deleted: " + path);
        return false;
    }

    /**
     * @param path (String: The current file or directory path)
     * @param newPath (String: The new path that should be applied in the file or directory target)
     * @return boolean
     * @implNote Rename on file or directory simply passing the old and new path in the parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileMove(String path, String newPath) {
        try {

            File file = new File(path);
            File newFile = new File(newPath);

            if (file.exists()) {
                if (file.renameTo(newFile)) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not moved: " + ex.getMessage());
        }

        System.out.println("ERROR: File not moved: " + path);
        return false;
    }

    /**
     * @param data (byte[]: The content in bytes to write in the target file)
     * @param path (String: The path where the file to write is located)
     * @return boolean
     * @implNote Write in some file according data parameters, if the process not end up with success
     * then one exception will be thrown
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileWriter(byte[] data, String path) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(data);
            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Is not possible to write in the file: " + ex.getMessage());
        }

        return true;
    }

}
