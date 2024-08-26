package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsProcessorDto {

    private String qty = null;
    private String name = null;
    private String arch = null;
    private String topology = null;
    private String model = null;
    private String speed = null;
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
    private List<String> cores = new ArrayList<>();
    private List<String> coreSpeeds = new ArrayList<>();

    public Help4DevsProcessorDto(List<String> processor) {
        this.builder(processor);
    }

    private void builder(List<String> processor) {
        for (String item : processor) {
            System.out.println("============> "+item);
        }
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
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

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public List<String> getCoreSpeeds() {
        return coreSpeeds;
    }

    public void setCoreSpeeds(List<String> coreSpeeds) {
        this.coreSpeeds = coreSpeeds;
    }

    public String toString() {
        return "Help4DevsProcessorDto{" +
                "qty='" + qty + '\'' +
                ", name='" + name + '\'' +
                ", arch='" + arch + '\'' +
                ", topology='" + topology + '\'' +
                ", model='" + model + '\'' +
                ", speed='" + speed + '\'' +
                ", bits='" + bits + '\'' +
                ", cache='" + cache + '\'' +
                ", cacheL1='" + cacheL1 + '\'' +
                ", cacheL2='" + cacheL2 + '\'' +
                ", cacheL3='" + cacheL3 + '\'' +
                ", cacheL4='" + cacheL4 + '\'' +
                ", cacheL5='" + cacheL5 + '\'' +
                ", bogomips='" + bogomips + '\'' +
                ", flags='" + flags + '\'' +
                ", minSpeed='" + minSpeed + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", socket='" + socket + '\'' +
                ", type='" + type + '\'' +
                ", family='" + family + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", version='" + version + '\'' +
                ", voltage='" + voltage + '\'' +
                ", clock='" + clock + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", cores=" + cores +
                ", coreSpeeds=" + coreSpeeds +
                '}';
    }
}
