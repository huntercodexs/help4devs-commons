package com.huntercodexs.demo.services.system.hardsys.core;

import com.huntercodexs.demo.services.stdout.Help4DevsStdoutService;
import com.huntercodexs.demo.services.system.hardsys.Help4DevsHardSys;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsAudioDto.Help4DevsAudio;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsBaseboardDto.Help4DevsBaseboard;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsBatteryDto.Help4DevsBattery;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsBiosDto.Help4DevsBios;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsBluetoothDto.Help4DevsBluetooth;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsBridgeDto.Help4DevsBridge;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsCacheDto.Help4DevsCache;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsCdRomDto.Help4DevsCdRom;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsChassisDto.Help4DevsChassis;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsConnectorDto.Help4DevsConnector;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsControllerDto.Help4DevsController;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsDiskDto.Help4DevsDisk;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsDriversDto.Help4DevsDrivers;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsDrivesDto.Help4DevsDrives;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsGraphicsDto.Help4DevsGraphics;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsHubDto.Help4DevsHub;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsKeyboardDto.Help4DevsKeyboard;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsMachineDto.Help4DevsMachine;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsMemoryDto.Help4DevsMemory;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsModemDto.Help4DevsModem;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsMonitorDto.Help4DevsMonitor;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsMouseDto.Help4DevsMouse;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsMultimediaDto.Help4DevsMultimedia;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsNetworkDto.Help4DevsNetwork;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsNicInterfaceDto.Help4DevsNicInterface;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsPartitionDto.Help4DevsPartition;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsPrinterDto.Help4DevsPrinter;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsProcessorDto.Help4DevsProcessor;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsRunningDto.Help4DevsRunning;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsSensorsDto.Help4DevsSensors;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsSlotsDto.Help4DevsSlots;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsStorageDto.Help4DevsStorage;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsSwitchDto.Help4DevsSwitch;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsSystemDto.Help4DevsSystem;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsUnknownDto.Help4DevsUnknown;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsUsbDto.Help4DevsUsb;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsVideoDto.Help4DevsVideo;

import static com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands.*;

public class Help4DevsHardSysCliOutput extends Help4DevsHardSysBase {

    private final Help4DevsStdoutService stdout;
    private Help4DevsHardSys hardSys;
    private Help4DevsHardSysResourcesDto result;

    public Help4DevsHardSysCliOutput() {
        System.out.println("Load information: Please wait...");
        this.stdout = new Help4DevsStdoutService();
    }

    private void loader(String command) {

        switch (command) {
            case "inxi":
                this.hardSys = new Help4DevsHardSys(INXI);
                break;
            case "hwinfo":
                this.hardSys = new Help4DevsHardSys(HWINFO);
                break;
            case "lshw":
                this.hardSys = new Help4DevsHardSys(LSHW);
                break;
            case "auto":
                this.hardSys = new Help4DevsHardSys(AUTO);
                break;
            default:
                throw new RuntimeException("Invalid command for HARDSYS: " + command);
        }

        this.result = this.hardSys.resources().builder();
    }

