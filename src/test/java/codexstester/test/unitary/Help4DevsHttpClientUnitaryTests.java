package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.http.Help4DevsHttpClientService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperGuideGenerator;
import static codexstester.engine.util.CodexsHelperTests.codexsHelperStringToJsonSimple;

public class Help4DevsHttpClientUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsHttpClientService help4DevsHttpClientService;

    @Test
    public void httpGetTest() throws Exception {

        //Z2h$!@#wX1ZxNXU0SXRYM0dab01COTJnVDd0Y05jWFhxeWltMTBBRzBjRg==
        help4DevsHttpClientService.setMakeLog(true);
        help4DevsHttpClientService.setHeaderList("Authorization", "Bearer 89237128931289371289372183927189");
        help4DevsHttpClientService.setHeaderList("X-Api-Key", "09x890x8x908x9x08");
        help4DevsHttpClientService.setUrl("https://viacep.com.br/ws/12090002/json/");
        help4DevsHttpClientService.setTrack(codexsHelperGuideGenerator(null));
        help4DevsHttpClientService.setBodyRequest("");
        help4DevsHttpClientService.setBodyRequestType(String.class);
        help4DevsHttpClientService.setHttpMethod().httpGet();
        
        ResponseEntity<?> response = help4DevsHttpClientService.request();

        JSONObject jsonResponse = codexsHelperStringToJsonSimple(String.valueOf(response.getBody()));

        codexsTesterCompareJsonFormat(
                expectedJsonPostalCode2DataTree(),
                jsonResponse,
                true,
                "none",
                true);
    }

    @Test
    public void httpPostTest() throws Exception {

        /*JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("cpf", randomCpf());
        bodyRequest.put("serialNumber", randomNumber(7));
        bodyRequest.put("tcn", "");
        bodyRequest.put("webhookUrl", "");

        String url = "";
        String auth = "";
        String apiKey = "TEST09x890x8x908x9x08";

        help4DevsHttpClientService.setMakeLog(true);
        help4DevsHttpClientService.setHeaderList("Authorization", auth);
        help4DevsHttpClientService.setHeaderList("X-Api-Key", apiKey);
        help4DevsHttpClientService.setUrl(url);
        help4DevsHttpClientService.setTrack(codexsHelperGuideGenerator(null));
        help4DevsHttpClientService.setBodyRequest(bodyRequest);
        help4DevsHttpClientService.setBodyRequestType(JSONObject.class);
        help4DevsHttpClientService.setHttpMethod().httpPost();

        ResponseEntity<?> response = help4DevsHttpClientService.request();

        JSONObject jsonResponse = codexsHelperStringToJsonSimple(String.valueOf(response.getBody()));

        codexsTesterCompareJsonFormat(
                expectedJsonSerproDataTree(),
                jsonResponse,
                false,
                "none",
                true);*/
    }

    @Test
    public void httpDeleteTest() throws Exception {

        /*String url = "";
        String auth = "";
        String apiKey = "TEST09x890x8x908x9x08";

        help4DevsHttpClientService.setMakeLog(true);
        help4DevsHttpClientService.setHeaderList("Authorization", auth);
        help4DevsHttpClientService.setHeaderList("X-Api-Key", apiKey);
        help4DevsHttpClientService.setUrl(url);
        help4DevsHttpClientService.setTrack(codexsHelperGuideGenerator(null));
        help4DevsHttpClientService.setBodyRequest(null);
        help4DevsHttpClientService.setBodyRequestType(JSONObject.class);
        help4DevsHttpClientService.setHttpMethod().httpDelete();

        ResponseEntity<?> response = help4DevsHttpClientService.request();

        JSONObject jsonResponse = codexsHelperStringToJsonSimple(String.valueOf(response.getBody()));
        codexsTesterAssertExact("", jsonResponse.toJSONString());*/
    }

    @Test
    public void httpPutTest() throws Exception {

        /*String url = "";
        String auth = "";
        String apiKey = "TEST09x890x8x908x9x08";

        help4DevsHttpClientService.setMakeLog(true);
        help4DevsHttpClientService.setHeaderList("Authorization", auth);
        help4DevsHttpClientService.setHeaderList("X-Api-Key", apiKey);
        help4DevsHttpClientService.setUrl(url);
        help4DevsHttpClientService.setTrack(codexsHelperGuideGenerator(null));
        help4DevsHttpClientService.setBodyRequest(null);
        help4DevsHttpClientService.setBodyRequestType(JSONObject.class);
        help4DevsHttpClientService.setHttpMethod().httpPut();

        ResponseEntity<?> response = help4DevsHttpClientService.request();

        JSONObject jsonResponse = codexsHelperStringToJsonSimple(String.valueOf(response.getBody()));
        codexsTesterAssertExact("", jsonResponse.toJSONString());*/
    }

    @Test
    public void httpPatchTest() throws Exception {

        /*String url = "";
        String auth = "";
        String apiKey = "TEST09x890x8x908x9x08";

        help4DevsHttpClientService.setMakeLog(true);
        help4DevsHttpClientService.setHeaderList("Authorization", auth);
        help4DevsHttpClientService.setHeaderList("X-Api-Key", apiKey);
        help4DevsHttpClientService.setUrl(url);
        help4DevsHttpClientService.setTrack(codexsHelperGuideGenerator(null));
        help4DevsHttpClientService.setBodyRequest(null);
        help4DevsHttpClientService.setBodyRequestType(JSONObject.class);
        help4DevsHttpClientService.setHttpMethod().httpPatch();

        ResponseEntity<?> response = help4DevsHttpClientService.request();

        JSONObject jsonResponse = codexsHelperStringToJsonSimple(String.valueOf(response.getBody()));
        codexsTesterAssertExact("", jsonResponse.toJSONString());*/
    }

}
