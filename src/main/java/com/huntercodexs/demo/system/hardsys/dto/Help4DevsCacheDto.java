package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsCacheDto {

    private String size;
    private String source;
    private String socket;
    private String cacheL1;
    private String cacheL2;
    private String cacheL3;
    private String location;
    private String description;
    private String configuration;
    private List<String> details;

    public Help4DevsCacheDto() {}

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getCacheL1() {
        return cacheL1;
    }

    public void setCacheL1(String cacheL1) {
        this.cacheL1 = cacheL1;
    }

    public String getCacheL2() {
        return cacheL2;
    }

    public void setCacheL2(String cacheL2) {
        this.cacheL2 = cacheL2;
    }

    public String getCacheL3() {
        return cacheL3;
    }

    public void setCacheL3(String cacheL3) {
        this.cacheL3 = cacheL3;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsCacheDto[" +
                "size=" + size +
                ", source=" + source +
                ", socket=" + socket +
                ", cacheL1=" + cacheL1 +
                ", cacheL2=" + cacheL2 +
                ", cacheL3=" + cacheL3 +
                ", location=" + location +
                ", description=" + description +
                ", configuration=" + configuration +
                ", details=" + details +
                "]";
    }
}
