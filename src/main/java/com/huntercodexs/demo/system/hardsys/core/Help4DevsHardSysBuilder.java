package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;

import java.util.HashMap;

public class Help4DevsHardSysBuilder extends Help4DevsHardSysBase {

    public Help4DevsHardSysBuilder(HashMap<String, Object> transport) {
        this.transport = transport;
    }

    public Help4DevsHardSysResourcesDto build() {
        Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
        dto.setSystem(this.transport.get(hardsys("system")));
        dto.setMachine(this.transport.get(hardsys("machine")));
        dto.setBattery(this.transport.get(hardsys("battery")));
        dto.setMemory(this.transport.get(hardsys("memory")));
        dto.setSlots(this.transport.get(hardsys("slots")));
        dto.setProcessor(this.transport.get(hardsys("processor")));
        dto.setGraphics(this.transport.get(hardsys("graphics")));
        dto.setAudio(this.transport.get(hardsys("audio")));
        dto.setNetwork(this.transport.get(hardsys("network")));
        dto.setDrivers(this.transport.get(hardsys("drivers")));
        dto.setPartition(this.transport.get(hardsys("partition")));
        dto.setUsb(this.transport.get(hardsys("usb")));
        dto.setSensors(this.transport.get(hardsys("sensors")));
        dto.setRunning(this.transport.get(hardsys("running")));
        dto.setMonitor(this.transport.get(hardsys("monitor")));
        dto.setBios(this.transport.get(hardsys("bios")));
        dto.setBaseboard(this.transport.get(hardsys("baseboard")));
        dto.setChassis(this.transport.get(hardsys("chassis")));
        dto.setCache(this.transport.get(hardsys("cache")));
        dto.setConnector(this.transport.get(hardsys("connector")));
        dto.setKeyboard(this.transport.get(hardsys("keyboard")));
        dto.setMouse(this.transport.get(hardsys("mouse")));
        dto.setHub(this.transport.get(hardsys("hub")));
        dto.setSwitcher(this.transport.get(hardsys("switcher")));
        dto.setModem(this.transport.get(hardsys("modem")));
        dto.setDisk(this.transport.get(hardsys("disk")));
        dto.setBluetooth(this.transport.get(hardsys("bluetooth")));
        dto.setVideo(this.transport.get(hardsys("video")));
        dto.setStorage(this.transport.get(hardsys("storage")));
        dto.setBridge(this.transport.get(hardsys("bridge")));
        dto.setNetworkInterface(this.transport.get(hardsys("networkInterface")));
        dto.setUnknown(this.transport.get(hardsys("unknown")));
        dto.setMultimedia(this.transport.get(hardsys("multimedia")));
        dto.setPrinter(this.transport.get(hardsys("printer")));
        return dto;
    }

}
