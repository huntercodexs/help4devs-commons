package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsUnknownDto {

    private String qty;
    private List<Help4DevsUnknown> details;

    public Help4DevsUnknownDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsUnknown> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsUnknown> details) {
        this.details = details;
    }

    public void addUnknown(Help4DevsUnknown unknown) {
        this.details.add(unknown);
    }

    public String toString() {
        return "Help4DevsUnknownDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsUnknown {

        private String id;
        private String name;
        private String source;
        private String vendor;
        private String description;

        public Help4DevsUnknown() {}

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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String toString() {
            return "Help4DevsUnknown[" +
                    "id=" + id +
                    ", name=" + name +
                    ", source=" + source +
                    ", vendor=" + vendor +
                    ", description=" + description +
                    "]";
        }
    }
}
