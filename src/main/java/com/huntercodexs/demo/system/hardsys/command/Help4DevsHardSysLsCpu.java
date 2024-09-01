
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLsCpu extends Help4DevsHardSysBase {

    public Help4DevsHardSysLsCpu(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private String layoutTranslator(String input) {
        return input
                .replaceAll("PCI Slots:", "slots")
                .replaceAll("CPU: ", "processor")
                .replaceAll("Info: ", "running")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
    }

    private void makeSourceAndGroup() {

        /* ! Code Here ! */

    }

    private boolean forceBreak(int index, String line) {
        for (int k = index; k < lsCpuLayout.length; k++) {
            if (line.contains(lsCpuLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.LSCPU)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < lsCpuLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(lsCpuLayout[i])) {

                    String line;
                    array.add(currentLine.replace(lsCpuLayout[i], "").trim());

                    while ((line = reader.readLine()) != null) {

                        //Last line
                        if (i == lsCpuLayout.length-1) {
                            array.add(line.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (line.contains(lsCpuLayout[i+1])) {
                            currentLine = line;
                            break;
                        }

                        //Prevent bug - In the case below the current resource is present in the output result
                        if (forceBreak(i, line)) {
                            currentLine = line;
                            break;
                        }

                        array.add(line.trim());
                    }

                    //Save line according HARDSYS
                    this.resources.put(layoutTranslator(lsCpuLayout[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
