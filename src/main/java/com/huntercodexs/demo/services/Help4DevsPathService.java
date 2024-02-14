package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Slf4j
@Service
public class Help4DevsPathService {

    public static String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

    public static String sanitizeAscii(String input, String letterType) {
        if (letterType == null) letterType = "";
        try {
            if (letterType.endsWith("upper")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
            } else if (letterType.endsWith("lower")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            } else {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
        } catch (RuntimeException re) {
            log.error("Normalize Error: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }
    }

}
