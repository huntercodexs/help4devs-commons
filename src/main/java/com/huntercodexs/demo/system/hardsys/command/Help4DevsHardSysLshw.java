
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLshw extends Help4DevsHardSysBase {

    public Help4DevsHardSysLshw(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private String layoutTranslator(String input) {
        return input
                .replaceAll("disk", hardsysCheck("disk"))
                .replaceAll("bridge", hardsysCheck("bridge"))
                .replaceAll("memory", hardsysCheck("memory"))
                .replaceAll("input", hardsysCheck("unknown"))
                .replaceAll("generic", hardsysCheck("unknown"))
                .replaceAll("system", hardsysCheck("unknown"))
                .replaceAll("display", hardsysCheck("graphics"))
                .replaceAll("network", hardsysCheck("network"))
                .replaceAll("storage", hardsysCheck("storage"))
                .replaceAll("volume", hardsysCheck("partition"))
                .replaceAll("processor", hardsysCheck("processor"))
                .replaceAll("multimedia", hardsysCheck("multimedia"))
                .replaceAll("bus", hardsysCheck("controller"))
                .replaceAll("communication", hardsysCheck("controller"))
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private String layoutRevert(String input) {
        return input
                .replaceAll("bus", "controller")
                .replaceAll("communication", "controller")
                .replaceAll("display", "graphics")
                .replaceAll("(input|generic|system)", "unknown")
                .replaceAll("volume", "partition");
    }

    private void makeSource(String pattern, String hardsy) {

        List<String> makeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {
            if (key.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = item
                            .replaceAll("( {12,})", " n/a ")
                            .replaceAll("( {2,})", ":::")
                            .replaceAll(" (n/a) (\\w+) (n/a) ", ":::$1:::$2:::")
                            .replaceAll(" n/a ", ":::n/a:::");

                    String[] split = item.split(":::");

                    makeList.add(
                            " type: "+ layoutRevert(split[2]) +
                            " source: " + split[0] +
                            " device: "+ split[1] +
                            " description: " + split[3] + " ("+split[2]+")");

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
                    merge.add(layoutRevert(item));
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

    private void makeMouse(String content) {
        List<String> current = this.resources.get(hardsysCheck("mouse"));
        current.add(content);
        this.resources.put(hardsysCheck("mouse"), current);
    }

    private void makeKeyboard(String content) {
        List<String> current = this.resources.get(hardsysCheck("keyboard"));
        current.add(content);
        this.resources.put(hardsysCheck("keyboard"), current);
    }

    private void makeMachine(String content) {
        List<String> current = this.resources.get(hardsysCheck("machine"));
        current.add(content);
        this.resources.put(hardsysCheck("machine"), current);
    }

    private void makeUnknown(List<String> unknown) {
        this.resources.put(hardsysCheck("unknown"), unknown);
    }

    private void splitResources() {

        List<String> keepUnknown = new ArrayList<>();

        for (String unknown : this.resources.get(hardsysCheck("unknown"))) {

            if (unknown.contains("input")) {
                if (unknown.toLowerCase().contains("mouse") && !unknown.toLowerCase().contains("keyboard")) {
                    makeMouse(unknown);
                } else if (unknown.toLowerCase().contains("keyboard")) {
                    makeKeyboard(unknown);
                } else if (unknown.contains("PnP device")) {
                    keepUnknown.add(unknown);
                }
            }

            if (unknown.contains("system")) {
                if (unknown.contains("PnP device")) {
                    keepUnknown.add(unknown);
                } else {
                    makeMachine(unknown);
                }
            }

            if (unknown.contains("generic")) {
                keepUnknown.add(unknown);
            }
        }

        makeUnknown(keepUnknown);
    }

    private void makeSourceAndGroup() {

        /*
         * These resources have sources from the current command and
         * hence needs to treated with a different mode
         * */
        makeSource("^(keyboard)$", hardsysCheck("keyboard"));
        makeSource("^(mouse)$", hardsysCheck("mouse"));
        makeSource("^(network)$", hardsysCheck("network"));
        makeSource("^(disk)$", hardsysCheck("disk"));
        makeSource("^(partition)$", hardsysCheck("partition"));
        makeSource("^(controller)$", hardsysCheck("controller"));
        makeSource("^(bridge)$", hardsysCheck("bridge"));
        makeSource("^(unknown)$", hardsysCheck("unknown"));
        makeSource("^(memory)$", hardsysCheck("memory"));
        makeSource("^(multimedia)$", hardsysCheck("multimedia"));
        makeSource("^(processor)$", hardsysCheck("processor"));
        makeSource("^(storage)$", hardsysCheck("storage"));
        makeSource("^(machine)$", hardsysCheck("machine"));
        makeSource("^(graphics)$", hardsysCheck("graphics"));

        /*
         * In this point the resources already  was set and are
         * done to be used in the related group
         * */
        makeGroup("^(battery|sensors)$", hardsysCheck("devicesGroup"), false);
        makeGroup("^(network|bridge)$", hardsysCheck("networksGroup"), false);
        makeGroup("^(partition|disk|storage)$", hardsysCheck("drivesGroup"), false);
        makeGroup("^(processor|memory|multimedia)$", hardsysCheck("componentsGroup"), false);
        makeGroup("^(graphics|multimedia)$", hardsysCheck("boardsGroup"), false);
        makeGroup("^(processor|memory|disk|controller|partition|unknown)$", hardsysCheck("hardwareGroup"), false);

    }

    private List<String> reader() {
        List<String> output = new ArrayList<>();

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.LSHW)) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("==============================")) continue;
                if (line.contains("H/W path")) continue;
                output.add(line);
            }

            return output;

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    public void run() {

        List<String> output = reader();

        for (String layout : lshwLayout) {

            List<String> array = new ArrayList<>();

            for (String line : output) {
                if (line.contains(layout)) {
                    array.add(line);
                }
            }

            String translated = layoutTranslator(layout);

            List<String> current = this.resources.get(translated);

            if (!current.isEmpty()) {
                array.addAll(current);
            }

            this.resources.put(translated, array);

        }

        splitResources();
        makeSourceAndGroup();

    }

}
