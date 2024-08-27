package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysHwinfoFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfoFactory(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private void processorFactory(List<String> items) {

        String[] details = new String[]{"model", "family", "speed", "current"};
        String[] replacer = new String[]{"model:$1$2", "family:$1", "speed:$1", "current:$1 $2"};
        String[] pattern = new String[]{
                "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)",
                "(Intel|AMD|NVIDIA)",
                "([0-9]+\\.[0-9]+[MG]Hz)",
                "([0-9]+) (MHz)"};

        int cores = items.size();
        int countFieldsProcessor = Help4DevsProcessorDto.class.getDeclaredFields().length - 1;
        String processorName = items.get(0).replaceAll("type: processor source: ", "");

        List<String> processorList = new ArrayList<>();

        /*
         * IMPORTANT: Vector Initialization - according countFieldsProcessor
         * This feature it will be used in the Help4DevsProcessorDto class to builder the DTO object
         * */
        for (int i = 0; i < countFieldsProcessor; i++) {
            processorList.add(null);
        }

        processorList.set(0, String.valueOf(cores));
        processorList.set(1, processorName.replaceAll(",", ""));

        for (int  i = 0; i < details.length; i++) {
            processorList.set(i+2, stringExtractor(processorName, details[i], pattern[i], replacer[i], i));
        }

        processorList.set(27, stringList(items, "type: processor source: "));

        processorList.set(28, listExtractor(
                items,
                "speedCore",
                "type: processor source: ",
                "([0-9]+) (MHz)",
                "speedCore:$1 $2"));

        this.resources.put(processor(), processorList);

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

        processorFactory(this.resources.get(processor()));
        keyboardFactory(this.resources.get(keyboard()));
        mouseFactory(this.resources.get(mouse()));
        monitorFactory(this.resources.get(monitor()));
        graphicsFactory(this.resources.get(graphics()));
        audioFactory(this.resources.get(audio()));
        storageFactory(this.resources.get(storage()));
        networkFactory(this.resources.get(network()));
        networkInterfaceFactory(this.resources.get(networkInterface()));
        diskFactory(this.resources.get(disk()));
        partitionFactory(this.resources.get(partition()));
        usbFactory(this.resources.get(usb()));
        biosFactory(this.resources.get(bios()));
        bridgeFactory(this.resources.get(bridge()));
        hubFactory(this.resources.get(hub()));
        memoryFactory(this.resources.get(memory()));
        bluetoothFactory(this.resources.get(bluetooth()));
        unknownFactory(this.resources.get(unknown()));

    }

}
