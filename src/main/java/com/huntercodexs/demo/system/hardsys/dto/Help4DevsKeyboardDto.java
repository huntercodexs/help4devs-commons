package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsKeyboardDto {

    private final List<String> keyboard;

    private String id;
    private String qty;
    private String name;
    private String type;
    private String model;
    private String series;
    private String vendor;
    private String source;
    private String serial;
    private String layout;
    private String frequency;
    private String description;
    private List<String> details;

    public Help4DevsKeyboardDto(List<String> keyboard) {
        this.keyboard = keyboard;
    }

    private String buildString(int i) {
        try {
            return this.keyboard.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.keyboard.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsKeyboardDto builder() {
        this.id = buildString(0);
        this.qty = buildString(1);
        this.name = buildString(2);
        this.type = buildString(3);
        this.model = buildString(4);
        this.series = buildString(5);
        this.vendor = buildString(6);
        this.source = buildString(7);
        this.serial = buildString(8);
        this.layout = buildString(9);
        this.frequency = buildString(10);
        this.description = buildString(11);
        this.details = buildCollection(12);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getSeries() {
        return series;
    }

    public String getVendor() {
        return vendor;
    }

    public String getSource() {
        return source;
    }

    public String getSerial() {
        return serial;
    }

    public String getLayout() {
        return layout;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDetails() {
        return details;
    }

    public String toString() {
        return "Help4DevsKeyboardDto[" +
                ", id=" + id +
                ", qty=" + qty +
                ", name=" + name +
                ", type=" + type +
                ", model=" + model +
                ", series=" + series +
                ", vendor=" + vendor +
                ", source=" + source +
                ", serial=" + serial +
                ", layout=" + layout +
                ", frequency=" + frequency +
                ", description=" + description +
                ", details=" + details +
                "]";
    }
}
