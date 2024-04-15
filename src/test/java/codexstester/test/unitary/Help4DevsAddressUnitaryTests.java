package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.enumerator.UfTable;
import org.junit.Test;

public class Help4DevsAddressUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void checkUfExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfExists("SP"));
        codexsTesterAssertBool(false, UfTable.checkUfExists("NN"));
    }

    @Test
    public void checkUfCodeExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfCodeExists("35"));
        codexsTesterAssertBool(false, UfTable.checkUfCodeExists("99"));
    }

    @Test
    public void checkUfNameExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkUfNameExists("São Paulo"));
        codexsTesterAssertBool(false, UfTable.checkUfNameExists("Sao Test"));
    }

    @Test
    public void checkRgSspExistsTest() {
        codexsTesterAssertBool(true, UfTable.checkRgSspExists("SSPSP"));
        codexsTesterAssertBool(false, UfTable.checkRgSspExists("SSPLL"));
    }

}





