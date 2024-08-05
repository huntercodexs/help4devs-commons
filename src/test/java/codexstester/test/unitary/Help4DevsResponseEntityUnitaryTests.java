package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import lombok.*;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.huntercodexs.demo.services.http.Help4DevsResponseEntityService.responseEntitySimulate;

public class Help4DevsResponseEntityUnitaryTests extends Help4DevsBridgeTests {

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
    public void responseEntitySimulateTest() {
        RestResponseSimulateDto restResponseSimulateDto = new RestResponseSimulateDto();
        restResponseSimulateDto.setCode("404");
        restResponseSimulateDto.setMessage("Resource Not Found");

        ResponseEntity<?> result = responseEntitySimulate(404, restResponseSimulateDto);
        RestResponseSimulateDto resultDto = (RestResponseSimulateDto) result.getBody();
        codexsTesterAssertNotNull(resultDto.getCode());
        codexsTesterAssertText("Resource Not Found", resultDto.getMessage());
    }

}
