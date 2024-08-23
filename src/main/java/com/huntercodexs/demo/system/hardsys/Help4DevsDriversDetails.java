package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsDriversDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> driversDetails;

    public Help4DevsDriversDetails(List<String> drivers, Help4DevsHardSysCommands command) {
        this.command = command;
        this.driversDetails = drivers;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.driversDetails) {
            filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), drivers());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), drivers());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), drivers());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), drivers());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), drivers());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), drivers());
        }
        throw new RuntimeException("Invalid command for "+ drivers() +": " + this.command);
    }

}
