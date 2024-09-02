package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.group.*;
import com.huntercodexs.demo.system.hardsys.processing.*;

public interface Help4DevsHardSysInterface {
    /*Single*/
    Help4DevsSystemDetails getSystem();
    Help4DevsMachineDetails getMachine();
    Help4DevsBatteryDetails getBattery();
    Help4DevsMemoryDetails getMemory();
    Help4DevsSlotsDetails getSlots();
    Help4DevsProcessorDetails getProcessor();
    Help4DevsGraphicsDetails getGraphics();
    Help4DevsAudioDetails getAudio();
    Help4DevsNetworkDetails getNetwork();
    Help4DevsDriversDetails getDrivers();
    Help4DevsPartitionDetails getPartition();
    Help4DevsCdRomDetails getCdRom();
    Help4DevsUsbDetails getUsb();
    Help4DevsSensorsDetails getSensors();
    Help4DevsRunningDetails getRunning();
    Help4DevsMonitorDetails getMonitor();
    Help4DevsBiosDetails getBios();
    Help4DevsBaseboardDetails getBaseboard();
    Help4DevsChassisDetails getChassis();
    Help4DevsCacheDetails getCache();
    Help4DevsConnectorDetails getConnector();
    Help4DevsKeyboardDetails getKeyboard();
    Help4DevsMouseDetails getMouse();
    Help4DevsHubDetails getHub();
    Help4DevsSwitchDetails getSwitch();
    Help4DevsModemDetails getModem();
    Help4DevsDiskDetails getDisk();
    Help4DevsBluetoothDetails getBluetooth();
    Help4DevsVideoDetails getVideo();
    Help4DevsStorageDetails getStorage();
    Help4DevsBridgeDetails getBridge();
    Help4DevsNicInterfaceDetails getNicInterface();
    Help4DevsUnknownDetails getUnknown();
    Help4DevsMultimediaDetails getMultimedia();
    Help4DevsPrinterDetails getPrinter();
    /*Groups*/
    Help4DevsDevicesGroupDetails getDevicesGroup();
    Help4DevsNetworkGroupDetails getNetworksGroup();
    Help4DevsDrivesGroupDetails getDrivesGroup();
    Help4DevsComponentsGroupDetails getComponentsGroup();
    Help4DevsBoardsGroupDetails getBoardsGroup();
    Help4DevsHardwareGroupDetails getHardwareGroup();
    /*All Resources - JSON Format*/
    String all();
}
