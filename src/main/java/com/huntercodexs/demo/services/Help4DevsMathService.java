package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsMathService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">decimalToBinary</h6>
     *
     * <p style="color: #CDCDCD">Convert a decimal to binary value</p>
     *
     * @param decimal (long)
     * @return String (Binary Number)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String decimalToBinary(long decimal) {
        StringBuilder binary = new StringBuilder();

        if (decimal == 0 || decimal == 1) return String.valueOf(decimal);

        while (decimal >= 1) {

            if (decimal == 1) {
                binary.append(1);
                break;
            }

            if (decimal % 2 == 0) {
                binary.append(0);
            } else {
                binary.append(1);
            }
            decimal = decimal / 2;
        }

        StringBuilder binaryResult = new StringBuilder();
        for (int i = binary.length()-1; i >= 0; i--) {
            binaryResult.append(binary.charAt(i));
        }

        return String.valueOf(binaryResult);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">binaryToDecimal</h6>
     *
     * <p style="color: #CDCDCD">Convert a binary to decimal value</p>
     *
     * @param binary (String)
     * @return int (Decimal Number)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static int binaryToDecimal(String binary) {
        if (Integer.parseInt(binary) < 1) return 0;
        int decimalNumber = 0;
        int power = binary.length();

        for (int i = 0; i < binary.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(binary.charAt(i)));
            decimalNumber += (digit * ((int) (Math.pow(2, power-1))));
            power--;
        }
        return decimalNumber;
    }

}
