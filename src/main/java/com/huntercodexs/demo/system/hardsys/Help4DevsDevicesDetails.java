package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsDevicesDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> devicesDetails;

    public Help4DevsDevicesDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.devicesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.devicesDetails) {

            if (!details.contains("type: "+device)) continue;

            details = details.replaceAll("type: "+device+" ", "");

            indexerUpdate(index);
            details = indexer(details, "source: ", "source", ": ", true);

            indexerUpdate(index);
            details = indexer(details, "description: ", "description", ": ", true);

            details = details.replaceAll("\\.@\\.", ":");
            listFilter.add(details);

            index++;

        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Local Storage: ", "localStorage: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), devices());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String keyboard = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo("keyboard"), "keyboard");
            String mouse = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo("mouse"), "mouse");
            return jsonMergerRFC8259(Arrays.asList(keyboard, mouse), devices());

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), devices());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), devices());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), devices());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), devices());
        }
        throw new RuntimeException("Invalid command for "+ devices() +": " + this.command);
    }
}

