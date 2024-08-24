
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

    private String fieldsTranslatorFromInxi(String input) {
        return input
                .replaceAll("PCI Slots:", "slots")
                .replaceAll("CPU: ", "processor")
                .replaceAll("Info: ", "running")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private String fieldsTranslatorFromHwinfo(String input) {
        return input
                .replaceAll("cpu:", "processor")
                .replaceAll("keyboard:", "keyboard")
                .replaceAll("mouse:", "mouse")
                .replaceAll("monitor:", "monitor")
                .replaceAll("graphics card:", "graphics")
                .replaceAll("sound:", "audio")
                .replaceAll("storage:", "storage")
                .replaceAll("network:", "network")
                .replaceAll("network interface:", "network_interface")
                .replaceAll("disk:", "disk")
                .replaceAll("partition:", "partition")
                .replaceAll("usb controller:", "usb")
                .replaceAll("bios:", "bios")
                .replaceAll("bridge:", "bridge")
                .replaceAll("hub:", "hub")
                .replaceAll("memory:", "memory")
                .replaceAll("bluetooth:", "bluetooth")
                .replaceAll("unknown:", "unknown")
                .replaceAll("[^-_a-zA-Z ]", "")
                .toLowerCase();
    }

    private void makeSource(String pattern, String hardsy) {

        List<String> makeList = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches(pattern)) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
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

    private void makeSourceAndGroupFromInxi() {

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

    private void makeSourceAndGroupFromHwinfo() {

        /*
            These resources have sources from the current command and
            hence needs to treated with a different mode
        */
        makeSource("^(keyboard)$", keyboard());
        makeSource("^(mouse)$", mouse());
        makeSource("^(network)$", network());
        makeSource("^(network_interface)$", networkInterface());
        makeSource("^(disk)$", disk());
        makeSource("^(partition)$", partition());

        //makeGroup("^(keyboard|mouse)$", devicesGroup(), false);
        //makeGroup("^(network|network_interface)$", networkGroup(), false);
        //makeGroup("^(disk|partition)$", partitionGroup(), false);

        if (HARDSYS_DEBUG) {
            this.resources.forEach((item, list) -> {
                System.out.println("AFTER: ITEM: "+item);
                for (String value : list) {
                    System.out.println("VALUE:AFTER: "+ value);
                }
            });
        }
    }

    private BufferedReader execute(Help4DevsHardSysCommands command) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(sysCmd(command));
            return  new BufferedReader(new InputStreamReader(process.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void inxiRun() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.INXI)) {

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
                    this.resources.put(fieldsTranslatorFromInxi(inxiInfo[i]), array);
                }
            }

            makeSourceAndGroupFromInxi();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    private void hwinfoRun() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.HWINFO)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < hwinfoLayout.length; i++) {

                if (currentLine == null) continue;

                if (currentLine.contains(hwinfoLayout[i])) {

                    String lines;
                    List<String> array = new ArrayList<>();
                    array.add(currentLine.replace(hwinfoLayout[i], "").trim());

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
                    this.resources.put(fieldsTranslatorFromHwinfo(hwinfoLayout[i]), array);

                }
            }

            makeSourceAndGroupFromHwinfo();

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
        } else if (command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            this.systeminfoRun();
        } else {
            throw new RuntimeException("Wrong sys cmd to retrieve information: " + sysCmd(command));
        }
    }

    public Help4DevsHardSysMetrics metrics() {
        return new Help4DevsHardSysMetrics(this.resources, this.command);
    }

}

/*DONE: Finalize a segregation items with specific information about each one - HWINFO*/
/*DONE: Review all resources to find out any kind of code error or optimization  - HWINFO*/
/*TODO: Create a Sub-groups of items when it is applicable, for example: devices {keyboard, mouse, monitor, hub,...}*/
/*TODO: Create a response grouped by something like: {hardsys:{devices:{keyboard, mouse, etc...}}}, in this case
   the only one call should be required, for example: instance.group();*/