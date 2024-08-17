package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.params;

public class Help4DevsBaseUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void anyTest() {
        String str = null;
        str = str + "Hello";
        System.out.println(str);
    }

    @Test
    public void systemIndoWindowsTest() throws IOException {

        Process process = Runtime.getRuntime().exec("systeminfo");

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String result;
        List<String> list = new ArrayList<>();

        while ((result = reader.readLine()) != null) {

            //System.out.println(result);

            String[] splitter = result
                    .replaceAll("(: +)+", ":")
                    .replaceAll("^ +\\[", "[")
                    .replaceAll("^ +([0-9a-zA-Z])", "$1")
                    .replaceFirst(":", "{:cutter:}")
                    .split("\\{:cutter:}");

            //System.out.println(Arrays.toString(splitter));

            if (splitter.length == 2) {
                list.add(splitter[0]+"="+splitter[1]);
            }

            //System.out.println("FIELD: "+splitter[0]);
            //System.out.println("VALUE: "+splitter[1]);
        }

        for (String item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void shellScriptTest() throws IOException {

        //WINDOWS SYSTEM INFO
        try {
            String windowsSystemInfo = "systeminfo";

            Process process0 = Runtime.getRuntime().exec(windowsSystemInfo);
            BufferedReader reader0 = new BufferedReader(new InputStreamReader(process0.getInputStream()));
            String result0;

            while ((result0 = reader0.readLine()) != null) {
                System.out.println(result0);
            }

        } catch (Exception e1) {
            System.out.println("Command Error: " + e1.getMessage());
        }

        //HARDWARE DETAILS
        //String hardware = "lshw -short | awk '{print $2,$3,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16,$17,$18,$19,$20,$21,$22}'";
        String hardware = "lshw -short";

        Process process1 = Runtime.getRuntime().exec(hardware);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
        String result1;

        while ((result1 = reader1.readLine()) != null) {
            System.out.println(result1);
        }

        //PROCESSOR NAME
        //String processorName = "lscpu | grep 'Model name'";
        String processorName = "lscpu";

        Process process2 = Runtime.getRuntime().exec(processorName);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
        String result2;

        while ((result2 = reader2.readLine()) != null) {
            System.out.println(result2);
        }

        //CPU DETAILS
        String cpuDetails = "lshw -C cpu";

        Process process3 = Runtime.getRuntime().exec(cpuDetails);
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(process3.getInputStream()));
        String result3;

        while ((result3 = reader3.readLine()) != null) {
            System.out.println(result3);
        }
    }

    @Test
    public void paramsTest() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }

}
