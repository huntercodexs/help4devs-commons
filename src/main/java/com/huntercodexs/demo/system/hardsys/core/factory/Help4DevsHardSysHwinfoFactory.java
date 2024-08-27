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

    private void systemFactory(List<String> items) {}

    private void machineFactory(List<String> items) {}

    private void batteryFactory(List<String> items) {}

    private void memoryFactory(List<String> items) {}

    private void slotsFactory(List<String> items) {}

    private void processorFactory(List<String> items) {

        /*HWINFO - Layout*/
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

        systemFactory(this.resources.get(system()));
        machineFactory(this.resources.get(machine()));
        batteryFactory(this.resources.get(battery()));
        memoryFactory(this.resources.get(memory()));
        slotsFactory(this.resources.get(slots()));
        processorFactory(this.resources.get(processor()));
        graphicsFactory(this.resources.get(graphics()));
        audioFactory(this.resources.get(audio()));
        networkFactory(this.resources.get(network()));
        driversFactory(this.resources.get(drivers()));
        partitionFactory(this.resources.get(partition()));
        usbFactory(this.resources.get(usb()));
        sensorsFactory(this.resources.get(sensors()));
        runningFactory(this.resources.get(running()));
        monitorFactory(this.resources.get(monitor()));
        biosFactory(this.resources.get(bios()));
        baseboardFactory(this.resources.get(baseboard()));
        chassisFactory(this.resources.get(chassis()));
        cacheFactory(this.resources.get(cache()));
        connectorFactory(this.resources.get(connector()));
        keyboardFactory(this.resources.get(keyboard()));
        mouseFactory(this.resources.get(mouse()));
        hubFactory(this.resources.get(hub()));
        switcherFactory(this.resources.get(switcher()));
        modemFactory(this.resources.get(modem()));
        diskFactory(this.resources.get(disk()));
        bluetoothFactory(this.resources.get(bluetooth()));
        videoFactory(this.resources.get(video()));
        storageFactory(this.resources.get(storage()));
        bridgeFactory(this.resources.get(bridge()));
        networkInterfaceFactory(this.resources.get(networkInterface()));
        unknownFactory(this.resources.get(unknown()));

    }

}
