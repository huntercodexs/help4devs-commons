package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.*;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsBatteryDto.Help4DevsBattery;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsGraphicsDto.Help4DevsGraphics;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMachineDto.Help4DevsMachine;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMemoryDto.Help4DevsMemory;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto.Help4DevsProcessor;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsSlotsDto.Help4DevsSlots;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsSystemDto.Help4DevsSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.*;
import static com.huntercodexs.demo.services.core.Help4DevsJavaCoreService.listNormalize;

public class Help4DevsHardSysInxiFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysInxiFactory(HashMap<String, List<String>> resources, HashMap<String, Object> transport) {
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
        system.setJavaClassPath(items.get(18).replaceAll("javaLibPath:", "").trim());
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

        System.out.println("==========[system]> " + this.transport.get(hardsysCheck("system")));

    }

    private void machineFactory(List<String> items) {

        Help4DevsMachineDto machineDto = new Help4DevsMachineDto();
        machineDto.setQty("1");

        Help4DevsMachine machine = new Help4DevsMachine();

        machine.setType(alphaFieldPattern(items.get(0), "Type", null));
        machine.setName(alphaFieldPattern(items.get(0), "System", null));
        machine.setProduct(alphaFieldPattern(items.get(0), "product", " "));
        machine.setVersion(alphaFieldPattern(items.get(0), "v", null));
        machine.setSerial(alphaFieldPattern(items.get(0), "serial", "><"));

        machine.setVendor(stringExtractor(
                items.get(0).toUpperCase(),
                "",
                vendorsPattern,
                "$1",
                1));

        machine.setFamily(stringExtractor(
                items.get(0).toUpperCase(),
                "",
                productFamilyPattern,
                "$1",
                1));

        machine.setMobo(alphaFieldPattern(items.get(0), "Mobo", null));
        machine.setModel(alphaFieldPattern(items.get(0), "model", null));
        machine.setUefi(alphaFieldPattern(items.get(0), "UEFI", null));

        machine.setDate(alphaFieldPattern(items.get(0), "date", "/"));

        machine.setDescription(machine.getType()+" "+machine.getName()+" "+machine.getProduct());

        machineDto.addMachine(machine);

        this.transport.put(hardsysCheck("machine"), machineDto);

        System.out.println("==========[machine]> " + this.transport.get(hardsysCheck("machine")));

    }

    private void batteryFactory(List<String> items) {

        Help4DevsBatteryDto batteryDto = new Help4DevsBatteryDto();
        batteryDto.setQty("1");

        Help4DevsBattery battery = new Help4DevsBattery();

        battery.setId(alphaFieldPattern(items.get(0), "ID-1", null));
        battery.setCharge(alphaFieldPattern(items.get(0), "charge", " "));
        battery.setCondition(alphaFieldPattern(items.get(0), "condition", "/"));
        battery.setName(alphaFieldPattern(items.get(0), "model", " "));
        battery.setType(alphaFieldPattern(items.get(0), "model", " "));
        battery.setStatus(alphaFieldPattern(items.get(0), "status", null));

        batteryDto.addBattery(battery);

        this.transport.put(hardsysCheck("battery"), batteryDto);

        System.out.println("==========[battery]> " + this.transport.get(hardsysCheck("battery")));
    }

    private void memoryFactory(List<String> items) {

        Help4DevsMemoryDto memoryDto = new Help4DevsMemoryDto();
        memoryDto.setQty("unknown");

        Help4DevsMemory memory = new Help4DevsMemory();

        memory.setTotal(alphaFieldPattern(items.get(0), "total", " "));
        memory.setUsed(alphaFieldPattern(items.get(0), "used", " "));
        memory.setDescription(alphaFieldPattern(items.get(0), "permissions", " "));
        memory.setType("RAM");

        memoryDto.addMemory(memory);

        this.transport.put(hardsysCheck("memory"), memoryDto);

        System.out.println("==========[memory]> " + this.transport.get(hardsysCheck("memory")));

    }

    private void slotsFactory(List<String> items) {

        Help4DevsSlotsDto slotsDto = new Help4DevsSlotsDto();
        slotsDto.setQty("unknown");

        Help4DevsSlots slots = new Help4DevsSlots();

        slots.setDescription(alphaFieldPattern(items.get(0), "Permissions", " "));

        slotsDto.addSlot(slots);

        this.transport.put(hardsysCheck("slots"), slotsDto);

        System.out.println("==========[slots]> " + this.transport.get(hardsysCheck("slots")));
    }

    private void processorFactory(List<String> items) {

        Help4DevsProcessorDto processorDto = new Help4DevsProcessorDto();
        processorDto.setCores(String.valueOf(stringCounter(items.get(0), "[1-9]: ([0-9]{4})")));
        processorDto.setName(alphaFieldPattern(items.get(0), "model", " "));

        Help4DevsProcessor processor = new Help4DevsProcessor();

        processor.setTopology(alphaFieldPattern(items.get(0), "Topology", " "));
        processor.setModel(alphaFieldPattern(items.get(0), "model", " "));
        processor.setBits(alphaFieldPattern(items.get(0), "bits", null));
        processor.setType(alphaFieldPattern(items.get(0), "type", " "));
        processor.setArch(alphaFieldPattern(items.get(0), "arch", " "));
        processor.setCache(alphaFieldPattern(items.get(0), "cache", " "));
        processor.setFlags(alphaFieldPattern(items.get(0), "flags", " "));
        processor.setBogomips(alphaFieldPattern(items.get(0), "bogomips", " "));
        processor.setSpeed(alphaFieldPattern(items.get(0), "Speed", " "));

        processor.setMinSpeed(
                alphaFieldPattern(
                        items.get(0), "min/max", " ")
                        .replaceAll("Core speeds", ""));

        processor.setMaxSpeed(
                alphaFieldPattern(
                        items.get(0), "min/max", " ")
                        .replaceAll("Core speeds", ""));

        processor.setCharacteristics(
                "Core Speeds - "+stringExtractor(
                items.get(0).toUpperCase(),
                "",
                "([1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4})",
                "$1",
                1).replaceAll(" ", ": "));

        processor.setFamily(stringExtractor(
                items.get(0).toUpperCase(),
                "",
                processorFamilyPattern,
                "$1",
                1));

        processorDto.addProcessor(processor);

        this.transport.put(hardsysCheck("processor"), processorDto);

        System.out.println("==========[processor]> " + this.transport.get(hardsysCheck("processor")));

    }

    private void graphicsFactory(List<String> items) {

        List<List<String>> cleanup = new ArrayList<>();

        List<String> toClean1 = new ArrayList<>();
        toClean1.add("bus ID");
        toClean1.add("busId");
        cleanup.add(toClean1);

        List<String> toClean2 = new ArrayList<>();
        toClean2.add("direct render");
        toClean2.add("directRender");
        cleanup.add(toClean2);

        List<String> listFixed = listNormalize(items, "Device-", cleanup);

        Help4DevsGraphicsDto graphicsDto = new Help4DevsGraphicsDto();
        graphicsDto.setQty(String.valueOf(listFixed.size()));

        int index = 1;
        for (String item : listFixed) {
            Help4DevsGraphics graphics = new Help4DevsGraphics();

            graphics.setName(alphaFieldPattern(item, "Device-"+index, " "));
            graphics.setVendor(alphaFieldPattern(item, "vendor", " "));
            graphics.setDriver(alphaFieldPattern(item, "driver", ""));
            graphics.setVersion(alphaFieldPattern(item, "v", ""));
            graphics.setId(alphaFieldPattern(item, "busId", ""));

            graphicsDto.addGraphic(graphics);
            index += 1;
        }

        this.transport.put(hardsysCheck("graphics"), graphicsDto);

        System.out.println("==========[graphics]> " + this.transport.get(hardsysCheck("graphics")));

    }

    private void audioFactory(List<String> items) {}

    private void networkFactory(List<String> items) {}

    private void diskFactory(List<String> items) {}

    private void storageFactory(List<String> items) {}

    private void partitionFactory(List<String> items) {}

    private void usbFactory(List<String> items) {}

    private void sensorsFactory(List<String> items) {}

    private void runningFactory(List<String> items) {}

    private void printerFactory(List<String> items) {}

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

                    String value = item
                            .replaceAll("\\[", "(")
                            .replaceAll("]", ")")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(value);
                    this.resources.put(key, makeList);

                }

            }

        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }

        systemFactory(this.resources.get(hardsysCheck("system")));
        machineFactory(this.resources.get(hardsysCheck("machine")));
        batteryFactory(this.resources.get(hardsysCheck("battery")));
        memoryFactory(this.resources.get(hardsysCheck("memory")));
        slotsFactory(this.resources.get(hardsysCheck("slots")));
        processorFactory(this.resources.get(hardsysCheck("processor")));
        graphicsFactory(this.resources.get(hardsysCheck("graphics")));
        audioFactory(this.resources.get(hardsysCheck("audio")));
        networkFactory(this.resources.get(hardsysCheck("network")));
        diskFactory(this.resources.get(hardsysCheck("disk")));
        storageFactory(this.resources.get(hardsysCheck("storage")));
        partitionFactory(this.resources.get(hardsysCheck("partition")));
        usbFactory(this.resources.get(hardsysCheck("usb")));
        sensorsFactory(this.resources.get(hardsysCheck("sensors")));
        runningFactory(this.resources.get(hardsysCheck("running")));
        printerFactory(this.resources.get(hardsysCheck("printer")));

    }

}
