package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsPathService {

    public static String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

}
