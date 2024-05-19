package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;

public class Help4DevsFilePathUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        System.out.println("RESULT IS: " + result);

        result = sanitizePath("/home/user/test");
        System.out.println("RESULT IS: " + result);
    }

}





