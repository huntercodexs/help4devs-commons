package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsProcessorDto {

    private final List<String> processor;

    private String cores;
    private String name;
    private String model;
    private String family;
    private String speed;
    private String current;
    private String arch;
    private String topology;
    private String bits;
    private String cache;
    private String cacheL1;
    private String cacheL2;
    private String cacheL3;
    private String cacheL4;
    private String cacheL5;
    private String bogomips;
    private String flags;
    private String minSpeed;
    private String maxSpeed;
    private String socket;
    private String type;
    private String manufacturer;
    private String version;
    private String voltage;
    private String clock;
    private String serialNumber;
    private String characteristics;
    private List<String> listCores;
    private List<String> speedCores;

    public Help4DevsProcessorDto(List<String> processor) {
        this.processor = processor;
    }

    private String buildString(int i) {
        try {
            return this.processor.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.processor.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsProcessorDto builder() {
        this.cores = buildString(0);
        this.name = buildString(1);
        this.model = buildString(2);
        this.family = buildString(3);
        this.speed = buildString(4);
        this.current = buildString(5);
        this.arch = buildString(6);
        this.topology = buildString(7);
        this.bits = buildString(8);
        this.cache = buildString(9);
        this.cacheL1 = buildString(10);
        this.cacheL2 = buildString(11);
        this.cacheL3 = buildString(12);
        this.cacheL4 = buildString(13);
        this.cacheL5 = buildString(14);
        this.bogomips = buildString(15);
        this.flags = buildString(16);
        this.minSpeed = buildString(17);
        this.maxSpeed = buildString(18);
        this.socket = buildString(19);
        this.type = buildString(20);
        this.manufacturer = buildString(21);
        this.version = buildString(22);
        this.voltage = buildString(23);
        this.clock = buildString(24);
        this.serialNumber = buildString(25);
        this.characteristics = buildString(26);
        this.listCores = buildCollection(27);
        this.speedCores = buildCollection(28);
        return this;
    }

    public String getCores() {
        return cores;
    }

    public String getName() {
        return name;
    }

    public String getArch() {
        return arch;
    }

    public String getTopology() {
        return topology;
    }

    public String getModel() {
        return model;
    }

    public String getSpeed() {
        return speed;
    }

    public String getCurrent() {
        return current;
    }

    public String getBits() {
        return bits;
    }

    public String getCache() {
        return cache;
    }

    public String getCacheL1() {
        return cacheL1;
    }

    public String getCacheL2() {
        return cacheL2;
    }

    public String getCacheL3() {
        return cacheL3;
    }

    public String getCacheL4() {
        return cacheL4;
    }

    public String getCacheL5() {
        return cacheL5;
    }

    public String getBogomips() {
        return bogomips;
    }

    public String getFlags() {
        return flags;
    }

    public String getMinSpeed() {
        return minSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getSocket() {
        return socket;
    }

    public String getType() {
        return type;
    }

    public String getFamily() {
        return family;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public String getVoltage() {
        return voltage;
    }

    public String getClock() {
        return clock;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public List<String> getListCores() {
        return listCores;
    }

    public List<String> getSpeedCores() {
        return speedCores;
    }

    public String toString() {
        return "Help4DevsProcessorDto[" +
                "cores=" + cores +
                ", name=" + name +
                ", arch=" + arch +
                ", topology=" + topology +
                ", model=" + model +
                ", speed=" + speed +
                ", current=" + current +
                ", bits=" + bits +
                ", cache=" + cache +
                ", cacheL1=" + cacheL1 +
                ", cacheL2=" + cacheL2 +
                ", cacheL3=" + cacheL3 +
                ", cacheL4=" + cacheL4 +
                ", cacheL5=" + cacheL5 +
                ", bogomips=" + bogomips +
                ", flags=" + flags +
                ", minSpeed=" + minSpeed +
                ", maxSpeed=" + maxSpeed +
                ", socket=" + socket +
                ", type=" + type +
                ", family=" + family +
                ", manufacturer=" + manufacturer +
                ", version=" + version +
                ", voltage=" + voltage +
                ", clock=" + clock +
                ", serialNumber=" + serialNumber +
                ", characteristics=" + characteristics +
                ", listCores=" + listCores +
                ", speedCores=" + speedCores +
                "]";
    }
}
