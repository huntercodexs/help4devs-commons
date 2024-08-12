package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.data.Help4DevsDataOrderService.linearSearch;

public class Help4DevsDataOrderUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void linearSearchTest() {

        String[] base = new String[]{"TTT","EEE","88I","AAA","UIQ","QQQ","KKK"};
        codexsTesterAssertBool(true, linearSearch(base, "UIQ"));
        codexsTesterAssertBool(false, linearSearch(base, "UIQ2"));

    }

    @Test
    public void bubbleSortTest() {}

    @Test
    public void dijkstraTest() {}

}
