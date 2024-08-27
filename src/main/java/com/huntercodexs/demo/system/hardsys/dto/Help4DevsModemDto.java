package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsModemDto {

    private final List<String> modem;

    private String id;
    private String name;
    private String ports;
    private String speed;
    private String source;
    private String vendor;
    private String description;
    private List<String> features;

    public Help4DevsModemDto(List<String> modem) {
        this.modem = modem;
    }

    private String buildString(int i) {
        try {
            return this.modem.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.modem.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsModemDto builder() {
        this.id = buildString(0);
        this.name = buildString(1);
        this.ports = buildString(2);
        this.speed = buildString(3);
        this.source = buildString(4);
        this.vendor = buildString(5);
        this.description = buildString(6);
        this.features = buildCollection(7);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPorts() {
        return ports;
    }

    public String getSpeed() {
        return speed;
    }

    public String getSource() {
        return source;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String toString() {
        return "Help4DevsModemDto[" +
                "id=" + id +
                ", name=" + name +
                ", ports=" + ports +
                ", speed=" + speed +
                ", source=" + source +
                ", vendor=" + vendor +
                ", description=" + description +
                ", features=" + features +
                "]";
    }

}
