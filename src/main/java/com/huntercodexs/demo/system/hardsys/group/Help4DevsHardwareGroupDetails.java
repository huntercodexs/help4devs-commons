package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsHardwareGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> devicesDetails;

    public Help4DevsHardwareGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.devicesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
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
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", "HardwareGroup: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("hardwareGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String bios = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("bios")), hardsysCheck("bios"));
            String cache = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("cache")), hardsysCheck("cache"));
            return jsonMergerRFC8259(Arrays.asList(bios, cache), hardsysCheck("hardwareGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("hardwareGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("hardwareGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck("hardwareGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck("hardwareGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("hardwareGroup"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("hardwareGroup") +": " + this.command);
    }
}

