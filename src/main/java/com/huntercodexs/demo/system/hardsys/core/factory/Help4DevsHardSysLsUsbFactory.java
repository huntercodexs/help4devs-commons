package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLsUsbFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysLsUsbFactory(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private void systemFactory(List<String> items) {}

    private void machineFactory(List<String> items) {}

    private void batteryFactory(List<String> items) {}

    private void memoryFactory(List<String> items) {}

    private void slotsFactory(List<String> items) {}

    private void processorFactory(List<String> items) {}

    private void graphicsFactory(List<String> items) {}

    private void audioFactory(List<String> items) {}

    private void networkFactory(List<String> items) {}

    private void driversFactory(List<String> items) {}

    private void partitionFactory(List<String> items) {}

    private void usbFactory(List<String> items) {}

    private void sensorsFactory(List<String> items) {}

    private void runningFactory(List<String> items) {}

    private void monitorFactory(List<String> items) {}

    private void biosFactory(List<String> items) {}

    private void baseboardFactory(List<String> items) {}

    private void chassisFactory(List<String> items) {}

    private void cacheFactory(List<String> items) {}

    private void connectorFactory(List<String> items) {}

    private void keyboardFactory(List<String> items) {}

    private void mouseFactory(List<String> items) {}

    private void hubFactory(List<String> items) {}

    private void switcherFactory(List<String> items) {}

    private void modemFactory(List<String> items) {}

    private void diskFactory(List<String> items) {}

    private void bluetoothFactory(List<String> items) {}

    private void videoFactory(List<String> items) {}

    private void storageFactory(List<String> items) {}

    private void bridgeFactory(List<String> items) {}

    private void networkInterfaceFactory(List<String> items) {}

    private void unknownFactory(List<String> items) {}

    public void factory() {

        /*Code Here*/

        systemFactory(this.resources.get(hardsys("system")));
        machineFactory(this.resources.get(hardsys("machine")));
        batteryFactory(this.resources.get(hardsys("battery")));
        memoryFactory(this.resources.get(hardsys("memory")));
        slotsFactory(this.resources.get(hardsys("slots")));
        processorFactory(this.resources.get(hardsys("processor")));
        graphicsFactory(this.resources.get(hardsys("graphics")));
        audioFactory(this.resources.get(hardsys("audio")));
        networkFactory(this.resources.get(hardsys("network")));
        driversFactory(this.resources.get(hardsys("drivers")));
        partitionFactory(this.resources.get(hardsys("partition")));
        usbFactory(this.resources.get(hardsys("usb")));
        sensorsFactory(this.resources.get(hardsys("sensors")));
        runningFactory(this.resources.get(hardsys("running")));
        monitorFactory(this.resources.get(hardsys("monitor")));
        biosFactory(this.resources.get(hardsys("bios")));
        baseboardFactory(this.resources.get(hardsys("baseboard")));
        chassisFactory(this.resources.get(hardsys("chassis")));
        cacheFactory(this.resources.get(hardsys("cache")));
        connectorFactory(this.resources.get(hardsys("connector")));
        keyboardFactory(this.resources.get(hardsys("keyboard")));
        mouseFactory(this.resources.get(hardsys("mouse")));
        hubFactory(this.resources.get(hardsys("hub")));
        switcherFactory(this.resources.get(hardsys("switcher")));
        modemFactory(this.resources.get(hardsys("modem")));
        diskFactory(this.resources.get(hardsys("disk")));
        bluetoothFactory(this.resources.get(hardsys("bluetooth")));
        videoFactory(this.resources.get(hardsys("video")));
        storageFactory(this.resources.get(hardsys("storage")));
        bridgeFactory(this.resources.get(hardsys("bridge")));
        networkInterfaceFactory(this.resources.get(hardsys("networkInterface")));
        unknownFactory(this.resources.get(hardsys("unknown")));

    }

}
