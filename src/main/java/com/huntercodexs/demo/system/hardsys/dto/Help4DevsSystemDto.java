package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSystemDto {

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

    public Help4DevsSystemDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    public String getUserdir() {
        return userdir;
    }

    public void setUserdir(String userdir) {
        this.userdir = userdir;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMounted() {
        return mounted;
    }

    public void setMounted(String mounted) {
        this.mounted = mounted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public void setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public List<String> getHotfix() {
        return hotfix;
    }

    public void setHotfix(List<String> hotfix) {
        this.hotfix = hotfix;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
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
