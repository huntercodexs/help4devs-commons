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
//        dto.setSystem(this.resources.getSystem());
//        dto.setMachine(this.resources.getMachine());
//        dto.setBattery(this.resources.getBattery());
//        dto.setMemory(this.resources.getMemory());
//        dto.setSlots(this.resources.getSlots());

        dto.setProcessor(this.resources.get(processor()));

//        dto.setGraphics(this.resources.getGraphics());
//        dto.setAudio(this.resources.getAudio());
//        dto.setNetwork(this.resources.getNetwork());
//        dto.setDrivers(this.resources.getDrivers());
//        dto.setPartition(this.resources.getPartition());
//        dto.setUsb(this.resources.getUsb());
//        dto.setSensors(this.resources.getSensors());
//        dto.setRunning(this.resources.getRunning());
//        dto.setMonitor(this.resources.getMonitor());
//        dto.setBios(this.resources.getBios());
//        dto.setBaseboard(this.resources.getBaseboard());
//        dto.setChassis(this.resources.getChassis());
//        dto.setCache(this.resources.getCache());
//        dto.setConnector(this.resources.getConnector());
//        dto.setKeyboard(this.resources.getKeyboard());
//        dto.setMouse(this.resources.getMouse());
//        dto.setHub(this.resources.getHub());
//        dto.setSwitcher(this.resources.getSwitch());
//        dto.setModem(this.resources.getModem());
//        dto.setDisk(this.resources.getDisk());
//        dto.setBluetooth(this.resources.getBluetooth());
//        dto.setVideo(this.resources.getVideo());
//        dto.setStorage(this.resources.getStorage());
//        dto.setBridge(this.resources.getBridge());
//        dto.setNetworkInterface(this.resources.getNetworkInterface());
//        dto.setUnknown(this.resources.getUnknown());
//        dto.setDevicesGroup(this.resources.getDevicesGroup());
//        dto.setNetworksGroup(this.resources.getNetworksGroup());
//        dto.setDrivesGroup(this.resources.getDrivesGroup());
//        dto.setComponentsGroup(this.resources.getComponentsGroup());
//        dto.setBoardsGroup(this.resources.getBoardsGroup());
//        dto.setHardwareGroup(this.resources.getHardwareGroup());
        return dto;
    }

}
