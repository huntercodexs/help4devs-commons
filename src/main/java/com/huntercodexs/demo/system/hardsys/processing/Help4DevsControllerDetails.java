package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsControllerDetails extends Help4DevsHardSysBase {

    private final String resourceName = "controller";
    private final Help4DevsHardSysCommands command;
    private final List<String> controllerDetails;

    public Help4DevsControllerDetails(List<String> baseboard, Help4DevsHardSysCommands command) {
        this.command = command;
        this.controllerDetails = baseboard;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String baseboard : this.controllerDetails) {
            filter.add(baseboard.replaceAll("CONTROLLER: ", resourceName+": "));
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
        throw new RuntimeException("Invalid command for " + hardsysCheck(resourceName) + ": " + this.command);
    }

}
