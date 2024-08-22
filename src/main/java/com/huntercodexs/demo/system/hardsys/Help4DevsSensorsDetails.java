package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsSensorsDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> sensorsDetails;

    public Help4DevsSensorsDetails(List<String> sensors, Help4DevsHardSysCommands command) {
        this.command = command;
        this.sensorsDetails = sensors;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "temp: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "rpm: ")
            );
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
            );
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
            );
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
            );
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
            );
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.sensorsDetails) {
            filter.add(details
                    .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                    .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
            );
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[12]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), HARDSYS[12]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[12]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[12]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[12]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[12]);
        }
        throw new RuntimeException("Invalid command for "+ HARDSYS[12]+": " + this.command);
    }

}
