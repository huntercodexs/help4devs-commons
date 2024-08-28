package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsModemDto {

    private String id;
    private String name;
    private String ports;
    private String speed;
    private String source;
    private String vendor;
    private String description;
    private List<String> features;

    public Help4DevsModemDto() {}

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

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String toString() {
        return "Help4DevsModemDto[" +
                "id=" + id +
                ", name=" + name +
                ", ports=" + ports +
                ", speed=" + speed +
                ", source=" + source +
                ", vendor=" + vendor +
                ", description=" + description +
                ", features=" + features +
                "]";
    }

}
