package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysHwinfo extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfo(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private String fieldsTranslator(String input) {
        return input
                .replaceAll("cpu:", processor())
                .replaceAll("keyboard:", keyboard())
                .replaceAll("mouse:", mouse())
                .replaceAll("monitor:", monitor())
                .replaceAll("graphics card:", graphics())
                .replaceAll("sound:", audio())
                .replaceAll("storage:", storage())
                .replaceAll("network:", network())
                .replaceAll("network interface:", networkInterface())
                .replaceAll("disk:", disk())
                .replaceAll("partition:", partition())
                .replaceAll("usb controller:", usb())
                .replaceAll("bios:", bios())
                .replaceAll("bridge:", bridge())
                .replaceAll("hub:", hub())
                .replaceAll("memory:", memory())
                .replaceAll("bluetooth:", bluetooth())
                .replaceAll("unknown:", unknown())
                .replaceAll("[^-_a-zA-Z ]", "")
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
        makeSource("^(keyboard)$", keyboard());
        makeSource("^(mouse)$", mouse());
        makeSource("^(network)$", network());
        makeSource("^("+networkInterface()+")$", networkInterface());
        makeSource("^(disk)$", disk());
        makeSource("^(partition)$", partition());

        /*
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(keyboard|mouse|monitor|hub)$", devicesGroup(), false);
        makeGroup("^(network|"+networkInterface()+"|bridge|hub|switch)$", networksGroup(), false);
        makeGroup("^(disk|partition|storage)$", drivesGroup(), false);
        makeGroup("^(processor|memory|audio|battery)$", componentsGroup(), false);
        makeGroup("^(baseboard|audio|bios|slots)$", boardsGroup(), false);
        makeGroup("^(bios|cache)$", hardwareGroup(), false);

    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.HWINFO)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < hwinfoLayout.length; i++) {

                if (currentLine == null) continue;

                if (currentLine.contains(hwinfoLayout[i])) {

                    String lines;
                    List<String> array = new ArrayList<>();

                    while ((lines = reader.readLine()) != null) {

                        //Last line - continue until readLine() equals null
                        if (i == hwinfoLayout.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        if (lines.contains(hwinfoLayout[i+1])) {
                            currentLine = lines;
                            break;
                        }
                        array.add(lines.trim());
                    }

                    //Save line according HARDSYS expected fields
                    this.resources.put(fieldsTranslator(hwinfoLayout[i]), array);

                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
