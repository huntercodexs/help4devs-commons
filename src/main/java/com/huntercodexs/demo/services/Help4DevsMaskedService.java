package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;

@Slf4j
@Service
public class Help4DevsMaskedService {

    /**
     * @param cardNumber (String)
     * @param mask (String)
     * @return String (Masked Card Number)
     * @implNote Mask a card number with a specific mask
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String cardNumberMasked(String cardNumber, String mask) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, 4);
        String regexCard = "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})";
        return cardNumber.replaceAll(regexCard, "$1$2"+mask+"$4"+mask+"$6$7");
    }

}
