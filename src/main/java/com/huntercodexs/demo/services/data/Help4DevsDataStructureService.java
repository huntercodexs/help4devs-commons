package com.huntercodexs.demo.services.data;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class Help4DevsDataStructureService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">intVector</h6>
     *
     * <p style="color: #CDCDCD">Create a vector (array) from data (Object)</p>
     *
     * @param input (Object: Data to convert)
     * @return int[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static int[] intVector(Object input) {
        String tidyUp = input.toString().replaceAll("[^0-9]+", "");
        int[] vector = new int[tidyUp.length()];
        for (int i = 0; i < tidyUp.length(); i++) {
            vector[i] = Integer.parseInt(String.valueOf(tidyUp.charAt(i)));
        }
        return vector;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">charVector</h6>
     *
     * <p style="color: #CDCDCD">Create a vector (array) from data (Object)</p>
     *
     * @param input (Object: Data to convert)
     * @return String[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String[] charVector(Object input) {
        String tidyUp = String.valueOf(input);
        String[] vector = new String[tidyUp.length()];
        for (int i = 0; i < tidyUp.length(); i++) {
            vector[i] = String.valueOf(tidyUp.charAt(i));
        }
        return vector;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">objectMatrix</h6>
     *
     * <p style="color: #CDCDCD">Create a matrix (array) from data (Object)</p>
     *
     * @param inputList (List[Object]: Data to convert)
     * @return Object[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Object[][] objectMatrix(List<List<Object>> inputList) {

        if (inputList.isEmpty()) {
            return new Object[][]{};
        }

        if (inputList.size() == 1) {
            throw new RuntimeException("Matrix should be [Rows x Cols]");
        }

        if (inputList.get(0).isEmpty()) {
            return new Object[][]{};
        }

        Object[][] vector = new Object[inputList.size()][inputList.get(0).size()];

        for (int rows = 0; rows < inputList.size(); rows++) {

            for (int cols = 0; cols < inputList.get(rows).size(); cols++) {
                vector[rows][cols] = inputList.get(rows).get(cols);
            }

        }

        return vector;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">createIntegerList</h6>
     *
     * <p style="color: #CDCDCD">Create an data object to List[Integer]</p>
     *
     * @param input (Object: data to convert)
     * @return List[?]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static List<Integer> createIntegerList(Object input) {

        String tidyUp = input.toString().replaceAll("[^0-9]+", "");
        String[] splitter = tidyUp.split("");

        List<Integer> integers = new ArrayList<>();
        for (String item : splitter) {
            integers.add(Integer.parseInt(item));
        }
        return integers;

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">createIntegerHashMap</h6>
     *
     * <p style="color: #CDCDCD">Create an data object to HashMap[Integer]</p>
     *
     * @param input (Object: data to convert)
     * @return HashMap[Integer, Object]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static HashMap<Integer, Object> createIntegerHashMap(Object input) {

        String tidyUp = input.toString().replaceAll("[^0-9]+", "");
        String[] splitter = tidyUp.split("");

        HashMap<Integer, Object> integers = new HashMap<>();
        int index = 0;
        for (String item : splitter) {
            integers.put(index, Integer.parseInt(item));
            index += 1;
        }
        return integers;

    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        int id;
        String name;
    }

}
