//package com.huntercodexs.demo.services.http;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
//import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
//import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
//import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
//import org.apache.hc.core5.ssl.SSLContexts;
//import org.apache.hc.core5.ssl.TrustStrategy;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.BufferingClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
///**
// * Dependencies
// * <dependency>
// *     <groupId>org.apache.httpcomponents.client5</groupId>
// *     <artifactId>httpclient5</artifactId>
// *     <version>5.2</version>
// * </dependency>
// * <dependency>
// *     <groupId>org.springframework</groupId>
// *     <artifactId>spring-web</artifactId>
// *     <version>6.1.16</version>
// * </dependency>
// * */
//@Slf4j
//@Service
//public class Help4DevsRestClient {
//
//    @Value("${rest-client.proxy.ssl.verify:true}")
//    private boolean sslVerify;
//
//    private HttpMethod httpMethod;
//    private String url;
//    private Object bodyRequest;
//    private Class<?> bodyRequestType;
//
//    private final List<LinkedHashMap<String, String>> headerList = new ArrayList<>();
//    private RestTemplate restTemplate = new RestTemplate();
//
//    public Help4DevsRestClient() {
//        if (!sslVerify) sslOff();
//    }
//
//    private SSLConnectionSocketFactory sslSocketFactory() throws GeneralSecurityException {
//        TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
//        var sslContext = SSLContexts
//                .custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//        return new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//    }
//
//    private CloseableHttpClient closeableHttpClient() throws GeneralSecurityException {
//        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
//                .setSSLSocketFactory(sslSocketFactory())
//                .build();
//
//        return HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .setRoutePlanner(null)
//                .build();
//    }
//
//    private RestTemplate restTemplateSslOff() throws GeneralSecurityException {
//        RestTemplateBuilder builder = new RestTemplateBuilder();
//        var restTemplate = builder.build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(closeableHttpClient());
//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
//        return restTemplate;
//    }
//
//    private HttpHeaders httpHeaders() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        for (LinkedHashMap<String, String> header : this.headerList) {
//            httpHeaders.add(header.get("header"), header.get("value"));
//        }
//        return httpHeaders;
//    }
//
//    private void checkRequest() {
//        if (this.bodyRequestType == null) this.bodyRequestType = String.class;
//        if (this.bodyRequestType != this.bodyRequest.getClass()) {
//            throw new RuntimeException("Wrong parameters: BodyRequest does not match the Class");
//        }
//    }
//
//    private ResponseEntity<?> request() {
//        checkRequest();
//        HttpEntity<?> httpEntity = new HttpEntity<>(this.bodyRequest, httpHeaders());
//        return this.restTemplate.exchange(this.url, this.httpMethod, httpEntity, this.bodyRequestType);
//    }
//
//    public Help4DevsRestClient sslOff() {
//        try {
//            this.restTemplate = restTemplateSslOff();
//        } catch (GeneralSecurityException ge) {
//            /*Use it just for development purposes*/
//            log.warn("Impossible to turn off the SSL Verification: {}", ge.getMessage());
//        }
//        return this;
//    }
//
//    public Help4DevsRestClient addHeader(String headerName, String headerValue) {
//        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("header", headerName);
//        linkedHashMap.put("value", headerValue);
//        this.headerList.add(linkedHashMap);
//        return this;
//    }
//
//    public Help4DevsRestClient setUrl(String url) {
//        this.url = url;
//        return this;
//    }
//
//    public Help4DevsRestClient setBodyRequest(Object bodyRequest) {
//        this.bodyRequest = bodyRequest;
//        return this;
//    }
//
//    public Help4DevsRestClient setBodyRequestType(Class<?> bodyRequestType) {
//        this.bodyRequestType = bodyRequestType;
//        return this;
//    }
//
//    public ResponseEntity<?> get() {
//        this.httpMethod = HttpMethod.GET;
//        return request();
//    }
//
//    public ResponseEntity<?> post() {
//        this.httpMethod = HttpMethod.POST;
//        return request();
//    }
//
//    public ResponseEntity<?> delete() {
//        this.httpMethod = HttpMethod.DELETE;
//        return request();
//    }
//
//    public ResponseEntity<?> put() {
//        this.httpMethod = HttpMethod.PUT;
//        return request();
//    }
//
//    public ResponseEntity<?> patch() {
//        this.httpMethod = HttpMethod.PATCH;
//        return request();
//    }
//
//    public ResponseEntity<?> options() {
//        this.httpMethod = HttpMethod.OPTIONS;
//        return request();
//    }
//
//    public ResponseEntity<?> head() {
//        this.httpMethod = HttpMethod.HEAD;
//        return request();
//    }
//
//}
