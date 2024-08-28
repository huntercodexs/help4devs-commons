package com.huntercodexs.demo.system.hardsys.core;

import com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService;
import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.sysCmd;

public abstract class Help4DevsHardSysBase {

    private boolean jsonOn = false;

    //Available Information: Hardware x System
    private static final String[] HARDSYS = new String[]{
            /*[ 0]*/ "system",
            /*[ 1]*/ "machine",
            /*[ 2]*/ "battery",
            /*[ 3]*/ "memory",
            /*[ 4]*/ "slots",
            /*[ 5]*/ "processor",
            /*[ 6]*/ "graphics",
            /*[ 7]*/ "audio",
            /*[ 8]*/ "network",
            /*[ 9]*/ "drivers",
            /*[10]*/ "partition",
            /*[11]*/ "usb",
            /*[12]*/ "sensors",
            /*[13]*/ "running",
            /*[14]*/ "monitor",
            /*[15]*/ "bios",
            /*[16]*/ "baseboard",
            /*[17]*/ "chassis",
            /*[18]*/ "cache",
            /*[19]*/ "connector",
            /*[20]*/ "keyboard",
            /*[21]*/ "mouse",
            /*[22]*/ "hub",
            /*[23]*/ "switch",
            /*[24]*/ "modem",
            /*[25]*/ "disk",
            /*[26]*/ "bluetooth",
            /*[27]*/ "video",
            /*[28]*/ "storage",
            /*[29]*/ "bridge",
            /*[30]*/ "net_interface",
            /*[31]*/ "unknown",
            /*[32]*/ "devices_group",
            /*[33]*/ "network_group",
            /*[34]*/ "drives_group",
            /*[35]*/ "components_group",
            /*[36]*/ "boards_group",
            /*[37]*/ "hardware_group",
            /*[38]*/ "all"
    };

    private final Help4DevsStringHandlerService stringHandler = new Help4DevsStringHandlerService();

    protected Help4DevsHardSysCommands command;
    protected HashMap<String, Object> transport;
    protected HashMap<String, List<String>> resources;

    //INXI Version 3.0.38 (Layout)
    protected static final String[] inxiInfo = new String[] {
            /*[ 0]*/ "System:    ",
            /*[ 1]*/ "Machine:   ",
            /*[ 2]*/ "Battery:   ",
            /*[ 3]*/ "Memory:    ",
            /*[ 4]*/ "PCI Slots: ",
            /*[ 5]*/ "CPU:       ",
            /*[ 6]*/ "Graphics:  ",
            /*[ 7]*/ "Audio:     ",
            /*[ 8]*/ "Network:   ",
            /*[ 9]*/ "Drives:    ",
            /*[10]*/ "Partition: ",
            /*[11]*/ "USB:       ",
            /*[12]*/ "Sensors:   ",
            /*[13]*/ "Info:      "
    };

    //HWINFO Version 21.68 (Layout)
    protected static final String[] hwinfoLayout = new String[] {
            /*[ 0]*/ "cpu:",
            /*[ 1]*/ "keyboard:",
            /*[ 2]*/ "mouse:",
            /*[ 3]*/ "monitor:",
            /*[ 4]*/ "graphics card:",
            /*[ 5]*/ "sound:",
            /*[ 6]*/ "storage:",
            /*[ 7]*/ "network:",
            /*[ 8]*/ "network interface:",
            /*[ 9]*/ "disk:",
            /*[10]*/ "partition:",
            /*[11]*/ "usb controller:",
            /*[12]*/ "bios:",
            /*[13]*/ "bridge:",
            /*[14]*/ "hub:",
            /*[15]*/ "memory:",
            /*[16]*/ "bluetooth:",
            /*[17]*/ "unknown:"
    };

    protected String[] hardsys() {
        return HARDSYS;
    }

    protected String system() {
        return HARDSYS[0];
    }

    protected String machine() {
        return HARDSYS[1];
    }

    protected String battery() {
        return HARDSYS[2];
    }

    protected String memory() {
        return HARDSYS[3];
    }

    protected String slots() {
        return HARDSYS[4];
    }

    protected String processor() {
        return HARDSYS[5];
    }

    protected String graphics() {
        return HARDSYS[6];
    }

    protected String audio() {
        return HARDSYS[7];
    }

    protected String network() {
        return HARDSYS[8];
    }

    protected String drivers() {
        return HARDSYS[9];
    }

    protected String partition() {
        return HARDSYS[10];
    }

    protected String usb() {
        return HARDSYS[11];
    }

    protected String sensors() {
        return HARDSYS[12];
    }

    protected String running() {
        return HARDSYS[13];
    }

    protected String monitor() {
        return HARDSYS[14];
    }

    protected String bios() {
        return HARDSYS[15];
    }

    protected String baseboard() {
        return HARDSYS[16];
    }

    protected String chassis() {
        return HARDSYS[17];
    }

    protected String cache() {
        return HARDSYS[18];
    }

    protected String connector() {
        return HARDSYS[19];
    }

    protected String keyboard() {
        return HARDSYS[20];
    }

    protected String mouse() {
        return HARDSYS[21];
    }

    protected String hub() {
        return HARDSYS[22];
    }

    protected String switcher() {
        return HARDSYS[23];
    }

    protected String modem() {
        return HARDSYS[24];
    }

    protected String disk() {
        return HARDSYS[25];
    }

    protected String bluetooth() {
        return HARDSYS[26];
    }

    protected String video() {
        return HARDSYS[27];
    }

    protected String storage() {
        return HARDSYS[28];
    }

    protected String bridge() {
        return HARDSYS[29];
    }

    protected String networkInterface() {
        return HARDSYS[30];
    }

    protected String unknown() {
        return HARDSYS[31];
    }

    protected String devicesGroup() {
        return HARDSYS[32];
    }

    protected String networksGroup() {
        return HARDSYS[33];
    }

    protected String drivesGroup() {
        return HARDSYS[34];
    }

    protected String componentsGroup() {
        return HARDSYS[35];
    }

    protected String boardsGroup() {
        return HARDSYS[36];
    }

    protected String hardwareGroup() {
        return HARDSYS[37];
    }

    protected String allGroup() {
        return HARDSYS[38];
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
                .replaceAll(detail+":", "");
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
