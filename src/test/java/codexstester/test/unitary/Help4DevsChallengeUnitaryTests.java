package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsChallengeService.isPangram;

public class Help4DevsChallengeUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void pangramsTest() {
        String pangram = ("We promptly judged antique ivory buckles for the next prize".toLowerCase());
        String notPangram = ("The promptly judged antique ivory buckles for the next prize").toLowerCase();

        codexsTesterAssertBool(true, isPangram(pangram, "en"));
        codexsTesterAssertBool(false, isPangram(notPangram, "en"));
    }

}





