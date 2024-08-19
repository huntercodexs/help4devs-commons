package com.huntercodexs.demo.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.*;
import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;

@Slf4j
@Service
public class Help4DevsSystemService {

    public static class TimerInfo {

        private int decimals;
        private long start;
        private long current;
        private long end;
        private long total;
        private boolean finished;
        private TimerSpec timerSpec;

        public TimerInfo() {
            this.setStart();
            this.setCurrent();
            this.setEnd();
            this.setTotal();
            this.decimals = 2;
            this.finished = false;
            this.timerSpec = TimerSpec.MILLI;
        }

        private void setStart() {
            this.start = System.currentTimeMillis();
        }

        private void setEnd() {
            this.end = System.currentTimeMillis();
        }

        private void setTotal() {
            this.setEnd();
            this.total = this.end - this.start;
        }

        public void setCurrent() {
            this.current = System.currentTimeMillis();
        }

        public void finish() {
            this.finished = true;
        }

        public void timerSpec(TimerSpec spec, int decimals) {
            this.decimals = decimals;
            this.timerSpec = spec;
        }

        public void restart() {
            this.setStart();
            this.setCurrent();
            this.setEnd();
            this.setTotal();
        }

        public TimerInfoMetrics metrics() {
            if (!finished) {
                this.setCurrent();
                this.setTotal();
            }
            this.setCurrent();
            return new TimerInfoMetrics(this.start, this.current, this.end, this.total, this.timerSpec, this.decimals);
        }

        public static class TimerInfoMetrics {

            public int decimals;
            public long start;
            public long current;
            public long end;
            public long total;
            public TimerSpec timerSpec;

            public TimerInfoMetrics(long start, long current, long end, long total, TimerSpec timerSpec, int decimals) {
                this.decimals = decimals;
                this.start = start;
                this.current = current;
                this.end = end;
                this.total = total;
                this.timerSpec = timerSpec;
            }

            private String timerFormatter(long timer) {

                String result;
                int dig = this.decimals;

                if (this.timerSpec.equals(TimerSpec.HOUR)) {
                    result = String.format("%.0"+dig+"f", ((float)timer / (float)(60000 * 60))) + " hours";

                } else if (this.timerSpec.equals(TimerSpec.MIN)) {
                    result = String.format("%.0"+dig+"f", ((float)timer / (float)60000)) + " minutes";

                } else if (this.timerSpec.equals(TimerSpec.SEC)) {
                    result = String.format("%.0"+dig+"f", ((float)timer / (float)1000)) + " seconds";

                } else {
                    result = String.format("%s", timer) + " milliseconds";
                }

                return result;
            }

            public String getStart() {
                return String.valueOf(this.start);
            }

            public String getCurrent() {
                return String.valueOf(this.current);
            }

            public String getEnd() {
                return String.valueOf(this.end);
            }

            public String getTotal() {
                return timerFormatter(this.total);
            }

            public String toString() {
                return
                        "Total Time: "+this.total+"\n" +
                        "Start Time: "+this.start+"\n" +
                        "Current Time: "+this.current+"\n" +
                        "End Time: "+this.end+"\n";
            }
        }

        public enum TimerSpec {
            MILLI,
            SEC,
            MIN,
            HOUR;
        }
    }

    public static class JavaInfo {

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
        private String javaIoTmpDir;
        private String javaCompiler;
        private String javaExtraDirs;

        public JavaInfo() {
            this.setJavaVersion();
            this.setJavaVendor();
            this.setJavaVendorUrl();
            this.setJavaHome();
            this.setJavaSpecVersion();
            this.setJavaSpecVendor();
            this.setJavaSpecName();
            this.setJavaClassVersion();
            this.setJavaClassPath();
            this.setJavaLibPath();
            this.setJavaIoTmpDir();
            this.setJavaCompiler();
            this.setJavaExtraDirs();
        }

        //The version of Java Runtime Environment.
        private void setJavaVersion() {
            this.javaVersion = "Java Version: "+System.getProperty("java.version");
        }

        //The name of Java Runtime Environment vendor
        private void setJavaVendor() {
            this.javaVendor = "Java Vendor: "+System.getProperty("java.vendor");
        }

        //The URL of Java vendor
        private void setJavaVendorUrl() {
            this.javaVendorUrl = "Java Vendor URL: "+System.getProperty("java.vendor.url");
        }

        //The directory of Java installation
        private void setJavaHome() {
            this.javaHome = "Java Home: "+System.getProperty("java.home");
        }

        //The name of specification version Java Runtime Environment
        private void setJavaSpecVersion() {
            this.javaSpecVersion = "Java Specification Version: "+System.getProperty("java.specification.version");
        }

        // JRE specification vendor
        private void setJavaSpecVendor() {
            this.javaSpecVendor = "Java Specification Vendor: "+System.getProperty("java.specification.vendor");
        }

        //JRE specification name
        private void setJavaSpecName() {
            this.javaSpecName = "Java Specification Name: "+System.getProperty("java.specification.name");
        }

        //Java class format version number
        private void setJavaClassVersion() {
            this.javaClassVersion = "Java Class Version: "+System.getProperty("java.class.version");
        }

        //Path of java class
        private void setJavaClassPath() {
            this.javaClassPath = "Java Class Path: "+System.getProperty("java.class.path");
        }

        //List of paths to search when loading libraries
        private void setJavaLibPath() {
            this.javaLibPath = "Java Library Path: "+System.getProperty("java.library.path");
        }

        //The path of temp file
        private void setJavaIoTmpDir() {
            this.javaIoTmpDir = "Java IO Tmpdir: "+System.getProperty("java.io.tmpdir");
        }

        //The Name of JIT compiler to use
        private void setJavaCompiler() {
            this.javaCompiler = "Java Compiler: "+System.getProperty("java.compiler");
        }

