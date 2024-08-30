package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsMonitors {

    private String qty;
    private List<Help4DevsMonitorDto> details;

    public Help4DevsMonitors() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsMonitorDto> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsMonitorDto> details) {
        this.details = details;
    }

    public void addMonitor(Help4DevsMonitorDto monitor) {
        this.details.add(monitor);
    }

    public String toString() {
        return "Help4DevsMonitor[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsMonitorDto {

        private String id;
        private String name;
        private String type;
        private String size;
        private String model;
        private String series;
        private String driver;
        private String vendor;
        private String source;
        private String serial;
        private String frequency;
        private String description;
        private List<String> features;

        public Help4DevsMonitorDto() {}

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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
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

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
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
            return "Help4DevsMonitorDto[" +
                    "id=" + id +
                    ", name=" + name +
                    ", type=" + type +
                    ", size=" + size +
                    ", model=" + model +
                    ", series=" + series +
                    ", driver=" + driver +
                    ", vendor=" + vendor +
                    ", source=" + source +
                    ", serial=" + serial +
                    ", frequency=" + frequency +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
