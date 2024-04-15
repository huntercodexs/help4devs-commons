package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static java.lang.Math.ceil;

public class Help4DevsMathUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void ceilTest() {

        int splitter1 = (int) ceil((double) 5500 / 500);
        System.out.println(splitter1);

        int splitter2 = (int) ceil((double) 500 / 500);
        System.out.println(splitter2);

        int splitter3 = (int) ceil((double) 1200 / 500);
        System.out.println(splitter3);

        int splitter4 = (int) ceil((double) 800 / 500);
        System.out.println(splitter4);

    }

}





