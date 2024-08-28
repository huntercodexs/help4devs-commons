package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBluetoothDto {

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String driver;
    private String source;
    private String serial;
    private String version;
    private String product;
    private String revision;
    private String description;
    private List<String> details;

    public Help4DevsBluetoothDto() {}

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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsBluetoothDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", driver=" + driver +
                ", source=" + source +
                ", serial=" + serial +
                ", version=" + version +
                ", product=" + product +
                ", revision=" + revision +
                ", description=" + description +
                ", details=" + details +
                "]";
    }
}
