package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsSystemDto {

    private String qty;
    private List<Help4DevsSystem> details;

    public Help4DevsSystemDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsSystem> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsSystem> details) {
        this.details = details;
    }

    public void addSystem(Help4DevsSystem system) {
        this.details.add(system);
    }

    public String toString() {
        return "Help4DevsSystemDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsSystem {

        private String name;
        private String distro;
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

        private String javaVersion;
        private String javaVendor;
        private String javaVendorUrl;
        private String javaHome;
        private String javaSpecVersion;
        private String javaSpecVendor;
        private String javaSpecName;
        private String javaClassVersion;
        private String javaClassPath;
        private String javaLibPath;
        private String javaTmpDir;
        private String javaCompiler;
        private String javaExtDir;
        private String javaVmSpecVersion;
        private String javaVmSpecVendor;
        private String javaVmSpecName;
        private String javaVmVersion;
        private String javaVmVendor;
        private String javaVmName;

        private List<String> hotfix;
        private List<String> features;

        public Help4DevsSystem() {}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDistro() {
            return distro;
        }

        public void setDistro(String distro) {
            this.distro = distro;
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

        public String getJavaVersion() {
            return javaVersion;
        }

        public void setJavaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
        }

        public String getJavaVendor() {
            return javaVendor;
        }

        public void setJavaVendor(String javaVendor) {
            this.javaVendor = javaVendor;
        }

        public String getJavaVendorUrl() {
            return javaVendorUrl;
        }

        public void setJavaVendorUrl(String javaVendorUrl) {
            this.javaVendorUrl = javaVendorUrl;
        }

        public String getJavaHome() {
            return javaHome;
        }

        public void setJavaHome(String javaHome) {
            this.javaHome = javaHome;
        }

        public String getJavaSpecVersion() {
            return javaSpecVersion;
        }

        public void setJavaSpecVersion(String javaSpecVersion) {
            this.javaSpecVersion = javaSpecVersion;
        }

        public String getJavaSpecVendor() {
            return javaSpecVendor;
        }

        public void setJavaSpecVendor(String javaSpecVendor) {
            this.javaSpecVendor = javaSpecVendor;
        }

        public String getJavaSpecName() {
            return javaSpecName;
        }

        public void setJavaSpecName(String javaSpecName) {
            this.javaSpecName = javaSpecName;
        }

        public String getJavaClassVersion() {
            return javaClassVersion;
        }

        public void setJavaClassVersion(String javaClassVersion) {
            this.javaClassVersion = javaClassVersion;
        }

        public String getJavaClassPath() {
            return javaClassPath;
        }

        public void setJavaClassPath(String javaClassPath) {
            this.javaClassPath = javaClassPath;
        }

        public String getJavaLibPath() {
            return javaLibPath;
        }

        public void setJavaLibPath(String javaLibPath) {
            this.javaLibPath = javaLibPath;
        }

        public String getJavaTmpDir() {
            return javaTmpDir;
        }

        public void setJavaTmpDir(String javaTmpDir) {
            this.javaTmpDir = javaTmpDir;
        }

        public String getJavaCompiler() {
            return javaCompiler;
        }

        public void setJavaCompiler(String javaCompiler) {
            this.javaCompiler = javaCompiler;
        }

        public String getJavaExtDir() {
            return javaExtDir;
        }

        public void setJavaExtDir(String javaExtDir) {
            this.javaExtDir = javaExtDir;
        }

        public String getJavaVmSpecVersion() {
            return javaVmSpecVersion;
        }

        public void setJavaVmSpecVersion(String javaVmSpecVersion) {
            this.javaVmSpecVersion = javaVmSpecVersion;
        }

        public String getJavaVmSpecVendor() {
            return javaVmSpecVendor;
        }

        public void setJavaVmSpecVendor(String javaVmSpecVendor) {
            this.javaVmSpecVendor = javaVmSpecVendor;
        }

        public String getJavaVmSpecName() {
            return javaVmSpecName;
        }

        public void setJavaVmSpecName(String javaVmSpecName) {
            this.javaVmSpecName = javaVmSpecName;
        }

        public String getJavaVmVersion() {
            return javaVmVersion;
        }

        public void setJavaVmVersion(String javaVmVersion) {
            this.javaVmVersion = javaVmVersion;
        }

        public String getJavaVmVendor() {
            return javaVmVendor;
        }

        public void setJavaVmVendor(String javaVmVendor) {
            this.javaVmVendor = javaVmVendor;
        }

        public String getJavaVmName() {
            return javaVmName;
        }

        public void setJavaVmName(String javaVmName) {
            this.javaVmName = javaVmName;
        }

        public List<String> getHotfix() {
            return hotfix;
        }

        public void setHotfix(List<String> hotfix) {
            this.hotfix = hotfix;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsSystem[" +
                    "name=" + name +
                    ", distro=" + distro +
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
                    ", javaVersion=" + javaVersion +
                    ", javaVendor=" + javaVendor +
                    ", javaVendorUrl=" + javaVendorUrl +
                    ", javaHome=" + javaHome +
                    ", javaSpecVersion=" + javaSpecVersion +
                    ", javaSpecVendor=" + javaSpecVendor +
                    ", javaSpecName=" + javaSpecName +
                    ", javaClassVersion=" + javaClassVersion +
                    ", javaClassPath=" + javaClassPath +
                    ", javaLibPath=" + javaLibPath +
                    ", javaTmpDir=" + javaTmpDir +
                    ", javaCompiler=" + javaCompiler +
                    ", javaExtDir=" + javaExtDir +
                    ", javaVmSpecVersion=" + javaVmSpecVersion +
                    ", javaVmSpecVendor=" + javaVmSpecVendor +
                    ", javaVmSpecName=" + javaVmSpecName +
                    ", javaVmVersion=" + javaVmVersion +
                    ", javaVmVendor=" + javaVmVendor +
                    ", javaVmName=" + javaVmName +
                    ", hotfix=" + hotfix +
                    ", features=" + features +
                    "]";
        }
    }
}
