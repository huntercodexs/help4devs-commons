package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsProcessorDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> processorDetails;

    public Help4DevsProcessorDetails(List<String> processor, Help4DevsHardSysCommands command) {
        this.command = command;
        this.processorDetails = processor;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            filter.add(details
                    .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            if (details == null || details.isEmpty()) continue;
            filter.add(detailsFilter(details, "core"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            filter.add(details
                    .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            filter.add(details
                    .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            filter.add(details
                    .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.processorDetails) {
            filter.add(details
                    .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), processor());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), processor());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), processor());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), processor());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), processor());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), processor());
        }
        throw new RuntimeException("Invalid command for "+ processor() +": " + this.command);
    }

}
