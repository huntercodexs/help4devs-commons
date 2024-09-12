package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsMultimediaDto {

    private String qty;
    private List<Help4DevsMultimedia> details;

    public Help4DevsMultimediaDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsMultimedia> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsMultimedia> details) {
        this.details = details;
    }

    public void addMultimedia(Help4DevsMultimedia multimedia) {
        this.details.add(multimedia);
    }

    public String toString() {
        return "Help4DevsMultimediaDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsMultimedia {

        private String id;
        private String name;
        private String source;
        private String description;

        public Help4DevsMultimedia() {
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String toString() {
            return "Help4DevsMultimedia[" +
                    "id=" + id +
                    ", name=" + name +
                    ", source=" + source +
                    ", description=" + description +
                    "]";
        }
    }
}
