package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

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

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", "DrivesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
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
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("drivesGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String disk = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("disk")), hardsysCheck("disk"));
            String partition = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("partition")), hardsysCheck("partition"));
            String storage = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("storage")), hardsysCheck("storage"));
            String cdrom = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("cdrom")), hardsysCheck("cdrom"));
            return jsonMergerRFC8259(Arrays.asList(disk, partition, storage, cdrom), hardsysCheck("drivesGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("drivesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("drivesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck("drivesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck("drivesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("drivesGroup"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("drivesGroup") +": " + this.command);
    }
}

