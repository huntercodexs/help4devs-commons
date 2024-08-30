package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsSlotsDto {

    private String qty;
    private List<Help4DevsSlots> details;

    public Help4DevsSlotsDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsSlots> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsSlots> details) {
        this.details = details;
    }

    public void addSlot(Help4DevsSlots slots) {
        this.details.add(slots);
    }

    public String toString() {
        return "Help4DevsSlotsDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsSlots {

        private String id;
        private String name;
        private String type;
        private String vendor;
        private String device;
        private String serial;
        private String location;
        private String description;
        private String manufacturer;
        private List<String> features;

        public Help4DevsSlots() {}

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsSlots[" +
                    "id=" + id +
                    ", name=" + name +
                    ", type=" + type +
                    ", vendor=" + vendor +
                    ", device=" + device +
                    ", serial=" + serial +
                    ", location=" + location +
                    ", description=" + description +
                    ", manufacturer=" + manufacturer +
                    ", features=" + features +
                    "]";
        }
    }
}
