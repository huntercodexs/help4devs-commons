package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsBaseService.*;

public class Help4DevsBaseUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void paramsTests() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }

    @Test
    public void numberFormatterTest() {
        String result = numberFormatter(1, "%09d");
        System.out.println(result);
    }

    @Test
    public void stringFormatterTest() {
        String result1 = stringFormatter("XXX", "%10s");
        System.out.println("["+result1+"]");

        String result2 = stringFormatter("XXX", "%-10s");
        System.out.println("["+result2+"]");
    }

    @Test
    public void fillerFormatterTest() {
        String result1 = fillerFormatter("XXX", "F", "left", 20);
        System.out.println("["+result1+"]");

        String result2 = fillerFormatter("XXX", "F", "right", 20);
        System.out.println("["+result2+"]");

        String result3 = fillerFormatter("ZZZ", "8", "left", 20);
        System.out.println("["+result3+"]");

        String result4 = fillerFormatter("ZZZ", "8", "right", 20);
        System.out.println("["+result4+"]");

        String result5 = fillerFormatter("YYY", "A", "left", -20);
        System.out.println("["+result5+"]");

        String result6 = fillerFormatter("YYY", "A", "right", -20);
        System.out.println("["+result6+"]");
    }

    @Test
    public void rgFormatterTest() {
        System.out.println("RJ    > ["+ rgFormatter("231048415", "RJ", true)+"]");
        System.out.println("MG    > ["+ rgFormatter("2310484159", "MG", true)+"]");
        System.out.println("SP    > ["+ rgFormatter("2310484150", "SP", true)+"]");
        System.out.println("SSPSP > ["+ rgFormatter("2310484150", "SSPSP", true)+"]");
        System.out.println("SSPTO > ["+ rgFormatter("2310484150", "SSPTO", true)+"]");
        System.out.println("SSPSC > ["+ rgFormatter("2310484150", "SSPSC", true)+"]");
        System.out.println("CNH   > ["+ rgFormatter("2310484150", "CNH", true)+"]");
        System.out.println("DOC   > ["+ rgFormatter("2310484150", "DOC", true)+"]");

        System.out.println("RJ    > ["+ rgFormatter("231048415", "RJ", false)+"]");
        System.out.println("MG    > ["+ rgFormatter("2310484159", "MG", false)+"]");
        System.out.println("SP    > ["+ rgFormatter("2310484150", "SP", false)+"]");
        System.out.println("SSPSP > ["+ rgFormatter("2310484150", "SSPSP", false)+"]");
        System.out.println("SSPTO > ["+ rgFormatter("2310484150", "SSPTO", false)+"]");
        System.out.println("SSPSC > ["+ rgFormatter("2310484150", "SSPSC", false)+"]");
        System.out.println("CNH   > ["+ rgFormatter("2310484150", "CNH", false)+"]");
        System.out.println("DOC   > ["+ rgFormatter("2310484150", "DOC", false)+"]");
    }

}
