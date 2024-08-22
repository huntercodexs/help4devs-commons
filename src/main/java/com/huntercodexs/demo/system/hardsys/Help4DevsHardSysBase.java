package com.huntercodexs.demo.system.hardsys;

import com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService;

public abstract class Help4DevsHardSysBase {

    private final Help4DevsStringHandlerService stringHandler = new Help4DevsStringHandlerService();

    protected static boolean HARDSYS_DEBUG = false;

    //Available Information: Hardware x System
    protected static final String[] HARDSYS = new String[]{
            /*[ 0]*/ "system",
            /*[ 1]*/ "machine",
            /*[ 2]*/ "battery",
            /*[ 3]*/ "memory",
            /*[ 4]*/ "slots",
            /*[ 5]*/ "processor",
            /*[ 6]*/ "graphics",
            /*[ 7]*/ "audio",
            /*[ 8]*/ "network",
            /*[ 9]*/ "drivers",
            /*[10]*/ "partition",
            /*[11]*/ "usb",
            /*[12]*/ "sensors",
            /*[13]*/ "running",
            /*[14]*/ "monitor",
            /*[15]*/ "bios",
            /*[16]*/ "baseboard",
            /*[17]*/ "chassis",
            /*[18]*/ "cache",
            /*[19]*/ "connector",
            /*[20]*/ "drives",
            /*[21]*/ "devices"
    };

    //INXI Version 3.0.38 (Layout)
    protected static final String[] inxiInfo = new String[] {
            /*[ 0]*/ "System:    ",
            /*[ 1]*/ "Machine:   ",
            /*[ 2]*/ "Battery:   ",
            /*[ 3]*/ "Memory:    ",
            /*[ 4]*/ "PCI Slots: ",
            /*[ 5]*/ "CPU:       ",
            /*[ 6]*/ "Graphics:  ",
            /*[ 7]*/ "Audio:     ",
            /*[ 8]*/ "Network:   ",
            /*[ 9]*/ "Drives:    ",
            /*[10]*/ "Partition: ",
            /*[11]*/ "USB:       ",
            /*[12]*/ "Sensors:   ",
            /*[13]*/ "Info:      "
    };

    //HWINFO Version 21.68 (Layout)
    protected static final String[] hwinfoLayout = new String[] {
            /*[ 0]*/ "cpu:",
            /*[ 1]*/ "keyboard:",
            /*[ 2]*/ "mouse:",
            /*[ 3]*/ "monitor:",
            /*[ 4]*/ "graphics card:",
            /*[ 5]*/ "sound:",
            /*[ 6]*/ "storage:",
            /*[ 7]*/ "network:",
            /*[ 8]*/ "network interface:",
            /*[ 9]*/ "disk:",
            /*[10]*/ "partition:",
            /*[11]*/ "usb controller:",
            /*[12]*/ "bios:",
            /*[13]*/ "bridge:",
            /*[14]*/ "hub:",
            /*[15]*/ "memory:",
            /*[16]*/ "bluetooth",
            /*[17]*/ "unknown"
    };

    protected void indexerRestart() {
        this.stringHandler.setIndex(0);
    }

    protected void indexerUpdate(int index) {
        this.stringHandler.setIndex(index);
    }

    protected String indexer(String input, String target, String replacement, String separator, boolean indexer) {
        return this.stringHandler.replaceIndexing(input, target, replacement, separator, indexer);
    }

}
