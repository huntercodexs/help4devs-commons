package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.challenge.Help4DevsCoderPadService.computeClosestToZero;
import static com.huntercodexs.demo.services.challenge.Help4DevsCoderPadService.greatValue;

public class Help4DevsCoderPadUnitaryTests extends Help4DevsBridgeTests {

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

    @Test
    public void greatValueTest() {
        int result = greatValue(90, 80, 100);
        codexsTesterAssertInt(result, 2);

        result = greatValue(90, 80, 0);
        codexsTesterAssertInt(result, 0);

        result = greatValue(0, 80, 0);
        codexsTesterAssertInt(result, 1);

        result = greatValue(0, 0, 0);
        codexsTesterAssertInt(result, 0);
    }

}
