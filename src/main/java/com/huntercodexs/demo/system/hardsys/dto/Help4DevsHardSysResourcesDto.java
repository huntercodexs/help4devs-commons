package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsHardSysResourcesDto {

    private Help4DevsSystemDto system;
    private Help4DevsMachineDto machine;
    private Help4DevsBatteryDto battery;
    private Help4DevsMemoryDto memory;
    private Help4DevsSlotsDto slots;
    private Help4DevsProcessorDto processor;
    private Help4DevsGraphicsDto graphics;
    private Help4DevsAudioDto audio;
    private Help4DevsNetworkDto network;
    private Help4DevsDriversDto drivers;
    private Help4DevsPartitionDto partition;
    private Help4DevsUsbDto usb;
    private Help4DevsSensorsDto sensors;
    private Help4DevsRunningDto running;
    private Help4DevsMonitorDto monitor;
    private Help4DevsBiosDto bios;
    private Help4DevsBaseboardDto baseboard;
    private Help4DevsChassisDto chassis;
    private Help4DevsCacheDto cache;
    private Help4DevsConnectorDto connector;
    private Help4DevsKeyboardDto keyboard;
    private Help4DevsMouseDto mouse;
    private Help4DevsHubDto hub;
    private Help4DevsSwitchDto switcher;
    private Help4DevsModemDto modem;
    private Help4DevsDiskDto disk;
    private Help4DevsBluetoothDto bluetooth;
    private Help4DevsVideoDto video;
    private Help4DevsStorageDto storage;
    private Help4DevsBridgeDto bridge;
    private Help4DevsNetworkInterfaceDto networkInterface;
    private Help4DevsUnknownDto unknown;

    public Help4DevsSystemDto getSystem() {
        return system;
    }

    public void setSystem(List<String> system) {
        this.system = new Help4DevsSystemDto(system).builder();
    }

    public Help4DevsMachineDto getMachine() {
        return machine;
    }

    public void setMachine(List<String> machine) {
        this.machine = new Help4DevsMachineDto(machine).builder();
    }

    public Help4DevsBatteryDto getBattery() {
        return battery;
    }

    public void setBattery(List<String> battery) {
        this.battery = new Help4DevsBatteryDto(battery).builder();
    }

    public Help4DevsMemoryDto getMemory() {
        return memory;
    }

    public void setMemory(List<String> memory) {
        this.memory = new Help4DevsMemoryDto(memory).builder();
    }

    public Help4DevsSlotsDto getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = new Help4DevsSlotsDto(slots).builder();
    }

    public Help4DevsProcessorDto getProcessor() {
        return processor;
    }

    public void setProcessor(List<String> processor) {
        this.processor = new Help4DevsProcessorDto(processor).builder();
    }

    public Help4DevsGraphicsDto getGraphics() {
        return graphics;
    }

    public void setGraphics(List<String> graphics) {
        this.graphics = new Help4DevsGraphicsDto(graphics).builder();
    }

    public Help4DevsAudioDto getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = new Help4DevsAudioDto(audio).builder();
    }

    public Help4DevsNetworkDto getNetwork() {
        return network;
    }

    public void setNetwork(List<String> network) {
        this.network = new Help4DevsNetworkDto(network).builder();
    }

    public Help4DevsDriversDto getDrivers() {
        return drivers;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = new Help4DevsDriversDto(drivers).builder();
    }

    public Help4DevsPartitionDto getPartition() {
        return partition;
    }

    public void setPartition(List<String> partition) {
        this.partition = new Help4DevsPartitionDto(partition).builder();
    }

    public Help4DevsUsbDto getUsb() {
        return usb;
    }

    public void setUsb(List<String> usb) {
        this.usb = new Help4DevsUsbDto(usb).builder();
    }

    public Help4DevsSensorsDto getSensors() {
        return sensors;
    }

    public void setSensors(List<String> sensors) {
        this.sensors = new Help4DevsSensorsDto(sensors).builder();
    }

    public Help4DevsRunningDto getRunning() {
        return running;
    }

    public void setRunning(List<String> running) {
        this.running = new Help4DevsRunningDto(running).builder();
    }

    public Help4DevsMonitorDto getMonitor() {
        return monitor;
    }

    public void setMonitor(List<String> monitor) {
        this.monitor = new Help4DevsMonitorDto(monitor).builder();
    }

    public Help4DevsBiosDto getBios() {
        return bios;
    }

    public void setBios(List<String> bios) {
        this.bios = new Help4DevsBiosDto(bios).builder();
    }

    public Help4DevsBaseboardDto getBaseboard() {
        return baseboard;
    }

    public void setBaseboard(List<String> baseboard) {
        this.baseboard = new Help4DevsBaseboardDto(baseboard).builder();
    }

    public Help4DevsChassisDto getChassis() {
        return chassis;
    }

    public void setChassis(List<String> chassis) {
        this.chassis = new Help4DevsChassisDto(chassis).builder();
    }

    public Help4DevsCacheDto getCache() {
        return cache;
    }

    public void setCache(List<String> cache) {
        this.cache = new Help4DevsCacheDto(cache).builder();
    }

    public Help4DevsConnectorDto getConnector() {
        return connector;
    }

    public void setConnector(List<String> connector) {
        this.connector = new Help4DevsConnectorDto(connector).builder();
    }

    public Help4DevsKeyboardDto getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(List<String> keyboard) {
        this.keyboard = new Help4DevsKeyboardDto(keyboard).builder();
    }

    public Help4DevsMouseDto getMouse() {
        return mouse;
    }

    public void setMouse(List<String> mouse) {
        this.mouse = new Help4DevsMouseDto(mouse).builder();
    }

    public Help4DevsHubDto getHub() {
        return hub;
    }

    public void setHub(List<String> hub) {
        this.hub = new Help4DevsHubDto(hub).builder();
    }

    public Help4DevsSwitchDto getSwitcher() {
        return switcher;
    }

    public void setSwitcher(List<String> switcher) {
        this.switcher = new Help4DevsSwitchDto(switcher).builder();
    }

    public Help4DevsModemDto getModem() {
        return modem;
    }

    public void setModem(List<String> modem) {
        this.modem = new Help4DevsModemDto(modem).builder();
    }

    public Help4DevsDiskDto getDisk() {
        return disk;
    }

    public void setDisk(List<String> disk) {
        this.disk = new Help4DevsDiskDto(disk).builder();
    }

    public Help4DevsBluetoothDto getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(List<String> bluetooth) {
        this.bluetooth = new Help4DevsBluetoothDto(bluetooth).builder();
    }

    public Help4DevsVideoDto getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = new Help4DevsVideoDto(video).builder();
    }

    public Help4DevsStorageDto getStorage() {
        return storage;
    }

    public void setStorage(List<String> storage) {
        this.storage = new Help4DevsStorageDto(storage).builder();
    }

    public Help4DevsBridgeDto getBridge() {
        return bridge;
    }

    public void setBridge(List<String> bridge) {
        this.bridge = new Help4DevsBridgeDto(bridge).builder();
    }

    public Help4DevsNetworkInterfaceDto getNetworkInterface() {
        return networkInterface;
    }

    public void setNetworkInterface(List<String> networkInterface) {
        this.networkInterface = new Help4DevsNetworkInterfaceDto(networkInterface).builder();
    }

    public Help4DevsUnknownDto getUnknown() {
        return unknown;
    }

    public void setUnknown(List<String> unknown) {
        this.unknown = new Help4DevsUnknownDto(unknown).builder();
    }

    public String toString() {
        return "Help4DevsHardSysMetricsDto[" +
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
                "]";
    }
}
