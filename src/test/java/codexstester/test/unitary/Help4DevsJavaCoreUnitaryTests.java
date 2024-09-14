package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.core.Help4DevsJavaCoreService.listNormalize;
import static com.huntercodexs.demo.services.core.Help4DevsJavaCoreService.recursiveSum;

public class Help4DevsJavaCoreUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void recursiveSumTest() {
        int total = recursiveSum(new Integer[]{2,2,2,2,2}, 0, 0);
        codexsTesterAssertInt(total, 10);
    }

    @Test
    public void listNormalizeTest() {

        List<String> items = new ArrayList<>();
        items.add("Device-1: Intel UHD Graphics 630 vendor: Acer Incorporated ALI driver: i915 v: kernel bus ID: 00:02.0");
        items.add("Device-2: NVIDIA TU117M [GeForce GTX 1650 Mobile / Max-Q] vendor: Acer Incorporated ALI driver: nvidia");
        items.add("v: 535.183.01 bus ID: 01:00.0");
        items.add("Display: x11 server: X.Org 1.20.13 driver: modesetting FAILED: nvidia unloaded: fbdev,nouveau,vesa");
        items.add("resolution: 1920x1080~60Hz, 2560x1080~60Hz");
        items.add("OpenGL: renderer: Mesa Intel UHD Graphics 630 (CFL GT2) v: 4.6 Mesa 21.2.6 direct render: Yes");
        items.add("IF-ID-1: br-1222323251ed state: down mac: <filter>");
        items.add("IF-ID-2: br-6a9bcd66bcea state: down mac: <filter>");
        items.add("IF-ID-3: br-809eca8ee88a state: down mac: <filter>");

        List<List<String>> cleanup = new ArrayList<>();

        List<String> toClean1 = new ArrayList<>();
        toClean1.add("bus ID");
        toClean1.add("budId");
        cleanup.add(toClean1);

        List<String> toClean2 = new ArrayList<>();
        toClean2.add("direct render");
        toClean2.add("directRender");
        cleanup.add(toClean2);

        List<String> listFixed = listNormalize(items, "Device-|IF-ID-", cleanup);

        for (String item : listFixed) {
            System.out.println("===> " + item);
        }

    }

}