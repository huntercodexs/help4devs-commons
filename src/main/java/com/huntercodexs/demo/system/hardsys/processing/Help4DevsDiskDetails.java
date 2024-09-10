package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsDiskDetails extends Help4DevsHardSysBase {

    private final String resourceName = "disk";
    private final List<String> diskDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsDiskDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.diskDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.diskDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: Local "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.diskDetails) {
            if (details.isEmpty() || !details.contains(hardsysCheck("disk"))) continue;
            listFilter.add(sourceFilter(details, hardsysCheck("disk"), index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.diskDetails) {
            if (details.isEmpty() || !details.contains(hardsysCheck("disk"))) continue;
            listFilter.add(lshwFilter(details, hardsysCheck("disk"), index));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.diskDetails) {
            filter.add(details.replaceAll("Disk: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.diskDetails) {
            filter.add(details.replaceAll("Disk: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.diskDetails) {
            filter.add(details.replaceAll("Disk: ", resourceName+": "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck(resourceName));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck(resourceName) +": " + this.command);
    }

}

