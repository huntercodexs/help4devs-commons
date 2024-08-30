package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsVideoDto {

    private String qty;
    private List<Help4DevsVideo> details;

    public Help4DevsVideoDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsVideo> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsVideo> details) {
        this.details = details;
    }

    public void addVideo(Help4DevsVideo video) {
        this.details.add(video);
    }

    public String toString() {
        return "Help4DevsVideoDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsVideo {

        private String id;
        private String bus;
        private String vga;
        private String name;
        private String model;
        private String memory;
        private String board;
        private String vendor;
        private String driver;
        private String source;
        private String openGL;
        private String version;
        private String product;
        private String renderer;
        private String resources;
        private String frequency;
        private String description;
        private String capabilities;
        private String devices;
        private List<String> features;

        public Help4DevsVideo() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBus() {
            return bus;
        }

        public void setBus(String bus) {
            this.bus = bus;
        }

        public String getVga() {
            return vga;
        }

        public void setVga(String vga) {
            this.vga = vga;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMemory() {
            return memory;
        }

        public void setMemory(String memory) {
            this.memory = memory;
        }

        public String getBoard() {
            return board;
        }

        public void setBoard(String board) {
            this.board = board;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getOpenGL() {
            return openGL;
        }

        public void setOpenGL(String openGL) {
            this.openGL = openGL;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getRenderer() {
            return renderer;
        }

        public void setRenderer(String renderer) {
            this.renderer = renderer;
        }

        public String getResources() {
            return resources;
        }

        public void setResources(String resources) {
            this.resources = resources;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(String capabilities) {
            this.capabilities = capabilities;
        }

        public String getDevices() {
            return devices;
        }

        public void setDevices(String devices) {
            this.devices = devices;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsVideo[" +
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
                    ", description=" + description +
                    ", capabilities=" + capabilities +
                    ", devices=" + devices +
                    ", features=" + features +
                    "]";
        }
    }
}
