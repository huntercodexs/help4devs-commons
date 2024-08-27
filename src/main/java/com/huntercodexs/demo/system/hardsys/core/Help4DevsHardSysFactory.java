package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.core.factory.*;

import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysFactory(HashMap<String, List<String>> resources, Help4DevsHardSysCommands command) {
        this.command = command;
        this.resources = resources;
    }

    public void make() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            new Help4DevsHardSysInxiFactory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            new Help4DevsHardSysHwinfoFactory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            new Help4DevsHardSysLshwFactory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            new Help4DevsHardSysLsCpuFactory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            new Help4DevsHardSysLsCpu2Factory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            new Help4DevsHardSysDmidecodeFactory(this.resources).factory();
        } else if (this.command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            new Help4DevsHardSysSystemInfoFactory(this.resources).factory();
        } else {
            throw new RuntimeException("Invalid command to run make resource method: " + this.command);
        }
    }

}
