package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsKeyboardDto;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands.sysCmd;

public class Help4DevsHardSysCommandsUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void availableCommandsTest() {
        String result = sysCmd(Help4DevsHardSysCommands.INXI);
        codexsTesterAssertExact("inxi -Fxz --slots --memory --cpu --disk-full --graphics --machine --network --sensors --system --usb --info --color", result);

        result = sysCmd(Help4DevsHardSysCommands.HWINFO);
        codexsTesterAssertExact("hwinfo --short", result);

        result = sysCmd(Help4DevsHardSysCommands.LSHW);
        codexsTesterAssertExact("lshw -short", result);

        result = sysCmd(Help4DevsHardSysCommands.LSCPU);
        codexsTesterAssertExact("lscpu", result);

        result = sysCmd(Help4DevsHardSysCommands.LSPCI);
        codexsTesterAssertExact("lspci", result);

        result = sysCmd(Help4DevsHardSysCommands.LSUSB);
        codexsTesterAssertExact("lsusb", result);

        result = sysCmd(Help4DevsHardSysCommands.DMIDECODE);
        codexsTesterAssertExact("dmidecode", result);

        result = sysCmd(Help4DevsHardSysCommands.SYSTEMINFO);
        codexsTesterAssertExact("systeminfo", result);
    }

    @Test
    public void inxiTest() {

        try {

            System.out.println("INXI INFORMATION");
            String inxi = "inxi -Fxz --slots --memory --cpu --disk-full --graphics --machine --network --sensors --system --usb --info --color";

            Process processInxi = Runtime.getRuntime().exec(inxi);
            BufferedReader readerInxi = new BufferedReader(new InputStreamReader(processInxi.getInputStream()));
            String resultInxi;

            while ((resultInxi = readerInxi.readLine()) != null) {
                System.out.println(resultInxi);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    @Test
    public void hwinfoTest() {

        try {
            System.out.println("HWINFO INFORMATION");
            String hwinfo = "hwinfo --short";

            Process processHwInfo = Runtime.getRuntime().exec(hwinfo);
            BufferedReader readerHwInfo = new BufferedReader(new InputStreamReader(processHwInfo.getInputStream()));
            String resultHwInfo;

            while ((resultHwInfo = readerHwInfo.readLine()) != null) {
                System.out.println(resultHwInfo);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    @Test
    public void lshwTest() {

        try {

            System.out.println("HARDWARE DETAILS");
            String hardware = "lshw -short";

            Process process1 = Runtime.getRuntime().exec(hardware);
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
            String result1;

            while ((result1 = reader1.readLine()) != null) {
                System.out.println(result1);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    @Test
    public void lscpuTest() {

        try {
            System.out.println("PROCESSOR NAME");
            String processorName = "lscpu";

            Process process2 = Runtime.getRuntime().exec(processorName);
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            String result2;

            while ((result2 = reader2.readLine()) != null) {
                System.out.println(result2);
            }

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    @Test
    public void lscpuDetailsTest() {

        try {
            System.out.println("CPU DETAILS");
            String cpuDetails = "lshw -C cpu";

            Process process3 = Runtime.getRuntime().exec(cpuDetails);
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(process3.getInputStream()));
            String result3;

            while ((result3 = reader3.readLine()) != null) {
                System.out.println(result3);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

    }

    @Test
    public void dmidecodeTest() {

        /*
         * [IMPORTANT] This method use dmidecode linux command that needs superuser (root), so
         * it's very important to compile the application, get the jar file generated and run that one
         * in the following way:
         *
         * sudo java -jar {APP}.jar --spring.config.location={APPLICATION-PROPERTIES}
         *
         * Or you can (should) run the IDE like a IntelliJ using the sudo command to get the required kind of
         * permissions to access the dmidecode linux command
         * */

        try {

            System.out.println("DMI DECODE");
            String dmidecode = "sudo dmidecode";
            Process process4 = Runtime.getRuntime().exec(dmidecode);
            process4.waitFor();
            BufferedReader reader4 = new BufferedReader(new InputStreamReader(process4.getInputStream()));

            String result4;

            while ((result4 = reader4.readLine()) != null) {
                System.out.println(result4);
            }

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Test
    public void systemInfoWindowsTest() throws IOException {

        System.out.println("WINDOWS SYSTEM INFO");
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
                list.add(splitter[0] + "=" + splitter[1]);
            }

            //System.out.println("FIELD: "+splitter[0]);
            //System.out.println("VALUE: "+splitter[1]);
        }

        for (String item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void help4DevsKeyboardDtoTest() {

        Help4DevsKeyboardDto keyboard = new Help4DevsKeyboardDto();
        keyboard.setQty("2");

        Help4DevsKeyboardDto.Help4DevsKeyboard keyboardDto = new Help4DevsKeyboardDto.Help4DevsKeyboard();
        keyboardDto.setId("1");
        keyboardDto.setName("name 1");

        keyboard.addKeyboard(keyboardDto);

        keyboardDto = new Help4DevsKeyboardDto.Help4DevsKeyboard();
        keyboardDto.setId("2");
        keyboardDto.setName("name 2");

        keyboard.addKeyboard(keyboardDto);

        System.out.println(keyboard);

        System.out.println(keyboard.getQty());
        System.out.println(keyboard.getDetails());
        System.out.println(keyboard.getDetails().get(0));
        System.out.println(keyboard.getDetails().get(0).getId());
        System.out.println(keyboard.getDetails().get(0).getName());
        System.out.println(keyboard.getDetails().get(1));
        System.out.println(keyboard.getDetails().get(1).getId());
        System.out.println(keyboard.getDetails().get(1).getName());
    }

    @Test
    public void help4DevsKeyboardsTest() {

        Help4DevsKeyboardDto keyboard = new Help4DevsKeyboardDto();
        keyboard.setQty("2");

        List<Help4DevsKeyboardDto.Help4DevsKeyboard> keyboardList = new ArrayList<>();

        Help4DevsKeyboardDto.Help4DevsKeyboard keyboardDto = new Help4DevsKeyboardDto.Help4DevsKeyboard();
        keyboardDto.setId("1");
        keyboardDto.setName("name 1");
        keyboardList.add(keyboardDto);

        keyboardDto = new Help4DevsKeyboardDto.Help4DevsKeyboard();
        keyboardDto.setId("2");
        keyboardDto.setName("name 2");
        keyboardList.add(keyboardDto);

        keyboard.setDetails(keyboardList);

        System.out.println(keyboard);

        System.out.println(keyboard.getQty());
        System.out.println(keyboard.getDetails());
        System.out.println(keyboard.getDetails().get(0));
        System.out.println(keyboard.getDetails().get(0).getId());
        System.out.println(keyboard.getDetails().get(0).getName());
        System.out.println(keyboard.getDetails().get(1));
        System.out.println(keyboard.getDetails().get(1).getId());
        System.out.println(keyboard.getDetails().get(1).getName());
    }

}
