package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsNetworkDto {

    private String qty;
    private List<Help4DevsNetwork> details;

    public Help4DevsNetworkDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsNetwork> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsNetwork> details) {
        this.details = details;
    }

    public void addNetwork(Help4DevsNetwork network) {
        this.details.add(network);
    }

    public String toString() {
        return "Help4DevsNetworkDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsNetwork {

        private String id;
        private String mac;
        private String ips;
        private String name;
        private String type;
        private String speed;
        private String ports;
        private String driver;
        private String vendor;
        private String source;
        private String status;
        private String version;
        private String address;
        private List<String> features;

        public Help4DevsNetwork() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getIps() {
            return ips;
        }

        public void setIps(String ips) {
            this.ips = ips;
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

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getPorts() {
            return ports;
        }

        public void setPorts(String ports) {
            this.ports = ports;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsNetwork[" +
                    "id=" + id +
                    ", mac=" + mac +
                    ", ips=" + ips +
                    ", name=" + name +
                    ", type=" + type +
                    ", speed=" + speed +
                    ", ports=" + ports +
                    ", driver=" + driver +
                    ", vendor=" + vendor +
                    ", source=" + source +
                    ", status=" + status +
                    ", version=" + version +
                    ", address=" + address +
                    ", features=" + features +
                    "]";
        }
    }
}
