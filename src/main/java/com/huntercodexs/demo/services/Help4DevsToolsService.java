package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
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

    public static String base64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);
        return new String(base64InputBytes);
    }

    public static String bcrypt(String data) {
        return BCrypt.hashpw(data, BCrypt.gensalt(12));
    }

    public static boolean bcrypt(String dataEncrypted, String dataToCompare) {
        return BCrypt.checkpw(dataToCompare, dataEncrypted);
    }

    public static String bcryptPassword(String data) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(data);
    }

    public static boolean bcryptPassword(String dataToCompare, String dataFromDatabase) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(dataToCompare, dataFromDatabase);
    }

    public static void infoLog(String... inputs) {
        if (log.isInfoEnabled()) {
            for (String text : inputs) {
                log.info(text);
            }
        }
    }

    public static void warnLog(String... inputs) {
        if (log.isWarnEnabled()) {
            for (String text : inputs) {
                log.warn(text);
            }
        }
    }

    public static void errLog(String... inputs) {
        for (String text : inputs) {
            log.error(text);
        }
    }

    public static void traceLog(String... inputs) {
        if (log.isTraceEnabled()) {
            for (String text : inputs) {
                log.trace(text);
            }
        }
    }

    public static void debugLog(String... inputs) {
        if (log.isDebugEnabled()) {
            for (String text : inputs) {
                log.debug(text);
            }
        }
    }

    public static void stdout(String... inputs) {
        for (String text : inputs) {
            System.out.println(text);
        }
    }
}
