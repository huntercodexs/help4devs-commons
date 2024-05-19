package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class Help4DevsFileWriterService {

    public BufferedWriter bufferedWriter;

    public boolean folderCreate(String path) {
        try {

            File file = new File(path);

            if (file.mkdirs()) {
                return true;
            }

        } catch (Exception ex) {
            throw new RuntimeException("EXCEPTION: Folder not created: " + ex.getMessage());
        }

        System.out.println("ERROR: Folder not created: " + path);
        return false;
    }

    public boolean fileDelete(String path) {
        try {

            File file = new File(path);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("EXCEPTION: File not deleted: " + ex.getMessage());
        }

        System.out.println("ERROR: File not deleted: " + path);
        return false;
    }

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
            throw new RuntimeException("EXCEPTION: File not moved: " + ex.getMessage());
        }

        System.out.println("ERROR: File not moved: " + path);
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

    public void fileWrite(Object data) {
        try {
            this.bufferedWriter.write(String.valueOf(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileNewLine() {
        try {
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileClose() throws IOException {
        this.bufferedWriter.close();
    }
}
