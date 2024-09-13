package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.stdout.Help4DevsStdoutService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.huntercodexs.demo.services.data.Help4DevsDataStructureService.objectMatrix;
import static com.huntercodexs.demo.services.stdout.Help4DevsStdoutService.objectMatrixPrinter;

public class Help4DevsStdoutUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void cliTableTest() {

        Help4DevsStdoutService stdout = new Help4DevsStdoutService();
        stdout.addWidth(101);

        stdout.drawHeader("CODEXS CLI TABLE - huntercodexs.com");

        stdout.drawItemHeader("Resource", "System");
        stdout.drawItemId("1", "osVersion", "Ubuntu", "");
        stdout.drawItem("osVendor", "Linux", "");
        stdout.drawItem("osPath", "Debian", "");
        stdout.drawItem("osTest", "Linux", "");
        stdout.drawItem("osDistro", "Linux", "");

        stdout.nextItem();
        stdout.nextItemHeader();

        stdout.drawItemHeader("Resource", "Audio");
        stdout.drawItemId("1", "Board", "REALTEK", "test");
        stdout.drawItem( "Board", "REALTEK", "test");

        stdout.drawItemId("2", "Board", "REALTEK", "test");
        stdout.drawItem("Board", "REALTEK", "test");
        stdout.drawItem("Board", "REALTEK", "test");

        stdout.drawItemId("3", "Board", "REALTEK", "test");
        stdout.drawItem( "Board", "REALTEK", "Intel64 Family 6 Model 158 Stepping 13 GenuineIntel ~2400 Mhz :: Intel64 Family 6 Model 158 Stepping 13 GenuineIntel ~2400 Mhz");

        stdout.nextItem();

    }

    @Test
    public void arrayMatrixTest() {

        int[][] intMatrix = new int[][]{
                {1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5}
        };
        String[][] strMatrix = new String[][]{
                {"AAA", "AAA", "AAA", "AAA", "AAA"},
                {"BBB", "BBB", "BBB", "BBB", "BBB"},
                {"CCC", "CCC", "CCC", "CCC", "CCC"},
                {"DDD", "DDD", "DDD", "DDD", "DDD"},
                {"EEE", "EEE", "EEE", "EEE", "EEE"}
        };
        boolean[][] bolMatrix = new boolean[][]{
                {true, true, true, true, true},
                {false, false, false, false, false},
                {false, true, true, true, true},
                {true, false, true, true, true},
                {true, true, false, true, true},
        };
        float[][] floMatrix = new float[][]{
                {1.5f, 1.5f, 1.5f, 1.5f, 1.5f},
                {2.5f, 2.5f, 2.5f, 2.5f, 2.5f},
                {3.5f, 3.5f, 3.5f, 3.5f, 3.5f},
                {4.5f, 4.5f, 4.5f, 4.5f, 4.5f},
                {5.5f, 5.5f, 5.5f, 5.5f, 5.5f}
        };
        double[][] douMatrix = new double[][]{
                {1.55, 1.55, 1.55, 1.55, 1.55},
                {2.55, 2.55, 2.55, 2.55, 2.55},
                {3.55, 3.55, 3.55, 3.55, 3.55},
                {4.55, 4.55, 4.55, 4.55, 4.55},
                {5.55, 5.55, 5.55, 5.55, 5.55}
        };

        System.out.println("\nint Matrix");
        for (int[] matrixCol : intMatrix) {
            for (int col : matrixCol) {
                System.out.print(col + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nString Matrix");
        for (String[] matrixCol : strMatrix) {
            for (String col : matrixCol) {
                System.out.print(col + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nboolean Matrix");
        for (boolean[] matrixCol : bolMatrix) {
            for (boolean col : matrixCol) {
                System.out.print(col + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nfloat Matrix");
        for (float[] matrixCol : floMatrix) {
            for (float col : matrixCol) {
                System.out.print(col + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\ndouble Matrix");
        for (double[] matrixCol : douMatrix) {
            for (double col : matrixCol) {
                System.out.print(col + " ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void toMatrixTest() {
        List<List<Object>> objectList = new ArrayList<>();

        List<Object> objectList1 = new ArrayList<>();
        Collections.addAll(objectList1, "XXX", "WWW", "AAA", "EEE", 444, 654);
        objectList.add(objectList1);

        List<Object> objectList2 = new ArrayList<>();
        Collections.addAll(objectList2, "R12", "RQE", 901, "NIO", "NOT", "QQQ");
        objectList.add(objectList2);

        Object[][] result = objectMatrix(objectList);

        objectMatrixPrinter(result, 6);
    }

}
