package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;
import static com.huntercodexs.demo.services.crypto.Help4DevsCryptographyService.*;

public class Help4DevsCryptographyUnitaryTests extends Help4DevsBridgeTests {

    private final String secretKey = "F1F2F3F4F5F6F7F8F1F2F3F4F5F6F7F8";
    private final String salt = "1";

    @Test
    public void encryptAES256CBCTest() {
        String secretMessageClear = "This is a secret message, please don't break it !";
        String result = encryptAesCbc256(secretMessageClear, secretKey, salt);
        codexsHelperLogTerm("Encrypt AES256CBC", result, true);

        /*Result Samples*/
        //FW9UsuVZAky/yMkpX8JtkviJ+KQw/bfZC2Aces1HOVQ0BBlqY6rqdfXiFpNzlN6HlQkx7yRCGYRwqvhC52zf/FK/4gkG1ikk/zYyVKExKIM=
        //cSwH5G8anjQ56p28kEyWzufYF1wm6Xjk0NyzjpYjvuBiHcW2C19UhCD23f7qo0RwSfXFqfc6h66OCOnQ+sJCfbjZ/yU2msCKzcBVDeCOA/k=
        //EF8oNXRoH4WhHWfvayGa01X7RS7RjrNuwxfT95YIiKI56Cs5TLIHVhsUprpahuWRQ+KHpnLoroBqgPj3xRLusx18zIZaEJMCCFXCZ4IfrCw=
    }

    @Test
    public void decryptAES256CBCTest() {
        String secretMessageEncrypted = "i85+PwYB5tNlw38hFn2EQi4iTI38whZ/EBleGF/xvHj4Qih7/l6GA4k/en2BuPSPEFWJ4mXWT8tPSdb0XUWz2TTzCQMyO6+h7FeYtC0afAM=";
        String result = decryptAesCbc256(secretMessageEncrypted, secretKey, salt);
        codexsHelperLogTerm("Decrypt AES256CBC", result, true);
    }

    @Test
    public void encrypt3DesEdeTest() throws Exception {
        String secretMessage = "This is a secret message, please don't break it !";
        String secretKey = "F1F2F3F4F5F6F7F8F9F00000"; /*Must have 24 bytes*/

        String encrypted = encrypt3desEde(secretMessage, secretKey);

        codexsHelperLogTerm("SECRET MESSAGE CLEAR", secretMessage, true);
        codexsHelperLogTerm("ENCRYPTED 3DES-EDE" , encrypted, true);

        /*Result Samples*/
        //k4ksamLyPl+YhK0HafiFd2mbz0pt7DjvWqBX2ogtn6tPwpOfJ3m2IlmXsPSAwF+k12Poe0VUPQU=
    }

    @Test
    public void decrypt3DesEdeTest() throws Exception {
        String encrypted = "k4ksamLyPl+YhK0HafiFd2mbz0pt7DjvWqBX2ogtn6tPwpOfJ3m2IlmXsPSAwF+k12Poe0VUPQU=";
        String secretKey = "F1F2F3F4F5F6F7F8F9F00000"; /*Must have 24 bytes*/
        String decrypted = decrypt3DesEde(encrypted, secretKey);

        codexsHelperLogTerm("SECRET MESSAGE ENCRYPTED", encrypted, true);
        codexsHelperLogTerm("DECRYPTED 3DES-EDE" , decrypted, true);
    }

}
