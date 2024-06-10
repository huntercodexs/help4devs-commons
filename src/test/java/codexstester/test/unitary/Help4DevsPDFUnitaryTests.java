package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPDFService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

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

    @Test
    public void pdfReaderTest() {
        stdout(pdfReader("./hello-world-small.pdf", 1));
    }

    @Test
    public void pdfDetailsTest() {
        PDFDetails details = pdfDetails("./hello-world-small.pdf");

        stdout(details.getId());
        stdout(details.getNumberOfPages());
        stdout(details.getWidth());
        stdout(details.getHeight());
        stdout(details.getVersion());
        stdout(details.getAuthor());
        stdout(details.getCreator());
        stdout(details.getTitle());
        stdout(details.getSubject());
        stdout(details.getTrapped());
        stdout(details.getKeywords());
        stdout(Arrays.toString(details.getLabels()));
        stdout(details.getPageSize());
        stdout(details.getFontName());
        stdout(details.getAssocFiles());
        stdout(details.getFirstPage());
        stdout(details.getLastPage());
    }

    @Test
    public void pdfCopyTest() {
        pdfCopy("./hello-world-small.pdf", "./hello-world-small-copy.pdf");
    }
}
