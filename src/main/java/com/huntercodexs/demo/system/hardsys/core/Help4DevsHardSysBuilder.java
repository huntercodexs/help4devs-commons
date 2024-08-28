package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;

import java.util.HashMap;

public class Help4DevsHardSysBuilder extends Help4DevsHardSysBase {

    public Help4DevsHardSysBuilder(HashMap<String, Object> transport) {
        this.transport = transport;
    }

    public Help4DevsHardSysResourcesDto build() {
        Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
        dto.setSystem(this.transport.get(system()));
        dto.setMachine(this.transport.get(machine()));
        dto.setBattery(this.transport.get(battery()));
        dto.setMemory(this.transport.get(memory()));
        dto.setSlots(this.transport.get(slots()));
        dto.setProcessor(this.transport.get(processor()));
        dto.setGraphics(this.transport.get(graphics()));
        dto.setAudio(this.transport.get(audio()));
        dto.setNetwork(this.transport.get(network()));
        dto.setDrivers(this.transport.get(drivers()));
        dto.setPartition(this.transport.get(partition()));
        dto.setUsb(this.transport.get(usb()));
        dto.setSensors(this.transport.get(sensors()));
        dto.setRunning(this.transport.get(running()));
        dto.setMonitor(this.transport.get(monitor()));
        dto.setBios(this.transport.get(bios()));
        dto.setBaseboard(this.transport.get(baseboard()));
        dto.setChassis(this.transport.get(chassis()));
        dto.setCache(this.transport.get(cache()));
        dto.setConnector(this.transport.get(connector()));
        dto.setKeyboard(this.transport.get(keyboard()));
        dto.setMouse(this.transport.get(mouse()));
        dto.setHub(this.transport.get(hub()));
        dto.setSwitcher(this.transport.get(switcher()));
        dto.setModem(this.transport.get(modem()));
        dto.setDisk(this.transport.get(disk()));
        dto.setBluetooth(this.transport.get(bluetooth()));
        dto.setVideo(this.transport.get(video()));
        dto.setStorage(this.transport.get(storage()));
        dto.setBridge(this.transport.get(bridge()));
        dto.setNetworkInterface(this.transport.get(networkInterface()));
        dto.setUnknown(this.transport.get(unknown()));
        return dto;
    }

}
