package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsSlotsDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> slotsDetails;

    public Help4DevsSlotsDetails(List<String> slot, Help4DevsHardSysCommands command) {
        this.command = command;
        this.slotsDetails = slot;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String slot : this.slotsDetails) {
            filter.add(slot.replaceAll("Permissions: ", "permissions: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[4]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), HARDSYS[4]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[4]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[4]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[4]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[4]);
        }
        throw new RuntimeException("Invalid command for "+ HARDSYS[4]+": " + this.command);
    }

}
