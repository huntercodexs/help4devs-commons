package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsRunningDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> runningDetails;

    public Help4DevsRunningDetails(List<String> runningDetails, Help4DevsHardSysCommands command) {
        this.command = command;
        this.runningDetails = runningDetails;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.runningDetails) {

            details = indexer(details, "Compilers: ", "compilers: N/A ", "", false);
            details = indexer(details, "Client: ", "client", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.runningDetails) {
            filter.add(details
                    .replaceAll("Compilers: ", "compilers: N/A ")
                    .replaceFirst("Client: ", "client_"+n+": ")
            );
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.runningDetails) {
            filter.add(details
                    .replaceAll("Compilers: ", "compilers: N/A ")
                    .replaceFirst("Client: ", "client_"+n+": ")
            );
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.runningDetails) {
            filter.add(details
                    .replaceAll("Compilers: ", "compilers: N/A ")
                    .replaceFirst("Client: ", "client_"+n+": ")
            );
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.runningDetails) {
            filter.add(details
                    .replaceAll("Compilers: ", "compilers: N/A ")
                    .replaceFirst("Client: ", "client_"+n+": ")
            );
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.runningDetails) {
            filter.add(details
                    .replaceAll("Compilers: ", "compilers: N/A ")
                    .replaceFirst("Client: ", "client_"+n+": ")
            );
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), running());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), running());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), running());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), running());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), running());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), running());
        }
        throw new RuntimeException("Invalid command for "+ running() +": " + this.command);
    }

}
