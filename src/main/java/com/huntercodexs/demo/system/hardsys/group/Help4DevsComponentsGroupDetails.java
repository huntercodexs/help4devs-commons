package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsComponentsGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> devicesDetails;

    public Help4DevsComponentsGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.devicesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
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
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", "ComponentsGroup: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("componentsGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String processor = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("processor")), hardsysCheck("processor"));
            String memory = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("memory")), hardsysCheck("memory"));
            String audio = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("audio")), hardsysCheck("audio"));
            String battery = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsysCheck("battery")), hardsysCheck("battery"));
            return jsonMergerRFC8259(Arrays.asList(processor, memory, audio, battery), hardsysCheck("componentsGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("componentsGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("componentsGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck("componentsGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck("componentsGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("componentsGroup"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("componentsGroup") +": " + this.command);
    }
}

