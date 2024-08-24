package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsBluetoothDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> bluetoothDetails;

    public Help4DevsBluetoothDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.bluetoothDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bluetoothDetails) {
            filter.add(details.replaceAll("Bluetooth: ", "bluetooth: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bluetoothDetails) {

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
        for (String details : this.bluetoothDetails) {
            filter.add(details.replaceAll("Bluetooth: ", "bluetooth: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bluetoothDetails) {
            filter.add(details.replaceAll("Bluetooth: ", "bluetooth: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bluetoothDetails) {
            filter.add(details.replaceAll("Bluetooth: ", "bluetooth: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.bluetoothDetails) {
            filter.add(details.replaceAll("Bluetooth: ", "bluetooth: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), bluetooth());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), bluetooth());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), bluetooth());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), bluetooth());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), bluetooth());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), bluetooth());
        }
        throw new RuntimeException("Invalid command for "+ bluetooth() +": " + this.command);
    }

}

