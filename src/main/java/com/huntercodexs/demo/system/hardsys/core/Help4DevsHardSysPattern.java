package com.huntercodexs.demo.system.hardsys.core;

public abstract class Help4DevsHardSysPattern extends Help4DevsHardSysLayout {

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
            "ANALOG|ADLIB|MULTILASER|EXBOM|MOSART|NVIDIA|CANNON|LINUX FOUNDATION|PHOENIX|CHICONY)";

    protected String productFamilyPattern =
            "(NITRO|ASPIRE|PREDATOR|COMPUTEX|SIM+|INTEL|RYZEN|INSPIRON|VIVOBOOK|LATITUTE|VOSTRO|PRECISION|" +
            "XPS|IDEAL ?PAD|THINK ?PAD|PAVILION ?[0-9A-Z]+|GALAXY|SAMSUNG|DV\\-[0-9A-Z]+)";

    protected String osVendorsPattern =
            "(LINUX|PHOENIX|BSD|GOOGLE|CHROME OS|IOS|MICROSOFT|APPLE|RED HAT|LENOVO|IBM|HP|ORACLE|JUNIPER" +
            "|WAVEOS|ACKSYS|MITSUBISHI|SOLARIS|ORACLELINUX|SYNOLOGY)";

    protected String osTypePattern =
            "(UBUNTU|RED HAT|BSD|ALMALINUX|BIG LINUX|MINT|MANDRIVA|SLACKWARE|AMAZON ?LINUX|ORACLE ?LINUX|" +
                    "WINDOWS 7|WINDOWS 10|WINDOWS 11|WINDOWS|WIN7|WIN10|WIN11|IOS|ANDROID|IOSX|CENTOS|DEBIAN|SUSE|" +
                    "OPENSUSE|WINDOWS SERVER|ORACLE|ARCH|ARCH-LINUX|MS-DOS|CHROME[- ]OS|KURUMIN|XUBUNTU|AMIGA ?OS|" +
                    "MAC ?OS|OPEN ?BSD|JAVA ?OS|ROCK ?LINUX|OS2|OS/2|XEROX|FREE ?BSD|OPEN ?SOLARIS|SKY ?OS|IS-DOS)";

    protected String drivesTypePattern =
            "(HD|FLOPPY|DISK|CD-?ROM|DVD|DISKETTE|LOCAL)";

    protected String datePattern =
            "(SMP [A-Z][a-z]{2} [A-Z][a-z]{2} [0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2} UTC)";

    protected String processorModelPattern =
            "(I[0-9]+|AMD|NVIDIA|MSI|GIGABYTE|ASUS|SAMSUNG)([-_.0-9a-zA-Z]+)";

    protected String processorFamilyPattern =
            "(INTEL|AMD|NVIDIA|MSI|GIGABYTE|ASUS|SAMSUNG)";

    protected String monitorTypePattern =
            "(FHD|WFHD|UHD|4K|HF LCD|LCD|HF|LED|UHLED|QLED)";

    protected String videoTypePattern =
            "(VGA|HD|FHD|UHD|DVI|HDMI|4K|DP|USB|RCA|MDP)";

    protected String audioTypePattern =
            "(PCH|HDMI|DTS|P2|USB|PS/2|S/PDIF)";

    protected String storageTypePattern =
            "(HD|SATA|SSD|NVME SSD|NVME|M2 SSD|M2|SCSI|SAS|ATA|NVME)";

    protected String usbVersionTypePattern =
            "(USB 3.1|3.1|C-TYPE|TYPE-C|2.0|PCH USB)";

    protected String networkTypePattern =
            "(WAN|WLAN|ETHERNET|WI\\- ?FI|WIFI|WI ?FI|WIRELESS|LOOPBACK|LAN|LO)";

    protected String bridgeTypePattern =
            "(PCI Express|PCIe|PCI|PCH|ISA)";

    protected String sensorsTypePattern =
            "(FAN ?SPEEDS|SYSTEM ?TEMPERATURES|COOLER|SENSOR|FAN|TEMP)";

    protected String controllerTypePattern =
            "(SPI|SMBUS|IO|USB|MOTHERBOARD|BUS)";

    protected String usbTypePattern =
            "(AUDIO|VIDEO|BLUETOOTH|KEYBOARD,MOUSE|KEYBOARD|MOUSE)";

    protected String numericPattern = "([0-9]+)";
    protected String alphaPattern1 = "([a-zA-Z])";
    protected String alphaPattern2 = "([-a-zA-Z_])";

    protected String alphaFieldSpacePattern = "([a-zA-Z]+: ?[-_.0-9a-zA-Z]+)";

    protected String alphaField1SpacePattern = "([a-zA-Z]+: ?)([-_.0-9a-zA-Z ]+){1}";
    protected String alphaField2SpacePattern = "([a-zA-Z]+: ?)([\\-_\\.0-9a-zA-Z ]+){1,2} ";
    protected String alphaField3SpacePattern = "([a-zA-Z]+: ?)([-_.0-9a-zA-Z ]+){3}";
    protected String alphaField4SpacePattern = "([a-zA-Z]+: ?)([-_.0-9a-zA-Z ]+){4}";
    protected String alphaField5SpacePattern = "([a-zA-Z]+: ?)([-_.0-9a-zA-Z ]+){5}";

    protected String alphaNumericPattern = "([0-9a-zA-Z]+)";

    protected String alphaNumericSpacePattern = "([0-9a-zA-Z ]+)";
    protected String alphaNumeric1SpacePattern = "([0-9a-zA-Z ]+){1}";
    protected String alphaNumeric2SpacePattern = "([0-9a-zA-Z ]+){2}";
    protected String alphaNumeric3SpacePattern = "([0-9a-zA-Z ]+){3}";
    protected String alphaNumeric4SpacePattern = "([0-9a-zA-Z ]+){4}";
    protected String alphaNumeric5SpacePattern = "([0-9a-zA-Z ]+){5}";

}
