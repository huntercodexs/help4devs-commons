package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsFileReaderService.getFileContentByMatch;

public class Help4DevsFileReaderUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void getFileContentTest() throws Exception {
        /*TIP: Edit the file ./src/test/resources/help4devs/file.txt and press [Ctrl+S] button*/
        String code = getFileContentByMatch("./src/test/resources/help4devs/file.txt", "[0-9]{6}",1500000000);
        System.out.println("Content: " + code);
    }

}





