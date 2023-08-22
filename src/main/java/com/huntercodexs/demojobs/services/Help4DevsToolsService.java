package com.huntercodexs.demojobs.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Log4j2
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

}
