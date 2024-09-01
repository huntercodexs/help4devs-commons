package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsKeyboardDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> keyboardDetails;

    public Help4DevsKeyboardDetails(List<String> devices, Help4DevsHardSysCommands command) {
        this.command = command;
        this.keyboardDetails = devices;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.keyboardDetails) {
            filter.add(details.replaceAll("Keyboard: ", "keyboard: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.keyboardDetails) {
            if (details.isEmpty() || !details.contains(hardsys("keyboard"))) continue;
            listFilter.add(sourceFilter(details, hardsys("keyboard"), index, "source", "input"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.keyboardDetails) {
            filter.add(details.replaceAll("Keyboard: ", "keyboard: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.keyboardDetails) {
            filter.add(details.replaceAll("Keyboard: ", "keyboard: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.keyboardDetails) {
            filter.add(details.replaceAll("Keyboard: ", "keyboard: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.keyboardDetails) {
            filter.add(details.replaceAll("Keyboard: ", "keyboard: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsys("keyboard"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsys("keyboard"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsys("keyboard"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsys("keyboard"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsys("keyboard"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsys("keyboard"));
        }
        throw new RuntimeException("Invalid command for "+ hardsys("keyboard") +": " + this.command);
    }
}

