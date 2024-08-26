package com.huntercodexs.demo.system.hardsys.dto;

import com.huntercodexs.demo.system.hardsys.group.*;
import com.huntercodexs.demo.system.hardsys.processing.*;

import java.util.List;

public class Help4DevsHardSysResourcesDto {

    private String system;
    private Help4DevsMachineDetails machine;
    private Help4DevsBatteryDetails battery;
    private Help4DevsMemoryDetails memory;
    private Help4DevsSlotsDetails slots;
    private Help4DevsProcessorDto processor;
    private Help4DevsGraphicsDetails graphics;
    private Help4DevsAudioDetails audio;
    private Help4DevsNetworkDetails network;
    private Help4DevsDriversDetails drivers;
    private Help4DevsPartitionDetails partition;
    private Help4DevsUsbDetails usb;
    private Help4DevsSensorsDetails sensors;
    private Help4DevsRunningDetails running;
    private Help4DevsMonitorDetails monitor;
    private Help4DevsBiosDetails bios;
    private Help4DevsBaseboardDetails baseboard;
    private Help4DevsChassisDetails chassis;
    private Help4DevsCacheDetails cache;
    private Help4DevsConnectorDetails connector;
    private Help4DevsKeyboardDetails keyboard;
    private Help4DevsMouseDetails mouse;
    private Help4DevsHubDetails hub;
    private Help4DevsSwitchDetails switcher;
    private Help4DevsModemDetails modem;
    private Help4DevsDiskDetails disk;
    private Help4DevsBluetoothDetails bluetooth;
    private Help4DevsVideoDetails video;
    private Help4DevsStorageDetails storage;
    private Help4DevsBridgeDetails bridge;
    private Help4DevsNetworkInterfaceDetails networkInterface;
    private Help4DevsUnknownDetails unknown;
    private Help4DevsDevicesGroupDetails devicesGroup;
    private Help4DevsNetworkGroupDetails networksGroup;
    private Help4DevsDrivesGroupDetails drivesGroup;
    private Help4DevsComponentsGroupDetails componentsGroup;
    private Help4DevsBoardsGroupDetails boardsGroup;
    private Help4DevsHardwareGroupDetails hardwareGroup;

    public String getSystem() {
        return system;
    }

    public void setSystem(Help4DevsSystemDetails system) {
        this.system = system.getDetails();
    }

    public Help4DevsMachineDetails getMachine() {
        return machine;
    }

    public void setMachine(Help4DevsMachineDetails machine) {
        this.machine = machine;
    }

    public Help4DevsBatteryDetails getBattery() {
        return battery;
    }

    public void setBattery(Help4DevsBatteryDetails battery) {
        this.battery = battery;
    }

    public Help4DevsMemoryDetails getMemory() {
        return memory;
    }

    public void setMemory(Help4DevsMemoryDetails memory) {
        this.memory = memory;
    }

    public Help4DevsSlotsDetails getSlots() {
        return slots;
    }

    public void setSlots(Help4DevsSlotsDetails slots) {
        this.slots = slots;
    }

    public Help4DevsProcessorDto getProcessor() {
        return processor;
    }

    public void setProcessor(List<String> processor) {
        this.processor = new Help4DevsProcessorDto(processor);
    }

    public Help4DevsGraphicsDetails getGraphics() {
        return graphics;
    }

    public void setGraphics(Help4DevsGraphicsDetails graphics) {
        this.graphics = graphics;
    }

    public Help4DevsAudioDetails getAudio() {
        return audio;
    }

    public void setAudio(Help4DevsAudioDetails audio) {
        this.audio = audio;
    }

    public Help4DevsNetworkDetails getNetwork() {
        return network;
    }

    public void setNetwork(Help4DevsNetworkDetails network) {
        this.network = network;
    }

    public Help4DevsDriversDetails getDrivers() {
        return drivers;
    }

    public void setDrivers(Help4DevsDriversDetails drivers) {
        this.drivers = drivers;
    }

    public Help4DevsPartitionDetails getPartition() {
        return partition;
    }

    public void setPartition(Help4DevsPartitionDetails partition) {
        this.partition = partition;
    }

    public Help4DevsUsbDetails getUsb() {
        return usb;
    }

    public void setUsb(Help4DevsUsbDetails usb) {
        this.usb = usb;
    }

    public Help4DevsSensorsDetails getSensors() {
        return sensors;
    }

    public void setSensors(Help4DevsSensorsDetails sensors) {
        this.sensors = sensors;
    }

    public Help4DevsRunningDetails getRunning() {
        return running;
    }

    public void setRunning(Help4DevsRunningDetails running) {
        this.running = running;
    }

    public Help4DevsMonitorDetails getMonitor() {
        return monitor;
    }

    public void setMonitor(Help4DevsMonitorDetails monitor) {
        this.monitor = monitor;
    }

    public Help4DevsBiosDetails getBios() {
        return bios;
    }

    public void setBios(Help4DevsBiosDetails bios) {
        this.bios = bios;
    }

    public Help4DevsBaseboardDetails getBaseboard() {
        return baseboard;
    }

    public void setBaseboard(Help4DevsBaseboardDetails baseboard) {
        this.baseboard = baseboard;
    }

    public Help4DevsChassisDetails getChassis() {
        return chassis;
    }

    public void setChassis(Help4DevsChassisDetails chassis) {
        this.chassis = chassis;
    }

