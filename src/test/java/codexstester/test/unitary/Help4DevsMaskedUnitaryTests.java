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
    public void dataMaskedCardTest() {
        String cardMasked = dataMasked("1234-3456-8982-8908", "*", DataMasked.CARD_NUMBER_MASK);
        codexsTesterAssertText("1234-****-****-8908", cardMasked);
    }

    @Test
    public void dataMaskedCpfTest() {
        String cpfMasked = dataMasked("447.359.437-81", "*", DataMasked.CPF_NUMBER_MASK);
        codexsTesterAssertText("***.***.437-81", cpfMasked);
    }

    @Test
    public void dataMaskedCnpjTest() {
        String cnpjMasked = dataMasked("38577435000176", "*", DataMasked.CNPJ_NUMBER_MASK);
        codexsTesterAssertText("38*********176", cnpjMasked);

        cnpjMasked = dataMasked("38.577.435/0001-76", "*", DataMasked.CNPJ_NUMBER_MASK);
        codexsTesterAssertText("38.***.***/***1-76", cnpjMasked);
    }

    @Test
    public void dataMaskedRgTest() {
        String rgMasked = dataMasked("238764581", "*", DataMasked.RG_NUMBER_SSPSP_MASK);
        codexsTesterAssertText("23******1", rgMasked);

        rgMasked = dataMasked("23.876.458-1", "*", DataMasked.RG_NUMBER_SSPSP_MASK);
        codexsTesterAssertText("23.***.***-1", rgMasked);
    }

    @Test
    public void dataMaskedEmailTest() {
        String emailMasked = dataMasked("john.wizz@email.com", "*", DataMasked.EMAIL_ADDRESS_MASK);
        codexsTesterAssertText("jo*****z@email.com", emailMasked);

        emailMasked = dataMasked("john@email.com", "*", DataMasked.EMAIL_ADDRESS_MASK);
        codexsTesterAssertText("jo*****n@email.com", emailMasked);

        emailMasked = dataMasked("john1000@email.com", "*", DataMasked.EMAIL_ADDRESS_MASK);
        codexsTesterAssertText("jo*****0@email.com", emailMasked);
    }

    @Test
    public void dataMaskedPhoneTest() {
        String phoneMasked = dataMasked("5512982277653", "*", DataMasked.PHONE_NUMBER_MASK);
        codexsTesterAssertText("55129****7653", phoneMasked);

        phoneMasked = dataMasked("982277653", "*", DataMasked.PHONE_NUMBER_MASK);
        codexsTesterAssertText("9****7653", phoneMasked);

        phoneMasked = dataMasked("12982277653", "*", DataMasked.PHONE_NUMBER_MASK);
        codexsTesterAssertText("129****7653", phoneMasked);

        phoneMasked = dataMasked("82277653", "*", DataMasked.PHONE_NUMBER_MASK);
        codexsTesterAssertText("****7653", phoneMasked);
    }

    @Test
    public void dataMaskedGenericTest() {
        String genericMasked = dataMasked("only a test", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("o*********t", genericMasked);

        genericMasked = dataMasked("82394832948329", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("8************9", genericMasked);

        genericMasked = dataMasked("19/10/1988", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("1********8", genericMasked);

        genericMasked = dataMasked("new", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("n*w", genericMasked);

        genericMasked = dataMasked("InnovationDevs", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("I************s", genericMasked);

        genericMasked = dataMasked("Argument-1099", "*", DataMasked.GENERIC_MASK);
        codexsTesterAssertText("A***********9", genericMasked);
    }

}





