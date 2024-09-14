
package com.huntercodexs.demo.services.system.hardsys.command;

import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.listClear;

public class Help4DevsHardSysInxi extends Help4DevsHardSysBase {

    private List<String> copySystemResources;

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

    private void makeGroup(String pattern, String hardsy, boolean delete) {

        List<String> merge = new ArrayList<>();
        List<String> remove = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;
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
        makeGroup("^(processor|memory|bios|cache|disk|partition|cdrom|drives)$", hardsysCheck("hardwareGroup"), false);

    }

    private boolean forceBreak(int index, String line) {
        for (int k = index; k < inxiLayout.length; k++) {
            if (line.contains(inxiLayout[k])) {
                return true;
            }
        }
        return false;
    }

    private void mergeSystemResource() {
        List<String> merge = new ArrayList<>();
        merge.addAll(this.copySystemResources);
        merge.addAll(this.resources.get(hardsysCheck("system")));
        this.resources.put(hardsysCheck("system"), merge);
    }

    private void makeSingleListItem() {
        for (String keyname : hardsysFields()) {

            switch (keyname) {
                case "system":
                case "graphics":
                case "audio":
                case "network":
                case "drives":
                case "partition":
                case "usb":
                case "sensors":
                    continue;
            }

            List<String> single = new ArrayList<>();
            StringBuilder concat = new StringBuilder();

            for (String item : this.resources.get(keyname)) {
                concat.append(item).append(" ");
            }

            single.add(String.valueOf(concat).trim());
            this.resources.put(keyname, single);
        }
    }

    public void run() {

        this.copySystemResources = listClear(
                this.resources.get(hardsysCheck("system")), "type: system ", "");

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.INXI)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < inxiLayout.length; i++) {

                //if (currentLine == null) continue;

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
            mergeSystemResource();
            makeSingleListItem();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
