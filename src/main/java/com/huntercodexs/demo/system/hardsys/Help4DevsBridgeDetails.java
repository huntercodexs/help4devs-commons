package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsBridgeDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> bridgeDetails;

    public Help4DevsBridgeDetails(List<String> bridge, Help4DevsHardSysCommands command) {
        this.command = command;
        this.bridgeDetails = bridge;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {
            filter.add(details.replaceAll("BRIDGE: ", "bridge: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {

            if (details == null || details.isEmpty()) continue;

            details = details.replaceAll("\\[", "(").replaceAll("]", ")");

            details = indexer(details, "(\\w+)", "source: $1", "", false);
            details = indexer(details, "source: ", "source", ": ", true);
            filter.add(details);
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {
            filter.add(details.replaceAll("BRIDGE: ", "bridge: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {
            filter.add(details.replaceAll("BRIDGE: ", "bridge: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {
            filter.add(details.replaceAll("BRIDGE: ", "bridge: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bridgeDetails) {
            filter.add(details.replaceAll("BRIDGE: ", "bridge: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), bridge());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), bridge());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), bridge());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), bridge());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), bridge());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), bridge());
        }
        throw new RuntimeException("Invalid command for "+ bridge() +": " + this.command);
    }

}
