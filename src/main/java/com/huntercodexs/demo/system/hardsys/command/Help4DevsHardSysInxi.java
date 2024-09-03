
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
                .replaceAll("Drives: ", "disk")
                .replaceAll("Partition: ", "partition")
                .replaceAll("USB: ", "usb")
                .replaceAll("Sensors: ", "sensors")
                .replaceAll("Info: ", "running")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private void makeGroup(String pattern, String hardsy, boolean delete) {

        List<String> merge = new ArrayList<>();
        List<String> remove = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    /*if (!item.contains("type")) {
                        item = "type: " + value + " source: " + item
                                .replaceAll("-", "###")
                                .replaceAll("\\[", "(")
                                .replaceAll("]", ")")
                                .replaceAll(" ", "-")
                                .replaceAll("([0-9])([-#]+)([0-9]):([0-9])", "$1###$3.@.$4")
                                .replaceAll("-{2,}", " description: ");
                    }*/

                    merge.add("type: " + value + " " + item);

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
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(battery|sensors)$", hardsysCheck("devicesGroup"), false);
        makeGroup("^(network|nicInterface)$", hardsysCheck("networksGroup"), false);
        makeGroup("^(partition|disk|drives)$", hardsysCheck("drivesGroup"), false);
        makeGroup("^(processor|memory|audio|battery|sensors)$", hardsysCheck("componentsGroup"), false);
        makeGroup("^(baseboard|audio|graphics|slots)$", hardsysCheck("boardsGroup"), false);
        makeGroup("^(processor|memory|bios|cache|disk|partition|cdrom)$", hardsysCheck("hardwareGroup"), false);

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
