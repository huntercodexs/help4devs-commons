package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsRunningDto {

    private String qty;
    private List<Help4DevsRunning> details;

    public Help4DevsRunningDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsRunning> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsRunning> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsRunningDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public void addRunning(Help4DevsRunning running) {
        this.details.add(running);
    }

    public static class Help4DevsRunning {

        private String id;
        private String name;
        private String source;
        private String description;
        private List<String> features;

        public Help4DevsRunning() {}

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String toString() {
            return "Help4DevsRunning[" +
                    "id=" + id +
                    ", name=" + name +
                    ", source=" + source +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
