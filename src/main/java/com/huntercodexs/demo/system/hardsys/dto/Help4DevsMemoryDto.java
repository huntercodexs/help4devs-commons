package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsMemoryDto {

    private String qty;
    private List<Help4DevsMemory> details;

    public Help4DevsMemoryDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsMemory> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsMemory> details) {
        this.details = details;
    }

    public void addMemory(Help4DevsMemory memory) {
        this.details.add(memory);
    }

    public String toString() {
        return "Help4DevsMemoryDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsMemory {

        private String id;
        private String name;
        private String type;
        private String used;
        private String free;
        private String rank;
        private String total;
        private String width;
        private String speed;
        private String volts;
        private String source;
        private String vendor;
        private String serial;
        private String location;
        private String manufacturer;
        private String description;
        private List<String> features;

        public Help4DevsMemory() {}

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getVolts() {
            return volts;
        }

        public void setVolts(String volts) {
            this.volts = volts;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFree() {
            return free;
        }

        public void setFree(String free) {
            this.free = free;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String toString() {
            return "Help4DevsMemory[" +
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
}
