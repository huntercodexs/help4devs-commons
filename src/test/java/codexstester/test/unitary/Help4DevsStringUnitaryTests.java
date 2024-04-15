package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsStringHandlerService.*;

public class Help4DevsStringUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void queryStringBuilderTest() {
        String result = queryStringBuilder("[{age: 40, gender: female},{age: 30, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("[{age: 40, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{age: 40, gender: female}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{'age': '40', 'gender': 'female'}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void getDataFromQueryStringTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        String result = getDataFromQueryString(queryString, "age");
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void queryStringToJsonTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        JSONObject result = queryStringToJson(queryString);
        System.out.println("RESULT IS: " + result);
    }

    @Test
    public void stringToJsonTest() {
        JSONObject result = stringToJson("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }

}





