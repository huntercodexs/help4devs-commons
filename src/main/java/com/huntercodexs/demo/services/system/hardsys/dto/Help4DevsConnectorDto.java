package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsConnectorDto {

    private String qty;
    private List<Help4DevsConnector> details;

    public Help4DevsConnectorDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsConnector> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsConnector> details) {
        this.details = details;
    }

    public void addConnector(Help4DevsConnector connector) {
        this.details.add(connector);
    }

    public String toString() {
        return "Help4DevsConnectorDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsConnector {

        private String type;
        private String source;
        private String description;

        public Help4DevsConnector() {}

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
            return "Help4DevsConnectorDto[" +
                    "type=" + type +
                    ", source=" + source +
                    ", description=" + description +
                    "]";
        }

    }
}
