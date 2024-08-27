package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsSystemDto {

    private final List<String> system;

    private String name;
    private String arch;
    private String home;
    private String date;
    private String root;
    private String vendor;
    private String kernel;
    private String userdir;
    private String release;
    private String version;
    private String mounted;
    private String username;
    private String description;
    private String manufacturer;
    private String fileSeparator;
    private String pathSeparator;
    private String lineSeparator;
    private List<String> hotfix;
    private List<String> details;

    public Help4DevsSystemDto(List<String> system) {
        this.system = system;
    }

    private String buildString(int i) {
        try {
            return this.system.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.system.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsSystemDto builder() {
        this.name = buildString(0);
        this.arch = buildString(1);
        this.home = buildString(2);
        this.date = buildString(3);
        this.root = buildString(4);
        this.vendor = buildString(5);
        this.kernel = buildString(6);
        this.userdir = buildString(7);
        this.release = buildString(8);
        this.version = buildString(9);
        this.mounted = buildString(10);
        this.username = buildString(11);
        this.description = buildString(12);
        this.manufacturer = buildString(13);
        this.fileSeparator = buildString(14);
        this.pathSeparator = buildString(15);
        this.lineSeparator = buildString(16);
        this.hotfix = buildCollection(17);
        this.details = buildCollection(18);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getArch() {
        return arch;
    }

    public String getHome() {
        return home;
    }

    public String getDate() {
        return date;
    }

    public String getRoot() {
        return root;
    }

    public String getVendor() {
        return vendor;
    }

    public String getKernel() {
        return kernel;
    }

    public String getUserdir() {
        return userdir;
    }

    public String getRelease() {
        return release;
    }

    public String getVersion() {
        return version;
    }

    public String getMounted() {
        return mounted;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public List<String> getHotfix() {
        return hotfix;
    }

    public List<String> getDetails() {
        return details;
    }

    public String toString() {
        return "Help4DevsSystemDto[" +
                "name=" + name +
                ", arch=" + arch +
                ", home=" + home +
                ", date=" + date +
                ", root=" + root +
                ", vendor=" + vendor +
                ", kernel=" + kernel +
                ", userdir=" + userdir +
                ", release=" + release +
                ", version=" + version +
                ", mounted=" + mounted +
                ", username=" + username +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                ", fileSeparator=" + fileSeparator +
                ", pathSeparator=" + pathSeparator +
                ", lineSeparator=" + lineSeparator +
                ", hotfix=" + hotfix +
                ", details=" + details +
                "]";
    }
}
