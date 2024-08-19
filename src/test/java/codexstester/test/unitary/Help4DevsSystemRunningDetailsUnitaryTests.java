package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.Help4DevsSystemService;
import com.huntercodexs.demo.system.Help4DevsSystemService.*;
import com.huntercodexs.demo.system.Help4DevsSystemService.GeneralSystemInfo.GeneralSystemInfoMetrics.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.ManagementPermission;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.system.Help4DevsSystemService.GeneralSystemInfo.AvailableCommands.*;

public class Help4DevsSystemRunningDetailsUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void availableCommandsTest() {
        String result = sysCmd(GeneralSystemInfo.AvailableCommands.INXI);
        codexsTesterAssertExact("inxi -Fxz --slots --memory --cpu --disk-full --graphics --machine --network --sensors --system --usb --info --color", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.HWINFO);
        codexsTesterAssertExact("hwinfo --short", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.LSHW);
        codexsTesterAssertExact("lshw -short", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.LSCPU);
        codexsTesterAssertExact("lscpu", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.LSCPU2);
        codexsTesterAssertExact("lshw -C cpu", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.DMIDECODE);
        codexsTesterAssertExact("dmidecode", result);

        result = sysCmd(GeneralSystemInfo.AvailableCommands.SYSTEMINFO_WINDOWS);
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
    public void managementMXBeanTest() throws UnknownHostException {

        System.getenv("PROCESSOR_IDENTIFIER");
        System.getenv("PROCESSOR_ARCHITECTURE");
        System.getenv("PROCESSOR_ARCHITEW6432");
        System.getenv("NUMBER_OF_PROCESSORS");

        for (Object propertyKeyName : System.getProperties().keySet()) {
            System.out.println(propertyKeyName + " - " + System.getProperty(propertyKeyName.toString()));
        }

        InetAddress ip = InetAddress.getLocalHost();
        ip.getHostAddress();

        System.getProperties().list(System.out);

        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getInit();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getCommitted();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax();

        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit();
        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted();
        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();

        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getInit();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getCommitted();
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax();

        ManagementFactory.getOperatingSystemMXBean();
        ManagementFactory.getOperatingSystemMXBean().getArch();
        ManagementFactory.getOperatingSystemMXBean().getName();
        ManagementFactory.getOperatingSystemMXBean().getVersion();
        ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
        ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();

        ManagementFactory.getRuntimeMXBean();
        ManagementFactory.getRuntimeMXBean().getName();
        ManagementFactory.getRuntimeMXBean().getClassPath();
        ManagementFactory.getRuntimeMXBean().getBootClassPath();
        ManagementFactory.getRuntimeMXBean().getManagementSpecVersion();
        ManagementFactory.getRuntimeMXBean().getLibraryPath();
        ManagementFactory.getRuntimeMXBean().getSpecName();
        ManagementFactory.getRuntimeMXBean().getUptime();
        ManagementFactory.getRuntimeMXBean().getVmName();
        ManagementFactory.getRuntimeMXBean().getVmVendor();
        ManagementFactory.getRuntimeMXBean().getVmVersion();
        ManagementFactory.getRuntimeMXBean().getSystemProperties().forEach((k, v) -> {

        });

        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        os.getTotalSwapSpaceSize();
        os.getSystemLoadAverage();

        ManagementFactory.getThreadMXBean();
        ManagementFactory.getThreadMXBean().getAllThreadIds();

        ManagementPermission permission = new ManagementPermission("monitor");
        permission.newPermissionCollection();
    }

    @Test
    public void timerSystemTest() {

        TimerInfo timerInfo = new TimerInfo();

        System.out.println("START: " + timerInfo.metrics().getStart());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("CURRENT: " + timerInfo.metrics().getCurrent());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timerInfo.timerSpec(TimerInfo.TimerSpec.SEC, 2);
        System.out.println("TOTAL: " + timerInfo.metrics().getTotal());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timerInfo.finish();
        System.out.println("END: " + timerInfo.metrics().getEnd());
    }

    @Test
    public void sysInfoTest() {
        JavaInfo javaInfo = new JavaInfo();
        System.out.println(javaInfo.info().getJavaVersion());
        System.out.println(javaInfo.info().toString());
    }

