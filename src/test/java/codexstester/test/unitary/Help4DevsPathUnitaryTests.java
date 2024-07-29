package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsPathService.fileExtension;
import static com.huntercodexs.demo.services.Help4DevsPathService.sanitizePath;

public class Help4DevsPathUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        codexsTesterAssertText("/home/user/test/", result);

        result = sanitizePath("/home/user/test");
        codexsTesterAssertText("/home/user/test", result);

        result = sanitizePath("/home/user/test/text.txt");
        codexsTesterAssertText("/home/user/test/text.txt", result);

        result = sanitizePath("text.txt");
        codexsTesterAssertText("text.txt", result);

        result = sanitizePath("text");
        codexsTesterAssertText("text", result);
    }

    @Test
    public void splitTest() {

        String url = "https://s3-help4devs-test.s3.amazonaws.com/3333ef72-bdea-3333-b333-d4289cde590f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240713T153639Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=AKIA5FTZD4AW5RTSYCL3%2F20240713%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=b1f5fb862cf9265b57c5d70d1b90c0dd86c4169ba77d806bee149e4c1e467f98";
        String filename = url.split("\\?")[0].split("/")[3];

        System.out.println(filename);

    }

    @Test
    public void fileExtensionTest() {
        codexsTesterAssertExact("txt", fileExtension("file.txt"));
        codexsTesterAssertExact("txt", fileExtension("/home/user/file.txt"));
        codexsTesterAssertExact("txt", fileExtension("/home/user.name/file.txt"));
        codexsTesterAssertExact("txt", fileExtension("/home/user.name/file.name.txt"));
        codexsTesterAssertExact("txt", fileExtension("/home/user.name/file.name.txt"));
        codexsTesterAssertExact("txt", fileExtension("~/file.name.txt"));
    }
}
