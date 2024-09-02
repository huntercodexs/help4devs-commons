package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsSystemDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> systemDetails;

    public Help4DevsSystemDetails(List<String> system, Help4DevsHardSysCommands command) {
        this.command = command;
        this.systemDetails = system;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            filter.add(details
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            if (details.isEmpty()) continue;
            filter.add(details
                    .replaceAll("type: system ", "")
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            filter.add(details
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            filter.add(details
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            filter.add(details
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.systemDetails) {
            filter.add(details
                    .replaceAll("Kernel:", "kernel:")
                    .replaceAll("Desktop:", "desktop:")
                    .replaceAll("Distro:", "distro:"));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("system"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck("system"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("system"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("system"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck("system"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("system"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("system") +": " + this.command);
    }

}
