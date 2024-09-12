package com.huntercodexs.demo.services.system.hardsys.core;

public abstract class Help4DevsHardSysLayout {

    /**
     * <p>INXI Version 3.0.38 (Layout)</p>
     */
    protected static final String[] inxiLayout = new String[] {
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
            /*[11]*/ "CD Rom:    ",
            /*[12]*/ "USB:       ",
            /*[13]*/ "Sensors:   ",
            /*[14]*/ "Info:      "
    };

    /**
     * <p>HWINFO Version 21.68 (Layout)</p>
     */
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
            /*[11]*/ "cdrom:",
            /*[12]*/ "usb controller:",
            /*[13]*/ "bios:",
            /*[14]*/ "bridge:",
            /*[15]*/ "hub:",
            /*[16]*/ "memory:",
            /*[17]*/ "bluetooth:",
            /*[18]*/ "unknown:"
    };

    /**
     * <p>LSHW (Layout)</p>
     *
     * <p>
     *     The Lshw command layout below doesn't respect the order from output command,
     *     it means that the output result when execute this command can be very different
     *     that one, so this layout just is used to show witch fields or resources are
     *     expected from that current linux command
     * </p>
     *
     * <p>
     *     NOTE: DO NOT REMOVE THE SPACES ON THE LINES BELOW, IT IS BELONG TO THE
     *     LSHW LINUX COMMAND LAYOUT
     * </p>
     */
    protected static final String[] lshwLayout = new String[] {
            /*[ 1]*/ "bridge         ",
            /*[ 2]*/ "bus            ",
            /*[ 3]*/ "communication  ",
            /*[ 4]*/ "disk           ",
            /*[ 5]*/ "display        ",
            /*[ 6]*/ "generic        ",
            /*[ 7]*/ "input          ",
            /*[ 8]*/ "memory         ",
            /*[ 9]*/ "multimedia     ",
            /*[10]*/ "network        ",
            /*[11]*/ "processor      ",
            /*[12]*/ "storage        ",
            /*[13]*/ "system         ",
            /*[14]*/ "volume         "
    };

    /**
     * <p>LSCPU Version 2.34 (Layout)</p>
     */
    protected static final String[] lsCpuLayout = new String[] {
            "Device-1: ",
            "Device-2"
    };

    /**
     * <p>LSPCI Version 3.6.4 (Layout)</p>
     */
    protected static final String[] lsPciLayout = new String[] {
            "Device-1: ",
            "Device-2"
    };

    /**
     * <p>LSUSB Version 3.6.4 (Layout)</p>
     */
    protected static final String[] lsUsbLayout = new String[] {
            "Device-1: ",
            "Device-2"
    };

    /**
     * <p>DMIDECODE Version 3.2 (Layout)</p>
     */
    protected static final String[] dmidecodeLayout = new String[] {
            "Device-1: ",
            "Device-2"
    };

    /**
     * <p>SYSTEMINFO Windows (Layout)</p>
     */
    protected static final String[] systemInfoLayout = new String[] {
            "Device-1: ",
            "Device-2"
    };

}
