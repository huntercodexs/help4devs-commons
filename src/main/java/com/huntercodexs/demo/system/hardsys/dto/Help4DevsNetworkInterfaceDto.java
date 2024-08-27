package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsNetworkInterfaceDto {

    private final List<String> networkInterface;

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

    public Help4DevsNetworkInterfaceDto(List<String> networkInterface) {
        this.networkInterface = networkInterface;
    }

    private String buildString(int i) {
        try {
            return this.networkInterface.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.networkInterface.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsNetworkInterfaceDto builder() {
        this.id = buildCollection(0);
        this.mac = buildCollection(1);
        this.ips = buildCollection(2);
        this.name = buildCollection(3);
        this.type = buildCollection(4);
        this.speed = buildCollection(5);
        this.ports = buildCollection(6);
        this.driver = buildCollection(7);
        this.vendor = buildCollection(8);
        this.source = buildCollection(9);
        this.status = buildCollection(10);
        this.version = buildCollection(11);
        this.address = buildCollection(12);
        this.details = buildCollection(13);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getMac() {
        return mac;
    }

    public List<String> getIps() {
        return ips;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getSpeed() {
        return speed;
    }

    public List<String> getPorts() {
        return ports;
    }

    public List<String> getDriver() {
        return driver;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getStatus() {
        return status;
    }

    public List<String> getVersion() {
        return version;
    }

    public List<String> getAddress() {
        return address;
    }

    public List<String> getDetails() {
        return details;
    }

    public String toString() {
        return "Help4DevsNetworkInterfaceDto[" +
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
                ']';
    }
}
