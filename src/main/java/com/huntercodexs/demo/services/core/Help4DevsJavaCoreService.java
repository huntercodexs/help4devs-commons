package com.huntercodexs.demo.services.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsJavaCoreService {

    public static int recursiveSum(Integer[] array, int pos, int total) {
        if (pos >= array.length) {
            return total;
        }
        return recursiveSum(array, pos+1, total+array[pos]);
    }

    //replace
    //replaceFirst
    //replaceAll

}
