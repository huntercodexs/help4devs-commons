package com.huntercodexs.demo.system.hardsys;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysMetrics extends Help4DevsHardSysBase implements Help4DevsHardSysInterface {

    public Help4DevsSystemDetails systemDetails;
    public Help4DevsMachineDetails machineDetails;
    public Help4DevsBatteryDetails batteryDetails;
    public Help4DevsMemoryDetails memoryDetails;
    public Help4DevsSlotsDetails slotsDetails;
    public Help4DevsProcessorDetails processorDetails;
    public Help4DevsGraphicsDetails graphicsDetails;
    public Help4DevsAudioDetails audioDetails;
    public Help4DevsNetworkDetails networkDetails;
    public Help4DevsDriversDetails driversDetails;
    public Help4DevsPartitionDetails partitionDetails;
    public Help4DevsUsbDetails usbDetails;
    public Help4DevsSensorsDetails sensorsDetails;
    public Help4DevsRunningDetails runningDetails;
    public Help4DevsMonitorDetails monitorDetails;
    public Help4DevsBiosDetails biosDetails;
    public Help4DevsBaseboardDetails baseboardDetails;
    public Help4DevsChassisDetails chassisDetails;
    public Help4DevsCacheDetails cacheDetails;
    public Help4DevsConnectorDetails connectorDetails;
    public Help4DevsDrivesDetails drivesDetails;
    public Help4DevsDevicesDetails devicesDetails;
    public Help4DevsKeyboardDetails keyboardDetails;
    public Help4DevsMouseDetails mouseDetails;
    public Help4DevsHubDetails hubDetails;
    public Help4DevsSwitchDetails switchDetails;
    public Help4DevsModemDetails modemDetails;
    public Help4DevsDiskDetails diskDetails;
    public Help4DevsBluetoothDetails bluetoothDetails;
    public Help4DevsVideoDetails videoDetails;
    public Help4DevsUnknownDetails unknownDetails;

    public Help4DevsHardSysMetrics(HashMap<String, List<String>> resources, Help4DevsHardSysCommands command) {
        //See the INFO_INDEX to get more details
        this.systemDetails = new Help4DevsSystemDetails(resources.get(system()), command);
        this.machineDetails = new Help4DevsMachineDetails(resources.get(machine()), command);
        this.batteryDetails = new Help4DevsBatteryDetails(resources.get(battery()), command);
        this.memoryDetails = new Help4DevsMemoryDetails(resources.get(memory()), command);
        this.slotsDetails = new Help4DevsSlotsDetails(resources.get(slots()), command);
        this.processorDetails = new Help4DevsProcessorDetails(resources.get(processor()), command);
        this.graphicsDetails = new Help4DevsGraphicsDetails(resources.get(graphics()), command);
        this.audioDetails = new Help4DevsAudioDetails(resources.get(audio()), command);
        this.networkDetails = new Help4DevsNetworkDetails(resources.get(network()), command);
        this.driversDetails = new Help4DevsDriversDetails(resources.get(drivers()), command);
        this.partitionDetails = new Help4DevsPartitionDetails(resources.get(partition()), command);
        this.usbDetails = new Help4DevsUsbDetails(resources.get(usb()), command);
        this.sensorsDetails = new Help4DevsSensorsDetails(resources.get(sensors()), command);
        this.runningDetails = new Help4DevsRunningDetails(resources.get(running()), command);
        this.monitorDetails = new Help4DevsMonitorDetails(resources.get(monitor()), command);
        this.biosDetails = new Help4DevsBiosDetails(resources.get(bios()), command);
        this.baseboardDetails = new Help4DevsBaseboardDetails(resources.get(baseboard()), command);
        this.chassisDetails = new Help4DevsChassisDetails(resources.get(chassis()), command);
        this.cacheDetails = new Help4DevsCacheDetails(resources.get(cache()), command);
        this.connectorDetails = new Help4DevsConnectorDetails(resources.get(connector()), command);
        this.drivesDetails = new Help4DevsDrivesDetails(resources.get(drives()), command);
        this.devicesDetails = new Help4DevsDevicesDetails(resources.get(devices()), command);
        this.keyboardDetails = new Help4DevsKeyboardDetails(resources.get(keyboard()), command);
        this.mouseDetails = new Help4DevsMouseDetails(resources.get(mouse()), command);
        this.hubDetails = new Help4DevsHubDetails(resources.get(hub()), command);
        this.switchDetails = new Help4DevsSwitchDetails(resources.get(switcher()), command);
        this.modemDetails = new Help4DevsModemDetails(resources.get(modem()), command);
        this.diskDetails = new Help4DevsDiskDetails(resources.get(disk()), command);
        this.bluetoothDetails = new Help4DevsBluetoothDetails(resources.get(bluetooth()), command);
        this.videoDetails = new Help4DevsVideoDetails(resources.get(video()), command);
        this.unknownDetails = new Help4DevsUnknownDetails(resources.get(unknown()), command);
    }

    public Help4DevsSystemDetails getSystem() {
        return systemDetails;
    }

    public Help4DevsMachineDetails getMachine() {
        return machineDetails;
    }

    public Help4DevsBatteryDetails getBattery() {
        return batteryDetails;
    }

    public Help4DevsMemoryDetails getMemory() {
        return memoryDetails;
    }

    public Help4DevsSlotsDetails getSlots() {
        return slotsDetails;
    }

    public Help4DevsProcessorDetails getProcessor() {
        return processorDetails;
    }

    public Help4DevsGraphicsDetails getGraphics() {
        return graphicsDetails;
    }

    public Help4DevsAudioDetails getAudio() {
        return audioDetails;
    }

    public Help4DevsNetworkDetails getNetwork() {
        return networkDetails;
    }

    public Help4DevsDriversDetails getDrivers() {
        return driversDetails;
    }

    public Help4DevsPartitionDetails getPartition() {
        return partitionDetails;
    }

    public Help4DevsUsbDetails getUsb() {
        return usbDetails;
    }

    public Help4DevsSensorsDetails getSensors() {
        return sensorsDetails;
    }

    public Help4DevsRunningDetails getRunning() {
        return runningDetails;
    }

    public Help4DevsMonitorDetails getMonitor() {
        return monitorDetails;
    }

    public Help4DevsBiosDetails getBios() {
        return biosDetails;
    }

    public Help4DevsBaseboardDetails getBaseboard() {
        return baseboardDetails;
    }

    public Help4DevsChassisDetails getChassis() {
        return chassisDetails;
    }

    public Help4DevsCacheDetails getCache() {
        return cacheDetails;
    }

    public Help4DevsConnectorDetails getConnector() {
        return connectorDetails;
    }

    public Help4DevsDrivesDetails getDrives() {
        return drivesDetails;
    }

    public Help4DevsDevicesDetails getDevices() {
        return devicesDetails;
    }

    public Help4DevsKeyboardDetails getKeyboard() {
        return keyboardDetails;
    }

    public Help4DevsMouseDetails getMouse() {
        return mouseDetails;
    }

    public Help4DevsHubDetails getHub() {
        return hubDetails;
    }

    public Help4DevsSwitchDetails getSwitch() {
        return switchDetails;
    }

    public Help4DevsModemDetails getModem() {
        return modemDetails;
    }

    public Help4DevsDiskDetails getDisk() {
        return diskDetails;
    }

    public Help4DevsBluetoothDetails getBluetooth() {
        return bluetoothDetails;
    }

    public Help4DevsVideoDetails getVideo() {
        return videoDetails;
    }

    public Help4DevsUnknownDetails getUnknown() {
        return unknownDetails;
    }

}
