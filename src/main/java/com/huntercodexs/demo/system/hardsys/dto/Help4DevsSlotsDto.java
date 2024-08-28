package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSlotsDto {

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

    public Help4DevsSlotsDto() {}

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

    public List<String> getVendor() {
        return vendor;
    }

    public void setVendor(List<String> vendor) {
        this.vendor = vendor;
    }

    public List<String> getDevice() {
        return device;
    }

    public void setDevice(List<String> device) {
        this.device = device;
    }

    public List<String> getSerial() {
        return serial;
    }

    public void setSerial(List<String> serial) {
        this.serial = serial;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(List<String> manufacturer) {
        this.manufacturer = manufacturer;
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
