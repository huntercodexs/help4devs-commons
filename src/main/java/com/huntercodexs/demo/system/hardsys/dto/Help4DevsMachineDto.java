package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsMachineDto {

    private final List<String> machine;

    private String uefi;
    private String name;
    private String type;
    private String date;
    private String mobo;
    private String code;
    private String model;
    private String serial;
    private String vendor;
    private String family;
    private String version;
    private String product;
    private String description;
    private String manufacturer;
    private List<String> features;

    public Help4DevsMachineDto(List<String> machine) {
        this.machine = machine;
    }

    private String buildString(int i) {
        try {
            return this.machine.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.machine.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsMachineDto builder() {
        this.uefi = buildString(0);
        this.name = buildString(1);
        this.type = buildString(2);
        this.date = buildString(3);
        this.mobo = buildString(4);
        this.code = buildString(5);
        this.model = buildString(6);
        this.serial = buildString(7);
        this.vendor = buildString(8);
        this.family = buildString(9);
        this.version = buildString(10);
        this.product = buildString(11);
        this.description = buildString(12);
        this.manufacturer = buildString(13);
        this.features = buildCollection(14);
        return this;
    }

    public String getUefi() {
        return uefi;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getMobo() {
        return mobo;
    }

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public String getVendor() {
        return vendor;
    }

    public String getFamily() {
        return family;
    }

    public String getVersion() {
        return version;
    }

    public String getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<String> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "Help4DevsMachineDto[" +
                "uefi=" + uefi +
                ", name=" + name +
                ", type=" + type +
                ", date=" + date +
                ", mobo=" + mobo +
                ", code=" + code +
                ", model=" + model +
                ", serial=" + serial +
                ", vendor=" + vendor +
                ", family=" + family +
                ", version=" + version +
                ", product=" + product +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                ", features=" + features +
                "]";
    }
}
