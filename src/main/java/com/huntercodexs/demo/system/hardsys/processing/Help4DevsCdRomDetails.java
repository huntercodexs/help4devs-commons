package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsCdRomDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> cdromDetails;

    public Help4DevsCdRomDetails(List<String> cdrom, Help4DevsHardSysCommands command) {
        this.command = command;
        this.cdromDetails = cdrom;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.cdromDetails) {
            filter.add(details.replaceAll("fs: ", "type: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.cdromDetails) {
            if (details.isEmpty() || !details.contains(hardsys("cdrom"))) continue;
            listFilter.add(sourceFilter(details, hardsys("cdrom"), index, "source", "source"));
            index++;
        }
        return listFilter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String details : this.cdromDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.cdromDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.cdromDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.cdromDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsys("cdrom"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsys("cdrom"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsys("cdrom"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsys("cdrom"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsys("cdrom"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsys("cdrom"));
        }
        throw new RuntimeException("Invalid command for "+ hardsys("cdrom") +": " + this.command);
    }

}
