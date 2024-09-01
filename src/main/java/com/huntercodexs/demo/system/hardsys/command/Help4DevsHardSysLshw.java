
package com.huntercodexs.demo.system.hardsys.command;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysLshw extends Help4DevsHardSysBase {

    public Help4DevsHardSysLshw(HashMap<String, List<String>> resources) {
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
        for (int k = index; k < lshwLayout.length; k++) {
            if (line.contains(lshwLayout[k])) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try (BufferedReader reader = execute(Help4DevsHardSysCommands.LSHW)) {

            String currentLine = reader.readLine();

            for (int i = 0; i < lshwLayout.length; i++) {

                List<String> array = new ArrayList<>();

                if (currentLine == null) continue;

                if (currentLine.contains(lshwLayout[i])) {

                    String line;
                    array.add(currentLine.replace(lshwLayout[i], "").trim());

                    while ((line = reader.readLine()) != null) {

                        //Last line
                        if (i == lshwLayout.length-1) {
                            array.add(line.trim());
                            continue;
                        }

                        //Next Layout Item
                        if (line.contains(lshwLayout[i+1])) {
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
                    this.resources.put(layoutTranslator(lshwLayout[i]), array);
                }
            }

            makeSourceAndGroup();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
