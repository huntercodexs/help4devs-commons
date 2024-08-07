package com.huntercodexs.demo.services.basic;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class Help4DevsBaseService {

    private static String calculateResult(String[] metrics, String scale) {
        if (metrics[1].length() >= 2) {
            metrics[1] = metrics[1].substring(0, 2);
        } else {
            metrics[1] = String.valueOf(metrics[1].charAt(0));
        }
        return metrics[0]+"."+metrics[1]+scale;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">params</h6>
     *
     * <p style="color: #CDCDCD">Sample method to show how to work and use the grouped parameters</p>
     *
     * @param jsonObjects (JSONObject: Sample)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">params</h6>
     *
     * <p style="color: #CDCDCD">Sample method to show how to work and use the grouped parameters</p>
     *
     * @param bytesLength (long)
     * @return String (Bytes length calculated - Bytes)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String calculateBytes(long bytesLength) {
        if (bytesLength >= 1 && bytesLength < 1024) {
            if (bytesLength == 1) {
                return bytesLength + "byte";
            } else {
                return bytesLength + "bytes";
            }
        }
        return null;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">params</h6>
     *
     * <p style="color: #CDCDCD">Sample method to show how to work and use the grouped parameters</p>
     *
     * @param bytesLength (long)
     * @return String (Bytes length calculated - KB)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String calculateKilobytes(long bytesLength) {
        if (bytesLength >= 1024 && bytesLength < 1024000) {
            String size = String.valueOf(((float) bytesLength) / 1024);
            return calculateResult(size.split("\\."), "KB");
        }
        return null;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">params</h6>
     *
     * <p style="color: #CDCDCD">Sample method to show how to work and use the grouped parameters</p>
     *
     * @param bytesLength (long)
     * @return String (Bytes length calculated - MB)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String calculateMegabytes(long bytesLength) {
        if (bytesLength >= 1024000 && bytesLength < 1024000000) {
            float kilobytes = (((float) bytesLength) / 1024);
            float megabytes = (kilobytes / 1024);
            String size = String.valueOf(megabytes);
            return calculateResult(size.split("\\."), "MB");
        }
        return null;
    }

}
