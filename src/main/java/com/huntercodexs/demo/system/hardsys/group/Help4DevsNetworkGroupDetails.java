package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.processing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsNetworkGroupDetails extends Help4DevsHardSysBase {

    private final List<String> networkDetails;
    private final Help4DevsHardSysCommands command;
    private final String resourceName = "networksGroup";

    public Help4DevsNetworkGroupDetails(List<String> network, Help4DevsHardSysCommands command) {
        this.command = command;
        this.networkDetails = network;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.networkDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device, boolean replace) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.networkDetails) {
            if (!details.contains("type: "+device)) continue;
            if (replace) {
                details = details.replaceAll("type: " + device + " ?", "");
                details = details.replaceAll("source: ?", "");
            }
            listFilter.add(details);
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

    private List<String> detailsFromLinuxCommandLsPci() {
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

    private List<String> detailsFromLinuxCommandLsUsb() {
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

    private List<String> detailsFromWindowsCommandSystemInfo() {
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

    private String groupsByInxiCommand() {

        String network = new Help4DevsNetworkDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("network")),
                this.command).getDetails();

        String nicInterface = new Help4DevsNicInterfaceDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("nicInterface")),
                this.command).getDetails();

        String bridge = new Help4DevsBridgeDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("bridge")),
                this.command).getDetails();

        String hub = new Help4DevsHubDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("hub")),
                this.command).getDetails();

        String switcher = new Help4DevsSwitchDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("switcher")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(network, nicInterface, bridge, hub, switcher), hardsysCheck(resourceName));

    }

    private String groupsByHwinfoCommand() {

        String network = new Help4DevsNetworkDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("network"), false),
                this.command).getDetails();

        String nicInterface = new Help4DevsNicInterfaceDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("nicInterface"), false),
                this.command).getDetails();

        String bridge = new Help4DevsBridgeDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("bridge"), true),
                this.command).getDetails();

        String hub = new Help4DevsHubDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("hub"), true),
                this.command).getDetails();

        String switcher = new Help4DevsSwitchDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("switcher"), true),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(network, nicInterface, bridge, hub, switcher), hardsysCheck(resourceName));
    }

    private String groupsByLshwCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck(resourceName));
    }

    private String groupsByLsCpuCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck(resourceName));
    }

    private String groupsByLsPciCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck(resourceName));
    }

    private String groupsByLsUsbCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck(resourceName));
    }

    private String groupsByDmidecodeCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck(resourceName));
    }

    private String groupsBySystemInfoCommand() {
        return jsonCreatorRFC8259(detailsFromWindowsCommandSystemInfo(), hardsysCheck(resourceName));
    }

    public String getDetails() {

        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return groupsByInxiCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return groupsByHwinfoCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return groupsByLshwCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return groupsByLsCpuCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return groupsByLsPciCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return groupsByLsUsbCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return groupsByDmidecodeCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            return groupsBySystemInfoCommand();

        }

        throw new RuntimeException("Invalid command for "+ hardsysCheck(resourceName) +": " + this.command);
    }

}
