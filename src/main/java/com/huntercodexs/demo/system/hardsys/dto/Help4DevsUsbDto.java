package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsUsbDto {

    private List<String> id;
    private List<String> type;
    private List<String> name;
    private List<String> ports;
    private List<String> speed;
    private List<String> info;
    private List<String> source;
    private List<String> vendor;
    private List<String> description;
    private List<String> features;

    public Help4DevsUsbDto() {}

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public void setSpeed(List<String> speed) {
        this.speed = speed;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public void setVendor(List<String> vendor) {
        this.vendor = vendor;
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
        return "Help4DevsUsbDto[" +
                "id=" + id +
                ", type=" + type +
                ", name=" + name +
                ", ports=" + ports +
                ", speed=" + speed +
                ", info=" + info +
                ", source=" + source +
                ", vendor=" + vendor +
                ", description=" + description +
                ", features=" + features +
                "]";
    }
}
