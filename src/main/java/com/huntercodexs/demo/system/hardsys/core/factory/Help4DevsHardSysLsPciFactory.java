package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLsPciFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysLsPciFactory(HashMap<String, List<String>> resources) {
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

    private void nicInterfaceFactory(List<String> items) {}

    private void unknownFactory(List<String> items) {}

    public void factory() {

        /*Code Here*/

        systemFactory(this.resources.get(hardsysCheck("system")));
        machineFactory(this.resources.get(hardsysCheck("machine")));
        batteryFactory(this.resources.get(hardsysCheck("battery")));
        memoryFactory(this.resources.get(hardsysCheck("memory")));
        slotsFactory(this.resources.get(hardsysCheck("slots")));
        processorFactory(this.resources.get(hardsysCheck("processor")));
        graphicsFactory(this.resources.get(hardsysCheck("graphics")));
        audioFactory(this.resources.get(hardsysCheck("audio")));
        networkFactory(this.resources.get(hardsysCheck("network")));
        driversFactory(this.resources.get(hardsysCheck("drivers")));
        partitionFactory(this.resources.get(hardsysCheck("partition")));
        usbFactory(this.resources.get(hardsysCheck("usb")));
        sensorsFactory(this.resources.get(hardsysCheck("sensors")));
        runningFactory(this.resources.get(hardsysCheck("running")));
        monitorFactory(this.resources.get(hardsysCheck("monitor")));
        biosFactory(this.resources.get(hardsysCheck("bios")));
        baseboardFactory(this.resources.get(hardsysCheck("baseboard")));
        chassisFactory(this.resources.get(hardsysCheck("chassis")));
        cacheFactory(this.resources.get(hardsysCheck("cache")));
        connectorFactory(this.resources.get(hardsysCheck("connector")));
        keyboardFactory(this.resources.get(hardsysCheck("keyboard")));
        mouseFactory(this.resources.get(hardsysCheck("mouse")));
        hubFactory(this.resources.get(hardsysCheck("hub")));
        switcherFactory(this.resources.get(hardsysCheck("switcher")));
        modemFactory(this.resources.get(hardsysCheck("modem")));
        diskFactory(this.resources.get(hardsysCheck("disk")));
        bluetoothFactory(this.resources.get(hardsysCheck("bluetooth")));
        videoFactory(this.resources.get(hardsysCheck("video")));
        storageFactory(this.resources.get(hardsysCheck("storage")));
        bridgeFactory(this.resources.get(hardsysCheck("bridge")));
        nicInterfaceFactory(this.resources.get(hardsysCheck("nicInterface")));
        unknownFactory(this.resources.get(hardsysCheck("unknown")));

    }

}
