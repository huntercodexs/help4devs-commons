package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsDriversDto {

    private List<String> id;
    private List<String> names;
    private List<String> vendor;
    private List<String> source;
    private List<String> description;
    private List<String> detail;
    private List<String> device;

    public Help4DevsDriversDto() {}

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public List<String> getDevice() {
        return device;
    }

    public void setDevice(List<String> device) {
        this.device = device;
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
