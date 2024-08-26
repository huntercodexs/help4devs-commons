package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsChassisDto {

    private final List<String> chassis;

    public Help4DevsChassisDto(List<String> chassis) {
        this.chassis = chassis;
    }

    public Help4DevsChassisDto builder() {
        return this;
    }

}
