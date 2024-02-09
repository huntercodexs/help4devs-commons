package com.huntercodexs.demojobs.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Slf4j
@Service
public class Help4DevsPathService {

    public static String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

    public static String sanitizeAscii(String input) {
        try {
            return Normalizer.normalize(input, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
        } catch (RuntimeException re) {
            log.error("Normalize Error: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }
    }

}
