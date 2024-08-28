package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @implNote This class only process for DTO format results
 * */
public class Help4DevsHardSysHwinfoFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfoFactory(HashMap<String, List<String>> resources, HashMap<String, Object> transport) {
        this.resources = resources;
        this.transport = transport;
    }

    private void processorFactory(List<String> items) {

        int cores = items.size();
        String processorName = items.get(0).replaceAll("type: processor source: ", "");

        Help4DevsProcessorDto processorDto = new Help4DevsProcessorDto();
        processorDto.setCores(String.valueOf(cores));
        processorDto.setName(processorName.replaceAll(",", ""));
        processorDto.setModel(stringExtractor(processorName, "model", "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)", "model:$1$2", 0));
        processorDto.setFamily(stringExtractor(processorName, "family", "(Intel|AMD|NVIDIA)", "family:$1", 1));
        processorDto.setSpeed(stringExtractor(processorName, "speed", "([0-9]+\\.[0-9]+[MG]Hz)", "speed:$1", 2));
        processorDto.setCurrent(stringExtractor(processorName, "current", "([0-9]+) (MHz)", "current:$1 $2", 3));
        processorDto.setListCores(Collections.singletonList(stringList(items, "type: processor source: ")));
        processorDto.setSpeedCores(Collections.singletonList(listExtractor(
                items,
                "speedCore",
                "type: processor source: ",
                "([0-9]+) (MHz)",
                "speedCore:$1 $2")));

        this.transport.put(hardsys("processor"), processorDto);

    }

    private void keyboardFactory(List<String> items) {}

    private void mouseFactory(List<String> items) {}

    private void monitorFactory(List<String> items) {}

    private void graphicsFactory(List<String> items) {}

    private void audioFactory(List<String> items) {}

    private void storageFactory(List<String> items) {}

    private void networkFactory(List<String> items) {}

    private void networkInterfaceFactory(List<String> items) {}

    private void diskFactory(List<String> items) {}

    private void partitionFactory(List<String> items) {}

    private void usbFactory(List<String> items) {}

    private void biosFactory(List<String> items) {}

    private void bridgeFactory(List<String> items) {}

    private void hubFactory(List<String> items) {}

    private void memoryFactory(List<String> items) {}

    private void bluetoothFactory(List<String> items) {}

    private void unknownFactory(List<String> items) {}

    /**
     * @implNote This method will convert the resources from a List object to a DTO object
     * to be used easier and understandably
     * */
    public void factory() {
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {

            List<String> makeList = new ArrayList<>();

            if (key.contains("_group") || key.equals("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;

                    String value = "type: " +key+ " source: "+item
                            .replaceAll("\\[", "(")
                            .replaceAll("]", ")")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(value);
                    this.resources.put(key, makeList);

                }
            }
        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }

        processorFactory(this.resources.get(hardsys("processor")));
        keyboardFactory(this.resources.get(hardsys("keyboard")));
        mouseFactory(this.resources.get(hardsys("mouse")));
        monitorFactory(this.resources.get(hardsys("monitor")));
        graphicsFactory(this.resources.get(hardsys("graphics")));
        audioFactory(this.resources.get(hardsys("audio")));
        storageFactory(this.resources.get(hardsys("storage")));
        networkFactory(this.resources.get(hardsys("network")));
        networkInterfaceFactory(this.resources.get(hardsys("networkInterface")));
        diskFactory(this.resources.get(hardsys("disk")));
        partitionFactory(this.resources.get(hardsys("partition")));
        usbFactory(this.resources.get(hardsys("usb")));
        biosFactory(this.resources.get(hardsys("bios")));
        bridgeFactory(this.resources.get(hardsys("bridge")));
        hubFactory(this.resources.get(hardsys("hub")));
        memoryFactory(this.resources.get(hardsys("memory")));
        bluetoothFactory(this.resources.get(hardsys("bluetooth")));
        unknownFactory(this.resources.get(hardsys("unknown")));

    }

}
