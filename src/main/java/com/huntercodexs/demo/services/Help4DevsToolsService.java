package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Slf4j
@Service
public class Help4DevsToolsService {

    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String guide(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static boolean isPangram(String input, String language) {

        String[] englishAlphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        String pangram = input.toLowerCase();
        int alphabetSize = englishAlphabet.length;
        int pangramCounter = 0;

        for (String s : englishAlphabet) {
            if (pangram.contains(s)) {
                pangramCounter += 1;
            }
        }

        return pangramCounter == alphabetSize;

    }
}
