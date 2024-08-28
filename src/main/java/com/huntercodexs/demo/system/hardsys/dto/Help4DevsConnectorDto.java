package com.huntercodexs.demo.system.hardsys.dto;

public class Help4DevsConnectorDto {

    private String type;
    private String source;
    private String description;

    public Help4DevsConnectorDto() {}

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
