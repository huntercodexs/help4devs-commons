package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsHttpClientService;
import lombok.*;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static codexstester.engine.util.CodexsHelperTests.*;
import static com.huntercodexs.demo.services.Help4DevsHttpService.httpResponseErrorExtractor;
import static com.huntercodexs.demo.services.Help4DevsHttpService.restResponseSimulate;
import static com.huntercodexs.demo.services.Help4DevsResponseEntityService.responseEntitySimulate;

public class Help4DevsHttpUnitaryTests extends Help4DevsBridgeTests {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestResponseSimulateDto {
        String code;
        String message;
    }

    @Test
    public void restResponseSimulateTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        HttpClientErrorException result = restResponseSimulate(404, false, restResponseSimulateDto);

        codexsTesterAssertText(String.valueOf(result.getStatusCode()), "404 NOT_FOUND");
        codexsTesterAssertText(result.getStatusText(), "404 Not Found");
        codexsTesterAssertText(String.valueOf(result.getRawStatusCode()), "404");
        codexsTesterAssertText(result.getResponseBodyAsString(), "Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)");
    }

    @Test
    public void restResponseSimulateThrowTest() {

        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        try {

            HttpClientErrorException result = restResponseSimulate(404, true, restResponseSimulateDto);
            System.out.println(result.getResponseBodyAsString());

        } catch (HttpClientErrorException | HttpServerErrorException he) {
            codexsTesterAssertText(String.valueOf(he.getStatusCode()), "404 NOT_FOUND");
            codexsTesterAssertText(he.getStatusText(), "404 Not Found");
            codexsTesterAssertText(String.valueOf(he.getRawStatusCode()), "404");
            codexsTesterAssertText(he.getResponseBodyAsString(), "Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)");
        }
    }

    @Test
    public void httpResponseErrorExtractorTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        HttpClientErrorException response = restResponseSimulate(404, false, restResponseSimulateDto);
        String extract = httpResponseErrorExtractor(response);
        codexsTesterAssertText(extract, "Body{Help4DevsUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)} StatusText{404 Not Found} StatusCode{404 NOT_FOUND} Headers{[]}");
    }

    @Test
    public void responseEntitySimulateTests() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        ResponseEntity<?> result = responseEntitySimulate(404, restResponseSimulateDto);
        RestResponseSimulateDto resultDto = (RestResponseSimulateDto) result.getBody();
        codexsTesterAssertNotNull(resultDto.getCode());
        codexsTesterAssertText("Resource Not Found", resultDto.getMessage());
    }

    private static List<LinkedHashMap<String, String>> getHeaders() {
        List<LinkedHashMap<String, String>> headerList = new ArrayList<>();

        LinkedHashMap<String, String> linkedHashMap1 = new LinkedHashMap<>();
        linkedHashMap1.put("header", "Authorization");
        linkedHashMap1.put("value", "Bearer 89237128931289371289372183927189");
        headerList.add(linkedHashMap1);

        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap2.put("header", "X-Api-Key");
        linkedHashMap2.put("value", "09x890x8x908x9x08");
        headerList.add(linkedHashMap2);
        return headerList;
    }

    @Test
    public void httpHeadersCreateTest() {
        List<LinkedHashMap<String, String>> headerList = getHeaders();

        HttpHeaders result = (HttpHeaders) codexsHelperToPrivateMethods(
                new Help4DevsHttpClientService(),
                "httpHeadersCreate",
                Collections.singletonList(headerList));

        codexsTesterAssertText("Bearer 89237128931289371289372183927189", result.get("Authorization").get(0));
        codexsTesterAssertText("09x890x8x908x9x08", result.get("X-Api-Key").get(0));

    }

    @Autowired
    Help4DevsHttpClientService help4DevsHttpClientService;

    @Test
    public void httpGetTest() throws Exception {

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

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("cpf", "34551505862");
        bodyRequest.put("serialNumber", "5445654");
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
                true);
    }

}





