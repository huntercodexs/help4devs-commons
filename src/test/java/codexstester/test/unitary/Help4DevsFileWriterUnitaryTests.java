package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.file.Help4DevsFileWriterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Help4DevsFileWriterUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsFileWriterService help4DevsFileWriterService;

    @Test
    public void fileCreateTest() throws IOException {
        String textTest = "This is only a test!\n";

        help4DevsFileWriterService.fileDelete("/home/jereelton/tmp/java-tests/moved-file-tests.txt");
        help4DevsFileWriterService.folderCreate("/home/jereelton/tmp/java-tests");
        help4DevsFileWriterService.fileCreate("/home/jereelton/tmp/java-tests/buffered-file-tests.txt");
        help4DevsFileWriterService.fileWrite("HEADER: "+textTest);
        help4DevsFileWriterService.fileNewLine();
        help4DevsFileWriterService.fileWrite("1."+textTest);
        help4DevsFileWriterService.fileWrite("2."+textTest);
        help4DevsFileWriterService.fileWrite("3."+textTest);
        help4DevsFileWriterService.fileWrite("4."+textTest);
        help4DevsFileWriterService.fileNewLine();
        help4DevsFileWriterService.fileClose();
        help4DevsFileWriterService.fileMove(
                "/home/jereelton/tmp/java-tests/buffered-file-tests.txt",
                "/home/jereelton/tmp/java-tests/moved-file-tests.txt");
    }

}
