package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.processing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsBoardsGroupDetails extends Help4DevsHardSysBase {

    private final String resourceName = "boardsGroup";
    private final List<String> boardDetails;
    private final Help4DevsHardSysCommands command;

    public Help4DevsBoardsGroupDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.boardDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi(String device) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.boardDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String device, boolean replace) {
        List<String> listFilter = new ArrayList<>();
        for (String details : this.boardDetails) {
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
        for (String details : this.boardDetails) {
            if (!details.contains("type: "+device)) continue;
            details = details.replaceAll("type: " + device +" ?", "");
            listFilter.add(details);
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.boardDetails) {
            filter.add(details.replaceAll("BOARDS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        for (String details : this.boardDetails) {
            filter.add(details.replaceAll("BOARDS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        for (String details : this.boardDetails) {
            filter.add(details.replaceAll("BOARDS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.boardDetails) {
            filter.add(details.replaceAll("BOARDS GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private List<String> detailsFromWindowsCommandSystemInfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.boardDetails) {
            filter.add(details.replaceAll("DRIVES GROUP: ", resourceName+": "));
        }
        return filter;
    }

    private String groupsByInxiCommand() {

        String baseboard = new Help4DevsSensorsDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("baseboard")),
                this.command).getDetails();

        String audio = new Help4DevsAudioDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("audio")),
                this.command).getDetails();

        String graphics = new Help4DevsGraphicsDetails(
                detailsFromLinuxCommandInxi(hardsysCheck("graphics")),
                this.command).getDetails();

        String slots = new Help4DevsSlotsDetails(
                detailsFromLinuxCommandInxi(hardsysCheck( "slots")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(baseboard, audio, graphics, slots), hardsysCheck(resourceName));

    }

    private String groupsByHwinfoCommand() {

        String baseboard = new Help4DevsBaseboardDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("baseboard"), false),
                this.command).getDetails();

        String audio = new Help4DevsAudioDetails(
                detailsFromLinuxCommandHwinfo(hardsysCheck("audio"), true),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(baseboard, audio), hardsysCheck(resourceName));

    }

    private String groupsByLshwCommand() {

        String graphics = new Help4DevsGraphicsDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("graphics")),
                this.command).getDetails();

        String multimedia = new Help4DevsGraphicsDetails(
                detailsFromLinuxCommandLshw(hardsysCheck("multimedia")),
                this.command).getDetails();

        return jsonMergerRFC8259(Arrays.asList(graphics, multimedia), hardsysCheck(resourceName));

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

