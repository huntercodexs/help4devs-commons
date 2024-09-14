package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.*;
import static com.huntercodexs.demo.services.data.Help4DevsDataRandomService.randomCardNumber;

public class Help4DevsStringUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void countStringTest() {

        String data = "Topology: Quad Core model: Intel Core i5-9300H bits: 64 type: MT MCP " +
                "arch: Kaby Lake rev: D L2 cache: 8192 KiB flags: avx avx2 lm nx pae sse sse2 sse3 sse4_1 sse4_2 ssse3 vmx " +
                "bogomips: 38400 Speed: 4000 MHz min/max: 800/4100 MHz " +
                "Core speeds (MHz): 1: 4000 2: 4000 3: 4000 4: 4000 5: 4020 6: 4000 7: 4020 8: 4000";

        int result = stringCounter(data, "[1-9]: ([0-9]{4})");
        codexsTesterAssertInt(8, result);

        result = stringCounter(data, "4020");
        codexsTesterAssertInt(2, result);

    }

    @Test
    public void repeatTest() {
        codexsTesterAssertText("*****", repeat("*", 5));
        codexsTesterAssertText("++++++++++", repeat("+", 10));
        codexsTesterAssertText("XYZXYZXYZ", repeat("XYZ", 3));
    }

    @Test
    public void reverseTest() {
        codexsTesterAssertExact("0987654321", reverse("1234567890"));
        codexsTesterAssertExact("JIHGFEDCBA", reverse("ABCDEFGHIJ"));
    }

    @Test
    public void queryStringBuilderTest() {
        String result = queryStringBuilder("[ {age: 40, gender: female},{age: 30, gender: female}]");
        codexsTesterAssertExact("age=40&gender=female&age=30&gender=female", result);

        result = queryStringBuilder("[{age: 40, gender: female}]");
        codexsTesterAssertExact("age=40&gender=female", result);

        result = queryStringBuilder("{age: 40, gender: female}");
        codexsTesterAssertExact("age=40&gender=female", result);

        result = queryStringBuilder("{'age': '40', 'gender': 'female'}");
        codexsTesterAssertExact("age=40&gender=female", result);

        result = queryStringBuilder("{\"age\": \"40\", \"gender\": \"female\"}");
        codexsTesterAssertExact("age=40&gender=female", result);
    }

    @Test
    public void getDataFromQueryStringTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        Object result = getDataFromQueryString(queryString, "age");
        codexsTesterAssertExact("40", result.toString());
    }

    @Test
    public void queryStringToJsonTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        JSONObject result = queryStringToJson(queryString);
        codexsTesterAssertExact(
                "{\"gender\":\"female\",\"age\":\"40\"}",
                result.toJSONString());
    }

    @Test
    public void stringToJsonTest() {
        JSONObject result = stringToJson("{\"age\": \"40\", \"gender\": \"female\"}");
        codexsTesterAssertExact(
                "{\"gender\":\"female\",\"age\":\"40\"}",
                result.toJSONString());
    }

    @Test
    public void sanitizeAsciiCaseSensitiveTest() {
        String result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", "upper");
        codexsTesterAssertExact("TESTE COM ACENTUACAO E INEVITAL !", result);

        result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", "lower");
        codexsTesterAssertExact("teste com acentuacao e inevital !", result);

        result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", null);
        codexsTesterAssertExact("Teste com acentuacao e inevital !", result);

    }

    @Test
    public void sanitizeAsciiTest() {
        String result = sanitizeAscii("Teste com acentuação é inevital, pois acontece mesmo.");
        codexsTesterAssertExact("Teste com acentuacao e inevital, pois acontece mesmo.", result);
    }

    @Test
    public void queryExtractorTest() {
        codexsTesterAssertRegExp(
                "[0-9]{4}",
                queryExtractor(randomCardNumber("-"), 15, 19));
    }

    @Test
    public void replaceIndexingTest() {

        Help4DevsStringHandlerService stringHandler = new Help4DevsStringHandlerService();

        String result = stringHandler.replaceIndexing(
                "t: testA v: valueA v: valueB t: testB",
                "t: ",
                "type",
                ": ",
                true);

        result = stringHandler.replaceIndexing(
                result,
                "v: ",
                "version",
                ": ",
                true);

        codexsTesterAssertExact("type_0: testA version_2: valueA version_3: valueB type_1: testB", result);

    }

    @Test
    public void stringExtractorTest() {
        String source = "Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz";
        String pattern = "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)";
        String replacer = "model:$1$2";
        String clear = "model";
        codexsTesterAssertExact("i5-9300H", stringExtractor(source, clear, pattern, replacer, 1));

        source = "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard";
        clear = "source";
        pattern = "(source: [/-_.0-9a-zA-Z]+)( description: )";
        replacer = "$1";
        codexsTesterAssertExact("/dev/input/event4", stringExtractor(source, clear, pattern, replacer, 1));

        source = "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard";
        clear = "description";
        pattern = "(description: [-_.0-9a-zA-Z]+)";
        replacer = "$1";
        codexsTesterAssertExact("AT-Translated-Set-2-keyboard", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        clear = "description";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "$1";
        codexsTesterAssertExact("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        clear = "DESCRIPTION";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "DESCRIPTION: $1";
        codexsTesterAssertExact("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "description:$1";
        clear = "description";

        codexsTesterAssertExact("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "Topology: Quad Core model: Intel Core i5-9300H bits: 64 type: MT MCP arch: Kaby Lake " +
                "rev: D L2 cache: 8192 KiB flags: avx avx2 lm nx pae sse sse2 sse3 sse4_1 sse4_2 ssse3 vmx " +
                "bogomips: 38400 Speed: 4000 MHz min/max: 800/4100 MHz " +
                "Core speeds (MHz): 1: 4000 2: 4000 3: 4000 4: 4000 5: 4000 6: 4000 7: 4000 8: 4000";
        pattern = "([1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4})";
        replacer = "$1";
        clear = "";

        codexsTesterAssertExact(
                "1 4000 2 4000 3 4000 4 4000 5 4000 6 4000 7 4000 8 4000",
                stringExtractor(source, clear, pattern, replacer, 1));
    }

    @Test
    public void extractByPatternTest() {

        String input = "Type: Laptop";
        String result = extractByPattern(input, "Type", null);

        codexsTesterAssertExact("Laptop", result);

        input = "Type: \"Laptop\"";
        result = extractByPattern(input, "Type", "\"");

        codexsTesterAssertExact("Laptop", result);

        input = "product: Nitro AN517-51 version: 1.33.3";
        result = extractByPattern(input, "product", " ");

        codexsTesterAssertExact("Nitro AN517-51", result);

        input = "product: Nitro AN517-51 v: 1.33.3";
        result = extractByPattern(input, "v", "");

        codexsTesterAssertExact("1.33.3", result);

        input = "RAM: total: 23.31 GiB used: 6.97 GiB (29.9%) RAM Report: permissions: Unable to run dmidecode. Root privileges required.";
        result = extractByPattern(input, "total", " ");

        codexsTesterAssertExact("23.31 GiB", result);

        result = extractByPattern(input, "used", " ");

        codexsTesterAssertExact("6.97 GiB (29.9%) RAM", result);

    }

    @Test
    public void stringListTest() {

        List<String> list = Arrays. asList(
                "data to remove: Intel(R) Core(TM)",
                "data to remove: Intel(R) Core(TM)",
                "data to remove: Intel(R) Core(TM)"
        );

        String result = stringList(list, "data to remove: ");

        codexsTesterAssertExact(result, "Intel(R) Core(TM),Intel(R) Core(TM),Intel(R) Core(TM)");

    }

    @Test
    public void listExtractorTest() {

        List<String> source = Arrays. asList(
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz",
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4001 MHz",
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4002 MHz"
        );

        String pattern = "([0-9]+) (MHz)";
        String replacer = "speedCore:$1 $2";
        String detail = "speedCore";

        String result = listExtractor(source, detail, "data to remove: ", pattern, replacer);

        codexsTesterAssertExact(result, "4000 MHz,4001 MHz,4002 MHz");

    }

    @Test
    public void listClearTest() {

        List<String> list = new ArrayList<>();
        list.add("type: keyboard source: type: keyboard source: /dev/input/event9 description: MosArt-Wireless-Keyboard/Mouse");
        list.add("type: keyboard source: type: keyboard source: /dev/input/event4 description: AT-Translated-Set-2-keyboard");

        List<String> result = listClear(
                list,
                "type: keyboard source: type: keyboard source: ",
                "source: ");

        for (String res : result) {
            System.out.println(res);
        }

    }

    @Test
    public void listHasMapTest() {

        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        //0[
        //  {k:v},
        //  {k:v},
        //  {k:v},
        //  {k:v},
        //  {k:v}
        //],
        //1[
        //  {k:v},
        //  {k:v},
        //  {k:v},
        //  {k:v},
        //  {k:v}
        //]

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("k1", "v1");
        hashMap.put("k2", "v2");
        hashMap.put("k3", "v3");
        hashMapList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("k4", "v4");
        hashMap.put("k5", "v5");
        hashMap.put("k6", "v6");
        hashMapList.add(hashMap);

        System.out.println(hashMapList);

    }

    @Test
    public void alphaFieldPatternTest() {
        String item1 = "IF: enp7s0 state: up speed: 1000 Mbps duplex: full mac: <filter>";
        String result1 = alphaFieldPattern(item1, "IF", "");
        System.out.println("=====[result1]> " + result1);

        String item2 = "IF-ID-1: br-1222323251ed state: down mac: <filter>";
        String result2 = alphaFieldPattern(item2, "IF-ID-1", "");
        System.out.println("=====[result2]> " + result2);

        String item3 = "IF-ID-1: br-1222323251ed state: down mac: <filter>";
        String result3 = alphaFieldPattern(item3, "IF|IF-ID-1", "");
        System.out.println("=====[result3]> " + result3);
    }

}
