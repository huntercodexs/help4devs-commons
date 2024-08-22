package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsCacheDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> cacheDetails;

    public Help4DevsCacheDetails(List<String> cache, Help4DevsHardSysCommands command) {
        this.command = command;
        this.cacheDetails = cache;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String cache : this.cacheDetails) {
            filter.add(cache.replaceAll("CACHE: ", "cache: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[18]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), HARDSYS[18]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[18]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[18]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[18]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[18]);
        }
        throw new RuntimeException("Invalid command for "+ HARDSYS[18]+": " + this.command);
    }
}
