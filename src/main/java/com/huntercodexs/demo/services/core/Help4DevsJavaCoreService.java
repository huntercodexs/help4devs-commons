package com.huntercodexs.demo.services.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class Help4DevsJavaCoreService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">recursiveSum</h6>
     *
     * <p style="color: #CDCDCD">Sum a Integer array value</p>
     *
     * @param array (String)
     * @param pos (String)
     * @param total (String)
     * @return int (Total Sum)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static int recursiveSum(Integer[] array, int pos, int total) {
        if (pos >= array.length) {
            return total;
        }
        return recursiveSum(array, pos+1, total+array[pos]);
    }

    public static String replace() {
        return null;
    }

    public static String replaceFirst() {
        return null;
    }

    public static String replaceAll() {
        return null;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">listNormalize</h6>
     *
     * <p style="color: #CDCDCD">Fix one List of string content according below examples</p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * List<String> items = new ArrayList<>();
     * items.add("Device-1: Intel UHD Graphics 630 vendor: Acer Incorporated ALI driver: i915 v: kernel bus ID: 00:02.0");
     * items.add("Device-2: NVIDIA TU117M [GeForce GTX 1650 Mobile / Max-Q] vendor: Acer Incorporated ALI driver: nvidia");
     * items.add("v: 535.183.01 bus ID: 01:00.0");
     * items.add("Display: x11 server: X.Org 1.20.13 driver: modesetting FAILED: nvidia unloaded: fbdev,nouveau,vesa");
     * items.add("resolution: 1920x1080~60Hz, 2560x1080~60Hz");
     * items.add("OpenGL: renderer: Mesa Intel UHD Graphics 630 (CFL GT2) v: 4.6 Mesa 21.2.6 direct render: Yes");
     *
     * List<List<String>> cleanup = new ArrayList<>();
     *
     * List<String> toClean1 = new ArrayList<>();
     * toClean1.add("bus ID");
     * toClean1.add("budId");
     * cleanup.add(toClean1);
     *
     * List<String> toClean2 = new ArrayList<>();
     * toClean2.add("direct render");
     * toClean2.add("directRender");
     * cleanup.add(toClean2);
     *
     * List<String> listFixed = listNormalize(items, "Device-", cleanup);
     *
     * for (String item : listFixed) {
     *     System.out.println("===> " + item);
     * }
     * </pre></blockquote>
     *
     * <p>Extras</p>
     *
     * <p>
     *     You can also to use this method to create a list from a messing list of string into a
     *     new list of string organizing the index according one or more keys, see the example
     *     below to get more insights about it.
     * </p>
     *
     * <blockquote><pre>
     * List<String> items = new ArrayList<>();
     * items.add("Device-1: Intel UHD Graphics 630 vendor: Acer Incorporated ALI driver: i915 v: kernel bus ID: 00:02.0");
     * items.add("Device-2: NVIDIA TU117M [GeForce GTX 1650 Mobile / Max-Q] vendor: Acer Incorporated ALI driver: nvidia");
     * items.add("v: 535.183.01 bus ID: 01:00.0");
     * items.add("Display: x11 server: X.Org 1.20.13 driver: modesetting FAILED: nvidia unloaded: fbdev,nouveau,vesa");
     * items.add("resolution: 1920x1080~60Hz, 2560x1080~60Hz");
     * items.add("OpenGL: renderer: Mesa Intel UHD Graphics 630 (CFL GT2) v: 4.6 Mesa 21.2.6 direct render: Yes");
     * items.add("IF-ID-1: br-1222323251ed state: down mac: <filter>");
     * items.add("IF-ID-2: br-6a9bcd66bcea state: down mac: <filter>");
     * items.add("IF-ID-3: br-809eca8ee88a state: down mac: <filter>");
     *
     * List<List<String>> cleanup = new ArrayList<>();
     *
     * List<String> toClean1 = new ArrayList<>();
     * toClean1.add("bus ID");
     * toClean1.add("budId");
     * cleanup.add(toClean1);
     *
     * List<String> toClean2 = new ArrayList<>();
     * toClean2.add("direct render");
     * toClean2.add("directRender");
     * cleanup.add(toClean2);
     *
     * List<String> listFixed = listNormalize(items, "Device-|IF-ID-", cleanup);
     *
     * for (String item : listFixed) {
     *     System.out.println("===> " + item);
     * }
     * </pre></blockquote>
     *
     * <p>
     *     Look the line
     * </p>
     *
     * <blockquote><pre>
     * List<String> listFixed = listNormalize(items, "Device-|IF-ID-", cleanup);
     * </pre></blockquote>
     *
     * <p>
     *     you can see thar the argument passed in the very first is "Device-|IF-ID-", so, it means that
     *     the seek in the string should be made using these two keys and that these keys are separated by
     *     "|" character, this offer much more power and control to handle this kind of situations. In that
     *     specific example the output should be something like below
     * </p>
     *
     * <blockquote><pre>
     * ===> Device-1: Intel UHD Graphics 630 vendor: Acer Incorporated ALI driver: i915 v: kernel budId: 00:02.0
     * ===> Device-2: NVIDIA TU117M [GeForce GTX 1650 Mobile / Max-Q] vendor: Acer Incorporated ALI driver: nvidia v: 535.183.01 budId: 01:00.0 Display: x11 server: X.Org 1.20.13 driver: modesetting FAILED: nvidia unloaded: fbdev,nouveau,vesa resolution: 1920x1080~60Hz, 2560x1080~60Hz OpenGL: renderer: Mesa Intel UHD Graphics 630 (CFL GT2) v: 4.6 Mesa 21.2.6 directRender: Yes
     * ===> IF-ID-1: br-1222323251ed state: down mac: <filter>
     * ===> IF-ID-2: br-6a9bcd66bcea state: down mac: <filter>
     * ===> IF-ID-3: br-809eca8ee88a state: down mac: <filter>
     * </pre></blockquote>
     *
     * <pre>
     * IMPORTANT NOTE: DOT NOT USE REG-EXP OR BACKSLASHES
     * </pre>
     *
     * @param items (List&lt;String&gt;)
     * @param delimiter (String)
     * @param listFix (List&lt;List&lt;String&gt;&gt;)
     * @return List&lt;String&gt; (Total Sum)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static List<String> listNormalize(
            List<String> items,
            String delimiter,
            List<List<String>> listFix
    ) {
        String join = "";
        boolean deviceOn = false;
        List<String> list = new ArrayList<>();

        for (String item : items) {

            for (List<String> clear : listFix) {
                item = item.replaceAll(clear.get(0), clear.get(1));
            }

            String[] delimiters = delimiter.split("\\|");

            boolean contains = false;
            for (String delimit : delimiters) {

                if (item.contains(delimit)) {
                    contains = true;
                    break;
                }
            }

            if (contains) {

                if (deviceOn) {
                    list.add(join);
                    join = item;
                } else {
                    join = item;
                    deviceOn = true;
                }

            } else {
                join += " "+item;
            }

        }

        //Get last Device
        list.add(join);

        return list;
    }

}
