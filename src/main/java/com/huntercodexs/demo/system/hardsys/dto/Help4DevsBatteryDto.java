package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBatteryDto {

    private final List<String> battery;

    public Help4DevsBatteryDto(List<String> battery) {
        this.battery = battery;
    }

    public Help4DevsBatteryDto builder() {
        return this;
    }

}
