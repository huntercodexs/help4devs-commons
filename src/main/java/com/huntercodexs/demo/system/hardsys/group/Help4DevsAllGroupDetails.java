package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

public class Help4DevsAllGroupDetails extends Help4DevsHardSysBase {

    private final Help4DevsHardSysCommands command;
    private final Help4DevsHardSysResources allResources;

    public Help4DevsAllGroupDetails(Help4DevsHardSysResources allResources, Help4DevsHardSysCommands command) {
        this.command = command;
        this.allResources = allResources;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        return Arrays.asList(
            this.allResources.getProcessor().getDetails(),
            this.allResources.getKeyboard().getDetails(),
            this.allResources.getMouse().getDetails(),
            this.allResources.getMonitor().getDetails(),
            this.allResources.getGraphics().getDetails(),
            this.allResources.getAudio().getDetails(),
            this.allResources.getStorage().getDetails(),
            this.allResources.getNetwork().getDetails(),
            this.allResources.getNetworkInterface().getDetails(),
            this.allResources.getDisk().getDetails(),
            this.allResources.getPartition().getDetails(),
            this.allResources.getUsb().getDetails(),
            this.allResources.getBios().getDetails(),
            this.allResources.getBridge().getDetails(),
            this.allResources.getHub().getDetails(),
            this.allResources.getMemory().getDetails(),
            this.allResources.getBluetooth().getDetails(),
            this.allResources.getUnknown().getDetails());
    }

    private List<String> detailsFromLinuxCommandLshw() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandLscpu2() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandInxi(), allGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandHwinfo(), allGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLshw(), allGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLscpu(), allGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLscpu2(), allGroup());
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandDmidecode(), allGroup());
        }
        throw new RuntimeException("Invalid command for "+ allGroup() +": " + this.command);
    }
}

