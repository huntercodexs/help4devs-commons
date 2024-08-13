package com.huntercodexs.demo.services.challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class Help4DevsCoderPadService {

    public static int computeClosestToZero(int[] ts) {
        if (ts.length == 0) return 0;

        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();

        Arrays.stream(ts).sorted().forEach(value -> {
            if (value < 0) {
                neg.add(value);
            } else {
                pos.add(value);
            }
        });

        neg.sort(Comparator.reverseOrder());
        pos.sort(Comparator.naturalOrder());

        int minNegative = 0;
        int minPositive = 0;

        if (!neg.isEmpty()) {
            minNegative = neg.get(0);
        }

        if (!pos.isEmpty()) {
            minPositive = pos.get(0);
        }

        if (neg.isEmpty() && !pos.isEmpty()) {
            return minPositive;
        }

        if (!neg.isEmpty() && pos.isEmpty()) {
            return minNegative;
        }

        if (minPositive == 0 || minPositive == 1) {
            return minPositive;
        }

        if (Math.abs(minNegative) == minPositive || Math.abs(minNegative) > minPositive) {
            return minPositive;
        }

        return minNegative;
    }

     public static int greatValue(int value1, int value2, int value3) {

        if (value1 == value2 && value1 == value3) {
            return 0;
        }

        if (value1 > value2 && value1 > value3) {
            return 0;
        }

         if (value2 > value1 && value2 > value3) {
             return 1;
         }

         return 2;

    }

}
