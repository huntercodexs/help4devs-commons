package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsConnectorDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> connectorDetails;

    public Help4DevsConnectorDetails(List<String> connector, Help4DevsHardSysCommands command) {
        this.command = command;
        this.connectorDetails = connector;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String connector : this.connectorDetails) {
            filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), connector());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), connector());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), connector());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), connector());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), connector());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), connector());
        }
        throw new RuntimeException("Invalid command for " + connector() + ": " + this.command);
    }

}
