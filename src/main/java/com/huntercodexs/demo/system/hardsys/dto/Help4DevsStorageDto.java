package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsStorageDto {

    private List<String> id;
    private List<String> name;
    private List<String> type;
    private List<String> size;
    private List<String> used;
    private List<String> free;
    private List<String> speed;
    private List<String> vendor;
    private List<String> driver;
    private List<String> serial;
    private List<String> source;
    private List<String> partition;
    private List<String> manufacture;
    private List<String> description;
    private List<String> details;

    public Help4DevsStorageDto() {}

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

    public List<String> getSpeed() {
        return speed;
    }

    public void setSpeed(List<String> speed) {
        this.speed = speed;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public void setVendor(List<String> vendor) {
        this.vendor = vendor;
    }

    public List<String> getDriver() {
        return driver;
    }

    public void setDriver(List<String> driver) {
        this.driver = driver;
    }

    public List<String> getSerial() {
        return serial;
    }

    public void setSerial(List<String> serial) {
        this.serial = serial;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getPartition() {
        return partition;
    }

    public void setPartition(List<String> partition) {
        this.partition = partition;
    }

    public List<String> getManufacture() {
        return manufacture;
    }

    public void setManufacture(List<String> manufacture) {
        this.manufacture = manufacture;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsStorageDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", size=" + size +
                ", used=" + used +
                ", free=" + free +
                ", speed=" + speed +
                ", vendor=" + vendor +
                ", driver=" + driver +
                ", serial=" + serial +
                ", source=" + source +
                ", partition=" + partition +
                ", manufacture=" + manufacture +
                ", description=" + description +
                ", details=" + details +
                "]";
    }
}
