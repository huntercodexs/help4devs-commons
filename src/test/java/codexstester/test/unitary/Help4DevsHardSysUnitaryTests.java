package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.Help4DevsHardSys;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import com.huntercodexs.demo.system.hardsys.group.*;
import com.huntercodexs.demo.system.hardsys.processing.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.*;

public class Help4DevsHardSysUnitaryTests extends Help4DevsBridgeTests {

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

        result = sysCmd(Help4DevsHardSysCommands.LSCPU2);
        codexsTesterAssertExact("lshw -C cpu", result);

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
    public void generalSystemInfoByInxiCommandTest() {

        Help4DevsHardSys hardSys = new Help4DevsHardSys(INXI);
        hardSys.json();

        //SYSTEM
        Help4DevsSystemDetails system = hardSys.resources().getSystem();
        System.out.println(system.getDetails());
        //--MACHINE
        Help4DevsMachineDetails machine = hardSys.resources().getMachine();
        System.out.println(machine.getDetails());
        //--BATTERY
        Help4DevsBatteryDetails battery = hardSys.resources().getBattery();
        System.out.println(battery.getDetails());
        //--MEMORY
        Help4DevsMemoryDetails memory = hardSys.resources().getMemory();
        System.out.println(memory.getDetails());
        //--SLOTS
        Help4DevsSlotsDetails slots = hardSys.resources().getSlots();
        System.out.println(slots.getDetails());
        //--PROCESSOR
        Help4DevsProcessorDetails processor = hardSys.resources().getProcessor();
        System.out.println(processor.getDetails());
        //--GRAPHICS
        Help4DevsGraphicsDetails graphics = hardSys.resources().getGraphics();
        System.out.println(graphics.getDetails());
        //--AUDIO
        Help4DevsAudioDetails audio = hardSys.resources().getAudio();
        System.out.println(audio.getDetails());
        //--NETWORK
        Help4DevsNetworkDetails network = hardSys.resources().getNetwork();
        System.out.println(network.getDetails());
        //--DRIVERS
        Help4DevsDriversDetails drivers = hardSys.resources().getDrivers();
        System.out.println(drivers.getDetails());
        //--PARTITION
        Help4DevsPartitionDetails partition = hardSys.resources().getPartition();
        System.out.println(partition.getDetails());
        //--USB
        Help4DevsUsbDetails usb = hardSys.resources().getUsb();
        System.out.println(usb.getDetails());
        //--SENSORS
        Help4DevsSensorsDetails sensors = hardSys.resources().getSensors();
        System.out.println(sensors.getDetails());
        //--RUNNING
        Help4DevsRunningDetails running = hardSys.resources().getRunning();
        System.out.println(running.getDetails());
        //--MONITOR
        Help4DevsMonitorDetails monitor = hardSys.resources().getMonitor();
        System.out.println(monitor.getDetails());
        //--BIOS
        Help4DevsBiosDetails bios = hardSys.resources().getBios();
        System.out.println(bios.getDetails());
        //--BASEBOARD
        Help4DevsBaseboardDetails baseboard = hardSys.resources().getBaseboard();
        System.out.println(baseboard.getDetails());
        //--CHASSIS
        Help4DevsChassisDetails chassis = hardSys.resources().getChassis();
        System.out.println(chassis.getDetails());
        //--CACHE
        Help4DevsCacheDetails cache = hardSys.resources().getCache();
        System.out.println(cache.getDetails());
        //--CONNECTOR
        Help4DevsConnectorDetails connector = hardSys.resources().getConnector();
        System.out.println(connector.getDetails());
        //--KEYBOARD
        Help4DevsKeyboardDetails keyboard = hardSys.resources().getKeyboard();
        System.out.println(keyboard.getDetails());
        //--MOUSE
        Help4DevsMouseDetails mouse = hardSys.resources().getMouse();
        System.out.println(mouse.getDetails());
        //--HUB
        Help4DevsHubDetails hub = hardSys.resources().getHub();
        System.out.println(hub.getDetails());
        //--SWITCH
        Help4DevsSwitchDetails switcher = hardSys.resources().getSwitch();
        System.out.println(switcher.getDetails());
        //--MODEM
        Help4DevsModemDetails modem = hardSys.resources().getModem();
        System.out.println(modem.getDetails());
        //--DISK
        Help4DevsDiskDetails disk = hardSys.resources().getDisk();
        System.out.println(disk.getDetails());
        //--BLUETOOTH
        Help4DevsBluetoothDetails bluetooth = hardSys.resources().getBluetooth();
        System.out.println(bluetooth.getDetails());
        //--VIDEO
        Help4DevsVideoDetails video = hardSys.resources().getVideo();
        System.out.println(video.getDetails());
        //--STORAGE
        Help4DevsStorageDetails storage = hardSys.resources().getStorage();
        System.out.println(storage.getDetails());
        //--BRIDGE
        Help4DevsBridgeDetails bridge = hardSys.resources().getBridge();
        System.out.println(bridge.getDetails());
        //--NETWORK-INTERFACE
        Help4DevsNetworkInterfaceDetails networkInterface = hardSys.resources().getNetworkInterface();
        System.out.println(networkInterface.getDetails());
        //--UNKNOWN
        Help4DevsUnknownDetails unknown = hardSys.resources().getUnknown();
        System.out.println(unknown.getDetails());
        //--DEVICES (GROUPS)
        Help4DevsDevicesGroupDetails devicesGroup = hardSys.resources().getDevicesGroup();
        System.out.println(devicesGroup.getDetails());
        //--NETWORK (GROUPS)
        Help4DevsNetworkGroupDetails networkGroup = hardSys.resources().getNetworksGroup();
        System.out.println(networkGroup.getDetails());
        //--DRIVES (GROUPS)
        Help4DevsDrivesGroupDetails drives = hardSys.resources().getDrivesGroup();
        System.out.println(drives.getDetails());
        //--COMPONENTS (GROUPS)
        Help4DevsComponentsGroupDetails components = hardSys.resources().getComponentsGroup();
        System.out.println(components.getDetails());
        //--BOARDS (GROUPS)
        Help4DevsBoardsGroupDetails boards = hardSys.resources().getBoardsGroup();
        System.out.println(boards.getDetails());
        //--HARDWARE (GROUPS)
        Help4DevsHardwareGroupDetails hardware = hardSys.resources().getHardwareGroup();
        System.out.println(hardware.getDetails());
    }

