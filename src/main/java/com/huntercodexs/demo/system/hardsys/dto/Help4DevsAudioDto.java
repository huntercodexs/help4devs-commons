package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsAudioDto {

    private final List<String> audio;

    private String id;
    private String bus;
    private String name;
    private String model;
    private String board;
    private String vendor;
    private String driver;
    private String source;
    private String version;
    private String product;
    private String frequency;
    private String resources;
    private String multimedia;
    private String description;
    private String capabilities;
    private List<String> details;
    private List<String> devices;

    public Help4DevsAudioDto(List<String> audio) {
        this.audio = audio;
    }

    private String buildString(int i) {
        try {
            return this.audio.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.audio.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsAudioDto builder() {
        this.id = buildString(0);
        this.bus = buildString(1);
        this.name = buildString(2);
        this.model = buildString(3);
        this.board = buildString(4);
        this.vendor = buildString(5);
        this.driver = buildString(6);
        this.source = buildString(7);
        this.version = buildString(8);
        this.product = buildString(9);
        this.frequency = buildString(10);
        this.resources = buildString(11);
        this.multimedia = buildString(12);
        this.description = buildString(13);
        this.capabilities = buildString(14);
        this.details = buildCollection(15);
        this.devices = buildCollection(16);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getBus() {
        return bus;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getBoard() {
        return board;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDriver() {
        return driver;
    }

    public String getSource() {
        return source;
    }

    public String getVersion() {
        return version;
    }

    public String getProduct() {
        return product;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getResources() {
        return resources;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public String getDescription() {
        return description;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public List<String> getDetails() {
        return details;
    }

    public List<String> getDevices() {
        return devices;
    }

    public String toString() {
        return "Help4DevsAudioDto[" +
                ", id=" + id +
                ", bus=" + bus +
                ", name=" + name +
                ", model=" + model +
                ", board=" + board +
                ", vendor=" + vendor +
                ", driver=" + driver +
                ", source=" + source +
                ", version=" + version +
                ", product=" + product +
                ", frequency=" + frequency +
                ", resources=" + resources +
                ", multimedia=" + multimedia +
                ", description=" + description +
                ", capabilities=" + capabilities +
                ", details=" + details +
                ", devices=" + devices +
                "]";
    }
}