    @Test
    public void memoryTest() {

        int sizeTest = 1000000000;
        int[] arrayTest = new int[sizeTest];

        Help4DevsSystemService.MemoryInfo memoryInfo = new Help4DevsSystemService.MemoryInfo();
        memoryInfo.memorySpec(Help4DevsSystemService.MemoryInfo.MemorySpec.AUTO);

        String total = memoryInfo.total();
        System.out.println("TOTAL MEMORY: " + total);

        String using = memoryInfo.using();
        System.out.println("USING 1: " + using);

        for (int h = 0; h < 4; h++) {
            for (int i = 0; i < sizeTest; i++) {
                arrayTest[i] = i + 1;
                if (i % 2 == i) {
                    arrayTest[i] = i + 10;
                }
            }
        }

        using = memoryInfo.using();
        System.out.println("USING 2: " + using);

        for (int i = 0; i < sizeTest; i++) {
            arrayTest[i] = i + 1;
            if (i % 2 == i) {
                arrayTest[i] = i + 10;
            }
        }

        using = memoryInfo.using();
        System.out.println("USING 3: " + using);

        System.out.println("MEMORY INFO - TO STRING");
        memoryInfo.finished();
        System.out.println(memoryInfo.metrics());
        System.out.println("MEMORY INFO - ONE BY ONE");
        System.out.println(memoryInfo.metrics().getTotal());
        System.out.println(memoryInfo.metrics().getStart());
        System.out.println(memoryInfo.metrics().getCurrent());
        System.out.println(memoryInfo.metrics().getEnd());
        System.out.println(memoryInfo.metrics().getFree());
        System.out.println(memoryInfo.metrics().getUsed());

    }

    @Test
    public void hardDriveTest() {

        HardDriveInfo hardDriveInfo = new HardDriveInfo();

        System.out.println("-- HARD DRIVE - TO STRING --");
        System.out.println(hardDriveInfo.metrics());

        System.out.println("-- HARD DRIVE - ONE BY ONE --");
        System.out.println(hardDriveInfo.metrics().getAbsPath());
        System.out.println(hardDriveInfo.metrics().getTotal());
        System.out.println(hardDriveInfo.metrics().getFree());
        System.out.println(hardDriveInfo.metrics().getUsed());

    }

    @Test
    public void processorTest() {

        Help4DevsSystemService.ProcessorInfo processorInfo = new Help4DevsSystemService.ProcessorInfo();

        System.out.println("-- PROCESSOR INFO - TO STRING --");
        System.out.println(processorInfo.metrics());

        System.out.println("-- PROCESSOR INFO - ONE BY ONE --");
        System.out.println(processorInfo.metrics().getName());
        System.out.println(processorInfo.metrics().getArch());
        System.out.println(processorInfo.metrics().getVendor());
        System.out.println(processorInfo.metrics().getCores());
        System.out.println(processorInfo.metrics().getCpus());
        System.out.println(processorInfo.metrics().getMhz());
        System.out.println(processorInfo.metrics().getVirtualization());
        System.out.println(processorInfo.metrics().getL1Cache());
        System.out.println(processorInfo.metrics().getL2Cache());
        System.out.println(processorInfo.metrics().getL3Cache());

    }

    @Test
    public void jVirtualMachineInfoTest() {

        JVirtualMachineInfo jVirtualMachineInfo = new JVirtualMachineInfo();

        System.out.println("-- JAVA VIRTUAL MACHINE INFO - TO STRING --");
        System.out.println(jVirtualMachineInfo.metrics());

        System.out.println("-- JAVA VIRTUAL MACHINE INFO - ONE BY ONE --");
        System.out.println(jVirtualMachineInfo.metrics().getJavaSpecificationVersion());
        System.out.println(jVirtualMachineInfo.metrics().getJavaVMSpecificationVendor());
        System.out.println(jVirtualMachineInfo.metrics().getJavaVMSpecificationName());
        System.out.println(jVirtualMachineInfo.metrics().getJavaVMVersion());
        System.out.println(jVirtualMachineInfo.metrics().getJavaVMVendor());
        System.out.println(jVirtualMachineInfo.metrics().getJavaVMName());

    }

