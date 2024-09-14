package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsStorageDto {

    private String qty;
    private List<Help4DevsStorage> details;

    public Help4DevsStorageDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsStorage> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsStorage> details) {
        this.details = details;
    }

    public void addStorage(Help4DevsStorage storage) {
        this.details.add(storage);
    }

    public String toString() {
        return "Help4DevsStorageDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsStorage {

        private String id;
        private String name;
        private String type;
        private String size;
        private String used;
        private String free;
        private String speed;
        private String vendor;
        private String driver;
        private String serial;
        private String source;
        private String partition;
        private String manufacture;
        private String description;
        private List<String> features;

        public Help4DevsStorage() {}

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

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getFree() {
            return free;
        }

        public void setFree(String free) {
            this.free = free;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPartition() {
            return partition;
        }

        public void setPartition(String partition) {
            this.partition = partition;
        }

        public String getManufacture() {
            return manufacture;
        }

        public void setManufacture(String manufacture) {
            this.manufacture = manufacture;
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
            return "Help4DevsStorage[" +
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
                    ", features=" + features +
                    "]";
        }
    }
}
