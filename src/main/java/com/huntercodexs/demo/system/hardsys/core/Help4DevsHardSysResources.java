package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import com.huntercodexs.demo.system.hardsys.group.*;
import com.huntercodexs.demo.system.hardsys.processing.*;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysResources extends Help4DevsHardSysBase implements Help4DevsHardSysInterface {

    private boolean jsonOn = false;
    private Help4DevsSystemDetails systemDetails;
    private Help4DevsMachineDetails machineDetails;
    private Help4DevsBatteryDetails batteryDetails;
    private Help4DevsMemoryDetails memoryDetails;
    private Help4DevsSlotsDetails slotsDetails;
    private Help4DevsProcessorDetails processorDetails;
    private Help4DevsGraphicsDetails graphicsDetails;
    private Help4DevsAudioDetails audioDetails;
    private Help4DevsNetworkDetails networkDetails;
    private Help4DevsDriversDetails driversDetails;
    private Help4DevsPartitionDetails partitionDetails;
    private Help4DevsCdRomDetails cdRomDetails;
    private Help4DevsUsbDetails usbDetails;
    private Help4DevsSensorsDetails sensorsDetails;
    private Help4DevsRunningDetails runningDetails;
    private Help4DevsMonitorDetails monitorDetails;
    private Help4DevsBiosDetails biosDetails;
    private Help4DevsBaseboardDetails baseboardDetails;
    private Help4DevsChassisDetails chassisDetails;
    private Help4DevsCacheDetails cacheDetails;
    private Help4DevsConnectorDetails connectorDetails;
    private Help4DevsKeyboardDetails keyboardDetails;
    private Help4DevsMouseDetails mouseDetails;
    private Help4DevsHubDetails hubDetails;
    private Help4DevsSwitchDetails switchDetails;
    private Help4DevsModemDetails modemDetails;
    private Help4DevsDiskDetails diskDetails;
    private Help4DevsBluetoothDetails bluetoothDetails;
    private Help4DevsVideoDetails videoDetails;
    private Help4DevsStorageDetails storageDetails;
    private Help4DevsBridgeDetails bridgeDetails;
    private Help4DevsNicInterfaceDetails nicInterfaceDetails;
    private Help4DevsUnknownDetails unknownDetails;
    private Help4DevsMultimediaDetails multimediaDetails;
    private Help4DevsPrinterDetails printerDetails;
    private Help4DevsDevicesGroupDetails devicesGroupDetails;
    private Help4DevsNetworkGroupDetails networksGroupDetails;
    private Help4DevsDrivesGroupDetails drivesGroupDetails;
    private Help4DevsComponentsGroupDetails componentsGroupDetails;
    private Help4DevsBoardsGroupDetails boardsGroupDetails;
    private Help4DevsHardwareGroupDetails hardwareGroupDetails;
    private Help4DevsAllGroupDetails allGroupDetails;

    /**
     * @implNote Use for JSON cases
     * */
    public Help4DevsHardSysResources(
            Help4DevsHardSysCommands command,
            HashMap<String, List<String>> resources
    ) {
        this.jsonOn = true;
        this.command = command;
        this.resources = resources;
        this.jsonBuilder();
    }

    /**
     * @implNote Use for DTO cases
     * */
    public Help4DevsHardSysResources(
            HashMap<String, Object> transport,
            Help4DevsHardSysCommands command
    ) {
        this.jsonOn = false;
        this.command = command;
        this.transport = transport;
    }

    private void jsonBuilder() {
        //See the HARDSYS to get more details
        this.systemDetails = new Help4DevsSystemDetails(this.resources.get(hardsysCheck("system")), this.command);
        this.machineDetails = new Help4DevsMachineDetails(this.resources.get(hardsysCheck("machine")), this.command);
        this.batteryDetails = new Help4DevsBatteryDetails(this.resources.get(hardsysCheck("battery")), this.command);
        this.memoryDetails = new Help4DevsMemoryDetails(this.resources.get(hardsysCheck("memory")), this.command);
        this.slotsDetails = new Help4DevsSlotsDetails(this.resources.get(hardsysCheck("slots")), this.command);
        this.processorDetails = new Help4DevsProcessorDetails(this.resources.get(hardsysCheck("processor")), this.command);
        this.graphicsDetails = new Help4DevsGraphicsDetails(this.resources.get(hardsysCheck("graphics")), this.command);
        this.audioDetails = new Help4DevsAudioDetails(this.resources.get(hardsysCheck("audio")), this.command);
        this.networkDetails = new Help4DevsNetworkDetails(this.resources.get(hardsysCheck("network")), this.command);
        this.driversDetails = new Help4DevsDriversDetails(this.resources.get(hardsysCheck("drivers")), this.command);
        this.partitionDetails = new Help4DevsPartitionDetails(this.resources.get(hardsysCheck("partition")), this.command);
        this.cdRomDetails = new Help4DevsCdRomDetails(this.resources.get(hardsysCheck("cdrom")), this.command);
        this.usbDetails = new Help4DevsUsbDetails(this.resources.get(hardsysCheck("usb")), this.command);
        this.sensorsDetails = new Help4DevsSensorsDetails(this.resources.get(hardsysCheck("sensors")), this.command);
        this.runningDetails = new Help4DevsRunningDetails(this.resources.get(hardsysCheck("running")), this.command);
        this.monitorDetails = new Help4DevsMonitorDetails(this.resources.get(hardsysCheck("monitor")), this.command);
        this.biosDetails = new Help4DevsBiosDetails(this.resources.get(hardsysCheck("bios")), this.command);
        this.baseboardDetails = new Help4DevsBaseboardDetails(this.resources.get(hardsysCheck("baseboard")), this.command);
        this.chassisDetails = new Help4DevsChassisDetails(this.resources.get(hardsysCheck("chassis")), this.command);
        this.cacheDetails = new Help4DevsCacheDetails(this.resources.get(hardsysCheck("cache")), this.command);
        this.connectorDetails = new Help4DevsConnectorDetails(this.resources.get(hardsysCheck("connector")), this.command);
        this.keyboardDetails = new Help4DevsKeyboardDetails(this.resources.get(hardsysCheck("keyboard")), this.command);
        this.mouseDetails = new Help4DevsMouseDetails(this.resources.get(hardsysCheck("mouse")), this.command);
        this.hubDetails = new Help4DevsHubDetails(this.resources.get(hardsysCheck("hub")), this.command);
        this.switchDetails = new Help4DevsSwitchDetails(this.resources.get(hardsysCheck("switcher")), this.command);
        this.modemDetails = new Help4DevsModemDetails(this.resources.get(hardsysCheck("modem")), this.command);
        this.diskDetails = new Help4DevsDiskDetails(this.resources.get(hardsysCheck("disk")), this.command);
        this.bluetoothDetails = new Help4DevsBluetoothDetails(this.resources.get(hardsysCheck("bluetooth")), this.command);
        this.videoDetails = new Help4DevsVideoDetails(this.resources.get(hardsysCheck("video")), this.command);
        this.storageDetails = new Help4DevsStorageDetails(this.resources.get(hardsysCheck("storage")), this.command);
        this.bridgeDetails = new Help4DevsBridgeDetails(this.resources.get(hardsysCheck("bridge")), this.command);
        this.nicInterfaceDetails = new Help4DevsNicInterfaceDetails(this.resources.get(hardsysCheck("nicInterface")), this.command);
        this.unknownDetails = new Help4DevsUnknownDetails(this.resources.get(hardsysCheck("unknown")), this.command);
        this.multimediaDetails = new Help4DevsMultimediaDetails(this.resources.get(hardsysCheck("multimedia")), this.command);
        this.printerDetails = new Help4DevsPrinterDetails(this.resources.get(hardsysCheck("printer")), this.command);
        this.devicesGroupDetails = new Help4DevsDevicesGroupDetails(this.resources.get(hardsysCheck("devicesGroup")), this.command);
        this.networksGroupDetails = new Help4DevsNetworkGroupDetails(this.resources.get(hardsysCheck("networksGroup")), this.command);
        this.drivesGroupDetails = new Help4DevsDrivesGroupDetails(this.resources.get(hardsysCheck("drivesGroup")), this.command);
        this.componentsGroupDetails = new Help4DevsComponentsGroupDetails(this.resources.get(hardsysCheck("componentsGroup")), this.command);
        this.boardsGroupDetails = new Help4DevsBoardsGroupDetails(this.resources.get(hardsysCheck("boardsGroup")), this.command);
        this.hardwareGroupDetails = new Help4DevsHardwareGroupDetails(this.resources.get(hardsysCheck("hardwareGroup")), this.command);
        this.allGroupDetails = new Help4DevsAllGroupDetails(this, this.command);
    }

    private void checkJsonState() {
        if (!this.jsonOn) {
            throw new RuntimeException("Invalid Operation, please use jsonOn = true");
        }
    }

    public Help4DevsSystemDetails getSystem() {
        checkJsonState();
        return systemDetails;
    }

    public Help4DevsMachineDetails getMachine() {
        checkJsonState();
        return machineDetails;
    }

    public Help4DevsBatteryDetails getBattery() {
        checkJsonState();
        return batteryDetails;
    }

    public Help4DevsMemoryDetails getMemory() {
        checkJsonState();
        return memoryDetails;
    }

    public Help4DevsSlotsDetails getSlots() {
        checkJsonState();
        return slotsDetails;
    }

    public Help4DevsProcessorDetails getProcessor() {
        checkJsonState();
        return processorDetails;
    }

    public Help4DevsGraphicsDetails getGraphics() {
        checkJsonState();
        return graphicsDetails;
    }

    public Help4DevsAudioDetails getAudio() {
        checkJsonState();
        return audioDetails;
    }

    public Help4DevsNetworkDetails getNetwork() {
        checkJsonState();
        return networkDetails;
    }

    public Help4DevsDriversDetails getDrivers() {
        checkJsonState();
        return driversDetails;
    }

    public Help4DevsPartitionDetails getPartition() {
        checkJsonState();
        return partitionDetails;
    }

    public Help4DevsCdRomDetails getCdRom() {
        checkJsonState();
        return cdRomDetails;
    }

    public Help4DevsUsbDetails getUsb() {
        checkJsonState();
        return usbDetails;
    }

    public Help4DevsSensorsDetails getSensors() {
        checkJsonState();
        return sensorsDetails;
    }

    public Help4DevsRunningDetails getRunning() {
        checkJsonState();
        return runningDetails;
    }

    public Help4DevsMonitorDetails getMonitor() {
        checkJsonState();
        return monitorDetails;
    }

    public Help4DevsBiosDetails getBios() {
        checkJsonState();
        return biosDetails;
    }

    public Help4DevsBaseboardDetails getBaseboard() {
        checkJsonState();
        return baseboardDetails;
    }

    public Help4DevsChassisDetails getChassis() {
        checkJsonState();
        return chassisDetails;
    }

    public Help4DevsCacheDetails getCache() {
        checkJsonState();
        return cacheDetails;
    }

    public Help4DevsConnectorDetails getConnector() {
        checkJsonState();
        return connectorDetails;
    }

    public Help4DevsKeyboardDetails getKeyboard() {
        checkJsonState();
        return keyboardDetails;
    }

    public Help4DevsMouseDetails getMouse() {
        checkJsonState();
        return mouseDetails;
    }

    public Help4DevsHubDetails getHub() {
        checkJsonState();
        return hubDetails;
    }

    public Help4DevsSwitchDetails getSwitch() {
        checkJsonState();
        return switchDetails;
    }

    public Help4DevsModemDetails getModem() {
        checkJsonState();
        return modemDetails;
    }

    public Help4DevsDiskDetails getDisk() {
        checkJsonState();
        return diskDetails;
    }

    public Help4DevsBluetoothDetails getBluetooth() {
        checkJsonState();
        return bluetoothDetails;
    }

    public Help4DevsVideoDetails getVideo() {
        checkJsonState();
        return videoDetails;
    }

    public Help4DevsStorageDetails getStorage() {
        checkJsonState();
        return storageDetails;
    }

    public Help4DevsBridgeDetails getBridge() {
        checkJsonState();
        return bridgeDetails;
    }

    public Help4DevsNicInterfaceDetails getNicInterface() {
        checkJsonState();
        return nicInterfaceDetails;
    }

    public Help4DevsUnknownDetails getUnknown() {
        checkJsonState();
        return unknownDetails;
    }

    public Help4DevsMultimediaDetails getMultimedia() {
        checkJsonState();
        return multimediaDetails;
    }

    public Help4DevsPrinterDetails getPrinter() {
        checkJsonState();
        return printerDetails;
    }

    public Help4DevsDevicesGroupDetails getDevicesGroup() {
        checkJsonState();
        return devicesGroupDetails;
    }

    public Help4DevsNetworkGroupDetails getNetworksGroup() {
        checkJsonState();
        return networksGroupDetails;
    }

    public Help4DevsDrivesGroupDetails getDrivesGroup() {
        checkJsonState();
        return this.drivesGroupDetails;
    }

    public Help4DevsComponentsGroupDetails getComponentsGroup() {
        checkJsonState();
        return this.componentsGroupDetails;
    }

    public Help4DevsBoardsGroupDetails getBoardsGroup() {
        checkJsonState();
        return this.boardsGroupDetails;
    }

    public Help4DevsHardwareGroupDetails getHardwareGroup() {
        checkJsonState();
        return this.hardwareGroupDetails;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">all</h6>
     *
     * <p style="color: #CDCDCD">Get all data from linux command in JSON Format</p>
     *
     * <p>
     *     To active the JSON Format output you need to set the jsonOn as follow:<br />
     *     <pre>
     *         Help4DevsHardSys hardSys = new Help4DevsHardSys({Help4DevsHardSysCommands});
     *         hardSys.json();
     *         hardSys.resources().getAll();
     *     </pre>
     * </p>
     *
     * @return String (JSON Format)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String all() {
        checkJsonState();
        return this.allGroupDetails.getDetails();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">builder</h6>
     *
     * <p style="color: #CDCDCD">Get all data from linux command in DTO Format</p>
     *
     * <p>
     *     Usage:<br />
     *     <pre>
     *         Help4DevsHardSys hardSys = new Help4DevsHardSys({Help4DevsHardSysCommands});
     *         Help4DevsHardSysMetricsDto result = hardSys.resources().builder();
     *         System.out.println(result);
     *     </pre>
     * </p>
     *
     * @return Help4DevsHardSysMetricsDto (DTO Format)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Help4DevsHardSysResourcesDto builder() {
        if (this.jsonOn) {
            throw new RuntimeException("Invalid Operation, please use jsonOn = false");
        }
        return new Help4DevsHardSysBuilder(this.transport).build();
    }

    /**
     * TODO
     * <h6 style="color: #FFFF00; font-size: 11px">nonNull</h6>
     *
     * <p style="color: #CDCDCD">Get all data from linux command in DTO Format ignoring null values</p>
     *
     * <p>
     *     Usage:<br />
     *     <pre>
     *         Help4DevsHardSys hardSys = new Help4DevsHardSys({Help4DevsHardSysCommands});
     *         Help4DevsHardSysResourcesDto nonNull = hardSys.resources().nonNull();
     *         System.out.println(result);
     *     </pre>
     * </p>
     *
     * @return Help4DevsHardSysMetricsDto (DTO Format)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Help4DevsHardSysResourcesDto nonNull() {
        if (this.jsonOn) {
            throw new RuntimeException("Invalid Operation, please use jsonOn = false");
        }
        return new Help4DevsHardSysBuilder(this.transport).buildNonNull();
    }

}