    @Test
    public void generalSystemInfoByHwinfoCommand_SINGLE_Test() {

        Help4DevsHardSys hardSys = new Help4DevsHardSys(HWINFO);
        hardSys.json();

        //SYSTEM
        Help4DevsSystemDetails system = hardSys.resources().getSystem();
        System.out.println(system.getDetails());
        //--MACHINE
        Help4DevsMachineDetails machine = hardSys.resources().getMachine();
        System.out.println(machine.getDetails());
        //--BATTERY
        Help4DevsBatteryDetails battery = hardSys.resources().getBattery();
        System.out.println(battery.getDetails());
        //--MEMORY
        Help4DevsMemoryDetails memory = hardSys.resources().getMemory();
        System.out.println(memory.getDetails());
        //--SLOTS
        Help4DevsSlotsDetails slots = hardSys.resources().getSlots();
        System.out.println(slots.getDetails());
        //--PROCESSOR
        Help4DevsProcessorDetails processor = hardSys.resources().getProcessor();
        System.out.println(processor.getDetails());
        //--GRAPHICS
        Help4DevsGraphicsDetails graphics = hardSys.resources().getGraphics();
        System.out.println(graphics.getDetails());
        //--AUDIO
        Help4DevsAudioDetails audio = hardSys.resources().getAudio();
        System.out.println(audio.getDetails());
        //--NETWORK
        Help4DevsNetworkDetails network = hardSys.resources().getNetwork();
        System.out.println(network.getDetails());
        //--DRIVERS
        Help4DevsDriversDetails drivers = hardSys.resources().getDrivers();
        System.out.println(drivers.getDetails());
        //--PARTITION
        Help4DevsPartitionDetails partition = hardSys.resources().getPartition();
        System.out.println(partition.getDetails());
        //--USB
        Help4DevsUsbDetails usb = hardSys.resources().getUsb();
        System.out.println(usb.getDetails());
        //--SENSORS
        Help4DevsSensorsDetails sensors = hardSys.resources().getSensors();
        System.out.println(sensors.getDetails());
        //--RUNNING
        Help4DevsRunningDetails running = hardSys.resources().getRunning();
        System.out.println(running.getDetails());
        //--MONITOR
        Help4DevsMonitorDetails monitor = hardSys.resources().getMonitor();
        System.out.println(monitor.getDetails());
        //--BIOS
        Help4DevsBiosDetails bios = hardSys.resources().getBios();
        System.out.println(bios.getDetails());
        //--BASEBOARD
        Help4DevsBaseboardDetails baseboard = hardSys.resources().getBaseboard();
        System.out.println(baseboard.getDetails());
        //--CHASSIS
        Help4DevsChassisDetails chassis = hardSys.resources().getChassis();
        System.out.println(chassis.getDetails());
        //--CACHE
        Help4DevsCacheDetails cache = hardSys.resources().getCache();
        System.out.println(cache.getDetails());
        //--CONNECTOR
        Help4DevsConnectorDetails connector = hardSys.resources().getConnector();
        System.out.println(connector.getDetails());
        //--KEYBOARD
        Help4DevsKeyboardDetails keyboard = hardSys.resources().getKeyboard();
        System.out.println(keyboard.getDetails());
        //--MOUSE
        Help4DevsMouseDetails mouse = hardSys.resources().getMouse();
        System.out.println(mouse.getDetails());
        //--HUB
        Help4DevsHubDetails hub = hardSys.resources().getHub();
        System.out.println(hub.getDetails());
        //--SWITCH
        Help4DevsSwitchDetails switcher = hardSys.resources().getSwitch();
        System.out.println(switcher.getDetails());
        //--MODEM
        Help4DevsModemDetails modem = hardSys.resources().getModem();
        System.out.println(modem.getDetails());
        //--DISK
        Help4DevsDiskDetails disk = hardSys.resources().getDisk();
        System.out.println(disk.getDetails());
        //--BLUETOOTH
        Help4DevsBluetoothDetails bluetooth = hardSys.resources().getBluetooth();
        System.out.println(bluetooth.getDetails());
        //--VIDEO
        Help4DevsVideoDetails video = hardSys.resources().getVideo();
        System.out.println(video.getDetails());
        //--STORAGE
        Help4DevsStorageDetails storage = hardSys.resources().getStorage();
        System.out.println(storage.getDetails());
        //--BRIDGE
        Help4DevsBridgeDetails bridge = hardSys.resources().getBridge();
        System.out.println(bridge.getDetails());
        //--NETWORK-INTERFACE
        Help4DevsNetworkInterfaceDetails networkInterface = hardSys.resources().getNetworkInterface();
        System.out.println(networkInterface.getDetails());
        //--UNKNOWN
        Help4DevsUnknownDetails unknown = hardSys.resources().getUnknown();
        System.out.println(unknown.getDetails());
    }

