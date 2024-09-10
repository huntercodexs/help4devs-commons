package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsNetworkDetails extends Help4DevsHardSysBase {

    private final String resourceName = "network";
    private final List<String> networkDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsNetworkDetails(List<String> network, Help4DevsHardSysCommands command) {
        this.command = command;
        this.networkDetails = network;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.networkDetails) {

            details = indexer(details, "bus ID: ", "busId", ": ", false);
            details = indexer(details, "v: ", "version", ": ", true);
            details = indexer(details, "driver: ", "driver", ": ", true);
            details = indexer(details, "port: ", "port", ": ", true);
            details = indexer(details, "busId: ", "busId", ": ", true);
            details = indexer(details, "IF: ", "IF", ": ", true);
            details = indexer(details, "state: ", "state", ": ", true);
            details = indexer(details, "mac: ", "mac", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.networkDetails) {
            if (details.isEmpty() || !details.contains(hardsysCheck("network"))) continue;
            listFilter.add(sourceFilter(details, hardsysCheck("network"), index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.networkDetails) {
            if (details.isEmpty() || !details.contains(hardsysCheck("network"))) continue;
            listFilter.add(lshwFilter(details, hardsysCheck("network"), index));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.networkDetails) {
            filter.add(details
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("port: ", "port_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": ")
                    .replaceFirst("IF: ", "IF_"+n+": ")
                    .replaceFirst("state: ", "state_"+n+": ")
                    .replaceFirst("mac: ", "mac_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.networkDetails) {
            filter.add(details
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("port: ", "port_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": ")
                    .replaceFirst("IF: ", "IF_"+n+": ")
                    .replaceFirst("state: ", "state_"+n+": ")
                    .replaceFirst("mac: ", "mac_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.networkDetails) {
            filter.add(details
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("port: ", "port_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": ")
                    .replaceFirst("IF: ", "IF_"+n+": ")
                    .replaceFirst("state: ", "state_"+n+": ")
                    .replaceFirst("mac: ", "mac_"+n+": "));
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck(resourceName));
        }

        throw new RuntimeException("Invalid command for "+ hardsysCheck(resourceName) +": " + this.command);
    }

}
