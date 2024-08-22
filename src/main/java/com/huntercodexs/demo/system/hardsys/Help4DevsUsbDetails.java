package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsUsbDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> UsbDetails;

    public Help4DevsUsbDetails(List<String> usb, Help4DevsHardSysCommands command) {
        this.command = command;
        this.UsbDetails = usb;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.UsbDetails) {

            details = indexer(details, "(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3", "", false);
            details = indexer(details, "info: ", "info", ": ", true);
            details = indexer(details, "rev: ", "rev", ": ", true);
            details = indexer(details, "Hub: ", "Hub", ": ", true);
            details = indexer(details, "type: ", "type", ": ", true);
            details = indexer(details, "driver: ", "driver", ": ", true);
            details = indexer(details, "ports: ", "ports", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.UsbDetails) {

            if (details == null || details.isEmpty()) continue;

            details = indexer(details, "(\\w+)", "name: $1", "", false);
            details = indexer(details, "name: ", "name", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.UsbDetails) {
            filter.add(details
                    .replaceAll("(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3")
                    .replaceFirst("info: ", "info_"+n+": ")
                    .replaceFirst("rev: ", "rev_"+n+": ")
                    .replaceFirst("Hub: ", "hub_"+n+": ")
                    .replaceFirst("type: ", "type_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("ports: ", "ports_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.UsbDetails) {
            filter.add(details
                    .replaceAll("(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3")
                    .replaceFirst("info: ", "info_"+n+": ")
                    .replaceFirst("rev: ", "rev_"+n+": ")
                    .replaceFirst("Hub: ", "hub_"+n+": ")
                    .replaceFirst("type: ", "type_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("ports: ", "ports_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.UsbDetails) {
            filter.add(details
                    .replaceAll("(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3")
                    .replaceFirst("info: ", "info_"+n+": ")
                    .replaceFirst("rev: ", "rev_"+n+": ")
                    .replaceFirst("Hub: ", "hub_"+n+": ")
                    .replaceFirst("type: ", "type_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("ports: ", "ports_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.UsbDetails) {
            filter.add(details
                    .replaceAll("(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3")
                    .replaceFirst("info: ", "info_"+n+": ")
                    .replaceFirst("rev: ", "rev_"+n+": ")
                    .replaceFirst("Hub: ", "hub_"+n+": ")
                    .replaceFirst("type: ", "type_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("ports: ", "ports_"+n+": "));
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[11]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), HARDSYS[11]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[11]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[11]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[11]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[11]);
        }
        throw new RuntimeException("Invalid command for "+ HARDSYS[11]+": " + this.command);
    }

}
