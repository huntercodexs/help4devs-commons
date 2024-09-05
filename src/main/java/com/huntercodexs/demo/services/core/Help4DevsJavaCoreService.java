package com.huntercodexs.demo.services.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * <p style="color: #CDCDCD">Fix one List of string content</p>
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
     * @param items (List&lt;String&gt;)
     * @param delimiter (String)
     * @param toClear (List&lt;List&lt;String&gt;&gt;)
     * @return List&lt;String&gt; (Total Sum)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static List<String> listNormalize(
            List<String> items,
            String delimiter,
            List<List<String>> toClear
    ) {
        String join = "";
        boolean deviceOn = false;
        List<String> list = new ArrayList<>();

        for (String item : items) {

            for (List<String> clear : toClear) {
                item = item.replaceAll(clear.get(0), clear.get(1));
            }

            //New Delimiter
            if (item.contains(delimiter)) {

                if (deviceOn) {
                    list.add(join);
                    join = item;
                } else {
                    join = item;
                    deviceOn = true;
                }

            } else {
                //Items
                join += " "+item;
            }

        }

        //Get last Device
        list.add(join);

        return list;
    }

}
