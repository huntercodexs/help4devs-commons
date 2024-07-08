package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCpf;
import static com.huntercodexs.demo.services.Help4DevsValidatorService.*;

public class Help4DevsValidatorUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void cpfValidatorTest() {
        boolean result = cpfValidator("07365238801");
        codexsTesterAssertBool(result, true);

        boolean result2 = cpfValidator("07365238899");
        codexsTesterAssertBool(result2, false);

        boolean result3 = cpfValidator(randomCpf());
        codexsTesterAssertBool(result3, true);
    }

    @Test
    public void mailValidatorTest() throws Exception {
        boolean result = mailValidator("marcos_portela@yahoo.com.br");
        codexsTesterAssertBool(result, true);

        result = mailValidator("johnsmith23@email.com");
        codexsTesterAssertBool(result, true);
    }

    @Test
    public void phoneValidatorTest() {
        boolean result = phoneValidator("5511982772389", "br");
        codexsTesterAssertBool(result, true);

        boolean result2 = phoneValidator("5511982772", "br");
        codexsTesterAssertBool(result2, false);

        boolean result3 = phoneValidator("551187722212", "br");
        codexsTesterAssertBool(result3, true);
    }

    @Test
    public void cvvValidatorTest() {
        codexsTesterAssertBool(false, cvvValidator("1111"));
        codexsTesterAssertBool(false, cvvValidator("a99"));
        codexsTesterAssertBool(false, cvvValidator("aaa"));
        codexsTesterAssertBool(true, cvvValidator("999"));
        codexsTesterAssertBool(true, cvvValidator("123"));
        codexsTesterAssertBool(true, cvvValidator("765"));
    }

    @Test
    public void cardNumberValidatorTest() {
        codexsTesterAssertBool(false, cardNumberValidator("xxxx-eeee-1111-zzzz"));
        codexsTesterAssertBool(false, cardNumberValidator("11e1-1111-1111-1e11"));
        codexsTesterAssertBool(true, cardNumberValidator("1111-1111-1111-1111"));
        codexsTesterAssertBool(true, cardNumberValidator("4544-8909-7865-6768"));
    }

}
