package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsUsbDto {

    private final List<String> usb;

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

    public Help4DevsUsbDto(List<String> usb) {
        this.usb = usb;
    }

    private String buildString(int i) {
        try {
            return this.usb.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.usb.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsUsbDto builder() {
        this.id = buildCollection(0);
        this.type = buildCollection(1);
        this.name = buildCollection(2);
        this.ports = buildCollection(3);
        this.speed = buildCollection(4);
        this.info = buildCollection(5);
        this.source = buildCollection(6);
        this.vendor = buildCollection(7);
        this.description = buildCollection(8);
        this.features = buildCollection(9);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getPorts() {
        return ports;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public List<String> getInfo() {
        return info;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getFeatures() {
        return features;
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
