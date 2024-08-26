package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsNetworkGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> networkDetails;

    public Help4DevsNetworkGroupDetails(List<String> network, Help4DevsHardSysCommands command) {
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

    private List<String> detailsFromLinuxCommandHwinfo(String device) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.networkDetails) {
            if (!details.contains("type: "+device)) continue;
            listFilter.add(sourceFilter(details, device, index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
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
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), networksGroup());

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String network = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(network()), network());
            String networkInterface = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(networkInterface()), "networkInterface");
            String bridge = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(bridge()), bridge());
            String hub = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hub()), hub());
            String switcher = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(switcher()), switcher());
            return jsonMergerRFC8259(Arrays.asList(network, networkInterface, bridge, hub, switcher), networksGroup());

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), networksGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), networksGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), networksGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), networksGroup());
        }

        throw new RuntimeException("Invalid command for "+ networksGroup() +": " + this.command);
    }

}
