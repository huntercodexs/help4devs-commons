package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.huntercodexs.demo.services.Help4DevsFileHandlerService.binFile;
import static com.huntercodexs.demo.services.Help4DevsPdfBoxService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsPdfBoxUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void pdfCreateTest() throws IOException {
        pdfCreate(
                binFile("./src/test/resources/help4devs/files/txt/file.txt"),
                "./src/test/resources/help4devs/files/pdf/hello-world-small.pdf",
                "small",
                null);
    }

    @Test
    public void pdfCreateUsingPasswordTest() throws IOException {
        pdfCreate(binFile(
                        "./src/test/resources/help4devs/files/txt/file.txt"),
                "./src/test/resources/help4devs/files/pdf/hello-world-small-password.pdf",
                "small",
                "password");

        pdfCreate(binFile(
                        "./src/test/resources/help4devs/files/txt/file.txt"),
                "./src/test/resources/help4devs/files/pdf/hello-world-normal-password.pdf",
                "normal",
                "password");

        pdfCreate(binFile(
                        "./src/test/resources/help4devs/files/txt/file.txt"),
                "./src/test/resources/help4devs/files/pdf/hello-world-large-password.pdf",
                "large",
                "password");
    }

    @Test
    public void pdfFromImageTest() throws IOException {
        pdfFromImage(
                "./src/test/resources/help4devs/images/ads/img.png",
                "./src/test/resources/help4devs/files/pdf/pdf-from-image.pdf",
                null);
    }

    @Test
    public void pdfFromImageUsingPasswordTest() throws IOException {
        pdfFromImage(
                "./src/test/resources/help4devs/images/ads/img.png",
                "./src/test/resources/help4devs/files/pdf/pdf-from-image-password.pdf",
                "password");
    }

    @Test
    public void pdfFromDocTest() throws IOException, ExecutionException, InterruptedException {
        pdfFromDoc(
                "./src/test/resources/help4devs/files/doc/file.doc",
                "./src/test/resources/help4devs/files/pdf/hello-world-doc.pdf");
    }

    @Test
    public void pdfReaderTest() {
        stdout(pdfReader("./src/test/resources/help4devs/files/pdf/hello-world-small.pdf", 1));
    }

    @Test
    public void pdfDetailsTest() {
        PdfBoxDetails details = pdfDetails("./src/test/resources/help4devs/files/pdf/hello-world-small.pdf");
    }

    @Test
    public void pdfCopyTest() {
        pdfCopy(
                "./src/test/resources/help4devs/files/pdf/hello-world-small.pdf",
                "./src/test/resources/help4devs/files/pdf/hello-world-small-copy.pdf");
    }

    @Test
    public void pdfProtectTest() throws IOException {
        pdfProtect(
                "./src/test/resources/help4devs/files/pdf/hello-world-to-crypt.pdf",
                "password");
    }

    @Test
    public void pdfUnprotectTest() throws IOException {
        pdfUnprotect(
                "./src/test/resources/help4devs/files/pdf/hello-world-to-crypt-enc.pdf",
                "./src/test/resources/help4devs/files/pdf/hello-world-decrypted.pdf",
                "password");
    }
}
