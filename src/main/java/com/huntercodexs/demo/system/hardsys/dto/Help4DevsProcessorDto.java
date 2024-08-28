package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsProcessorDto {

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

    public Help4DevsProcessorDto() {}

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getTopology() {
        return topology;
    }

    public void setTopology(String topology) {
        this.topology = topology;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getCacheL1() {
        return cacheL1;
    }

    public void setCacheL1(String cacheL1) {
        this.cacheL1 = cacheL1;
    }

    public String getCacheL2() {
        return cacheL2;
    }

    public void setCacheL2(String cacheL2) {
        this.cacheL2 = cacheL2;
    }

    public String getCacheL3() {
        return cacheL3;
    }

    public void setCacheL3(String cacheL3) {
        this.cacheL3 = cacheL3;
    }

    public String getCacheL4() {
        return cacheL4;
    }

    public void setCacheL4(String cacheL4) {
        this.cacheL4 = cacheL4;
    }

    public String getCacheL5() {
        return cacheL5;
    }

    public void setCacheL5(String cacheL5) {
        this.cacheL5 = cacheL5;
    }

    public String getBogomips() {
        return bogomips;
    }

    public void setBogomips(String bogomips) {
        this.bogomips = bogomips;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(String minSpeed) {
        this.minSpeed = minSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getListCores() {
        return listCores;
    }

    public void setListCores(List<String> listCores) {
        this.listCores = listCores;
    }

    public List<String> getSpeedCores() {
        return speedCores;
    }

    public void setSpeedCores(List<String> speedCores) {
        this.speedCores = speedCores;
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
