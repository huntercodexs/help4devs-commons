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

        private List<String> id;
        private List<String> name;
        private List<String> source;
        private List<String> description;

        public Help4DevsMultimedia() {
        }

        public List<String> getId() {
            return id;
        }

        public void setId(List<String> id) {
            this.id = id;
        }

        public List<String> getName() {
            return name;
        }

        public void setName(List<String> name) {
            this.name = name;
        }

        public List<String> getSource() {
            return source;
        }

        public void setSource(List<String> source) {
            this.source = source;
        }

        public List<String> getDescription() {
            return description;
        }

        public void setDescription(List<String> description) {
            this.description = description;
        }

        public String toString() {
            return "Help4DevsMultimedia[" +
                    "id=" + id +
                    ", name=" + name +
                    ", source=" + source +
                    ", description=" + description +
                    ']';
        }
    }
}
