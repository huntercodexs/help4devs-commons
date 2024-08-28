package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsNetworkDto {

    private List<String> id;
    private List<String> mac;
    private List<String> ips;
    private List<String> name;
    private List<String> type;
    private List<String> speed;
    private List<String> ports;
    private List<String> driver;
    private List<String> vendor;
    private List<String> source;
    private List<String> status;
    private List<String> version;
    private List<String> address;
    private List<String> details;

    public Help4DevsNetworkDto() {}

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getMac() {
        return mac;
    }

    public void setMac(List<String> mac) {
        this.mac = mac;
    }

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public void setSpeed(List<String> speed) {
        this.speed = speed;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public List<String> getDriver() {
        return driver;
    }

    public void setDriver(List<String> driver) {
        this.driver = driver;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public void setVendor(List<String> vendor) {
        this.vendor = vendor;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getVersion() {
        return version;
    }

    public void setVersion(List<String> version) {
        this.version = version;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsNetworkDto[" +
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
                ", details=" + details +
                "]";
    }
}
