package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsAudioDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> audioDetails;

    public Help4DevsAudioDetails(List<String> audio, Help4DevsHardSysCommands command) {
        this.command = command;
        this.audioDetails = audio;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.audioDetails) {

            details = indexer(details, "Sound Server: ", "soundServer", ": ", false);
            details = indexer(details, "v: ", "version", ": ", true);
            details = indexer(details, "bus ID: ", "busId", ": ", true);
            details = indexer(details, "vendor: ", "vendor", ": ", true);
            details = indexer(details, "driver: ", "driver", ": ", true);
            details = indexer(details, "busId: ", "busId", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.audioDetails) {

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
        for (String details : this.audioDetails) {
            filter.add(details
                    .replaceAll("Sound Server: ", "soundServer: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst("vendor: ", "vendor_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.audioDetails) {
            filter.add(details
                    .replaceAll("Sound Server: ", "soundServer: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst("vendor: ", "vendor_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.audioDetails) {
            filter.add(details
                    .replaceAll("Sound Server: ", "soundServer: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst("vendor: ", "vendor_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.audioDetails) {
            filter.add(details
                    .replaceAll("Sound Server: ", "soundServer: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceAll("bus ID: ", "busId: ")
                    .replaceFirst("vendor: ", "vendor_"+n+": ")
                    .replaceFirst("driver: ", "driver_"+n+": ")
                    .replaceFirst("busId: ", "busId_"+n+": "));
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), audio());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), audio());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), audio());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), audio());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), audio());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), audio());
        }
        throw new RuntimeException("Invalid command for "+ audio() +": " + this.command);
    }

}
