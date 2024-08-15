package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.util.Arrays;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMegabytes;
import static com.huntercodexs.demo.services.data.Help4DevsDataSortService.*;

public class Help4DevsDataSortUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void sortAlgorithmComparatorTest() {

        long arraySize = 1000000;
        Integer[] array = new Integer[(int) arraySize];

        long start = System.currentTimeMillis();
        for (long i = 0; i < arraySize; i++) {
            array[(int) i] = (int) Math.floor(Math.random() * arraySize);
        }
        long end = System.currentTimeMillis();

        long total = end - start;

        System.out.println("Array Size: "+arraySize);
        System.out.println("Array initialized in "+total+"ms");
        if (array.length <= 999) {
            System.out.println(Arrays.toString(array));
        }

        ResultMetrics result;

        //Bubble Sort
        System.out.println("\n[Starting] -- Bubble Sort Result Metrics --");
        result = bubbleSortForIntegerMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Bubble Sort Optimized
        System.out.println("\n[Starting] -- Bubble Sort Optimized Result Metrics --");
        result = bubbleSortForIntegerOptimizedMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Insertion Sort
        System.out.println("\n[Starting] -- Insertion Sort Result Metrics --");
        result = insertionSortMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Selection Sort
        System.out.println("\n[Starting] -- Selection Sort Result Metrics --");
        result = selectionSortMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Head Sort
        System.out.println("\n[Starting] -- Heap Sort Result Metrics --");
        result = heapSortMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Quick Sort
        System.out.println("\n[Starting] -- Quick Sort Result Metrics --");
        result = quickSortMetrics(array.clone(), 0, array.clone().length-1);
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

        //Shell Sort
        System.out.println("\n[Starting] -- Shell Sort Result Metrics --");
        result = shellSortMetrics(array.clone());
        System.out.println("Changes: "+result.getChanges());
        System.out.println("Iterations: "+result.getIterations());
        System.out.println("Start Time: "+result.getStartTime());
        System.out.println("End Time: "+result.getEndTime());
        System.out.println("Elapsed Time: "+result.getElapsedTime()+"ms");
        System.out.println("Memory Used: "+calculateMegabytes(result.getMemoryUsed())+" ("+result.getMemoryUsed()+")");
        if (array.length <= 999) { //To avoid unnecessary visualization
            System.out.println("Array Sorted: " + Arrays.toString(result.getArray()));
        }
        System.out.println("[Done]\n");

    }

    @Test
    public void bubbleSortForIntegerTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = bubbleSortForInteger(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void bubbleSortForStringTest() {
        String[] array = new String[]{"AAA","GGG","XXX","ZZZ","FFF","BBB","RRR","CCC","_A1", "0B9"};
        String[] result = bubbleSortForString(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertText("0B9", result[0]);
        codexsTesterAssertText("AAA", result[1]);
        codexsTesterAssertText("BBB", result[2]);
        codexsTesterAssertText("CCC", result[3]);
        codexsTesterAssertText("FFF", result[4]);
        codexsTesterAssertText("GGG", result[5]);
        codexsTesterAssertText("RRR", result[6]);
        codexsTesterAssertText("XXX", result[7]);
        codexsTesterAssertText("ZZZ", result[8]);
        codexsTesterAssertText("_A1", result[9]);
    }

    @Test
    public void bubbleSortForIntegerOptimizedTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = bubbleSortForIntegerOptimized(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void insertionSortTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = insertionSort(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void selectionSortTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = selectionSort(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void heapSortTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = heapSort(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void quickSortTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = quickSort(array,0, array.length - 1);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void shellSortTest() {
        Integer[] array = new Integer[]{9,0,4,5,6,2,54,6,7,2,34,45,55,3222,8};
        Integer[] result = shellSort(array);
        System.out.println(Arrays.toString(result));
        codexsTesterAssertInt(0, result[0]);
        codexsTesterAssertInt(2, result[1]);
        codexsTesterAssertInt(2, result[2]);
        codexsTesterAssertInt(4, result[3]);
        codexsTesterAssertInt(5, result[4]);
        codexsTesterAssertInt(6, result[5]);
        codexsTesterAssertInt(6, result[6]);
        codexsTesterAssertInt(7, result[7]);
        codexsTesterAssertInt(8, result[8]);
        codexsTesterAssertInt(9, result[9]);
        codexsTesterAssertInt(34, result[10]);
        codexsTesterAssertInt(45, result[11]);
        codexsTesterAssertInt(54, result[12]);
        codexsTesterAssertInt(55, result[13]);
        codexsTesterAssertInt(3222, result[14]);
    }

    @Test
    public void dijkstraTest() {}

}
