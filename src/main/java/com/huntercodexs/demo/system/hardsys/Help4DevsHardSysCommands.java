package com.huntercodexs.demo.system.hardsys;

public enum Help4DevsHardSysCommands {

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
    LSCPU2("lshw -C cpu"),
    DMIDECODE("dmidecode"),
    SYSTEMINFO_WINDOWS("systeminfo");

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
