package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsProcessorDto {

    private final List<String> processor;

    private String cores = null;
    private String name = null;
    private String arch = null;
    private String topology = null;
    private String model = null;
    private String speed = null;
    private String current = null;
    private String bits = null;
    private String cache = null;
    private String cacheL1 = null;
    private String cacheL2 = null;
    private String cacheL3 = null;
    private String cacheL4 = null;
    private String cacheL5 = null;
    private String bogomips = null;
    private String flags = null;
    private String minSpeed = null;
    private String maxSpeed = null;
    private String socket = null;
    private String type = null;
    private String family = null;
    private String manufacturer = null;
    private String version = null;
    private String voltage = null;
    private String clock = null;
    private String serialNumber = null;
    private String characteristics = null;
    private List<String> listCores = new ArrayList<>();
    private List<String> speedCores = new ArrayList<>();

    public Help4DevsProcessorDto(List<String> processor) {
        this.processor = processor;
    }

    public Help4DevsProcessorDto builder() {
        this.cores = this.processor.get(0);
        this.name = this.processor.get(1);
        this.model = this.processor.get(2);
        this.family = this.processor.get(3);
        this.speed = this.processor.get(4);
        this.current = this.processor.get(5);
        this.listCores = Collections.singletonList(this.processor.get(6));
        this.speedCores = Collections.singletonList(this.processor.get(7));
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
