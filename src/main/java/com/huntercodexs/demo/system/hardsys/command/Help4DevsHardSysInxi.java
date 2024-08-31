
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysInxi extends Help4DevsHardSysBase {

    public Help4DevsHardSysInxi(HashMap<String, List<String>> resources) {
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

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.INXI)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < inxiInfo.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(inxiInfo[i])) {

                    String lines;
                    array.add(currentLine.replace(inxiInfo[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
                        if (i == inxiInfo.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        if (lines.contains(inxiInfo[i+1])) {
                            currentLine = lines;
                            break;
                        }
                        array.add(lines.trim());
                    }

                    //Save line according HARDSYS
                    this.resources.put(layoutTranslator(inxiInfo[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
