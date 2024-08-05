package com.huntercodexs.demo.services.format;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.repeat;

@Slf4j
@Service
public class Help4DevsMaskedService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">cardNumberMasked</h6>
     *
     * <p style="color: #CDCDCD">Mask a card number with a specific mask</p>
     *
     * @param cardNumber (String)
     * @param mask (String)
     * @return String (Masked Card Number)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String cardNumberMasked(String cardNumber, String mask) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, 4);
        String regexCard = "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})";
        return cardNumber.replaceAll(regexCard, "$1$2"+mask+"$4"+mask+"$6$7");
    }

}
