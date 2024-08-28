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
    private Help4DevsNetworkInterfaceDetails networkInterfaceDetails;
    private Help4DevsUnknownDetails unknownDetails;
    private Help4DevsDevicesGroupDetails devicesGroupDetails;
    private Help4DevsNetworkGroupDetails networksGroupDetails;
    private Help4DevsDrivesGroupDetails drivesGroupDetails;
    private Help4DevsComponentsGroupDetails componentsGroupDetails;
    private Help4DevsBoardsGroupDetails boardsGroupDetails;
    private Help4DevsHardwareGroupDetails hardwareGroupDetails;
    private Help4DevsAllGroupDetails allGroupDetails;
    private HashMap<String, List<String>> resources;

    /**
     * @implNote Use for JSON cases
     * */
    public Help4DevsHardSysResources(
            Help4DevsHardSysCommands command,
            HashMap<String, List<String>> resources
    ) {
        this.jsonOn = true;
        this.command = command;
        this.jsonBuilder(resources, command);
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

    private void jsonBuilder(HashMap<String, List<String>> resources, Help4DevsHardSysCommands command) {
        //See the HARDSYS to get more details
        this.systemDetails = new Help4DevsSystemDetails(resources.get(hardsys("system")), command);
        this.machineDetails = new Help4DevsMachineDetails(resources.get(hardsys("machine")), command);
        this.batteryDetails = new Help4DevsBatteryDetails(resources.get(hardsys("battery")), command);
        this.memoryDetails = new Help4DevsMemoryDetails(resources.get(hardsys("memory")), command);
        this.slotsDetails = new Help4DevsSlotsDetails(resources.get(hardsys("slots")), command);
        this.processorDetails = new Help4DevsProcessorDetails(resources.get(hardsys("processor")), command);
        this.graphicsDetails = new Help4DevsGraphicsDetails(resources.get(hardsys("graphics")), command);
        this.audioDetails = new Help4DevsAudioDetails(resources.get(hardsys("audio")), command);
        this.networkDetails = new Help4DevsNetworkDetails(resources.get(hardsys("network")), command);
        this.driversDetails = new Help4DevsDriversDetails(resources.get(hardsys("drivers")), command);
        this.partitionDetails = new Help4DevsPartitionDetails(resources.get(hardsys("partition")), command);
        this.usbDetails = new Help4DevsUsbDetails(resources.get(hardsys("usb")), command);
        this.sensorsDetails = new Help4DevsSensorsDetails(resources.get(hardsys("sensors")), command);
        this.runningDetails = new Help4DevsRunningDetails(resources.get(hardsys("running")), command);
        this.monitorDetails = new Help4DevsMonitorDetails(resources.get(hardsys("monitor")), command);
        this.biosDetails = new Help4DevsBiosDetails(resources.get(hardsys("bios")), command);
        this.baseboardDetails = new Help4DevsBaseboardDetails(resources.get(hardsys("baseboard")), command);
        this.chassisDetails = new Help4DevsChassisDetails(resources.get(hardsys("chassis")), command);
        this.cacheDetails = new Help4DevsCacheDetails(resources.get(hardsys("cache")), command);
        this.connectorDetails = new Help4DevsConnectorDetails(resources.get(hardsys("connector")), command);
        this.keyboardDetails = new Help4DevsKeyboardDetails(resources.get(hardsys("keyboard")), command);
        this.mouseDetails = new Help4DevsMouseDetails(resources.get(hardsys("mouse")), command);
        this.hubDetails = new Help4DevsHubDetails(resources.get(hardsys("hub")), command);
        this.switchDetails = new Help4DevsSwitchDetails(resources.get(hardsys("switcher")), command);
        this.modemDetails = new Help4DevsModemDetails(resources.get(hardsys("modem")), command);
        this.diskDetails = new Help4DevsDiskDetails(resources.get(hardsys("disk")), command);
        this.bluetoothDetails = new Help4DevsBluetoothDetails(resources.get(hardsys("bluetooth")), command);
        this.videoDetails = new Help4DevsVideoDetails(resources.get(hardsys("video")), command);
        this.storageDetails = new Help4DevsStorageDetails(resources.get(hardsys("storage")), command);
        this.bridgeDetails = new Help4DevsBridgeDetails(resources.get(hardsys("bridge")), command);
        this.networkInterfaceDetails = new Help4DevsNetworkInterfaceDetails(resources.get(hardsys("networkInterface")), command);
        this.unknownDetails = new Help4DevsUnknownDetails(resources.get(hardsys("unknown")), command);
        this.devicesGroupDetails = new Help4DevsDevicesGroupDetails(resources.get(hardsys("devicesGroup")), command);
        this.networksGroupDetails = new Help4DevsNetworkGroupDetails(resources.get(hardsys("networksGroup")), command);
        this.drivesGroupDetails = new Help4DevsDrivesGroupDetails(resources.get(hardsys("drivesGroup")), command);
        this.componentsGroupDetails = new Help4DevsComponentsGroupDetails(resources.get(hardsys("componentsGroup")), command);
        this.boardsGroupDetails = new Help4DevsBoardsGroupDetails(resources.get(hardsys("boardsGroup")), command);
        this.hardwareGroupDetails = new Help4DevsHardwareGroupDetails(resources.get(hardsys("hardwareGroup")), command);
        this.allGroupDetails = new Help4DevsAllGroupDetails(this, command);
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

    public Help4DevsNetworkInterfaceDetails getNetworkInterface() {
        checkJsonState();
        return networkInterfaceDetails;
    }

    public Help4DevsUnknownDetails getUnknown() {
        checkJsonState();
        return unknownDetails;
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

}
