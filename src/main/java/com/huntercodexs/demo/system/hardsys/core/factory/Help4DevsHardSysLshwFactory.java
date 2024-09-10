package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLshwFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysLshwFactory(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private void systemFactory(List<String> items) {}

    private void memoryFactory(List<String> items) {}

    private void processorFactory(List<String> items) {}

    private void graphicsFactory(List<String> items) {}

    private void networkFactory(List<String> items) {}

    private void partitionFactory(List<String> items) {}

    private void keyboardFactory(List<String> items) {}

    private void mouseFactory(List<String> items) {}

    private void diskFactory(List<String> items) {}

    private void storageFactory(List<String> items) {}

    private void bridgeFactory(List<String> items) {}

    private void unknownFactory(List<String> items) {}

    private void controllerFactory(List<String> items) {}

    private void multimediaFactory(List<String> items) {}

    private void machineFactory(List<String> items) {}

    public void factory() {

        /*Code Here*/

        systemFactory(this.resources.get(hardsysCheck("system")));
        memoryFactory(this.resources.get(hardsysCheck("memory")));
        processorFactory(this.resources.get(hardsysCheck("processor")));
        graphicsFactory(this.resources.get(hardsysCheck("graphics")));
        networkFactory(this.resources.get(hardsysCheck("network")));
        partitionFactory(this.resources.get(hardsysCheck("partition")));
        keyboardFactory(this.resources.get(hardsysCheck("keyboard")));
        mouseFactory(this.resources.get(hardsysCheck("mouse")));
        diskFactory(this.resources.get(hardsysCheck("disk")));
        storageFactory(this.resources.get(hardsysCheck("storage")));
        bridgeFactory(this.resources.get(hardsysCheck("bridge")));
        unknownFactory(this.resources.get(hardsysCheck("unknown")));
        controllerFactory(this.resources.get(hardsysCheck("controller")));
        multimediaFactory(this.resources.get(hardsysCheck("multimedia")));
        machineFactory(this.resources.get(hardsysCheck("machine")));

    }

}
