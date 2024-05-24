package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class Help4DevsBaseService {

    /**
     * @param jsonObjects (JSONObject: Sample)
     * @implNote Sample method to show how to work and use the grouped parameters
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }

}
