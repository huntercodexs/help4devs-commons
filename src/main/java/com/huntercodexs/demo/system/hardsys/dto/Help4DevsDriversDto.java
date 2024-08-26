package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsDriversDto {

    private final List<String> drivers;

    public Help4DevsDriversDto(List<String> drivers) {
        this.drivers = drivers;
    }

    public Help4DevsDriversDto builder() {
        return this;
    }

}
