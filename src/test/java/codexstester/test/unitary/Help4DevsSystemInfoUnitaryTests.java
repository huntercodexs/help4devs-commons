package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.Help4DevsSystemService.*;
import com.huntercodexs.demo.system.Help4DevsSystemService.GeneralSystemInfo.GeneralSystemInfoMetrics.*;
import org.junit.Test;

public class Help4DevsSystemInfoUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void timerSystemTest() {

        TimerInfo timerInfo = new TimerInfo();

        System.out.println("START: "+ timerInfo.metrics().getStart());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("CURRENT: "+ timerInfo.metrics().getCurrent());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timerInfo.timerSpec(TimerInfo.TimerSpec.SEC, 2);
        System.out.println("TOTAL: "+ timerInfo.metrics().getTotal());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timerInfo.finish();
        System.out.println("END: "+ timerInfo.metrics().getEnd());
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

        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.memorySpec(MemoryInfo.MemorySpec.AUTO);

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
            if (i%2 == i) {
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

        ProcessorInfo processorInfo = new ProcessorInfo();

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
    public void generalSystemInfoTest() {

        GeneralSystemInfo generalSystemInfo = new GeneralSystemInfo();

        generalSystemInfo.metrics();

        SystemInfo systemInfo = generalSystemInfo.metrics().getSystemInfo();
        System.out.println(systemInfo.toString());

        Memory memory = generalSystemInfo.metrics().getMemory();
        System.out.println(memory.toString());

        Processor processor = generalSystemInfo.metrics().getProcessor();
        System.out.println(processor.toString());

        Bios bios = generalSystemInfo.metrics().getBios();
        System.out.println(bios.toString());

        Baseboard baseboard = generalSystemInfo.metrics().getBaseboard();
        System.out.println(baseboard.toString());

        Chassis chassis = generalSystemInfo.metrics().getChassis();
        System.out.println(chassis.toString());

        Cache cache = generalSystemInfo.metrics().getCache();
        System.out.println(cache.toString());

        Connector connector = generalSystemInfo.metrics().getConnector();
        System.out.println(connector.toString());

        Slot slot = generalSystemInfo.metrics().getSlot();
        System.out.println(slot.toString());
    }

}
