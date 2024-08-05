package codexstester.test.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static codexstester.engine.util.CodexsParserJsonTests.codexsTesterParseNetJsonObject;
import static codexstester.setup.datasource.Help4DevsDataSourceTests.*;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.stdout;

public class Help4DevsInternalTests extends Help4DevsBridgeTests {

    /*
    * REST TESTS
    * */

    @Test
    public void getTest() throws Exception {
        whenRequestTest_RetrieveOK_StatusCode200_ByHttpMethodGET();
    }

    @Test
    public void postTest() throws Exception {
        whenRequestTest_RetrieveCreated_StatusCode201_ByHttpMethodPOST();
    }

    @Test
    public void putTest() throws Exception {
        whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPUT();
    }

    @Test
    public void deleteTest() throws Exception {
        whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodDELETE();
    }

    @Test
    public void patchTest() throws Exception {
        whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPATCH();
    }

    public void whenRequestTest_RetrieveOK_StatusCode200_ByHttpMethodGET()
            throws Exception
    {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUri(endpointGet);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        stdout(dispatcherResult);
        codexsTesterAssertExact("GET OK - 123456", dispatcherResult);
    }

    public void whenRequestTest_RetrieveCreated_StatusCode201_ByHttpMethodPOST()
            throws Exception
    {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(CREATED_201);
        requestDto.setUri(endpointPost);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        stdout(dispatcherResult);
        codexsTesterAssertExact("POST OK - 123456", dispatcherResult);
    }

    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPUT()
            throws Exception
    {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PUT);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPut);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        stdout(dispatcherResult);
        codexsTesterAssertExact("PUT OK - 123456", dispatcherResult);
    }

    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodDELETE()
            throws Exception
    {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointDelete);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        stdout(dispatcherResult);
        codexsTesterAssertExact("DELETE OK - 123456", dispatcherResult);
    }

    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPATCH()
            throws Exception
    {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PATCH);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPatch);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        stdout(dispatcherResult);
        codexsTesterAssertExact("PATCH OK - 123456", dispatcherResult);
    }

    /*
     * HTTP TESTS
     * */

    @Test
    public void httpHandlerExceptionTest() throws Exception {
        whenRequestHandlerException_RetrieveConflict_StatusCode409_ByHttpMethodPOST(
                false,
                "api");

        whenRequestHandlerException_RetrieveConflict_StatusCode409_ByHttpMethodPOST(
                false,
                "service");
    }

    @Test
    public void httpHandlerArgumentExceptionTest() throws Exception {
        whenRequestHandlerException_RetrieveConflict_StatusCode409_ByHttpMethodPOST(
                true,
                "api");
    }

    @Test
    public void httpMethodTest() throws Exception {
        whenRequestHandlerException_RetrieveMethodNotAllowed_StatusCode405_ByHttpMethodDELETE();
    }

    public void whenRequestHandlerException_RetrieveConflict_StatusCode409_ByHttpMethodPOST(
            boolean testArgument,
            String targetException
    )
            throws Exception
    {
        JSONObject dataRequest = templateControllerDtoDataSource();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(CONFLICT_409);
        requestDto.setUri(endpointHandlerException);
        requestDto.setId(targetException);

        if (testArgument) {
            dataRequest.remove("name");
            requestDto.setExpectedCode(BAD_REQUEST_400);
            requestDto.setUri(endpointHandlerArgumentException);
            requestDto.setId("");
        }

        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        stdout("=> HTTP RESPONSE REQUEST");
        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        JSONObject jsonResult = codexsTesterParseNetJsonObject(dispatcherResult, true);
        stdout("=> HTTP RESPONSE OK");

        if (!testArgument) {
            codexsTesterAssertExact("Unknown error", jsonResult.getAsString("message"));
        } else {
            codexsTesterAssertExact("Missing Data [name], please check the request", jsonResult.getAsString("message"));
        }
    }

    public void whenRequestHandlerException_RetrieveMethodNotAllowed_StatusCode405_ByHttpMethodDELETE()
            throws Exception
    {
        JSONObject dataRequest = templateControllerDtoDataSource();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(METHOD_NOT_ALLOWED_405);
        requestDto.setUri(endpointHandlerMethodNotAllowedException);
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        stdout("=> METHOD TEST REQUEST");
        String dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        JSONObject jsonResult = codexsTesterParseNetJsonObject(dispatcherResult, true);
        stdout("=> METHOD TEST OK");
        codexsTesterAssertExact("Request method 'DELETE' not supported", jsonResult.getAsString("message"));
    }
}
