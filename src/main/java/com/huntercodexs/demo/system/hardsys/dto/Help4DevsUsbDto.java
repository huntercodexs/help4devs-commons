package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsUsbDto {

    private String qty;
    private List<Help4DevsUsb> details;

    public Help4DevsUsbDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsUsb> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsUsb> details) {
        this.details = details;
    }

    public void addUsb(Help4DevsUsb usb) {
        this.details.add(usb);
    }

    public String toString() {
        return "Help4DevsUsbDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsUsb {

        private String id;
        private String type;
        private String name;
        private String ports;
        private String speed;
        private String info;
        private String source;
        private String vendor;
        private String driver;
        private String version;
        private String description;
        private List<String> features;

        public Help4DevsUsb() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPorts() {
            return ports;
        }

        public void setPorts(String ports) {
            this.ports = ports;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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
            return "Help4DevsUsb[" +
                    "id=" + id +
                    ", type=" + type +
                    ", name=" + name +
                    ", ports=" + ports +
                    ", speed=" + speed +
                    ", version=" + version +
                    ", driver=" + driver +
                    ", info=" + info +
                    ", source=" + source +
                    ", vendor=" + vendor +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
