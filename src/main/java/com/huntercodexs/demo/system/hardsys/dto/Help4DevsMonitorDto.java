package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMonitorDto {

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

    public Help4DevsMonitorDto() {}

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    public List<String> getDriver() {
        return driver;
    }

    public void setDriver(List<String> driver) {
        this.driver = driver;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public void setVendor(List<String> vendor) {
        this.vendor = vendor;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getSerial() {
        return serial;
    }

    public void setSerial(List<String> serial) {
        this.serial = serial;
    }

    public List<String> getFrequency() {
        return frequency;
    }

    public void setFrequency(List<String> frequency) {
        this.frequency = frequency;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
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
