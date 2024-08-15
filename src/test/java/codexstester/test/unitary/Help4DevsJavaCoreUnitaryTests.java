package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.core.Help4DevsJavaCoreService.recursiveSum;

public class Help4DevsJavaCoreUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void recursiveSumTest() {
        int total = recursiveSum(new Integer[]{2,2,2,2,2}, 0, 0);
        codexsTesterAssertInt(total, 10);
    }

}