package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        //replace
        str = "type: processor source: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz, 3960 MHz";
        str = str.replaceAll("(i[0-9]+|AMD|NVIDIA)([-0-9a-zA-Z]+)", "#<1#model: $1$2#1>#");
        String result = StringUtils.substringBetween(str, "#<1#", "#1>#");
        System.out.println(result);

    }

    @Test
    public void testing1Test() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("i"+i);
        }

        System.out.println(list);

        list.set(15, "changed");

        System.out.println(list);

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
