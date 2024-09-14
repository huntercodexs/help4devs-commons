package com.huntercodexs.demo.services.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsDataSortService {

    //METRICS
    private static int maxTime = 60000; //ms = 1 minute
    private static int changes = 0;
    private static int iterations = 0;
    private static int heapChanges = 0;
    private static int heapIterations = 0;
    private static int quickChanges = 0;
    private static int quickIterations = 0;
    private static long startTime = 0;
    private static long endTime = 0;
    private static long elapsedTime = 0;
    private static long memoryUsed = 0;

    private static void heapApply(Integer[] array, int n, int i) {
        int root = i;
        int leftNode = 2*i+1;
        int rightNode = 2*i+2;

        if (leftNode < n && array[leftNode] > array[root]) {
            root = leftNode;
        }
        if (rightNode < n && array[rightNode] > array[root]) {
            root = rightNode;
        }

        if (root != i) {
            int aux = array[i];
            array[i] = array[root];
            array[root] = aux;
            heapChanges++;
            heapIterations++;

            heapApply(array, n , root);
        }

    }

    private static int quickPartition(Integer[] array, int left, int right) {

        int pivot = array[right];
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                quickChanges++;
            }
            quickIterations++;
        }

        int temp = array[i+1];
        array[i+1] = array[right];
        array[right] = temp;
        quickChanges++;

        return i+1;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">bubbleSortForInteger</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the bubble sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] bubbleSortForInteger(Integer[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = (i + 1); j < array.length; j++) {
                if (array[i] > array[j]) {
                    int aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                }
            }
        }

        return array;
    }

    public static ResultMetrics bubbleSortForIntegerMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        startTime = System.currentTimeMillis();//metric
        for (int i = 0; i < array.length; i++) {
            for (int j = (i + 1); j < array.length; j++) {
                if (array[i] > array[j]) {
                    int aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                    changes++;//metric
                }
                iterations++;//metric
            }
            iterations++;//metric
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(changes, iterations, startTime, endTime, elapsedTime, memoryUsed, array);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">bubbleSortForIntegerOptimized</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the bubble sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] bubbleSortForIntegerOptimized(Integer[] array) {

        int n = array.length;
        boolean swapped;

        for (int i = 0; i < (n-1); i++) {
            swapped = false;
            for (int j = 0; j < (n-i-1); j++) {
                if (array[j] > array[j+1]) {
                    int aux = array[j];
                    array[j] = array[j+1];
                    array[j+1] = aux;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        return array;
    }

    public static ResultMetrics bubbleSortForIntegerOptimizedMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        int n = array.length;
        boolean swapped;

        startTime = System.currentTimeMillis();//metric
        for (int i = 0; i < (n-1); i++) {
            swapped = false;
            for (int j = 0; j < (n-i-1); j++) {
                if (array[j] > array[j+1]) {
                    int aux = array[j];
                    array[j] = array[j+1];
                    array[j+1] = aux;
                    swapped = true;
                    changes++;//metric
                }
                iterations++;//metric
            }
            iterations++;//metric
            if (!swapped) {
                break;
            }
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(changes, iterations, startTime, endTime, elapsedTime, memoryUsed, array);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">bubbleSortForString</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the bubble sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (String[]: Array data to order)
     * @return String[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String[] bubbleSortForString(String[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = (i + 1); j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    String aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                }
            }
        }

        return array;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">insertionSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the insertion sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] insertionSort(Integer[] array) {

        for (int i = 1; i < array.length; i++) {
            int aux = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > aux) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = aux;
        }

        return array;
    }

    public static ResultMetrics insertionSortMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        startTime = System.currentTimeMillis();//metric
        for (int i = 1; i < array.length; i++) {
            int aux = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > aux) {
                array[j+1] = array[j];
                j--;
                changes++;//metric
                iterations++;//metric
            }
            array[j+1] = aux;
            changes++;//metric
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(changes, iterations, startTime, endTime, elapsedTime, memoryUsed, array);

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">selectionSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the selection sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] selectionSort(Integer[] array) {

        for (int i = 0; i < array.length; i++) {
            int pos = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[pos]) {
                    pos = j;
                }
            }
            int aux = array[pos];
            array[pos] = array[i];
            array[i] = aux;
        }

        return array;
    }

    public static ResultMetrics selectionSortMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        startTime = System.currentTimeMillis();//metric
        for (int i = 0; i < array.length; i++) {
            int pos = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[pos]) {
                    pos = j;
                }
                iterations++;//metric
            }
            int aux = array[pos];
            array[pos] = array[i];
            array[i] = aux;
            changes++;//metric
            iterations++;//metric
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(changes, iterations, startTime, endTime, elapsedTime, memoryUsed, array);

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">heapSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the heap sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n)</p>
     * <p>Complexity Average Case = O(n^2)</p>
     * <p>Complexity Worst Case = O(n^2)</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] heapSort(Integer[] array) {

        int n = array.length;

        for (int i = n/2-1; i >= 0; i--) {
            heapApply(array, n, i);
        }

        for (int k = n-1; k > 0; k--) {
            int aux = array[0];
            array[0] = array[k];
            array[k] = aux;
            heapApply(array, k, 0);
        }

        return array;
    }

    public static ResultMetrics heapSortMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        int n = array.length;
        startTime = System.currentTimeMillis();//metric
        for (int i = n/2-1; i >= 0; i--) {
            heapApply(array, n, i);
        }

        for (int k = n-1; k > 0; k--) {
            int aux = array[0];
            array[0] = array[k];
            array[k] = aux;
            heapChanges++;//metric
            heapIterations++;//metric
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }

            heapApply(array, k, 0);
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(heapChanges, heapIterations, startTime, endTime, elapsedTime, memoryUsed, array);

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">quickSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the quick sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n^2)</p>
     * <p>Complexity Average Case =  O(n log(n))</p>
     * <p>Complexity Worst Case =  O(n log(n))</p>
     *
     * @param array (Integer[]: Array data to order)
     * @param left (int: minor data to order)
     * @param right (int: major data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] quickSort(Integer[] array, int left, int right) {
        if (left < right) {
            int pivot = quickPartition(array, left, right);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
        return array;
    }

    public static ResultMetrics quickSortMetrics(Integer[] array, int left, int right) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        startTime = System.currentTimeMillis();//metric
        if (left < right) {
            int pivot = quickPartition(array, left, right);
            quickSortMetrics(array, left, pivot - 1);//metric
            quickSortMetrics(array, pivot + 1, right);//metric
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(quickChanges, quickIterations, startTime, endTime, elapsedTime, memoryUsed, array);

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">shellSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the shell sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n^2)</p>
     * <p>Complexity Average Case =  O(n log(n))</p>
     * <p>Complexity Worst Case =  O(n log(n))</p>
     *
     * @param array (Integer[]: Array data to order)
     * @return Integer[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Integer[] shellSort(Integer[] array) {

        int hype = 1;
        int size = array.length;

        while (hype < size) {
            hype = hype * 3 + 1;
        }
        hype = hype / 3;

        int element;
        int salt;

        while (hype > 0) {
            for (int i = hype; i < size; i++) {
                element = array[i];
                salt = i;
                while (salt >= hype && array[salt-hype] > element) {
                    array[salt] = array[salt - hype];
                    salt = salt - hype;
                }
                array[salt] = element;
            }
            hype = hype / 2;
        }

        return array;
    }

    public static ResultMetrics shellSortMetrics(Integer[] array) {

        long freeMemoryBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric

        int hype = 1;
        int size = array.length;

        startTime = System.currentTimeMillis();//metric
        while (hype < size) {
            hype = hype * 3 + 1;
            iterations++;//metric
        }
        hype = hype / 3;

        int element;
        int salt;

        while (hype > 0) {
            for (int i = hype; i < size; i++) {
                element = array[i];
                salt = i;
                while (salt >= hype && array[salt-hype] > element) {
                    array[salt] = array[salt - hype];
                    salt = salt - hype;
                    changes++;//metric
                    iterations++;//metric
                }
                array[salt] = element;
                changes++;//metric
                iterations++;//metric
            }
            hype = hype / 2;
            iterations++;//metric
            if (System.currentTimeMillis() - startTime > maxTime) {
                System.out.println("[W A R N I N G] OVERFLOW LIMITED TIME - PROCESS ABORTED !");
                break;
            }
        }
        endTime = System.currentTimeMillis();//metric
        elapsedTime = endTime - startTime;//metric

        long freeMemoryAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();//metric
        memoryUsed = freeMemoryAfter - freeMemoryBefore;//metric

        return new ResultMetrics(changes, iterations, startTime, endTime, elapsedTime, memoryUsed, array);

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">mergeSort</h6>
     *
     * <p style="color: #CDCDCD">Sort an array using the merge sort algorithm</p>
     *
     * <p>Complexity Best Case = O(n log(n))</p>
     * <p>Complexity Average Case = O(n log(n))</p>
     * <p>Complexity Worst Case = O(n log(n))</p>
     *
     * @param array (String[]: Array data to order)
     * @return String[]
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String[] mergeSort(String[] array) {
        return null;
    }

    public static class ResultMetrics {
        int changes;
        int iterations;
        long startTime;
        long endTime;
        long elapsedTime;
        long memoryUsed;
        Integer[] array;

        public ResultMetrics() {}

        public ResultMetrics(
                int changes,
                int iterations,
                long startTime,
                long endTime,
                long elapsedTime,
                long memoryUsed,
                Integer[] array
        ) {
            this.changes = changes;
            this.iterations = iterations;
            this.startTime = startTime;
            this.endTime = endTime;
            this.elapsedTime = elapsedTime;
            this.memoryUsed = memoryUsed;
            this.array = array;
        }

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

        public int getIterations() {
            return iterations;
        }

        public void setIterations(int iterations) {
            this.iterations = iterations;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getElapsedTime() {
            return elapsedTime;
        }

        public void setElapsedTime(long elapsedTime) {
            this.elapsedTime = elapsedTime;
        }

        public long getMemoryUsed() {
            return memoryUsed;
        }

        public void setMemoryUsed(long memoryUsed) {
            this.memoryUsed = memoryUsed;
        }

        public Integer[] getArray() {
            return array;
        }

        public void setArray(Integer[] array) {
            this.array = array;
        }
    }

}
