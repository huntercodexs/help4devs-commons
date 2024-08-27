package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsConnectorDto {

    private final List<String> connector;

    private String type;
    private String source;
    private String description;

    public Help4DevsConnectorDto(List<String> connector) {
        this.connector = connector;
    }

    private String buildString(int i) {
        try {
            return this.connector.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.connector.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsConnectorDto builder() {
        this.type = buildString(0);
        this.source = buildString(1);
        this.description = buildString(2);
        return this;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "Help4DevsConnectorDto[" +
                "type=" + type +
                ", source=" + source +
                ", description=" + description +
                "]";
    }
}
