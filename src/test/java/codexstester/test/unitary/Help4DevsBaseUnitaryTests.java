package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void hardsysTest() {
        Field[] fields = Help4DevsHardSysResourcesDto.class.getDeclaredFields();
        int len = fields.length;
        String[] names = new String[len];

        for (int i = 0; i < len; i++) {
            names[i] = fields[i].getName();
        }

        System.out.println(Arrays.toString(names));
    }

    private String hardsysFind(String target) {

        Field[] fields = Help4DevsHardSysResourcesDto.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals(target)) {
                return target;
            }
        }

        throw new RuntimeException("ERROR: HARDSYS resource not found: " + target);

    }

    @Test
    public void hardsysFindTest() {
        System.out.println(hardsysFind("system"));
        System.out.println(hardsysFind("processor"));
    }

    @Test
    public void stringIteratorTest() {
        String json =
            "{" +
                "\"age\":43," +
                "\"name\":\"Diego Wiz \\\"Don\\\"\", " +
                "\"parental\":\"father\"," +
                "\"address\": {" +
                    "\"name\":\"USA\"," +
                    "\"street\":\"England Stanford\", " +
                    "\"number\":100," +
                    "\"states\":{" +
                        "\"first\":\"Kansas \\\"\\{ID\\}\\\"\"," +
                        "\"second\":\"New York \\\"\\[123,456,789\\]\\\"\"," +
                        "\"third\":\"North Carolina \\\"NC\\\"\"," +
                        "\"final\":[123,321,231,\"XYZ-1234\"]" +
                    "}" +
                "}," +
                "\"contacts\": [" +
                    "[" +
                        "12345678," +
                        "23432432," +
                        "[" +
                            "8909080," +
                            "\"XYZ43434RER\"," +
                            "\"XYZ8392018\"" +
                        "]" +
                    "], " +
                    "\"email@email.com\"," +
                    "{" +
                        "\"id\":902," +
                        "\"cities\": [\"Kansas 12\",\"New York\",\"Florida\"]" +
                    "}" +
                "]," +
                "\"zNumber\":789" +
            "}";

        String[] fields = new String[]{"contacts", "address", "parental", "name", "zNumber", "age"};
        String field = fields[3];
        boolean arrayTest = false;
        boolean jsonTest = false;

        json = json
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "")

                .replaceAll("\", \"", "\",\"")
                .replaceAll("\", \\[", "\",[")
                .replaceAll("\", \\{", "\",{")

                .replaceAll("\": \"", "\":\"")
                .replaceAll("\": \\[", "\":[")
                .replaceAll("\": \\{", "\":{")

                .replaceAll("], \"", "],\"")
                .replaceAll("], \\[", "],[")
                .replaceAll("], \\{", "],{")

                .replaceAll("}, \"", "},\"")
                .replaceAll("}, \\[", "},[")
                .replaceAll("}, \\{", "},{");

        //System.out.println(json);

        int pos = json.indexOf("\""+field+"\":");
        int len = ("\""+field+"\":").length();
        int tot = pos + len;

        //System.out.println(pos);
        //System.out.println(len);
        //System.out.println(tot);

        boolean isIntOn = false;
        boolean isStringOn = false;
        boolean isJsonOn = false;
        boolean isArrayOn = false;

        StringBuilder result = new StringBuilder();

        int jsonOpenCounter = 0;
        int arrayOpenCounter = 0;

        String prevChar = "";

        for (int i = tot; i < json.length(); i++) {
            String ch4r = String.valueOf(json.charAt(i));

            //Array
            if ((ch4r.equals("[") || isArrayOn) && arrayTest) {

                if (ch4r.equals("[") && !prevChar.equals("\\")) {
                    arrayOpenCounter += 1;
                } else if (ch4r.equals("]") && !prevChar.equals("\\")) {
                    arrayOpenCounter -= 1;
                }

                //isArrayOn = false
                if (arrayOpenCounter == 0 && isArrayOn) {
                    result.append(json.charAt(i));
                    break;
                }

                isArrayOn = true;
                result.append(json.charAt(i));
                prevChar = ch4r;
                continue;
            }
            //JSON
            if ((ch4r.equals("{") || isJsonOn) && jsonTest) {

                if (ch4r.equals("{") && !prevChar.equals("\\")) {
                    jsonOpenCounter += 1;
                } else if (ch4r.equals("}") && !prevChar.equals("\\")) {
                    jsonOpenCounter -= 1;
                }

                //isJsonOn = false
                if (jsonOpenCounter == 0 && isJsonOn) {
                    result.append(json.charAt(i));
                    break;
                }

                isJsonOn = true;
                result.append(json.charAt(i));
                prevChar = ch4r;
                continue;
            }
            //String
            if (ch4r.equals("\"") || isStringOn) {

                //isStringOn = false
                if (ch4r.equals("\"") && isStringOn && !prevChar.equals("\\")) {
                    break;
                }

                if (!ch4r.equals("\"") && !ch4r.equals("\\") || prevChar.equals("\\")) {
                    result.append(json.charAt(i));
                }

                isStringOn = true;
                prevChar = ch4r;
                continue;
            }
            //Integer
            if (ch4r.matches("^[0-9]$") || isIntOn) {

                //isIntOn = false
                if (!ch4r.matches("^[0-9]$")) {
                    break;
                }

                isIntOn = true;
                result.append(json.charAt(i));
                continue;
            }
        }

        System.out.println("Result: " + result);
    }
}
