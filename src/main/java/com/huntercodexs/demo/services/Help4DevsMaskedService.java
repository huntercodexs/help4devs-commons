package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;

@Slf4j
@Service
public class Help4DevsMaskedService {

    public static String cardNumberMasked(String cardNumber, String mask) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, 4);
        String regexCard = "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})";
        return cardNumber.replaceAll(regexCard, "$1$2"+mask+"$4"+mask+"$6$7");
    }

}
