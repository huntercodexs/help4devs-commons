package codexstester.test.unitary;

import codexstester.engine.util.CodexsHelperTests;
import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.enumerator.DataMasked;
import org.junit.Test;

import static com.huntercodexs.demo.enumerator.DataMasked.dataMasked;
import static com.huntercodexs.demo.services.Help4DevsMaskedService.cardNumberMasked;

public class Help4DevsMaskedUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void cardNumberMaskedTest() {
        String mask1 = cardNumberMasked("1234-3456-8982-8908", "*");
        CodexsHelperTests.codexsHelperLogTerm("MASK1", mask1, true);
        codexsTesterAssertText("1234-****-****-8908", mask1);

        String mask2 = cardNumberMasked("1234345689828908", "X");
        CodexsHelperTests.codexsHelperLogTerm("MASK2", mask2, true);
        codexsTesterAssertText("1234XXXXXXXX8908", mask2);

        String mask3 = cardNumberMasked("1234 3456 8982 8908", "@");
        CodexsHelperTests.codexsHelperLogTerm("MASK3", mask3, true);
        codexsTesterAssertText("1234 @@@@ @@@@ 8908", mask3);
    }

    @Test
    public void dataMaskedTest() {
        String cardMasked = dataMasked("1234-3456-8982-8908", "*", DataMasked.CARD_NUMBER_MASK);
        codexsTesterAssertText("1234-****-****-8908", cardMasked);

        String cpfMasked = dataMasked("897.654.058-23", "*", DataMasked.CPF_NUMBER_MASK);
        codexsTesterAssertText("***.***.058-23", cpfMasked);
    }

}





