
package com.huntercodexs.demo.services.system.hardsys.command;

import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysSystemInfo extends Help4DevsHardSysBase {

    public Help4DevsHardSysSystemInfo(HashMap<String, List<String>> resources) {
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
        for (int k = index; k < systemInfoLayout.length; k++) {
            if (line.contains(systemInfoLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.SYSTEMINFO)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < systemInfoLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(systemInfoLayout[i])) {

                    String lines;
                    array.add(currentLine.replace(systemInfoLayout[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
                        if (i == systemInfoLayout.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (lines.contains(systemInfoLayout[i+1])) {
                            currentLine = lines;
                            break;
                        }

                        //Prevent bug - In the case below the current resource is present in the output result
                        if (forceBreak(i, lines)) {
                            currentLine = lines;
                            break;
                        }

                        array.add(lines.trim());
                    }

                    //Save line according HARDSYS
                    this.resources.put(layoutTranslator(systemInfoLayout[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
