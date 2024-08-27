package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsBatteryDto {

    private final List<String> battery;

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String source;
    private String serial;
    private String version;
    private String product;
    private String charge;
    private String status;
    private String condition;
    private String description;
    private String manufacturer;
    private List<String> details;
    private List<String> features;

    public Help4DevsBatteryDto(List<String> battery) {
        this.battery = battery;
    }

    private String buildString(int i) {
        try {
            return this.battery.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.battery.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsBatteryDto builder() {
        this.id = buildString(0);
        this.name = buildString(1);
        this.type = buildString(2);
        this.vendor = buildString(3);
        this.source = buildString(4);
        this.serial = buildString(5);
        this.version = buildString(6);
        this.product = buildString(7);
        this.charge = buildString(8);
        this.status = buildString(9);
        this.condition = buildString(10);
        this.description = buildString(11);
        this.manufacturer = buildString(12);
        this.details = buildCollection(13);
        this.features = buildCollection(14);
        return this;
    }

    public String getCharge() {
        return charge;
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

    public String getStatus() {
        return status;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<String> getDetails() {
        return details;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String toString() {
        return "Help4DevsBatteryDto[" +
                ", id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", source=" + source +
                ", serial=" + serial +
                ", version=" + version +
                ", product=" + product +
                ", charge=" + charge +
                ", status=" + status +
                ", condition=" + condition +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                ", details=" + details +
                ", features=" + features +
                "]";
    }
}
