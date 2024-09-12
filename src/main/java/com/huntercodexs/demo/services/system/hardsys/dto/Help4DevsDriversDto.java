package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsDriversDto {

    private String qty;
    private List<Help4DevsDrivers> details;

    public Help4DevsDriversDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsDrivers> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsDrivers> details) {
        this.details = details;
    }

    public void addDriver(Help4DevsDrivers driver) {
        this.details.add(driver);
    }

    public String toString() {
        return "Help4DevsDriversDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsDrivers {

        private String id;
        private String names;
        private String vendor;
        private String source;
        private String device;
        private String description;
        private List<String> features;

        public Help4DevsDrivers() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
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

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
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
            return "Help4DevsDrivers[" +
                    "id=" + id +
                    ", names=" + names +
                    ", vendor=" + vendor +
                    ", source=" + source +
                    ", device=" + device +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
