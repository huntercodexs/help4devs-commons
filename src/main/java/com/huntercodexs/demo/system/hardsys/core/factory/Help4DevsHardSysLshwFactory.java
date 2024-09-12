package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.*;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsBridgeDto.Help4DevsBridge;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsControllerDto.Help4DevsController;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsDiskDto.Help4DevsDisk;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsGraphicsDto.Help4DevsGraphics;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsKeyboardDto.Help4DevsKeyboard;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMachineDto.Help4DevsMachine;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMemoryDto.Help4DevsMemory;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMouseDto.Help4DevsMouse;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMultimediaDto.Help4DevsMultimedia;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsPartitionDto.Help4DevsPartition;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto.Help4DevsProcessor;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsStorageDto.Help4DevsStorage;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsSystemDto.Help4DevsSystem;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsUnknownDto.Help4DevsUnknown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.alphaFieldPattern;
import static com.huntercodexs.demo.services.basic.Help4DevsStringHandlerService.stringExtractor;

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

    private void processorFactory(List<String> items) {

        Help4DevsProcessorDto processorDto = new Help4DevsProcessorDto();
        processorDto.setCores("1");
        processorDto.setName(alphaFieldPattern(
                items.get(0).replaceAll("\\(processor\\)", ""),
                "description",
                " "));

        Help4DevsProcessor processor = new Help4DevsProcessor();

        processor.setSpeed(stringExtractor(
                items.get(0).toUpperCase(),
                "",
                "([0-9]+\\.[0-9]+[0-9]+[GM]HZ)",
                "$1",
                1));

        processor.setFamily(stringExtractor(
                items.get(0).toUpperCase(), "", processorFamilyPattern, "$1", 1));

        processor.setManufacturer(stringExtractor(
                items.get(0).toUpperCase(), "description", vendorsPattern, "description:$1", 1));

        processor.setModel(stringExtractor(
                items.get(0).toUpperCase(), "model", processorModelPattern, "model:$1$2", 1));

        processor.setManufacturer(stringExtractor(
                items.get(0).toUpperCase(), "description", vendorsPattern, "description:$1", 1));

        processorDto.addProcessor(processor);

        this.transport.put(hardsysCheck("processor"), processorDto);

    }

    private void graphicsFactory(List<String> items) {

        Help4DevsGraphicsDto graphicsDto = new Help4DevsGraphicsDto();
        graphicsDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            item = item
                    .replaceAll("([Nn][vV][iI][Dd][iI][Aa])", "")
                    .replaceAll("(G[eE]-?F[oO][rR][cC][eE])", "NVIDIA $1");

            Help4DevsGraphics graphics = new Help4DevsGraphics();

            graphics.setId(String.format("%06d", id+1));
            graphics.setName(alphaFieldPattern(
                    item.replaceAll("\\(display\\)", ""),
                    "description",
                    " "));

            graphics.setSource(alphaFieldPattern(item, "source", " "));

            graphics.setVendor(stringExtractor(
                    item.toUpperCase(), "type", vendorsPattern, "type:$1", id));

            graphics.setSource(stringExtractor(
                    item, "", videoTypePattern, "$1", id));

            graphics.setDescription(alphaFieldPattern(item, "description", " "));

            graphicsDto.addGraphic(graphics);
            id++;

        }

        this.transport.put(hardsysCheck("graphics"), graphicsDto);

    }

    private void networkFactory(List<String> items) {

        Help4DevsNetworkDto networkDto = new Help4DevsNetworkDto();
        networkDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            item = item.replaceAll("(RTL[0-9]+)", "REALTEK $1");

            Help4DevsNetworkDto.Help4DevsNetwork network = new Help4DevsNetworkDto.Help4DevsNetwork();

            network.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            network.setType(stringExtractor(
                    item.toUpperCase(), "", networkTypePattern, "$1", id));

            network.setName(alphaFieldPattern(item, "description", " "));
            network.setSource(alphaFieldPattern(item, "source", " "));
            network.setId(alphaFieldPattern(item, "device", " "));

            networkDto.addNetwork(network);
            id++;
        }

        this.transport.put(hardsysCheck("network"), networkDto);

    }

    private void partitionFactory(List<String> items) {

        Help4DevsPartitionDto partitionDto = new Help4DevsPartitionDto();
        partitionDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsPartition partition = new Help4DevsPartition();
            partition.setId(String.format("%06d", id+1));

            partition.setName(alphaFieldPattern(item, "description", " "));
            partition.setSource(alphaFieldPattern(item, "source", " "));
            partition.setDev(alphaFieldPattern(item, "device", " "));
            partition.setDescription(alphaFieldPattern(item, "description", " "));

            partitionDto.addPartition(partition);
            id++;
        }

        this.transport.put(hardsysCheck("partition"), partitionDto);

    }

    private void keyboardFactory(List<String> items) {

        Help4DevsKeyboardDto keyboardDto = new Help4DevsKeyboardDto();
        keyboardDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsKeyboard keyboard = new Help4DevsKeyboard();
            keyboard.setId(String.format("%06d", id+1));

            keyboard.setName(alphaFieldPattern(item, "description", " "));
            keyboard.setSource(alphaFieldPattern(item, "source", " "));
            keyboard.setDescription(alphaFieldPattern(item, "description", " "));

            keyboardDto.addKeyboard(keyboard);
            id++;
        }

        this.transport.put(hardsysCheck("keyboard"), keyboardDto);

    }

    private void mouseFactory(List<String> items) {

        Help4DevsMouseDto mouseDto = new Help4DevsMouseDto();
        mouseDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsMouse mouse = new Help4DevsMouse();
            mouse.setId(String.format("%06d", id+1));

            mouse.setName(alphaFieldPattern(item, "description", " "));
            mouse.setSource(alphaFieldPattern(item, "source", " "));
            mouse.setDescription(alphaFieldPattern(item, "description", " "));

            mouseDto.addMouse(mouse);
            id++;
        }

        this.transport.put(hardsysCheck("mouse"), mouseDto);

    }

    private void diskFactory(List<String> items) {

        Help4DevsDiskDto disksDto = new Help4DevsDiskDto();
        disksDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsDisk disk = new Help4DevsDisk();
            disk.setId(String.format("%06d", id+1));

            disk.setName(alphaFieldPattern(item, "description", " "));
            disk.setSource(alphaFieldPattern(item, "source", " "));
            disk.setPartition(alphaFieldPattern(item, "device", " "));
            disk.setDescription(alphaFieldPattern(item, "description", " "));

            disk.setType(stringExtractor(
                    item.toUpperCase(), "", storageTypePattern, "$1", id));

            disk.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            disksDto.addDisk(disk);
            id++;
        }

        this.transport.put(hardsysCheck("disk"), disksDto);
    }

    private void storageFactory(List<String> items) {

        Help4DevsStorageDto storageDto = new Help4DevsStorageDto();
        storageDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsStorage storage = new Help4DevsStorage();
            storage.setId(String.format("%06d", id+1));

            storage.setName(alphaFieldPattern(item, "description", " "));
            storage.setSource(alphaFieldPattern(item, "source", " "));
            storage.setPartition(alphaFieldPattern(item, "device", " "));
            storage.setDescription(alphaFieldPattern(item, "description", " "));

            storage.setType(stringExtractor(
                    item.toUpperCase(), "", storageTypePattern, "$1", id));

            storage.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            storageDto.addStorage(storage);
            id++;
        }

        this.transport.put(hardsysCheck("storage"), storageDto);
    }

    private void bridgeFactory(List<String> items) {

        Help4DevsBridgeDto bridgeDto = new Help4DevsBridgeDto();
        bridgeDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsBridge bridge = new Help4DevsBridge();
            bridge.setId(String.format("%06d", id+1));

            bridge.setName(alphaFieldPattern(item, "description", " "));
            bridge.setSource(alphaFieldPattern(item, "source", " "));
            bridge.setDescription(alphaFieldPattern(item, "description", " "));

            bridge.setType(stringExtractor(
                    item.toUpperCase(), "", bridgeTypePattern, "$1", id));

            bridge.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            bridgeDto.addBridge(bridge);
            id++;
        }

        this.transport.put(hardsysCheck("bridge"), bridgeDto);

    }

    private void unknownFactory(List<String> items) {

        Help4DevsUnknownDto unknownDto = new Help4DevsUnknownDto();
        unknownDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsUnknown unknown = new Help4DevsUnknown();
            unknown.setId(String.format("%06d", id+1));

            unknown.setName(alphaFieldPattern(item, "description", " "));
            unknown.setSource(alphaFieldPattern(item, "source", " "));
            unknown.setDescription(alphaFieldPattern(item, "description", " "));

            unknown.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            unknownDto.addUnknown(unknown);
            id++;
        }

        this.transport.put(hardsysCheck("unknown"), unknownDto);

    }

    private void controllerFactory(List<String> items) {

        Help4DevsControllerDto controllerDto = new Help4DevsControllerDto();
        controllerDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsController controller = new Help4DevsController();
            controller.setId(String.format("%06d", id+1));

            controller.setName(alphaFieldPattern(item, "description", " "));
            controller.setSource(alphaFieldPattern(item, "source", " "));
            controller.setDescription(alphaFieldPattern(item, "description", " "));

            controller.setType(stringExtractor(
                    item.toUpperCase(), "", controllerTypePattern, "$1", id));

            controller.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            controllerDto.addController(controller);
            id++;
        }

        this.transport.put(hardsysCheck("controller"), controllerDto);

    }

    private void multimediaFactory(List<String> items) {

        Help4DevsMultimediaDto multimediaDto = new Help4DevsMultimediaDto();
        multimediaDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsMultimedia multimedia = new Help4DevsMultimedia();
            multimedia.setId(String.format("%06d", id+1));

            multimedia.setName(alphaFieldPattern(item, "description", " "));
            multimedia.setSource(alphaFieldPattern(item, "source", " "));
            multimedia.setDescription(alphaFieldPattern(item, "description", " "));

            multimediaDto.addMultimedia(multimedia);
            id++;
        }

        this.transport.put(hardsysCheck("multimedia"), multimediaDto);

    }

    private void machineFactory(List<String> items) {

        Help4DevsMachineDto machineDto = new Help4DevsMachineDto();
        machineDto.setQty(String.valueOf(items.size()));

        int id = 0;
        for (String item : items) {

            Help4DevsMachine machine = new Help4DevsMachine();

            machine.setName(alphaFieldPattern(item, "description", " "));
            machine.setDescription(alphaFieldPattern(item, "description", " "));

            machine.setVendor(stringExtractor(
                    item.toUpperCase(), "", vendorsPattern, "$1", id));

            machineDto.addMachine(machine);
            id++;
        }

        this.transport.put(hardsysCheck("machine"), machineDto);
    }

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
