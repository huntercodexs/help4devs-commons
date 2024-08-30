package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsKeyboardDto {

    private String qty;
    private List<Help4DevsKeyboard> details;

    public Help4DevsKeyboardDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsKeyboard> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsKeyboard> details) {
        this.details = details;
    }

    public void addKeyboard(Help4DevsKeyboard keyboard) {
        this.details.add(keyboard);
    }

    public String toString() {
        return "Help4DevsKeyboardDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsKeyboard {

        private String id;
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

        public Help4DevsKeyboard() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getLayout() {
            return layout;
        }

        public void setLayout(String layout) {
            this.layout = layout;
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

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public String toString() {
            return "Help4DevsKeyboard[" +
                    "id=" + id +
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
}
