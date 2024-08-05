package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.challenge.Help4DevsHackerRankService.*;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.stdout;

public class Help4DevsHackerRankUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void plusMinusTest() {
        List<Integer> list = new ArrayList<>();
        list.add(-4);
        list.add(3);
        list.add(-9);
        list.add(0);
        list.add(4);
        list.add(1);
        plusMinus(list);
    }

    @Test
    public void miniMaxSumTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        miniMaxSum(list);
    }

    @Test
    public void timeConversionTest() {
        String result = timeConversion("07:05:45PM");
        codexsTesterAssertText("19:05:45", result);

        result = timeConversion("07:05:45AM");
        codexsTesterAssertText("07:05:45", result);

        result = timeConversion("12:05:45PM");
        codexsTesterAssertText("12:05:45", result);

        result = timeConversion("12:05:45AM");
        codexsTesterAssertText("00:05:45", result);
    }

    @Test
    public void matchingStringsTest() {
        List<String> source = new ArrayList<>();
        List<String> queries = new ArrayList<>();

        source.add("4");
        source.add("aba");
        source.add("baba");
        source.add("aba");
        source.add("xzxb");
        source.add("3");
        source.add("aba");
        source.add("xzxb");
        source.add("ab");

        queries.add("xzxb");
        queries.add("baba");
        queries.add("bc");

        List<Integer> result = matchingStrings(source, queries);
        codexsTesterAssertText("[2, 1, 0]", result.toString());

    }

    @Test
    public void lonelyintegerTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        int result = lonelyinteger(list);
        codexsTesterAssertInt(4, result);
    }

    @Test
    public void flippingBitsTest() {
        long result = flippingBits(3);
        stdout(String.valueOf(result));

        result = flippingBits(2147483647);
        stdout(String.valueOf(result));

        result = flippingBits(1);
        stdout(String.valueOf(result));

        result = flippingBits(0);
        stdout(String.valueOf(result));
    }

    @Test
    public void diagonalDifferenceTest() {
        List<List<Integer>> arr = new ArrayList<>();

        List<Integer> line1 = new ArrayList<>();
        line1.add(1);
        line1.add(2);
        line1.add(3);

        List<Integer> line2 = new ArrayList<>();
        line2.add(4);
        line2.add(5);
        line2.add(6);

        List<Integer> line3 = new ArrayList<>();
        line3.add(9);
        line3.add(8);
        line3.add(9);

        arr.add(line1);
        arr.add(line2);
        arr.add(line3);

        int result = diagonalDifference(arr);
        codexsTesterAssertInt(2, result);
    }

    @Test
    public void countingSortTest() {
        List<Integer> arr = new ArrayList<>();
        arr.add(100);
        arr.add(63);
        arr.add(25);
        arr.add(73);
        arr.add(1);
        arr.add(98);
        arr.add(73);
        arr.add(56);
        arr.add(84);
        arr.add(86);
        arr.add(57);
        arr.add(16);
        arr.add(83);
        arr.add(8);
        arr.add(25);
        arr.add(81);
        arr.add(56);
        arr.add(9);
        arr.add(53);
        arr.add(98);
        arr.add(67);
        arr.add(99);
        arr.add(12);
        arr.add(83);
        arr.add(89);
        arr.add(80);
        arr.add(91);
        arr.add(39);
        arr.add(86);
        arr.add(76);
        arr.add(85);
        arr.add(74);
        arr.add(39);
        arr.add(25);
        arr.add(90);
        arr.add(59);
        arr.add(10);
        arr.add(94);
        arr.add(32);
        arr.add(44);
        arr.add(3);
        arr.add(89);
        arr.add(30);
        arr.add(27);
        arr.add(79);
        arr.add(46);
        arr.add(96);
        arr.add(27);
        arr.add(32);
        arr.add(18);
        arr.add(21);
        arr.add(92);
        arr.add(69);
        arr.add(81);
        arr.add(40);
        arr.add(40);
        arr.add(34);
        arr.add(68);
        arr.add(78);
        arr.add(24);
        arr.add(87);
        arr.add(42);
        arr.add(69);
        arr.add(23);
        arr.add(41);
        arr.add(78);
        arr.add(22);
        arr.add(6);
        arr.add(90);
        arr.add(99);
        arr.add(89);
        arr.add(50);
        arr.add(30);
        arr.add(20);
        arr.add(1);
        arr.add(43);
        arr.add(3);
        arr.add(70);
        arr.add(95);
        arr.add(33);
        arr.add(46);
        arr.add(44);
        arr.add(9);
        arr.add(69);
        arr.add(48);
        arr.add(33);
        arr.add(60);
        arr.add(65);
        arr.add(16);
        arr.add(82);
        arr.add(67);
        arr.add(61);
        arr.add(32);
        arr.add(21);
        arr.add(79);
        arr.add(75);
        arr.add(75);
        arr.add(13);
        arr.add(87);
        arr.add(70);
        arr.add(33);

        List<Integer> result = countingSort(arr);

        codexsTesterAssertText(
                "0 2 0 2 0 0 1 0 1 2 1 0 1 1 0 0 2 0 1 0 1 2 1 1 1 3 0 2 0 0 2 0 3 3 1 0 0 0 0 2 2 1 1 1 2 0 2 0 1 0 1 0 0 1 0 0 2 1 0 1 1 1 0 1 0 1 0 2 1 3 2 0 0 2 1 2 1 0 2 2 1 2 1 2 1 1 2 2 0 3 2 1 1 0 1 1 1 0 2 2",
                result.toString().replaceAll("[\\]\\[]", "").replaceAll(",", ""));

    }

    @Test
    public void pangramsTest() {
        String pangram = ("We promptly judged antique ivory buckles for the next prize".toLowerCase());
        String notPangram = ("The promptly judged antique ivory buckles for the next prize").toLowerCase();

        codexsTesterAssertBool(true, pangrams(pangram, "en"));
        codexsTesterAssertBool(false, pangrams(notPangram, "en"));
    }

    @Test
    public void computeClosestToZeroTest() {
        int[] vector;
        int result;

        vector = new int[]{};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(0, result);

        vector = new int[]{3,1,4,6,-9,2,0};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(0, result);

        vector = new int[]{1, -2, -88, 4, 5};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(1, result);

        vector = new int[]{-3, 3};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(3, result);

        vector = new int[]{-4, 0, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(0, result);

        vector = new int[]{2, -2, -88, 20, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(2, result);

        vector = new int[]{-44, -1, -2, 3, 4, 5};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(-1, result);

        vector = new int[]{-1, 2, -2, -88, 20, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(-1, result);

        vector = new int[]{1, -2, 2, 3, -100, 28, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(1, result);

        vector = new int[]{1, -2, 2, 3, -10, -10, -100, 28, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(1, result);

        vector = new int[]{-10, -10};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(-10, result);

        vector = new int[]{-15, -7, -9, -14, -12};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(-7, result);

        vector = new int[]{7, 5, 9, 1, 4};
        result = computeClosestToZero(vector);
        codexsTesterAssertInt(1, result);
    }

}