        //The path of extension directory or directories
        private void setJavaExtraDirs() {
            this.javaExtraDirs = "Java Extra Dirs: "+System.getProperty("java.ext.dirs");
        }

        public JavaInfoMetrics info() {
            return new JavaInfoMetrics(
                this.javaVersion,
                this.javaVendor,
                this.javaVendorUrl,
                this.javaHome,
                this.javaSpecVersion,
                this.javaSpecVendor,
                this.javaSpecName,
                this.javaClassVersion,
                this.javaClassPath,
                this.javaLibPath,
                this.javaIoTmpDir,
                this.javaCompiler,
                this.javaExtraDirs);
        }

        public static class JavaInfoMetrics {

            public String javaVersion;
            public String javaVendor;
            public String javaVendorUrl;
            public String javaHome;
            public String javaSpecVersion;
            public String javaSpecVendor;
            public String javaSpecName;
            public String javaClassVersion;
            public String javaClassPath;
            public String javaLibPath;
            public String javaIoTmpDir;
            public String javaCompiler;
            public String javaExtraDirs;

            public JavaInfoMetrics(
                    String javaVersion,
                    String javaVendor,
                    String javaVendorUrl,
                    String javaHome,
                    String javaSpecVersion,
                    String javaSpecVendor,
                    String javaSpecName,
                    String javaClassVersion,
                    String javaClassPath,
                    String javaLibPath,
                    String javaIoTmpDir,
                    String javaCompiler,
                    String javaExtraDirs
            ) {
                this.javaVersion = javaVersion;
                this.javaVendor = javaVendor;
                this.javaVendorUrl = javaVendorUrl;
                this.javaHome = javaHome;
                this.javaSpecVersion = javaSpecVersion;
                this.javaSpecVendor = javaSpecVendor;
                this.javaSpecName = javaSpecName;
                this.javaClassVersion = javaClassVersion;
                this.javaClassPath = javaClassPath;
                this.javaLibPath = javaLibPath;
                this.javaIoTmpDir = javaIoTmpDir;
                this.javaCompiler = javaCompiler;
                this.javaExtraDirs = javaExtraDirs;
            }

            public String getJavaVersion() {
                return javaVersion;
            }

            public String getJavaVendor() {
                return javaVendor;
            }

            public String getJavaVendorUrl() {
                return javaVendorUrl;
            }

            public String getJavaHome() {
                return javaHome;
            }

            public String getJavaSpecVersion() {
                return javaSpecVersion;
            }

            public String getJavaSpecVendor() {
                return javaSpecVendor;
            }

            public String getJavaSpecName() {
                return javaSpecName;
            }

            public String getJavaClassVersion() {
                return javaClassVersion;
            }

            public String getJavaClassPath() {
                return javaClassPath;
            }

            public String getJavaLibPath() {
                return javaLibPath;
            }

            public String getJavaIoTmpDir() {
                return javaIoTmpDir;
            }

            public String getJavaCompiler() {
                return javaCompiler;
            }

            public String getJavaExtraDirs() {
                return javaExtraDirs;
            }

            public String toString() {
                return
                        this.javaVersion+"\n" +
                        this.javaVendor+"\n" +
                        this.javaVendorUrl+"\n" +
                        this.javaHome+"\n" +
                        this.javaSpecVersion+"\n" +
                        this.javaSpecVendor+"\n" +
                        this.javaSpecName+"\n" +
                        this.javaClassVersion+"\n" +
                        this.javaClassPath+"\n" +
                        this.javaLibPath+"\n" +
                        this.javaIoTmpDir+"\n" +
                        this.javaCompiler+"\n" +
                        this.javaExtraDirs+"\n";
            }
        }
    }

    public static class MemoryInfo {

        private long total;
        private long start;
        private long current;
        private long end;
        private long free;
        private long used;
        private boolean finished;
        MemorySpec memorySpec;
        private MemoryInfoMetrics metrics;

        public MemoryInfo() {
            this.arrange();
        }

        private void arrange() {
            this.setTotal();
            this.setStart();
            this.setCurrent();
            this.setEnd();
            this.setFree();
            this.setUsed();
            this.finished = false;
            this.memorySpec = MemorySpec.AUTO;
            this.metrics = new MemoryInfoMetrics(this.memorySpec);
        }

        private void killFinished() {
            if (finished) {
                throw new RuntimeException("The process was finished by calling finish!");
            }
        }

        private void setTotal() {
            this.total = Runtime.getRuntime().totalMemory();
        }

        private void setStart() {
            this.start = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        }

        private void setCurrent() {
            this.current = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        }

        private void setEnd() {
            this.end = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        }

        private void setFree() {
            this.free = Runtime.getRuntime().freeMemory();
        }

        private void setUsed() {
            this.used = this.end - this.start;
        }

        private void memoryClean() {
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
        }

        public String total() {
            this.killFinished();
            return metrics.memoryFormatter(this.total);
        }

        public void memorySpec(MemorySpec memorySpec) {
            this.killFinished();
            this.memorySpec = memorySpec;
        }

        public void current() {
            this.killFinished();
            this.setCurrent();
        }

        public String using() {
            this.killFinished();
            this.setEnd();
            this.setUsed();
            return metrics.memoryFormatter(this.used);
        }

        public void restart() {
            this.arrange();
        }

        public void finished() {
            this.setEnd();
            this.setUsed();
            this.setFree();
            this.memoryClean();
            this.finished = true;
        }

        public void clean() {
            this.memoryClean();
            this.setStart();
            this.setCurrent();
            this.setEnd();
            this.setFree();
            this.setUsed();
        }

        public MemoryInfoMetrics metrics() {
            if (this.finished) {
                return new MemoryInfoMetrics(
                        this.total,
                        this.start,
                        this.current,
                        this.end,
                        this.free,
                        this.used,
                        this.memorySpec);
            }
            throw new RuntimeException("You must finish the current process before call the metrics!");
        }

        public static class MemoryInfoMetrics {

