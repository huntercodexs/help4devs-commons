package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.*;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsAudioDto.Help4DevsAudio;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsBiosDto.Help4DevsBios;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsBluetoothDto.Help4DevsBluetooth;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsBridgeDto.Help4DevsBridge;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsDiskDto.Help4DevsDisk;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsGraphicsDto.Help4DevsGraphics;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsHubDto.Help4DevsHub;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsKeyboardDto.Help4DevsKeyboard;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMemoryDto.Help4DevsMemory;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMonitorDto.Help4DevsMonitor;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsMouseDto.Help4DevsMouse;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsNetworkDto.Help4DevsNetwork;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsNetworkInterfaceDto.Help4DevsNetworkInterface;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsPartitionDto.Help4DevsPartition;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto.Help4DevsProcessor;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsStorageDto.Help4DevsStorage;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsUnknownDto.Help4DevsUnknown;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsUsbDto.Help4DevsUsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @implNote This class only process for DTO format results
 * */
public class Help4DevsHardSysHwinfoFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfoFactory(HashMap<String, List<String>> resources, HashMap<String, Object> transport) {
        this.resources = resources;
        this.transport = transport;
    }

    private void processorFactory(List<String> items) {

        Help4DevsProcessorDto processorDto = new Help4DevsProcessorDto();
        processorDto.setCores(String.valueOf(items.size()));

        String itemClear = items.get(0).replaceAll("type: processor source: ", "");
        processorDto.setName(itemClear.replaceAll(",", ""));

        int id = 0;
        for (String item : items) {

            Help4DevsProcessor processor = new Help4DevsProcessor();

            processor.setCore(String.valueOf(id));

            itemClear = item
                    .replaceAll("type: processor source: ", "")
                    .toUpperCase();

            processor.setManufacturer(stringExtractor(
                    itemClear,
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            processor.setModel(stringExtractor(
                    itemClear,
                    "model",
                    processorModelPattern,
                    "model:$1$2",
                    id+1));

            processor.setFamily(stringExtractor(
                    itemClear,
                    "family",
                    processorFamilyPatter,
                    "family:$1",
                    id+2));

            processor.setSpeed(stringExtractor(
                    itemClear,
                    "speed",
                    "([0-9]+\\.[0-9]+[MG]HZ)",
                    "speed:$1",
                    id+3));

            processor.setCurrent(stringExtractor(
                    itemClear,
                    "current",
                    "([0-9]+) (MHZ)",
                    "current:$1 $2",
                    id+4));

            processorDto.addProcessor(processor);
            id++;

        }

        this.transport.put(hardsys("processor"), processorDto);

    }

    private void keyboardFactory(List<String> items) {

        Help4DevsKeyboardDto keyboardsDto = new Help4DevsKeyboardDto();
        keyboardsDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: keyboard source: type: keyboard ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsKeyboard keyboard = new Help4DevsKeyboard();
            keyboard.setId(String.format("%06d", id));

            keyboard.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            keyboard.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id));
            keyboard.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));
            keyboard.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id).replaceAll("-", " "));

            keyboardsDto.addKeyboard(keyboard);
            id++;
        }

        this.transport.put(hardsys("keyboard"), keyboardsDto);

    }

    private void mouseFactory(List<String> items) {

        Help4DevsMouseDto mouseDto = new Help4DevsMouseDto();
        mouseDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: mouse source: type: mouse ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsMouse mouse = new Help4DevsMouse();
            mouse.setId(String.format("%06d", id));

            mouse.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            mouse.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id));
            mouse.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));
            mouse.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:]+)",
                    "description:$1",
                    id).replaceAll("-", " "));

            mouseDto.addMouse(mouse);
            id++;
        }

        this.transport.put(hardsys("mouse"), mouseDto);
    }

    private void monitorFactory(List<String> items) {

        Help4DevsMonitorDto monitorsDto = new Help4DevsMonitorDto();
        monitorsDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: monitor source: ", "");

            Help4DevsMonitor monitor = new Help4DevsMonitor();

            monitor.setId(String.format("%06d", id));
            monitor.setName(item);

            monitor.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            monitor.setType(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    monitorTypePattern,
                    "type:$1",
                    id));

            monitorsDto.addMonitor(monitor);
            id++;

        }

        this.transport.put(hardsys("monitor"), monitorsDto);

    }

    private void graphicsFactory(List<String> items) {

        Help4DevsGraphicsDto graphicsDto = new Help4DevsGraphicsDto();
        graphicsDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: graphics source: ", "");

            Help4DevsGraphics graphics = new Help4DevsGraphics();

            graphics.setId(String.format("%06d", id));
            graphics.setName(item);

            graphics.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            graphics.setSource(stringExtractor(
                    item,
                    "type",
                    videoTypePattern,
                    "type:$1",
                    id));

            graphics.setDescription(item
                    .replaceAll("type: graphics source: ", "")
                    .replaceAll("-", " "));

            if (graphics.getSource().equals("VGA")) {
                graphics.setVga("YES");
            }

            graphicsDto.addGraphic(graphics);
            id++;

        }

        this.transport.put(hardsys("graphics"), graphicsDto);

    }

    private void audioFactory(List<String> items) {

        Help4DevsAudioDto audioDto = new Help4DevsAudioDto();
        audioDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: audio source: ", "");

            Help4DevsAudio audio = new Help4DevsAudio();

            audio.setId(String.format("%06d", id));
            audio.setName(item);

            audio.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            audio.setSource(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    audioTypePattern,
                    "type:$1",
                    id));

            audio.setDescription(item
                    .replaceAll("type: audio source: ", "")
                    .replaceAll("-", " "));

            audioDto.addAudio(audio);
            id++;

        }

        this.transport.put(hardsys("audio"), audioDto);

    }

    private void storageFactory(List<String> items) {

        Help4DevsStorageDto storageDto = new Help4DevsStorageDto();
        storageDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: storage source: ", "");

            Help4DevsStorage storage = new Help4DevsStorage();

            storage.setId(String.format("%06d", id));
            storage.setName(item);

            storage.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            storage.setType(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    storageTypePattern,
                    "type:$1",
                    id));

            storage.setDescription(item
                    .replaceAll("type: storage source: ", "")
                    .replaceAll("-", " "));

            storageDto.addStorage(storage);
            id++;

        }

        this.transport.put(hardsys("storage"), storageDto);

    }

    private void networkFactory(List<String> items) {

        Help4DevsNetworkDto networkDto = new Help4DevsNetworkDto();
        networkDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: network source: type: network ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsNetwork network = new Help4DevsNetwork();
            network.setId(String.format("%06d", id));

            network.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            network.setType(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    networkTypePattern,
                    "type:$1",
                    id));

            network.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:/]+)",
                    "description:$1",
                    id));

            network.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));

            networkDto.addNetwork(network);
            id++;
        }

        this.transport.put(hardsys("network"), networkDto);

    }

    private void networkInterfaceFactory(List<String> items) {

        Help4DevsNetworkInterfaceDto networkInterfaceDto = new Help4DevsNetworkInterfaceDto();
        networkInterfaceDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(
                items,
                "type: networkinterface source: type: networkinterface ",
                "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsNetworkInterface networkInterface = new Help4DevsNetworkInterface();
            networkInterface.setId(String.format("%06d", id));

            networkInterface.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:/]+)",
                    "description:$1",
                    id));

            networkInterface.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));

            networkInterface.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "description",
                    vendorsPattern,
                    "description:$1",
                    id));

            networkInterface.setType(stringExtractor(
                    item.toUpperCase(),
                    "DESCRIPTION",
                    "DESCRIPTION: "+networkTypePattern,
                    "DESCRIPTION:$1",
                    id));

            networkInterfaceDto.addNetworkInterface(networkInterface);
            id++;
        }

        this.transport.put(hardsys("networkInterface"), networkInterfaceDto);

    }

    private void diskFactory(List<String> items) {

        Help4DevsDiskDto disksDto = new Help4DevsDiskDto();
        disksDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(items, "type: disk source: type: disk ", "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsDisk disk = new Help4DevsDisk();
            disk.setId(String.format("%06d", id));

            disk.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            disk.setName(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:/]+)",
                    "description:$1",
                    id));
            disk.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));
            disk.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:/]+)",
                    "description:$1",
                    id).replaceAll("-", " "));
            disk.setManufacture(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            disksDto.addDisk(disk);
            id++;
        }

        this.transport.put(hardsys("disk"), disksDto);

    }

    private void partitionFactory(List<String> items) {

        Help4DevsPartitionDto partitionDto = new Help4DevsPartitionDto();
        partitionDto.setQty(String.valueOf(items.size()));

        List<String> list = listClear(
                items,
                "type: partition source: type: partition ",
                "source: ");

        int id = 1;
        for (String item : list) {

            item = item.replaceAll("\\.@\\.", ":");

            Help4DevsPartition partition = new Help4DevsPartition();
            partition.setId(String.format("%06d", id));

            partition.setSource(stringExtractor(
                    item,
                    "source",
                    "(source: [/-_.0-9a-zA-Z]+)( description:)",
                    "source:$1",
                    id));

            partition.setDescription(stringExtractor(
                    item,
                    "description",
                    "(description: [-_.0-9a-zA-Z:/]+)",
                    "description:$1",
                    id).replaceAll("-", " "));

            partitionDto.addPartition(partition);
            id++;
        }

        this.transport.put(hardsys("partition"), partitionDto);

    }

    private void usbFactory(List<String> items) {

        Help4DevsUsbDto usbDto = new Help4DevsUsbDto();
        usbDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: usb source: ", "");

            Help4DevsUsb usb = new Help4DevsUsb();

            usb.setId(String.format("%06d", id));
            usb.setName(item);

            usb.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            usb.setType(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    usbTypePattern,
                    "type:$1",
                    id));

            usb.setDescription(item
                    .replaceAll("type: usb source: ", "")
                    .replaceAll("-", " "));

            usbDto.addUsb(usb);
            id++;

        }

        this.transport.put(hardsys("usb"), usbDto);

    }

    private void biosFactory(List<String> items) {

        Help4DevsBiosDto biosDto = new Help4DevsBiosDto();
        biosDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: bios source: ", "");

            Help4DevsBios bios = new Help4DevsBios();

            bios.setId(String.format("%06d", id));
            bios.setName(item);

            bios.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            bios.setDescription(item.replaceAll("-", " "));

            biosDto.addBios(bios);
            id++;

        }

        this.transport.put(hardsys("bios"), biosDto);

    }

    private void bridgeFactory(List<String> items) {

        Help4DevsBridgeDto bridgeDto = new Help4DevsBridgeDto();
        bridgeDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: bridge source: ", "");

            Help4DevsBridge bridge = new Help4DevsBridge();

            bridge.setId(String.format("%06d", id));
            bridge.setName(item);

            bridge.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            bridge.setType(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    bridgeTypePattern,
                    "type:$1",
                    id));

            bridge.setDescription(item
                    .replaceAll("type: bridge source: ", "")
                    .replaceAll("-", " "));

            bridgeDto.addBridge(bridge);
            id++;

        }

        this.transport.put(hardsys("bridge"), bridgeDto);

    }

    private void hubFactory(List<String> items) {

        Help4DevsHubDto hubDto = new Help4DevsHubDto();
        hubDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: hub source: ", "");

            Help4DevsHub hub = new Help4DevsHub();

            hub.setId(String.format("%06d", id));
            hub.setName(item);

            hub.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            hub.setDescription(item
                    .replaceAll("type: hub source: ", "")
                    .replaceAll("-", " "));

            hubDto.addHub(hub);
            id++;

        }

        this.transport.put(hardsys("hub"), hubDto);

    }

    private void memoryFactory(List<String> items) {

        Help4DevsMemoryDto memoryDto = new Help4DevsMemoryDto();
        memoryDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: memory source: ", "");

            Help4DevsMemory memory = new Help4DevsMemory();

            memory.setId(String.format("%06d", id));
            memory.setName(item);

            memory.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            memory.setDescription(item
                    .replaceAll("type: memory source: ", "")
                    .replaceAll("-", " "));

            memoryDto.addMemory(memory);
            id++;

        }

        this.transport.put(hardsys("memory"), memoryDto);

    }

    private void bluetoothFactory(List<String> items) {

        Help4DevsBluetoothDto bluetoothDto = new Help4DevsBluetoothDto();
        bluetoothDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: bluetooth source: ", "");

            Help4DevsBluetooth bluetooth = new Help4DevsBluetooth();

            bluetooth.setId(String.format("%06d", id));
            bluetooth.setName(item);

            bluetooth.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            bluetooth.setDescription(item
                    .replaceAll("type: bluetooth source: ", "")
                    .replaceAll("-", " "));

            bluetoothDto.addBluetooth(bluetooth);
            id++;

        }

        this.transport.put(hardsys("bluetooth"), bluetoothDto);

    }

    private void unknownFactory(List<String> items) {

        Help4DevsUnknownDto unknownDto = new Help4DevsUnknownDto();
        unknownDto.setQty(String.valueOf(items.size()));

        int id = 1;
        for (String item : items) {

            item = item.replaceAll("type: unknown source: ", "");

            Help4DevsUnknown unknown = new Help4DevsUnknown();

            unknown.setId(String.format("%06d", id));
            unknown.setName(item);

            unknown.setVendor(stringExtractor(
                    item.toUpperCase(),
                    "type",
                    vendorsPattern,
                    "type:$1",
                    id));

            unknown.setDescription(item
                    .replaceAll("type: unknown source: ", "")
                    .replaceAll("-", " "));

            unknownDto.addUnknown(unknown);
            id++;

        }

        this.transport.put(hardsys("unknown"), unknownDto);

    }

    /**
     * @implNote This method will convert the resources from a List object to a DTO object
     * to be used easier and understandably
     * */
    public void factory() {
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {

            List<String> makeList = new ArrayList<>();

            if (key.contains("_group") || key.equals("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;

                    String value = "type: " +key+ " source: "+item
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

        processorFactory(this.resources.get(hardsys("processor")));
        keyboardFactory(this.resources.get(hardsys("keyboard")));
        mouseFactory(this.resources.get(hardsys("mouse")));
        monitorFactory(this.resources.get(hardsys("monitor")));
        graphicsFactory(this.resources.get(hardsys("graphics")));
        audioFactory(this.resources.get(hardsys("audio")));
        storageFactory(this.resources.get(hardsys("storage")));
        networkFactory(this.resources.get(hardsys("network")));
        networkInterfaceFactory(this.resources.get(hardsys("networkinterface")));
        diskFactory(this.resources.get(hardsys("disk")));
        partitionFactory(this.resources.get(hardsys("partition")));
        usbFactory(this.resources.get(hardsys("usb")));
        biosFactory(this.resources.get(hardsys("bios")));
        bridgeFactory(this.resources.get(hardsys("bridge")));
        hubFactory(this.resources.get(hardsys("hub")));
        memoryFactory(this.resources.get(hardsys("memory")));
        bluetoothFactory(this.resources.get(hardsys("bluetooth")));
        unknownFactory(this.resources.get(hardsys("unknown")));

    }

}
