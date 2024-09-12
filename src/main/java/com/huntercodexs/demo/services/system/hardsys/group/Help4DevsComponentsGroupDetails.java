package com.huntercodexs.demo.services.system.hardsys.group;

import com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.services.system.hardsys.processing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsComponentsGroupDetails extends Help4DevsHardSysBase {

    private final String resourceName = "componentsGroup";
    private final List<String> devicesDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsComponentsGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.devicesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device, boolean replace) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            if (!details.contains("type: "+device)) continue;
            if (replace) {
                details = details.replaceAll("type: " + device + " ?", "");
                details = details.replaceAll("source: ?", "");
            }
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromWindowsCommandSystemInfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.devicesDetails) {
            filter.add(details.replaceAll("COMPONENTS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private String groupsByInxiCommand() {

        String processor = new Help4DevsProcessorDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("processor")),
                this.command).getDetails();

        String memory = new Help4DevsMemoryDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("memory")),
                this.command).getDetails();

        String audio = new Help4DevsAudioDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("audio")),
                this.command).getDetails();

        String battery = new Help4DevsBatteryDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("battery")),
                this.command).getDetails();

        String sensors = new Help4DevsSensorsDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("sensors")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(processor, memory, audio, battery, sensors), hardsysCheck(resourceName));
    }

    private String groupsByHwinfoCommand() {

        String processor = new Help4DevsProcessorDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("processor"), true),
                this.command).getDetails();

        String memory = new Help4DevsMemoryDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("memory"), true),
                this.command).getDetails();

        String audio = new Help4DevsAudioDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("audio"), true),
                this.command).getDetails();

        String battery = new Help4DevsBatteryDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("battery"), true),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(processor, memory, audio, battery), hardsysCheck(resourceName));

    }

    private String groupsByLshwCommand() {

        String processor = new Help4DevsProcessorDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("processor")),
                this.command).getDetails();

        String memory = new Help4DevsMemoryDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("memory")),
                this.command).getDetails();

        String audio = new Help4DevsAudioDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("multimedia")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(processor, memory, audio), hardsysCheck(resourceName));

    }

    private String groupsByLsCpuCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck(resourceName));
    }

    private String groupsByLsPciCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck(resourceName));
    }

    private String groupsByLsUsbCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck(resourceName));
    }

    private String groupsByDmidecodeCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck(resourceName));
    }

    private String groupsBySystemInfoCommand() {
        return jsonCreatorRFC8259(detailsFromWindowsCommandSystemInfo(), hardsysCheck(resourceName));
    }

    public String getDetails() {

        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return groupsByInxiCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return groupsByHwinfoCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return groupsByLshwCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return groupsByLsCpuCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return groupsByLsPciCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return groupsByLsUsbCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return groupsByDmidecodeCommand();

        } else if (this.command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            return groupsBySystemInfoCommand();

        }

        throw new RuntimeException("Invalid command for "+ hardsysCheck(resourceName) +": " + this.command);
    }
}

