package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsMemoryDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> memoryDetails;

    public Help4DevsMemoryDetails(List<String> memory, Help4DevsHardSysCommands command) {
        this.command = command;
        this.memoryDetails = memory;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {
            filter.add(details
                    .replaceAll("RAM: total:", "type: RAM total:")
                    .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {

            if (details == null || details.isEmpty()) continue;

            details = indexer(details, "(\\w+)", "name: $1", "", false);
            details = indexer(details, "name: ", "name", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {
            filter.add(details
                    .replaceAll("RAM: total:", "type: RAM total:")
                    .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {
            filter.add(details
                    .replaceAll("RAM: total:", "type: RAM total:")
                    .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {
            filter.add(details
                    .replaceAll("RAM: total:", "type: RAM total:")
                    .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.memoryDetails) {
            filter.add(details
                    .replaceAll("RAM: total:", "type: RAM total:")
                    .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[3]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), HARDSYS[3]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[3]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[3]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[3]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[3]);
        }

        throw new RuntimeException("Invalid command for "+ HARDSYS[3]+": " + this.command);
    }

}
