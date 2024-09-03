package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.processing.Help4DevsCdRomDetails;
import com.huntercodexs.demo.system.hardsys.processing.Help4DevsDiskDetails;
import com.huntercodexs.demo.system.hardsys.processing.Help4DevsPartitionDetails;
import com.huntercodexs.demo.system.hardsys.processing.Help4DevsStorageDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsDrivesGroupDetails extends Help4DevsHardSysBase {

    private final String resourceName = "drivesGroup";
    private final List<String> drivesDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsDrivesGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.drivesDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device, boolean replace) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            if (!details.contains("type: "+device)) continue;
            if (replace) {
                details = details.replaceAll("type: " + device + " ?", "");
                details = details.replaceAll("source: ?", "");
            }
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromWindowsCommandSystemInfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.drivesDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private String groupsByInxiCommand() {

        String disk = new Help4DevsDiskDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("disk")),
                this.command).getDetails();

        String partition = new Help4DevsPartitionDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("partition")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(disk, partition), hardsysCheck(resourceName));

    }

    private String groupsByHwinfoCommand() {

        String disk = new Help4DevsDiskDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("disk"), false),
                this.command).getDetails();

        String partition = new Help4DevsPartitionDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("partition"), false),
                this.command).getDetails();

        String storage = new Help4DevsStorageDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("storage"), true),
                this.command).getDetails();

        String cdrom = new Help4DevsCdRomDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("cdrom"), true),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(disk, partition, storage, cdrom), hardsysCheck(resourceName));

    }

    private String groupsByLshwCommand() {
        return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck(resourceName));
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

