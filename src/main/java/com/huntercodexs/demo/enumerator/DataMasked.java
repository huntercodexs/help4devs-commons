package com.huntercodexs.demo.enumerator;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.repeat;

@Getter
@NoArgsConstructor
public enum DataMasked {

    //[1234345689828908], [1234-3456-8982-8908], [1234 3456 8982 8908]
    CARD_NUMBER_MASK(
            "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})",
            "$1$2@mask_replace$4@mask_replace$6$7",
            4),

    //[89765405823], [897-654-058-23], [897.654.058-23], [897.654.058.23], [897 654 058-23], [897 654 058 23]
    CPF_NUMBER_MASK(
            "([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{2})",
            "@mask_replace$2@mask_replace$4$5$6$7",
            3),

    //
    RG_NUMBER_MASK(
            "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})",
            "$1$2@mask_replace$4@mask_replace$6$7",
            4),

    //
    CNPJ_NUMBER_MASK(
            "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})",
            "$1$2@mask_replace$4@mask_replace$6$7",
            4);


    private String pattern;
    private String replace;
    private int repeat;

    DataMasked(String pattern, String replace, int repeat) {
        this.pattern = pattern;
        this.replace = replace;
        this.repeat = repeat;
    }

    public static String dataMasked(String data, String mask, DataMasked dataMasked) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, dataMasked.getRepeat());
        String pattern = dataMasked.getPattern();
        String replacement = dataMasked.getReplace().replaceAll("@mask_replace", mask);
        return data.replaceAll(pattern, replacement);
    }
}
