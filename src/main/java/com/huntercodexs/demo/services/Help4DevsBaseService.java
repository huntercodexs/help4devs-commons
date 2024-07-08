package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class Help4DevsBaseService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">params</h6>
     *
     * <p style="color: #CDCDCD">Sample method to show how to work and use the grouped parameters</p>
     *
     * @param jsonObjects (JSONObject: Sample)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }

}
