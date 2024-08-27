package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsBluetoothDto {

    private final List<String> bluetooth;

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

    public Help4DevsBluetoothDto(List<String> bluetooth) {
        this.bluetooth = bluetooth;
    }

    private String buildString(int i) {
        try {
            return this.bluetooth.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.bluetooth.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsBluetoothDto builder() {
        this.id = buildString(0);
        this.name = buildString(1);
        this.type = buildString(2);
        this.vendor = buildString(3);
        this.driver = buildString(4);
        this.source = buildString(5);
        this.serial = buildString(6);
        this.version = buildString(7);
        this.product = buildString(8);
        this.revision = buildString(9);
        this.description = buildString(10);
        this.details = buildCollection(11);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public String getSerial() {
        return serial;
    }

    public String getVersion() {
        return version;
    }

    public String getProduct() {
        return product;
    }

    public String getRevision() {
        return revision;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDetails() {
        return details;
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
