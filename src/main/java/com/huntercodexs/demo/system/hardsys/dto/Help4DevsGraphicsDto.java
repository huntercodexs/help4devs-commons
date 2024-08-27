package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsGraphicsDto {

    private final List<String> graphics;

    private List<String> id;
    private List<String> bus;
    private List<String> vga;
    private List<String> name;
    private List<String> model;
    private List<String> memory;
    private List<String> board;
    private List<String> vendor;
    private List<String> driver;
    private List<String> source;
    private List<String> openGL;
    private List<String> version;
    private List<String> product;
    private List<String> renderer;
    private List<String> resources;
    private List<String> frequency;
    private List<String> multimedia;
    private List<String> description;
    private List<String> capabilities;
    private List<String> devices;
    private List<String> features;

    public Help4DevsGraphicsDto(List<String> graphics) {
        this.graphics = graphics;
    }

    private String buildString(int i) {
        try {
            return this.graphics.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.graphics.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsGraphicsDto builder() {
        this.id = buildCollection(0);
        this.bus = buildCollection(1);
        this.vga = buildCollection(2);
        this.name = buildCollection(3);
        this.model = buildCollection(4);
        this.memory = buildCollection(5);
        this.board = buildCollection(6);
        this.vendor = buildCollection(7);
        this.driver = buildCollection(8);
        this.source = buildCollection(9);
        this.openGL = buildCollection(10);
        this.version = buildCollection(11);
        this.product = buildCollection(12);
        this.renderer = buildCollection(13);
        this.resources = buildCollection(14);
        this.frequency = buildCollection(15);
        this.multimedia = buildCollection(16);
        this.description = buildCollection(17);
        this.capabilities = buildCollection(18);
        this.devices = buildCollection(20);
        this.features = buildCollection(21);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getBus() {
        return bus;
    }

    public List<String> getVga() {
        return vga;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getModel() {
        return model;
    }

    public List<String> getMemory() {
        return memory;
    }

    public List<String> getBoard() {
        return board;
    }

    public List<String> getVendor() {
        return vendor;
    }

    public List<String> getDriver() {
        return driver;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getOpenGL() {
        return openGL;
    }

    public List<String> getVersion() {
        return version;
    }

    public List<String> getProduct() {
        return product;
    }

    public List<String> getRenderer() {
        return renderer;
    }

    public List<String> getResources() {
        return resources;
    }

    public List<String> getFrequency() {
        return frequency;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public List<String> getDevices() {
        return devices;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String toString() {
        return "Help4DevsGraphicsDto[" +
                "id=" + id +
                ", bus=" + bus +
                ", vga=" + vga +
                ", name=" + name +
                ", model=" + model +
                ", memory=" + memory +
                ", board=" + board +
                ", vendor=" + vendor +
                ", driver=" + driver +
                ", source=" + source +
                ", openGL=" + openGL +
                ", version=" + version +
                ", product=" + product +
                ", renderer=" + renderer +
                ", resources=" + resources +
                ", frequency=" + frequency +
                ", multimedia=" + multimedia +
                ", description=" + description +
                ", capabilities=" + capabilities +
                ", devices=" + devices +
                ", features=" + features +
                ']';
    }
}