            public long total;
            public long start;
            public long current;
            public long end;
            public long free;
            public long used;
            public MemorySpec memorySpec;

            public MemoryInfoMetrics(MemorySpec memorySpec) {
                this.memorySpec = memorySpec;
            }

            public MemoryInfoMetrics(
                    long total,
                    long start,
                    long current,
                    long end,
                    long free,
                    long used,
                    MemorySpec memorySpec
            ) {
                this.total = total;
                this.start = start;
                this.current = current;
                this.end = end;
                this.free = free;
                this.used = used;
                this.memorySpec = memorySpec;
            }

            public String memoryFormatter(long memory) {
                if (this.memorySpec.equals(MemorySpec.AUTO)) {
                    return calculateMemory(memory);
                } else if (this.memorySpec.equals(MemorySpec.GIGABYTES)) {
                    return calculateGigabytes(memory);
                } else if (this.memorySpec.equals(MemorySpec.MEGABYTES)) {
                    return calculateMegabytes(memory);
                } else if (this.memorySpec.equals(MemorySpec.KILOBYTES)) {
                    return calculateKilobytes(memory);
                } else {
                    System.out.println("BYTES");
                    return calculateBytes(memory);
                }
            }

            public String getTotal() {
                return this.memoryFormatter(this.total);
            }

            public String getStart() {
                return this.memoryFormatter(this.start);
            }

            public String getCurrent() {
                return this.memoryFormatter(this.current);
            }

            public String getEnd() {
                return this.memoryFormatter(this.end);
            }

            public String getFree() {
                return this.memoryFormatter(this.free);
            }

            public String getUsed() {
                return this.memoryFormatter(this.used);
            }

            public String toString() {
                return
                        "Total Memory: "+this.getTotal()+"\n" +
                        "Start Memory: "+this.getStart()+"\n" +
                        "Current Memory: "+this.getCurrent()+"\n" +
                        "End Memory: "+this.getEnd()+"\n" +
                        "Free Memory: "+this.getFree()+"\n" +
                        "Used Memory: "+this.getUsed()+"\n";
            }

        }

        public enum MemorySpec {
            AUTO,
            BYTES,
            KILOBYTES,
            MEGABYTES,
            GIGABYTES;
        }

    }

    public static class HardDriveInfo {

        private String absPath;
        private long total;
        private long free;
        private long used;
        private final File[] roots;

        public HardDriveInfo() {
            this.roots = File.listRoots();
            this.setAbsPath();
            this.setTotal();
            this.setFree();
            this.setUsed();
        }

        private void setAbsPath() {
            this.absPath = this.roots[0].getAbsolutePath();
        }

        private void setTotal() {
            this.total = this.roots[0].getTotalSpace();
        }

        private void setFree() {
            this.free = this.roots[0].getFreeSpace();
        }

        private void setUsed() {
            this.used = this.roots[0].getUsableSpace();
        }

        public HardDriveInfoMetrics metrics() {
            return new HardDriveInfoMetrics(this.absPath, this.total, this.free, this.used);
        }

        public static class HardDriveInfoMetrics {

            public String absPath;
            public long total;
            public long free;
            public long used;

            public HardDriveInfoMetrics(String absPath, long total, long free, long used) {
                this.absPath = absPath;
                this.total = total;
                this.free = free;
                this.used = used;
            }

            public String getAbsPath() {
                return "File system root: " + this.absPath;
            }

            public String getTotal() {
                return "Total space: " + calculateMemory(this.total) + " (" + this.total + " bytes)";
            }

            public String  getFree() {
                return "Free space: " + calculateMemory(this.free) + " (" + this.free + " bytes)";
            }

            public String getUsed() {
                return "Usable space: " + calculateMemory(this.used) + " (" + this.used + " bytes)";
            }

            public String toString() {
                return
                        "Class Path: "+this.getAbsPath()+"\n" +
                        "Total Disk: "+this.getTotal()+"\n" +
                        "Free Disk: "+this.getFree()+"\n" +
                        "Used Disk: "+this.getUsed()+"\n";
            }
        }
    }

    public static class ProcessorInfo {

        private String arch;
        private String name;
        private String vendor;
        private String cores;
        private String cpus;
        private String mhz;
        private String virtualization;
        private String l1Cache;
        private String l2Cache;
        private String l3Cache;

        private List<String> processorList;

        public ProcessorInfo() {
            this.loader();
            this.processor();
        }

        private void loader() {

            String command = "lscpu";

            try {

                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String result;
                this.processorList = new ArrayList<>();

                while ((result = reader.readLine()) != null) {

                    String[] splitter = result
                            .replaceAll("(: +)+", ":")
                            .replaceAll("^ +\\[", "[")
                            .replaceAll("^ +([0-9a-zA-Z])", "$1")
                            .replaceFirst(":", "{:cutter:}")
                            .split("\\{:cutter:}");

                    if (splitter.length == 2) {
                        this.processorList.add(splitter[0] + "{:splitter:}" + splitter[1]);
                    }

                }

            } catch (Exception ex) {
                throw new RuntimeException(
                        "There is a problem to running this resource.\n" +
                        "Please check if the '"+command+"' command is installed in the current operation system.\n" +
                        "Error: "+ ex.getMessage());
            }
        }

