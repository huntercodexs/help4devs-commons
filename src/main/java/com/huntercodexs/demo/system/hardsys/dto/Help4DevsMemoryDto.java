package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMemoryDto {

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

    public Help4DevsMemoryDto() {}

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

    public List<String> getUsed() {
        return used;
    }

    public void setUsed(List<String> used) {
        this.used = used;
    }

    public List<String> getFree() {
        return free;
    }

    public void setFree(List<String> free) {
        this.free = free;
    }

    public List<String> getRank() {
        return rank;
    }

    public void setRank(List<String> rank) {
        this.rank = rank;
    }

    public List<String> getTotal() {
        return total;
    }

    public void setTotal(List<String> total) {
        this.total = total;
    }

    public List<String> getWidth() {
        return width;
    }

    public void setWidth(List<String> width) {
        this.width = width;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public void setSpeed(List<String> speed) {
        this.speed = speed;
    }

    public List<String> getVolts() {
        return volts;
    }

    public void setVolts(List<String> volts) {
        this.volts = volts;
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

    public List<String> getSerial() {
        return serial;
    }

    public void setSerial(List<String> serial) {
        this.serial = serial;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(List<String> manufacturer) {
        this.manufacturer = manufacturer;
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
