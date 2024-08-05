package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.annotation.Help4DevsAnnotationService;
import org.junit.Test;

public class Help4DevsAnnotationUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void useHelp4DevsLoggerAnnotationTest() {
        Help4DevsAnnotationService help4DevsAnnotationService = new Help4DevsAnnotationService();
        help4DevsAnnotationService.useHelp4DevsLoggerAnnotation();
    }

    @Test
    public void useHelp4DevsValidationAnnotationTest() {
        Help4DevsAnnotationService.Person person = new Help4DevsAnnotationService.Person();
        Help4DevsAnnotationService help4DevsAnnotationService = new Help4DevsAnnotationService();
        person.setName("ab");
        help4DevsAnnotationService.useHelp4DevsValidationAnnotation(person);
    }

}
