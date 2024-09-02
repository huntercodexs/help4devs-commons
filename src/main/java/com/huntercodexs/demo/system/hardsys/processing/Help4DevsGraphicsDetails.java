package com.huntercodexs.demo.system.hardsys.processing;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

public class Help4DevsGraphicsDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final List<String> graphicsDetails;

    public Help4DevsGraphicsDetails(List<String> graphics, Help4DevsHardSysCommands command) {
        this.command = command;
        this.graphicsDetails = graphics;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        for (String details : this.graphicsDetails) {

            details = indexer(details, "\\[", "(", "", false);
            details = indexer(details, "\\]", ")", "", false);
            details = indexer(details, "OpenGL: ", "openGL", ": ", false);
            details = indexer(details, "direct render: ", "directRender", ": ", false);
            details = indexer(details, "driver: ", "driver", ": ", true);
            details = indexer(details, "bus ID: ", "busId", ": ", true);
            details = indexer(details, "vendor: ", "vendor", ": ", true);
            details = indexer(details, "v: ", "version", ": ", true);
            filter.add(details);

        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        List<String> filter = new ArrayList<>();
        for (String details : this.graphicsDetails) {
            if (details == null || details.isEmpty()) continue;
            filter.add(detailsFilter(details, "source"));
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.graphicsDetails) {
            filter.add(details
                    .replaceAll("OpenGL: ", "openGL: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.graphicsDetails) {
            filter.add(details
                    .replaceAll("OpenGL: ", "openGL: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.graphicsDetails) {
            filter.add(details
                    .replaceAll("OpenGL: ", "openGL: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": "));
            n++;
        }
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        int n = 0;
        for (String details : this.graphicsDetails) {
            filter.add(details
                    .replaceAll("OpenGL: ", "openGL: ")
                    .replaceFirst(" v:", " version_"+n+":")
                    .replaceFirst("driver: ", "driver_"+n+": "));
            n++;
        }
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck("graphics"));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck("graphics"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck("graphics"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck("graphics"));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsysCheck("graphics"));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck("graphics"));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck("graphics") +": " + this.command);
    }

}
