package com.huntercodexs.demo.system.hardsys.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help4DevsUnknownDto {

    private final List<String> unknown;

    private List<String> id;
    private List<String> name;
    private List<String> source;
    private List<String> description;

    public Help4DevsUnknownDto(List<String> unknown) {
        this.unknown = unknown;
    }

    private String buildString(int i) {
        try {
            return this.unknown.get(i);
        } catch (RuntimeException re) {
            System.out.println("ERROR[String]: "+re.getMessage());
            return null;
        }
    }

    private List<String> buildCollection(int i) {
        try {
            return Collections.singletonList(this.unknown.get(i));
        } catch (RuntimeException re) {
            System.out.println("ERROR[Collection]: "+re.getMessage());
            return new ArrayList<>();
        }
    }

    public Help4DevsUnknownDto builder() {
        this.id = buildCollection(0);
        this.name = buildCollection(1);
        this.source = buildCollection(2);
        this.description = buildCollection(3);
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

    public List<String> getDescription() {
        return description;
    }

    public String toString() {
        return "Help4DevsUnknownDto[" +
                "id=" + id +
                ", name=" + name +
                ", source=" + source +
                ", description=" + description +
                ']';
    }
}
