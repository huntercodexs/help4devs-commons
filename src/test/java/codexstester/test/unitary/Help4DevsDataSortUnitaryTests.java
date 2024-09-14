package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMegabytes;
import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.calculateMemory;
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

        //System information
        System.out.println("[Information] - System");
        //Processor
        System.out.println("Processor: "+Runtime.getRuntime().availableProcessors());
        //Memory
        System.out.println("Memory: "+Runtime.getRuntime().totalMemory());
        //The version of Java Runtime Environment.
        System.out.println("Java Version: "+System.getProperty("java.version"));
        //The name of Java Runtime Environment vendor
        System.out.println("Java Vendor: "+System.getProperty("java.vendor "));
        //The URL of Java vendor
        System.out.println("Java Vendor URL: "+System.getProperty("java.vendor.url"));
        //The directory of Java installation
        System.out.println("Java Home: "+System.getProperty("java.home"));
        //The specification version of Java Virtual Machine
        System.out.println("Java Specification Version: "+System.getProperty("java.vm.specification.version"));
        //The name of specification vendor of Java Virtual Machine
        System.out.println("Java VM Specification Vendor: "+System.getProperty("java.vm.specification.vendor"));
        //Java Virtual Machine specification name
        System.out.println("Java VM Specification Name: "+System.getProperty("java.vm.specification.name"));
        //JVM implementation version
        System.out.println("Java VM Version: "+System.getProperty("java.vm.version"));
        //JVM implementation vendor
        System.out.println("Java VM Vendor: "+System.getProperty("java.vm.vendor"));
        //JVM  implementation name
        System.out.println("Java VM Name: "+System.getProperty("java.vm.name"));
        //The name of specification version Java Runtime Environment
        System.out.println("Java Specification Version: "+System.getProperty("java.specification.version"));
        // JRE specification vendor
        System.out.println("Java Specification Vendor: "+System.getProperty("java.specification.vendor"));
        //JRE specification name
        System.out.println("Java Specification Name: "+System.getProperty("java.specification.name"));
        //Java class format version number
        System.out.println("Java Class Version: "+System.getProperty("java.class.version"));
        //Path of java class
        System.out.println("Java Class Path: "+System.getProperty("ava.class.path"));
        //List of paths to search when loading libraries
        System.out.println("Java Library Path: "+System.getProperty("java.library.path"));
        //The path of temp file
        System.out.println("Java IO Tmpdir: "+System.getProperty("java.io.tmpdir"));
        //The Name of JIT compiler to use
        System.out.println("Java Compiler: "+System.getProperty("java.compiler"));
        //The path of extension directory or directories
        System.out.println("Java Extra Dirs: "+System.getProperty("java.ext.dirs"));
        //The name of OS name
        System.out.println("OS Name: "+System.getProperty("os.name"));
        //The OS architecture
        System.out.println("OS Arch: "+System.getProperty("os.arch"));
        //The version of OS
        System.out.println("OS Version: "+System.getProperty("os.version"));
        //The File separator
        System.out.println("OS File Separator: "+System.getProperty("file.separator"));
        //The path separator
        System.out.println("OS Path Separator: "+System.getProperty("path.separator"));
        //The line separator
        System.out.print("OS Line Separator: "+System.getProperty("line.separator"));
        //The name of account name user
        System.out.println("User Name: "+System.getProperty("user.name"));
        //The home directory of user
        System.out.println("User Home: "+System.getProperty("user.home"));
        //The current working directory of the user
        System.out.println("User Dir: "+System.getProperty("user.dir"));
        //Roots
        for (File root : File.listRoots()) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space: " + calculateMemory(root.getTotalSpace()) +" ("+root.getTotalSpace()+" bytes)");
            System.out.println("Free space: " + calculateMemory(root.getFreeSpace()) +" ("+root.getFreeSpace()+" bytes)");
            System.out.println("Usable space: " + calculateMemory(root.getUsableSpace()) +" ("+ root.getFreeSpace() + " bytes)");
        }

        Runtime.getRuntime().runFinalization();
        Runtime.getRuntime().gc();

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
