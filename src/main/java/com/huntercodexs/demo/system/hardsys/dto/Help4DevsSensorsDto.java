package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSensorsDto {

    private final List<String> sensors;

    public Help4DevsSensorsDto(List<String> sensors) {
        this.sensors = sensors;
    }

    public Help4DevsSensorsDto builder() {
        return this;
    }

}
