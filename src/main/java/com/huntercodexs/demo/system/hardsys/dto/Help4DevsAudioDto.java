package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsAudioDto {

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

    public Help4DevsAudioDto() {}

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
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

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Help4DevsAudioDto[" +
                "id=" + id +
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
