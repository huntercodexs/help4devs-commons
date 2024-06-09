package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPDFService.*;

public class Help4DevsPDFUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void pdfCreateTest() throws IOException {
        pdfCreate(binFile(
                "src/test/resources/help4devs/files/txt/file.txt"),
                "./hello-world-small.pdf",
                "small");

        pdfCreate(binFile(
                "src/test/resources/help4devs/files/txt/file.txt"),
                "./hello-world-normal.pdf",
                "normal");

        pdfCreate(binFile(
                "src/test/resources/help4devs/files/txt/file.txt"),
                "./hello-world-large.pdf",
                "large");
    }

    @Test
    public void pdfFromImageTest() throws IOException {
        pdfFromImage(
                "src/test/resources/help4devs/images/ads/img.png",
                "./pdf-from-image.pdf");
    }

    @Test
    public void pdfFromDocTest() throws IOException, ExecutionException, InterruptedException {
        pdfFromDoc(
                "src/test/resources/help4devs/files/doc/file.doc",
                "./hello-world-doc.pdf");
    }

}
