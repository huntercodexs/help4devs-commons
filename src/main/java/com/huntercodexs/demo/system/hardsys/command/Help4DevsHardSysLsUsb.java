
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLsUsb extends Help4DevsHardSysBase {

    public Help4DevsHardSysLsUsb(HashMap<String, List<String>> resources) {
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
        for (int k = index; k < lsUsbLayout.length; k++) {
            if (line.contains(lsUsbLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.LSUSB)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < lsUsbLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(lsUsbLayout[i])) {

                    String lines;
                    array.add(currentLine.replace(lsUsbLayout[i], "").trim());

                    while ((lines = reader.readLine()) != null) {

                        //Last line
                        if (i == lsUsbLayout.length-1) {
                            array.add(lines.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (lines.contains(lsUsbLayout[i+1])) {
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
                    this.resources.put(layoutTranslator(lsUsbLayout[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
