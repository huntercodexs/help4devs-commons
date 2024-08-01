package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsPathService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">sanitizePath</h6>
     *
     * <p style="color: #CDCDCD">Sanitize any path as you need</p>
     *
     * @param path (String)
     * @return String (Path sanitized)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "") + "/";
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileExtension</h6>
     *
     * <p style="color: #CDCDCD">Get the file extension from one string</p>
     *
     * @param filepath (String)
     * @return String (Extension File)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String fileExtension(String filepath) {
        if (!filepath.contains(".")) {
            return filepath;
        }

        String[] fileParts = filepath.split("\\.");
        String fileType = fileParts[fileParts.length-1];

        if (fileType.isEmpty()) {
            return filepath;
        }

        return fileType;
    }

}
