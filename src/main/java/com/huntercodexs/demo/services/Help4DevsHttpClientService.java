package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class Help4DevsHttpClientService {

    private final RestTemplate restTemplate = new RestTemplate();

}