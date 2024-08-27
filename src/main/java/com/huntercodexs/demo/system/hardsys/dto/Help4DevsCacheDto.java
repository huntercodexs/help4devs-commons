package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsCacheDto {

    private final List<String> cache;

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

    public Help4DevsCacheDto(List<String> cache) {
        this.cache = cache;
    }

    private String buildString(int i) {
        try {
            return this.cache.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.cache.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsCacheDto builder() {
        this.size = buildString(0);
        this.source = buildString(1);
        this.socket = buildString(2);
        this.cacheL1 = buildString(3);
        this.cacheL2 = buildString(4);
        this.cacheL3 = buildString(5);
        this.location = buildString(6);
        this.description = buildString(7);
        this.configuration = buildString(8);
        this.details = buildCollection(9);
        return this;
    }

    public String getSize() {
        return size;
    }

    public String getSource() {
        return source;
    }

    public String getSocket() {
        return socket;
    }

    public String getCacheL1() {
        return cacheL1;
    }

    public String getCacheL2() {
        return cacheL2;
    }

    public String getCacheL3() {
        return cacheL3;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getConfiguration() {
        return configuration;
    }

    public List<String> getDetails() {
        return details;
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
