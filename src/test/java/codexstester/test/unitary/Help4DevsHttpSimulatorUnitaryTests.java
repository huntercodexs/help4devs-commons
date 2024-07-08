package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import lombok.*;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.huntercodexs.demo.services.Help4DevsHttpSimulatorService.httpResponseErrorExtractor;
import static com.huntercodexs.demo.services.Help4DevsHttpSimulatorService.restResponseSimulate;

public class Help4DevsHttpSimulatorUnitaryTests extends Help4DevsBridgeTests {

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
        codexsTesterAssertText(
                "Help4DevsHttpSimulatorUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)",
                result.getResponseBodyAsString());
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
            codexsTesterAssertText(
                    "Help4DevsHttpSimulatorUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)",
                    he.getResponseBodyAsString());
        }
    }

    @Test
    public void httpResponseErrorExtractorTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        HttpClientErrorException response = restResponseSimulate(404, false, restResponseSimulateDto);
        String extract = httpResponseErrorExtractor(response);
        codexsTesterAssertText(
                "Body{Help4DevsHttpSimulatorUnitaryTests.RestResponseSimulateDto(code=404, message=Resource Not Found)} StatusText{404 Not Found} StatusCode{404 NOT_FOUND} Headers{[]}",
                extract);
    }

}
