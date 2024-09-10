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
            .replaceAll("cpu:", hardsysCheck("processor"))
            .replaceAll("keyboard:", hardsysCheck("keyboard"))
            .replaceAll("mouse:", hardsysCheck("mouse"))
            .replaceAll("monitor:", hardsysCheck("monitor"))
            .replaceAll("graphics card:", hardsysCheck("graphics"))
            .replaceAll("sound:", hardsysCheck("audio"))
            .replaceAll("storage:", hardsysCheck("storage"))
            .replaceAll("network:", hardsysCheck("network"))
            .replaceAll("network interface:", hardsysCheck("nicInterface"))
            .replaceAll("disk:", hardsysCheck("disk"))
            .replaceAll("partition:", hardsysCheck("partition"))
            .replaceAll("cdrom:", hardsysCheck("cdrom"))
            .replaceAll("usb controller:", hardsysCheck("usb"))
            .replaceAll("bios:", hardsysCheck("bios"))
            .replaceAll("bridge:", hardsysCheck("bridge"))
            .replaceAll("hub:", hardsysCheck("hub"))
            .replaceAll("memory:", hardsysCheck("memory"))
            .replaceAll("bluetooth:", hardsysCheck("bluetooth"))
            .replaceAll("unknown:", hardsysCheck("unknown"))
            .replaceAll("[^-_a-zA-Z ]", "");
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
        makeSource("^("+ hardsysCheck("nicInterface")+")$", hardsysCheck("nicInterface"));
        makeSource("^(disk)$", hardsysCheck("disk"));
        makeSource("^(partition)$", hardsysCheck("partition"));
        makeSource("^(cdrom)$", hardsysCheck("cdrom"));

        /*
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(keyboard|mouse|monitor|hub)$", hardsysCheck("devicesGroup"), false);
        makeGroup("^(network|nicInterface|bridge|hub|switch)$", hardsysCheck("networksGroup"), false);
        makeGroup("^(disk|partition|cdrom|storage)$", hardsysCheck("drivesGroup"), false);
        makeGroup("^(processor|memory|audio|battery)$", hardsysCheck("componentsGroup"), false);
        makeGroup("^(baseboard|audio|bios|slots)$", hardsysCheck("boardsGroup"), false);
        makeGroup("^(bios|cache)$", hardsysCheck("hardwareGroup"), false);

    }

    private boolean forceBreak(int index, String line) {
        for (int k = index; k < hwinfoLayout.length; k++) {
            if (line.contains(hwinfoLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.HWINFO)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < hwinfoLayout.length; i++) {

                //if (currentLine == null) continue;

                if (currentLine.contains(hwinfoLayout[i])) {

                    String line;
                    List<String> array = new ArrayList<>();

                    while ((line = reader.readLine()) != null) {

                        //Last line - continue until readLine() equals null
                        if (i == hwinfoLayout.length-1) {
                            array.add(line.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (line.contains(hwinfoLayout[i+1])) {
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
