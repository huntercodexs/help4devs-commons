package com.huntercodexs.demo.system.hardsys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsPartitionDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> partitionDetails;

    public Help4DevsPartitionDetails(List<String> partition, Help4DevsHardSysCommands command) {
        this.command = command;
        this.partitionDetails = partition;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.partitionDetails) {
            filter.add(details.replaceAll("fs: ", "type: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo(String partition) {
        List<String> listFilter = new ArrayList<>();
        int index = 0;
        for (String details : this.partitionDetails) {

            if (!details.contains("type: "+partition)) continue;

            details = details.replaceAll("type: "+partition+" ", "");

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
        for (String details : this.partitionDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        for (String details : this.partitionDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        for (String details : this.partitionDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        for (String details : this.partitionDetails) {
            filter.add(details.replaceAll("fs: ", "fileSystem: "));
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), HARDSYS[10]);
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {

            String partition = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo("source"), "source");
            String disk = jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo("disk"), "disk");
            return jsonMergerRFC8259(Arrays.asList(partition, disk), HARDSYS[10]);

        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), HARDSYS[10]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), HARDSYS[10]);
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), HARDSYS[10]);
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), HARDSYS[10]);
        }
        throw new RuntimeException("Invalid command for "+ HARDSYS[10]+": " + this.command);
    }

}
