package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsHackerRankService {

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
