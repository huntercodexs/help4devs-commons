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

    protected String vendorsPattern =
            "(GAMDIAS|SMS|SEAGATE|SANDISK|SEGURIMAX|SECCON|MAX ELETRON|LACIE|NEPTON|SAMSUNG|PLUSCABLE|" +
            "BIOSTAR|VIEWSONIC|JFL|TENDA|ARROW|GALAX|MSI|GAINWARD|VONDER|NOVE54|ZOTAC|HIKSEMI|HIKSEMI|" +
            "WAVEONE|NORTH BAYOU|UBIQUITI|GAMEMAX|SEAL|CONTROL ID|MIKROTIK|INNO3D|MOTOROLA|MOTOROLA|" +
            "MOTOROLA|MOTOROLA|GENIUS|GENIUS|GENIUS|PPA|LIKETEC|2FLEX|ACCEPT|ACER|ADATA|AGL|AMD|AOC|" +
            "ASROCK|ASUS|AZZA|BELLA CERCA|BETA CAVI|BRASIL PC|C3 TECH|CHIP SCE|CISCO|CITROX|COLETEK|" +
            "CONDUTTI|CONFISEG|CREALITY|D-LINK|DAZZ|DEEP COOL|ELGIN|ELGIN-BEMATECH|EPSON|EVGA|EZVIZ|" +
            "FC FONTES|FIBERHOME|FLEX MIDIA|FURUKAWA|GENNO|GFORCE|GIGA|GIGABYTE|GILTAR|HDL|HIKVISION|" +
            "HP|HUION|HYPERX|IGECAST|IMILAB|INTEL|INTELBRAS|IPEC|K-MEX|KASPERSKY|KINGSTON|LAN EXPERT|" +
            "LENOVO|LG|LINEAR|LOGITECH|MEGATRON|MERCUSYS|MICROSOFT|APPLE|LINUS|MULTITOC|NANO ACCESS|" +
            "NAZDA|NICE|NORTON|O-TECH|ONE POWER|ONIX|PATRIOT|PCYES|PECCININ|PHILIPS|PIXXO|PNY NVIDIA|" +
            "POWERTEK|REDRAGON|T-DAGGER|TCL|TP-LINK|TRANSCEND|TSSHARA|VAIO|WACOM|WESTERN DIGITAL|XEROX|" +
            "XZONE|G-TECH|SONY|CCE|PHILCO|AMAZON|LEXMARK|IBM|CREATIVE|VIA TECHNOLOGIES|REALTEK|C-MEDIA|" +
            "ANALOG|ADLIB|MULTILASER|EXBOM|MOSART|NVIDIA|CANNON|LINUX FOUNDATION|PHOENIX)";

    protected String osVendorsPattern =
            "(LINUX|PHOENIX|BSD|GOOGLE|CHROME OS|IOS|MICROSOFT|APPLE|RED HAT|LENOVO|IBM|HP|ORACLE|JUNIPER" +
            "|WAVEOS|ACKSYS|MITSUBISHI|SOLARIS|ORACLELINUX|SYNOLOGY)";

    protected String osTypePattern =
            "(UBUNTU|RED HAT|BSD|ALMALINUX|BIG LINUX|MINT|MANDRIVA|SLACKWARE|AMAZON ?LINUX|ORACLE ?LINUX|" +
            "WINDOWS 7|WINDOWS 10|WINDOWS 11|WINDOWS|WIN7|WIN10|WIN11|IOS|ANDROID|IOSX|CENTOS|DEBIAN|SUSE|" +
            "OPENSUSE|WINDOWS SERVER|ORACLE|ARCH|ARCH-LINUX|MS-DOS|CHROME[- ]OS|KURUMIN|XUBUNTU|AMIGA ?OS|" +
            "MAC ?OS|OPEN ?BSD|JAVA ?OS|ROCK ?LINUX|OS2|OS/2|XEROX|FREE ?BSD|OPEN ?SOLARIS|SKY ?OS|IS-DOS)";

    protected String datePattern =
            "(SMP [A-Z][a-z]{2} [A-Z][a-z]{2} [0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2} UTC)";

    protected String processorModelPattern =
            "(I[0-9]+|AMD|NVIDIA|MSI|GIGABYTE|ASUS|SAMSUNG)([-_.0-9a-zA-Z]+)";

    protected String processorFamilyPatter =
            "(INTEL|AMD|NVIDIA|MSI|GIGABYTE|ASUS|SAMSUNG)";

    protected String monitorTypePattern =
            "(FHD|WFHD|UHD|4K|HF LCD|LCD|HF|LED|UHLED|QLED)";

    protected String videoTypePattern =
            "(VGA|HD|FHD|UHD|DVI|HDMI|4K|DP|USB|RCA|MDP)";

    protected String audioTypePattern =
            "(PCH|HDMI|DTS|P2|USB|PS/2|S/PDIF)";

    protected String storageTypePattern =
            "(HD|SATA|SSD|NVME SSD|NVME|M2 SSD|M2|SCSI|SAS|ATA)";

    protected String usbTypePattern =
            "(USB 3.1|3.1|C-TYPE|TYPE-C|2.0|PCH USB)";

    protected String networkTypePattern =
            "(WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";

    protected String bridgeTypePattern =
            "(PCI Express|PCIe|PCI|PCH|ISA)";

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
            if (field.getName().equalsIgnoreCase(target)) {
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

    protected String stringExtractor(String input, String clear, String pattern, String replacer, int index) {

        try {

            String begin = input.replaceAll(pattern, "#<" + index + "#" + replacer + "#" + index + ">#");
            String extract = begin.replaceAll(", ", " ");

            return StringUtils
                    .substringBetween(extract, "#<" + index + "#", "#" + index + ">#")
                    .replaceAll(clear + ":", "").trim();

        } catch (Exception ex) {
            System.out.println("Exception during stringExtractor: " + ex.getMessage());
            return "";
        }

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
