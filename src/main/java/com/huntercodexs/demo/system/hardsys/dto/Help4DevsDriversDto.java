package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsDriversDto {

    private final List<String> drivers;

    private List<String> id;
    private List<String> names;
    private List<String> vendor;
    private List<String> source;
    private List<String> description;
    private List<String> detail;
    private List<String> device;

    public Help4DevsDriversDto(List<String> drivers) {
        this.drivers = drivers;
    }

    private String buildString(int i) {
        try {
            return this.drivers.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.drivers.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsDriversDto builder() {
        this.id = buildCollection(0);
        this.names = buildCollection(1);
        this.vendor = buildCollection(2);
        this.source = buildCollection(3);
        this.description = buildCollection(4);
        this.detail = buildCollection(5);
        this.device = buildCollection(6);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getDetail() {
        return detail;
    }

    public List<String> getDevice() {
        return device;
    }

    public String toString() {
        return "Help4DevsDriversDto[" +
                "device=" + device +
                ", detail=" + detail +
                ", description=" + description +
                ", source=" + source +
                ", vendor=" + vendor +
                ", names=" + names +
                ", id=" + id +
                "]";
    }
}
