
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
                .replaceAll("storage:", "drives")
                .replaceAll("network:", "network")
                .replaceAll("network interface:", "interface")
                .replaceAll("disk:", "disk")
                .replaceAll("partition:", "source")
                .replaceAll("usb controller:", "usb")
                .replaceAll("bios:", "bios")
                .replaceAll("memory:", "memory")
                .replaceAll("unknown:", "system")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private void checkAndMergeInxi() {
        if (HARDSYS_DEBUG) {
            this.resources.forEach((item, list) -> {
                System.out.println("ITEM: " + item);
                for (String value : list) {
                    System.out.println(value);
                }
            });
        }
    }

    private void checkAndMergeHwinfo() {

        List<String> merge1 = new ArrayList<>();
        List<String> remove1 = new ArrayList<>();

        if (HARDSYS_DEBUG) {
            this.resources.forEach((item, list) -> {
                System.out.println("BEFORE: ITEM: "+item);
                for (String value : list) {
                    System.out.println("VALUE:BEFORE: "+ value);
                }
            });
        }

        /*Devices*/

        this.resources.forEach((value, list) -> {
            if (value.matches("^(keyboard|mouse)$")) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    merge1.add(item);
                }
                remove1.add(value);
            }
        });

        for (String item : remove1) {
            this.resources.remove(item);
        }

        this.resources.put(HARDSYS[21], merge1);

        /*Network*/

        List<String> merge2 = new ArrayList<>();
        List<String> remove2 = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches("^(network|interface)$")) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    merge2.add(item);
                }
                remove2.add(value);
            }
        });

        for (String item : remove2) {
            this.resources.remove(item);
        }

        this.resources.put(HARDSYS[8], merge2);

        /*Partition*/

        List<String> merge3 = new ArrayList<>();
        List<String> remove3 = new ArrayList<>();

        this.resources.forEach((value, list) -> {
            if (value.matches("^(disk|source)$")) {
                for (String item : list) {
                    if (item.isEmpty()) continue;

                    item = "type: " +value+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    merge3.add(item);
                }
                remove3.add(value);
            }
        });

        for (String item : remove3) {
            this.resources.remove(item);
        }

        this.resources.put(HARDSYS[10], merge3);

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
                            break;
                        }

                        if (lines.contains(inxiInfo[i+1])) {
                            currentLine = lines;
                            break;
                        }
                        array.add(lines.trim());
                    }

                    //Save line according INFO_INDEX
                    this.resources.put(prepareFieldsToInxi(inxiInfo[i]), array);
                }
            }

            checkAndMergeInxi();

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
                            break;
                        }

                        if (lines.contains(hwinfoLayout[i+1])) {
                            currentLine = lines;
                            break;
                        }
                        array.add(lines.trim());
                    }

                    //Save line according INFO_INDEX
                    this.resources.put(prepareFieldsToHwinfo(hwinfoLayout[i]), array);
                }
            }

            checkAndMergeHwinfo();

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

        for (String res : HARDSYS) {
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
