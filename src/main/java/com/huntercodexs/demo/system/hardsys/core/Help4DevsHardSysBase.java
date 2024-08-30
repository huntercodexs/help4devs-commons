package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.sysCmd;

public abstract class Help4DevsHardSysBase extends Help4DevsHardSysLayout {

    private boolean jsonOn = false;
    private final Help4DevsStringHandlerService stringHandler = new Help4DevsStringHandlerService();

    protected Help4DevsHardSysCommands command;
    protected HashMap<String, Object> transport;
    protected HashMap<String, List<String>> resources;

    protected String[] fields() {
        Field[] fields = Help4DevsHardSysResourcesDto.class.getDeclaredFields();
        int len = fields.length;
        String[] names = new String[len];

        for (int i = 0; i < len; i++) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    protected String hardsys(String target) {
        Field[] fields = Help4DevsHardSysResourcesDto.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals(target)) {
                return target;
            }
        }
        throw new RuntimeException("ERROR: HARDSYS resource not found: " + target);
    }

    protected BufferedReader execute(Help4DevsHardSysCommands command) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(sysCmd(command));
            return  new BufferedReader(new InputStreamReader(process.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setJsonOn() {
        this.jsonOn = true;
    }

    protected boolean getJsonOn() {
        return this.jsonOn;
    }

    protected void indexerRestart() {
        this.stringHandler.setIndex(0);
    }

    protected void indexerUpdate(int index) {
        this.stringHandler.setIndex(index);
    }

    protected String indexer(String input, String target, String replacement, String separator, boolean indexer) {
        return this.stringHandler.replaceIndexing(input, target, replacement, separator, indexer);
    }

    protected String detailsFilter(String input, String field) {
        input = indexer(input, "(\\w+)", field+": $1", "", false);
        input = indexer(input, field+": ", field, ": ", true);
        return input;
    }

    protected String sourceFilter(String input, String type, int index, String field, String replacer) {
        input = input.replaceAll("type: "+type+" ", "");

        indexerUpdate(index);
        input = indexer(input, field+": ", replacer, ": ", true);

        indexerUpdate(index);
        input = indexer(input, "description: ", "description", ": ", true);

        input = input.replaceAll("\\.@\\.", ":");

        return input;
    }

    protected String stringExtractor(String input, String detail, String pattern, String replacer, int index) {

        String begin = input.replaceAll(pattern, "#<"+index+"#"+replacer+"#"+index+">#");
        String extract = begin.replaceAll(", ", " ");

        return StringUtils
                .substringBetween(extract, "#<"+index+"#", "#"+index+">#")
                .replaceAll(detail+":", "").trim();
    }

    protected String stringList(List<String> items, String clear) {

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

    protected static List<String> listClear(List<String> items, String replace, String replacement) {
        List<String> result = new ArrayList<>();
        for (String current : items) {
            result.add(current.replaceAll(replace, replacement));
        }
        return result;
    }

    protected String listExtractor(List<String> items, String detail, String clear, String pattern, String replacer) {

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

}
