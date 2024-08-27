package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsSlotsDto {

    private final List<String> slots;

    private List<String> id;
    private List<String> name;
    private List<String> type;
    private List<String> vendor;
    private List<String> device;
    private List<String> serial;
    private List<String> details;
    private List<String> location;
    private List<String> description;
    private List<String> manufacturer;

    public Help4DevsSlotsDto(List<String> slots) {
        this.slots = slots;
    }

    private String buildString(int i) {
        try {
            return this.slots.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.slots.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsSlotsDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.type = buildCollection(2);
        this.vendor = buildCollection(3);
        this.device = buildCollection(4);
        this.serial = buildCollection(5);
        this.details = buildCollection(6);
        this.location = buildCollection(7);
        this.description = buildCollection(9);
        this.manufacturer = buildCollection(10);
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

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getDevice() {
        return device;
    }

    public List<String> getSerial() {
        return serial;
    }

    public List<String> getDetails() {
        return details;
    }

    public List<String> getLocation() {
        return location;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "Help4DevsSlotsDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", device=" + device +
                ", serial=" + serial +
                ", details=" + details +
                ", location=" + location +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                "]";
    }
}
