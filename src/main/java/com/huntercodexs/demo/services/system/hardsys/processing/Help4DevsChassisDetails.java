package com.huntercodexs.demo.services.system.hardsys.processing;

import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsChassisDetails extends Help4DevsHardSysBase {

    private final String resourceName = "chassis";
    private final List<String> chassisDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsChassisDetails(List<String> chassis, Help4DevsHardSysCommands command) {
        this.command = command;
        this.chassisDetails = chassis;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.chassisDetails) {
            filter.add(details.replaceAll("CHASSIS: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.chassisDetails) {
            filter.add(details.replaceAll("CHASSIS: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.chassisDetails) {
            if (details.isEmpty() || !details.contains(hardsysCheck("chassis"))) continue;
            listFilter.add(lshwFilter(details, hardsysCheck("chassis"), index));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.chassisDetails) {
            filter.add(details.replaceAll("CHASSIS: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.chassisDetails) {
            filter.add(details.replaceAll("CHASSIS: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.chassisDetails) {
            filter.add(details.replaceAll("CHASSIS: ", resourceName+": "));
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