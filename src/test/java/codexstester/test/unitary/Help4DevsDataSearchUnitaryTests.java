package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.data.Help4DevsDataSearchService.binarySearch;
import static com.huntercodexs.demo.services.data.Help4DevsDataSearchService.linearSearch;
import static com.huntercodexs.demo.services.data.Help4DevsDataSortService.bubbleSortForInteger;

public class Help4DevsDataSearchUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void linearSearchTest() {
        String[] base = new String[]{"TTT","EEE","88I","AAA","UIQ","QQQ","KKK"};
        codexsTesterAssertBool(true, linearSearch(base, "UIQ"));
        codexsTesterAssertBool(false, linearSearch(base, "UIQ2"));
    }

    @Test
    public void binarySearchTest() {
        Integer[] base = bubbleSortForInteger(new Integer[]{1,5,3,9,8,3,6,5,1,39,4,16,90});
        codexsTesterAssertBool(binarySearch(base, 39), true);
        codexsTesterAssertBool(binarySearch(base, 455), false);
    }

}
