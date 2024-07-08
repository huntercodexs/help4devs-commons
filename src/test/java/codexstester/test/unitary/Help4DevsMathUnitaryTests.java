package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsMathService.binaryToDecimal;
import static com.huntercodexs.demo.services.Help4DevsMathService.decimalToBinary;
import static java.lang.Math.ceil;

public class Help4DevsMathUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void ceilTest() {

        int splitter1 = (int) ceil((double) 5500 / 500);
        System.out.println(splitter1);

        int splitter2 = (int) ceil((double) 500 / 500);
        System.out.println(splitter2);

        int splitter3 = (int) ceil((double) 1200 / 500);
        System.out.println(splitter3);

        int splitter4 = (int) ceil((double) 800 / 500);
        System.out.println(splitter4);

    }

    @Test
    public void xorTest() {
    }

    @Test
    public void decimalToBinaryTest() {
        codexsTesterAssertExact("0", decimalToBinary(0));
        codexsTesterAssertExact("1", decimalToBinary(1));
        codexsTesterAssertExact("10", decimalToBinary(2));
        codexsTesterAssertExact("11", decimalToBinary(3));
        codexsTesterAssertExact("100", decimalToBinary(4));
        codexsTesterAssertExact("101", decimalToBinary(5));
        codexsTesterAssertExact("110", decimalToBinary(6));
        codexsTesterAssertExact("111", decimalToBinary(7));
        codexsTesterAssertExact("1000", decimalToBinary(8));
        codexsTesterAssertExact("1001", decimalToBinary(9));
        codexsTesterAssertExact("1010", decimalToBinary(10));
        codexsTesterAssertExact("1011", decimalToBinary(11));
        codexsTesterAssertExact("1100", decimalToBinary(12));
        codexsTesterAssertExact("1100100", decimalToBinary(100));
    }

    @Test
    public void binaryToDecimalTest() {
        codexsTesterAssertExact("0", String.valueOf(binaryToDecimal("0")));
        codexsTesterAssertExact("1", String.valueOf(binaryToDecimal("1")));
        codexsTesterAssertExact("2", String.valueOf(binaryToDecimal("10")));
        codexsTesterAssertExact("3", String.valueOf(binaryToDecimal("11")));
        codexsTesterAssertExact("4", String.valueOf(binaryToDecimal("100")));
        codexsTesterAssertExact("5", String.valueOf(binaryToDecimal("101")));
        codexsTesterAssertExact("6", String.valueOf(binaryToDecimal("110")));
        codexsTesterAssertExact("7", String.valueOf(binaryToDecimal("111")));
        codexsTesterAssertExact("8", String.valueOf(binaryToDecimal("1000")));
        codexsTesterAssertExact("9", String.valueOf(binaryToDecimal("1001")));
        codexsTesterAssertExact("10", String.valueOf(binaryToDecimal("1010")));
        codexsTesterAssertExact("11", String.valueOf(binaryToDecimal("1011")));
        codexsTesterAssertExact("12", String.valueOf(binaryToDecimal("1100")));
        codexsTesterAssertExact("100", String.valueOf(binaryToDecimal("1100100")));
        codexsTesterAssertExact("32", String.valueOf(binaryToDecimal("100000")));
        codexsTesterAssertExact("64", String.valueOf(binaryToDecimal("1000000")));
        codexsTesterAssertExact("128", String.valueOf(binaryToDecimal("10000000")));
        codexsTesterAssertExact("256", String.valueOf(binaryToDecimal("100000000")));
    }

}





