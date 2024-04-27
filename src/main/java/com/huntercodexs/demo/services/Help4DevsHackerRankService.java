package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class Help4DevsHackerRankService {

    public static void plusMinus(List<Integer> arr) {

        int positive = 0;
        int negative = 0;
        int zero = 0;
        int arrSize = arr.size();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > 0) positive += 1;
            if (arr.get(i) < 0) negative += 1;
            if (arr.get(i) == 0) zero += 1;
        }

        float resultPositive = (float) positive / (float) arrSize;
        float resultNegative = (float) negative / (float) arrSize;
        float resultZeros = (float) zero / (float) arrSize;

        System.out.printf("%f\n", resultPositive);
        System.out.printf("%f\n", resultNegative);
        System.out.printf("%f\n", resultZeros);

    }

    public static void miniMaxSum(List<Integer> arr) {

        int arrSize = arr.size();
        long minSum = 0;
        long maxSum = 0;

        arr.sort(Comparator.naturalOrder());

        for (int i = 0; i < arrSize-1; i++) {
            minSum += arr.get(i);
            maxSum += arr.get(i+1);
        }

        System.out.println(minSum + " " + maxSum);
    }

    public static String timeConversion(String s) {

        String result = null;
        int militaryHour = 12;
        String[] matches = s.replaceAll("(PM|AM)$", "").split(":");

        if (s.matches("(.*)PM$")) {

            if (!matches[0].equals("12")) {
                militaryHour = Integer.parseInt(matches[0]) + 12;
            }
            result = militaryHour +":"+ matches[1] +":"+ matches[2];

        } else if (s.matches("(.*)AM")) {

            if (matches[0].equals("12")) {
                matches[0] = "00";
            }
            result = matches[0] +":"+ matches[1] +":"+ matches[2];
        }

        return result;
    }

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {

        List<Integer> matches = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        queries.forEach(query -> {

            hashMap.put(query, 0);

            for (int i = 0; i < strings.size(); i++) {

                if (query.equals(strings.get(i))) {
                    int counter = 0;
                    if (hashMap.get(query) != null) {
                        counter += hashMap.get(query);
                    }
                    hashMap.put(query, counter+1);
                }

            }

            matches.add(hashMap.get(query));

        });

        return matches;

    }

    public static int lonelyinteger(List<Integer> a) {

        int unique = 0;

        for (int i = 0; i < a.size(); i++) {
            unique =  unique ^ a.get(i);
        }

        return unique;

    }

    public static long flippingBits(long n) {

        String bits = String.format("%32s", Long.toBinaryString(n)).replace(" ", "0");
        bits = bits.replaceAll("1", "2");
        bits = bits.replaceAll("0", "1");
        bits = bits.replaceAll("2", "0");
        long res = Long.parseLong(bits, 2);

        //System.out.println(res);

        return res;

    }

    public static int diagonalDifference(List<List<Integer>> arr) {

        List<Integer> leftToRight = new ArrayList<>();
        List<Integer> rightToLeft = new ArrayList<>();

        int indexLeft = 0;
        int indexRight = arr.size() - 1;

        for (int i = 0; i < arr.size(); i++) {
            leftToRight.add(arr.get(i).get(indexLeft));
            indexLeft+=1;
            rightToLeft.add(arr.get(i).get(indexRight));
            indexRight-=1;
        }

        int leftRightSum = 0;
        int rightLeftSum = 0;

        for (int itemL : leftToRight) {
            leftRightSum += itemL;
        }

        for (int itemR : rightToLeft) {
            rightLeftSum += itemR;
        }

        int result = Math.abs(leftRightSum-rightLeftSum);

        System.out.println(result);

        return result;
    }

    public static List<Integer> countingSort(List<Integer> arr) {

        List<Integer> resultSort = new ArrayList<>();

        arr.sort(Comparator.naturalOrder());

        for (int i = 0; i < 100; i++) {
            int counter = 0;
            for(int num : arr) {
                if (num == i) {
                    counter+=1;
                }
            }
            resultSort.add(counter);
        }

        return resultSort;
    }

    public static boolean pangrams(String input, String language) {

        String[] englishAlphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        String pangram = input.toLowerCase();
        int alphabetSize = englishAlphabet.length;
        int pangramCounter = 0;

        for (String s : englishAlphabet) {
            if (pangram.contains(s)) {
                pangramCounter += 1;
            }
        }

        return pangramCounter == alphabetSize;

    }
}
