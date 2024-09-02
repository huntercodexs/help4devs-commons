package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

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
            filter.add(detailsFilter(details, "name"));
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
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("memory"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck("memory"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("memory"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("memory"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck("memory"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("memory"));
        }

        throw new RuntimeException("Invalid command for "+ hardsysCheck("memory") +": " + this.command);
    }

}
