package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMemoryDto;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMemoryDto.Help4DevsMemory;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsSystemDto;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsSystemDto.Help4DevsSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.alphaFieldPattern;

public class Help4DevsHardSysLshwFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysLshwFactory(HashMap<String, List<String>> resources, HashMap<String, Object> transport) {
        this.resources = resources;
        this.transport = transport;
    }

    private void systemFactory(List<String> items) {

        Help4DevsSystemDto systemDto = new Help4DevsSystemDto();
        systemDto.setQty("1");

        Help4DevsSystem system = new Help4DevsSystem();

        system.setName(items.get(0).replaceAll("osName:", "").trim());
        system.setArch(items.get(1).replaceAll("osArch:", "").trim());
        system.setVersion(items.get(2).replaceAll("osVersion:", "").trim());
        system.setFileSeparator(items.get(3).replaceAll("fileSeparator:", "").trim());
        system.setPathSeparator(items.get(4).replaceAll("pathSeparator:", "").trim());
        system.setLineSeparator(items.get(5).replaceAll("lineSeparator:", "").trim());
        system.setUsername(items.get(6).replaceAll("userName:", "").trim());
        system.setHome(items.get(7).replaceAll("userHome:", "").trim());
        system.setUserdir(items.get(8).replaceAll("userDir:", "").trim());
        system.setJavaVersion(items.get(9).replaceAll("javaVersion:", "").trim());
        system.setJavaVendor(items.get(10).replaceAll("javaVendor:", "").trim());
        system.setJavaVendorUrl(items.get(11).replaceAll("javaVendorUrl:", "").trim());
        system.setJavaHome(items.get(12).replaceAll("javaHome:", "").trim());
        system.setJavaSpecVersion(items.get(13).replaceAll("javaSpecVersion:", "").trim());
        system.setJavaSpecVendor(items.get(14).replaceAll("javaSpecVendor:", "").trim());
        system.setJavaSpecName(items.get(15).replaceAll("javaSpecName:", "").trim());
        system.setJavaClassVersion(items.get(16).replaceAll("javaClassVersion:", "").trim());
        system.setJavaClassPath(items.get(17).replaceAll("javaClassPath:", "").trim());
        system.setJavaLibPath(items.get(18).replaceAll("javaLibPath:", "").trim());
        system.setJavaTmpDir(items.get(19).replaceAll("javaTmpDir:", "").trim());
        system.setJavaCompiler(items.get(20).replaceAll("javaCompiler:", "").trim());
        system.setJavaExtDir(items.get(21).replaceAll("javaExtDir:", "").trim());
        system.setJavaVmSpecVersion(items.get(22).replaceAll("javaVmSpecVersion:", "").trim());
        system.setJavaVmSpecVendor(items.get(23).replaceAll("javaVmSpecVendor:", "").trim());
        system.setJavaVmSpecName(items.get(24).replaceAll("javaVmSpecName:", "").trim());
        system.setJavaVmVersion(items.get(25).replaceAll("javaVmVersion:", "").trim());
        system.setJavaVmVendor(items.get(26).replaceAll("javaVmVendor:", "").trim());
        system.setJavaVmName(items.get(27).replaceAll("javaVmName:", "").trim());
        system.setDistro(items.get(28).replaceAll("osDistro:", "").trim());
        system.setDate(items.get(29).replaceAll("osDate:", "").trim());
        system.setVendor(items.get(30).replaceAll("osVendor:", "").trim());
        system.setKernel(items.get(31).replaceAll("osKernel:", "").trim());
        system.setDescription(items.get(32).replaceAll("osDescription:", "").trim());

        systemDto.addSystem(system);

        this.transport.put(hardsysCheck("system"), systemDto);
    }

    private void memoryFactory(List<String> items) {

        Help4DevsMemoryDto memoryDto = new Help4DevsMemoryDto();
        memoryDto.setQty("unknown");

        Help4DevsMemory memory = new Help4DevsMemory();

        memory.setSource(
                alphaFieldPattern(items.get(0), "source", " ")
                +", "+
                alphaFieldPattern(items.get(1), "source", " ")
        );
        memory.setTotal(alphaFieldPattern(items.get(0), "description", " "));
        memory.setDescription(alphaFieldPattern(items.get(1), "description", " "));

        memoryDto.addMemory(memory);

        this.transport.put(hardsysCheck("memory"), memoryDto);

    }

    private void processorFactory(List<String> items) {}

    private void graphicsFactory(List<String> items) {}

    private void networkFactory(List<String> items) {}

    private void partitionFactory(List<String> items) {}

    private void keyboardFactory(List<String> items) {}

    private void mouseFactory(List<String> items) {}

    private void diskFactory(List<String> items) {}

    private void storageFactory(List<String> items) {}

    private void bridgeFactory(List<String> items) {}

    private void unknownFactory(List<String> items) {}

    private void controllerFactory(List<String> items) {}

    private void multimediaFactory(List<String> items) {}

    private void machineFactory(List<String> items) {}

    /**
     * @implNote This method will convert the resources from a List object to a DTO object
     * to be used easier and understandably during the processing commute
     * */
    public void factory() {
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {

            List<String> makeList = new ArrayList<>();

            if (key.contains("Group") || key.contains("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;

                    String value = item.replaceAll("type: " + key, "");

                    makeList.add(value);
                    this.resources.put(key, makeList);

                }

            }

        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }

        systemFactory(this.resources.get(hardsysCheck("system")));
        memoryFactory(this.resources.get(hardsysCheck("memory")));
        processorFactory(this.resources.get(hardsysCheck("processor")));
        graphicsFactory(this.resources.get(hardsysCheck("graphics")));
        networkFactory(this.resources.get(hardsysCheck("network")));
        partitionFactory(this.resources.get(hardsysCheck("partition")));
        keyboardFactory(this.resources.get(hardsysCheck("keyboard")));
        mouseFactory(this.resources.get(hardsysCheck("mouse")));
        diskFactory(this.resources.get(hardsysCheck("disk")));
        storageFactory(this.resources.get(hardsysCheck("storage")));
        bridgeFactory(this.resources.get(hardsysCheck("bridge")));
        unknownFactory(this.resources.get(hardsysCheck("unknown")));
        controllerFactory(this.resources.get(hardsysCheck("controller")));
        multimediaFactory(this.resources.get(hardsysCheck("multimedia")));
        machineFactory(this.resources.get(hardsysCheck("machine")));

    }

}
