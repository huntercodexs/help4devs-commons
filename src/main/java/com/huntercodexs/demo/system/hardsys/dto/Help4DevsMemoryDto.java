package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsMemoryDto {

    private final List<String> memory;

    private List<String> id;
    private List<String> name;
    private List<String> type;
    private List<String> used;
    private List<String> free;
    private List<String> rank;
    private List<String> total;
    private List<String> width;
    private List<String> speed;
    private List<String> volts;
    private List<String> source;
    private List<String> vendor;
    private List<String> serial;
    private List<String> location;
    private List<String> manufacturer;
    private List<String> description;
    private List<String> features;

    public Help4DevsMemoryDto(List<String> memory) {
        this.memory = memory;
    }

    private String buildString(int i) {
        try {
            return this.memory.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.memory.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsMemoryDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.type = buildCollection(2);
        this.used = buildCollection(3);
        this.free = buildCollection(4);
        this.rank = buildCollection(5);
        this.total = buildCollection(6);
        this.width = buildCollection(7);
        this.speed = buildCollection(8);
        this.volts = buildCollection(9);
        this.source = buildCollection(10);
        this.vendor = buildCollection(11);
        this.serial = buildCollection(12);
        this.location = buildCollection(13);
        this.manufacturer = buildCollection(14);
        this.description = buildCollection(15);
        this.features = buildCollection(16);
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

    public List<String> getUsed() {
        return used;
    }

    public List<String> getFree() {
        return free;
    }

    public List<String> getRank() {
        return rank;
    }

    public List<String> getTotal() {
        return total;
    }

    public List<String> getWidth() {
        return width;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public List<String> getVolts() {
        return volts;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getSerial() {
        return serial;
    }

    public List<String> getLocation() {
        return location;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String toString() {
        return "Help4DevsMemoryDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", used=" + used +
                ", free=" + free +
                ", rank=" + rank +
                ", total=" + total +
                ", width=" + width +
                ", speed=" + speed +
                ", volts=" + volts +
                ", source=" + source +
                ", vendor=" + vendor +
                ", serial=" + serial +
                ", location=" + location +
                ", manufacturer=" + manufacturer +
                ", description=" + description +
                ", features=" + features +
                "]";
    }
}
