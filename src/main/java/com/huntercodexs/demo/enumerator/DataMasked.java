package com.huntercodexs.demo.enumerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.repeat;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.infoLog;

@Getter
@NoArgsConstructor
public enum DataMasked {

    //[1234345689828908], [1234-3456-8982-8908], [1234 3456 8982 8908]
    CARD_NUMBER_MASK(
            "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})",
            "$1$2@masked$4@masked$6$7",
            4),

    //[89765405823], [897-654-058-23], [897.654.058-23], [897.654.058.23], [897 654 058-23], [897 654 058 23]
    CPF_NUMBER_MASK(
            "([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{2})",
            "@masked$2@masked$4$5$6$7",
            3),

    //[89765405823], [897-654-058-23], [897.654.058-23], [897.654.058.23], [897 654 058-23], [897 654 058 23]
    CPF_NUMBER_MASK_INITIAL(
            "([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{2})",
            "$1$2$3$4@masked$6@masked",
            3),

    //[89765405823], [897-654-058-23], [897.654.058-23], [897.654.058.23], [897 654 058-23], [897 654 058 23]
    CPF_NUMBER_DIGIT_MASK(
            "([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{2})",
            "@masked$2$3$4$5$6@masked",
            3),

    //[39582423000100], [39-676-876-0001-53], [39-676-876/0001-53], [39.676.876.0001.53], [39.676.876/0001.53], [39 676 876 0001 53], [39 676 876 0001-53]
    CNPJ_NUMBER_MASK(
            "([0-9]{2})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. \\/]?)(000)(1)([-. ]?)([0-9]{2})",
            "$1$2@masked$4@masked$6@masked$8$9$10",
            3),

    //(SSP/SP) [238764581], [23.876.458-1], [23.876.458.1], [23 876 458 1], [23 876 458-1]
    RG_NUMBER_SSPSP_MASK(
            "([0-9]{2})([-. ]?)([0-9]{3})([-. ]?)([0-9]{3})([-. ]?)([0-9]{1,})",
            "$1$2@masked$4@masked$6$7",
            3),

    //[john.wizz@email.com], [john@email.com], [john.wizz@email.com.uk], [john.wizz-1000@email.com.uk]
    EMAIL_ADDRESS_MASK(
            "([a-zA-Z]{1})([0-9a-zA-Z]{1})([0-9a-zA-Z\\.\\-\\_]{1,})([0-9a-zA-Z\\.\\-\\_]{1})(\\@)(.*)",
            "$1$2@masked$4$5$6",
            5),

    //(Only Numbers) [5512982277653]
    PHONE_NUMBER_MASK(
            "([0-9]{2})?([0-9]{2})?([0-9]{1})?([0-9]{4})([0-9]{4})",
            "$1$2$3@masked$5",
            4),

    //[b642fd04-86e2-42e1-9b2c-2ba6d0b383cc]
    GUID_MASK(
            "([0-9a-z]{4})([0-9a-z]{4})(-)([0-9a-z]{4})(-)([0-9a-z]{4})(-)([0-9a-z]{4})(-)([0-9a-z]{4})([0-9a-z]{4})([0-9a-z]{4})",
            "$1@masked$3@masked$5@masked$7@masked$9$10@masked$12",
            4),

    //[only a test], [82394832948329], [19/10/1988], [new], [InnovationDevs], [Argument-1099]
    GENERIC_MASK(
            "([0-9a-zA-Z]{1})(.*)([0-9a-zA-Z]{1})",
            "$1@masked$3",
            0);

    private String pattern;
    private String replace;
    private int repeat;

    DataMasked(String pattern, String replace, int repeat) {
        this.pattern = pattern;
        this.replace = replace;
        this.repeat = repeat;
    }

    private static String defaultMask(String input, DataMasked dataMasked) {
        switch (dataMasked) {
            case CARD_NUMBER_MASK:
                return repeat("0", 16);
            case CPF_NUMBER_MASK:
            case CPF_NUMBER_DIGIT_MASK:
            case CPF_NUMBER_MASK_INITIAL:
                return repeat("0", 11);
            case CNPJ_NUMBER_MASK:
                return repeat("0", 14);
            case RG_NUMBER_SSPSP_MASK:
                return repeat("0", 9);
            case EMAIL_ADDRESS_MASK:
                return "******@*mail.com";
            case PHONE_NUMBER_MASK:
                return repeat("0", 13);
            case GENERIC_MASK:
                return repeat("*", 8);
            default:
                throw new RuntimeException("Wrong Data Mask Name");
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">dataMasked</h6>
     *
     * <p style="color: #CDCDCD">This method make a mask in the data input according the parameter dataMasked</p>
     *
     * @param data (String: the data value to apply mask)
     * @param mask (String: the type of mask [*, #, $, @...])
     * @param dataMasked (DataMasked Enum: the name of mask to apply in the current data)
     * @return String (Data Masked)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String dataMasked(String data, String mask, DataMasked dataMasked) {
        if (mask.isEmpty()) mask = "*";

        if (data == null || data.isEmpty()) {
            data = defaultMask(data, dataMasked);
        }

        if (dataMasked.equals(DataMasked.PHONE_NUMBER_MASK)) {
            data = data.replaceAll("[^0-9]", "");
        }

        if (dataMasked.equals(DataMasked.GENERIC_MASK)) {
            mask = repeat(mask, data.length()-2);
        } else {
            mask = repeat(mask, dataMasked.getRepeat());
        }

        if (dataMasked.equals(DataMasked.CPF_NUMBER_DIGIT_MASK) || dataMasked.equals(DataMasked.CPF_NUMBER_MASK_INITIAL) || dataMasked.equals(DataMasked.CPF_NUMBER_MASK)) {
            data = cpfPadding(data);
        }

        String pattern = dataMasked.getPattern();
        String replacement = dataMasked.getReplace().replaceAll("@masked", mask);
        String result = data.replaceAll(pattern, replacement);

        if (result.equals(data)) {
            infoLog("dataMasked say: Nothing to do ["+result+"]");
            return data;
        }

        if (dataMasked.equals(DataMasked.CPF_NUMBER_DIGIT_MASK) || dataMasked.equals(DataMasked.CPF_NUMBER_MASK_INITIAL)) {
            result = result.substring(0, result.length()-1);
            result = removeCpfPadding(result);
        } else if (dataMasked.equals(DataMasked.CPF_NUMBER_MASK)) {
            result = removeCpfPadding(result);
        }

        return result;
    }

    private static String cpfPadding(String data) {
        if (data.length() < 11) {
            var zerosToAdd = 11 - data.replaceAll("[^0-9]", "").length();
            var leadingZeros = repeat("0", zerosToAdd);
            data = leadingZeros + data;
        }
        return data;
    }

    private static String removeCpfPadding(String data) {
        return data.replaceFirst("^0+", "");
    }
}
