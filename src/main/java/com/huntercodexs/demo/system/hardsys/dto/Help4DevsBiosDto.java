package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsBiosDto {

    private final List<String> bios;

    private String id;
    private String name;
    private String type;
    private String vendor;
    private String source;
    private String serial;
    private String version;
    private String product;
    private String release;
    private String firmware;
    private String revision;
    private String description;
    private String manufacturer;
    private List<String> details;
    private List<String> characteristics;

    public Help4DevsBiosDto(List<String> bios) {
        this.bios = bios;
    }

    private String buildString(int i) {
        try {
            return this.bios.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.bios.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsBiosDto builder() {
        this.id = buildString(0);
        this.name = buildString(1);
        this.type = buildString(2);
        this.vendor = buildString(3);
        this.source = buildString(4);
        this.serial = buildString(5);
        this.version = buildString(6);
        this.product = buildString(7);
        this.release = buildString(8);
        this.firmware = buildString(9);
        this.revision = buildString(10);
        this.description = buildString(11);
        this.manufacturer = buildString(12);
        this.details = buildCollection(13);
        this.characteristics = buildCollection(14);
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

    public String getSerial() {
        return serial;
    }

    public String getVersion() {
        return version;
    }

    public String getProduct() {
        return product;
    }

    public String getRelease() {
        return release;
    }

    public String getFirmware() {
        return firmware;
    }

    public String getRevision() {
        return revision;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<String> getDetails() {
        return details;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public String toString() {
        return "Help4DevsBiosDto[" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", vendor=" + vendor +
                ", source=" + source +
                ", serial=" + serial +
                ", version=" + version +
                ", product=" + product +
                ", release=" + release +
                ", firmware=" + firmware +
                ", revision=" + revision +
                ", description=" + description +
                ", manufacturer=" + manufacturer +
                ", details=" + details +
                ", characteristics=" + characteristics +
                "]";
    }
}