    public Help4DevsCacheDetails getCache() {
        return cache;
    }

    public void setCache(Help4DevsCacheDetails cache) {
        this.cache = cache;
    }

    public Help4DevsConnectorDetails getConnector() {
        return connector;
    }

    public void setConnector(Help4DevsConnectorDetails connector) {
        this.connector = connector;
    }

    public Help4DevsKeyboardDetails getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Help4DevsKeyboardDetails keyboard) {
        this.keyboard = keyboard;
    }

    public Help4DevsMouseDetails getMouse() {
        return mouse;
    }

    public void setMouse(Help4DevsMouseDetails mouse) {
        this.mouse = mouse;
    }

    public Help4DevsHubDetails getHub() {
        return hub;
    }

    public void setHub(Help4DevsHubDetails hub) {
        this.hub = hub;
    }

    public Help4DevsSwitchDetails getSwitcher() {
        return switcher;
    }

    public void setSwitcher(Help4DevsSwitchDetails switcher) {
        this.switcher = switcher;
    }

    public Help4DevsModemDetails getModem() {
        return modem;
    }

    public void setModem(Help4DevsModemDetails modem) {
        this.modem = modem;
    }

    public Help4DevsDiskDetails getDisk() {
        return disk;
    }

    public void setDisk(Help4DevsDiskDetails disk) {
        this.disk = disk;
    }

    public Help4DevsBluetoothDetails getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Help4DevsBluetoothDetails bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Help4DevsVideoDetails getVideo() {
        return video;
    }

    public void setVideo(Help4DevsVideoDetails video) {
        this.video = video;
    }

    public Help4DevsStorageDetails getStorage() {
        return storage;
    }

    public void setStorage(Help4DevsStorageDetails storage) {
        this.storage = storage;
    }

    public Help4DevsBridgeDetails getBridge() {
        return bridge;
    }

    public void setBridge(Help4DevsBridgeDetails bridge) {
        this.bridge = bridge;
    }

    public Help4DevsNetworkInterfaceDetails getNetworkInterface() {
        return networkInterface;
    }

    public void setNetworkInterface(Help4DevsNetworkInterfaceDetails networkInterface) {
        this.networkInterface = networkInterface;
    }

    public Help4DevsUnknownDetails getUnknown() {
        return unknown;
    }

    public void setUnknown(Help4DevsUnknownDetails unknown) {
        this.unknown = unknown;
    }

    public Help4DevsDevicesGroupDetails getDevicesGroup() {
        return devicesGroup;
    }

    public void setDevicesGroup(Help4DevsDevicesGroupDetails devicesGroup) {
        this.devicesGroup = devicesGroup;
    }

    public Help4DevsNetworkGroupDetails getNetworksGroup() {
        return networksGroup;
    }

    public void setNetworksGroup(Help4DevsNetworkGroupDetails networksGroup) {
        this.networksGroup = networksGroup;
    }

    public Help4DevsDrivesGroupDetails getDrivesGroup() {
        return drivesGroup;
    }

    public void setDrivesGroup(Help4DevsDrivesGroupDetails drivesGroup) {
        this.drivesGroup = drivesGroup;
    }

    public Help4DevsComponentsGroupDetails getComponentsGroup() {
        return componentsGroup;
    }

    public void setComponentsGroup(Help4DevsComponentsGroupDetails componentsGroup) {
        this.componentsGroup = componentsGroup;
    }

    public Help4DevsBoardsGroupDetails getBoardsGroup() {
        return boardsGroup;
    }

    public void setBoardsGroup(Help4DevsBoardsGroupDetails boardsGroup) {
        this.boardsGroup = boardsGroup;
    }

    public Help4DevsHardwareGroupDetails getHardwareGroup() {
        return hardwareGroup;
    }

    public void setHardwareGroup(Help4DevsHardwareGroupDetails hardwareGroup) {
        this.hardwareGroup = hardwareGroup;
    }

    public String toString() {
        return "Help4DevsHardSysMetricsDto{" +
                "system=" + system +
                ", machine=" + machine +
                ", battery=" + battery +
                ", memory=" + memory +
                ", slots=" + slots +
                ", processor=" + processor +
                ", graphics=" + graphics +
                ", audio=" + audio +
                ", network=" + network +
                ", drivers=" + drivers +
                ", partition=" + partition +
                ", usb=" + usb +
                ", sensors=" + sensors +
                ", running=" + running +
                ", monitor=" + monitor +
                ", bios=" + bios +
                ", baseboard=" + baseboard +
                ", chassis=" + chassis +
                ", cache=" + cache +
                ", connector=" + connector +
                ", keyboard=" + keyboard +
                ", mouse=" + mouse +
                ", hub=" + hub +
                ", switcher=" + switcher +
                ", modem=" + modem +
                ", disk=" + disk +
                ", bluetooth=" + bluetooth +
                ", video=" + video +
                ", storage=" + storage +
                ", bridge=" + bridge +
                ", networkInterface=" + networkInterface +
                ", unknown=" + unknown +
                ", devicesGroup=" + devicesGroup +
                ", networksGroup=" + networksGroup +
                ", drivesGroup=" + drivesGroup +
                ", componentsGroup=" + componentsGroup +
                ", boardsGroup=" + boardsGroup +
                ", hardwareGroup=" + hardwareGroup +
                '}';
    }
}
