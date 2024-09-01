package com.huntercodexs.demo.system.hardsys.command;

public enum Help4DevsHardSysCommands {

    AUTO("auto"),
    INXI("inxi -Fxz " +
            "--slots " +
            "--memory " +
            "--cpu " +
            "--disk-full " +
            "--graphics " +
            "--machine " +
            "--network " +
            "--sensors " +
            "--system " +
            "--usb " +
            "--info " +
            "--color"),
    HWINFO("hwinfo --short"),
    LSHW("lshw -short"),
    LSCPU("lscpu"),
    LSPCI("lspci"), //check the command: lspci -k
    LSUSB("lsusb"),
    DMIDECODE("dmidecode"),
    //For windows
    SYSTEMINFO("systeminfo");

    private final String cmd;

    Help4DevsHardSysCommands(String cmd) {
        this.cmd = cmd;
    }

    private String getCmd() {
        return cmd;
    }

    public static String sysCmd(Help4DevsHardSysCommands cmd) {
        return cmd.getCmd();
    }

}
