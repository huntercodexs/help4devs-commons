package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.Help4DevsToolsService.errLog;

@Slf4j
@Service
public class Help4DevsFileReaderService {

    public static boolean exists(String filepath) {
        try {
            File file = new File(filepath);
            return file.exists();
        } catch (Exception e) {
            errLog(e.getMessage());
            return false;
        }
    }

    public static FileReader open(String filepath) {

        System.out.println("[INFO] >> Opening file: " + filepath);

        FileReader activateFile = null;

        try {
            activateFile = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return activateFile;
    }

    public static BufferedReader buffer(FileReader activateFile) {
        return new BufferedReader(activateFile);
    }

    public static String reader(BufferedReader readActivateFile) throws InterruptedException {
        String lineFile = "";

        try {
            Thread.sleep(1000);
            lineFile = readActivateFile.readLine();
            if (lineFile == null || lineFile.isEmpty()) {
                return "";
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return "";
        }

        return lineFile;
    }

    public static void close(FileReader activateFile, String filepath) throws IOException, InterruptedException {

        System.out.println("[INFO] << Closing file: " + filepath);
        System.out.println(repeat("-", 120));

        activateFile.close();
        System.out.flush();

        Thread.sleep(2000);
    }

    public static String getFileContentByMatch(String filepath, String regex, int timeout) throws Exception {
        String content = "null";
        int counter = 0;

        System.out.println("Awaiting content...");

        while (!content.matches(regex)) {

            FileReader openFile = open(filepath);
            BufferedReader bufferFile = buffer(openFile);

            try {
                counter++;
                content = reader(bufferFile);

                if (content.matches(regex)) {
                    break;
                }
                if (counter > timeout) {
                    close(openFile, filepath);
                    throw new RuntimeException("Time Exception to get content: timeout !");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }

            close(openFile,filepath);
        }

        return content;
    }

}
