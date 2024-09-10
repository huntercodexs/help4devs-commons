package com.huntercodexs.demo.system.hardsys.group;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonMergerRFC8259;

/**
 * @implNote This class only process for JSON format results
 * */
public class Help4DevsAllGroupDetails extends Help4DevsHardSysBase {

    private final String resourceName = "all";
    private final Help4DevsHardSysCommands command;
    private final Help4DevsHardSysResources allResources;

    public Help4DevsAllGroupDetails(Help4DevsHardSysResources allResources, Help4DevsHardSysCommands command) {
        this.command = command;
        this.allResources = allResources;
    }

    private List<String> detailsFromLinuxCommandInxi() {
        return Arrays.asList(
                this.allResources.getSystem().getDetails(),
                this.allResources.getMachine().getDetails(),
                this.allResources.getBattery().getDetails(),
                this.allResources.getMemory().getDetails(),
                this.allResources.getSlots().getDetails(),
                this.allResources.getProcessor().getDetails(),
                this.allResources.getGraphics().getDetails(),
                this.allResources.getAudio().getDetails(),
                this.allResources.getNetwork().getDetails(),
                this.allResources.getDrives().getDetails(),
                this.allResources.getPartition().getDetails(),
                this.allResources.getUsb().getDetails(),
                this.allResources.getSensors().getDetails(),
                this.allResources.getRunning().getDetails(),
                this.allResources.getPrinter().getDetails());
    }

    private List<String> detailsFromLinuxCommandHwinfo() {
        return Arrays.asList(
            this.allResources.getSystem().getDetails(),
            this.allResources.getProcessor().getDetails(),
            this.allResources.getKeyboard().getDetails(),
            this.allResources.getMouse().getDetails(),
            this.allResources.getMonitor().getDetails(),
            this.allResources.getGraphics().getDetails(),
            this.allResources.getAudio().getDetails(),
            this.allResources.getStorage().getDetails(),
            this.allResources.getNetwork().getDetails(),
            this.allResources.getNicInterface().getDetails(),
            this.allResources.getDisk().getDetails(),
            this.allResources.getPartition().getDetails(),
            this.allResources.getCdRom().getDetails(),
            this.allResources.getUsb().getDetails(),
            this.allResources.getBios().getDetails(),
            this.allResources.getBridge().getDetails(),
            this.allResources.getHub().getDetails(),
            this.allResources.getMemory().getDetails(),
            this.allResources.getBluetooth().getDetails(),
            this.allResources.getUnknown().getDetails(),
            this.allResources.getMultimedia().getDetails(),
            this.allResources.getPrinter().getDetails());
    }

    private List<String> detailsFromLinuxCommandLshw() {
        return Arrays.asList(
                this.allResources.getBridge().getDetails(),
                this.allResources.getController().getDetails(),
                this.allResources.getDisk().getDetails(),
                this.allResources.getGraphics().getDetails(),
                this.allResources.getUnknown().getDetails(),
                this.allResources.getMouse().getDetails(),
                this.allResources.getKeyboard().getDetails(),
                this.allResources.getMemory().getDetails(),
                this.allResources.getMultimedia().getDetails(),
                this.allResources.getNetwork().getDetails(),
                this.allResources.getProcessor().getDetails(),
                this.allResources.getStorage().getDetails(),
                this.allResources.getMachine().getDetails()
        );
    }

    private List<String> detailsFromLinuxCommandLscpu() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsPci() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandLsUsb() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    private List<String> detailsFromLinuxCommandDmidecode() {
        List<String> filter = new ArrayList<>();
        return filter;
    }

    public String getDetails() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandInxi(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandHwinfo(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLshw(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLscpu(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLsPci(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandLsUsb(), hardsysCheck(resourceName));
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            return jsonMergerRFC8259(detailsFromLinuxCommandDmidecode(), hardsysCheck(resourceName));
        }
        throw new RuntimeException("Invalid command for "+ hardsysCheck(resourceName) +": " + this.command);
    }
}