    private void printerPrinter() {
        if (this.result.getPrinter() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Printer");

        for (Help4DevsPrinter detail : this.result.getPrinter().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void multimediaPrinter() {
        if (this.result.getMultimedia() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Multimedia");

        for (Help4DevsMultimedia detail : this.result.getMultimedia().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void unknownPrinter() {
        if (this.result.getUnknown() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Unknown");

        for (Help4DevsUnknown detail : this.result.getUnknown().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void nicInterfacePrinter() {
        if (this.result.getNicInterface() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "NIC Interface");

        for (Help4DevsNicInterface detail : this.result.getNicInterface().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Mac", detail.getMac(), "Mac address");
            this.stdout.drawItem("Ips", detail.getIps(), "IP List");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Status", detail.getStatus(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Address", detail.getAddress(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void bridgePrinter() {
        if (this.result.getBridge() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Bridge");

        for (Help4DevsBridge detail : this.result.getBridge().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void storagePrinter() {
        if (this.result.getStorage() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Storage");

        for (Help4DevsStorage detail : this.result.getStorage().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Size", detail.getSize(), "");
            this.stdout.drawItem("Used", detail.getUsed(), "");
            this.stdout.drawItem("Free", detail.getFree(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Partition", detail.getPartition(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void videoPrinter() {
        if (this.result.getVideo() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Video");

        for (Help4DevsVideo detail : this.result.getVideo().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Bus", detail.getBus(), "");
            this.stdout.drawItem("Vga", detail.getVga(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Memory", detail.getMemory(), "");
            this.stdout.drawItem("Board", detail.getBoard(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("OpenGL", detail.getOpenGL(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), "");
            this.stdout.drawItem("Renderer", detail.getRenderer(), "");
            this.stdout.drawItem("Resources", detail.getResources(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Capabilities", detail.getCapabilities(), "");
            this.stdout.drawItem("Devices", detail.getDevices(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void bluetoothPrinter() {
        if (this.result.getBluetooth() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Bluetooth");

        for (Help4DevsBluetooth detail : this.result.getBluetooth().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), "");
            this.stdout.drawItem("Revision", detail.getRevision(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void drivesPrinter() {
        if (this.result.getDrives() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Drives");

        for (Help4DevsDrives detail : this.result.getDrives().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Size", detail.getSize(), "");
            this.stdout.drawItem("Used", detail.getUsed(), "");
            this.stdout.drawItem("Free", detail.getFree(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Partition", detail.getPartition(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void diskPrinter() {
        if (this.result.getDisk() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Disk");

        for (Help4DevsDisk detail : this.result.getDisk().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Size", detail.getSize(), "");
            this.stdout.drawItem("Used", detail.getUsed(), "");
            this.stdout.drawItem("Free", detail.getFree(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Partition", detail.getPartition(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void modemPrinter() {
        if (this.result.getModem() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Modem");

        for (Help4DevsModem detail : this.result.getModem().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void switchPrinter() {
        if (this.result.getSwitcher() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Switch");

        for (Help4DevsSwitch detail : this.result.getSwitcher().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void hubPrinter() {
        if (this.result.getHub() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Hub");

        for (Help4DevsHub detail : this.result.getHub().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void mousePrinter() {
        if (this.result.getMouse() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Mouse");

        for (Help4DevsMouse detail : this.result.getMouse().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Series", detail.getSeries(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), detail.getSource());
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Layout", detail.getLayout(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Details", String.valueOf(detail.getDetails()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void keyboardPrinter() {
        if (this.result.getKeyboard() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Keyboard");

        for (Help4DevsKeyboard detail : this.result.getKeyboard().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Series", detail.getSeries(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), detail.getSource());
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Layout", detail.getLayout(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Details", String.valueOf(detail.getDetails()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void connectorPrinter() {
        if (this.result.getConnector() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Connector");

        for (Help4DevsConnector detail : this.result.getConnector().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Type", detail.getType(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void cachePrinter() {
        if (this.result.getCache() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Cache");

        for (Help4DevsCache detail : this.result.getCache().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Size", detail.getSize(), "Cache size");
            this.stdout.drawItem("Source", detail.getSource(), detail.getSource());
            this.stdout.drawItem("Socket", detail.getSocket(), "");
            this.stdout.drawItem("CacheL1", detail.getCacheL1(), "");
            this.stdout.drawItem("CacheL2", detail.getCacheL2(), "");
            this.stdout.drawItem("CacheL3", detail.getCacheL3(), "");
            this.stdout.drawItem("Location", detail.getLocation(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");
            this.stdout.drawItem("Configuration", detail.getConfiguration(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void chassisPrinter() {
        if (this.result.getChassis() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Chassis");

        for (Help4DevsChassis detail : this.result.getChassis().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), detail.getProduct());
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void controllerPrinter() {
        if (this.result.getController() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Controller");

        for (Help4DevsController detail : this.result.getController().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Bus", detail.getBus(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), detail.getProduct());
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void baseboardPrinter() {
        if (this.result.getBaseboard() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Baseboard");

        for (Help4DevsBaseboard detail : this.result.getBaseboard().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), detail.getProduct());
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void biosPrinter() {
        if (this.result.getBios() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "BIOS");

        for (Help4DevsBios detail : this.result.getBios().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), detail.getProduct());
            this.stdout.drawItem("Release", detail.getRelease(), "");
            this.stdout.drawItem("Firmware", detail.getFirmware(), "");
            this.stdout.drawItem("Revision", detail.getRevision(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Characteristics", String.valueOf(detail.getCharacteristics()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void monitorPrinter() {
        if (this.result.getMonitor() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Monitor");

        for (Help4DevsMonitor detail : this.result.getMonitor().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Size", detail.getSize(), "");
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Series", detail.getSeries(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void runningPrinter() {
        if (this.result.getRunning() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Running");

        for (Help4DevsRunning detail : this.result.getRunning().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Client", detail.getClient(), "");
            this.stdout.drawItem("Processes", detail.getProcesses(), "");
            this.stdout.drawItem("Uptime", detail.getUptime(), "");
            this.stdout.drawItem("Init", detail.getInit(), "");
            this.stdout.drawItem("Runlevel", detail.getRunlevel(), "");
            this.stdout.drawItem("Compilers", detail.getCompilers(), "");
            this.stdout.drawItem("gcc", detail.getGcc(), "");
            this.stdout.drawItem("Shell", detail.getShell(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Inxi", detail.getInxi(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void sensorsPrinter() {
        if (this.result.getSensors() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Sensors");

        for (Help4DevsSensors detail : this.result.getSensors().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Mobo", detail.getMobo(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Temperature", detail.getTemperature(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void usbPrinter() {
        if (this.result.getUsb() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "USB");

        for (Help4DevsUsb detail : this.result.getUsb().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Info", detail.getInfo(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void cdRomPrinter() {
        if (this.result.getCdRom() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "CD-ROM");

        for (Help4DevsCdRom detail : this.result.getCdRom().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void partitionPrinter() {
        if (this.result.getPartition() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Partition");

        for (Help4DevsPartition detail : this.result.getPartition().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Dev", detail.getDev(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Size", detail.getSize(), "");
            this.stdout.drawItem("Used", detail.getUsed(), "");
            this.stdout.drawItem("Free", detail.getFree(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Manufacture", detail.getManufacture(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void driversPrinter() {
        if (this.result.getDrivers() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Drivers");

        for (Help4DevsDrivers detail : this.result.getDrivers().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Names", detail.getNames(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Device", detail.getDevice(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void networkPrinter() {
        if (this.result.getNetwork() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Network");

        for (Help4DevsNetwork detail : this.result.getNetwork().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "Specific ID");
            this.stdout.drawItem("Mac", detail.getMac(), "Mac address");
            this.stdout.drawItem("Ips", detail.getIps(), "");
            this.stdout.drawItem("Name", detail.getName(), "");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "");
            this.stdout.drawItem("Ports", detail.getPorts(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Status", detail.getStatus(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Address", detail.getAddress(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "Extras characteristics");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void audioPrinter() {
        if (this.result.getAudio() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Audio/Sound");

        for (Help4DevsAudio detail : this.result.getAudio().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Bus", detail.getBus(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Model", detail.getModel(), detail.getModel());
            this.stdout.drawItem("Board", detail.getBoard(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Resources", detail.getResources(), "");
            this.stdout.drawItem("Multimedia", detail.getMultimedia(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");
            this.stdout.drawItem("Capabilities", detail.getCapabilities(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void graphicsPrinter() {
        if (this.result.getGraphics() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Graphics");

        for (Help4DevsGraphics detail : this.result.getGraphics().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Bus", detail.getBus(), "");
            this.stdout.drawItem("Vga", detail.getVga(), "");
            this.stdout.drawItem("Name", detail.getName(), detail.getName());
            this.stdout.drawItem("Model", detail.getModel(), detail.getModel());
            this.stdout.drawItem("Memory", detail.getMemory(), "");
            this.stdout.drawItem("Board", detail.getBoard(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Driver", detail.getDriver(), "");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Failed", detail.getFailed(), "");
            this.stdout.drawItem("OpenGL", detail.getOpenGL(), "");
            this.stdout.drawItem("Server", detail.getServer(), "");
            this.stdout.drawItem("Devices", detail.getDevices(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Display", detail.getDisplay(), "");
            this.stdout.drawItem("Product", detail.getProduct(), detail.getProduct());
            this.stdout.drawItem("Renderer", detail.getRenderer(), "");
            this.stdout.drawItem("Unloaded", detail.getUnloaded(), detail.getUnloaded());
            this.stdout.drawItem("Resources", detail.getResources(), "");
            this.stdout.drawItem("Frequency", detail.getFrequency(), "");
            this.stdout.drawItem("Multimedia", detail.getMultimedia(), "");
            this.stdout.drawItem("Resolution", detail.getResolution(), "");
            this.stdout.drawItem("Description", detail.getDescription(), detail.getDescription());
            this.stdout.drawItem("Capabilities", detail.getCapabilities(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void processorPrinter() {
        if (this.result.getProcessor() == null) return;

        int id = 0;
        String  cores = this.result.getProcessor().getCores();
        this.stdout.drawItemHeader("Resource", "Processor - " + cores + " cores");

        for (Help4DevsProcessor detail : this.result.getProcessor().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Core", String.valueOf(id), "");
            this.stdout.drawItem("Model", detail.getModel(), "Processor model");
            this.stdout.drawItem("Family", detail.getFamily(), "Processor family");
            this.stdout.drawItem("Speed", detail.getSpeed(), "Processor speed core in MHz/GHz");
            this.stdout.drawItem("Current", detail.getCurrent(), "Processor speed of current core");
            this.stdout.drawItem("Arch", detail.getArch(), "Processor architecture");
            this.stdout.drawItem("Topology", detail.getTopology(), "");
            this.stdout.drawItem("Bits", detail.getBits(), "");
            this.stdout.drawItem("Cache", detail.getCache(), "Cache total");
            this.stdout.drawItem("CacheL1", detail.getCacheL1(), "Cache L1");
            this.stdout.drawItem("CacheL2", detail.getCacheL2(), "Cache L2");
            this.stdout.drawItem("CacheL3", detail.getCacheL3(), "Cache L3");
            this.stdout.drawItem("CacheL4", detail.getCacheL4(), "Cache L4");
            this.stdout.drawItem("CacheL5", detail.getCacheL5(), "Cache L5");
            this.stdout.drawItem("Bogomips", detail.getBogomips(),
                    "Measurement of CPU speed made by the Linux kernel");
            this.stdout.drawItem("Flags", "", detail.getFlags());
            this.stdout.drawItem("MinSpeed", detail.getMinSpeed(), "");
            this.stdout.drawItem("MaxSpeed", detail.getMaxSpeed(), "");
            this.stdout.drawItem("Socket", detail.getSocket(), "Physical processor socket");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Clock", detail.getClock(), "");
            this.stdout.drawItem("Serial Number", detail.getSerialNumber(), "");
            this.stdout.drawItem("Characteristics", "Cores Speeds",
                    detail.getCharacteristics().replaceAll("Core Speeds - ", ""));

        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void slotsPrinter() {
        if (this.result.getSlots() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Slots");

        for (Help4DevsSlots detail : this.result.getSlots().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "");
            this.stdout.drawItem("Name", detail.getName(), "");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Device", detail.getDevice(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Location", detail.getLocation(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "Extras characteristics");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void memoryPrinter() {
        if (this.result.getMemory() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Memory");

        for (Help4DevsMemory detail : this.result.getMemory().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "Specific ID");
            this.stdout.drawItem("Name", detail.getName(), "");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Used", detail.getUsed(), "Memory quantity in use currently");
            this.stdout.drawItem("Free", detail.getFree(), "Memory quantity affordable to use");
            this.stdout.drawItem("Rank", detail.getRank(), "Memory location");
            this.stdout.drawItem("Total", detail.getTotal(), "Memory total of current machine");
            this.stdout.drawItem("Width", detail.getWidth(), "");
            this.stdout.drawItem("Speed", detail.getSpeed(), "Memory work speed");
            this.stdout.drawItem("Volts", detail.getVolts(), "");
            this.stdout.drawItem("Source", detail.getSource(), "Device location");
            this.stdout.drawItem("Vendor", detail.getVendor(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Location", detail.getLocation(), "");
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "Information about memory");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "Extras characteristics");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void batteryPrinter() {
        if (this.result.getBattery() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Battery");

        for (Help4DevsBattery detail : this.result.getBattery().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "Id", detail.getId(), "Specific ID");
            this.stdout.drawItem("Name", detail.getName(), "Battery name");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Vendor", detail.getVendor(), "Product owner");
            this.stdout.drawItem("Source", detail.getSource(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), "");
            this.stdout.drawItem("Charge", detail.getCharge(), "");
            this.stdout.drawItem("Status", detail.getStatus(), "");
            this.stdout.drawItem("Condition", detail.getCondition(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "Extras characteristics");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();
    }

    private void machinePrinter() {
        if (this.result.getMachine() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "Machine");

        for (Help4DevsMachine detail : this.result.getMachine().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "UEFI", detail.getUefi(), "Unified extensible firmware interface");
            this.stdout.drawItem("Name", detail.getName(), "Machine name");
            this.stdout.drawItem("Type", detail.getType(), "");
            this.stdout.drawItem("Date", detail.getDate(), "Fabrication date");
            this.stdout.drawItem("Mobo", detail.getMobo(), "");
            this.stdout.drawItem("Code", detail.getCode(), "");
            this.stdout.drawItem("Model", detail.getModel(), "");
            this.stdout.drawItem("Serial", detail.getSerial(), "");
            this.stdout.drawItem("Family", detail.getFamily(), "");
            this.stdout.drawItem("Version", detail.getVersion(), "");
            this.stdout.drawItem("Product", detail.getProduct(), "");
            this.stdout.drawItem("Description", detail.getDescription(), "");
            this.stdout.drawItem("Manufacturer", detail.getManufacturer(), "");

            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "Extras characteristics");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();

    }

    private void systemPrinter() {

        if (this.result.getSystem() == null) return;

        int id = 0;
        this.stdout.drawItemHeader("Resource", "System");

        for (Help4DevsSystem detail : this.result.getSystem().getDetails()) {

            id += 1;
            this.stdout.drawItemId(String.valueOf(id), "OS Name", detail.getName(), "");
            this.stdout.drawItem("OS Distro", detail.getDistro(), "");
            this.stdout.drawItem("OS Arch", detail.getArch(), "");
            this.stdout.drawItem("OS Home", detail.getHome(), "");
            this.stdout.drawItem("OS Date", detail.getDate(), "");
            this.stdout.drawItem("OS Root", "", detail.getRoot());
            this.stdout.drawItem("OS Vendor", detail.getVendor(), "");
            this.stdout.drawItem("OS Kernel", detail.getKernel(), "");
            this.stdout.drawItem("OS UserDir", "", detail.getUserdir());
            this.stdout.drawItem("OS Release", detail.getRelease(), "");
            this.stdout.drawItem("OS Version", detail.getVersion(), "");
            this.stdout.drawItem("OS Mounted", detail.getMounted(), "");
            this.stdout.drawItem("OS Username", detail.getUsername(), "");
            this.stdout.drawItem("OS Description", detail.getDescription(), "");
            this.stdout.drawItem("OS Manufacture", detail.getManufacturer(), "");
            this.stdout.drawItem("OS File Separator", detail.getFileSeparator(), "");
            this.stdout.drawItem("OS Path Separator", detail.getPathSeparator(), "");
            this.stdout.drawItem("OS Line Separator", detail.getLineSeparator(), "");

            this.stdout.drawItem("Java Version", detail.getJavaVersion(), "");
            this.stdout.drawItem("Java Vendor", detail.getJavaVendor(), "");
            this.stdout.drawItem("Java VendorUrl", detail.getJavaVendorUrl(), "");
            this.stdout.drawItem("Java Home", detail.getJavaHome(), "");
            this.stdout.drawItem("Java Spec Version", detail.getJavaSpecVersion(), "");
            this.stdout.drawItem("Java Spec Vendor", detail.getJavaSpecVendor(), "");
            this.stdout.drawItem("Java Spec Name", detail.getJavaSpecName(), "");
            this.stdout.drawItem("Java Class Version", detail.getJavaClassVersion(), "");
            this.stdout.drawItem("Java Class Path", "", detail.getJavaClassPath());
            this.stdout.drawItem("Java Lib Path", "", detail.getJavaLibPath());
            this.stdout.drawItem("Java Tmp Dir", detail.getJavaTmpDir(), "");
            this.stdout.drawItem("Java Compiler", detail.getJavaCompiler(), "");
            this.stdout.drawItem("Java Extend Dir", "", detail.getJavaExtDir());
            this.stdout.drawItem("Java VM Spec Version", detail.getJavaVmSpecVersion(), "");
            this.stdout.drawItem("Java VM Spec Vendor", detail.getJavaVmSpecVendor(), "");
            this.stdout.drawItem("Java VM Spec Name", detail.getJavaVmSpecName(), "");
            this.stdout.drawItem("Java VM Version", detail.getJavaVmVersion(), "");
            this.stdout.drawItem("Java VM Vendor", detail.getJavaVmVendor(), "");
            this.stdout.drawItem("Java VM Name", detail.getJavaVmName(), "");

            this.stdout.drawItem("Hotfix", String.valueOf(detail.getHotfix()), "");
            this.stdout.drawItem("Features", String.valueOf(detail.getFeatures()), "");
        }

        this.stdout.nextItem();
        this.stdout.nextItemHeader();

    }

    public void printer(String command, String title, int width) {

        loader(command);
        this.stdout.addWidth(width);
        this.stdout.drawHeader(title);

        systemPrinter();
        machinePrinter();
        batteryPrinter();
        memoryPrinter();
        slotsPrinter();
        processorPrinter();
        graphicsPrinter();
        audioPrinter();
        networkPrinter();
        driversPrinter();
        partitionPrinter();
        cdRomPrinter();
        usbPrinter();
        sensorsPrinter();
        runningPrinter();
        monitorPrinter();
        biosPrinter();
        baseboardPrinter();
        controllerPrinter();
        chassisPrinter();
        cachePrinter();
        connectorPrinter();
        keyboardPrinter();
        mousePrinter();
        hubPrinter();
        switchPrinter();
        modemPrinter();
        diskPrinter();
        drivesPrinter();
        bluetoothPrinter();
        videoPrinter();
        storagePrinter();
        bridgePrinter();
        nicInterfacePrinter();
        multimediaPrinter();
        printerPrinter();
        unknownPrinter();

    }

}
