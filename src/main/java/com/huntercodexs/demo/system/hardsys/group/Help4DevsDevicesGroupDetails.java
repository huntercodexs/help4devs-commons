package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsDevicesGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> devicesDetails;

    public Help4DevsDevicesGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.devicesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.devicesDetails) {
            if (!details.contains("type: "+device)) continue;
            listFilter.add(sourceFilter(details, device, index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.devicesDetails) {
            if (!details.contains("type: "+device)) continue;
            listFilter.add(sourceFilter(details, device, index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Devices Group: ", "devicesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Devices Group: ", "devicesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Devices Group: ", "devicesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Devices Group: ", "devicesGroup: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("Devices Group: ", "devicesGroup: "));
        }
        return filter;
    }

    public String getDetails() {

        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {

            String battery = jsonCreatorRFC8259(detailsFromLinuxCommandInxi(hardsys("battery")), hardsys("battery"));
            String sensors = jsonCreatorRFC8259(detailsFromLinuxCommandInxi(hardsys("sensors")), hardsys("sensors"));
            String keyboard = jsonCreatorRFC8259(detailsFromLinuxCommandInxi(hardsys("keyboard")), hardsys("keyboard"));
            String mouse = jsonCreatorRFC8259(detailsFromLinuxCommandInxi(hardsys("mouse")), hardsys("mouse"));
            return jsonMergerRFC8259(Arrays.asList(battery, sensors, keyboard, mouse), hardsys("devicesGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String keyboard = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsys("keyboard")), hardsys("keyboard"));
            String mouse = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsys("mouse")), hardsys("mouse"));
            String monitor = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsys("monitor")), hardsys("monitor"));
            String hub = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(hardsys("hub")), hardsys("hub"));
            return jsonMergerRFC8259(Arrays.asList(keyboard, mouse, monitor, hub), hardsys("devicesGroup"));

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsys("devicesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsys("devicesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsys("devicesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsys("devicesGroup"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsys("devicesGroup"));
        }
        throw new RuntimeException("Invalid command for "+ hardsys("devicesGroup") +": " + this.command);
    }
}

