package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsPathService {

    /**
     * @param path (String)
     * @return String (Path sanitized)
     * @implNote Sanitize any path as you need
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "") + "/";
    }

}
