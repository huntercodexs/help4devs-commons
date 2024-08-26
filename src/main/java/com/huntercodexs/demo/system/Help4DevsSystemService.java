package com.huntercodexs.demo.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsBaseService.*;

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
                return "TimerInfoMetrics[" +
                        "decimals=" + decimals +
                        ", start=" + start +
                        ", current=" + current +
                        ", end=" + end +
                        ", total=" + total +
                        ", timerSpec=" + timerSpec +
                        "]";
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
                return "JavaInfoMetrics[" +
                        "javaVersion=" + javaVersion +
                        ", javaVendor=" + javaVendor +
                        ", javaVendorUrl=" + javaVendorUrl +
                        ", javaHome=" + javaHome +
                        ", javaSpecVersion=" + javaSpecVersion +
                        ", javaSpecVendor=" + javaSpecVendor +
                        ", javaSpecName=" + javaSpecName +
                        ", javaClassVersion=" + javaClassVersion +
                        ", javaClassPath=" + javaClassPath +
                        ", javaLibPath=" + javaLibPath +
                        ", javaIoTmpDir=" + javaIoTmpDir +
                        ", javaCompiler=" + javaCompiler +
                        ", javaExtraDirs=" + javaExtraDirs +
                        "]";
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
                return "MemoryInfoMetrics[" +
                        "total=" + total +
                        ", start=" + start +
                        ", current=" + current +
                        ", end=" + end +
                        ", free=" + free +
                        ", used=" + used +
                        ", memorySpec=" + memorySpec +
                        "]";
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
                return "HardDriveInfoMetrics[" +
                        "absPath=" + absPath +
                        ", total=" + total +
                        ", free=" + free +
                        ", used=" + used +
                        "]";
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
                return "ProcessorInfoMetrics[" +
                        "arch=" + arch +
                        ", name=" + name +
                        ", vendor=" + vendor +
                        ", cores=" + cores +
                        ", cpus=" + cpus +
                        ", mhz=" + mhz +
                        ", virtualization=" + virtualization +
                        ", l1Cache=" + l1Cache +
                        ", l2Cache=" + l2Cache +
                        ", l3Cache=" + l3Cache +
                        "]";
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
                return "JVirtualMachineInfoMetrics[" +
                        "javaSpecificationVersion=" + javaSpecificationVersion +
                        ", javaVMSpecificationVendor=" + javaVMSpecificationVendor +
                        ", javaVMSpecificationName=" + javaVMSpecificationName +
                        ", javaVMVersion=" + javaVMVersion +
                        ", javaVMVendor=" + javaVMVendor +
                        ", javaVMName=" + javaVMName +
                        "]";
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
                return "OperationSystemInfoMetrics[" +
                        "osName=" + osName +
                        ", osArch=" + osArch +
                        ", osVersion=" + osVersion +
                        ", osFileSeparator=" + osFileSeparator +
                        ", osPathSeparator=" + osPathSeparator +
                        ", osLineBreak=" + osLineBreak +
                        ", userName=" + userName +
                        ", userHome=" + userHome +
                        ", userDir=" + userDir +
                        "]";
            }
        }
    }
}
