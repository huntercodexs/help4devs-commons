package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;

public class Help4DevsPathUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        codexsTesterAssertText("/home/user/test/", result);

        result = sanitizePath("/home/user/test");
        codexsTesterAssertText("/home/user/test", result);

        result = sanitizePath("/home/user/test/text.txt");
        codexsTesterAssertText("/home/user/test/text.txt", result);

        result = sanitizePath("text.txt");
        codexsTesterAssertText("text.txt", result);

        result = sanitizePath("text");
        codexsTesterAssertText("text", result);
    }

}
