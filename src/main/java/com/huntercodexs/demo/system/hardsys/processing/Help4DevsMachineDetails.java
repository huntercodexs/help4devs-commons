package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsMachineDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> machineDetails;

    public Help4DevsMachineDetails(List<String> machine, Help4DevsHardSysCommands command) {
        this.command = command;
        this.machineDetails = machine;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.machineDetails) {

            details = indexer(details, "Type: ", "type", ": ", false);
            details = indexer(details, "System: ", "system", ": ", false);
            details = indexer(details, "serial: ", "serial", ": ", true);
            details = indexer(details, "v: ", "version", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.machineDetails) {
            filter.add(details
                    .replaceAll("Type:", "type:")
                    .replaceAll("System:", "system:")
                    .replaceFirst("serial:", "serial_"+n+":")
                    .replaceFirst(" v:", " version_"+n+":"));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.machineDetails) {
            filter.add(details
                    .replaceAll("Type:", "type:")
                    .replaceAll("System:", "system:")
                    .replaceFirst("serial:", "serial_"+n+":")
                    .replaceFirst(" v:", " version_"+n+":"));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.machineDetails) {
            filter.add(details
                    .replaceAll("Type:", "type:")
                    .replaceAll("System:", "system:")
                    .replaceFirst("serial:", "serial_"+n+":")
                    .replaceFirst(" v:", " version_"+n+":"));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.machineDetails) {
            filter.add(details
                    .replaceAll("Type:", "type:")
                    .replaceAll("System:", "system:")
                    .replaceFirst("serial:", "serial_"+n+":")
                    .replaceFirst(" v:", " version_"+n+":"));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.machineDetails) {
            filter.add(details
                    .replaceAll("Type:", "type:")
                    .replaceAll("System:", "system:")
                    .replaceFirst("serial:", "serial_"+n+":")
                    .replaceFirst(" v:", " version_"+n+":"));
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("machine"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck("machine"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("machine"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("machine"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck("machine"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("machine"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("machine") +": " + this.command);
    }

}
