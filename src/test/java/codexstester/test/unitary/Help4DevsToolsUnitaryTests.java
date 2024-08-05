package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.*;

public class Help4DevsToolsUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void md5Test() {
        String result = md5("data-to-md5");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void guideTest() {
        String result = guide(null);
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void base64Test() {
        codexsTesterAssertText(
                "Y2xpZW50X2lkOmFiZmNjNzRiLTA3Y2QtNDI1Yi05MDZiLWFiYmNkOGZhMWJlYw==",
                base64("client_id:abfcc74b-07cd-425b-906b-abbcd8fa1bec"));
    }

    @Test
    public void bcryptTest() {
        String result = bcrypt("password");
        codexsHelperLogTerm("BCRYPT", result, true);

        boolean match = bcrypt(result, "password");
        codexsHelperLogTerm("MATCH", match, true);
    }

    @Test
    public void bcryptPasswordTest() {
        String result = bcryptPassword("password");
        codexsHelperLogTerm("BCRYPT", result, true);

        boolean match = bcryptPassword("password", result);
        codexsHelperLogTerm("CHECK BCRYPT", match, true);
    }

    /**
     * @implNote Make sure the log42j.xml is correctly set to execute this test successfully
     * */
    @Test
    public void logLeveTest() {
        errLog("ERROR TEST"); /*[ERROR]*/
        warnLog("WARN TEST"); /*[ERROR, WARN]*/
        infoLog("INFO TEST"); /*[ERROR, INFO, WARN]*/
        debugLog("DEBUG TEST"); /*[ERROR, INFO, DEBUG, WARN]*/
        traceLog("TRACE TEST"); /*[ERROR, INFO, DEBUG, TRACE, WARN]*/
    }

    @Test
    public void infoLogTest() {
        infoLog("This is a infoLog", "This is a infoLog");
    }

    @Test
    public void errLogTest() {
        errLog("This is a errLog", "This is a errLog");
    }

    @Test
    public void debugLogTest() {
        debugLog("This is a debugLog", "This is a debugLog");
    }

    @Test
    public void stdoutLogTest() {
        stdout("This is a stdout", "This is a stdout");
    }

}
