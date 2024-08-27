package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsSensorsDto {

    private final List<String> sensors;

    private List<String> id;
    private List<String> name;
    private List<String> source;
    private List<String> driver;
    private List<String> details;
    private List<String> description;

    public Help4DevsSensorsDto(List<String> sensors) {
        this.sensors = sensors;
    }

    private String buildString(int i) {
        try {
            return this.sensors.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.sensors.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsSensorsDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.source = buildCollection(2);
        this.driver = buildCollection(3);
        this.details = buildCollection(4);
        this.description = buildCollection(5);
        return this;
    }

    public List<String> getId() {
        return id;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getSource() {
        return source;
    }

    public List<String> getDriver() {
        return driver;
    }

    public List<String> getDetails() {
        return details;
    }

    public List<String> getDescription() {
        return description;
    }

    public String toString() {
        return "Help4DevsSensorsDto[" +
                "id=" + id +
                ", name=" + name +
                ", source=" + source +
                ", driver=" + driver +
                ", details=" + details +
                ", description=" + description +
                "]";
    }
}
