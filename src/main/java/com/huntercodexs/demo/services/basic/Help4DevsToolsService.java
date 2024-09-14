package com.huntercodexs.demo.services.basic;

import com.huntercodexs.demo.enumerator.DataMasked;
import com.huntercodexs.demo.enumerator.TraceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.UUID;

import static com.huntercodexs.demo.enumerator.DataMasked.dataMasked;

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

    public static void stdout(Object... inputs) {
        for (Object input : inputs) {
            System.out.println(input);
        }
    }

    /**
     *
     * <h6 style="color: #FFFF00; font-size: 11px">trace</h6>
     *
     * <p style="color: #CDCDCD">This method is used to make a total trace and tracking in the idp
     * transaction flow where the result can be applied to information or error log level</p>
     *
     * @param track (String: Tracking log)
     * @param id (String: User or resource identifier)
     * @param message (String: Message to trace in log)
     * @param label (TraceType: label to stick the log detail and give an emphasis in the target message)
     * @param type (String: Type of log [info, error])
     * @return trace (String - trace detail)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String trace(String track, String id, String message, TraceType label, String type) {

        if (track == null || track.isEmpty()) track = "........-....-....-....-............";
        if (label == null) label = TraceType.UNKNOWN;
        if (type == null) type = "";

        String idMasked = dataMasked(id, "*", DataMasked.GENERIC_MASK);
        String trace = "TCN ["+track+"] ["+idMasked+"] ["+label.name().toUpperCase()+"]";

        if (type.equals("error")) {
            errLog(trace + " " + message);
        } else {
            infoLog(trace + " " + message);
        }
        return trace;
    }

}
