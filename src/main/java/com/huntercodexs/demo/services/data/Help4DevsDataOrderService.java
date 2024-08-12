package com.huntercodexs.demo.services.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsDataOrderService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">linearSearch</h6>
     *
     * <p style="color: #CDCDCD">Find an word in one specific array</p>
     *
     * @param array (String[]: Array data to search)
     * @param wordSearch (String: Word to search in the array data)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean linearSearch(String[] array, String wordSearch) {
        for (String item : array) {
            if (item.equals(wordSearch)) {
                return true;
            }
        }
        return false;
    }

}
