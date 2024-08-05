package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.enumerator.TokenType;
import com.huntercodexs.demo.services.security.Help4DevsJwtService;
import com.huntercodexs.demo.services.security.Help4DevsOauth2Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperGuideGenerator;
import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public class Help4DevsAuthorizationUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsOauth2Service help4DevsOauth2Service;

    @Test
    public void oauth2TokenTest() {
        help4DevsOauth2Service.setUrlOauth2Token("http://localhost:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
        help4DevsOauth2Service.setUsername("OAUTH2DEMO_USER");
        help4DevsOauth2Service.setPassword("1234567890");
        help4DevsOauth2Service.setGrant("password");
        help4DevsOauth2Service.setClientId("client_id");
        help4DevsOauth2Service.setSecret("abfcc74b-07cd-425b-906b-abbcd8fa1bec");
        String token = help4DevsOauth2Service.token();
        System.out.println(token);
    }

    @Test
    public void oauth2TokenCheckTest() {
        String token = "8d09dad8-2a1a-4091-bfe7-0c59c20eafdf";
        help4DevsOauth2Service.setUrlOauth2CheckToken("http://localhost:32943/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
        help4DevsOauth2Service.setToken(token);
        help4DevsOauth2Service.setClientId("client_id");
        help4DevsOauth2Service.setSecret("abfcc74b-07cd-425b-906b-abbcd8fa1bec");
        codexsTesterAssertBool(true, help4DevsOauth2Service.check());
    }

    @Autowired
    Help4DevsJwtService help4DevsJwtService;

    @Test
    public void jwtCreateTest() {
        String identifier = codexsHelperGuideGenerator(null);
        String token = help4DevsJwtService.create(identifier);
        codexsHelperLogTerm("JWT TOKEN", token, true);
    }

    @Test
    public void jwtGetTest() {
        String fromHeader = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjk2MTI1Zi1jYmUxLTQyNmQtYTE0Ny1mNzBlMjZmN2E3ODciLCJuYmYiOjE3MTMyMDM0OTcsInRva2VuX3R5cGUiOiJUT0tFTl9UWVBFXzEiLCJleHAiOjE3MTMyMjg2OTd9.g8zIivkFzrooKRvR68JQGN3O_zci2CvSQowrTdqhfjLe5pc82-_Knq4rGh_P_NkIjP1LfimunCK6Ii-lhPOASC3nQvmMHzmOXo2uPHskuAKqasscEDa2mZWAbKBauP0E8kg9f_0EaaQ4UgpTYwOTpfbcGUIhFwIUTLez4wFoje0dYMZdK86EDkX3K6LbUDSKZ_3rkF5wR0svjdSrLIpjaENIkQaCn4itgNESQ-qeGNDTmN67A-XgTwSzpo-F3TkPKzLJ4RyNIuyqeiZ7oovUs_HOS7Q9O7yumhyJ7d8yAU0YWZmVBXpye35srezcHv7JaiZuONnsk6P6zgChN3hPlg";
        String tokenFromHeader = help4DevsJwtService.get(fromHeader);
        codexsHelperLogTerm("JWT TOKEN", tokenFromHeader, true);
    }

    @Test
    public void jwtTypeTest() {
        String fromHeader = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjk2MTI1Zi1jYmUxLTQyNmQtYTE0Ny1mNzBlMjZmN2E3ODciLCJuYmYiOjE3MTMyMDM0OTcsInRva2VuX3R5cGUiOiJUT0tFTl9UWVBFXzEiLCJleHAiOjE3MTMyMjg2OTd9.g8zIivkFzrooKRvR68JQGN3O_zci2CvSQowrTdqhfjLe5pc82-_Knq4rGh_P_NkIjP1LfimunCK6Ii-lhPOASC3nQvmMHzmOXo2uPHskuAKqasscEDa2mZWAbKBauP0E8kg9f_0EaaQ4UgpTYwOTpfbcGUIhFwIUTLez4wFoje0dYMZdK86EDkX3K6LbUDSKZ_3rkF5wR0svjdSrLIpjaENIkQaCn4itgNESQ-qeGNDTmN67A-XgTwSzpo-F3TkPKzLJ4RyNIuyqeiZ7oovUs_HOS7Q9O7yumhyJ7d8yAU0YWZmVBXpye35srezcHv7JaiZuONnsk6P6zgChN3hPlg";
        String tokenType = help4DevsJwtService.type(help4DevsJwtService.get(fromHeader));
        codexsHelperLogTerm("JWT TOKEN TYPE", tokenType, true);

    }

    @Test
    public void jwtSubjectTest() {
        String fromHeader = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjk2MTI1Zi1jYmUxLTQyNmQtYTE0Ny1mNzBlMjZmN2E3ODciLCJuYmYiOjE3MTMyMDM0OTcsInRva2VuX3R5cGUiOiJUT0tFTl9UWVBFXzEiLCJleHAiOjE3MTMyMjg2OTd9.g8zIivkFzrooKRvR68JQGN3O_zci2CvSQowrTdqhfjLe5pc82-_Knq4rGh_P_NkIjP1LfimunCK6Ii-lhPOASC3nQvmMHzmOXo2uPHskuAKqasscEDa2mZWAbKBauP0E8kg9f_0EaaQ4UgpTYwOTpfbcGUIhFwIUTLez4wFoje0dYMZdK86EDkX3K6LbUDSKZ_3rkF5wR0svjdSrLIpjaENIkQaCn4itgNESQ-qeGNDTmN67A-XgTwSzpo-F3TkPKzLJ4RyNIuyqeiZ7oovUs_HOS7Q9O7yumhyJ7d8yAU0YWZmVBXpye35srezcHv7JaiZuONnsk6P6zgChN3hPlg";
        String tokenSubject = help4DevsJwtService.subject(help4DevsJwtService.get(fromHeader));
        codexsHelperLogTerm("JWT TOKEN SUBJECT", tokenSubject, true);

    }

    @Test
    public void jwtIdentifierTest() {
        String fromHeader = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjk2MTI1Zi1jYmUxLTQyNmQtYTE0Ny1mNzBlMjZmN2E3ODciLCJuYmYiOjE3MTMyMDM0OTcsInRva2VuX3R5cGUiOiJUT0tFTl9UWVBFXzEiLCJleHAiOjE3MTMyMjg2OTd9.g8zIivkFzrooKRvR68JQGN3O_zci2CvSQowrTdqhfjLe5pc82-_Knq4rGh_P_NkIjP1LfimunCK6Ii-lhPOASC3nQvmMHzmOXo2uPHskuAKqasscEDa2mZWAbKBauP0E8kg9f_0EaaQ4UgpTYwOTpfbcGUIhFwIUTLez4wFoje0dYMZdK86EDkX3K6LbUDSKZ_3rkF5wR0svjdSrLIpjaENIkQaCn4itgNESQ-qeGNDTmN67A-XgTwSzpo-F3TkPKzLJ4RyNIuyqeiZ7oovUs_HOS7Q9O7yumhyJ7d8yAU0YWZmVBXpye35srezcHv7JaiZuONnsk6P6zgChN3hPlg";
        String tokenIdentifier = help4DevsJwtService.identifier(
                fromHeader,
                TokenType.TOKEN_TYPE_1,
                help4DevsJwtService.subject(help4DevsJwtService.get(fromHeader)));
        codexsHelperLogTerm("JWT TOKEN IDENTIFIER", tokenIdentifier, true);

    }

}
