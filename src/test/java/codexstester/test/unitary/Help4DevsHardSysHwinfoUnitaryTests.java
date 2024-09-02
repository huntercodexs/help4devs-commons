package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.hardsys.Help4DevsHardSys;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import com.huntercodexs.demo.system.hardsys.group.*;
import com.huntercodexs.demo.system.hardsys.processing.*;
import org.junit.Test;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.HWINFO;

public class Help4DevsHardSysHwinfoUnitaryTests extends Help4DevsBridgeTests {

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
        Help4DevsNicInterfaceDetails networkInterface = hardSys.resources().getNicInterface();
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

//        Help4DevsHardSysResourcesDto nonNull = hardSys.resources().nonNull();
//        System.out.println(nonNull);
    }

}
