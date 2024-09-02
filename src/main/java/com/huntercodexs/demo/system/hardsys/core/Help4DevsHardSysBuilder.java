package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;

import java.util.HashMap;

public class Help4DevsHardSysBuilder extends Help4DevsHardSysBase {

    public Help4DevsHardSysBuilder(HashMap<String, Object> transport) {
        this.transport = transport;
    }

    public Help4DevsHardSysResourcesDto build() {
        Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
        dto.setSystem(this.transport.get(hardsysCheck("system")));
        dto.setMachine(this.transport.get(hardsysCheck("machine")));
        dto.setBattery(this.transport.get(hardsysCheck("battery")));
        dto.setMemory(this.transport.get(hardsysCheck("memory")));
        dto.setSlots(this.transport.get(hardsysCheck("slots")));
        dto.setProcessor(this.transport.get(hardsysCheck("processor")));
        dto.setGraphics(this.transport.get(hardsysCheck("graphics")));
        dto.setAudio(this.transport.get(hardsysCheck("audio")));
        dto.setNetwork(this.transport.get(hardsysCheck("network")));
        dto.setDrivers(this.transport.get(hardsysCheck("drivers")));
        dto.setPartition(this.transport.get(hardsysCheck("partition")));
        dto.setCdRom(this.transport.get(hardsysCheck("cdrom")));
        dto.setUsb(this.transport.get(hardsysCheck("usb")));
        dto.setSensors(this.transport.get(hardsysCheck("sensors")));
        dto.setRunning(this.transport.get(hardsysCheck("running")));
        dto.setMonitor(this.transport.get(hardsysCheck("monitor")));
        dto.setBios(this.transport.get(hardsysCheck("bios")));
        dto.setBaseboard(this.transport.get(hardsysCheck("baseboard")));
        dto.setChassis(this.transport.get(hardsysCheck("chassis")));
        dto.setCache(this.transport.get(hardsysCheck("cache")));
        dto.setConnector(this.transport.get(hardsysCheck("connector")));
        dto.setKeyboard(this.transport.get(hardsysCheck("keyboard")));
        dto.setMouse(this.transport.get(hardsysCheck("mouse")));
        dto.setHub(this.transport.get(hardsysCheck("hub")));
        dto.setSwitcher(this.transport.get(hardsysCheck("switcher")));
        dto.setModem(this.transport.get(hardsysCheck("modem")));
        dto.setDisk(this.transport.get(hardsysCheck("disk")));
        dto.setBluetooth(this.transport.get(hardsysCheck("bluetooth")));
        dto.setVideo(this.transport.get(hardsysCheck("video")));
        dto.setStorage(this.transport.get(hardsysCheck("storage")));
        dto.setBridge(this.transport.get(hardsysCheck("bridge")));
        dto.setNicInterface(this.transport.get(hardsysCheck("nicInterface")));
        dto.setUnknown(this.transport.get(hardsysCheck("unknown")));
        dto.setMultimedia(this.transport.get(hardsysCheck("multimedia")));
        dto.setPrinter(this.transport.get(hardsysCheck("printer")));
        return dto;
    }

    /*TODO*/
    public Help4DevsHardSysResourcesDto buildNonNull() {
        Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
        dto.setSystem(this.transport.get(hardsysCheck("system")));
        dto.setMachine(this.transport.get(hardsysCheck("machine")));
        dto.setBattery(this.transport.get(hardsysCheck("battery")));
        dto.setMemory(this.transport.get(hardsysCheck("memory")));
        dto.setSlots(this.transport.get(hardsysCheck("slots")));
        dto.setProcessor(this.transport.get(hardsysCheck("processor")));
        dto.setGraphics(this.transport.get(hardsysCheck("graphics")));
        dto.setAudio(this.transport.get(hardsysCheck("audio")));
        dto.setNetwork(this.transport.get(hardsysCheck("network")));
        dto.setDrivers(this.transport.get(hardsysCheck("drivers")));
        dto.setPartition(this.transport.get(hardsysCheck("partition")));
        dto.setCdRom(this.transport.get(hardsysCheck("cdrom")));
        dto.setUsb(this.transport.get(hardsysCheck("usb")));
        dto.setSensors(this.transport.get(hardsysCheck("sensors")));
        dto.setRunning(this.transport.get(hardsysCheck("running")));
        dto.setMonitor(this.transport.get(hardsysCheck("monitor")));
        dto.setBios(this.transport.get(hardsysCheck("bios")));
        dto.setBaseboard(this.transport.get(hardsysCheck("baseboard")));
        dto.setChassis(this.transport.get(hardsysCheck("chassis")));
        dto.setCache(this.transport.get(hardsysCheck("cache")));
        dto.setConnector(this.transport.get(hardsysCheck("connector")));
        dto.setKeyboard(this.transport.get(hardsysCheck("keyboard")));
        dto.setMouse(this.transport.get(hardsysCheck("mouse")));
        dto.setHub(this.transport.get(hardsysCheck("hub")));
        dto.setSwitcher(this.transport.get(hardsysCheck("switcher")));
        dto.setModem(this.transport.get(hardsysCheck("modem")));
        dto.setDisk(this.transport.get(hardsysCheck("disk")));
        dto.setBluetooth(this.transport.get(hardsysCheck("bluetooth")));
        dto.setVideo(this.transport.get(hardsysCheck("video")));
        dto.setStorage(this.transport.get(hardsysCheck("storage")));
        dto.setBridge(this.transport.get(hardsysCheck("bridge")));
        dto.setNicInterface(this.transport.get(hardsysCheck("nicInterface")));
        dto.setUnknown(this.transport.get(hardsysCheck("unknown")));
        dto.setMultimedia(this.transport.get(hardsysCheck("multimedia")));
        dto.setPrinter(this.transport.get(hardsysCheck("printer")));
        return dto;
    }

}
