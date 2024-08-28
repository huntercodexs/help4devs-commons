package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMultimediaDto {

    private List<String> id;
    private List<String> name;
    private List<String> source;
    private List<String> description;

    public Help4DevsMultimediaDto() {}

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
        return "Help4DevsMultimediaDto[" +
                "id=" + id +
                ", name=" + name +
                ", source=" + source +
                ", description=" + description +
                ']';
    }
}
