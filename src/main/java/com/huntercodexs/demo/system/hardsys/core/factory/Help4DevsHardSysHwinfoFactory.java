package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsKeyboardDto;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMonitorDto;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMouseDto;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto;

import java.util.ArrayList;
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

        String itemClear = items.get(0).replaceAll("type: processor source: ", "");

        Help4DevsProcessorDto processors = new Help4DevsProcessorDto();
        processors.setCores(String.valueOf(items.size()));
        processors.setName(itemClear.replaceAll(",", ""));

        int index = 0;
        for (String item : items) {

            Help4DevsProcessorDto.Help4DevsProcessor processorDto = new Help4DevsProcessorDto.Help4DevsProcessor();

            processorDto.setCore(String.valueOf(index));

            processorDto.setModel(stringExtractor(
                    itemClear,
                    "model",
                    "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)",
                    "model:$1$2",
                    index));

            processorDto.setFamily(stringExtractor(
                    itemClear,
                    "family",
                    "(Intel|AMD|NVIDIA)",
                    "family:$1",
                    index+1));

            processorDto.setSpeed(stringExtractor(
                    itemClear,
                    "speed",
                    "([0-9]+\\.[0-9]+[MG]Hz)",
                    "speed:$1",
                    index+2));

            processorDto.setCurrent(stringExtractor(
                    itemClear,
                    "current",
                    "([0-9]+) (MHz)",
                    "current:$1 $2",
                    index+3));

            processors.addProcessor(processorDto);
            index++;

        }

        this.transport.put(hardsys("processor"), processors);

    }

    private void keyboardFactory(List<String> items) {

        Help4DevsKeyboardDto keyboards = new Help4DevsKeyboardDto();
        keyboards.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: keyboard source: type: keyboard ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsKeyboardDto.Help4DevsKeyboard keyboardDto = new Help4DevsKeyboardDto.Help4DevsKeyboard();
            keyboardDto.setId(String.format("%06d", id));
            keyboardDto.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id));
            keyboardDto.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));
            keyboardDto.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id).replaceAll("-", " "));

            keyboards.addKeyboard(keyboardDto);
            id++;
        }

        this.transport.put(hardsys("keyboard"), keyboards);

    }

    private void mouseFactory(List<String> items) {

        Help4DevsMouseDto mouses = new Help4DevsMouseDto();
        mouses.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: mouse source: type: mouse ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsMouseDto.Help4DevsMouse mouseDto = new Help4DevsMouseDto.Help4DevsMouse();
            mouseDto.setId(String.format("%06d", id));
            mouseDto.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id));
            mouseDto.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));
            mouseDto.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id).replaceAll("-", " "));

            mouses.addMouse(mouseDto);
            id++;
        }

        this.transport.put(hardsys("mouse"), mouses);
    }

    private void monitorFactory(List<String> items) {

        Help4DevsMonitorDto monitors = new Help4DevsMonitorDto();
        monitors.setQty(String.valueOf(items.size()));

        for (String item : items) {

            item = item.replaceAll("type: monitor source: ", "");

            Help4DevsMonitorDto.Help4DevsMonitor monitorDto = new Help4DevsMonitorDto.Help4DevsMonitor();
            monitorDto.setName(item.replaceAll("type: monitor source: ", ""));
            monitorDto.setType(stringExtractor(
                    item,
                    "type",
                    "(FHD|WFHD|UHD|4K|HF LCD|LCD|HF|LED|UHLED|QLED)",
                    "type:$1",
                    1));

            monitors.addMonitor(monitorDto);

        }

        this.transport.put(hardsys("monitor"), monitors);

    }

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
