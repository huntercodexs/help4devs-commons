package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class Help4DevsHttpClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static HttpHeaders httpHeadersCreate(List<LinkedHashMap<String, String>> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (LinkedHashMap<String, String> header : headers) {
            httpHeaders.add(header.get("header"), header.get("value"));
        }
        return httpHeaders;
    }

}
