package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCardNumber;
import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsStringUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void repeatTest() {
        codexsTesterAssertText("*****", repeat("*", 5));
        codexsTesterAssertText("++++++++++", repeat("+", 10));
        codexsTesterAssertText("XYZXYZXYZ", repeat("XYZ", 3));
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
    public void sanitizeAsciiTest() {
        String result = sanitizeAscii("Teste com acentuação é inevital !", "upper");
        codexsTesterAssertExact("TESTE COM ACENTUACAO E INEVITAL !", result);

        result = sanitizeAscii("Teste com acentuação é inevital !", "lower");
        codexsTesterAssertExact("teste com acentuacao e inevital !", result);

        result = sanitizeAscii("Teste com acentuação é inevital !", null);
        codexsTesterAssertExact("Teste com acentuacao e inevital !", result);
    }

    @Test
    public void queryExtractorTest() {
        codexsTesterAssertRegExp(
                "[0-9]{4}",
                queryExtractor(randomCardNumber("-"), 15, 19));
    }

}





