package com.huntercodexs.demo.system.hardsys.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysSystem extends Help4DevsHardSysBase {

    private final List<String> array;

    public Help4DevsHardSysSystem(HashMap<String, List<String>> resources) {
        this.array = new ArrayList<>();
        this.resources = resources;
    }

    private void systemInfo() {
        this.array.add("type: system osName: "+System.getProperty("os.name"));
        this.array.add("type: system osArch: "+System.getProperty("os.arch"));
        this.array.add("type: system osVersion: "+System.getProperty("os.version"));
        this.array.add("type: system fileSeparator: "+System.getProperty("file.separator"));
        this.array.add("type: system pathSeparator: "+System.getProperty("path.separator"));
        this.array.add("type: system lineSeparator: "+System.getProperty("line.separator").replaceAll("\n", "\\\\n"));
        this.array.add("type: system userName: "+System.getProperty("user.name"));
        this.array.add("type: system userHome: "+System.getProperty("user.home"));
        this.array.add("type: system userDir: "+System.getProperty("user.dir"));
    }

    private void javaInfo() {
        //The version of Java Runtime Environment.
        this.array.add("type: system javaVersion: " + System.getProperty("java.version"));
        //The name of Java Runtime Environment vendor
        this.array.add("type: system javaVendor: " + System.getProperty("java.vendor"));
        //The URL of Java vendor
        this.array.add("type: system javaVendorUrl: " + System.getProperty("java.vendor.url"));
        //The directory of Java installation
        this.array.add("type: system javaHome: " + System.getProperty("java.home"));
        //The name of specification version Java Runtime Environment
        this.array.add("type: system javaSpecVersion: " + System.getProperty("java.specification.version"));
        // JRE specification vendor
        this.array.add("type: system javaSpecVendor: " + System.getProperty("java.specification.vendor"));
        //JRE specification name
        this.array.add("type: system javaSpecName: " + System.getProperty("java.specification.name"));
        //Java class format version number
        this.array.add("type: system javaClassVersion: " + System.getProperty("java.class.version"));
        //Path of java class
        this.array.add("type: system javaClassPath: " + System.getProperty("java.class.path"));
        //List of paths to search when loading libraries
        this.array.add("type: system javaLibPath: " + System.getProperty("java.library.path"));
        //The path of temp file
        this.array.add("type: system javaTmpDir: " + System.getProperty("java.io.tmpdir"));
        //The Name of JIT compiler to use
        this.array.add("type: system javaCompiler: " + System.getProperty("java.compiler"));
        //The path of extension directory or directories
        this.array.add("type: system javaExtDir: " + System.getProperty("java.ext.dirs"));
    }

    private void javaVmInfo() {
        this.array.add("type: system javaVmSpecVersion: "+System.getProperty("java.vm.specification.version"));
        this.array.add("type: system javaVmSpecVendor: "+System.getProperty("java.vm.specification.vendor"));
        this.array.add("type: system javaVmSpecName: "+System.getProperty("java.vm.specification.name"));
        this.array.add("type: system javaVmVersion: "+System.getProperty("java.vm.version"));
        this.array.add("type: system javaVmVendor: "+System.getProperty("java.vm.vendor"));
        this.array.add("type: system javaVmName: "+System.getProperty("java.vm.name"));
    }

    private void othersInfo() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("uname -a");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            //Distro
            this.array.add("type: system osDistro: "+
                    stringExtractor(output.toUpperCase(), "", osTypePattern, "$1", 1));

            //Date
            this.array.add("type: system osDate: "+
                    stringExtractor(output, "", datePattern, "$1", 1));

            //Vendor
            this.array.add("type: system osVendor: "+
                    stringExtractor(output.toUpperCase(), "", osVendorsPattern, "$1", 1));

            //Kernel
            this.array.add("type: system osKernel: "+
                    stringExtractor(output, "", "([-0-9.]+generic)", "$1", 1));

            //Description
            this.array.add("type: system osDescription: "+output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        systemInfo();
        javaInfo();
        javaVmInfo();
        othersInfo();
        this.resources.put(hardsys("system"), this.array);
    }
}
