package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.file.Help4DevsFileReaderService.awaitFileContent;

public class Help4DevsFileReaderUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void awaitFileContentTest() throws Exception {
        /*NOTE: Edit the file ./src/test/resources/help4devs/file.txt and press [Ctrl+S] button*/
        String code = awaitFileContent(
                "./src/test/resources/help4devs/file.txt",
                "[0-9]{6}",
                1500000000);
        codexsTesterAssertRegExp("[0-9]{6}", code);
    }

}
