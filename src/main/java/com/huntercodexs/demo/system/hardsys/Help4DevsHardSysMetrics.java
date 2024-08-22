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
        this.systemDetails = new Help4DevsSystemDetails(resources.get(HARDSYS[0]), command);
        this.machineDetails = new Help4DevsMachineDetails(resources.get(HARDSYS[1]), command);
        this.batteryDetails = new Help4DevsBatteryDetails(resources.get(HARDSYS[2]), command);
        this.memoryDetails = new Help4DevsMemoryDetails(resources.get(HARDSYS[3]), command);
        this.slotsDetails = new Help4DevsSlotsDetails(resources.get(HARDSYS[4]), command);
        this.processorDetails = new Help4DevsProcessorDetails(resources.get(HARDSYS[5]), command);
        this.graphicsDetails = new Help4DevsGraphicsDetails(resources.get(HARDSYS[6]), command);
        this.audioDetails = new Help4DevsAudioDetails(resources.get(HARDSYS[7]), command);
        this.networkDetails = new Help4DevsNetworkDetails(resources.get(HARDSYS[8]), command);
        this.driversDetails = new Help4DevsDriversDetails(resources.get(HARDSYS[9]), command);
        this.partitionDetails = new Help4DevsPartitionDetails(resources.get(HARDSYS[10]), command);
        this.usbDetails = new Help4DevsUsbDetails(resources.get(HARDSYS[11]), command);
        this.sensorsDetails = new Help4DevsSensorsDetails(resources.get(HARDSYS[12]), command);
        this.runningDetails = new Help4DevsRunningDetails(resources.get(HARDSYS[13]), command);
        this.monitorDetails = new Help4DevsMonitorDetails(resources.get(HARDSYS[14]), command);
        this.biosDetails = new Help4DevsBiosDetails(resources.get(HARDSYS[15]), command);
        this.baseboardDetails = new Help4DevsBaseboardDetails(resources.get(HARDSYS[16]), command);
        this.chassisDetails = new Help4DevsChassisDetails(resources.get(HARDSYS[17]), command);
        this.cacheDetails = new Help4DevsCacheDetails(resources.get(HARDSYS[18]), command);
        this.connectorDetails = new Help4DevsConnectorDetails(resources.get(HARDSYS[19]), command);
        this.drivesDetails = new Help4DevsDrivesDetails(resources.get(HARDSYS[20]), command);
        this.devicesDetails = new Help4DevsDevicesDetails(resources.get(HARDSYS[21]), command);
        this.keyboardDetails = new Help4DevsKeyboardDetails(resources.get(HARDSYS[22]), command);
        this.mouseDetails = new Help4DevsMouseDetails(resources.get(HARDSYS[23]), command);
        this.hubDetails = new Help4DevsHubDetails(resources.get(HARDSYS[24]), command);
        this.switchDetails = new Help4DevsSwitchDetails(resources.get(HARDSYS[25]), command);
        this.modemDetails = new Help4DevsModemDetails(resources.get(HARDSYS[26]), command);
        this.diskDetails = new Help4DevsDiskDetails(resources.get(HARDSYS[27]), command);
        this.bluetoothDetails = new Help4DevsBluetoothDetails(resources.get(HARDSYS[28]), command);
        this.videoDetails = new Help4DevsVideoDetails(resources.get(HARDSYS[29]), command);
        this.unknownDetails = new Help4DevsUnknownDetails(resources.get(HARDSYS[30]), command);
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

    public Help4DevsDevicesDetails getKeyboard() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getMouse() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getHub() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getSwitch() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getModem() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getDisk() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getBluetooth() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getVideo() {
        return devicesDetails;
    }

    public Help4DevsDevicesDetails getUnknown() {
        return devicesDetails;
    }

}
