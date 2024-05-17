package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsDataRandomService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsDataRandomUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void randomNumberTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                codexsTesterAssertRegExp("[0-9]{1}", String.valueOf(randomNumber(1)));
                codexsTesterAssertRegExp("[0-9]{2}", String.valueOf(randomNumber(2)));
                codexsTesterAssertRegExp("[0-9]{3}", String.valueOf(randomNumber(3)));
                codexsTesterAssertRegExp("[0-9]{4}", String.valueOf(randomNumber(4)));
                codexsTesterAssertRegExp("[0-9]{5}", String.valueOf(randomNumber(5)));
                codexsTesterAssertRegExp("[0-9]{6}", String.valueOf(randomNumber(6)));
                codexsTesterAssertRegExp("[0-9]{7}", String.valueOf(randomNumber(7)));
                codexsTesterAssertRegExp("[0-9]{8}", String.valueOf(randomNumber(8)));
                codexsTesterAssertRegExp("[0-9]{9}", String.valueOf(randomNumber(9)));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCardNumberTest() {
        codexsTesterAssertRegExp("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}", randomCardNumber(" "));
        codexsTesterAssertRegExp("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}", randomCardNumber(" "));
        codexsTesterAssertRegExp("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}", randomCardNumber(" "));
        codexsTesterAssertRegExp("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}", randomCardNumber(" "));
    }

    @Test
    public void randomIdTest() {
        codexsTesterAssertRegExp("ID_[0-9]{6}", randomId("ID_"));
        codexsTesterAssertRegExp("ID_[0-9]{6}", randomId("ID_"));
        codexsTesterAssertRegExp("ID_[0-9]{6}", randomId("ID_"));
    }

    @Test
    public void randomGuidTest() {
        stdout(randomGuid());
    }

    @Test
    public void randomTokenTest() {
        stdout(randomToken(16));
        stdout(randomToken(32));
        stdout(randomToken(64));
        stdout(randomToken(128));
    }

    @Test
    public void randomJwtTest() {
        stdout(randomJwt());
    }

    @Test
    public void randomHashTest() {
        stdout(randomHash());
        stdout(randomHash());
        stdout(randomHash());
    }

    @Test
    public void randomBinaryTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                stdout(randomBinary(4));
                stdout(randomBinary(8));
                stdout(randomBinary(2));
                stdout(randomBinary(6));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCpfTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                stdout(randomCpf());
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCnpjTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                stdout(randomCnpj());
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}