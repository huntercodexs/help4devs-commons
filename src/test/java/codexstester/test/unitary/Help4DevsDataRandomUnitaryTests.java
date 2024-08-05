package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.data.Help4DevsDataRandomService.*;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.stdout;

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
    public void randomMoneyTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                stdout("REAL R$");
                codexsTesterAssertRegExp("R\\$ [0-9],00", randomMoney(1, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{2},00", randomMoney(2, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{3},00", randomMoney(3, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]\\.[0-9]{3},00", randomMoney(4, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{2}\\.[0-9]{3},00", randomMoney(5, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{3}\\.[0-9]{3},00", randomMoney(6, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]\\.[0-9]{3}\\.[0-9]{3},00", randomMoney(7, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{2}\\.[0-9]{3}\\.[0-9]{3},00", randomMoney(8, "real"));
                codexsTesterAssertRegExp("R\\$ [0-9]{3}\\.[0-9]{3}\\.[0-9]{3},00", randomMoney(9, "real"));
                stdout("DOLLAR $");
                codexsTesterAssertRegExp("\\$ [0-9]\\.00", randomMoney(1, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{2}\\.00", randomMoney(2, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{3}\\.00", randomMoney(3, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9],[0-9]{3}\\.00", randomMoney(4, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{2},[0-9]{3}\\.00", randomMoney(5, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{3},[0-9]{3}\\.00", randomMoney(6, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9],[0-9]{3},[0-9]{3}\\.00", randomMoney(7, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{2},[0-9]{3},[0-9]{3}\\.00", randomMoney(8, "dollar"));
                codexsTesterAssertRegExp("\\$ [0-9]{3},[0-9]{3},[0-9]{3}\\.00", randomMoney(9, "dollar"));
                stdout("EURO €");
                codexsTesterAssertRegExp("[0-9],00 EUR", randomMoney(1, "euro"));
                codexsTesterAssertRegExp("[0-9]{2},00 EUR", randomMoney(2, "euro"));
                codexsTesterAssertRegExp("[0-9]{3},00 EUR", randomMoney(3, "euro"));
                codexsTesterAssertRegExp("[0-9] [0-9]{3},00 EUR", randomMoney(4, "euro"));
                codexsTesterAssertRegExp("[0-9]{2} [0-9]{3},00 EUR", randomMoney(5, "euro"));
                codexsTesterAssertRegExp("[0-9]{3} [0-9]{3},00 EUR", randomMoney(6, "euro"));
                codexsTesterAssertRegExp("[0-9] [0-9]{3} [0-9]{3},00 EUR", randomMoney(7, "euro"));
                codexsTesterAssertRegExp("[0-9]{2} [0-9]{3} [0-9]{3},00 EUR", randomMoney(8, "euro"));
                codexsTesterAssertRegExp("[0-9]{3} [0-9]{3} [0-9]{3},00 EUR", randomMoney(9, "euro"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCentsTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                stdout(randomCents("dollar"));
                stdout(randomCents("euro"));
                stdout(randomCents( "real"));
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

    @Test
    public void randomDateTest() {
        stdout(randomDate());
    }

    @Test
    public void randomDateTimeTest() {
        stdout(randomDateTime());
    }
}
