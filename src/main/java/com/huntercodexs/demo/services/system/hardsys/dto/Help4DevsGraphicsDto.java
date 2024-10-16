package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsGraphicsDto {

    private String qty;
    private List<Help4DevsGraphics> details;

    public Help4DevsGraphicsDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsGraphics> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsGraphics> details) {
        this.details = details;
    }

    public void addGraphic(Help4DevsGraphics graphic) {
        this.details.add(graphic);
    }

    public String toString() {
        return "Help4DevsGraphicDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsGraphics {

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
        private String failed;
        private String openGL;
        private String server;
        private String devices;
        private String version;
        private String display;
        private String product;
        private String renderer;
        private String unloaded;
        private String resources;
        private String frequency;
        private String multimedia;
        private String resolution;
        private String description;
        private String capabilities;
        private List<String> features;

        public Help4DevsGraphics() {}

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

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
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

        public String getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(String multimedia) {
            this.multimedia = multimedia;
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

        public String getFailed() {
            return failed;
        }

        public void setFailed(String failed) {
            this.failed = failed;
        }

        public String getUnloaded() {
            return unloaded;
        }

        public void setUnloaded(String unloaded) {
            this.unloaded = unloaded;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String toString() {
            return "Help4DevsGraphics[" +
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
                    ", failed=" + failed +
                    ", openGL=" + openGL +
                    ", server=" + server +
                    ", devices=" + devices +
                    ", version=" + version +
                    ", display=" + display +
                    ", product=" + product +
                    ", renderer=" + renderer +
                    ", unloaded=" + unloaded +
                    ", resources=" + resources +
                    ", frequency=" + frequency +
                    ", multimedia=" + multimedia +
                    ", resolution=" + resolution +
                    ", description=" + description +
                    ", capabilities=" + capabilities +
                    ", features=" + features +
                    "]";
        }
    }
}
