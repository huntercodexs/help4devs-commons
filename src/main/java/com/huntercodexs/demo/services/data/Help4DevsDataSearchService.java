package com.huntercodexs.demo.services.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsDataSearchService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">linearSearch</h6>
     *
     * <p style="color: #CDCDCD">Find an word in one specific array</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n)</p>
     * <p>Complexity Worst Case = O(n)</p>
     *
     * @param array (String[]: Array data to search)
     * @param dataSearch (String: Word to search in the array data)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean linearSearch(String[] array, String dataSearch) {
        for (String item : array) {
            if (item.equals(dataSearch)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">binarySearch</h6>
     *
     * <p style="color: #CDCDCD">Search data in one array using the binary search algorithm</p>
     *
     * <p>Complexity Best Case = O(log(n))</p>
     * <p>Complexity Average Case = O(log(n))</p>
     * <p>Complexity Worst Case = O(log(n))</p>
     *
     * @param array (Integer[]: Array data to search)
     * @param dataSearch (int: Data to search)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean binarySearch(Integer[] array, int dataSearch) {

        int start = 0;
        int middle;
        int end = array.length - 1;
        boolean found = false;

        while (start <= end) {

            middle = ((start + end) / 2);

            if (array[middle] == dataSearch) {
                found = true;
                break;
            } else if (array[middle] < dataSearch) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }

        }

        return found;
    }

}
