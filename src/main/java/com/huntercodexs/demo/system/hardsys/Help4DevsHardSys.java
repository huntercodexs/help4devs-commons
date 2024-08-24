
package com.huntercodexs.demo.system.hardsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.system.hardsys.Help4DevsHardSysCommands.sysCmd;

public class Help4DevsHardSys extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private HashMap<String, List<String>> resources;

    public Help4DevsHardSys(Help4DevsHardSysCommands command) {
        this.command = command;
        this.loader(command);
    }

    private String prepareFieldsToInxi(String input) {
        return input
                .replaceAll("PCI Slots:", "slots")
                .replaceAll("CPU: ", "processor")
                .replaceAll("Info: ", "running")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private String prepareFieldsToHwinfo(String input) {
        return input
                .replaceAll("cpu:", "processor")
                .replaceAll("sound:", "audio")
                .replaceAll("keyboard:", "keyboard")
                .replaceAll("mouse:", "mouse")
                .replaceAll("graphics card:", "graphics")
                //.replaceAll("storage:", "drives")
                .replaceAll("network:", "network")
                .replaceAll("network interface:", "network_interface")
                .replaceAll("disk:", "disk")
                .replaceAll("partition:", "partition")
                .replaceAll("usb controller:", "usb")
                .replaceAll("bios:", "bios")
                .replaceAll("memory:", "memory")
                .replaceAll("storage:", "storage")
                .replaceAll("bridge:", "bridge")
                .replaceAll("bluetooth:", "bluetooth")
                .replaceAll("unknown:", "unknown")
                .replaceAll("[^-_a-zA-Z]", "")
                .toLowerCase();
    }

    private void make(String pattern, String hardsy) {

        List<String> merge = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    merge.add(item);
                }
            }
        });

        this.resources.put(hardsy, merge);
    }

    private void merge(String pattern, String hardsy, boolean delete) {

        List<String> merge = new ArrayList<>();
        List<String> remove = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    merge.add(item);

                    /*IMPORTANT: Update the resources content related to current resource*/
                    if (!delete) {
                        this.resources.put(value, merge);
                    }
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

    private void makeAndMergeInxi() {

        /* ! Code Here ! */

        if (HARDSYS_DEBUG) {
            this.resources.forEach((item, list) -> {
                System.out.println("ITEM: " + item);
                for (String value : list) {
                    System.out.println(value);
                }
            });
        }
    }

    private void makeAndMergeHwinfo() {

        /*Single*/
        make("^(disk)$", disk());
        make("^(network)$", network());
        make("^(network_interface)$", networkInterface());
        make("^(keyboard)$", keyboard());
        make("^(mouse)$", mouse());
        make("^(partition)$", partition());

        /*Group*/
        //merge("^(network|network_interface)$", networkGroup(), false);
        //merge("^(disk|partition)$", partitionGroup(), false);
        //merge("^(keyboard|mouse)$", devicesGroup(), false);

        if (HARDSYS_DEBUG) {
            this.resources.forEach((item, list) -> {
                System.out.println("AFTER: ITEM: "+item);
                for (String value : list) {
                    System.out.println("VALUE:AFTER: "+ value);
                }
            });
        }
    }

    private void inxiRun() {

        try {

            Process process = Runtime.getRuntime().exec(sysCmd(Help4DevsHardSysCommands.INXI));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String currentLine = reader.readLine();

            for (int i = 0; i < inxiInfo.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(inxiInfo[i])) {

                    String lines;
                    array.add(currentLine.replace(inxiInfo[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
                        if (i == inxiInfo.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        if (lines.contains(inxiInfo[i+1])) {
                            currentLine = lines;
                            break;
                        }
                        array.add(lines.trim());
                    }

                    //Save line according HARDSYS
                    this.resources.put(prepareFieldsToInxi(inxiInfo[i]), array);
                }
            }

            makeAndMergeInxi();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private void hwinfoRun() {

        try {

            Process process = Runtime.getRuntime().exec(sysCmd(Help4DevsHardSysCommands.HWINFO));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String currentLine = reader.readLine();

            for (int i = 0; i < hwinfoLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(hwinfoLayout[i])) {

                    String lines;
                    array.add(currentLine.replace(hwinfoLayout[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
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

                    //Save line according HARDSYS
                    this.resources.put(prepareFieldsToHwinfo(hwinfoLayout[i]), array);

                }
            }

            makeAndMergeHwinfo();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private void lshwRun() {
    }

    private void lscpuRun() {
    }

    private void lscpu2Run() {
    }

    private void dmidecodeRun() {
    }

    private void systeminfoRun() {
    }

    private void loader(Help4DevsHardSysCommands command) {

        this.resources = new HashMap<>();

        for (String res : hardsys()) {
            this.resources.put(res, new ArrayList<>());
        }

        if (command.equals(Help4DevsHardSysCommands.INXI)) {
            this.inxiRun();
        } else if (command.equals(Help4DevsHardSysCommands.HWINFO)) {
            this.hwinfoRun();
        } else if (command.equals(Help4DevsHardSysCommands.LSHW)) {
            this.lshwRun();
        } else if (command.equals(Help4DevsHardSysCommands.LSCPU)) {
            this.lscpuRun();
        } else if (command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            this.lscpu2Run();
        } else if (command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            this.dmidecodeRun();
        } else if (command.equals(Help4DevsHardSysCommands.SYSTEMINFO_WINDOWS)) {
            this.systeminfoRun();
        } else {
            throw new RuntimeException("Wrong sys cmd to retrieve information: " + sysCmd(command));
        }
    }

    public Help4DevsHardSysMetrics metrics() {
        return new Help4DevsHardSysMetrics(this.resources, this.command);
    }

}

/*TODO: Finalize a segregation items with specific information about each one*/
/*TODO: Create a Sub-groups of items when it is applicable, for example: devices {keyboard, mouse, monitor, hub,...}*/
/*TODO: Create a response grouped by something like: {hardsys:{devices:{keyboard, mouse, etc...}}}, in this case
   the only one call should be required, for example: instance.group();*/