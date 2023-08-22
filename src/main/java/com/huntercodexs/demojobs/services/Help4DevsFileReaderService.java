package com.huntercodexs.demojobs.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Log4j2
@Service
public class Help4DevsFileReaderService {

    public static FileReader open(String filepath) {
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

    public static String reader(BufferedReader readActivateFile) {
        String lineFile = "";

        try {
            lineFile = readActivateFile.readLine();
            if (lineFile == null || lineFile.equals("")) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        return lineFile;
    }

    public static void close(FileReader activateFile) throws IOException {
        activateFile.close();
        System.out.flush();
    }

    public static String getFileContent(String filepath, String regex, int timeout) throws Exception {
        String code = null;
        int counter = 0;

        FileReader openFile = open(filepath);
        BufferedReader bufferFile = buffer(openFile);

        System.out.println("Awaiting content...");

        while (code == null) {

            try {
                Thread.sleep(1000);
                counter++;
                code = reader(bufferFile);
                if (code != null && code.matches(regex)) {
                    break;
                }
                if (counter > timeout) {
                    close(openFile);
                    throw new RuntimeException("Time Exception to get content: timeout !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        close(openFile);

        return code;
    }

}
