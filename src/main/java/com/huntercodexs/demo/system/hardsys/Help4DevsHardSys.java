
package com.huntercodexs.demo.system.hardsys;

import com.huntercodexs.demo.system.hardsys.command.*;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysFactory;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysResources;

import java.util.ArrayList;
import java.util.HashMap;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.SYSTEMINFO;
import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.sysCmd;

public class Help4DevsHardSys extends Help4DevsHardSysBase {

    public Help4DevsHardSys(Help4DevsHardSysCommands command) {
        this.command = command;
        this.checkOs();
        this.loader();
    }

    private void checkOs() {
        String os = System.getProperty("os.name");
        String msg = "Invalid OS - This command should be used on";

        if (os.contains("Linux") || os.contains("Macintosh")) {

            if (this.command.equals(SYSTEMINFO)) {
                throw new RuntimeException(msg+" DOS/Windows");
            }

        } else if (os.contains("Windows")) {

            if (!this.command.equals(SYSTEMINFO)) {
                throw new RuntimeException(msg+" UNIX/Linux");
            }

        } else {
            System.out.println("WARNING: OS not recognized !");
        }
    }

    private void loader() {

        this.resources = new HashMap<>();

        for (String res : hardsys()) {
            this.resources.put(res, new ArrayList<>());
        }

        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            new Help4DevsHardSysInxi(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            new Help4DevsHardSysHwinfo(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            new Help4DevsHardSysLsHw(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            new Help4DevsHardSysLsCpu(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            new Help4DevsHardSysLsCpu2(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            new Help4DevsHardSysDmidecode(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            new Help4DevsHardSysSystemInfo(this.resources).run();
        } else {
            throw new RuntimeException("Wrong sys cmd to retrieve information: " + sysCmd(this.command));
        }
    }

    public void json() {
        this.setJsonOn();
    }

    public Help4DevsHardSysResources resources() {
        if (!this.getJsonOn()) {
            new Help4DevsHardSysFactory(this.resources, this.command).make();
        }
        return new Help4DevsHardSysResources(this.resources, this.command, this.getJsonOn());
    }

}
