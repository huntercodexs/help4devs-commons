
package com.huntercodexs.demo.services.system.hardsys.command;

import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLsPci extends Help4DevsHardSysBase {

    public Help4DevsHardSysLsPci(HashMap<String, List<String>> resources) {
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
        for (int k = index; k < lsPciLayout.length; k++) {
            if (line.contains(lsPciLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.LSPCI)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < lsPciLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(lsPciLayout[i])) {

                    String lines;
                    array.add(currentLine.replace(lsPciLayout[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
                        if (i == lsPciLayout.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (lines.contains(lsPciLayout[i+1])) {
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
                    this.resources.put(layoutTranslator(lsPciLayout[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
