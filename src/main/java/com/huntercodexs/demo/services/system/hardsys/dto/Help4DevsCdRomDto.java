package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsCdRomDto {

    private String qty;
    private List<Help4DevsCdRom> details;

    public Help4DevsCdRomDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsCdRom> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsCdRom> details) {
        this.details = details;
    }

    public void addCdRom(Help4DevsCdRom cdrom) {
        this.details.add(cdrom);
    }

    public String toString() {
        return "Help4DevsCdRomDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsCdRom {

        private String id;
        private String name;
        private String type;
        private String vendor;
        private String driver;
        private String serial;
        private String source;
        private String manufacture;
        private String description;
        private List<String> features;

        public Help4DevsCdRom() {}

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
            return "Help4DevsCdRom[" +
                    "id=" + id +
                    ", name=" + name +
                    ", type=" + type +
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
