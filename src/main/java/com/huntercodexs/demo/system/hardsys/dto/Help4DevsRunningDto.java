package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsRunningDto {

    private final List<String> running;

    private List<String> id;
    private List<String> name;
    private List<String> source;
    private List<String> details;
    private List<String> description;

    public Help4DevsRunningDto(List<String> running) {
        this.running = running;
    }

    private String buildString(int i) {
        try {
            return this.running.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.running.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsRunningDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.source = buildCollection(2);
        this.details = buildCollection(3);
        this.description = buildCollection(4);
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

    public List<String> getDetails() {
        return details;
    }

    public List<String> getDescription() {
        return description;
    }

    public String toString() {
        return "Help4DevsRunningDto[" +
                "id=" + id +
                ", name=" + name +
                ", source=" + source +
                ", details=" + details +
                ", description=" + description +
                "]";
    }
}
