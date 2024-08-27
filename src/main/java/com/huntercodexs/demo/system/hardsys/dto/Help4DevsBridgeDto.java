package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsBridgeDto {

    private final List<String> bridge;

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String source;
    private String description;
    private List<String> details;

    public Help4DevsBridgeDto(List<String> bridge) {
        this.bridge = bridge;
    }

    private String buildString(int i) {
        try {
            return this.bridge.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.bridge.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsBridgeDto builder() {
        this.id = buildString(0);
        this.name = buildString(1);
        this.type = buildString(2);
        this.vendor = buildString(3);
        this.source = buildString(4);
        this.description = buildString(5);
        this.details = buildCollection(6);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVendor() {
        return vendor;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDetails() {
        return details;
    }

    public String toString() {
        return "Help4DevsBridgeDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", source=" + source +
                ", description=" + description +
                ", details=" + details +
                "]";
    }
}
