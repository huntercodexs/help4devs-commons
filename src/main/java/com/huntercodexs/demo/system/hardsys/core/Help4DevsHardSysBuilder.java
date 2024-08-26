package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysBuilder extends Help4DevsHardSysBase {

    private final HashMap<String, List<String>> resources;

    public Help4DevsHardSysBuilder(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    public Help4DevsHardSysResourcesDto build() {
        Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
        dto.setSystem(this.resources.get(system()));
        dto.setMachine(this.resources.get(machine()));
        dto.setBattery(this.resources.get(battery()));
        dto.setMemory(this.resources.get(memory()));
        dto.setSlots(this.resources.get(slots()));
        dto.setProcessor(this.resources.get(processor()));
        dto.setGraphics(this.resources.get(graphics()));
        dto.setAudio(this.resources.get(audio()));
        dto.setNetwork(this.resources.get(network()));
        dto.setDrivers(this.resources.get(drivers()));
        dto.setPartition(this.resources.get(partition()));
        dto.setUsb(this.resources.get(usb()));
        dto.setSensors(this.resources.get(sensors()));
        dto.setRunning(this.resources.get(running()));
        dto.setMonitor(this.resources.get(monitor()));
        dto.setBios(this.resources.get(bios()));
        dto.setBaseboard(this.resources.get(baseboard()));
        dto.setChassis(this.resources.get(chassis()));
        dto.setCache(this.resources.get(cache()));
        dto.setConnector(this.resources.get(connector()));
        dto.setKeyboard(this.resources.get(keyboard()));
        dto.setMouse(this.resources.get(mouse()));
        dto.setHub(this.resources.get(hub()));
        dto.setSwitcher(this.resources.get(switcher()));
        dto.setModem(this.resources.get(modem()));
        dto.setDisk(this.resources.get(disk()));
        dto.setBluetooth(this.resources.get(bluetooth()));
        dto.setVideo(this.resources.get(video()));
        dto.setStorage(this.resources.get(storage()));
        dto.setBridge(this.resources.get(bridge()));
        dto.setNetworkInterface(this.resources.get(networkInterface()));
        dto.setUnknown(this.resources.get(unknown()));
        return dto;
    }

}
