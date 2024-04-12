package com.huntercodexs.demo.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Setter
@Slf4j
@Service
public class Help4DevsOauth2Service {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Oauth2ResponseTokenDto {
        String access_token;
        String refresh_token;
        String scope;
        String token_type;
        String expires_in;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Oauth2ResponseTokenCheckDto {
        List<String> aud;
        String user_name;
        List<String> scope;
        boolean active;
        long exp;
        List<String> authorities;
        String client_id;
    }

    private String urlOauth2Token;
    private String urlOauth2CheckToken;
    private String clientId;
    private String secret;
    private String username;
    private String password;
    private String grant;
    private String additionalHeaderName;
    private String additionalHeaderValue;
    private String token;

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String wrongMessage = "Make sure you have instanced the class and set up the properties correctly";

    private static String base64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);
        return new String(base64InputBytes);
    }

    private String basicAuth() {
        if (this.secret.isEmpty() && this.clientId.isEmpty()) {
            throw new RuntimeException("Wrong instance [basic]: " + wrongMessage);
        }
        return "Basic " + base64(this.clientId+":"+this.secret);
    }

    private ResponseEntity<Oauth2ResponseTokenDto> tokenGenerate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuth());
        String credentials = "?username="+ this.username+"&password="+ this.password+"&grant_type="+ this.grant;
        HttpEntity<String> httpEntity = new HttpEntity<>(credentials, httpHeaders);

        System.out.println("TOKEN REQUEST: " + this.urlOauth2Token);
        System.out.println("HTTP-ENTITY: " + httpEntity);

        return restTemplate.postForEntity(this.urlOauth2Token + credentials, httpEntity, Oauth2ResponseTokenDto.class);
    }

    private ResponseEntity<Oauth2ResponseTokenCheckDto> tokenCheck() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuth());

        if (this.additionalHeaderName != null && !this.additionalHeaderName.isEmpty()) {
            if (this.additionalHeaderValue != null && !this.additionalHeaderValue.isEmpty()) {
                httpHeaders.set(this.additionalHeaderName, this.additionalHeaderValue);
            }
        }

        String queryString = "?token="+this.token.replaceFirst("Bearer ", "");
        HttpEntity<String> httpEntity = new HttpEntity<>(queryString, httpHeaders);

        System.out.println("TOKEN REQUEST: " + this.urlOauth2CheckToken);
        System.out.println("HTTP-ENTITY: " + httpEntity);

        return restTemplate.postForEntity(this.urlOauth2CheckToken + queryString, httpEntity, Oauth2ResponseTokenCheckDto.class);
    }

    private void checkTokenInstance(String operation) {
        if (operation.equals("generate")) {

            try {
                if (
                        this.urlOauth2Token.isEmpty() ||
                                this.username.isEmpty() ||
                                this.password.isEmpty() ||
                                this.grant.isEmpty() ||
                                this.secret.isEmpty() ||
                                this.clientId.isEmpty()
                ) {
                    throw new RuntimeException("Wrong instance [token]: " + wrongMessage);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Wrong instance [token][exception]: " + wrongMessage);
            }

        } else {

            try {
                if (this.urlOauth2CheckToken.isEmpty() || this.token.isEmpty()) {
                    throw new RuntimeException("Wrong instance [check]: " + wrongMessage);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Wrong instance [check][exception]: " + wrongMessage);
            }
        }
    }

    public String token() {
        checkTokenInstance("generate");
        ResponseEntity<Oauth2ResponseTokenDto> response = tokenGenerate();

        System.out.println("TOKEN [RESPONSE] " + response.getBody());

        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    public boolean check() {
        checkTokenInstance("check");
        ResponseEntity<Oauth2ResponseTokenCheckDto> response = tokenCheck();

        System.out.println("CHECK TOKEN [RESPONSE] " + response.getBody());

        if (response.getBody() != null) {
            return response.getBody().isActive();
        }
        return false;
    }

}
