package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @implNote Create a instance of Help4DevsFileWriterService to writer a file,
 * the annotation @Autowired also can be used:
 * <br /><br />use: <br />@Autowired
 *     <br />Help4DevsFileWriterService help4DevsFileWriterService;
 * <br /><br />or:
 *      <br />Help4DevsFileWriterService help4DevsFileWriterService = new Help4DevsFileWriterService()
 *
 * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
 * @author huntercodexs (powered by jereelton-devel)
 * */
@Slf4j
@Service
public class Help4DevsFileWriterService {

    public BufferedWriter bufferedWriter;

    /**
     * @param path (String: The path to create a target folder)
     * @return boolean
     * @implNote Create a folder in the specific path
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * @param path (String: The path to delete a target file)
     * @return boolean
     * @implNote Delete one file in the specific path
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean fileDelete(String path) {
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

        System.out.println("[ERROR] File not deleted: " + path);
        return false;
    }

    /**
     * @param path (String: The path to rename a target file)
     * @param newPath (String: The new path to rename a target file)
     * @return boolean
     * @implNote Rename file to specific filename passed in the parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean fileMove(String path, String newPath) {
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

        System.out.println("[ERROR] File not moved: " + path);
        return false;
    }

    /**
     * @param filepath (String: The path to create a target file)
     * @implNote Create a target file
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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

    /**
     * @param data (String: Data to write in the file)
     * @implNote Write in the target file
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileWrite(String data) {
        try {
            this.bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @implNote Append new line in the target file
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileNewLine() {
        try {
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @implNote Close the target file
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileClose() throws IOException {
        this.bufferedWriter.close();
    }

}
