package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSystemDto {

    private final List<String> system;

    public Help4DevsSystemDto(List<String> system) {
        this.system = system;
    }

    public Help4DevsSystemDto builder() {
        return this;
    }

}
