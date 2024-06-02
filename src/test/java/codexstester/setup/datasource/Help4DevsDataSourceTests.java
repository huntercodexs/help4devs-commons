package codexstester.setup.datasource;

import codexstester.engine.security.SecurityTests;
import net.minidev.json.JSONObject;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public class Help4DevsDataSourceTests extends SecurityTests {

    /*
     * DEFAULT ATTRIBUTES
     * Change it as needed
     * */

    public static final boolean ignoreOAuth2Tests = true;
    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";
    public static final String sampleOauth2Token = "d4cd86a0-809a-40aa-a590-ef68873dcd7b";

    /*
    * REST ENDPOINTS TESTS
    * */

    public static final String endpointGet = "/api/get";
    public static final String endpointPost = "/api/post";
    public static final String endpointPut = "/api/put";
    public static final String endpointDelete = "/api/delete";
    public static final String endpointPatch = "/api/patch";

    /*
     * HTTP HANDLER EXCEPTION ENDPOINTS TESTS
     * */
    public static final String endpointHandlerException = "/api/handler/exception";
    public static final String endpointHandlerArgumentException = "/api/handler/argument/exception";
    public static final String endpointHandlerMethodNotAllowedException = "/api/handler/method/exception";

    public static String dataSourceSampleResponse() {
        return "This is a expected sample response";
    }

    public static int dataSourceSampleSum(int a, int b) {
        return a + b;
    }

    public static JSONObject templateControllerDtoDataSource() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name2", "Joseph Smith");
        jsonObject.put("address", "Street 3001");
        jsonObject.put("phone", "123456789011");
        jsonObject.put("email", "email@email.com");
        return jsonObject;
    }

}
