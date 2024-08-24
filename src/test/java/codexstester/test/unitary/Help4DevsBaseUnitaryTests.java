package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.params;

public class Help4DevsBaseUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void anyTest() {
        String str = null;
        str = str + "Hello";
        System.out.println(str);

        //jsonFormatter (RFC8259)
        //Examples
        //{{ = 2 => }} = 2 OK
        //{{ = 2 => } = 1 ERROR
        //{{ = 2 => }}} = 2 ERROR
        System.out.println("{\n\t\"person\":{\n\t\t\"id\":1,\n\t\t\"name\":\"John Smith\"\n\t}\n}");
    }

    @Test
    public void paramsTest() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }

}
