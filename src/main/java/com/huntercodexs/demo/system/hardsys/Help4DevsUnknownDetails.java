package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsUnknownDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> unknownDetails;

    public Help4DevsUnknownDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.unknownDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.unknownDetails) {
            filter.add(details.replaceAll("Unknown: ", "unknown: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.unknownDetails) {

            indexerUpdate(index);
            details = indexer(details, "source: ", "source", ": ", true);

            indexerUpdate(index);
            details = indexer(details, "description: ", "description", ": ", true);

            details = details.replaceAll("\\.@\\.", ":");
            listFilter.add(details);

            index++;

        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.unknownDetails) {
            filter.add(details.replaceAll("Unknown: ", "unknown: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.unknownDetails) {
            filter.add(details.replaceAll("Unknown: ", "unknown: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.unknownDetails) {
            filter.add(details.replaceAll("Unknown: ", "unknown: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.unknownDetails) {
            filter.add(details.replaceAll("Unknown: ", "unknown: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), unknown());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), unknown());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), unknown());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), unknown());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), unknown());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), unknown());
        }
        throw new RuntimeException("Invalid command for "+ unknown() +": " + this.command);
    }

}

