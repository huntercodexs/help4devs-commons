package com.huntercodexs.demo.services.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Service
public class Help4DevsFileReaderService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">awaitFileContent</h6>
     *
     * <p style="color: #CDCDCD">Get the file content based input parameters - like a sentinel</p>
     *
     * @param filepath (String: The filepath to reader the target file)
     * @param regex (String: The regular expression to interrupt the file reader process)
     * @param timeout (String: Time to automatically stop the file reader process)
     * @return String (File Content based on regular expression)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String awaitFileContent(String filepath, String regex, int timeout) throws Exception {

        String content = " ";
        int counter = 0;

        while (!content.matches(regex)) {

            FileReader openFile = open(filepath);
            BufferedReader bufferFile = buffer(openFile);

            try {
                counter++;
                content = reader(bufferFile);

                if (content.matches(regex)) {
                    break;
                }

                if (counter > timeout && timeout > 0) {
                    close(openFile, filepath);
                    throw new RuntimeException("Time Exception to get content: timeout !");
                }

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            close(openFile,filepath);
        }

        return content;
    }

    private static FileReader open(String filepath) {

        FileReader activateFile;

        try {
            activateFile = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return activateFile;
    }

    private static BufferedReader buffer(FileReader activateFile) {
        return new BufferedReader(activateFile);
    }

    private static String reader(BufferedReader readActivateFile) throws InterruptedException {
        String lineFile;

        try {
            Thread.sleep(1000);
            lineFile = readActivateFile.readLine();
            if (lineFile == null || lineFile.isEmpty()) {
                return "";
            }
        } catch (IOException e) {
            return "";
        }

        return lineFile;
    }

    private static void close(FileReader activateFile, String filepath) throws IOException, InterruptedException {
        activateFile.close();
        Thread.sleep(2000);
    }

}
