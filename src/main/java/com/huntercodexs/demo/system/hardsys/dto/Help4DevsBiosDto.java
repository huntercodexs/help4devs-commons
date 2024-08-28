package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBiosDto {

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String source;
    private String serial;
    private String version;
    private String product;
    private String release;
    private String firmware;
    private String revision;
    private String description;
    private String manufacturer;
    private List<String> details;
    private List<String> characteristics;

    public Help4DevsBiosDto() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public String toString() {
        return "Help4DevsBiosDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", source=" + source +
                ", serial=" + serial +
                ", version=" + version +
                ", product=" + product +
                ", release=" + release +
                ", firmware=" + firmware +
                ", revision=" + revision +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                ", details=" + details +
                ", characteristics=" + characteristics +
                "]";
    }
}
