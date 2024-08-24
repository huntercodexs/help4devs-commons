package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsDrivesGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> drivesDetails;

    public Help4DevsDrivesGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.drivesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.drivesDetails) {
            if (!details.contains("type: "+device)) continue;
            listFilter.add(sourceFilter(details, device, index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), drivesGroup());

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String disk = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(disk()), disk());
            String partition = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(partition()), partition());
            String storage = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(storage()), storage());
            return jsonMergerRFC8259(Arrays.asList(disk, partition, storage), drivesGroup());

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), drivesGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), drivesGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), drivesGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), drivesGroup());
        }
        throw new RuntimeException("Invalid command for "+ drivesGroup() +": " + this.command);
    }
}

