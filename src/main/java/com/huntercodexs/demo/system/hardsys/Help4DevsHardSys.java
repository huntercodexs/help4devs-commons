package com.huntercodexs.demo.system.hardsys;

import com.huntercodexs.demo.system.hardsys.command.*;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysFactory;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysResources;
import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysSystem;

import java.util.ArrayList;
import java.util.HashMap;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.*;

public class Help4DevsHardSys extends Help4DevsHardSysBase {

    public Help4DevsHardSys(Help4DevsHardSysCommands command) {
        this.command = command;
        this.checkOs();
        this.loader();
    }

    private void checkOs() {
        String os = System.getProperty("os.name");
        String msg = "Invalid OS - This command should be used on";

        /*
         * The command will be configured late in the process flow
         * */
        if (this.command.equals(AUTO)) {
            return;
        }

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

        this.resources = new HashMap<>();/*JSON*/
        this.transport = new HashMap<>();/*DTO*/

        for (String res : fields()) {
            this.resources.put(res, new ArrayList<>());
        }

        /*Global information - for all commands*/
        new Help4DevsHardSysSystem(this.resources).run();

        /*Command information - specific command*/
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            new Help4DevsHardSysInxi(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            new Help4DevsHardSysHwinfo(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            new Help4DevsHardSysLshw(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            new Help4DevsHardSysLsCpu(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSPCI)) {
            new Help4DevsHardSysLsPci(this.resources).run();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSUSB)) {
            new Help4DevsHardSysLsUsb(this.resources).run();
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
            /*for DTO result*/
            new Help4DevsHardSysFactory(this.command, this.resources, this.transport).make();
            return new Help4DevsHardSysResources(this.transport, this.command);
        }
        /*for JSON result*/
        return new Help4DevsHardSysResources(this.command, this.resources);
    }

}
