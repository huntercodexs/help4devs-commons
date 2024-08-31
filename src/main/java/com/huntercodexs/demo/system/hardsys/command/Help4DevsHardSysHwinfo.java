package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @implNote This class process for JSON and DTO formats results
 * */
public class Help4DevsHardSysHwinfo extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfo(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private String layoutTranslator(String input) {
        return input
                .replaceAll("cpu:", hardsys("processor"))
                .replaceAll("keyboard:", hardsys("keyboard"))
                .replaceAll("mouse:", hardsys("mouse"))
                .replaceAll("monitor:", hardsys("monitor"))
                .replaceAll("graphics card:", hardsys("graphics"))
                .replaceAll("sound:", hardsys("audio"))
                .replaceAll("storage:", hardsys("storage"))
                .replaceAll("network:", hardsys("network"))
                .replaceAll("network interface:", hardsys("networkInterface"))
                .replaceAll("disk:", hardsys("disk"))
                .replaceAll("partition:", hardsys("partition"))
                .replaceAll("cdrom:", hardsys("cdrom"))
                .replaceAll("usb controller:", hardsys("usb"))
                .replaceAll("bios:", hardsys("bios"))
                .replaceAll("bridge:", hardsys("bridge"))
                .replaceAll("hub:", hardsys("hub"))
                .replaceAll("memory:", hardsys("memory"))
                .replaceAll("bluetooth:", hardsys("bluetooth"))
                .replaceAll("unknown:", hardsys("unknown"))
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
        makeSource("^(keyboard)$", hardsys("keyboard"));
        makeSource("^(mouse)$", hardsys("mouse"));
        makeSource("^(network)$", hardsys("network"));
        makeSource("^("+hardsys("networkinterface")+")$", hardsys("networkinterface"));
        makeSource("^(disk)$", hardsys("disk"));
        makeSource("^(partition)$", hardsys("partition"));
        makeSource("^(cdrom)$", hardsys("cdrom"));

        /*
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(keyboard|mouse|monitor|hub)$", hardsys("devicesGroup"), false);
        makeGroup("^(network|"+hardsys("networkInterface")+"|bridge|hub|switch)$", hardsys("networksGroup"), false);
        makeGroup("^(disk|partition|cdrom|storage)$", hardsys("drivesGroup"), false);
        makeGroup("^(processor|memory|audio|battery)$", hardsys("componentsGroup"), false);
        makeGroup("^(baseboard|audio|bios|slots)$", hardsys("boardsGroup"), false);
        makeGroup("^(bios|cache)$", hardsys("hardwareGroup"), false);

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
                    this.resources.put(layoutTranslator(hwinfoLayout[i]), array);

                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
