package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBridgeDto {

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String source;
    private String description;
    private List<String> details;

    public Help4DevsBridgeDto() {}

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
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
