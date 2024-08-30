package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsPrinterDto {

    private String qty;
    private List<Help4DevsPrinter> details;

    public Help4DevsPrinterDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsPrinter> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsPrinter> details) {
        this.details = details;
    }

    public void addPrinter(Help4DevsPrinter printer) {
        this.details.add(printer);
    }

    public String toString() {
        return "Help4DevsPrinterDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsPrinter {

        private String id;
        private String name;
        private String type;
        private String model;
        private String vendor;
        private String driver;
        private String serial;
        private String source;
        private String manufacture;
        private String description;
        private List<String> features;

        public Help4DevsPrinter() {}

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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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
            return "Help4DevsPrinter[" +
                    "id=" + id +
                    ", name=" + name +
                    ", type=" + type +
                    ", model=" + model +
                    ", vendor=" + vendor +
                    ", driver=" + driver +
                    ", serial=" + serial +
                    ", source=" + source +
                    ", manufacture=" + manufacture +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