    @Test
    public void operationSystemInfoTest() {

        OperationSystemInfo operationSystemInfo = new OperationSystemInfo();

        System.out.println("-- OPERATION SYSTEM INFO - TO STRING --");
        System.out.println(operationSystemInfo.metrics());

        System.out.println("-- OPERATION SYSTEM INFO - ONE BY ONE --");
        System.out.println(operationSystemInfo.metrics().getOsName());
        System.out.println(operationSystemInfo.metrics().getOsArch());
        System.out.println(operationSystemInfo.metrics().getOsVersion());
        System.out.println(operationSystemInfo.metrics().getOsFileSeparator());
        System.out.println(operationSystemInfo.metrics().getOsPathSeparator());
        System.out.println(operationSystemInfo.metrics().getOsLineBreak());
        System.out.println(operationSystemInfo.metrics().getUserName());
        System.out.println(operationSystemInfo.metrics().getUserHome());
        System.out.println(operationSystemInfo.metrics().getUserDir());

    }

    @Test
    public void generalSystemInfoByInxiCommandTest() {

        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(INXI);
        generalSystemInfo.metrics();

        //SYSTEM
        SystemDetails system = generalSystemInfo.metrics().getSystem();
        System.out.println(system.getDetails());
        //--MACHINE
        MachineDetails machine = generalSystemInfo.metrics().getMachine();
        System.out.println(machine.getDetails());
        //--BATTERY
        BatteryDetails battery = generalSystemInfo.metrics().getBattery();
        System.out.println(battery.getDetails());
        //--MEMORY
        MemoryDetails memory = generalSystemInfo.metrics().getMemory();
        System.out.println(memory.getDetails());
        //--SLOTS
        SlotsDetails slots = generalSystemInfo.metrics().getSlots();
        System.out.println(slots.getDetails());
        //--PROCESSOR
        ProcessorDetails processor = generalSystemInfo.metrics().getProcessor();
        System.out.println(processor.getDetails());
        //--GRAPHICS
        GraphicsDetails graphics = generalSystemInfo.metrics().getGraphics();
        System.out.println(graphics.getDetails());
        //--AUDIO
        AudioDetails audio = generalSystemInfo.metrics().getAudio();
        System.out.println(audio.getDetails());
        //--NETWORK
        NetworkDetails network = generalSystemInfo.metrics().getNetwork();
        System.out.println(network.getDetails());
        //--DRIVERS
        DriversDetails drivers = generalSystemInfo.metrics().getDrivers();
        System.out.println(drivers.getDetails());
        //--PARTITION
        PartitionDetails partition = generalSystemInfo.metrics().getPartition();
        System.out.println(partition.getDetails());
        //--USB
        UsbDetails usb = generalSystemInfo.metrics().getUsb();
        System.out.println(usb.getDetails());
        //--SENSORS
        SensorsDetails sensors = generalSystemInfo.metrics().getSensors();
        System.out.println(sensors.getDetails());
        //--RUNNING
        RunningDetails running = generalSystemInfo.metrics().getRunning();
        System.out.println(running.getDetails());
        //--MONITOR
        MonitorDetails monitor = generalSystemInfo.metrics().getMonitor();
        System.out.println(monitor.getDetails());
        //--BIOS
        BiosDetails bios = generalSystemInfo.metrics().getBios();
        System.out.println(bios.getDetails());
        //--BASEBOARD
        BaseboardDetails baseboard = generalSystemInfo.metrics().getBaseboard();
        System.out.println(baseboard.getDetails());
        //--CHASSIS
        ChassisDetails chassis = generalSystemInfo.metrics().getChassis();
        System.out.println(chassis.getDetails());
        //--CACHE
        CacheDetails cache = generalSystemInfo.metrics().getCache();
        System.out.println(cache.getDetails());
        //--CONNECTOR
        ConnectorDetails connector = generalSystemInfo.metrics().getConnector();
        System.out.println(connector.getDetails());
        //--DRIVES
        DrivesDetails drives = generalSystemInfo.metrics().getDrives();
        System.out.println(drives.getDetails());

    }

    @Test
    public void generalSystemInfoByHwinfoCommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(HWINFO);
        generalSystemInfo.metrics();
    }

    @Test
    public void generalSystemInfoByLshwCommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(LSHW);
        generalSystemInfo.metrics();
    }

    @Test
    public void generalSystemInfoByLscpuCommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(LSCPU);
        generalSystemInfo.metrics();
    }

    @Test
    public void generalSystemInfoByLscpu2CommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(LSCPU2);
        generalSystemInfo.metrics();
    }

    @Test
    public void generalSystemInfoByDmidecodeCommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(DMIDECODE);
        generalSystemInfo.metrics();
    }

    @Test
    public void generalSystemInfoBySysteminfoWindowsCommandTest() {
        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo(SYSTEMINFO_WINDOWS);
        generalSystemInfo.metrics();
    }

}
