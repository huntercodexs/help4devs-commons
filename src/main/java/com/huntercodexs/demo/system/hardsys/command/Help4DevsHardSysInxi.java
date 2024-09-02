
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysInxi extends Help4DevsHardSysBase {

    public Help4DevsHardSysInxi(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private String layoutTranslator(String input) {
        return input
                .replaceAll("System:", "system")
                .replaceAll("Machine:", "machine")
                .replaceAll("Battery:", "battery")
                .replaceAll("Memory:", "memory")
                .replaceAll("PCI Slots:", "slots")
                .replaceAll("CPU: ", "processor")
                .replaceAll("Graphics: ", "graphics")
                .replaceAll("Audio: ", "audio")
                .replaceAll("Network: ", "network")
                .replaceAll("Drives: ", "drives")
                .replaceAll("Partition: ", "partition")
                .replaceAll("USB: ", "usb")
                .replaceAll("Sensors: ", "sensors")
                .replaceAll("Info: ", "running")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private void makeSource(String pattern, String hardsy) {

        List<String> makeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {
            if (key.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +key+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(item);

                }
            }
        });

        this.resources.put(hardsy, makeList);
    }

    private void makeGroup(String pattern, String hardsy, boolean delete) {

        List<String> merge = new ArrayList<>();
        List<String> remove = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    if (!item.contains("type")) {
                        item = "type: " + value + " source: " + item
                                .replaceAll(" ", "-")
                                .replaceAll(":", ".@.")
                                .replaceAll("-{2,}", " description: ");
                    }

                    merge.add(item);

                }
                remove.add(value);
            }
        });

        if (delete) {
            for (String item : remove) {
                this.resources.remove(item);
            }
        }

        this.resources.put(hardsy, merge);
    }

    private void makeSourceAndGroup() {

        /*
         * These resources have sources from the current command and
         * hence needs to treated with a different mode
         * */
        makeSource("^(keyboard)$", hardsysCheck("keyboard"));
        makeSource("^(mouse)$", hardsysCheck("mouse"));
        makeSource("^(network)$", hardsysCheck("network"));
        makeSource("^(nicInterface)$", hardsysCheck("nicInterface"));
        makeSource("^(disk)$", hardsysCheck("disk"));
        makeSource("^(partition)$", hardsysCheck("partition"));

        /*
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(battery|sensors|keyboard|mouse|monitor|hub)$", hardsysCheck("devicesGroup"), false);
        makeGroup("^(network|nicInterface|bridge|hub|switch)$", hardsysCheck("networksGroup"), false);
        makeGroup("^(disk|partition|cdrom|storage)$", hardsysCheck("drivesGroup"), false);
        makeGroup("^(processor|memory|sensors|audio|battery)$", hardsysCheck("componentsGroup"), false);
        makeGroup("^(baseboard|audio|bios|slots)$", hardsysCheck("boardsGroup"), false);
        makeGroup("^(bios|cache|running)$", hardsysCheck("hardwareGroup"), false);

    }

    private boolean forceBreak(int index, String line) {
        for (int k = index; k < inxiLayout.length; k++) {
            if (line.contains(inxiLayout[k])) {
                return true;
            }
        }
        return false;
    }

    private void mergeSystemResource(List<String> copySystemResources) {
        List<String> merge = new ArrayList<>();
        merge.addAll(copySystemResources);
        merge.addAll(this.resources.get(hardsysCheck("system")));
        this.resources.put(hardsysCheck("system"), merge);
    }

    public void run() {

        List<String> copySystemResources = listClear(
                this.resources.get(hardsysCheck("system")), "type: system ", "");

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.INXI)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < inxiLayout.length; i++) {

                if (currentLine == null) continue;

                if (currentLine.contains(inxiLayout[i])) {

                    String line;
                    List<String> array = new ArrayList<>();
                    array.add(currentLine.replace(inxiLayout[i], "").trim());

                    while ((line = reader.readLine()) != null) {

                        //Last line
                        if (i == inxiLayout.length-1) {
                            array.add(line.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (line.contains(inxiLayout[i+1])) {
                            currentLine = line;
                            break;
                        }

                        //Prevent bug - In the case below the current resource is present in the output result
                        if (forceBreak(i, line)) {
                            currentLine = line;
                            break;
                        }

                        array.add(line.trim());
                    }

                    //Save line according HARDSYS
                    this.resources.put(layoutTranslator(inxiLayout[i]), array);
                }
            }

            makeSourceAndGroup();
            mergeSystemResource(copySystemResources);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
