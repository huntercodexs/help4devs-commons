package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.processing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsHardwareGroupDetails extends Help4DevsHardSysBase {

    private final List<String> hardwareDetails;
    private final Help4DevsHardSysCommands command;
    private final String resourceName = "hardwareGroup";

    public Help4DevsHardwareGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.hardwareDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device, boolean replace) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
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
        for (String details : this.hardwareDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", resourceName));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", resourceName));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", resourceName));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            filter.add(details.replaceAll("HARDWARE GROUP: ", resourceName));
        }
        return filter;
    }

    private List<String> detailsFromWindowsCommandSystemInfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.hardwareDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
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

        String bios = new Help4DevsBiosDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("bios")),
                this.command).getDetails();

        String cache = new Help4DevsCacheDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "cache")),
                this.command).getDetails();

        String disk = new Help4DevsDiskDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "disk")),
                this.command).getDetails();

        String drives = new Help4DevsDrivesDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "drives")),
                this.command).getDetails();

        String partition = new Help4DevsPartitionDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "partition")),
                this.command).getDetails();

        String cdrom = new Help4DevsCdRomDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "cdrom")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(
                processor, memory, bios, cache, disk, drives, partition, cdrom), hardsysCheck(resourceName));

    }

    private String groupsByHwinfoCommand() {

        String bios = new Help4DevsBiosDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("bios"), false),
                this.command).getDetails();

        String cache = new Help4DevsCacheDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("cache"), false),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(bios, cache), hardsysCheck(resourceName));

    }

    private String groupsByLshwCommand() {

        String processor = new Help4DevsProcessorDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("processor")),
                this.command).getDetails();

        String memory = new Help4DevsMemoryDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("memory")),
                this.command).getDetails();

        String disk = new Help4DevsDiskDetails(
                detailsFromLinuxCommandLshw(hardsysCheck( "disk")),
                this.command).getDetails();

        String controller = new Help4DevsDrivesDetails(
                detailsFromLinuxCommandLshw(hardsysCheck( "controller")),
                this.command).getDetails();

        String partition = new Help4DevsPartitionDetails(
                detailsFromLinuxCommandLshw(hardsysCheck( "partition")),
                this.command).getDetails();

        String unknown = new Help4DevsPartitionDetails(
                detailsFromLinuxCommandLshw(hardsysCheck( "unknown")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(
                processor, memory, disk, controller, partition, unknown), hardsysCheck(resourceName));

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