    @Test
    public void generalSystemInfoByHwinfoCommand_GROUP_Test() {

        Help4DevsHardSys hardSys = new Help4DevsHardSys(HWINFO);
        hardSys.json();

        //--DEVICES (GROUPS)
        Help4DevsDevicesGroupDetails devicesGroup = hardSys.resources().getDevicesGroup();
        System.out.println(devicesGroup.getDetails());
        //--NETWORK (GROUPS)
        Help4DevsNetworkGroupDetails networkGroup = hardSys.resources().getNetworksGroup();
        System.out.println(networkGroup.getDetails());
        //--DRIVES (GROUPS)
        Help4DevsDrivesGroupDetails drives = hardSys.resources().getDrivesGroup();
        System.out.println(drives.getDetails());
        //--COMPONENTS (GROUPS)
        Help4DevsComponentsGroupDetails components = hardSys.resources().getComponentsGroup();
        System.out.println(components.getDetails());
        //--BOARDS (GROUPS)
        Help4DevsBoardsGroupDetails boards = hardSys.resources().getBoardsGroup();
        System.out.println(boards.getDetails());
        //--HARDWARE (GROUPS)
        Help4DevsHardwareGroupDetails hardware = hardSys.resources().getHardwareGroup();
        System.out.println(hardware.getDetails());
    }

    @Test
    public void generalSystemInfoByHwinfoCommand_ALL_JSON_Test() {
        Help4DevsHardSys hardSys = new Help4DevsHardSys(HWINFO);
        hardSys.json();
        System.out.println(hardSys.resources().all());
    }

    @Test
    public void generalSystemInfoByHwinfoCommand_ALL_DTO_Test() {
        Help4DevsHardSys hardSys = new Help4DevsHardSys(HWINFO);
        Help4DevsHardSysResourcesDto result = hardSys.resources().builder();
        System.out.println(result);
    }

    @Test
    public void generalSystemInfoByLshwCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(LSHW);
        generalSystemInfo.resources();
    }

    @Test
    public void generalSystemInfoByLscpuCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(LSCPU);
        generalSystemInfo.resources();
    }

    @Test
    public void generalSystemInfoByLscpu2CommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(LSCPU2);
        generalSystemInfo.resources();
    }

    @Test
    public void generalSystemInfoByDmidecodeCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(DMIDECODE);
        generalSystemInfo.resources();
    }

    @Test
    public void generalSystemInfoBySysteminfoWindowsCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(SYSTEMINFO);
        generalSystemInfo.resources();
    }

}
