package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Slf4j
@Service
public class Help4DevsToolsService {

    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String guide(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static void infoLog(String... input) {
        if (log.isInfoEnabled()) {
            for (String text : input) {
                log.info(text);
            }
        }
    }

    public static void errLog(String... input) {
        for (String text : input) {
            log.error(text);
        }
    }

    public static void debugLog(String... input) {
        if (log.isDebugEnabled()) {
            for (String text : input) {
                log.debug(text);
            }
        }
    }

    public static void stdout(String... input) {
        for (String text : input) {
            System.out.println(text);
        }
    }
}
