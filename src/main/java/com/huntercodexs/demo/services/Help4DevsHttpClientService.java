package com.huntercodexs.demo.services;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.huntercodexs.demo.services.Help4DevsToolsService.infoLog;

@Slf4j
@Service
public class Help4DevsHttpClientService {

    @Setter
    private String url;
    @Setter
    private String track;
    @Setter
    private Object bodyRequest;
    @Setter
    private Class<?> bodyRequestType;
    @Setter
    private HttpMethod httpMethod;
    @Setter
    private boolean makeLog = false;

    private final List<LinkedHashMap<String, String>> headerList = new ArrayList<>();
    private static final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeadersCreate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (LinkedHashMap<String, String> header : this.headerList) {
            httpHeaders.add(header.get("header"), header.get("value"));
        }
        return httpHeaders;
    }

    private void log(HttpEntity<?> httpEntity) {
        if (this.makeLog) {
            if (this.track.isEmpty()) {
                this.track = "undefined";
            }
            infoLog("TRACK ["+this.track+"] HTTP[GET] " + this.url);
            infoLog("TRACK ["+this.track+"] HttpEntity " + httpEntity);
        }
    }

    private void requestBreak() {
        if (this.bodyRequestType != this.bodyRequest.getClass()) {
            throw new RuntimeException("Wrong parameters: BodyRequest is not a valid ClassName");
        }
    }

    public void setHeaderList(String headerName, String headerValue) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("header", headerName);
        linkedHashMap.put("value", headerValue);
        this.headerList.add(linkedHashMap);
    }

    public Help4DevsHttpClientService setHttpMethod() {
        return this;
    }

    public void httpGet() {
        this.httpMethod = HttpMethod.GET;
    }

    public void httpPost() {
        this.httpMethod = HttpMethod.POST;
    }

    public void httpDelete() {
        this.httpMethod = HttpMethod.DELETE;
    }

    public void httpPut() {
        this.httpMethod = HttpMethod.PUT;
    }

    public void httpPatch() {
        this.httpMethod = HttpMethod.PATCH;
    }

    public void httpOptions() {
        this.httpMethod = HttpMethod.OPTIONS;
    }

    public void httpHead() {
        this.httpMethod = HttpMethod.HEAD;
    }

    public ResponseEntity<?> request() {
        requestBreak();
        HttpEntity<?> httpEntity = new HttpEntity<>(this.bodyRequest, httpHeadersCreate());
        log(httpEntity);
        return restTemplate.exchange(this.url, this.httpMethod, httpEntity, this.bodyRequestType);
    }

}
