package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsMonitorDto {

    private final List<String> monitor;

    private List<String> id;
    private List<String> name;
    private List<String> type;
    private List<String> size;
    private List<String> model;
    private List<String> series;
    private List<String> driver;
    private List<String> vendor;
    private List<String> source;
    private List<String> serial;
    private List<String> frequency;
    private List<String> description;
    private List<String> features;

    public Help4DevsMonitorDto(List<String> monitor) {
        this.monitor = monitor;
    }

    private String buildString(int i) {
        try {
            return this.monitor.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.monitor.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsMonitorDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.type = buildCollection(2);
        this.size = buildCollection(3);
        this.model = buildCollection(4);
        this.series = buildCollection(5);
        this.driver = buildCollection(6);
        this.vendor = buildCollection(7);
        this.source = buildCollection(8);
        this.serial = buildCollection(9);
        this.frequency = buildCollection(10);
        this.description = buildCollection(11);
        this.features = buildCollection(12);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getSize() {
        return size;
    }

    public List<String> getModel() {
        return model;
    }

    public List<String> getSeries() {
        return series;
    }

    public List<String> getDriver() {
        return driver;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getSerial() {
        return serial;
    }

    public List<String> getFrequency() {
        return frequency;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String toString() {
        return "Help4DevsMonitorDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", size=" + size +
                ", model=" + model +
                ", series=" + series +
                ", driver=" + driver +
                ", vendor=" + vendor +
                ", source=" + source +
                ", serial=" + serial +
                ", frequency=" + frequency +
                ", description=" + description +
                ", features=" + features +
                "]";
    }
}