        private void processor() {

            for (String item : this.processorList) {

                String[] keyValue = item.split("\\{:splitter:}");

                if (keyValue[0].contains("Architecture")) {
                    this.setArch(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("Model name")) {
                    this.setName(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("Vendor")) {
                    this.setVendor(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("CPU(s) list")) {
                    this.setCpus(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("CPU MHz")) {
                    this.setMhz(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("Virtualization")) {
                    this.setVirtualization(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("L1") && keyValue[0].toLowerCase().contains("cache")) {
                    this.setL1Cache(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("L2") && keyValue[0].toLowerCase().contains("cache")) {
                    this.setL2Cache(keyValue[1]);
                    continue;
                }

                if (keyValue[0].contains("L3") && keyValue[0].toLowerCase().contains("cache")) {
                    this.setL3Cache(keyValue[1]);
                }

            }
            this.setCores(String.valueOf(Runtime.getRuntime().availableProcessors()));
        }

        private void setArch(String arch) {
            this.arch = arch;
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setVendor(String vendor) {
            this.vendor = vendor;
        }

        private void setCores(String cores) {
            this.cores = cores;
        }

        private void setCpus(String cpus) {
            this.cpus = cpus;
        }

        private void setMhz(String mhz) {
            this.mhz = mhz;
        }

        private void setVirtualization(String virtualization) {
            this.virtualization = virtualization;
        }

        private void setL1Cache(String l1Cache) {
            this.l1Cache = l1Cache;
        }

        private void setL2Cache(String l2Cache) {
            this.l2Cache = l2Cache;
        }

        private void setL3Cache(String l3Cache) {
            this.l3Cache = l3Cache;
        }

        public ProcessorInfoMetrics metrics() {
            return new ProcessorInfoMetrics(
                    this.arch,
                    this.name,
                    this.vendor,
                    this.cores,
                    this.cpus,
                    this.mhz,
                    this.virtualization,
                    this.l1Cache,
                    this.l2Cache,
                    this.l3Cache);
        }

        public static class ProcessorInfoMetrics {

            public String arch;
            public String name;
            public String vendor;
            public String cores;
            public String cpus;
            public String mhz;
            public String virtualization;
            public String l1Cache;
            public String l2Cache;
            public String l3Cache;

            public ProcessorInfoMetrics(
                    String arch,
                    String name,
                    String vendor,
                    String cores,
                    String cpus,
                    String mhz,
                    String virtualization,
                    String l1Cache,
                    String l2Cache,
                    String l3Cache
            ) {
                this.arch = arch;
                this.name = name;
                this.vendor = vendor;
                this.cores = cores;
                this.cpus = cpus;
                this.mhz = mhz;
                this.virtualization = virtualization;
                this.l1Cache = l1Cache;
                this.l2Cache = l2Cache;
                this.l3Cache = l3Cache;
            }

            public String getArch() {
                return this.arch;
            }

            public String getName() {
                return this.name;
            }

            public String getVendor() {
                return this.vendor;
            }

            public String getCores() {
                return this.cores;
            }

            public String getCpus() {
                return this.cpus;
            }

            public String getMhz() {
                return this.mhz;
            }

            public String getVirtualization() {
                return this.virtualization;
            }

            public String getL1Cache() {
                return this.l1Cache;
            }

            public String getL2Cache() {
                return this.l2Cache;
            }

            public String getL3Cache() {
                return this.l3Cache;
            }

            public String toString() {
                return
                        "Name: "+this.getName()+"\n" +
                        "Architecture: "+this.getArch()+"\n" +
                        "Vendor: "+this.getVendor()+"\n" +
                        "Cores: "+this.getCores()+"\n" +
                        "Cpus: "+this.getCpus()+"\n" +
                        "Mhz: "+this.getMhz()+"\n" +
                        "Virtualization: "+this.getVirtualization()+"\n" +
                        "L1Cache: "+this.getL1Cache()+"\n" +
                        "L2Cache: "+this.getL2Cache()+"\n" +
                        "L3Cache: "+this.getL3Cache()+"\n";
            }
        }

    }

    public static class JVirtualMachineInfo {

        private String javaSpecificationVersion;
        private String javaVMSpecificationVendor;
        private String javaVMSpecificationName;
        private String javaVMVersion;
        private String javaVMVendor;
        private String javaVMName;

        public JVirtualMachineInfo() {
            this.loader();
        }

        private void loader() {
            this.setJavaVmSpecVersion(System.getProperty("java.vm.specification.version"));
            this.setJavaVmSpecVendor(System.getProperty("java.vm.specification.vendor"));
            this.setJavaVmSpecName(System.getProperty("java.vm.specification.name"));
            this.setJavaVmVersion(System.getProperty("java.vm.version"));
            this.setJavaVmVendor(System.getProperty("java.vm.vendor"));
            this.setJavaVmName(System.getProperty("java.vm.name"));
        }

        //The specification version of Java Virtual Machine
        private void setJavaVmSpecVersion(String value) {
            this.javaSpecificationVersion = value;
        }

        //The name of specification vendor of Java Virtual Machine
        private void setJavaVmSpecVendor(String value) {
            this.javaVMSpecificationVendor = value;
        }

        //Java Virtual Machine specification name
        private void setJavaVmSpecName(String value) {
            this.javaVMSpecificationName = value;
        }

        //JVM implementation version
        private void setJavaVmVersion(String value) {
            this.javaVMVersion = value;
        }

        //JVM implementation vendor
        private void setJavaVmVendor(String value) {
            this.javaVMVendor = value;
        }

        //JVM  implementation name
        private void setJavaVmName(String value) {
            this.javaVMName = value;
        }

        public JVirtualMachineInfoMetrics metrics() {
            return new JVirtualMachineInfoMetrics(
                this.javaSpecificationVersion,
                this.javaVMSpecificationVendor,
                this.javaVMSpecificationName,
                this.javaVMVersion,
                this.javaVMVendor,
                this.javaVMName);
        }

        public static class JVirtualMachineInfoMetrics {

            public String javaSpecificationVersion;
            public String javaVMSpecificationVendor;
            public String javaVMSpecificationName;
            public String javaVMVersion;
            public String javaVMVendor;
            public String javaVMName;

            public JVirtualMachineInfoMetrics(
                    String javaSpecificationVersion,
                    String javaVMSpecificationVendor,
                    String javaVMSpecificationName,
                    String javaVMVersion,
                    String javaVMVendor,
                    String javaVMName
            ) {
                this.javaSpecificationVersion = javaSpecificationVersion;
                this.javaVMSpecificationVendor = javaVMSpecificationVendor;
                this.javaVMSpecificationName = javaVMSpecificationName;
                this.javaVMVersion = javaVMVersion;
                this.javaVMVendor = javaVMVendor;
                this.javaVMName = javaVMName;
            }

            public String getJavaSpecificationVersion() {
                return javaSpecificationVersion;
            }

            public String getJavaVMSpecificationVendor() {
                return javaVMSpecificationVendor;
            }

            public String getJavaVMSpecificationName() {
                return javaVMSpecificationName;
            }

            public String getJavaVMVersion() {
                return javaVMVersion;
            }

            public String getJavaVMVendor() {
                return javaVMVendor;
            }

            public String getJavaVMName() {
                return javaVMName;
            }

            public String toString() {
                return
                        "Java Specification Version: "+this.getJavaSpecificationVersion()+"\n"+
                        "Java VM Specification Vendor: "+this.getJavaVMSpecificationVendor()+"\n"+
                        "Java VM Specification Name: "+this.getJavaVMSpecificationName()+"\n"+
                        "Java VM Version: "+this.getJavaVMVersion()+"\n"+
                        "Java VM Vendor: "+this.getJavaVMVendor()+"\n"+
                        "Java VM Name: "+this.getJavaVMName()+"\n";
            }
        }

    }

    public static class OperationSystemInfo {

        private String osName;
        private String osArch;
        private String osVersion;
        private String osFileSeparator;
        private String osPathSeparator;
        private String osLineBreak;
        private String userName;
        private String userHome;
        private String userDir;

        public OperationSystemInfo() {
            this.loader();
        }

        private void loader() {
            this.setOsName(System.getProperty("os.name"));
            this.setOsArch(System.getProperty("os.arch"));
            this.setOsVersion(System.getProperty("os.version"));
            this.setOsFileSeparator(System.getProperty("file.separator"));
            this.setOsPathSeparator(System.getProperty("path.separator"));
            this.setOsLineBreak(System.getProperty("line.separator"));
            this.setUserName(System.getProperty("user.name"));
            this.setUserHome(System.getProperty("user.home"));
            this.setUserDir(System.getProperty("user.dir"));
        }

        //The name of OS name
        private void setOsName(String value) {
            this.osName = value;
        }

        //The OS architecture
        private void setOsArch(String value) {
            this.osArch = value;
        }

        //The version of OS
        private void setOsVersion(String value) {
            this.osVersion = value;
        }

        //The File separator
        private void setOsFileSeparator(String value) {
            this.osFileSeparator = value;
        }

        //The path separator
        private void setOsPathSeparator(String value) {
            this.osPathSeparator = value;
        }

        //The line separator
        private void setOsLineBreak(String value) {
            this.osLineBreak = value;
        }

        //The name of account name user
        private void setUserName(String value) {
            this.userName = value;
        }

        //The home directory of user
        private void setUserHome(String value) {
            this.userHome = value;
        }

        //The current working directory of the user
        private void setUserDir(String value) {
            this.userDir = value;
        }

        public OperationSystemInfoMetrics metrics() {
            return new OperationSystemInfoMetrics(
                this.osName,
                this.osArch,
                this.osVersion,
                this.osFileSeparator,
                this.osPathSeparator,
                this.osLineBreak,
                this.userName,
                this.userHome,
                this.userDir);
        }

        public static class OperationSystemInfoMetrics {

            public String osName;
            public String osArch;
            public String osVersion;
            public String osFileSeparator;
            public String osPathSeparator;
            public String osLineBreak;
            public String userName;
            public String userHome;
            public String userDir;

            public OperationSystemInfoMetrics(
                    String osName,
                    String osArch,
                    String osVersion,
                    String osFileSeparator,
                    String osPathSeparator,
                    String osLineBreak,
                    String userName,
                    String userHome,
                    String userDir
            ) {
                this.osName = osName;
                this.osArch = osArch;
                this.osVersion = osVersion;
                this.osFileSeparator = osFileSeparator;
                this.osPathSeparator = osPathSeparator;
                this.osLineBreak = osLineBreak;
                this.userName = userName;
                this.userHome = userHome;
                this.userDir = userDir;
            }

            public String getOsName() {
                return osName;
            }

            public String getOsArch() {
                return osArch;
            }

            public String getOsVersion() {
                return osVersion;
            }

            public String getOsFileSeparator() {
                return osFileSeparator;
            }

            public String getOsPathSeparator() {
                return osPathSeparator;
            }

            public String getOsLineBreak() {
                return "'"+osLineBreak
                        .replaceAll("\n", "\\\\n")
                        .replaceAll("\r", "\\\\r")+"'";
            }

            public String getUserName() {
                return userName;
            }

            public String getUserHome() {
                return userHome;
            }

            public String getUserDir() {
                return userDir;
            }

            public String toString() {

                return
                        "OS Name: "+this.getOsName()+"\n"+
                        "OS Arch: "+this.getOsArch()+"\n"+
                        "OS Version: "+this.getOsVersion()+"\n"+
                        "OS File Separator: "+this.getOsFileSeparator()+"\n"+
                        "OS Path Separator: "+this.getOsPathSeparator()+"\n"+
                        "OS Line Break: "+this.getOsLineBreak()+"\n"+
                        "User Name: "+this.getUserName()+"\n"+
                        "User Home: "+this.getUserHome()+"\n"+
                        "User Dir: "+this.getUserDir()+"\n";

            }

        }

    }

    public static class GeneralSystemInfo {

        private AvailableCommands command;
        private HashMap<String, List<String>> resources;

        public static final String[] INFO_INDEX = new String[]{
                "system", "machine", "battery", "memory", "slots", "processor", "graphics",
                "audio", "network", "drivers", "partition", "usb", "sensors", "running",
                "monitor", "bios", "baseboard", "chassis", "cache", "connector", "drives"};

        public GeneralSystemInfo(AvailableCommands command) {
            this.loader(command);
        }

        private String resourceName(String input) {
            return input
                    .replaceAll("PCI Slots:", "slots")//TODO: VERIFICAR COMO SEPARAR OS FLUXOS DE PROCESSAMENTO POR COMANDOS
                    .replaceAll("CPU: ", "processor")
                    .replaceAll("Info: ", "running")
                    .replaceAll("[^a-zA-Z]", "")
                    .toLowerCase();
        }

        private void inxiRun() {

            try {

                Process process = Runtime.getRuntime().exec(AvailableCommands.INXI.getCmd());
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                //INXI Version 3.0.38 (Layout)
                String[] inxiInfo = new String[] {
                        "System:    ", "Machine:   ", "Battery:   ", "Memory:    ", "PCI Slots: ",
                        "CPU:       ", "Graphics:  ", "Audio:     ", "Network:   ", "Drives:    ",
                        "Partition: ", "USB:       ", "Sensors:   ", "Info:      "};

                String currentLine = reader.readLine();

                for (int i = 0; i < inxiInfo.length; i++) {

                    //System.out.println("CURRENT-LINE: "+currentLine);

                    List<String> array = new ArrayList<>();

                    if (currentLine == null) continue;

                    if (currentLine.contains(inxiInfo[i])) {
                       // System.out.println(">>>> CURRENT LINE CONTAINS: " + inxiInfo[i]);//ok

                        String lines;
                        array.add(currentLine.replace(inxiInfo[i], "").trim());
                       // System.out.println(">>> array-1: "+array);

                        while ((lines = reader.readLine()) != null) {

                            //System.out.println("CURRENT-LINE: "+lines);

                            //Last line
                            if (i == inxiInfo.length-1) {
                                array.add(lines.trim());
                               // System.out.println(">>> array-[last]: "+array);
                                break;
                            }

                            if (lines.contains(inxiInfo[i+1])) {
                                currentLine = lines;
                                break;
                            }
                            array.add(lines.trim());
                           // System.out.println(">>> array-2: "+array);
                        }

                        //Save line according INFO_INDEX
                        this.resources.put(resourceName(inxiInfo[i]), array);
                       // System.out.println("PUT: "+array+" in "+resourceName(inxiInfo[i]));
                    }
                }

                /*this.metrics.forEach((item, list) -> {
                    System.out.println("ITEM: "+item);
                    for (String value : list) {
                        System.out.println(value);
                    }
                });*/

            } catch (IOException ioe) {
                throw new RuntimeException(ioe.getMessage());
            }
        }

        private void hwinfoRun() {
        }

        private void lshwRun() {
        }

        private void lscpuRun() {
        }

        private void lscpu2Run() {
        }

        private void dmidecodeRun() {
        }

        private void systeminfoRun() {
        }

        private void loader(AvailableCommands command) {

            this.resources = new HashMap<>();
            for (String res : INFO_INDEX) {
                this.resources.put(res, new ArrayList<>());
            }

            if (command.equals(AvailableCommands.INXI)) {
                this.inxiRun();
            } else if (command.equals(AvailableCommands.HWINFO)) {
                this.hwinfoRun();
            } else if (command.equals(AvailableCommands.LSHW)) {
                this.lshwRun();
            } else if (command.equals(AvailableCommands.LSCPU)) {
                this.lscpuRun();
            } else if (command.equals(AvailableCommands.LSCPU2)) {
                this.lscpu2Run();
            } else if (command.equals(AvailableCommands.DMIDECODE)) {
                this.dmidecodeRun();
            } else if (command.equals(AvailableCommands.SYSTEMINFO_WINDOWS)) {
                this.systeminfoRun();
            } else {
                throw new RuntimeException("Wrong sys cmd to retrieve information: " + command.getCmd());
            }
        }

        public GeneralSystemInfoMetrics metrics() {
            return new GeneralSystemInfoMetrics(this.resources);
        }

        public static class GeneralSystemInfoMetrics {

            public SystemDetails systemDetails;
            public MachineDetails machineDetails;
            public BatteryDetails batteryDetails;
            public MemoryDetails memoryDetails;
            public SlotsDetails slotsDetails;
            public ProcessorDetails processorDetails;
            public GraphicsDetails graphicsDetails;
            public AudioDetails audioDetails;
            public NetworkDetails networkDetails;
            public DriversDetails driversDetails;
            public PartitionDetails partitionDetails;
            public UsbDetails usbDetails;
            public SensorsDetails sensorsDetails;
            public RunningDetails runningDetails;
            public MonitorDetails monitorDetails;
            public BiosDetails biosDetails;
            public BaseboardDetails baseboardDetails;
            public ChassisDetails chassisDetails;
            public CacheDetails cacheDetails;
            public ConnectorDetails connectorDetails;
            public DrivesDetails drivesDetails;

            public GeneralSystemInfoMetrics(HashMap<String, List<String>> resources) {
                //See the INFO_INDEX to get more details
                this.systemDetails = new SystemDetails(resources.get(INFO_INDEX[0]));
                this.machineDetails = new MachineDetails(resources.get(INFO_INDEX[1]));
                this.batteryDetails = new BatteryDetails(resources.get(INFO_INDEX[2]));
                this.memoryDetails = new MemoryDetails(resources.get(INFO_INDEX[3]));
                this.slotsDetails = new SlotsDetails(resources.get(INFO_INDEX[4]));
                this.processorDetails = new ProcessorDetails(resources.get(INFO_INDEX[5]));
                this.graphicsDetails = new GraphicsDetails(resources.get(INFO_INDEX[6]));
                this.audioDetails = new AudioDetails(resources.get(INFO_INDEX[7]));
                this.networkDetails = new NetworkDetails(resources.get(INFO_INDEX[8]));
                this.driversDetails = new DriversDetails(resources.get(INFO_INDEX[9]));
                this.partitionDetails = new PartitionDetails(resources.get(INFO_INDEX[10]));
                this.usbDetails = new UsbDetails(resources.get(INFO_INDEX[11]));
                this.sensorsDetails = new SensorsDetails(resources.get(INFO_INDEX[12]));
                this.runningDetails = new RunningDetails(resources.get(INFO_INDEX[13]));
                this.monitorDetails = new MonitorDetails(resources.get(INFO_INDEX[14]));
                this.biosDetails = new BiosDetails(resources.get(INFO_INDEX[15]));
                this.baseboardDetails = new BaseboardDetails(resources.get(INFO_INDEX[16]));
                this.chassisDetails = new ChassisDetails(resources.get(INFO_INDEX[17]));
                this.cacheDetails = new CacheDetails(resources.get(INFO_INDEX[18]));
                this.connectorDetails = new ConnectorDetails(resources.get(INFO_INDEX[19]));
                this.drivesDetails = new DrivesDetails(resources.get(INFO_INDEX[20]));
            }

            public SystemDetails getSystem() {
                return systemDetails;
            }

            public MachineDetails getMachine() {
                return machineDetails;
            }

            public BatteryDetails getBattery() {
                return batteryDetails;
            }

            public MemoryDetails getMemory() {
                return memoryDetails;
            }

            public SlotsDetails getSlots() {
                return slotsDetails;
            }

            public ProcessorDetails getProcessor() {
                return processorDetails;
            }

            public GraphicsDetails getGraphics() {
                return graphicsDetails;
            }

            public AudioDetails getAudio() {
                return audioDetails;
            }

            public NetworkDetails getNetwork() {
                return networkDetails;
            }

            public DriversDetails getDrivers() {
                return driversDetails;
            }

            public PartitionDetails getPartition() {
                return partitionDetails;
            }

            public UsbDetails getUsb() {
                return usbDetails;
            }

            public SensorsDetails getSensors() {
                return sensorsDetails;
            }

            public RunningDetails getRunning() {
                return runningDetails;
            }

            public MonitorDetails getMonitor() {
                return monitorDetails;
            }

            public BiosDetails getBios() {
                return biosDetails;
            }

            public BaseboardDetails getBaseboard() {
                return baseboardDetails;
            }

            public ChassisDetails getChassis() {
                return chassisDetails;
            }

            public CacheDetails getCache() {
                return cacheDetails;
            }

            public ConnectorDetails getConnector() {
                return connectorDetails;
            }

            public DrivesDetails getDrives() {
                return drivesDetails;
            }

            public static class SystemDetails {

                public List<String> systemDetails;

                public SystemDetails(List<String> system) {
                    this.systemDetails = system;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.systemDetails) {
                        filter.add(details
                                .replaceAll("Kernel:", "kernel:")
                                .replaceAll("Desktop:", "desktop:")
                                .replaceAll("Distro:", "distro:"));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[0]);
                }
            }

            public static class MachineDetails {

                public List<String> machineDetails;

                public MachineDetails(List<String> machine) {
                    this.machineDetails = machine;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.machineDetails) {
                        filter.add(details
                                .replaceAll("Type:", "type:")
                                .replaceAll("System:", "system:")
                                .replaceFirst("serial:", "serial_"+n+":")
                                .replaceFirst(" v:", " version_"+n+":"));
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[1]);
                }
            }

            public static class BatteryDetails {

                public List<String> batteryDetails;

                public BatteryDetails(List<String> battery) {
                    this.batteryDetails = battery;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.batteryDetails) {
                        filter.add(details.replaceAll("ID-1:", "id1:"));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[2]);
                }
            }

            public static class MemoryDetails {

                public List<String> memoryDetails;

                public MemoryDetails(List<String> memory) {
                    this.memoryDetails = memory;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.memoryDetails) {
                        filter.add(details
                                .replaceAll("RAM: total:", "type: RAM total:")
                                .replaceAll("RAM Report: permissions:", "report: N/A permissions:"));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[3]);
                }
            }

            public static class SlotsDetails {

                public List<String> slotsDetails;

                public SlotsDetails(List<String> slot) {
                    this.slotsDetails = slot;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String slot : this.slotsDetails) {
                        filter.add(slot.replaceAll("Permissions: ", "permissions: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[4]);
                }
            }

            public static class ProcessorDetails {

                public List<String> processorDetails;

                public ProcessorDetails(List<String> processor) {
                    this.processorDetails = processor;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.processorDetails) {
                        filter.add(details
                                .replaceAll("Core speeds \\(MHz\\): 1:", "coreSpeeds: MHz 1:"));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[5]);
                }
            }

            public static class GraphicsDetails {

                public List<String> graphicsDetails;

                public GraphicsDetails(List<String> graphics) {
                    this.graphicsDetails = graphics;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.graphicsDetails) {
                        filter.add(details
                                .replaceAll("OpenGL: ", "openGL: ")
                                .replaceFirst(" v:", " version_"+n+":")
                                .replaceFirst("driver: ", "driver_"+n+": "));
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[6]);
                }
            }

            public static class AudioDetails {

                public List<String> audioDetails;

                public AudioDetails(List<String> audio) {
                    this.audioDetails = audio;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.audioDetails) {
                        filter.add(details
                                .replaceAll("Sound Server: ", "soundServer: ")
                                .replaceFirst(" v:", " version_"+n+":")
                                .replaceAll("bus ID: ", "busId: ")
                                .replaceFirst("vendor: ", "vendor_"+n+": ")
                                .replaceFirst("driver: ", "driver_"+n+": ")
                                .replaceFirst("busId: ", "busId_"+n+": "));
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[7]);
                }
            }

            public static class NetworkDetails {

                public List<String> networkDetails;

                public NetworkDetails(List<String> network) {
                    this.networkDetails = network;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.networkDetails) {
                        filter.add(details
                                .replaceAll("bus ID: ", "busId: ")
                                .replaceFirst(" v:", " version_"+n+":")
                                .replaceFirst("driver: ", "driver_"+n+": ")
                                .replaceFirst("port: ", "port_"+n+": ")
                                .replaceFirst("busId: ", "busId_"+n+": ")
                                .replaceFirst("IF: ", "IF_"+n+": ")
                                .replaceFirst("state: ", "state_"+n+": ")
                                .replaceFirst("mac: ", "mac_"+n+": "));
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[8]);
                }
            }

            public static class DriversDetails {

                public List<String> driversDetails;

                public DriversDetails(List<String> drivers) {
                    this.driversDetails = drivers;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.driversDetails) {
                        filter.add(details.replaceAll("DRIVERS: ", "drivers: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[9]);
                }
            }

            public static class PartitionDetails {

                public List<String> partitionDetails;

                public PartitionDetails(List<String> partition) {
                    this.partitionDetails = partition;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.partitionDetails) {
                        filter.add(details.replaceAll("fs: ", "fileSystem: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[10]);
                }
            }

            public static class UsbDetails {

                public List<String> UsbDetails;

                public UsbDetails(List<String> usb) {
                    this.UsbDetails = usb;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.UsbDetails) {
                        filter.add(details
                                .replaceAll("(Device-[0-9]): ([0-9]-[0-9]):([0-9])", "$1: $2.$3")
                                .replaceFirst("info: ", "info_"+n+": ")
                                .replaceFirst("rev: ", "rev_"+n+": ")
                                .replaceFirst("Hub: ", "hub_"+n+": ")
                                .replaceFirst("type: ", "type_"+n+": ")
                                .replaceFirst("driver: ", "driver_"+n+": ")
                                .replaceFirst("ports: ", "ports_"+n+": "));
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[11]);
                }
            }

            public static class SensorsDetails {

                public List<String> sensorsDetails;

                public SensorsDetails(List<String> sensors) {
                    this.sensorsDetails = sensors;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.sensorsDetails) {
                        filter.add(details
                                .replaceAll("System Temperatures: ", "systemTemperatures: ºC ")
                                .replaceAll("Fan Speeds \\(RPM\\): ", "fanSpeedsRPM: ")
                        );
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[12]);
                }
            }

            public static class RunningDetails {

                public List<String> runningDetails;

                public RunningDetails(List<String> runningDetails) {
                    this.runningDetails = runningDetails;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    int n = 0;
                    for (String details : this.runningDetails) {
                        filter.add(details
                                .replaceAll("Compilers: ", "compilers: N/A ")
                                .replaceFirst("Client: ", "client_"+n+": ")
                        );
                        n++;
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[13]);
                }
            }

            public static class MonitorDetails {

                public List<String> monitorDetails;

                public MonitorDetails(List<String> monitorDetails) {
                    this.monitorDetails = monitorDetails;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String monitor : this.monitorDetails) {
                        filter.add(monitor.replaceAll("MONITOR: ", "monitor: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[14]);
                }
            }

            public static class BiosDetails {

                public List<String> biosDetails;

                public BiosDetails(List<String> bios) {
                    this.biosDetails = bios;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String bios : this.biosDetails) {
                        filter.add(bios.replaceAll("BIOS: ", "bios: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[15]);
                }
            }

            public static class BaseboardDetails {

                public List<String> baseboardDetails;

                public BaseboardDetails(List<String> baseboard) {
                    this.baseboardDetails = baseboard;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String baseboard : this.baseboardDetails) {
                        filter.add(baseboard.replaceAll("BASEBOARD: ", "baseboard: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[16]);
                }
            }

            public static class ChassisDetails {

                public List<String> chassisDetails;

                public ChassisDetails(List<String> chassis) {
                    this.chassisDetails = chassis;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String details : this.chassisDetails) {
                        filter.add(details.replaceAll("CHASSIS: ", "chassis: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[17]);
                }
            }

            public static class CacheDetails {

                public List<String> cacheDetails;

                public CacheDetails(List<String> cache) {
                    this.cacheDetails = cache;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String cache : this.cacheDetails) {
                        filter.add(cache.replaceAll("CACHE: ", "cache: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[18]);
                }
            }

            public static class ConnectorDetails {

                public List<String> connectorDetails;

                public ConnectorDetails(List<String> connector) {
                    this.connectorDetails = connector;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String connector : this.connectorDetails) {
                        filter.add(connector.replaceAll("CONNECTOR: ", "connector: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[19]);
                }
            }

            public static class DrivesDetails {

                public List<String> drivesDetails;

                public DrivesDetails(List<String> drives) {
                    this.drivesDetails = drives;
                }

                public String getDetails() {
                    List<String> filter = new ArrayList<>();
                    for (String drives : this.drivesDetails) {
                        filter.add(drives.replaceAll("Local Storage: ", "localStorage: "));
                    }
                    return jsonCreatorRFC8259(filter, INFO_INDEX[20]);
                }
            }
        }

        public enum AvailableCommands {
            INXI("inxi -Fxz " +
                    "--slots " +
                    "--memory " +
                    "--cpu " +
                    "--disk-full " +
                    "--graphics " +
                    "--machine " +
                    "--network " +
                    "--sensors " +
                    "--system " +
                    "--usb " +
                    "--info " +
                    "--color"),
            HWINFO("hwinfo --short"),
            LSHW("lshw -short"),
            LSCPU("lscpu"),
            LSCPU2("lshw -C cpu"),
            DMIDECODE("dmidecode"),
            SYSTEMINFO_WINDOWS("systeminfo");

            private final String cmd;

            AvailableCommands(String cmd) {
                this.cmd = cmd;
            }

            private String getCmd() {
                return cmd;
            }

            public static String sysCmd(AvailableCommands cmd) {
                return cmd.getCmd();
            }
        }
    }
}
