package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsStorageDto {

    private final List<String> storage;

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

    public Help4DevsStorageDto(List<String> storage) {
        this.storage = storage;
    }

    private String buildString(int i) {
        try {
            return this.storage.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.storage.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsStorageDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.type = buildCollection(2);
        this.size = buildCollection(3);
        this.used = buildCollection(4);
        this.free = buildCollection(5);
        this.speed = buildCollection(6);
        this.vendor = buildCollection(7);
        this.driver = buildCollection(8);
        this.serial = buildCollection(9);
        this.source = buildCollection(10);
        this.partition = buildCollection(11);
        this.manufacture = buildCollection(12);
        this.description = buildCollection(13);
        this.details = buildCollection(14);
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

    public List<String> getUsed() {
        return used;
    }

    public List<String> getFree() {
        return free;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getDriver() {
        return driver;
    }

    public List<String> getSerial() {
        return serial;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getPartition() {
        return partition;
    }

    public List<String> getManufacture() {
        return manufacture;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getDetails() {
        return details;
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
