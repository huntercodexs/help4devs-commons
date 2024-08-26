package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysFactory(HashMap<String, List<String>> resources, Help4DevsHardSysCommands command) {
        this.command = command;
        this.resources = resources;
    }

    private String stringExtractor(String input, String detail, String pattern, String replacer, int index) {

        String begin = input.replaceAll(pattern, "#<"+index+"#"+replacer+"#"+index+">#");
        String extract = begin.replaceAll(", ", " ");

        return StringUtils
                .substringBetween(extract, "#<"+index+"#", "#"+index+">#")
                .replaceAll(detail+":", "");
    }

    private String stringList(List<String> items, String clear) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            result.append(current.replaceAll(clear, "").replaceAll(",", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }

    private String listExtractor(List<String> items, String detail, String clear, String pattern, String replacer) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            String item = current
                    .replaceAll(clear, "")
                    .replaceAll(pattern, "#<"+i+"#"+replacer+"#"+i+">#");

            result.append(
                    StringUtils.substringBetween(item, "#<"+i+"#", "#"+i+">#")
                            .replaceAll(detail+":", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }

    private void makeStartHwinfo() {
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {

            List<String> makeList = new ArrayList<>();

            if (key.contains("_group") || key.equals("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;

                    String value = "type: " +key+ " source: "+item
                            .replaceAll("\\[", "(")
                            .replaceAll("]", ")")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(value);
                    this.resources.put(key, makeList);

                }
            }
        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }

        makeProcessorHwinfo(this.resources.get(processor()));
    }

    private void makeProcessorHwinfo(List<String> processorItems) {

        int cores = processorItems.size();
        String name = processorItems.get(0).replaceAll("type: processor source: ", "");

        String[] details = new String[]{"model", "family", "speed", "current"};

        String[] pattern = new String[]{
                "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)",
                "(Intel|AMD|NVIDIA)",
                "([0-9]+\\.[0-9]+[MG]Hz)",
                "([0-9]+) (MHz)"};

        String[] replacer = new String[]{"model:$1$2", "family:$1", "speed:$1", "current:$1 $2"};

        List<String> makeList = new ArrayList<>();

        makeList.add(String.valueOf(cores));
        makeList.add(name.replaceAll(",", ""));

        for (int  i = 0; i < details.length; i++) {
            makeList.add(stringExtractor(name, details[i], pattern[i], replacer[i], i));
        }

        makeList.add(stringList(processorItems, "type: processor source: "));

        makeList.add(listExtractor(
                processorItems,
                "speedCore",
                "type: processor source: ",
                "([0-9]+) (MHz)",
                "speedCore:$1 $2"));

        this.resources.put(processor(), makeList);

    }

    public void make() {
        if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
            //code here
        } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
            makeStartHwinfo();
        } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
            //code here
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
            //code here
        } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
            //code here
        } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
            //code here
        } else if (this.command.equals(Help4DevsHardSysCommands.SYSTEMINFO)) {
            //code here
        } else {
            throw new RuntimeException("Invalid command to run make resource method: " + this.command);
        }
    }

}
