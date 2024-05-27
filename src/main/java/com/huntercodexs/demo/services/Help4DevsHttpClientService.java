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

/**
 * @implNote Create a instance of Help4DevsHttpClientService to writer a file,
 * the annotation @Autowired also can be used:
 * <br /><br />use: <br />@Autowired
 *     <br />Help4DevsHttpClientService help4DevsHttpClientService;
 * <br /><br />or:
 *      <br />Help4DevsHttpClientService help4DevsHttpClientService = new Help4DevsHttpClientService()
 *
 * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
 * @author huntercodexs (powered by jereelton-devel)
 * */
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

    /**
     * @param headerName (String: The field name)
     * @param headerValue (String: The field value)
     * @implNote Set the header names and values to HTTP Requests
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void setHeaderList(String headerName, String headerValue) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("header", headerName);
        linkedHashMap.put("value", headerValue);
        this.headerList.add(linkedHashMap);
    }

    /**
     * @return Help4DevsHttpClientService (this)
     * @implNote Call the http method setter and get the current instance of this class where its possible
     * to prepare a specific HTTP REQUEST METHOD and finally call the request method.
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Help4DevsHttpClientService setHttpMethod() {
        return this;
    }

    /**
     * @implNote HTTP METHOD GET
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpGet() {
        this.httpMethod = HttpMethod.GET;
    }

    /**
     * @implNote HTTP METHOD POST
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPost() {
        this.httpMethod = HttpMethod.POST;
    }

    /**
     * @implNote HTTP METHOD DELETE
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpDelete() {
        this.httpMethod = HttpMethod.DELETE;
    }

    /**
     * @implNote HTTP METHOD PUT
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPut() {
        this.httpMethod = HttpMethod.PUT;
    }

    /**
     * @implNote HTTP METHOD PATCH
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPatch() {
        this.httpMethod = HttpMethod.PATCH;
    }

    /**
     * @implNote HTTP METHOD OPTIONS
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpOptions() {
        this.httpMethod = HttpMethod.OPTIONS;
    }

    /**
     * @implNote HTTP METHOD HEAD
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpHead() {
        this.httpMethod = HttpMethod.HEAD;
    }

    /**
     * @return ResponseEntity
     * @implNote Execute the HTTP Request previously configured in the current instance
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public ResponseEntity<?> request() {
        requestBreak();
        HttpEntity<?> httpEntity = new HttpEntity<>(this.bodyRequest, httpHeadersCreate());
        log(httpEntity);
        return restTemplate.exchange(this.url, this.httpMethod, httpEntity, this.bodyRequestType);
    }

}
